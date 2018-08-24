package modelo.ws;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Album;
import servicios.pojos.Cancion;
import servicios.pojos.Genero;
import servicios.pojos.TipoUsuario;
import servicios.pojos.Usuario;

/**
 * REST Web Service
 *
 * @author Mari
 */
@Path("services")
public class Catalog {

    @Context
    private UriInfo context;
    private String RUTA_MUSICA = "/home/maritello/Escritorio/music/";
    private String RUTA_IMAGENES = "/home/maritello/Escritorio/images/";

    /**
     * Creates a new instance of catalog
     */
    public Catalog() {
    }

    @POST
    @Path("accesoUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario iniciarSesion(
        @FormParam("nombreUsuario") String nombreUsuario,
        @FormParam("clave") String clave
    ) {
        SqlSession conn = null;
        Usuario usuario = new Usuario();
        try {
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> param
                = new HashMap<String, Object>();
            param.put("nombreUsuario", nombreUsuario);
            param.put("clave", clave);
            usuario = conn.selectOne("Usuario.accesoUsuario", param);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }

        }
        return usuario;
    }

    @POST
    @Path("registroUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUsuario(
        @FormParam("nombreUsuario") String nombreUsuario,
        @FormParam("clave") String clave,
        @FormParam("nombre") String nombre,
        @FormParam("apellidoPat") String apellioPat,
        @FormParam("apellidoMat") String apellidoMat,
        @FormParam("fechaNacimiento") String fechaNacimiento,
        @FormParam("nombreArtistico") String nombreArtistico,
        @FormParam("tipoUsuario") Integer tipoUsuario) throws ParseException {
        SqlSession conn = null;
        Mensaje msg = new Mensaje();
        try {
            DateFormat formatter = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            Date fechaNac = formatter.parse(fechaNacimiento);
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> param
                = new HashMap<String, Object>();
            param.put("nombreUsuario", nombreUsuario);
            param.put("clave", clave);
            param.put("nombre", nombre);
            param.put("apellidoPat", apellioPat);
            param.put("apellidoMat", apellidoMat);
            param.put("fechaNacimiento", fechaNac);
            param.put("nombreArtistico", nombreArtistico);
            param.put("tipoUsuario", tipoUsuario);
            param.put("calidadDescarga", 1);
            param.put("calidadStream", 1);
            conn.insert("Usuario.registrarUsuario", param);
            conn.commit();
            msg.setMensaje("Usuario registrado con éxito.");
        } catch (IOException ex) {
            ex.printStackTrace();
            msg.setError(true);
            msg.setMensaje(ex.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return msg;
    }

    @POST
    @Path("actualizarCalidad")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarCalidad(
        @FormParam("nombreUsuario") String nombreUsuario,
        @FormParam("calidadDescarga") Integer calidadDescarga,
        @FormParam("calidadStream") Integer calidadStream) {
        SqlSession conn = null;
        Mensaje msg = new Mensaje();
        try {
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> param
                = new HashMap<String, Object>();
            param.put("nombreUsuario", nombreUsuario);
            param.put("calidadDescarga", calidadDescarga);
            param.put("calidadStream", calidadStream);
            conn.update("Usuario.actualizarCalidad", param);
            conn.commit();
        } catch (IOException ex) {
            ex.printStackTrace();
            msg.setError(true);
            //msg.setMensaje(ex.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }

        }
        return msg;
    }

    @GET
    @Path("recuperarTiposUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUsuario> recuperarTiposUsuarios() {
        SqlSession conn = null;
        List<TipoUsuario> tipos = new ArrayList();
        try {
            conn = MyBatisUtils.getSession();
            tipos = conn.selectList("TipoUsuario.recuperarTodos");
            conn.commit();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }

        }
        return tipos;
    }

    //recuperarGeneros
    @GET
    @Path("recuperarGeneros")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genero> recuperarGeneros() {
        SqlSession conn = null;
        List<Genero> tipos = new ArrayList();
        try {
            conn = MyBatisUtils.getSession();
            tipos = conn.selectList("Genero.recuperarTodos");
            conn.commit();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }

        }
        return tipos;
    }

    @GET
    @Path("recuperarUsuarioPorId/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioPorId(
        @PathParam("idUsuario") Integer idUsuario
    ) throws IOException {
        Usuario usuario = new Usuario();
        SqlSession conn = null;
        try {
            HashMap<String, Object> param
                = new HashMap<String, Object>();
            param.put("idUsuario", idUsuario);
            conn = MyBatisUtils.getSession();
            usuario = conn.selectOne("Usuario.getPorId", param);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return usuario;
    }

    @GET
    @Path("recuperarUsuarioPorNombre/{nombreUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioPorNombre(
        @PathParam("nombreUsuario") String nombreUsuario
    ) throws IOException {
        Usuario usuario = new Usuario();
        SqlSession conn = null;
        HashMap<String, Object> param
            = new HashMap<String, Object>();
        param.put("nombreUsuario", nombreUsuario);
        try {
            conn = MyBatisUtils.getSession();
            usuario = conn.selectOne("Usuario.getPorNombreUsuario", param);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return usuario;
    }

    @POST
    @Path("subirAlbum/{album}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Mensaje subirImagen(@PathParam("album") String album, byte[] bytes) throws UnsupportedEncodingException {
        Mensaje res = new Mensaje();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String nombre = URLEncoder.encode(dateFormat.format(new Date()), "UTF-8") + ".jpg";
        String path = RUTA_IMAGENES + nombre;
        String albumDecoded = URLDecoder.decode(album, "UTF-8");
        Album albumRecibido = new Gson().fromJson(albumDecoded, Album.class);
        try {
            if (bytes != null) {

                if (guardarImagen(nombre, path, bytes, albumRecibido)) {
                    res.setMensaje("Foto guardada correctamente...");
                } else {
                    res.setError(true);
                    res.setMensaje("No se pudo guardar la imagen en el servidor...");
                }
            } else {
                res.setError(true);
                res.setMensaje("No se recibio flujo de datos...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje(ex.getMessage());
        }
        return res;
    }

    private boolean guardarImagen(String nombre, String path, byte[] bytes, Album album) {
        InputStream in = new ByteArrayInputStream(bytes);
        try {
            SqlSession conn = null;
            album.setPathPortada(nombre);
            conn = MyBatisUtils.getSession();
            conn.insert("Album.registrarAlbum", album);
            conn.commit();
            BufferedImage buffImage = ImageIO.read(in);
            ImageIO.write(buffImage, "jpg", new File(path));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @POST
    @Path("subirCancion/{cancion}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Mensaje subirCancion(@PathParam("cancion") String cancion, byte[] bytes) throws UnsupportedEncodingException {
        Mensaje res = new Mensaje();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String nombre = URLEncoder.encode(dateFormat.format(new Date()), "UTF-8") + ".mp3";
        String path = RUTA_MUSICA + nombre;
        String cancionDecoded = URLDecoder.decode(cancion, "UTF-8");
        Cancion cancionRecibida = new Gson().fromJson(cancionDecoded, Cancion.class);
        try {
            if (bytes != null) {
                if (guardarCancion(nombre, path, bytes, cancionRecibida)) {
                    res.setMensaje("Cancion guardada correctamente...");
                } else {
                    res.setError(true);
                    res.setMensaje("No se pudo guardar la cancion en el servidor...");
                }
            } else {
                res.setError(true);
                res.setMensaje("No se recibio flujo de datos...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje(ex.getMessage());
        }
        return res;
    }

    private boolean guardarCancion(String nombre, String path, byte[] bytes, Cancion cancion) {
        InputStream in = new ByteArrayInputStream(bytes);
        try {
            File file = new File(path);
            FileOutputStream output = new FileOutputStream(file);
            output.write(bytes);
            System.out.println(file.getAbsolutePath());
            SqlSession conn = null;
            cancion.setPath(nombre);
            cancion.setFormato(".mp3");
            conn = MyBatisUtils.getSession();
            conn.insert("Cancion.registrarCancion", cancion);
            conn.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @POST
    @Path("registrarPlaylist")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarPlaylist(
        @FormParam("idUsuario") Integer idUsuario,
        @FormParam("nombreLista") String nombreLista,
        @FormParam("descripcion") String descripcion) {
        SqlSession conn = null;
        Mensaje msg = new Mensaje();
        try {
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> param
                = new HashMap<String, Object>();
            param.put("idUsuario", idUsuario);
            param.put("nombreLista", nombreLista);
            param.put("descripcion", descripcion);
            conn.insert("ListaReproduccion.registrarLista", param);
            conn.commit();
            msg.setMensaje("Lista registrada");
        } catch (IOException ex) {
            ex.printStackTrace();
            msg.setError(true);
            msg.setMensaje(ex.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return msg;
    }

    @POST
    @Path("agregarCancionPlaylist")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarCancionPlaylist(
        @FormParam("idUsuario") Integer idUsuario,
        @FormParam("idCancion") Integer idCancion,
        @FormParam("idListaReproduccion") Integer idListaReproduccion
    ) {
        SqlSession conn = null;
        Mensaje msg = new Mensaje();
        try {
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> param
                = new HashMap<String, Object>();
            param.put("idUsuario", idUsuario);
            param.put("idCancion", idCancion);
            param.put("idListaReproduccion", idListaReproduccion);
            conn.insert("ListaReproduccion.agregarCancion", param);
            conn.commit();
            msg.setMensaje("Cancion agregada a lista de reproducción");
        } catch (IOException ex) {
            ex.printStackTrace();
            msg.setError(true);
            msg.setMensaje(ex.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }

        }
        return msg;
    }

    @POST
    @Path("registrarValoracion")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarValoracion(
        @FormParam("valoracion") Integer valoracion,
        @FormParam("idCancion") Integer idCancion) {
        SqlSession conn = null;
        Mensaje msg = new Mensaje();
        try {
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> param
                = new HashMap<String, Object>();
            param.put("valoracion", valoracion);
            param.put("idCancion", idCancion);
            conn.insert("Calificacion.registrarValoracion", param);
            conn.commit();
            msg.setMensaje("Valoracion registrada");
        } catch (IOException ex) {
            ex.printStackTrace();
            msg.setError(true);
            msg.setMensaje(ex.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return msg;
    }

    @POST
    @Path("actualizarValoracion")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarValoracion(
        @FormParam("valoracion") Integer valoracion,
        @FormParam("idCancion") Integer idCancion) {
        SqlSession conn = null;
        Mensaje msg = new Mensaje();
        try {
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> param
                = new HashMap<String, Object>();
            param.put("valoracion", valoracion);
            param.put("idCancion", idCancion);
            conn.update("Calificacion.actualizarValoracion", param);
            conn.commit();
            msg.setMensaje("Valoracion actualizada");
        } catch (IOException ex) {
            ex.printStackTrace();
            msg.setError(true);
            msg.setMensaje(ex.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }

        }
        return msg;
    }

    @POST
    @Path("actualizarHistorial")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje actualizarHistorial(
        @FormParam("idUsuario") Integer idUsuario,
        @FormParam("idCancion") Integer idCancion) {
        SqlSession conn = null;
        Mensaje msg = new Mensaje();
        try {
            conn = MyBatisUtils.getSession();
            HashMap<String, Object> param
                = new HashMap<String, Object>();
            param.put("idUsuario", idUsuario);
            param.put("idCancion", idCancion);
            conn.insert("Historial.actualizarHistorial", param);
            conn.commit();
            msg.setMensaje("Historial actualizado");
        } catch (IOException ex) {
            ex.printStackTrace();
            msg.setError(true);
            msg.setMensaje(ex.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }

        }
        return msg;
    }

}
