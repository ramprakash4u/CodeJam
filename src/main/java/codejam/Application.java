package codejam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import codejam.service.Palindrome;
import codejam.service.SumByInputKey;
import codejam.service.SumByKey;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	SumByKey sumByKey;
	
	@Autowired
	SumByInputKey sumByInputKey;
	
	@Autowired
	Palindrome palindrome;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {

		System.out.println("Spring boot method called...");
		//sumByInputKey.sumBykeyValues();
		palindrome.findPalindrome();
	}
}
