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
public class TipoUsuarioDAO {
        private int id;
        private String tipoUsuario;

    public int getIdTipoUsuario() {
        return id;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.id = idTipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    @Override
    public String toString(){
        return tipoUsuario;
    }
    
    }
