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
public class UsuarioAgregaCancionDAO {
    private int id;
    private int valoracion;
    private int idUsuario;
    private int descargada;
    private int idCancion;
    private int idListaReproduccion;

    public int getIdUsuarioAgregaCancion() {
        return id;
    }

    public void setIdUsuarioAgregaCancion(int idUsuarioAgregaCancion) {
        this.id = idUsuarioAgregaCancion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDescargada() {
        return descargada;
    }

    public void setDescargada(int descargada) {
        this.descargada = descargada;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getIdListaReproduccion() {
        return idListaReproduccion;
    }

    public void setIdListaReproduccion(int idListaReproduccion) {
        this.idListaReproduccion = idListaReproduccion;
    }
}
