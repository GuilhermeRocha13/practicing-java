package com.screenmatch.main;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.screenmatch.models.TitleOMDB;
import com.screenmatch.models.Title;
import com.screenmatch.exception.numberLenght;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main_search {
    public static void main (String[] args) throws IOException, InterruptedException {

        Scanner reader = new Scanner(System.in);
        String search = "";
        List<Title> titles = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!search.equalsIgnoreCase("exit")) {

            search = reader.nextLine();

            if(search.equalsIgnoreCase("exit")){
                break;
            }

            String endereco = "https://www.omdbapi.com/?t=" + search.replace(" ", "+") + "&apikey=9e27a9fb";
            System.out.println(endereco);
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                TitleOMDB myTitleOMD = gson.fromJson(json, TitleOMDB.class);

                Title mytitle = new Title(myTitleOMD);
                System.out.println(mytitle);

                titles.add(mytitle);
            } catch (NumberFormatException e) {
                System.out.println("error: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("addr error");
            } catch (numberLenght e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println(titles);
        titles.get(0).avalia(10);
        FileWriter writer = new FileWriter("movies.json");
        writer.write(gson.toJson(titles));
        writer.close();
        System.out.println("over");

    }
}