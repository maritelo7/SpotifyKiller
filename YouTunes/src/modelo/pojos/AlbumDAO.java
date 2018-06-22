package modelo.pojos;

/**
 *
 * @author Mari
 */
public class AlbumDAO {
    private int id;
    private String titulo; 
    private String pathPortada;
    private int anioLanzamiento;
    private String companiaDiscografica;
    private int idUsuario;

    public int getIdAlbum() {
        return id;
    }

    public void setIdAlbum(int idAlbum) {
        this.id = idAlbum;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPathPortada() {
        return pathPortada;
    }

    public void setPathPortada(String pathPortada) {
        this.pathPortada = pathPortada;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getCompaniaDiscografica() {
        return companiaDiscografica;
    }

    public void setCompaniaDiscografica(String companiaDiscografica) {
        this.companiaDiscografica = companiaDiscografica;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int itdUsuario) {
        this.idUsuario = itdUsuario;
    }
}
