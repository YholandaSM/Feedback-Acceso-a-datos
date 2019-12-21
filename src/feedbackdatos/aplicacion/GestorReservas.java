package feedbackdatos.aplicacion;

import feedbackdatos.HibernateUtil;
import feedbackdatos.Reservas;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Jose
 */
public class GestorReservas {

    public void altaReservas(int idCliente, int idcoche, Date fechaIni, Date fechaDevol,
            float precio, float litros) {

        SessionFactory factoria = HibernateUtil.getSessionFactory();
        Session sesion = factoria.openSession();

        Transaction trans = sesion.beginTransaction();

        System.out.println("Comprobamos que no haya ya una reserva para el"
                + " mismo coche y fechas..");

        //Si exsite el cliente, existe el coche y no hay una reserva con los mismos criterios
        //se da de alta
        if (Consultas.consultaExisteCliente(idCliente) && Consultas.consultaExisteCoche(idcoche)
                && !Consultas.existeReserva(idcoche, fechaIni, fechaDevol)) {

            Reservas reserva = new Reservas();
            reserva.setIdReserva(new GetId().getIdReservas());
            reserva.setIdCliente(idCliente);
            reserva.setIdCoche(idcoche);
            reserva.setFechaInicio(fechaIni);
            reserva.setFechaDevolucion(fechaDevol);
            reserva.setPrecioTotal(precio);
            reserva.setLitros(litros);

            try {

                sesion.save(reserva);
                trans.commit();
            } catch (ConstraintViolationException c) {
                System.out.println("Reserva duplicada");

            } finally {

                sesion.close();
                System.exit(0);

            }

        } else {

            System.out.println("No se puede dar de alta..");

        }

        System.out.println("Insertamos una fila en la tabla RESERVAS...");

    }

}
