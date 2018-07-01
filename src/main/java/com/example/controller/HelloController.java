package com.example.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Camera;

@RestController
public class HelloController {
	
	@Autowired
	private Camera camera;
	
	@RequestMapping(value = "/hello")
	public String hello(){
		return "Hello";
	}
	
	@RequestMapping(value = "/camera")
	public String cam(){
		return camera.snap();
	}
	
	@RequestMapping(value = "/camera/{value}")
	public String exposure(@PathVariable("value") int expo){
		return camera.snap(expo);
	}
	
	@RequestMapping(value = "/camera/name/{value}")
	public String name(@PathVariable("value") String name){
		return camera.snap(name);
	}

}
