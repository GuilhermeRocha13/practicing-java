package com.screenmatch.main;

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
        System.out.println(response.body());
    }
}
