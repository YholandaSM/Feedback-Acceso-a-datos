package feedbackdatos.aplicacion;

import feedbackdatos.Clientes;
import feedbackdatos.Coches;
import feedbackdatos.HibernateUtil;
import feedbackdatos.Reservas;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Clase con consultas sobre la BBDD
 *
 * @author Jose
 */
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
     * periodo de fechas indicadas.
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
     * Método que devuelve una lista con todas las reservas que existen en BBDD
     *
     * @return
     */
    public static ArrayList<Reservas> getReservas() {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = " from Reservas";

        Query query = sesion.createQuery(hql);

        ArrayList<Reservas> lista = (ArrayList<Reservas>) query.list();

        if (lista.size() == 0) {
            System.out.println("No hay reservas");
        }
        return lista;

    }

    /**
     * *********************APARTADO
     * 3******************************************
     */
    /**
     * Método que consulta clientes por diferentes criterios: nombre y
     * apellidos. Hay 4 posibilidades a la hora de introducir criyerios: 1 Que
     * no introduzca nombre ni apellido. (Se listarán todos los clientes) 2 Que
     * introduzca sólo el apellido 3 Que introduzca sólo el nombre 4 Que
     * introduzca nombre y apellido
     *
     * @param nombre
     * @param apellidos
     */
    public void consultaClientes(String nombre, String apellidos) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "";
        Query query = null;
        //Construimos las querys en función de los parámetros que entran
        //1->No introduce ni nombre ni apellido
        if ((nombre == null || nombre.equals("")) && (apellidos == null || apellidos.equals(""))) {
            hql = " from Clientes";
            query = sesion.createQuery(hql);
        } else {
            if ((nombre == null || nombre.equals("")) && apellidos.length() > 0) {
                //2->Introduce apellido
                hql = " from Clientes where apellidos =:apellidos";
                query = sesion.createQuery(hql);
                query.setParameter("apellidos", apellidos);
            } else if ((apellidos == null || apellidos.equals("")) && nombre.length() > 0) {
                //3->Introduce nombre
                hql = " from Clientes where nombre =:nombre";
                query = sesion.createQuery(hql);
                query.setParameter("nombre", nombre);
            } else {

                //4->Introduce nombre y apellido
                hql = " from Clientes where nombre =:nombre and apellidos =:apellidos";
                query = sesion.createQuery(hql);
                query.setParameter("nombre", nombre);
                query.setParameter("apellidos", apellidos);
            }

        }

        List<Clientes> lista = query.list();
        if (lista.size() == 0) {

            System.out.println("No hay clientes con esos criterios");
        } else {

            for (Clientes c : lista) {

                System.out.println(c);

            }
        }

        sesion.close();
        System.exit(0);

    }

    /**
     * Método que consulta coches por diferentes criterios: matricula y marca. Hay 4
     * posibilidades:1. que no se introduzca ningún parámetro(se listarán todos los coches),2. que introduzca
     * sólo la matrícula, 3.que introduzca sólo la marca, 4.que introduzca ambos parámetros.
     *
     * @param matricula
     * @param marca
     */
    public void consultaCoches(String matricula, String marca) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "";
        Query query = null;

        //1->No introducen ningún criterio(Se listarán todos los coches)
        if ((matricula == null || matricula.equals("")) && (marca == null || marca.equals(""))) {

            hql = " from Coches";

            query = sesion.createQuery(hql);
        } else {

            //Construimos las querys en función de los parámetros que entran
            if (matricula == null || matricula.equals("")) {
                //2->por marca
                hql = " from Coches where marca =:marca";
                query = sesion.createQuery(hql);
                query.setParameter("marca", marca);
            } else if (marca == null || marca.endsWith("")) {
                //3->por matrícula
                hql = " from Coches where matricula =:matricula";
                query = sesion.createQuery(hql);
                query.setParameter("matricula", matricula);
            } else {

                //4->por marca y por matricula
                hql = " from Coches where matricula =:matricula and marca =:marca";
                query = sesion.createQuery(hql);
                query.setParameter("marca", marca);
                query.setParameter("matricula", matricula);
            }

        }

        List<Coches> lista = query.list();
        if (lista.size() == 0) {
            System.out.println("No hay coches con esos criterios");

        } else {

            for (Coches c : lista) {

                System.out.println(c);

            }

        }

        sesion.close();
        System.exit(0);

    }

    /**
     * *******************APARTADO 4**************************
     */
    /**
     * 4.a Método que recupera un listado de matrículas y números de reserva de
     * los coches que están reservados hoy.
     *
     */
    public static void listadoReservasHoy() {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = " from Reservas r, Coches c where now() between r.fechaInicio and "
                + " r.fechaDevolucion and r.idCoche=c.idCoche order by r.idReserva";

        Query query = sesion.createQuery(hql);

        Iterator q = query.iterate();
        System.out.println("//////////////////////////////////////////////////////////");
        System.out.println("////////////////////RESERVAS HOY///////////////////////////");
        System.out.println("//////////////////////////////////////////////////////////");
        while (q.hasNext()) {

            Object par[] = (Object[]) q.next();
            //Consultamos cada reserva, será el primer objeto del array
            Reservas reserva = (Reservas) par[0];
            //Consultamos cada coche, será el segundo objeto del array
            Coches coche = (Coches) par[1];

            System.out.println("Nº DE RESERVA " + reserva.getIdReserva() + " ,MATRÍCULA "
                    + coche.getMatricula() + " ,FECHAS: " + reserva.getFechaInicio() + " - "
                    + reserva.getFechaDevolucion());
        }

        sesion.close();
        System.exit(0);

    }

    /**
     * 4.b Método que recupera la lista de los clientes con reservas pendientes
     * (aquéllas en las que la fecha de inicio es posterior a la fecha de hoy)
     */
    public static void consultaReservasPendientes() {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = " from Reservas r, Clientes c where r.fechaInicio > now()"
                + " and r.idCliente= c.idCliente order by r.idReserva";
        try {

            Query query = sesion.createQuery(hql);
            Iterator q = query.iterate();
            System.out.println("//////////////////////////////////////////////////////////");
            System.out.println("///////////CLIENTES CON RESERVAS PENDIENTES///////////////////////");
            System.out.println("//////////////////////////////////////////////////////////");
            while (q.hasNext()) {
                Object par[] = (Object[]) q.next();
                //Consultamos cada reserva, será el primer objeto del array
                Reservas reserva = (Reservas) par[0];
                //Consultamos cada coche, será el segundo objeto del array
                Clientes cliente = (Clientes) par[1];

                System.out.println("Cliente :" + cliente.getNombre() + " " + cliente.getApellidos()
                        + " ,reserva nº" + reserva.getIdReserva() + ". Fechas:" + reserva.getFechaInicio()
                        + "--" + reserva.getFechaDevolucion());
            }
        } catch (NoSuchElementException n) {

            System.out.println("No se han encontrado resultados");
        }
        sesion.close();
        System.exit(0);
    }

    /**
     * 4.c Método que recupera el número de coches diferentes reservados a un
     * cliente determinado
     *
     * @param idCliente
     */
    public static void consultaCochesCliente(int idCliente) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "select count(distinct idCoche) "
                + " from Reservas "
                + " where idCliente=:idCliente";

        Query query = sesion.createQuery(hql);
        query.setParameter("idCliente", idCliente);
        long numeroCoches = (long) query.uniqueResult();
        System.out.println("El cliente " + idCliente + " ha reservado " + numeroCoches + " coches.");
        sesion.close();
        System.exit(0);

    }

    /**
     * Método que recupera el número de veces que se ha reservado un coche
     * pasado por parámetro
     *
     * @param idCoche
     */
    public static void consultaNumeroReservasCoche(int idCoche) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        String hql = "select count(r.idCoche) from Reservas r, Coches c"
                + " where r.idCoche=c.idCoche "
                + " and r.idCoche=:idCoche";

        Query query = sesion.createQuery(hql);
        query.setParameter("idCoche", idCoche);
        long numero = (long) query.uniqueResult();
        System.out.println("El coche " + idCoche + " se ha reservado " + numero + " veces");

        sesion.close();
        System.exit(0);

    }

}
