package codejam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class SumByKey {
	Scanner in = new Scanner(System.in);
	

	static Map<String, ArrayList<Integer>> nameMap = new HashMap<String, ArrayList<Integer>> ();

	public String sumBykeyValues() {
		
		while(in.hasNext()) {
			String name = in.next();
			Integer value = in.nextInt();
			addNamesMap(name, value);
		} 
		
		nameMap.keySet().forEach(key -> System.out.println(key + "->" + nameMap.get(key)));
		
		Map<String, Integer> sumMapOut = sumNamesMap(nameMap);
		System.out.println("===============");
		sumMapOut.keySet().forEach(key -> System.out.println(key + "->" + sumMapOut.get(key)));
		return "test";
	}

	private void addNamesMap(String name, int i) {
		
		ArrayList<Integer> arrayVal = new ArrayList<Integer>();

		if(nameMap.containsKey(name)) {
			arrayVal = nameMap.get(name);
			arrayVal.add(i);
			nameMap.remove(name);
			nameMap.put(name, arrayVal);
		} else {
			arrayVal.add(i);
			nameMap.put(name, arrayVal); 
		}
		
	}

	private Map<String, Integer> sumNamesMap(Map<String, ArrayList<Integer>> nameMap) {
		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		
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


}

