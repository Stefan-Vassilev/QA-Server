package com.project.QAserver;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;
/*
Format for JSON:

{"id":"[ID],"question":"[question","answers":[{"username":"[username]","answer":"[answer]"}]}

 */

public class PostManager {

    public String listablePost(int page){

        File dir = new File("src/main/resources/postJSON/");
        File[] directoryListing = dir.listFiles();

        JSONArray jsonArray = new JSONArray();

        //JSONParser jsonParse = new JSONParser();
        for (int i = 0; i < 5; i++) {
            if(i!=0) {
                try {
                    assert directoryListing != null;
                    Scanner reader = new Scanner(directoryListing[i]);
                    String str = "";
                    while(reader.hasNextLine()) {
                        String temp = reader.nextLine();
                        str = str + temp;
                    }
                    JSONObject obj = new JSONObject(str);
                    jsonArray.put(obj);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return jsonArray.toString();
    }
    public void addPost(String question, String description){
        JSONObject tempPostJson = new JSONObject();
        //JSONObject[] nullAnswersJson = new JSONObject[];
        tempPostJson.put("question", question);
        tempPostJson.put("description",description);
        //tempPostJson.put("answers")


    }

    public void addAnswer(){

    }

//  USE THE FOLLOWING CODE TO CREATE TEST QUESTIONS
    public void setupTest(){
        JSONObject testObject = new JSONObject();
        JSONObject[] answers = new JSONObject[0];
        String id;
        FileWriter writer = null;
        for (int i = 0; i < 10; i++) {
            id = UUID.randomUUID().toString();
            File file = new File("src/main/resources/postJSON/" + id + ".json");
            try {

                file.createNewFile();
            } catch (IOException e) {
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
