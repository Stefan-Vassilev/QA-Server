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
    public void viewPost(){

    }
    public String addPost(String question, String description){
        JSONObject tempPostJson = new JSONObject();
        JSONObject[] nullAnswersJson = new JSONObject[0];
        tempPostJson.put("question", question);
        tempPostJson.put("description",description);
        tempPostJson.put("answers",nullAnswersJson);
        System.out.println(tempPostJson);

        return tempPostJson.toString();

        //tempPostJson.put("answers")


    }

    public void addAnswer(String questionID, String username, String answer, String description){
        //Scanner reader = new Scanner("src/main/resources/postJSON/" + questionID + ".json");
        File file = new File("src/main/resources/postJSON/0ffab7fd-5ece-49ea-9f59-e0955cdebfad.json");
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String str = "";
        while (reader.hasNextLine()){
            String temp = reader.nextLine();
            str = str + temp;
        }
        JSONObject tempJson = new JSONObject(str);
        JSONArray previousList = new JSONArray(tempJson.get("answers").toString());
        JSONObject newAnswerJson = new JSONObject();
        newAnswerJson.put("username", username);
        newAnswerJson.put("answer", answer);
        newAnswerJson.put("description", description);
        previousList.put(newAnswerJson);
        tempJson.put("answers", previousList);
        System.out.println(tempJson);
    }

//  USE THE FOLLOWING CODE TO CREATE TEST JSON FILES WITH THE PROPER FORMAT
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
