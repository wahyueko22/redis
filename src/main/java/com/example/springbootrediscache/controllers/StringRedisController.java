package com.example.springbootrediscache.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootrediscache.models.Honda;
import com.example.springbootrediscache.models.User;
import com.example.springbootrediscache.repository.StringRedisRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.models.Response;

@RestController
@RequestMapping("/string")
public class StringRedisController {
	
	@Autowired
	private StringRedisRepository stringRedisRepository;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<?> add() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Honda honda = new Honda("handajazz", 3, false);
		String usrString = objectMapper.writeValueAsString(honda);
		stringRedisRepository.saveValue("string-honda", usrString, 1000L);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> get() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String strHonda = stringRedisRepository.getValue("string-honda");
		Honda honda = objectMapper.readValue(strHonda, Honda.class);
		return new ResponseEntity<>(honda, HttpStatus.OK);
	}
	
	@PostMapping("/addMap")
	public ResponseEntity<?> addMap() throws JsonProcessingException {
		 Map< String, String > properties = new HashMap< String, String >();
		 properties.put( "id", "hai" );
		 properties.put( "name", "namaku map");
		 properties.put( "email", "test@gmail.com" );
		 stringRedisRepository.saveHashes("mapKey", properties, 30L);
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/getMap")
	public ResponseEntity<?> getMap() throws JsonProcessingException {
		Map<Object, Object> properties = stringRedisRepository.getAllHash("mapKey");
		return new ResponseEntity<>(properties, HttpStatus.OK);
	}
	
}
