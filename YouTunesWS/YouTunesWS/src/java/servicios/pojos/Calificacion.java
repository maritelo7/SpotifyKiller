package servicios.pojos;

/**
 *
 * @author yamii
 */
public class Calificacion {
    private int id;
    private int valoracion;
    private int idCancion;

    public Calificacion(int id, int valoracion, int idCancion) {
        this.id = id;
        this.valoracion = valoracion;
        this.idCancion = idCancion;
    }

    public Calificacion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }
}
