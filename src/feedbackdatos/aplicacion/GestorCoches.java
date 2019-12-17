/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedbackdatos.aplicacion;

import feedbackdatos.Clientes;
import feedbackdatos.Coches;
import feedbackdatos.HibernateUtil;
import java.util.Date;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Jose
 */
public class GestorCoches {

    public void altaCoche(String matricula, float precio, String color,
            String marca, Date fechaMatr) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        Transaction trans = sesion.beginTransaction();

        System.out.println("Insertamos una fila en la tabla COCHES...");

        //Creamos un nuevo objeto coche
        Coches coche = new Coches();
        coche.setIdCoche(new GetId().getIdCoche());
        coche.setMatricula(matricula);
        coche.setPrecio(precio);
        coche.setColor(color);
        coche.setMarca(marca);
        coche.setFechaMatriculacion(fechaMatr);

        try {

            sesion.save(coche);
            trans.commit();
        } catch (ConstraintViolationException c) {
            System.out.println("Coche duplicado");

        } finally {

            sesion.close();
            System.exit(0);

        }

    }

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
        } catch (ObjectNotFoundException o) {

            System.out.println("Error: no existe el coche: " + id);

        } catch (Exception e) {

            System.out.println("Error");
            e.printStackTrace();

        }

        sesion.close();
        System.exit(0);

    }

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
            System.out.printf("Fecha anterior: %d%n", coche.getFechaMatriculacion());

            coche.setPrecio(precio);
            coche.setColor(color);
            coche.setMarca(marca);
            coche.setFechaMatriculacion(fecha);

            sesion.update(id);
            trans.commit();

            System.out.printf("Precio nuevo: %.2f%n", coche.getPrecio());
            System.out.printf("Color nuevo: %s%n", coche.getColor());
            System.out.printf("Marca nuevo: %s%n", coche.getMarca());
            System.out.printf("Fecha nuevo: %d%n", coche.getFechaMatriculacion());

        } catch (ObjectNotFoundException o) {

            System.out.println("Error: no existe el cliente: " + id);

        } catch (Exception e) {

            System.out.println("Error");
            e.printStackTrace();

        }

        sesion.close();
        System.exit(0);

    }

}
