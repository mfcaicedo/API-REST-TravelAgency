package co.unicauca.travelagency.domain.service;

import co.unicauca.travelagency.access.ICustomerRepository;
import co.unicauca.travelagency.commons.infra.JsonError;
import co.unicauca.travelagency.commons.infra.Utilities;
import co.unicauca.travelagency.domain.Customer;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * Fachada de acceso al negocio por parte de la presentación. Usa el repositorio
 * por defecto. Si no se pone @Default tambien funciona, pues inyecta la
 * implementaició por defecto
 * @author mfcaicedo
 */
@RequestScoped
public class CustomerService {
    @Inject
    @Default
    ICustomerRepository repo;

    /**
     * Constructor vacio. Hace inyeccion de dependencias
     */
    public CustomerService() {      
    }
    /**
     * Fachada para invocar al método del repositorio findAll()
     * @return lista del clientes. 
     */
    public List<Customer> findAll(){
        return repo.findAll();
    }
    /**
     * Fachada para invocar al método del repositorio findById()
     * @param id id del cliente a buscar 
     * @return cliente correspondiente al id 
     */
    public Customer findById(String id){
        return repo.findById(id);
    }
    /**
     * Fachada para invocar al método del repositorio create()
     * @param customer cliente a crear 
     * @return true en caso correcto false en caso contrario 
     */
    public boolean create(Customer customer){
        return repo.create(customer);
    }
    /**
     * Fachada para invocar al método del repositorio  update()
     * @param customer cliente a actualizar 
     * @return true en caso correcto false en caso contrario 
     */
    public boolean update(Customer customer){
        return repo.update(customer);
    }
    /**
     * Fachada para invocar al método del repositorio delete()
     * @param id id del cliente a eliminar 
     * @return true en caso correcto false en caso contrario. 
     */
    public boolean delete(String id){
        return repo.delete(id);
    }

}
