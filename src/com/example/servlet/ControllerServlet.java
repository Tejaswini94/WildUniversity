package com.example.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bean.Student;
import com.example.dao.StudentDAO;
import com.example.exception.DuplicateStudentException;

/**
 * ControllerServlet - (C)
 * 1.accept the request 	
 * 2.navigate the request to the respective view(V) or model(M) 
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	
	StudentDAO dao = new StudentDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		if(action.equals("register")) {
			// navigate to view registration.jsp
			response.sendRedirect("registration.jsp");
		
		}else if(action.equals("display")) {
			
			List<Student> studList = dao.getStudents(); //get students from database
			ServletContext context = getServletContext();
			context.setAttribute("StudentList", studList);
					
			// navigate to view list.jsp
			response.sendRedirect("list.jsp");
		
		}else if(action.equals("registerNewStudent")) {
			// navigate to model (M)
			// get the data
			// set the data in Context scope
			// redirect to list.jsp (V)
			
			ServletContext context = getServletContext();
			
			String uno = request.getParameter("uno");//101
			String name = request.getParameter("name");
			Student student = new Student(uno, name);
			try {
				dao.addStudent(student);   //add Student in database
				List<Student> studList = dao.getStudents(); // getting all Students from database
				context.setAttribute("StudentList", studList);
				response.sendRedirect("list.jsp");
				
			} catch (DuplicateStudentException e) {
				//KEY VALUE PAIRING
				context.setAttribute("ErrorMessage",e.getMessage());
				response.sendRedirect("error.jsp");
				
			} 
			
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
