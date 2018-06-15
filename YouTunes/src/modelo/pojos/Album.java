package modelo.pojos;

/**
 *
 * @author Mari
 */
public class Album {
    private int idAlbum;
    private String titulo; 
    private String pathPortada;
    private int anioLanzamiento;
    private String companiaDiscografica;
    private int itdUsuario;

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
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

    public int getItdUsuario() {
        return itdUsuario;
    }

    public void setItdUsuario(int itdUsuario) {
        this.itdUsuario = itdUsuario;
    }
}
