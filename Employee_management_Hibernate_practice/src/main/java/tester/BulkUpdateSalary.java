package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import pojos.Employee;
import pojos.EmploymentType;

public class BulkUpdateSalary {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = getFactory()) {
			// create dao instance
			EmployeeDao empDao = new EmployeeDaoImpl();
			System.out.println("Enter emp increment n join date");
			System.out.println(empDao.
					bulkUpdateEmpSalaryByDate(sc.nextDouble(),
							LocalDate.parse(sc.next())));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
