/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.mapeos;

/**
 *
 * @author Mari
 */
public class UsuarioAgregaCancion {
    private static final long serialVersionUID = 1L;
    private Integer id;  
    private Integer valoracion;  
    private Cancion idCancion;
    private ListaReproduccion idListaReproduccion;
    private Usuario idUsuario;
    private Cancion cancion;
    private ListaReproduccion listaReproduccion;
    private Usuario usuario;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }

    public Cancion getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Cancion idCancion) {
        this.idCancion = idCancion;
    }

    public ListaReproduccion getIdListaReproduccion() {
        return idListaReproduccion;
    }

    public void setIdListaReproduccion(ListaReproduccion idListaReproduccion) {
        this.idListaReproduccion = idListaReproduccion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

    public ListaReproduccion getListaReproduccion() {
        return listaReproduccion;
    }

    public void setListaReproduccion(ListaReproduccion listaReproduccion) {
        this.listaReproduccion = listaReproduccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
