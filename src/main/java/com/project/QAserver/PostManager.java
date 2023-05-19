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

        int firstPostIndex = (page - 1) * 5;
        int lastPostIndex = firstPostIndex + 4;
        Scanner reader = null;
        //JSONParser jsonParse = new JSONParser();
//        for (int i = firstPostIndex; i < lastPostIndex; i++) {
        String str = "";
        for(int i = 0; i < 5; i++){

                try {
                    assert directoryListing != null;
                    reader = new Scanner(directoryListing[i]);

                    while(reader.hasNextLine()) {
                        String temp = reader.nextLine();
                        System.out.println("Temp: " + temp);
                        str = str + temp;
                    }
                    reader.close();
                    System.out.println("Str:" + str);
                    JSONObject obj = new JSONObject(str);
                    jsonArray.put(obj);

                } catch (IOException e) {
                    System.out.println("ERROR OCCUREDDDDDDDDDDDDD");
                    e.printStackTrace();
                }


        }

        return jsonArray.toString();
    }
    public String viewPost(String questionID) {
        File file = new File("src/main/resources/postJSON/" + questionID + ".json");
        Scanner reader;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String str = "";
        while (reader.hasNextLine()) {
            String temp = reader.nextLine();
            str = str + temp;
        }
        JSONObject tempJson = new JSONObject(str);
        return tempJson.toString();


    }
    public String addPost(String question, String description){
        JSONObject tempPostJson = new JSONObject();
        JSONObject[] nullAnswersJson = new JSONObject[0];
        String id;
        id = UUID.randomUUID().toString();
        tempPostJson.put("id", id);
        tempPostJson.put("question", question);
        tempPostJson.put("description",description);
        tempPostJson.put("answers",nullAnswersJson);


        File file = new File("src/main/resources/postJSON/" + id + ".json");
        try {

            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(tempPostJson.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return tempPostJson.toString();

        //tempPostJson.put("answers")


    }

    public void addAnswer(String questionID, String username, String answer, String description){
        //Scanner reader = new Scanner("src/main/resources/postJSON/" + questionID + ".json");
        File file = new File("src/main/resources/postJSON/" + questionID + ".json");
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

        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(tempJson.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
