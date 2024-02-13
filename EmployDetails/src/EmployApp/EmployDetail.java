package EmployApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class EmployClass {

	private int empID;

	private String empName;

	private String empDesignation;

	private int empExperience;

	private float empSalary;

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public int getEmpExperience() {
		return empExperience;
	}

	public void setEmpExperience(int empExperience) {
		this.empExperience = empExperience;
	}

	public float getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(float empSalary) {
		this.empSalary = empSalary;
	}

	static HashMap<Integer, EmployClass> EmployDetail = new HashMap<>();

}

public class EmployDetail {

	private static void toCreateNewEmploy() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome - Creating a new Employ");

		EmployClass obj = new EmployClass();

		System.out.println("Enter Employ name:");
		obj.setEmpName(input.next());

		System.out.println("Enter Employ Designation:");
		obj.setEmpDesignation(input.next());

		System.out.println("Enter Employ Experience:");
		obj.setEmpExperience(input.nextInt());

		System.out.println("Enter Employ Salary:");
		obj.setEmpSalary(input.nextLong());

		DataBaseConnection.toCreateEmployeeDetails(obj);
	}

	public static void toRemoveEmploy() {
		System.out.println("-------------------------------------------");
		System.out.println("Welcome - Remove an Employ");
		System.out.println("-------------------------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Employ ID: ");
		int Id = input.nextInt();

		DataBaseConnection.toRemoveEmployData(Id);

	}

	public static void toUpdateEmploy() {
		System.out.println("-------------------------------------------");
		System.out.println("Welcome - Update an Employ");
		System.out.println("-------------------------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Employ ID: ");
		int Id = input.nextInt();
		if (DataBaseConnection.idExists(Id)) {
			System.out.println("1. Update Employ Name");
			System.out.println("2. Update Employ Designation");
			System.out.println("3. Update Employ Experience");
			System.out.println("4. Update Employ Salary");
			System.out.println("Enter a choice: ");
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the Updated Name: ");
				String name = input.next();
				DataBaseConnection.toUpdateAEmployName(name, Id);
				break;

			case 2:
				System.out.println("Enter the Updated Designation: ");
				String des = input.next();
				DataBaseConnection.toUpdateAEmployDesignation(des, Id);
				break;

			case 3:
				System.out.println("Enter the Updated Experience: ");
				int experience = input.nextInt();
				DataBaseConnection.toUpdateAEmployExperience(experience, Id);
				break;

			case 4:
				System.out.println("Enter the Updated Salary: ");
				float salary = input.nextFloat();
				DataBaseConnection.toUpdateAEmploySalary(salary, Id);
				break;

			default:
				System.err.println("Invalid Choice !!!\n");
				return;
			}
			System.out.println("Thank You - Employ details has been Successfully Updated \n");

		} else {
			System.out.println("ID does not Exist !!!\n");
		}

	}

	public static void toSearchEmploy() {
		System.out.println("-------------------------------------------");
		System.out.println("Welcome - Search an Employ");
		System.out.println("-------------------------------------------");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Employ ID: ");
		int Id = input.nextInt();

		if (DataBaseConnection.idExists(Id)) {
			EmployClass EmployDetail = DataBaseConnection.toGetAEmployData(Id);
			System.out.println("--------------------------------------------------");
			System.out.println("\t Employ Details");
			System.out.println("--------------------------------------------------");
			System.out.println("\t Employ Name: " + EmployDetail.getEmpName());
			System.out.println("\t Employ Designation: " + EmployDetail.getEmpDesignation());
			System.out.println("\t Employ Experience: " + EmployDetail.getEmpExperience());
			System.out.println("\t Employ Salary: " + EmployDetail.getEmpSalary());
		} else {
			System.out.println("ID does not Exist !!!\n");
		}
	}

	public static void toDisplayEmploy() {
		HashMap<Integer, EmployClass> EmployDetail = DataBaseConnection.toDisplayEmployData();

		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"Employ ID \t\t Employ Name \t\t Employ Designation \t\t Employ Experience \t\t Employ Salary");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------");

		for (Map.Entry<Integer, EmployClass> empData : EmployDetail.entrySet()) {
			System.out.print(empData.getKey() + "\t\t\t");
			System.out.print(empData.getValue().getEmpName() + "\t\t\t");
			System.out.print(empData.getValue().getEmpDesignation() + "\t\t\t");
			System.out.print(empData.getValue().getEmpExperience() + "\t\t\t");
			System.out.println(empData.getValue().getEmpSalary());
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------");

	}

	private static void printMenu() {
		System.out.println("1. To Create a new Employ :");
		System.out.println("2. To Remove a Employ :");
		System.out.println("3. To Update a Employ :");
		System.out.println("4. To Search a Employ :");
		System.out.println("5. To Display a Employ :");
		System.out.println("6. To Exit Application :");
		System.out.println("Enter a choice");

	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Boolean flag = true;

		System.out.println("-------------------------------------------");
		System.out.println("\t\t\t Employee Database system");
		System.out.println("-------------------------------------------");

		while (flag) {
			printMenu();
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				toCreateNewEmploy();
				break;
			case 2:
				toRemoveEmploy();
				break;
			case 3:
				toUpdateEmploy();
				break;
			case 4:
				toSearchEmploy();
				break;
			case 5:
				toDisplayEmploy();
				break;
			case 6:
				flag = false;
				System.out.println("Application is exited");
				break;
			default:
				System.out.println("Invalid Choice. !!!");
				break;
			}
		}

	}

}
