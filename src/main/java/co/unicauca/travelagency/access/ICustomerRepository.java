package co.unicauca.travelagency.access;

import co.unicauca.travelagency.domain.Customer;
import java.util.List;


/**
 * Interface del respositorio de clientes
 * @author mfcaicedo 
 */
public interface ICustomerRepository {
    /**
     * Busca un Customer por su ceduka
     * @param id cedula del cliente
     * @return  objeto de tipo Customer
     */
    List<Customer> findAll();
    Customer findById(String id);
    boolean create(Customer customer);
    boolean update(Customer customer);
    boolean delete(String id);

}
