package modelo.pojos;
/**
 * @author Mari
 */
public class ListaReproduccion {
    private int id;
    private int idUsuario;
    private String nombreLista;
    private String descripcion;

    public int getIdListaReproduccion() {
        return id;
    }

    public void setIdListaReproduccion(int idListaReproduccion) {
        this.id = idListaReproduccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String toString(){
        return nombreLista;
    }
}
