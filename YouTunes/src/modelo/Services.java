/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import modelo.mapeos.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 *
 */
public class Services {

    private static final String BASE_URL = "http://192.168.43.133:8000/";

    //Método buscar canción por título
    public static List<Cancion> buscarCancion(String titulo) {
        String metodo = "buscarCancion/" + titulo;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<Cancion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Cancion>>() {
        });
//        JSONObject json = new JSONObject(lista.get(0));
        //      System.out.println(json);
        return lista;
    }

    //Método para recuperar canciones que están almacenadas en una lista
    public static List<UsuarioAgregaCancion> buscarCancionesDeLista(int idLista) {
        String metodo = "buscarCancionesLista/" + idLista;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<UsuarioAgregaCancion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<UsuarioAgregaCancion>>() {
        });
//        JSONObject json = new JSONObject(lista.get(0));
//        System.out.println(json);
        return lista;
    }

    //Método para regresar listas asociadas a un usuario
    public static List<ListaReproduccion> buscarListasUsuario(int idUsuario) {
        String metodo = "buscarListasUsuario/" + idUsuario;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<ListaReproduccion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<ListaReproduccion>>() {
        });
//        JSONObject json = new JSONObject(lista.get(0));
//        System.out.println(json);
        return lista;
    }

    //Método regresar canciones de usuarioaagregacancion que subio un usuario
    public static List<UsuarioAgregaCancion> buscarUsuarioAgregaCancion(int idUsuario) {
        String metodo = "buscarUsuarioAgregaCancion/" + idUsuario;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<UsuarioAgregaCancion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<UsuarioAgregaCancion>>() {
        });
//        JSONObject json = new JSONObject(lista.get(0));
//        System.out.println(json);
        return lista;
    }

    //Método para regresar canciones que subio un cliente
    public static List<Cancion> buscarUsuarioCancion(int idUsuario) {
        String metodo = "buscarUsuarioCancion/" + idUsuario;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<Cancion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Cancion>>() {
        });
//        JSONObject json = new JSONObject(lista.get(0));
//        System.out.println(json);
        return lista;
    }

    //Método para regresar listas asociadas a un usuario
    public static List<Historial> recuperarHistorial(int idUsuario) {
        String metodo = "buscarHistorial/" + idUsuario;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<Historial> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Historial>>() {
        });
//        JSONObject json = new JSONObject(lista.get(0));
//        System.out.println(json);
        return lista;
    }

    //Método para regresar canciones de un genero pasado
    public static List<Cancion> buscarCancionGenero(int idGenero) {
        String metodo = "buscarCancionGenero/" + idGenero;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<Cancion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Cancion>>() {
        });
//        JSONObject json = new JSONObject(lista.get(0));
//        System.out.println(json);
        return lista;
    }

    //Método para regresar canciones de un genero pasado
    public static List<Album> recuperarAlbumes(int idUsuario) {
        String metodo = "recuperarAlbumes/" + idUsuario;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<Album> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Album>>() {
        });
//        JSONObject json = new JSONObject(lista.get(0));
//        System.out.println(json);
        return lista;
    }
    
     //Método para regresar canciones de un genero pasado
    public static List<Album> recuperarAlbum(String titulo) {
        String metodo = "recuperarAlbum/" + titulo;
        System.out.println(metodo);
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<Album> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Album>>() {
        });
       JSONObject json = new JSONObject(lista.get(0));
        System.out.println(json);
        return lista;
    }

    //Método para regresar canciones de un genero pasado
    public static List<Cancion> recuperarCancionesPorAlbum(int idAlbum) {
        String metodo = "cancionesPorAlbum/" + idAlbum;
        Client cliente = ClientBuilder.newClient();
        WebTarget webTarget = cliente.target(BASE_URL + metodo);
        List<Cancion> lista = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Cancion>>() {
        });
//        JSONObject json = new JSONObject(lista.get(0));
//        System.out.println(json);
        return lista;
    }

    //Método para regresar un archivo de imagen, buscando por id de album
    public static BufferedImage recuperarImagen(int idAlbum) throws MalformedURLException, IOException {
        URL url = new URL(BASE_URL + "buscarAlbum/" + idAlbum);
        URLConnection con = url.openConnection();
        BufferedImage image = null;
        if (con.getInputStream() != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            image = decodeToImage(sb.toString());
            //ImageIO.write(image, "jpg", new File("/home/maritello/Escritorio/images/hi2.jpg"));
        }
        return image;
    }

    public static BufferedImage decodeToImage(String imageString) {
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public static String recuperarAudio(int idCancion, String path) {

        String separator = System.getProperty("file.separator");

        if (!new File("music").exists()) {
            new File("music").mkdir();
        }

        System.out.println(separator);
        File f = new File("music" + separator + path);
        try {
            f.createNewFile();

            FileOutputStream output = new FileOutputStream(f);
            String urlString = BASE_URL + "cancion/" + idCancion;
            HttpURLConnection c = null;
            URL u = new URL(urlString);
            c = (HttpURLConnection) u.openConnection();
            c.connect();

            InputStream input = c.getInputStream();            

            Thread stream = new Thread() {
                public void run() {
                    try {
                        
                        byte[] buffer = new byte[10240];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        input.close();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };

            stream.start();            
            
            
            
         
        } catch (IOException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }

        String bin = f.getAbsolutePath();
//        String audio = new File(bin).toURI().toString();

        return bin;
    }
    
    

}
