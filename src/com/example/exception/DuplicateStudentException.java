package com.example.exception;


//Checked Exception
public class DuplicateStudentException extends Exception{

	
	public DuplicateStudentException(String msg) {
		super(msg);
	}
}
