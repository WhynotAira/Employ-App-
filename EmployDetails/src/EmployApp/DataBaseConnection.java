package EmployApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DataBaseConnection {
	private static String host = "jdbc:mysql://localhost:3306/employdb";
	private static String userName = "root";
	private static String userPassword = "AiraArif@2743";

	private static Connection toCreateConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		try {
			Connection con = DriverManager.getConnection(host, userName, userPassword);
			return con;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	public static void toCreateEmployeeDetails(EmployClass obj) {
		try {
			Connection con = toCreateConnection();
			String query = "Insert into emp (empName,empDesignation,empExperience,empSalary) values(?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, obj.getEmpName());
			stmt.setString(2, obj.getEmpDesignation());
			stmt.setInt(3, obj.getEmpExperience());
			stmt.setFloat(4, obj.getEmpSalary());

			stmt.executeUpdate();
			con.close();
			System.out.println("\n\n Thank You - Employ data Created Successfully \n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<Integer, EmployClass> toDisplayEmployData() {
		HashMap<Integer, EmployClass> EmployDetail = new HashMap<>();
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from emp");

			ResultSet rowData = stmt.executeQuery();

			while (rowData.next()) {
				EmployClass obj = new EmployClass();
				obj.setEmpID(rowData.getInt(1));
				obj.setEmpName(rowData.getString(2));
				obj.setEmpDesignation(rowData.getString(3));
				obj.setEmpExperience(rowData.getInt(4));
				obj.setEmpSalary(rowData.getFloat(5));

				EmployDetail.put(obj.getEmpID(), obj);
			}
			con.close();
			return EmployDetail;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void toRemoveEmployData(int id) {

		try {
			if (idExists(id)) {
				Connection con = toCreateConnection();
				PreparedStatement stmt = con.prepareStatement("Delete from emp where empId = ?");
				stmt.setInt(1, id);
				stmt.executeUpdate();
				con.close();
				System.out.println("Employ data is Removed Successfully ");
			} else {
				System.out.println("Employ data does not exist with this id !!!!!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean idExists(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from emp where empId = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			return rowData.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void toUpdateAEmployName(String name, int id) {

		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update emp set empName = ? where empId = ?");
			stmt.setString(1, name);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
			System.out.println("Employ data is Updated Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateAEmployDesignation(String des, int id) {

		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update emp set empDesignation = ? where empId = ?");
			stmt.setString(1, des);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
			System.out.println("Employ data is Updated Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateAEmployExperience(int exp, int id) {

		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update emp set empExperience = ? where empId = ?");
			stmt.setInt(1, exp);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
			System.out.println("Employ data is Updated Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateAEmploySalary(float sal, int id) {

		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update emp set empSalary = ? where empId = ?");
			stmt.setFloat(1, sal);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
			System.out.println("Employ data is Updated Successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static EmployClass toGetAEmployData(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from emp where empId = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			rowData.next();
			EmployClass obj = new EmployClass();
			obj.setEmpID(rowData.getInt(1));
			obj.setEmpName(rowData.getString(2));
			obj.setEmpDesignation(rowData.getString(3));
			obj.setEmpExperience(rowData.getInt(4));
			obj.setEmpSalary(rowData.getFloat(5));
			con.close();
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
