package com.alura.screenmatch.principal;


import com.alura.screenmatch.modelos.Titulo;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class PrincipalConBusqueda {
    public static void main (String[] args) throws IOException, InterruptedException {

        Scanner lectura = new Scanner(System.in);
        System.out.println("Escribe el nombre de la pelñicula que deseas ver: ");
        var busqueda = lectura.nextLine();
        //Armamos la direeccion de la pelicula que busca el cliente
        String clave = "219ec3c4";
        String direccion = "http://www.omdbapi.com/?t=" + busqueda + "&apikey=" +  clave;

    // esta es el HTTP request
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()  // Esto es que es lo que le vamos a pedir al servidor
            .uri(URI.create(direccion))
            .build(); // esto es uan forma de construir algo que puede tener muchas formas


    // este es el http response

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // CONVERTIR GSON A NUESTRA CLASE

        String json = response.body();
        System.out.println(json);

        Gson gson = new Gson();
        Titulo miTitulo = gson.fromJson(json, Titulo.class);
        System.out.println(miTitulo);




    }
}
