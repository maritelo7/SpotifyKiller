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
public class Genero {
    private int id;
    private String genero;

    public int getIdGenero() {
        return id;
    }

    public void setIdGenero(int idGenero) {
        this.id = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    @Override
    public String toString (){
        return genero;
    }
}
