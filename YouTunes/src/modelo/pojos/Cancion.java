/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

/**
 *
 * @author Mari
 */
public class Cancion {
    private int id;
    private String titulo;
    private String formato;
    private int idGenero;
    private String path;
    private String colaboradores;
    private int idUsuarioSubioCancion;
    private int idAlbum;
    private int calidad;

    public int getIdCancion() {
        return id;
    }

    public void setIdCancion(int idCancion) {
        this.id = idCancion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(String colaboradores) {
        this.colaboradores = colaboradores;
    }

    public int getIdUsuarioSubioCancion() {
        return idUsuarioSubioCancion;
    }

    public void setIdUsuarioSubioCancion(int idUsuarioSubioCancion) {
        this.idUsuarioSubioCancion = idUsuarioSubioCancion;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getCalidad() {
        return calidad;
    }

    public void setCalidad(int calidad) {
        this.calidad = calidad;
    }
    
    @Override
    public String toString(){
        return titulo;
    }
}
