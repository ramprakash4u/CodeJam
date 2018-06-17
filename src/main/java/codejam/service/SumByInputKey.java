package codejam.service;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Service;

/**
 * This class is to sum values by Key using LinkedHashMap in order to maintain Insertion Order.
 * 
 * Input - 
 *       1. Takes Input from SYSIN and adds that to LinkedHashMap
 *       2. Program (hasNext())  keep looking Input from SYSIN until end of file is reached
 *       3. if Input for test run is using Test file then it handles but if running on windows IDE then CNTR+Z on Console is to end the loop for SYSIN
 *       4. There are many ways to sum values by Keys, but below logic is to handle input comming as format (key,value).
 * Output -
 *       1. A LinkedHashMap with sum of values by same key(dublicate key as single entry key and summing values of all dublicate keys).
 *       2. Prints the final LinkedHashMap on colsole. 
 * 
 *       
 * Algorithm -
 *       1. Takes Input from SYSIN and adds that to LinkedHashMap<String, ArrayList<Integer>. ArrayList is needed for Values to handle dublicate key value entries.
 *       2. For every Input, the method addNamesMap() before adding, it checks if key already present in LinkedHashMap
 *       3. if key already present in LinkedHashMap then add new value to ArrayList and then in LinkedhashMap replace OldValue with NewValue of ArrayList.
 *       4. The final output from method  addNamesMap() will be LinkedHashMap having single entry for all dublicate keys with all values added to ArrayList- Ex - LinkedHashMap<String, ArrayList<Integer>> -> {Aaron,{3,1,2}}
 *       5. The method sumNamesMap() adds all values in ArrayList of Integer for given key in LinkedHashMap and Output (Key, value) where value being sum of all values for given Key.
 *       6. Prints the final LinkedHashMap on console SYSOUT.
 *       
 * @author Ram
 * @Date   6/10/2018
 *
 */

@Service
public class SumByInputKey  {
	
	static Map<String, ArrayList<Integer>> nameMap = new LinkedHashMap<String, ArrayList<Integer>> ();

	public void sumBykeyValues() throws Exception {
		//File myFile = new File("C:/Users/Dipti/Downloads/inputs/inputtest.txt");
		//Scanner in = new Scanner(myFile);
		
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()) {
			String inputKeyVal = in.next();
			validateInput(inputKeyVal);
			String[] arrayStr = inputKeyVal.split(",");
			String name = arrayStr[0];
			Integer value = Integer.parseInt(arrayStr[1]);
			addNamesMap(name, value);
		}


		Map<String, Integer> sumMapOut = sumNamesMap(nameMap);
		sumMapOut.keySet().forEach(key -> System.out.println(key + "," + sumMapOut.get(key)));

	}

	/**
	 * Takes Key and Value and adds all values to ArrayList if dublicate key else adds to LinkedhashMap as single entry 
	 * @param name
	 * @param i
	 * @return LinkedHashMap{Key, Value{v1,v2,v3}}
	 */
	private void addNamesMap(String name, int i) {

		ArrayList<Integer> arrayVal = new ArrayList<Integer>();

		if(nameMap.containsKey(name)) {
			arrayVal = nameMap.get(name);
			ArrayList<Integer> arrayOldVal = arrayVal;
			arrayVal.add(i);
			nameMap.replace(name, arrayOldVal, arrayVal);
			nameMap.put(name, arrayVal);
		} else {
			arrayVal.add(i);
			nameMap.put(name, arrayVal); 
		}

	}

	/**
	 * 
	 * @param  Map<String, ArrayList<Integer>> ->   LinkedHashMap{Key, Value{v1,v2,v3}}  as nameMap
	 * @return LinkedHashMap{Key,Value} as sumMap. Where Value being sum of all values in arrayList
	 * 
	 */
	private Map<String, Integer> sumNamesMap(Map<String, ArrayList<Integer>> nameMap) {
		Map<String, Integer> sumMap = new LinkedHashMap<String, Integer>();

		for(Map.Entry<String, ArrayList<Integer>> entry : nameMap.entrySet()) {
			String name = entry.getKey();
			ArrayList<Integer> arrayVal = entry.getValue();
			int a = arrayVal.stream()
					.mapToInt(i -> i)
					.sum();

			sumMap.put(name, a);        
		}

		return sumMap;
	}

	private void validateInput(String mapInput) {
		
		if (mapInput.length() > 100) {
			System.out.println("Error - Input String is too Long " +mapInput.length() +">" +100);
			throw new RuntimeException("Error - Input String is too Long < 100");
		}
		
		Integer value = 0;
		String[] arrayStr = mapInput.split(",");
		String name = arrayStr[0];
		
		if (arrayStr.length < 2) {
			System.out.println("Error - Comma seprator missing between key, value " +mapInput);
			throw new RuntimeException("Error - Comma separator missing between key, value. Please enter Key,Value seprated by comma" +mapInput);
		}
		
		try {
			value = Integer.parseInt(arrayStr[1]);
		} catch (NumberFormatException ex) {
			System.out.println("Error - value for key is not Integer" +arrayStr[1]);
			throw new NumberFormatException("Error - value for key is not Integer, please enter integer for value as Key,Value ->:" +arrayStr[0] +"," +arrayStr[1]);
		}

		int x = (int) value;
		if (!(x == value)) {
			System.out.println("Error - Float found instead of Integer for value" +arrayStr[1]);
			throw new NumberFormatException("Error - Float found instead of Integer for KeyValue, please enter integer for value as Key,Value ->:" +arrayStr[0] +"," +arrayStr[1]);
		}
		
		
		
	}
}
