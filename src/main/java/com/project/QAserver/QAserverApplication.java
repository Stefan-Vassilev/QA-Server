package com.project.QAserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class QAserverApplication {

	PostManager man = new PostManager();
	public static void main(String[] args) {
		SpringApplication.run(QAserverApplication.class, args);
	}
	@GetMapping("/")
	public String getListable(){
		man.setupTest();
		return "test";
	}
	@GetMapping("/next")
	public String next(){
		return "next";
	}
	@GetMapping("/previous")
	public String previous(){
		return "previous";
	}
	@GetMapping("/post/{id}")
	public String viewPost(@PathVariable(value="id") String postID){
		return "view post";
	}
	@GetMapping("/{postid}/{answer}/{description}/{username}")
	public void addAnswer(@PathVariable(value="postid") String postID, @PathVariable(value = "answer") String answer, @PathVariable(value = "description") String description, @PathVariable(value = "username") String username){

	}


}
