package com.anusha.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Division
{
	public static void main(String[] args) throws Exception{
		
		System.out.println("***Program Excecution start*****");
		
		  int firstNumber = 9;
		  int secondNumber = 3; 
		  int result=0;
		  
		  try {
				
				result = firstNumber/secondNumber;
				}
				catch(ArithmeticException ae ){
					System.out.println("The exception details are : "+ae.toString());
					throw ae;	
				}
				finally {
					System.out.println("Iam the finally blocks executes for both try and catch");
				}
				
			
			System.out.println("The output for the division is : "+result);
		  
		  System.out.println("*******Program Exceution ended*******");
	}
}






/*		try {
			
		result = firstNumber/secondNumber;
		}
		catch(ArithmeticException ae ){
			System.out.println("The exception details are : "+ae.toString());
			throw ae;	
		}
		finally {
			System.out.println("Iam the finally blocks executes for both try and catch");
		}
		
		System.out.println("The output is : "+result);
		System.out.println("***Program Excecution ended*****");*/

    /*String filePath = "D:\\Softwares\\"; 
	    String fileName = "Learning.txt";
	    
		
		String fullPath = filePath + fileName;
		File file = new File(fullPath);
		FileInputStream fis = new FileInputStream(file);
		System.out.println(fis);*/

