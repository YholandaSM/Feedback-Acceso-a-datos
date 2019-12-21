/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedbackdatos.aplicacion;

import feedbackdatos.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jose
 */
public class GetId {

    //Abrimos una nueva conexión con BBDD
    SessionFactory factoria = HibernateUtil.getSessionFactory();
    Session sesion = factoria.openSession();
    Transaction tx = sesion.beginTransaction();

    public int getIdCoche() {

        Query query = sesion.createQuery("select coalesce(max(idCoche),1) from Coches");

        int id = (int) query.uniqueResult();

        return (int) (id + 1);

    }
    
    
    public int getIdCliente() {

        Query query = sesion.createQuery("select coalesce(max(idCliente),1) from Clientes");

        int id = (int) query.uniqueResult();

        return (int) (id + 1);

    }
    
    public int getIdReservas() {

        Query query = sesion.createQuery("select coalesce(max(idReserva),1) from Reservas");

        int id = (int) query.uniqueResult();

        return (int) (id + 1);

    }


}
