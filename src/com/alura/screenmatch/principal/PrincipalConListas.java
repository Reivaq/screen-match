package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class PrincipalConListas {
    public static void main(String[] args) {

        Pelicula miPelicula = new Pelicula("Encanto",2021);
        miPelicula.evalua(9);
        Serie lost = new Serie("Lost", 2000);
        lost.evalua(10);
        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.evalua(10);
        var peliculaDeBruno = new Pelicula("El señor de los anillos", 2021);
        peliculaDeBruno.evalua(9);

        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(peliculaDeBruno);
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(lost);

        for (Titulo item:lista){
            System.out.println(item.getNombre() );
            if ( item instanceof Pelicula pelicula && pelicula.getClasificacion() > 3){
                //Pelicula pelicula = (Pelicula) item;
                System.out.println(pelicula.getClasificacion());

            }
        }

        ArrayList<String> listaDeArtistas = new ArrayList<>();

        listaDeArtistas.add("Juan Perez Leon");
        listaDeArtistas.add("Ramirez Ortigoza");
        listaDeArtistas.add("Sonia Sarai");
        listaDeArtistas.add("Antio Javier");
        System.out.println("Lista de artistas no ordenada: " + listaDeArtistas);

        Collections.sort(listaDeArtistas);
        System.out.println("Lista de artistas  ordenada: " + listaDeArtistas);

        Collections.sort(lista);
        System.out.println("Lista de titulos Ordenados: " + lista);

        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
        System.out.println("Lista ordenada por fecha : " + lista);


    }
}
