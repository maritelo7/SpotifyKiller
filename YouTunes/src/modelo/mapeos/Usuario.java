/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.mapeos;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Mari
 */
public class Usuario {

    private Integer id;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private Date fechaNacimiento;
    private String clave;
    private String nombreArtistico;
    private String nombreUsuario;
    private Integer calidadDescarga;
    private Integer calidadStream;
    private List<Album> albumList;
    private List<Historial> historialList;
    private List<ListaReproduccion> listaReproduccionList;
    private TipoUsuario tipoUsuario;
    private List<UsuarioAgregaCancion> usuarioAgregaCancionList;
    private List<Cancion> cancionList;
  
   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getCalidadDescarga() {
        return calidadDescarga;
    }

    public void setCalidadDescarga(Integer calidadDescarga) {
        this.calidadDescarga = calidadDescarga;
    }

    public Integer getCalidadStream() {
        return calidadStream;
    }

    public void setCalidadStream(Integer calidadStream) {
        this.calidadStream = calidadStream;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public List<Historial> getHistorialList() {
        return historialList;
    }

    public void setHistorialList(List<Historial> historialList) {
        this.historialList = historialList;
    }

    public List<ListaReproduccion> getListaReproduccionList() {
        return listaReproduccionList;
    }

    public void setListaReproduccionList(List<ListaReproduccion> listaReproduccionList) {
        this.listaReproduccionList = listaReproduccionList;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<UsuarioAgregaCancion> getUsuarioAgregaCancionList() {
        return usuarioAgregaCancionList;
    }

    public void setUsuarioAgregaCancionList(List<UsuarioAgregaCancion> usuarioAgregaCancionList) {
        this.usuarioAgregaCancionList = usuarioAgregaCancionList;
    }

    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }
}
