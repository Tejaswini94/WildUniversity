
package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.bean.Student;
import com.example.exception.DuplicateStudentException;

// Model Layer 
public class StudentDAO {
	
	
	//Business logic 
	/*
	 * Adding/Inserting a new Student 
	 */
	public void addStudent(Student stud) throws DuplicateStudentException  {
		
		 
		//STEP 1 - Get connection  
		Connection conn = ConnectionFactory.getConnection();
		System.out.println(conn);
		
		// check if the student is not duplicate
		boolean flag = checkforDuplicateStudents(stud);
		
		if(flag) {
			// do not add the student to db
			throw new DuplicateStudentException("Student university number is duplicate !! It should be always unique");
			
		}else {
			// insert records in Student table
		
			String query = "insert into Student values(?,?)";
			try {
				//STEP 2 : Create a statement for query to execute 
				// pre-compiled queries 
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1,stud.getUno());
				stmt.setString(2,stud.getName());
			
				//STEP 3 : fires the query - inserts records in database
				stmt.executeUpdate(); // return type is int- number of rows affected 
							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
			
	}
	
	
	private boolean checkforDuplicateStudents(Student stud) {
		
		boolean flag = false;
		
		List<Student> students = getStudents();// return all students from db
				
		//for-each loop 
		for(Student student: students) {
			if(student.getUno().equals(stud.getUno())) {
				flag = true;
				break;
			}			
		}
		
		return flag;
		
		
	}


	/*
	 * Fetching all Students
	 */
	public List<Student> getStudents(){
		
		//STEP 1 : Getting connection		
		Connection conn = ConnectionFactory.getConnection();
		
		// create an arraylist which will store the student information
		List<Student> studentlist = new ArrayList<Student>();
		
		String query = "select * from student";
		
		try {
			//STEP 2 : Creating statement to execute query
			Statement stmt = conn.createStatement();
			
			// STEP 3 : Execute Query
			ResultSet rs = stmt.executeQuery(query);
			
			// STEP 4 : Iterating the ResultSet 
			while(rs.next()) {
				
				Student stud = new Student(rs.getString("uno"),rs.getString("name"));	
				studentlist.add(stud);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return studentlist;
		
	}


	/*
	 * Remove Student
	 */
	public void removeStudent(String unino) {
		
		
		// delete where uno= unino
		
	}
}
