package com.project.QAserver;

import org.json.JSONObject;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.UUID;


public class PostManager {
    public String test(){
        File temp = new File("src/main/resources/postJSON");
        JSONObject testJSON = new JSONObject();
        testJSON.put("ID", UUID.randomUUID());
        testJSON.put("question","question 1");
        ArrayList<JSONObject> jsonArrayList = new ArrayList<JSONObject>();
        testJSON.put("answers", jsonArrayList);

        return testJSON.toString();
    }
}
