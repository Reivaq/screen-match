package com.alura.screenmatch.modelos;

import com.alura.screenmatch.exception.ErrorEnConversionDeDuracionException;
import com.google.gson.annotations.SerializedName;

public class Titulo implements Comparable<Titulo> {
//    @SerializedName("Title") // esto se agrega para que el gson sepa a que nos est5amos refiriendo por que en nuestra gson vien year y title y no nombre y feca de lanzamiento
    private String nombre;
//    @SerializedName("Year")
    private int fechaDeLanzamiento;
    private boolean incluidoEnElPlan;
    private double sumaDeLasEvaluaciones;
    private int totalDeEvaluaciones;
    @SerializedName("RunTime")
    private int duracionEnMinutos;


    public Titulo(String nombre, int fechaDeLanzamiento) {
        this.nombre = nombre;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public Titulo(TituloOmdb miTituloOmdb) {
        this.nombre = miTituloOmdb.title();
        this.fechaDeLanzamiento = Integer.valueOf(miTituloOmdb.year());
        if (miTituloOmdb.runtime().contains("N/A")){
            // CREAMOS NUESTRA PROPIA EXCEPCION
            throw new ErrorEnConversionDeDuracionException("No se puede converetir" +
                    "la duiracion por que continene un N/A ");
        }
        this.duracionEnMinutos = Integer.valueOf(miTituloOmdb.runtime().substring(0,3).replace(" ",""));
    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public boolean isIncluidoEnElPlan() {
        return incluidoEnElPlan;
    }

    public int getDuracionEnMinutos() {
        return duracionEnMinutos;
    }

    public int getTotalDeEvaluaciones() {
        return totalDeEvaluaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaDeLanzamiento(int fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public void setIncluidoEnElPlan(boolean incluidoEnElPlan) {
        this.incluidoEnElPlan = incluidoEnElPlan;
    }

    public void setDuracionEnMinutos(int duracionEnMinutos) {
        this.duracionEnMinutos = duracionEnMinutos;
    }

    public void muestraFichaTecnica(){
        System.out.println("Nombre de la película: " + nombre);
        System.out.println("Año de lanzamiento: " + fechaDeLanzamiento);
    }

    public void evalua(double nota){
        sumaDeLasEvaluaciones += nota;
        totalDeEvaluaciones++;
    }

    public double calculaMediaEvaluaciones(){
        return sumaDeLasEvaluaciones / totalDeEvaluaciones;
    }

    @Override
    public int compareTo(Titulo otroTitulo) {
        return this.getNombre().compareTo(otroTitulo.getNombre());
    }

    @Override
    public String toString() {
        return
                "(nombre= '" + nombre +
                ", fechaDeLanzamiento= " + fechaDeLanzamiento
                + ", Duracion= " + duracionEnMinutos + ")";
    }
}
