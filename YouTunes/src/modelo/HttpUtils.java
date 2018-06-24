package modelo;

import com.google.gson.Gson;
import modelo.pojos.UsuarioDAO;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.pojos.AlbumDAO;
import modelo.pojos.CancionDAO;
import modelo.pojos.HistorialDAO;
import modelo.pojos.ListaReproduccionDAO;
import modelo.pojos.UsuarioAgregaCancionDAO;

/**
 * Created by Mari on 04/05/2018.
 */


public class HttpUtils {
    private static final String BASE_URL =
            "http://localhost:8080/YouTunesWS/ws/services/";
    private static final Integer CONNECT_TIMEOUT = 4000; //MILISEGUNDOS
    private static final Integer READ_TIMEOUT = 10000; //MILISEGUNDOS

    public static Response accesoUsuario(UsuarioDAO usuario) {
        String url = "accesoUsuario";
        String parametros = String.format("nombreUsuario=%s&clave=%s",
                usuario.getNombreUsuario(), usuario.getClave());
        return invocarServicioWeb(url, "POST", parametros);
    }

    public static Response registroUsuario(UsuarioDAO usuario) {
        String url = "registroUsuario";
        String parametros = String.format("nombreUsuario=%s&clave=%s&nombre=%s&apellidoPat=%s&apellidoMat"
            + "=%s&fechaNacimiento=%s&nombreArtistico=%s&tipoUsuario=%s", usuario.getNombreUsuario(), usuario.getClave(),
            usuario.getNombre(), usuario.getApellidoPat(), usuario.getApellidoMat(), usuario.getFechaNacimiento(), 
            usuario.getNombreArtistico(), usuario.getTipoUsuario());
        return invocarServicioWeb(url, "POST", parametros);
    }
    
    public static Response registrarPlaylist(ListaReproduccionDAO lista) {
        String url = "registrarPlaylist";
        String parametros = String.format("idUsuario=%s&nombreLista=%s&descripcion=%s", lista.getIdUsuario(),
            lista.getNombreLista(), lista.getDescripcion());
        return invocarServicioWeb(url, "POST", parametros);
    }
    
    public static Response agregarCancionPlaylist(UsuarioAgregaCancionDAO usuarioCancion) {
        String url = "agregarCancionPlaylist";
        String parametros = String.format("idUsuario=%s&idCancion=%s&idListaReproduccion=%s", 
            usuarioCancion.getIdUsuario(), usuarioCancion.getIdCancion(), 
            usuarioCancion.getIdListaReproduccion());
        return invocarServicioWeb(url, "POST", parametros);
    }
    
     public static Response actualizarValoracion(UsuarioAgregaCancionDAO usuarioCancion) {
        String url = "actualizarValoracion";
        String parametros = String.format("idUsuarioAgregaCancion=%s&valoracion=%s",
                usuarioCancion.getIdUsuario(), usuarioCancion.getValoracion());
        return invocarServicioWeb(url, "POST", parametros);
    }
     
      public static Response actualizarHistorial(HistorialDAO historial) {
        String url = "actualizarHistorial";
        String parametros = String.format("idUsuario=%s&idCancion=%s",
                historial.getIdUsuario(), historial.getIdCancion());
        return invocarServicioWeb(url, "POST", parametros);
    }
    
    public static Response cambiarCalidadMusica(UsuarioDAO usuario) {
        String url = "actualizarCalidad";
        String parametros = String.format("nombreUsuario=%s&calidadDescarga=%s&calidadStream=%s",
                usuario.getNombreUsuario(), usuario.getCalidadDescarga(), usuario.getCalidadStream());
        return invocarServicioWeb(url, "POST", parametros);
    }
    
     public static Response recuperarCatalogoUsuarios() {
        String url = "recuperarTiposUsuarios";
        return invocarServicioWeb(url, "GET", null);
    }
     
    public static Response recuperarUsuarioPorNombreUsuario(UsuarioDAO usuario) {
        String url = "recuperarUsuarioPorNombre/" + usuario.getNombreUsuario();
        return invocarServicioWeb(url, "GET", null);
    }
  
  public static Response recuperarUsuarioPorId(UsuarioDAO usuario) {
        String url = "recuperarUsuarioPorId/" + usuario.getIdUsuario();
        return invocarServicioWeb(url, "GET", null);
    }
  
    public static Response recuperarCatalogoGeneros() {
        String url = "recuperarGeneros";
        return invocarServicioWeb(url, "GET", null);
    }
  
    public static void subirAlbum(AlbumDAO album, byte[] cancionByte) throws IOException, MalformedURLException {
            String albumJSON = new Gson().toJson(album, AlbumDAO.class);
            String encodedJSON = URLEncoder.encode(albumJSON, "UTF-8");
            URLConnection connection = new URL(BASE_URL + "subirAlbum/" + encodedJSON).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            try (OutputStream output = connection.getOutputStream()) {
                output.write(cancionByte);
                // Files.copy(new File("C:\\Users\\Mari\\Pictures\\Jariana\\IMG_0628.JPG").toPath(), output);
            }
            InputStream response = connection.getInputStream();    
    }
    
     public static void subirCancion(CancionDAO cancion, byte[] cancionByte) throws Exception {        
            String cancionJSON = new Gson().toJson(cancion, CancionDAO.class);        
            String encodedJSON = URLEncoder.encode(cancionJSON, "UTF-8");            
            URLConnection connection = new URL(BASE_URL + "subirCancion/" + encodedJSON).openConnection();            
            connection.setDoOutput(true); 
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            try (OutputStream output = connection.getOutputStream()) {
                output.write(cancionByte);
               // Files.copy(new File("C:\\Users\\Mari\\Pictures\\Jariana\\IMG_0628.JPG").toPath(), output);
            }            
            InputStream response = connection.getInputStream();          
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
