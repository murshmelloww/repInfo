package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String repoendpoint = "stleary/JSON-java";
        String releasesendpoint  = repoendpoint + "/releases";
        String response = response(repoendpoint);
        JSONObject jsonObject = new JSONObject(response);
        System.out.println("Repo Name: "+ releasesendpoint);
        System.out.println("Stars: " + jsonObject.getInt("stargazers_count"));
        response = response(releasesendpoint);
        JSONArray jsonArray = new JSONArray(response);
        System.out.println("Releases: " + jsonArray.length());
        if(jsonArray.length()!=0)
        {
            jsonObject = jsonArray.getJSONObject(0);
            System.out.print("Last release: " + jsonObject.getString("url"));
        }
        else
            System.out.print("Last release: no releases");

    }

    public static String response(String endpoint) throws IOException {
        URL url = new URL("https://api.github.com/repos/" + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content.toString();
    }
}
