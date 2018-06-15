package modelo.ws;

import java.io.IOException;
import java.util.HashMap;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
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
    Usuario conductor = new Usuario();
    try {
      conn = MyBatisUtils.getSession();
      HashMap<String, Object> param
          = new HashMap<String, Object>();
      param.put("nombreUsuario", nombreUsuario);
      param.put("clave", clave);
      conductor = conn.selectOne("Usuario.accesoUsuario", param);
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (conn != null) {
        conn.close();
      }

    }
    return conductor;
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
      @FormParam("tipoUsuario") Integer tipoUsuario){
    SqlSession conn = null;
    Mensaje msg = new Mensaje();
    try {
      conn = MyBatisUtils.getSession();
      HashMap<String, Object> param
          = new HashMap<String, Object>();
      param.put("nombreUsuario", nombreUsuario);
      param.put("clave", clave);
      param.put("nombre", nombre);     
      param.put("apellidoPat", apellioPat);
      param.put("apellidoMat", apellidoMat);
      param.put("fechaNacimiento", fechaNacimiento);
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
  
}
