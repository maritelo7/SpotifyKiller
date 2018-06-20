/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import modelo.pojos.*;

/**
 *
 * @author Mari
 */
public class Services {
    private static final String BASE_URL ="http://localhost:8000/";
    private static final Integer CONNECT_TIMEOUT = 4000; 
    private static final Integer READ_TIMEOUT = 10000; 
      
    
    // url = new URL("http://10.0.2.2:8080/HelloServlet/PDRS?param1="+lat+"&param2="+lon);
    
    public static Response subirCancion(Cancion cancion) {
        //donde se convierte a JSON la cancion, se pasa a la URL ya convertida
        //se tiene que enviar tambien el audio aunque en que formato??
        String url = "guardarCancion/" + cancion;
        return invocarServicioWeb(url, "POST", null);
    }
    
     public static Response buscarCancion(String titulo, int calidad) {
        String url = "" + titulo + "/" + calidad;
        return invocarServicioWeb(url, "GET", null);
    }
     
     //canciones propias del usuario no tienen calidad definida, las del artista si
    public static Response recuperarMisCanciones(int idUsuario) {
        String url = "" + idUsuario;
        return invocarServicioWeb(url, "GET", null);
    }
    
    public static Response recuperarListasReproduccion(int idUsuario) {
        String url = "" + idUsuario;
        return invocarServicioWeb(url, "GET", null);
    }
    
     public static Response recuperarListaReproduccion(int idLista) {
        String url = "" + idLista;
        return invocarServicioWeb(url, "GET", null);
    }
    
    public static Response crearLista(ListaReproduccion lista) {
        //donde se convierte a JSON la lista, se pasa a la URL ya convertida
        String url = "" + lista;
        return invocarServicioWeb(url, "POST", null);
    }
    
    public static Response agregarCancionLista(int idUsuario, int idCancion, int idLista ) {
        //donde se convierte a JSON la lista, se pasa a la URL ya convertida
        String url = "" + idLista;
        return invocarServicioWeb(url, "POST", null);
    }
    
    public static Response recuperarHistorial(int idUsuario) {
        //donde se convierte a JSON la lista, se pasa a la URL ya convertida
        String url = "" + idUsuario;
        return invocarServicioWeb(url, "GET", null);
    }

     private static Response invocarServicioWeb(String url, String tipoinvocacion, String parametros){
        HttpURLConnection c = null;
        URL u = null;
        Response res = new Response();
        try {
            if(tipoinvocacion.compareToIgnoreCase("GET")==0){
                u = new URL(BASE_URL+url+((parametros!=null)?parametros:""));
                c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod(tipoinvocacion);
                c.setRequestProperty("Content-length", "0");
                c.setUseCaches(false);
                c.setAllowUserInteraction(false);
                c.setConnectTimeout(CONNECT_TIMEOUT);
                c.setReadTimeout(READ_TIMEOUT);
                c.connect();
            }else{
                u = new URL(BASE_URL+url);
                c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod(tipoinvocacion);
                c.setDoOutput(true);
                c.setConnectTimeout(CONNECT_TIMEOUT);
                c.setReadTimeout(READ_TIMEOUT);
                //----PASAR PARÁMETROS EN EL CUERPO DEL MENSAJE POST, PUT y DELETE----//
                DataOutputStream wr = new DataOutputStream(c.getOutputStream());
                wr.writeBytes(parametros);
                wr.flush();
                wr.close();
                //------------------------------------------------------//
            }
            res.setStatus(c.getResponseCode());
            if(res.getStatus()!=200 && res.getStatus()!=201&& res.getStatus()!=204){
                res.setError(true);
            }
            if(c.getInputStream()!=null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                br.close();
                res.setResult(sb.toString());
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setResult(ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setResult(ex.getMessage());
        } finally {
            if (c != null) {
                c.disconnect();
            }
        }
        return res;
    }
}
