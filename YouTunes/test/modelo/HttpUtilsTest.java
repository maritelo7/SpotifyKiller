/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import modelo.pojos.Album;
import modelo.pojos.Cancion;
import modelo.pojos.Genero;
import modelo.pojos.Historial;
import modelo.pojos.ListaReproduccion;
import modelo.pojos.TipoUsuario;
import modelo.pojos.Usuario;
import modelo.pojos.UsuarioAgregaCancion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yamii
 */
public class HttpUtilsTest {

    ListaReproduccion playlist = new ListaReproduccion();
    UsuarioAgregaCancion usuarioCancion = new UsuarioAgregaCancion();
    Usuario usuario = new Usuario();
    Album album = new Album();
    Cancion cancion = new Cancion();

    public HttpUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        playlist.setNombreLista("Playlist prueba");
        playlist.setIdUsuario(1);
        playlist.setDescripcion("Descripcion prueba");

        usuarioCancion.setDescargada(1);
        usuarioCancion.setIdCancion(7);
        usuarioCancion.setIdListaReproduccion(2);
        usuarioCancion.setIdUsuario(1);
        usuarioCancion.setValoracion(2);
        
        album.setAnioLanzamiento(2000);
        album.setCompaniaDiscografica("Global");
        album.setIdUsuario(2);
        album.setPathPortada("");
        album.setTitulo("Lemonade");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void accesoUsuarioTest() {
        System.out.println("Test del método accesoUsuario()");
        Cifrado cifrar = new Cifrado();
        String nombreUsuario = "Beyonce";
        String clave = "art";
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setClave(cifrar.cifrarCadena(clave));
        Response result = HttpUtils.accesoUsuario(usuario);
        Usuario resultado;
        resultado = new Gson().fromJson(result.getResult(), Usuario.class);

        assertEquals(nombreUsuario, resultado.getNombreUsuario());
        System.out.println("Éxito de igualdad de nombres de usuario");
    }

    /**
     * El siguiente test requiere de conexión con el servidor para ejecutare sin errores.
     */
    /* @Test
    public void registroUsuarioTest() {
        System.out.println("Prueba del método RegistroUsuario");
        Usuario prueba = new Usuario();
        boolean expResult = false;
        Response resultado = HttpUtils.registroUsuario(prueba);
        boolean result = resultado.getResult().isEmpty();
        assertEquals(expResult, result);
        System.out.println("Prueba de registro exitoso");
    }*/
    @Test
    public void registrarPlaylistTest() {
        System.out.println("Prueba del método registrarPlaylist");
        Response resultado = HttpUtils.registrarPlaylist(playlist);
        boolean result = resultado.isError();
        boolean expResult = false;
        assertEquals(expResult, result);
        System.out.println("Prueba de registro de playlist exitoso");
    }

    @Test
    public void agregarCancionPlaylistTest() {
        System.out.println("Prueba del método agregaCancionPlaylist");
        Response resultado = HttpUtils.agregarCancionPlaylist(usuarioCancion);
        boolean result = resultado.isError();
        boolean expResult = false;
        assertEquals(expResult, result);
        System.out.println("Prueba de agregación de cancion a de playlist exitoso");
    }

    @Test
    public void actualizarValoracionTest() {
        System.out.println("Prueba del método actualizarValoracion");
        usuarioCancion.setValoracion(4);
        Response resultado = HttpUtils.actualizarValoracion(usuarioCancion);
        boolean result = resultado.isError();
        boolean expResult = false;
        assertEquals(expResult, result);
        System.out.println("Prueba de actualización de valoración exitoso");
    }

    @Test
    public void actualizarHistorialTest() {
        System.out.println("Prueba del método actualizarHistorial");
        Historial historial = new Historial();
        historial.setFecha(Date.valueOf(LocalDate.now()));
        historial.setIdUsuario(1);
        historial.setIdCancion(7);
        Response resultado = HttpUtils.actualizarHistorial(historial);
        boolean result = resultado.isError();
        boolean expResult = false;
        assertEquals(expResult, result);
        System.out.println("Prueba de actualización de historial exitoso");
    }

    @Test
    public void cambiarCalidadMusicaTest() {
        System.out.println("Prueba del método cambiarCalidadMusica");
        usuario.setCalidadDescarga(2);
        usuario.setCalidadStream(2);
        usuario.setNombreUsuario("Beyonce");
        Response resultado = HttpUtils.cambiarCalidadMusica(usuario);
        boolean result = resultado.isError();
        boolean expResult = false;
        assertEquals(expResult, result);
        System.out.println("Prueba de cambio de calidad exitoso");
    }

    @Test
    public void recuperarCatalogoUsuariosTest() {
        System.out.println("Prueba del método recuperarCatalogosUsuarios");
        String tipoUsuario = "Artista";

        Response resultado = HttpUtils.recuperarCatalogoUsuarios();

        List<TipoUsuario> tipos = new Gson().fromJson(resultado.getResult(), new TypeToken<List<TipoUsuario>>() {
        }.getType());

        assertEquals(tipoUsuario, tipos.get(1).getTipoUsuario());
        System.out.println("Éxito de igualdad de tipos de usuario");
    }

    @Test
    public void recuperarUsuarioPorNombreUsuarioTest() {
        System.out.println("Prueba del método recuperarUsuarioPorNombreUsuario");
        String nombreUsuario = "Esmeyhg";
        usuario.setNombreUsuario(nombreUsuario);

        String nombre = "Esmeralda";

        Response resultado = HttpUtils.recuperarUsuarioPorNombreUsuario(usuario);

        usuario = new Gson().fromJson(resultado.getResult(), Usuario.class);

        assertEquals(nombre, usuario.getNombre());
        System.out.println("Éxito de igualdad de nombres");
    }

    @Test
    public void recuperarUsuarioPorIdTest() {
        System.out.println("Prueba del método recuperarUsuarioPorId");
        usuario.setIdUsuario(1);

        String nombre = "Esmeralda";

        Response resultado = HttpUtils.recuperarUsuarioPorId(usuario);

        usuario = new Gson().fromJson(resultado.getResult(), Usuario.class);

        assertEquals(nombre, usuario.getNombre());
        System.out.println("Éxito de igualdad de nombres");
    }

    @Test
    public void recuperarCatalogoGenerosTest() {
        System.out.println("Prueba del método recuperarCatalogoGeneros");
        String genero = "Blues";

        Response resultado = HttpUtils.recuperarCatalogoGeneros();

        List<Genero> tipos = new Gson().fromJson(resultado.getResult(), new TypeToken<List<Genero>>() {
        }.getType());

        assertEquals(genero, tipos.get(0).getGenero());
        System.out.println("Éxito de igualdad de géneros");
    }
}
