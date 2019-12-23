package feedbackdatos.aplicacion;

import feedbackdatos.HibernateUtil;
import feedbackdatos.Reservas;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Clase para gestionar las reservas
 * @author Jose
 */
public class GestorReservas {
    
    /**
     * Método que da de alta un registro en la tabla reservas. Se comprobará previamente 
     * que exista el cliente, que exista el coche y que no haya una reserva ya en las mismas fechas
     * para el mismo coche.
     * @param idCliente
     * @param idcoche
     * @param fechaIni
     * @param fechaDevol
     * @param precio
     * @param litros 
     */
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

            System.out.println("Insertamos una fila en la tabla RESERVAS...");

            Reservas reserva = new Reservas();
            reserva.setIdCliente(idCliente);
            reserva.setIdCoche(idcoche);
            reserva.setFechaInicio(fechaIni);
            reserva.setFechaDevolucion(fechaDevol);
            reserva.setPrecioTotal(precio);
            reserva.setLitros(litros);

            try {

                sesion.save(reserva);
                trans.commit();
                JOptionPane.showMessageDialog(null, "Insertada una reserva con id "+reserva.getIdReserva());

            } catch (ConstraintViolationException c) {
                System.out.println("Reserva duplicada");

            } finally {

                sesion.close();
                System.exit(0);

            }

        } else {

            System.out.println("No se puede dar de alta..");

        }

    }

}
