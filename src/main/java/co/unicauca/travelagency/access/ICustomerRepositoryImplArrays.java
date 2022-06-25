package co.unicauca.travelagency.access;

import co.unicauca.travelagency.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Default;

/**
 * Implementación de ICustomerRepository. Utilliza arreglos en memoria
 *
 * @author mfcaicedo
 */
@Default
public class ICustomerRepositoryImplArrays implements ICustomerRepository {

    /**
     * Array List de clientes
     */
    private static List<Customer> customers;

    public ICustomerRepositoryImplArrays() {
        if (customers == null){
            customers = new ArrayList<>();
        }
        
        if (customers.size() == 0){
            inicializar();
        }
    }

    public void inicializar() {
        customers.add(new Customer("98000001", "Andrea", "Sanchez", "Calle 14 No 11-12 Popayan", "3145878752", "andrea@hotmail.com", "Femenino"));
        customers.add(new Customer("98000002", "Libardo", "Pantoja", "Santa Barbar Popayan", "3141257845", "libardo@gmail.com", "Masculino"));
        customers.add(new Customer("98000003", "Carlos", "Pantoja", "Santa Barbar Popayan", "3141257846", "carlos@gmail.com", "Masculino"));
        customers.add(new Customer("98000004", "Fernanda", "Arevalo", "Calle 16 No 12-12 Popayan", "3154562133", "fercha@hotmail.com", "Femenino"));
        customers.add(new Customer("98000005", "Manuel", "Perez", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000006", "Alejandro", "Mosquera", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000007", "Cesar", "Gutierres Sanchez", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000008", "Julio", "Bravo Bravo", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000009", "Alberto", "Mendez Bravo", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));
        customers.add(new Customer("98000010", "Alexander", "Ponce Yepes", "Calle 12 No 12-12 Popayan", "3154575845", "fer@hotmail.com", "Masculino"));

    }
    /**
     * Busqueda de todos los clientes 
     * @return lista de todos los clientes 
     */
    @Override
    public List<Customer> findAll() {
       return customers;
    }
    /**
     * Busqueda de cliente por id 
     * @param id id del cliente a buscar 
     * @return objeto de cliente en caso de ser econtrado o null en caso contrario 
     */
    @Override
    public Customer findById(String id) {
        for (Customer customer : customers) {
            if (customer.getId() == null ? id == null : customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }
    /**
     * Crea un cliente 
     * @param customer cliente a crear 
     * @return true en caso de ser correcto false en caso contrario 
     */
    @Override
    public boolean create(Customer customer) {
        Customer auxcustomer = this.findById(customer.getId());
        if (auxcustomer != null) {
            return false;
        }
        customers.add(customer);
        return true;
    }
    /**
     * Actualiza un cliente 
     * @param customer cliente a modificar 
     * @return true en caso de ser correcto false en caso contrario 
     */
    @Override
    public boolean update(Customer customer) {
        Customer auxCustomer = this.findById(customer.getId());
        if (auxCustomer != null) {
            auxCustomer.setFirstName(customer.getFirstName());
            auxCustomer.setLastName(customer.getLastName());
            auxCustomer.setAddress(customer.getAddress());
            auxCustomer.setMobile(customer.getMobile());
            auxCustomer.setEmail(customer.getEmail());
            auxCustomer.setGender(customer.getEmail());
            return true;
        }
        return false;
    }
    /**
     * Elimina un cliente 
     * @param id id del cliente a eliminar 
     * @return true en caso de ser correcto false en caso contrario. 
     */
    @Override
    public boolean delete(String id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(id)) {
                customers.remove(i);
                return true;
            }
        }
        return false;
    }
}
