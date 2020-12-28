package com.edowidagdo.dummypln;

import com.edowidagdo.dummypln.rabbitmq.PlnReceive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DummyPlnApplication {

	public static PlnReceive receive = new PlnReceive();

	public static void main(String[] args) {
		SpringApplication.run(DummyPlnApplication.class, args);
		try {
			receive.getTagihanPln();
		} catch (Exception e) {
			System.out.println("Error Pln Server Main : " + e);
		}
	}

}
