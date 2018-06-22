/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Mari
 */
public class UsuarioDAO {
    private Integer id;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private Date fechaNacimiento;
    private String clave;
    private String nombreArtistico;
    private Integer tipoUsuario;
    private String nombreUsuario;
    private Integer calidadDescarga;
    private Integer calidadStream;

    public Integer getIdUsuario() {
        return id;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.id = idUsuario;
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
    
    public String getFechaNacimientoFormato() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' YYYY", new Locale("ES"));
        String fechaNac = sdf.format(fechaNacimiento);
        return fechaNac;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
}
