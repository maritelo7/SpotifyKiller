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
public class ListaReproduccion {
    private Integer id;
    private String nombreLista;
    private String descripcion;
    private Usuario idUsuario;
    private List<UsuarioAgregaCancion> usuarioAgregaCancionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<UsuarioAgregaCancion> getUsuarioAgregaCancionList() {
        return usuarioAgregaCancionList;
    }

    public void setUsuarioAgregaCancionList(List<UsuarioAgregaCancion> usuarioAgregaCancionList) {
        this.usuarioAgregaCancionList = usuarioAgregaCancionList;
    }
}
