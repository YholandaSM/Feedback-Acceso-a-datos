package feedbackdatos.aplicacion;

import feedbackdatos.Clientes;
import feedbackdatos.Coches;
import feedbackdatos.HibernateUtil;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Clase para gestionar coches: altas, bajas y modificaciones.
 * @author Yolanda
 */
public class GestorCoches {
    /**
     * Método que inserta un registro en la tabla coches con los datos pasados por
     * parámetro.
     * 
     * @param matricula
     * @param precio
     * @param color
     * @param marca
     * @param fechaMatr 
     */
    public void altaCoche(String matricula, float precio, String color,
            String marca, Date fechaMatr) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        Transaction trans = sesion.beginTransaction();

        System.out.println("Insertamos una fila en la tabla COCHES...");

        //Creamos un nuevo objeto coche
        Coches coche = new Coches();
        coche.setMatricula(matricula);
        coche.setPrecio(precio);
        coche.setColor(color);
        coche.setMarca(marca);
        coche.setFechaMatriculacion(fechaMatr);

        try {

            sesion.save(coche);
            try {

                trans.commit();
            } catch (ConstraintViolationException e) {

                System.out.println("Cliente duplicado");
                System.out.println("Mensaje " + e.getMessage());
                System.out.println("Cod. error " + e.getErrorCode());
                System.out.println("Error sql" + e.getSQLException());

            }

            JOptionPane.showMessageDialog(null, "Se ha insertado el coche " + coche.getIdCoche()
                    + "-Matrícula: " + coche.getMatricula());
        } catch (TransientPropertyValueException c) {
            System.out.println("Coche duplicado");
            //Al ejecutarse el método save() puede detectar si exist el coche
            System.out.println("ERROR: El coche no existe");
            System.out.println("Mensaje: " + c.getMessage());
            System.out.println("Propiedad: " + c.getPropertyName());
        } catch (Exception e) {
            System.out.println("No se ha podido dar de alta..." + e.getMessage());
        } finally {

            sesion.close();
            System.exit(0);

        }

    }
    /**
     * Método que elimina el registro de la tabla coches cuyo id es el indicado por parámetro.
     * @param id 
     */
    public void bajaCoche(int id) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        Transaction trans = sesion.beginTransaction();

        //Recuperamos el coche que queremos borrar
        Coches coche = (Coches) sesion.load(Coches.class, id);

        try {
            sesion.delete(coche);
            trans.commit();
            System.out.println("Coche eliminado: " + id);
            JOptionPane.showMessageDialog(null, "Se ha eliminado el coche " + coche.getIdCoche()
                    + "-Matrícula: " + coche.getMatricula());
        } catch (ObjectNotFoundException o) {
            System.out.println("Error: no existe el coche: " + id);
        } catch (Exception e) {

            System.out.println("Error");
            e.printStackTrace();

        }

        sesion.close();
        System.exit(0);

    }
    /**
     * Método que modifica un registro de la tabla coches con los datos pasados por parámetro
     * @param id
     * @param precio
     * @param color
     * @param marca
     * @param fecha 
     */
    public void modificarCoche(int id, float precio, String color, String marca,
            Date fecha) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        Transaction trans = sesion.beginTransaction();
        try {
            //Recuperamos el coche que vamos a modificar
            Coches coche = (Coches) sesion.load(Coches.class, id);
            System.out.printf("Modificamos el coche: %d%n", coche.getIdCoche());
            System.out.printf("Precio anterior: %.2f%n", coche.getPrecio());
            System.out.printf("Color anterior: %s%n", coche.getColor());
            System.out.printf("Marca anterior: %s%n", coche.getMarca());
            System.out.println("Fecha anterior: " + coche.getFechaMatriculacion());

            coche.setPrecio(precio);
            coche.setColor(color);
            coche.setMarca(marca);
            if (fecha != null) {
                coche.setFechaMatriculacion(fecha);
            }

            sesion.update(coche);
            trans.commit();

            System.out.printf("Precio nuevo: %.2f%n", coche.getPrecio());
            System.out.printf("Color nuevo: %s%n", coche.getColor());
            System.out.printf("Marca nuevo: %s%n", coche.getMarca());
            System.out.println("Fecha nuevo: " + coche.getFechaMatriculacion());

            JOptionPane.showMessageDialog(null, "Se ha modificado el coche " + coche.getIdCoche()
                    + "-Matrícula: " + coche.getMatricula());

        } catch (ObjectNotFoundException o) {
            System.out.println("Error: no existe el coche: " + id);
        } catch (Exception e) {

            System.out.println("Error");
            e.printStackTrace();

        }

        sesion.close();
        System.exit(0);

    }

}
