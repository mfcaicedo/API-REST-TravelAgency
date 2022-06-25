package co.unicauca.travelagency.presentation;

import co.unicauca.travelagency.domain.Customer;
import co.unicauca.travelagency.domain.service.CustomerService;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 * API REST de los servicios web. Son nuestros servicios web. La anotación @Path
 * indica la URL en la cual responderá los servicios. Esta anotación se puede
 * poner a nivel de clase y método. En este ejemplo todos los servicios
 * comparten el mismo Path, la acción se hace mediante la anotació GET
 * (consultar), POST (agregar), PUT (editar), DELETE (eliminar).
 * 
 * @author mfcaicedo
 */
@Stateless
@Path("customers")
public class CustomerController {
    
    /**
     * Se inyecta la dependencia de la implementación CustomerService
     */
    @Inject
    private CustomerService service;

    public CustomerController() {
    }
    /*
    Su uso desde consola mediante client url:
    curl -X POST \
    http://localhost:8080/API-REST-TravelAgency/customer-service/customers/
    -H 'Content-Type: application/json' \
    -d '{"address":"Calle 14 No 11-12 Popayan",
    "email":"andrea@hotmail.com",
    "firstName":"Milthon",
    "gender":"Masculino",
    "id":"123",
    "lastName":"Caicedo",
    "mobile":"3145878752"}'
    */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String create(Customer customer) {
        if (service.create(customer)) {
            return "{\"ok\":\"true\", \"mensaje\":\"Cliente creado con éxito\",\"errores\":\"\"}";
        } else {
            return "{\"ok\":\"false\", \"mensaje\":\"No se pudo crear el cliente\",\"errores\":\"Id ya existe\"}";
        }
    }
    /*
    Su uso desde consola mediante client url:
    curl -X PUT \
    http://localhost:8080/API-REST-TravelAgency/customer-service/customers/
    -H 'Content-Type: application/json' \
    -d '{"address":"Calle 14 No 11-12 Popayan",
    "email":"andrea@hotmail.com",
    "firstName":"Milthon",
    "gender":"Masculino",
    "id":"123",
    "lastName":"Caicedo",
    "mobile":"3145878752"}'
    */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String update(Customer customer) {
        if (service.update(customer)) {
            return "{\"ok\":\"true\", \"mensaje\":\"Cliente modificado exitosamente\",\"errores\":\"\"}";
        } else {
            return "{\"ok\":\"false\", \"mensaje\":\"No se pudo modificar el cliente\",\"errores\":\"Id no existe\"}";
        }
    }
    /*
    Su uso desde consola mediante client url:
    curl -X DELETE http://localhost:8080/API-REST-TravelAgency/customer-service/customers/
    */
    @DELETE
    @Path("{id}")
    public String remove(@PathParam("id") String id) {
        if (service.delete(id)) {
            return "{\"ok\":\"true\", \"mensaje\":\"Cliente borrado con éxito\",\"errores\":\"\"}";
        } else {
            return "{\"ok\":\"false\", \"mensaje\":\"No se pudo borrar el cliente\",\"errores\":\"Id no existe\"}";
        }
    }

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Asynchronous
    public void findAll(@Suspended final AsyncResponse asyncResponse) {
        asyncResponse.resume(doFindAll());
    }

    private List<Customer> doFindAll() {
        return service.findAll();
    }

    @GET
    @Path(value = "{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Asynchronous
    public void findById(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "id") final String id) {
        asyncResponse.resume(doFindById(id));
    }

    private Customer doFindById(@PathParam("id") String id) {
        return service.findById(id);
    }
    
}
