package dao;

import pojos.Employee;
import pojos.EmploymentType;

import org.hibernate.*;
import static utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String saveEmpDetails(Employee emp) {
		
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		System.out.println("session open " + session.isOpen() + " is connected " + session.isConnected());// t t
		try {
			session.persist(emp);// emp : PERSISTENT (exists in L1 cache) BUT not yet part of Db

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return "Emp details added , with ID " + emp.getEmpId();
	}

	@Override
	public Employee getEmpDetailsById(long empId) {
		Employee emp = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			emp = session.get(Employee.class, empId);
			emp = session.get(Employee.class, empId);
			emp = session.get(Employee.class, empId);
		
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return emp;
	}

	@Override
	public List<Employee> getAllEmps() {
		List<Employee> emps = null;
		String jpql = "select e from Employee e";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			emps = session.createQuery(jpql, Employee.class)
					.getResultList();
			
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return emps;
	}

	@Override
	public List<Employee> getSelectedEmps(LocalDate begin, LocalDate end, double minSal) {
		List<Employee> emps = null;
		String jpql = "select e from Employee e where e.joinDate between :begin_dt and :end_dt and e.salary > :min";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			emps = session.createQuery(jpql, Employee.class).setParameter("begin_dt", begin).setParameter("end_dt", end)
					.setParameter("min", minSal).getResultList();

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return emps;

	}

	@Override
	public List<String> getLastNamesByEmpType(EmploymentType type) {
		List<String> lastNames = null;
		String jpql = "select e.lastName from Employee e where e.empType=:ty";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			lastNames = session.createQuery(jpql, String.class).setParameter("ty", type).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return lastNames;
	}

	@Override
	public List<Employee> getSelectedEmpsByCtorExpression(EmploymentType type1) {
		List<Employee> emps = null;
		String jpql = "select new pojos.Employee(firstName,lastName,salary) from Employee e where e.empType=:type";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			emps = session.createQuery(jpql, Employee.class).setParameter("type", type1).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return emps;
	}

	@Override
	public String updateEmpSal(String email, String pwd, double increment) {
		String mesg = "Salary updating failed....";
		String jpql = "select e from Employee e where e.email=:em and e.password=:pass";
		Employee emp = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			emp = session.createQuery(jpql, Employee.class).setParameter("em", email).setParameter("pass", pwd)
					.getSingleResult();
			emp.setSalary(emp.getSalary() + increment);
			
			session.evict(emp);
			tx.commit();
			mesg = "emp's salary updated....";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		emp.setSalary(emp.getSalary() + increment);
		return mesg;
	}

	@Override
	public String bulkUpdateEmpSalaryByDate(double increment, LocalDate date) {
		String mesg = "Bulk updation failed.....";
		String jpql = "update Employee e set e.salary=e.salary+:incr where e.joinDate < :dt";
		// 1. Get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. Begin a tx
		Transaction tx = session.beginTransaction();
		try {
			int updateCount = session.createQuery(jpql).setParameter("incr", increment).setParameter("dt", date)
					.executeUpdate();
			tx.commit();
			mesg = "Updated " + updateCount + " no of emps....";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

	@Override
	public String deleteEmpDetails(Long empId) {
		String mesg="deleting emp details failed!!!!!";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Employee emp=null;
		try {
			emp = session.get(Employee.class, empId);
			if (emp != null) {
				
				session.delete(emp);
				mesg="emp details deleted.....";
			}
			tx.commit(); 
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return mesg;
	}

}
