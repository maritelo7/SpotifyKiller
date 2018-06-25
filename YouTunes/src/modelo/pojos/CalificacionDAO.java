package modelo.pojos;

/**
 *
 * @author yamii
 */
public class CalificacionDAO {
    private int id;
    private int idCancion;
    private int valoracion;

    public CalificacionDAO() {
    }

    public CalificacionDAO(int id, int idCancion, int valoracion) {
        this.id = id;
        this.idCancion = idCancion;
        this.valoracion = valoracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
    
}
