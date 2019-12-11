package feedbackdatos.aplicacion;

import feedbackdatos.Clientes;
import feedbackdatos.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Yolanda
 */
public class GestorClientes {

    public void altaCliente(String dni, String nombre, String apellidos,
            String direc, String telefono) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        Transaction trans = sesion.beginTransaction();

        System.out.println("Insertamos una fila en la tabla CLIENTES...");
        
        Clientes cliente= new Clientes();
        cliente.setIdCliente(new GetId().getIdCliente());
        cliente.setDni(dni);
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setDireccion(direc);
        cliente.setTelefono(telefono);
        
        
        try {

            sesion.save(cliente);
            trans.commit();
        } catch (ConstraintViolationException c) {
            System.out.println("Coche duplicado");

        } finally {

            sesion.close();
            System.exit(0);

        }
        
        

    }

}
