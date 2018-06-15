/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.ws;

/**
 *
 * @author Mari
 */
public class Mensaje {
   private String mensaje;
   private boolean error;
   
   public Mensaje(){};
   
   public Mensaje(String mensaje, boolean error) {
      this.mensaje = mensaje;
      this.error = error;
   }

   public String getMensaje() {
      return mensaje;
   }

   public void setMensaje(String mensaje) {
      this.mensaje = mensaje;
   }

   public boolean isError() {
      return error;
   }

   public void setError(boolean error) {
      this.error = error;
   }

   
   
   
}
