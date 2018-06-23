/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.mapeos;

import java.util.List;

/**
 *
 * @author Mari
 */
public class Cancion {
    
    private Integer id;
    private String titulo;
    private String formato;
    private String path;
    private String colaboradores;
    private Integer calidad;
    private List<Historial> historialList;
    private List<UsuarioAgregaCancion> usuarioAgregaCancionList;
    private Album idAlbum;
    private Genero idGenero;
    private Usuario idUsuarioSubioCancion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCalidad() {
        return calidad;
    }

    public void setCalidad(Integer calidad) {
        this.calidad = calidad;
    }

    public List<Historial> getHistorialList() {
        return historialList;
    }

    public void setHistorialList(List<Historial> historialList) {
        this.historialList = historialList;
    }

    public List<UsuarioAgregaCancion> getUsuarioAgregaCancionList() {
        return usuarioAgregaCancionList;
    }

    public void setUsuarioAgregaCancionList(List<UsuarioAgregaCancion> usuarioAgregaCancionList) {
        this.usuarioAgregaCancionList = usuarioAgregaCancionList;
    }

    public Album getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Album idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    public Usuario getIdUsuarioSubioCancion() {
        return idUsuarioSubioCancion;
    }

    public void setIdUsuarioSubioCancion(Usuario idUsuarioSubioCancion) {
        this.idUsuarioSubioCancion = idUsuarioSubioCancion;
    }
}
