package modelo;

import modelo.pojos.Usuario;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mari on 04/05/2018.
 */


public class HttpUtils {
    private static final String BASE_URL =
            "http://localhost:8084/YouTunesWS/ws/services/";
    private static final Integer CONNECT_TIMEOUT = 4000; //MILISEGUNDOS
    private static final Integer READ_TIMEOUT = 10000; //MILISEGUNDOS

    public static Response accesoUsuario(Usuario usuario) {
        String url = "accesoUsuario";
        String parametros = String.format("nombreUsuario=%s&clave=%s",
                usuario.getNombreUsuario(), usuario.getClave());
        return invocarServicioWeb(url, "POST", parametros);
    }

    public static Response registroUsuario(Usuario usuario) {
        String url = "registroUsuario";
        String parametros = String.format("nombreUsuario=%s&clave=%s&nombre=%s&apellidoPat=%s&apellidoMat"
            + "=%s&fechaNacimiento=%s&nombreArtistico=%s&tipoUsuario=%s", usuario.getNombreUsuario(), usuario.getClave(),
            usuario.getNombre(), usuario.getApellidoPat(), usuario.getApellidoMat(), usuario.getFechaNacimiento(), 
            usuario.getNombreArtistico(), usuario.getTipoUsuario());
        return invocarServicioWeb(url, "POST", parametros);
    }
    
    public static Response cambiarCalidadMusica(Usuario usuario) {
        String url = "actualizarCalidad";
        String parametros = String.format("nombreUsuario=%s&calidadDescarga=%s&calidadStream=%s",
                usuario.getNombreUsuario(), usuario.getCalidadDescarga(), usuario.getCalidadStream());
        return invocarServicioWeb(url, "POST", parametros);
    }
    
     public static Response recuperarCatalogoUsuarios() {
        String url = "recuperarTiposUsuarios";
        return invocarServicioWeb(url, "GET", null);
    }

    //ESTE MÉTODO SE CONSERVA TAL CUAL
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
            if(res.getStatus()!=200 && res.getStatus()!=201){
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
