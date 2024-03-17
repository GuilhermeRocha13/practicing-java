package com.screenmatch.main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.screenmatch.models.TitleOMDB;
import com.screenmatch.models.Titulo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class main_search {
    public static void main (String[] args) throws IOException, InterruptedException {

        Scanner reader = new Scanner(System.in);
        var search = reader.nextLine();

        var addr = "https://www.omdbapi.com/?t=" + search + "&apikey=9e27a9fb";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(addr))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        String json = response.body();

        Gson gson = new GsonBuilder().
                setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).
                create();

        TitleOMDB myTitleODMB = gson.fromJson(json, TitleOMDB.class);

        //System.out.println(myTitleODMB);

        Titulo mytitle = new Titulo(myTitleODMB);

        System.out.println(mytitle);
    }
}
