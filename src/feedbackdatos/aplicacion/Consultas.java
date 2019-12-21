package feedbackdatos.aplicacion;

import feedbackdatos.Clientes;
import feedbackdatos.Coches;
import feedbackdatos.HibernateUtil;
import feedbackdatos.Reservas;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

        if (cliente == null) {
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

        if (coche == null) {
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
                + " and fechaInicio >=:fechaIni and fechaDevolucion<=:fechaFin";
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

    /**
     * *********************APARTADO
     * 3******************************************
     */
    /**
     * Método que consulta clientes por diferentes criterios: nombre y apellidos
     *
     * @param nombre
     * @param apellidos
     */
    public void consultaClientes(String nombre, String apellidos) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "";
        //Construimos las querys en función de los parámetros que entran
        if (nombre == null) {
            //por apellido
            hql = " from Clientes where apellidos =:apellidos";
        } else if (apellidos == null) {
            //por nombre
            hql = " from Clientes where nombre =:nombre";
        } else {

            //por apellido y por nombre
            hql = " from Clientes where nombre =:nombre and apellidos =:apellidos";
        }

        Query query = sesion.createQuery(hql);
        List<Clientes> lista = query.list();

        for (Clientes c : lista) {

            System.out.println(c);

        }

    }

    /**
     * Método que consulta coches por diferentes criterios: matricula y marca
     *
     * @param matricula
     * @param marca
     */
    public void consultaCoches(String matricula, String marca) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "";
        //Construimos las querys en función de los parámetros que entran
        if (matricula == null) {
            //por marca
            hql = " from Coches where marca =:marca";
        } else if (marca == null) {
            //por matrícula
            hql = " from Coches where matricula =:matricula";
        } else {

            //por marca y por matricula
            hql = " from Coches where matricula =:matricula and marca =:marca";
        }

        Query query = sesion.createQuery(hql);
        List<Coches> lista = query.list();

        for (Coches c : lista) {

            System.out.println(c);

        }

    }

    /**
     * *******************APARTADO 4**************************
     */
    /**
     * 4.a-> Método que ofrece un listado de matrículas y números de reserva de
     * los coches que están reservados hoy.
     *
     */
    public void listadoReservasHoy() {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = " form Reservas r, Coches c where now() between r.fechaInicio and "
                + " r.fechaDevolucion and r.idCoche=c.IdCoche order by c.matricula";

        Query query = sesion.createQuery(hql);

        Iterator q = query.iterate();

        while (q.hasNext()) {

            Object par[] = (Object[]) q.next();
            //Consultamos cada reserva, será el primer objeto del array
            Reservas reserva = (Reservas) par[0];
            //Consultamos cada coche, será el segundo objeto del array
            Coches coche = (Coches) par[1];

            System.out.println("Número de reserva " + reserva.getIdReserva() + " matrícula "
                    + coche.getMatricula());
        }

    }

    /**
     * 4.b-> Método que recupera la lista de los clientes con reservas
     * pendientes (aquéllas en las que la fecha de inicio es posterior a la
     * fecha de hoy)
     */
    public void consultaReservasPendientes() {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = " form Reservas r, Clientes c where r.fechaInicio > now()"
                + " and r.idCliente= c.idCliente";

        Query query = sesion.createQuery(hql);
        Iterator q = query.iterate();

        Object par[] = (Object[]) q.next();
        //Consultamos cada reserva, será el primer objeto del array
        Reservas reserva = (Reservas) par[0];
        //Consultamos cada coche, será el segundo objeto del array
        Clientes clientes = (Clientes) par[1];

        System.out.println(clientes);

    }

    /**
     * 4.c-> Método que recupera el número de coches diferentes reservados a un
     * cliente determinado
     *
     * @param idCliente
     */
    public void consultaCochesCliente(int idCliente) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "select count(idCoche) from Reservas r, Clientes c"
                + " where r.idCliente=c.idCliente "
                + " and r.idCliente=:idCliente"
                + " group by idCoche";

        Query query = sesion.createQuery(hql);
        int numeroCoches = (int) query.uniqueResult();
        System.out.println("El número de coches es " + numeroCoches);

    }

    /**
     * Método que recupera el número de veces que se ha reservado un coche
     * pasado por parámetro
     *
     * @param idCoche
     */
    public void consultaNumeroReservasCoche(int idCoche) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "select count(idCoche) from Reservas r, Coches c"
                + " where r.idCoche=c.idCoche "
                + " and r.idCoche=:idCoche";

        Query query = sesion.createQuery(hql);
        int numero = (int) query.uniqueResult();
        System.out.println("El coche " + idCoche + " se ha reservado " + numero + " veces");

    }

}
