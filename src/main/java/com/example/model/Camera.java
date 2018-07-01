package com.example.model;

import org.springframework.stereotype.Component;

@Component
public class Camera{

	public String snap(){
		System.out.println("Snap!");
		return "Snap !";
	}
	
	public String snap(int exposure){
		System.out.println("Snap! Exposure : " + exposure);
		return "Snap ! Exposure : " + exposure;
	}
	
	public String snap(String name){
		System.out.println("Snap! Name : " + name);
		return "Snap !";
	}
}
