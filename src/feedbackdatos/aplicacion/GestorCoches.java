/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedbackdatos.aplicacion;

import feedbackdatos.Coches;
import feedbackdatos.HibernateUtil;
import java.util.Date;
import java.util.GregorianCalendar;
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
}
