package emp_project1;

import java.sql.*;
import java.util.Scanner;


public class employee_project1 {

	 static Connection conn;//static is loaded the program to memory management
	 static Statement stmt;
	 static Scanner scanner = new Scanner(System.in);

	 public static void main(String[] args) { //main method
	  boolean loggedIn = false; //to check the function true or false

	  while (!loggedIn) { 
	   try { //try is for exception handling and it is error 
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emppefm", "root", "root");
	    stmt = conn.createStatement();
	    loggedIn = true; // Set loggedIn to true to exit the loop when the connection is established
	   } catch (ClassNotFoundException | SQLException e) {
	    e.printStackTrace();
	   }
	  }

	  while (true) {
	   System.out.println("Employee Performance Evaluation");
	   System.out.println("1. Manager Login");
	   System.out.println("2. Employee Login");
	   System.out.println("3. Exit");
	   System.out.print("Enter your choice: ");
	   int choice = scanner.nextInt();
	   scanner.nextLine(); // Consume the newline character

	   switch (choice) {
	   case 1:
	    managerLogin();
	    break;
	   case 2:
	    employeeLogin();
	    break;
	   case 3:
	    exit();
	    break;
	   default:
	    System.out.println("Invalid choice. Please try again.");
	   }
	  }
	 }

	 static void managerLogin() {
	  boolean loggedIn = false;

	  while (!loggedIn) {
	   try {
	    System.out.println("Manager Login");
	    System.out.print("Enter username: ");
	    String username = scanner.nextLine();

	    System.out.print("Enter password: ");
	    String password = scanner.nextLine();

	    String selectQuery = "SELECT * FROM MANAGER_LOGIN WHERE username = ? AND password = ?";
	    PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
	    preparedStatement.setString(1, username);
	    preparedStatement.setString(2, password);

	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {
	     System.out.println("Login successful! Welcome, " + username);
	     loggedIn = true; // Set the flag to exit the loop
	    } else {
	     System.out.println("Login failed. Invalid username or password, try again!");
	    }
	   } catch (SQLException e) {
	    e.printStackTrace();
	   }
	  }

	  while (loggedIn) {
	   System.out.println("\nManager Menu");
	   System.out.println("1. Add Employee Details");
	   System.out.println("2. Add Evaluation Details");
	   System.out.println("3. Update Evaluation Details");
	   System.out.println("4. Delete Employee");
	   System.out.println("5. Delete Evaluation Details");
	   System.out.println("6. Logout");
	   System.out.print("Enter your choice: ");
	   int managerChoice = scanner.nextInt();
	   scanner.nextLine(); // Consume the newline character

	   switch (managerChoice) {
	   case 1:
	    addEmployee();
	    break;
	   case 2:
	    addEvaluation();
	    break;
	   case 3:
	    updateEvaluation();
	    break;
	   case 4:
	    deleteEmployee();
	    break;
	   case 5:
	    deleteEvaluation();
	    break;
	   case 6:
	    loggedIn = false; // Logout and return to the main menu
	    break;
	   default:
	    System.out.println("Invalid choice. Please try again.");
	   }
	  }
	 }

	 static void employeeLogin() {
	  boolean loggedIn = false;

	  while (!loggedIn) {
	   try {
	    System.out.println("Employee Login");
	    System.out.print("Enter username: ");
	    String username = scanner.nextLine();

	    System.out.print("Enter password: ");
	    String password = scanner.nextLine();

	    String selectQuery = "SELECT * FROM EMPLOYEE_LOGIN WHERE username = ? AND password = ?";
	    PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
	    preparedStatement.setString(1, username);
	    preparedStatement.setString(2, password);

	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {
	     System.out.println("Login successful! Welcome, " + username);
	     loggedIn = true; // Set the flag to exit the loop
	    } else {
	     System.out.println("Login failed. Invalid username or password.");
	    }
	   } catch (SQLException e) {
	    e.printStackTrace();
	   }
	  }

	  while (loggedIn) {
	   System.out.println("\nEmployee Menu");
	   System.out.println("1. Display Employee Performance");
	   System.out.println("2. Logout");
	   System.out.print("Enter your choice: ");
	   int employeeChoice = scanner.nextInt();
	   scanner.nextLine(); // Consume the newline character

	   switch (employeeChoice) {
	   case 1:
	    displayEmployeePerformance();
	    break;
	   case 2:
	    loggedIn = false; // Logout and return to the main menu
	    break;
	   default:
	    System.out.println("Invalid choice. Please try again.");
	   }
	  }
	 }

	 // Add other methods for managing employee details, evaluations, etc.

	 static void addEmployee() {
	  try {
	   System.out.println("Add Employee");

	   // System.out.print("Enter Employee Id: ");
	   // int Id = scanner.nextInt();

	   System.out.print("Enter Employee Name: ");
	   String name = scanner.nextLine();

	   System.out.print("Enter Email: ");
	   String email = scanner.nextLine();

	   System.out.print("Enter Gender: ");
	   String gender = scanner.nextLine();

	   System.out.print("Enter Hiredate: ");
	   String hiredate = scanner.nextLine();

	   System.out.print("Enter Designation: ");
	   String designation = scanner.nextLine();

	   System.out.print("Enter Salary: ");
	   Float salary = scanner.nextFloat();

	   System.out.print("Enter Mobile Number: ");
	   long mobileno = scanner.nextLong();



	   // You can add more fields 

	   String insertQuery = "INSERT INTO Employee (Name, Email, Gender, Hiredate, Designation, Salary, Mobileno) VALUES (?, ?, ?, ?, ?, ?, ?)";
	   PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	   // preparedStatement.setInt(1, Id);
	   preparedStatement.setString(1, name);
	   preparedStatement.setString(2, email);
	   preparedStatement.setString(3, gender);
	   preparedStatement.setString(4, hiredate);
	   preparedStatement.setString(5, designation);
	   preparedStatement.setFloat(6, salary);
	   preparedStatement.setLong(7, mobileno);

	   int rowsInserted = preparedStatement.executeUpdate();

	   if (rowsInserted > 0) {
	    System.out.println("Employee added successfully!");
	   } else {
	    System.out.println("Failed to add the Employee.");
	   }
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	 }
	 static void addEvaluation() {
	  try {
	   System.out.println("Add Evaluation Details");

	   System.out.print("Enter Employee ID: ");
	   int Id = scanner.nextInt();
	   scanner.nextLine();

	   System.out.print("Enter Evaluation Date: ");
	   String date = scanner.nextLine();

	   System.out.print("Enter Rating: ");
	   int rating = scanner.nextInt();
	   scanner.nextLine();

	   System.out.print("Enter Feedback: ");
	   String feedback = scanner.nextLine();


	   // You can add more fields 

	   String insertQuery = "INSERT INTO Evaluation (Id, EvaluationDate, Rating, Feedback) VALUES (?, ?, ?, ?)";
	   PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
	   preparedStatement.setInt(1, Id);
	   preparedStatement.setString(2, date);
	   preparedStatement.setInt(3, rating);
	   preparedStatement.setString(4, feedback);

	   int rowsInserted = preparedStatement.executeUpdate();

	   if (rowsInserted > 0) {
	    System.out.println("Evaluation Detials added successfully!");
	   } else {
	    System.out.println("Failed to add the Evaluation Details.");
	   }
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	 }

	 static void updateEvaluation() {
	  try {
	   System.out.println("Update an Evaluation Details");
	   System.out.print("Enter Evaluation Id to update: ");
	   String Id = scanner.nextLine();

	   // Check if the Evaluation with the provided Id exists
	   String selectQuery = "SELECT * FROM Evaluation WHERE Id=?";
	   PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
	   selectStatement.setString(1, Id);

	   ResultSet resultSet = selectStatement.executeQuery();

	   if (resultSet.next()) {
	    // Employee exists, allow updates
	    System.out.print("Enter new Evaluation Date (leave empty to keep existing): ");
	    String Date = scanner.nextLine();

	    System.out.print("Enter new Rating (leave empty to keep existing): ");
	    String rating = scanner.nextLine();

	    System.out.print("Enter new Feedback (leave empty to keep existing): ");
	    String feedback = scanner.nextLine();

	    // Build the update query dynamically based on user input
	    StringBuilder updateQuery = new StringBuilder("UPDATE Evaluation SET ");
	    boolean needsComma = false;

	    if (!Date.isEmpty()) {
	     updateQuery.append("EvaluationDate = ?");
	     needsComma = true;
	    }

	    if (!rating.isEmpty()) {
	     if (needsComma) {
	      updateQuery.append(", ");
	     }
	     updateQuery.append("Rating = ?");
	     needsComma = true;
	    }

	    if (!feedback.isEmpty()) {
	     if (needsComma) {
	      updateQuery.append(", ");
	     }
	     updateQuery.append("Feedback = ?");
	    }

	    updateQuery.append(" WHERE Id = ?");
	    PreparedStatement updateStatement = conn.prepareStatement(updateQuery.toString());

	    int parameterIndex = 1;

	    if (!Date.isEmpty()) {
	     updateStatement.setString(parameterIndex++, Date);
	    }

	    if (!rating.isEmpty()) {
	     updateStatement.setInt(parameterIndex++, Integer.parseInt(rating));
	    }

	    if (!feedback.isEmpty()) {
	     updateStatement.setString(parameterIndex++, feedback);
	    }

	    updateStatement.setString(parameterIndex, Id);

	    int rowsUpdated = updateStatement.executeUpdate();

	    if (rowsUpdated > 0) {
	     System.out.println("Evaluation updated successfully!");
	    } else {
	     System.out.println("No changes made to the Evaluation.");
	    }
	   } else {
	    System.out.println("Evaluation with Id " + Id + " does not exist.");
	   }
	  } catch (SQLException e) {
	   // Handle SQLException properly, e.g., log the error
	   e.printStackTrace();
	  }
	 }

	 static void deleteEmployee() {
	  try {
	   System.out.println("Delete a Employee Details");
	   System.out.print("Enter Employee Id to delete: ");
	   String Id = scanner.nextLine();

	   // Check if the Employee with the provided Id exists
	   String selectQuery = "SELECT * FROM Employee WHERE Id = ?";
	   PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
	   selectStatement.setString(1, Id);

	   ResultSet resultSet = selectStatement.executeQuery();

	   if (resultSet.next()) {
	    // Book exists, proceed with deletion
	    String deleteQuery = "DELETE FROM Employee WHERE Id = ?";
	    PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
	    deleteStatement.setString(1, Id);

	    int rowsDeleted = deleteStatement.executeUpdate();

	    if (rowsDeleted > 0) {
	     System.out.println("Employee deleted successfully!");
	    } else {
	     System.out.println("Failed to delete the Employee.");
	    }
	   } else {
	    System.out.println("Employee with Id " + Id + " does not exist.");
	   }
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	 }


	 static void deleteEvaluation() {
	  try {
	   System.out.println("Delete a Evaluation Details");
	   System.out.print("Enter Evaluation Id to Delete: ");
	   String Id = scanner.nextLine();

	   // Check if the Employee with the provided Id exists
	   String selectQuery = "SELECT * FROM Evaluation WHERE Id = ?";
	   PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
	   selectStatement.setString(1, Id);

	   ResultSet resultSet = selectStatement.executeQuery();

	   if (resultSet.next()) {
	    // Employee exists, proceed with deletion
	    String deleteQuery = "DELETE FROM Evaluation WHERE Id = ?";
	    PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
	    deleteStatement.setString(1, Id);

	   // int rowsDeleted = deleteStatement.executeUpdate();
	    long rowsDeleted = deleteStatement.executeLargeUpdate();


	    if (rowsDeleted > 0) {
	     System.out.println("Evaluation deleted successfully!");
	    } else {
	     System.out.println("Failed to delete the Evaluation.");
	    }
	   } else {
	    System.out.println("Evaluation with Id " + Id + " does not exist.");
	   }
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	 }


	 static void displayEmployeePerformance() {
	  try {
		  System.out.println("Display evaluation details");
		   System.out.print("Display Employee Id to display: ");
		   String Id = scanner.nextLine();

		   // Check if the Employee with the provided Id exists
		  		   
		   System.out.println("Please Enter the name to search:");
		   String Names = scanner.nextLine();
	   String selectQuery1 = "SELECT e.Id, e.Name, e.Email, ev.EvaluationDate, ev.Rating, ev.Feedback "
	     + "FROM Employee e "
	     + "INNER JOIN Evaluation ev ON e.Id = ev.Id where e.id = ? and e.name = ?";
	   PreparedStatement preparedStatement = conn.prepareStatement(selectQuery1);
	   preparedStatement.setString(1, Id);
	   preparedStatement.setString(2, Names);


	   ResultSet resultSet1 = preparedStatement.executeQuery();

	   // Check if there are any employee performance details in the database
	   boolean hasPerformanceDetails = false;
	   while (resultSet1.next()) {
	    hasPerformanceDetails = true;
	    int Id1 = resultSet1.getInt("Id");
	    String Name = resultSet1.getString("Name");
	    String Email = resultSet1.getString("Email");
	    String date = resultSet1.getString("EvaluationDate");
	    double rating = resultSet1.getDouble("Rating");
	    String feedback = resultSet1.getString("Feedback");

	    // Display employee performance information

	    System.out.println("Employee ID: " + Id1);
	    System.out.println("Employee Name: " + Name);
	    System.out.println("Employee Email: " + Email);
	    System.out.println("Employee Evaluation Date: " + date);
	    System.out.println("Performance Rating: " + rating);
	    System.out.println("Employee Feedback: " + feedback);
	    System.out.println("-------------------");
	   }
	   if (!hasPerformanceDetails) {
	    System.out.println("No employee performance details found in the database.");
	   }
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	 }
	 

	 static void exit() {
	  try {
	   conn.close();
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  System.exit(0);
	 }
	}