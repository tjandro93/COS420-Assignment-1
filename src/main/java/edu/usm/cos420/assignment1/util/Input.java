package edu.usm.cos420.assignment1.util;

import java.util.Scanner;

/**
 * Utility class to allow for static access to a single Scanner reading from 
 * System.in
 * 
 * Follows a basic singleton pattern
 * 
 */
public class Input {

	private static Input instance = new Input();

	/** {@value #INVALID_INT} : signifies the parsed input was not an integer */
	public static final int INVALID_INT = Integer.MIN_VALUE;
	
	private Scanner in;
	
	private Input(){
		this.in = new Scanner(System.in);
	}
	
	/**
	 * Read the input line as an integer
	 * @return the integer input or 
	 * {@code INVALID_INT} if the input is not an integer
	 */
	public static int readInt(){
		String input = instance.in.nextLine();
		try{
			return Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			return INVALID_INT;
		}
	}
	
	/**
	 * Read the input up until a newline
	 * @return the input read from System.in
	 */
	public static String readLine(){
		return instance.in.nextLine();
	}
	
	/**
	 * Prompt user for confirmation
	 */
	public static boolean getConfirmation(){
		System.out.println("Enter Y to confirm");
		String input = Input.readLine();
		return input.equalsIgnoreCase("Y");
	}
}
