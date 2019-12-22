package feedbackdatos.aplicacion;

import feedbackdatos.Clientes;
import feedbackdatos.HibernateUtil;
import javax.swing.JOptionPane;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Clase para gestionar clientes
 * @author Yolanda
 */
public class GestorClientes {

    /**
     * 
     * @param dni
     * @param nombre
     * @param apellidos
     * @param direc
     * @param telefono
     */
    public void altaCliente(String dni, String nombre, String apellidos,
            String direc, String telefono) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        Transaction trans = sesion.beginTransaction();

        System.out.println("Insertamos una fila en la tabla CLIENTES...");

        Clientes cliente = new Clientes();
        cliente.setIdCliente(new GetId().getIdCliente());
        cliente.setDni(dni);
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setDireccion(direc);
        cliente.setTelefono(telefono);

        try {

            sesion.save(cliente);
            trans.commit();
            JOptionPane.showMessageDialog(null, "Se ha insertado el cliente " + cliente.getNombre()
                    + " " + cliente.getApellidos());
        } catch (ConstraintViolationException c) {
            System.out.println("Coche duplicado");

        } finally {

            sesion.close();
            System.exit(0);

        }

    }

    /**
     *
     * @param id
     */
    public void bajaCliente(int id) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        Transaction trans = sesion.beginTransaction();

        //Recuperamos el cliente que queremos borrar
        Clientes cliente = (Clientes) sesion.load(Clientes.class, id);

        try {
            sesion.delete(cliente);
            trans.commit();
            JOptionPane.showMessageDialog(null, "Se ha eliminado el cliente " + cliente.getNombre()
                    + " " + cliente.getApellidos());
            System.out.println("Cliente eliminado: " + id);
        } catch (ObjectNotFoundException o) {

            System.out.println("Error: no existe el cliente: " + id);

        } catch (Exception e) {

            System.out.println("Error");
            e.printStackTrace();

        }

        sesion.close();
        System.exit(0);

    }

    /**
     *
     * @param id
     * @param direccion
     * @param telefono
     */
    public void modificarCliente(int id, String direccion, String telefono) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        Transaction trans = sesion.beginTransaction();
        try {
            //Recuperamos el cliente que vamos a modificar
            Clientes cliente = (Clientes) sesion.load(Clientes.class, id);
            System.out.printf("Modificamos el cliente: %d%n", cliente.getIdCliente());
            System.out.println("Dirección anterior:  "+cliente.getDireccion());
            System.out.println("Teléfono anterior:  "+cliente.getTelefono());

            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);

            sesion.update(cliente);
            trans.commit();
             JOptionPane.showMessageDialog(null, "Se ha modificado el cliente con id "+
                     cliente.getIdCliente() +" "+ cliente.getNombre()
                    + " " + cliente.getApellidos());
            System.out.printf("Dirección nueva: %s%n", cliente.getDireccion());
            System.out.printf("Teléfono nuevo: %s%n", cliente.getTelefono());

        } catch (ObjectNotFoundException o) {

            System.out.println("Error: no existe el cliente: " + id);
            JOptionPane.showMessageDialog(null," No existe el cliente: " + id );

        } catch (Exception e) {

            System.out.println("Error");
            e.printStackTrace();

        }

        sesion.close();
        System.exit(0);

    }

}
