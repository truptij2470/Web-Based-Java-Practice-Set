package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import pojos.Employee;
import pojos.EmploymentType;

public class GetSelectedEmps {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); 
				SessionFactory sf = getFactory()) {
			EmployeeDao empDao = new EmployeeDaoImpl();
			System.out.println("Enter begin date , end date , min sal");
			List<Employee> list = empDao.getSelectedEmps(LocalDate.parse(sc.next()), LocalDate.parse(sc.next()),sc.nextDouble());
			System.out.println("Selected  emps : ");
			list.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
