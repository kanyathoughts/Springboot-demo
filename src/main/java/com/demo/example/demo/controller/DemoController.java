package com.demo.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {
	
	Map<String, String> map = new HashMap<>();
	Path path = Paths.get("Sample.txt");
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello world!";
	}
	
	@PostMapping("/postMessage")
	public void postMessage(@RequestBody String message) {
		map.put("message", message);
	}
	
	@GetMapping("/getMessage")
	public String getMessage() {
		return map.get("message");
	}
	
	@PostMapping("/postMessageToFile")
	public void postMessageToFile(@RequestBody String message) throws IOException {
		Files.writeString(path, message);
	}
	
	@PutMapping("/putMessageToFile")
	public void putMessage(@RequestBody String updatedMessage) throws IOException {
		Files.writeString(path, updatedMessage);
		map.put("message", updatedMessage);
	}
	
	@GetMapping("/getMessages")
	public Map<String,String> getMessages() throws IOException {
		Map<String, String> localMap = new HashMap<String, String>();
		localMap.put("message from hashmap", map.get("message"));
		localMap.put("message from file", Files.readString(path));
		return localMap;
	}
}