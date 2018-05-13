package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;

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

    
}
