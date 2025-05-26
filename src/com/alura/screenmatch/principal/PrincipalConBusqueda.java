package com.alura.screenmatch.principal;


import com.alura.screenmatch.exception.ErrorEnConversionDeDuracionException;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;


public class PrincipalConBusqueda {
    public static void main (String[] args) throws IOException, InterruptedException {

        Scanner lectura = new Scanner(System.in);

        List<Titulo> titulos = new ArrayList<>();
        // Gson gson = new Gson();
        // Esto se ace para que el gson detecte las maytusculas
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting() // para que se vea mas bonito el fromato
                .create();

        while (true) {

            System.out.println("Escribe el nombre de una pelicula: ");
            var busqueda = lectura.nextLine();


            if (busqueda.equalsIgnoreCase("salir")) {
                break;
            }


            //Armamos la direeccion de la pelicula que busca el cliente
            String clave = "219ec3c4";
            String direccion = "http://www.omdbapi.com/?t=" + busqueda.replace(" ", "+") + "&apikey=" + clave;

            try {
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



                //Titulo miTitulo = gson.fromJson(json, Titulo.class);
                //System.out.println(miTitulo);
                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(miTituloOmdb);


                Titulo miTitulo = new Titulo(miTituloOmdb);
                System.out.println("Titulo ya convertido: " + miTitulo);

//                FileWriter escritura = new FileWriter("peliculas.txt");
//                escritura.write(miTitulo.toString());
//                escritura.close();

                titulos.add(miTitulo);


            } catch (
                    NumberFormatException e) { // en esta linea tratamos de aberiuar cual fue el codigo que marco el error
                System.out.println("Ocurrio un error ");
                System.out.println(e.getMessage()); // de esta forma obtenemso el tipo de error que obtuvimos
            } catch (IllegalArgumentException e) {
                System.out.println("Errror en la URI, verifique la direccion.");
            } catch (ErrorEnConversionDeDuracionException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos);

//        guardamos los titulos dentro de un archivo
        FileWriter escritura = new FileWriter("Titulos.json");
               escritura.write(gson.toJson(titulos));
               escritura.close();

        System.out.println(" Finalizo la ejecucion del progrma ");

    }
}
