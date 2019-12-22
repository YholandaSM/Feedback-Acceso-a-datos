/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedbackdatos.aplicacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Método principal. Muestra un menú con 15 opciones para seleccionar
 *  
 * @author Yolanda
 */
public class Principal {

    public static void main(String[] args) {
        int opcion = 0;

        do {
            mostrarMenu();
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Escribe una opción"));

            switch (opcion) {

                case 1://Alta coche

                    GestorCoches gestorCoches = new GestorCoches();
                    String matricula = JOptionPane.showInputDialog("Matrícula");
                    float precio = Float.parseFloat(JOptionPane.showInputDialog("Precio (0000,00)"));
                    String color = JOptionPane.showInputDialog("color");
                    String marca = JOptionPane.showInputDialog("marca");
                    Date fecha = ParseFecha(JOptionPane.showInputDialog("Fecha (dd/mm/aaaa)"));
                    gestorCoches.altaCoche(matricula, precio, color, marca, fecha);

                    break;
                case 2://Baja coche

                    GestorCoches gestorCoches1 = new GestorCoches();
                    int idCoche = Integer.parseInt(JOptionPane.showInputDialog("Id. del coche a eliminar"
                            + ""));
                    gestorCoches1.bajaCoche(idCoche);

                    break;
                case 3://Modificar coche
                    GestorCoches gestorCoches3 = new GestorCoches();
                    int idCoche1 = Integer.parseInt(JOptionPane.showInputDialog("Id. del coche a modificar"));
                    float precio1 = Float.parseFloat(JOptionPane.showInputDialog("Precio"));
                    String color1 = JOptionPane.showInputDialog("color");
                    String marca1 = JOptionPane.showInputDialog("marca");
                    Date fecha1 = ParseFecha(JOptionPane.showInputDialog("fecha"));
                    gestorCoches3.modificarCoche(idCoche1, precio1, color1, marca1, fecha1);

                    break;
                case 4://Alta clientes

                    GestorClientes gestorClientes = new GestorClientes();
                    String dni = JOptionPane.showInputDialog("Dni");
                    String nombre = JOptionPane.showInputDialog("Nombre");
                    String apellidos = JOptionPane.showInputDialog("Apellidos");
                    String direccion = JOptionPane.showInputDialog("Dirección");
                    String tel = JOptionPane.showInputDialog("Teléfono");
                    gestorClientes.altaCliente(dni, nombre, apellidos, direccion, tel);

                    break;
                case 5://Baja cliente
                    GestorClientes gestorClientes1 = new GestorClientes();
                    int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Id. del cliente a elimiar"));
                    gestorClientes1.bajaCliente(idCliente);

                    break;
                case 6:// Modificar cliente
                    GestorClientes gestorClientes2 = new GestorClientes();
                    int idCliente1 = Integer.parseInt(JOptionPane.showInputDialog("Id. del cliente a modificar"));
                    String direccion1 = JOptionPane.showInputDialog("Dirección");
                    String tel1 = JOptionPane.showInputDialog("Teléfono");
                    gestorClientes2.modificarCliente(idCliente1, direccion1, tel1);

                    break;
                case 7://Alta reservas
                    GestorReservas gestorReservas = new GestorReservas();
                    int idCliente2 = Integer.parseInt(JOptionPane.showInputDialog("Id. del cliente"));
                    int idCoche2 = Integer.parseInt(JOptionPane.showInputDialog("Id. del coche"));
                    Date fechaIni = ParseFecha(JOptionPane.showInputDialog("fecha inicio"));
                    Date fechaDevolucion = ParseFecha(JOptionPane.showInputDialog("fecha de devolucion"));
                    float precioReserva = Float.parseFloat(JOptionPane.showInputDialog("Precio"));
                    float litros = Float.parseFloat(JOptionPane.showInputDialog("Litros"));
                    gestorReservas.altaReservas(idCliente2, idCoche2, fechaIni, fechaDevolucion, precioReserva, litros);

                    break;
                case 8://Consulta clientes
                    Consultas consultas = new Consultas();
                    String nombre1 = JOptionPane.showInputDialog("Nombre");
                    String apellidos1 = JOptionPane.showInputDialog("Apellidos");
                    consultas.consultaClientes(nombre1, apellidos1);

                    break;
                case 9://Consulta coches
                    Consultas consultas1 = new Consultas();
                    String matricula1 = JOptionPane.showInputDialog("Matrícula");
                    String marca2 = JOptionPane.showInputDialog("Marca");
                    consultas1.consultaCoches(matricula1, marca2);

                    break;
                case 10://Lista de reservas hoy

                    Consultas.listadoReservasHoy();

                    break;
                case 11://Clientes con reservas pendientes
                    Consultas.consultaReservasPendientes();

                    break;
                case 12://Coches reservados a un cliente determinado
                    int idCliente3 = Integer.parseInt(JOptionPane.showInputDialog("Id. del cliente"));
                    Consultas.consultaCochesCliente(idCliente3);

                    break;
                case 13://Veces que se ha reservado un coche
                    int idCoche3 = Integer.parseInt(JOptionPane.showInputDialog("Id. del coche"));
                    Consultas.consultaNumeroReservasCoche(idCoche3);

                    break;
                case 14://Generar fichero reservas.xml
                    GeneraXML.generarXml();

                    break;
                case 15:
                    System.exit(0);
                    break;
            }

        } while (opcion != 15);

    }

    /**
     * Permite convertir un String en fecha (Date).
     *
     * @param fecha Cadena de fecha dd/MM/yyyy
     * @return Objeto Date
     */
    public static Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }

    /**
     * Método que muestra un menú con todas las opciones disponibles
     */
    public static void mostrarMenu() {

        System.out.println("Introduzca una opción:");
        System.out.println("********COCHES(1.a)***********");
        System.out.println("1->Alta ");
        System.out.println("2->Baja ");
        System.out.println("3->Modificación");
        System.out.println("*****CLIENTESS(1.b)***********");
        System.out.println("4->Alta");
        System.out.println("5->Baja");
        System.out.println("6->Modificación");
        System.out.println("*****RESERVAS***********");
        System.out.println("7->Alta(2)");
        System.out.println("*****CONSULTAS(3)***********");
        System.out.println("8->Consulta clientes");
        System.out.println("9->Consulta coches");
        System.out.println("*****CONSULTAS(4)***********");
        System.out.println("10->Listado de reservas hoy(4.a)");
        System.out.println("11->Listado clientes con reservas pendientes(4.b)");
        System.out.println("12->Nº de coches reservados a un cliente(4.c)");
        System.out.println("13->Nº de veces que se ha reservado un coche determinado(4.d)");
        System.out.println("14->Generar fichero XML con las reservas que ahy en BBDD(5)");
        System.out.println("15->Salir");

    }
}
