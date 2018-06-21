/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.pojos;

/**
 *
 * @author Mari
 */
public class UsuarioAgregaCancion {
    private int id;
    private int valoracion;
    private int idUsuario;
    private int idCancion;
    private int idListaReproduccion;

    public int getId() {
        return id;
    }

    public void setId(int idUsuarioAgregaCancion) {
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
