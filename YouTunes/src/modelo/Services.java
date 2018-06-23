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
import java.util.List;
import modelo.mapeos.*;
import modelo.pojos.*;
import org.json.JSONObject;
import java.util.List;
import org.json.JSONObject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
/**
 *
 * @author Mari
 */
public class Services {
    private static final String BASE_URL = "http://192.168.43.52:8000/";
    
    public List<Cancion> buscarCancion(String titulo) {
        String metodo = "buscarCancion/" + titulo;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);      
        List<Cancion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Cancion>>(){});    
        JSONObject json = new JSONObject(lista.get(0));
        System.out.println(json);
        return lista;
    }
    
      public List<Cancion> buscarCancionesDeLista(int idLista) {
        String metodo = "buscarCancionesLista/" + idLista;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);      
        List<Cancion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Cancion>>(){});    
        JSONObject json = new JSONObject(lista.get(0));
        System.out.println(json);
        return lista;
    }
    
  
    public List<ListaReproduccion> buscarListasUsuario(String titulo) {
        String metodo = "buscarCancion/" + titulo;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);      
        List<ListaReproduccion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<ListaReproduccion>>(){});    
        JSONObject json = new JSONObject(lista.get(0));
        System.out.println(json);
        return lista;
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
    
    public static Response crearLista(ListaReproduccionDAO lista) {
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
    
    public static Response recuperarCancion(int idCancion) {
        //donde se convierte a JSON la lista, se pasa a la URL ya convertida
        String url = "" + idCancion;
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
