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
	int page = 1;
	public static void main(String[] args) {
		SpringApplication.run(QAserverApplication.class, args);
	}
	@GetMapping("/")
	public String getListable(){

		return man.listablePost(page);
	}
	@GetMapping("/next")
	public String next(){
		page = page + 1;
		return man.listablePost(page);
	}
	@GetMapping("/previous")
	public String previous(){
		if(page > 1){
			page = page - 1;
			return man.listablePost(page);
		} else{
			return man.listablePost(1);
		}
	}
	@GetMapping("/post/{id}")
	public String viewPost(@PathVariable(value="id") String postID){
		return man.viewPost(postID);
	}
	@GetMapping("/post/{postid}/{answer}/{description}/{username}")
	public void addAnswer(@PathVariable(value="postid") String postID, @PathVariable(value = "answer") String answer, @PathVariable(value = "description") String description, @PathVariable(value = "username") String username){
		man.addAnswer(postID,username,answer,description);
	}
	@GetMapping("/post/create/{question}/{description}")
	public void addPost(@PathVariable(value="question") String question, @PathVariable(value = "description") String description){
		man.addPost(question,description);
	}


}
