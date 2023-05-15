package com.project.QAserver;

import org.json.JSONObject;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.UUID;
/*
Format for JSON:

{"id":"[ID],"question":"[question","answers":[{"username":"[username]","answer":"[answer]"}]}

 */

public class PostManager {

    public void listablePost(int page){

    }
    public void addPost(){

    }

    public void addAnswer(){

    }

//  USE THE FOLLOWING CODE TO CREATE TEST QUESTIONS
    public void setupTest(){
        JSONObject testObject = new JSONObject();
        JSONObject[] answers = new JSONObject[4];
        String id;
        FileWriter writer = null;
        for (int i = 0; i < 10; i++) {
            id = UUID.randomUUID().toString();
            File file = new File("src/main/resources/postJSON/" + id + ".json");
            try {

                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error occured: " + e);
                throw new RuntimeException(e);
            }
            testObject.put("id", id);
            testObject.put("question", "question number " + i);
            for (int n = 0; n < 4; n++){
                answers[n] = new JSONObject();
                answers[n].put("username", "person number " + n);
                answers[n].put("answer", "answer number " + n);
            }
            testObject.put("answers", answers);

            try {
                writer = new FileWriter(file);
                writer.write(testObject.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
