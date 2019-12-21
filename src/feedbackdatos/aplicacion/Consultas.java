package feedbackdatos.aplicacion;

import feedbackdatos.Clientes;
import feedbackdatos.Coches;
import feedbackdatos.HibernateUtil;
import feedbackdatos.Reservas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Consultas {

    /**
     * Método que comprueba si un cliente existe consultando por id
     *
     * @param id
     * @return si existe devuelve true, sino false.
     */
    public static boolean consultaExisteCliente(int id) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "from Clientes where idCliente = :id";
        Query query = sesion.createQuery(hql);
        query.setParameter("id", id);

        //La consulta devolverá un único resultado
        Clientes cliente = (Clientes) query.uniqueResult();

        if (cliente != null) {
            System.out.println("No existe el cliente");
            return false;
        }

        return true;

    }

    /**
     * Método que comprueba si un coche existe consultando por id
     *
     * @param id
     * @return si existe devuelve true, sino false.
     */
    public static boolean consultaExisteCoche(int id) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "from Coches where idCoche = :id";
        Query query = sesion.createQuery(hql);
        query.setParameter("id", id);

        //La consulta devolverá un único resultado
        Coches coche = (Coches) query.uniqueResult();

        if (coche != null) {
            System.out.println("No existe el coche");
            return false;
        }

        return true;

    }

    /**
     * Método que comprueba si existe una reserva para el mismo coche en el
     * periodo de fechas inidicadas.
     *
     * @param idCoche
     * @param fechaIni
     * @param fechaFin
     * @return si existe devuelve true, sino false.
     */
    public static boolean existeReserva(int idCoche,
            Date fechaIni, Date fechaFin) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        List<Reservas> lista = new ArrayList<Reservas>();

        String hql = "from Reservas where   idCoche=:idCoche"
                + " and fechaInicio >=fechaIni and fechaDevolucion<=fechaFin";
        Query query = sesion.createQuery(hql);

        query.setParameter("idCoche", idCoche);
        query.setParameter("fechaIni", fechaIni);
        query.setParameter("fechaFin", fechaFin);

        lista = query.list();

        if (lista.size() == 0) {

            return false;
        }
        System.out.println("Ya hay una reserva para ese coche y esas fechas");
        return true;
    }

}
