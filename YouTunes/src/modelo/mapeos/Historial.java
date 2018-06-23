/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.mapeos;

import java.util.Date;

/**
 *
 * @author Mari
 */
public class Historial {
    private Integer id;
    private Date fecha;
    private Cancion idCancion;
    private Usuario idUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cancion getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Cancion idCancion) {
        this.idCancion = idCancion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
