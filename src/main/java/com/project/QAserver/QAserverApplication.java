package com.project.QAserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class QAserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(QAserverApplication.class, args);
	}
	@GetMapping
	public String getListable(){

	}

}
