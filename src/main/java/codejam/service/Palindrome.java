package codejam.service;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.stereotype.Service;

/**
 * This class find Palindrome for a given String.
 * @author Ram
 * @Date   10th May 2018
 * 
 * Assumption : In order to protect production system having attacker pass lengthy string and crash system, I have resricted 
 * 				Input String to max 100 character in length.
 * 	**
 * Input: String
 *         1. Takes input from SYSIN and adds to ArrayList
 *         2. Program (hasNext())  keep looking Input from SYSIN until end of file is reached
 *         3. if Input for test run is using Test file then it handles but if running on windows IDE then CNTR+Z on Console is to end the loop for SYSIN
 *         
 * Output: YES if String is Palindrome, NO if String is not Palindrome
 * Algorithm: 
 *         1. Takes input from SYSIN and adds to ArrayList.
 *         2. For each entry in ArrayList check if String is Palindrome.
 */
@Service
public class Palindrome {

	public static final Scanner in = new Scanner(System.in);

	public void findPalindrome() {
		ArrayList<String> strArray = new ArrayList<String>();

		//String[] Str = new String[] {"pop", "not a type writer", "Ah, Satan sees Natasha"};

		while(in.hasNextLine()) {
			String inStr = in.nextLine();
			validateInput(inStr);
			strArray.add(inStr);
		}
		
		for(String inputStr : strArray) {
			String isPalindrome = checkPalindrome(inputStr);
			System.out.println(isPalindrome);
		}
		
	}

	private static String checkPalindrome( String strInput) {
		String str1 = "";
		String outStr = "";
		strInput = strInput.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		int n = strInput.length();
		for(int i =n-1; i>=0; i--) {
			str1 = str1 + strInput.charAt(i);
		}

		if (strInput.equalsIgnoreCase(str1)) {
			outStr = "YES";
		} else {
			outStr = "NO";
		}
		return outStr;
	}

	/**
	 * 
	 * @param inStr
	 * Validator validates Input String is not > 100 in length
	 * 
	 */
	private void validateInput(String inStr) {

		if (inStr.length() > 100) {
			System.out.println("Error - Input String is too Long " +inStr.length() +">" +100);
			throw new RuntimeException("Error - Input String is too Long < 100");
		}

		if (inStr.isEmpty()) {
			System.out.println("Error - Input String is Empty, please enter valid String Input");
			throw new RuntimeException("Error - Input String is Empty, please enter valid String Input");
		}
		
		if ((inStr.trim().length()) < 1)
		 {
			System.out.println("Error - Input String is space String, please enter valid String Input");
			throw new RuntimeException("Error - Input String is space String, please enter valid String Input");
		}
	}
}
