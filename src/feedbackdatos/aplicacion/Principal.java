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
 * Método principal. Muestra un menú con 15 opciones.
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
                    insertarCoche();
                    break;

                case 2://Baja coche
                    eliminarCoche();
                    break;

                case 3://Modificar coche
                    updateCoche();
                    break;

                case 4://Alta clientes
                    insertarCliente();
                    break;

                case 5://Baja cliente
                    eliminarCliente();
                    break;

                case 6:// Modificar cliente
                    updateCliente();
                    break;
                case 7://Alta reservas
                    insertarReserva();
                    break;

                case 8://Consulta clientes
                    consultaClientes();
                    break;

                case 9://Consulta coches
                    consultaCoches();
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
    
    
    /**
     * Opción 1 del menú: valida los datos introducidos y si todo es correcto,
     * inserta un coche en BBDD
     */
    public static void insertarCoche() {

        GestorCoches gestorCoches = new GestorCoches();
        float fPrecio = 0;
        Date dFecha = null;
        String matricula = "";
        boolean validarMatricula = true;
        boolean validarFecha = true;

        //1.Comprobamos que se introducen los parámetros obligatoriso
        try {
            do {
                //MATRÍCULA
                matricula = JOptionPane.showInputDialog("Matrícula (obligatorio)");

                if (matricula.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir la matrícula",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    validarMatricula = false;

                } else {

                    validarMatricula = true;
                }

            } while (!validarMatricula);

            do {
                //FECHA
                String fecha = JOptionPane.showInputDialog("Fecha (obligatorio)");
                if (fecha.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir la fecha de matriculación",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    validarFecha = false;

                } else {
                    dFecha = ParseFecha(fecha);
                    validarFecha = true;
                }

            } while (!validarFecha);

            //2.Introducimos los parámetros que no son obligatorios        
            String precio = JOptionPane.showInputDialog("Precio (0000,00)");
            if (!precio.equals("")) {
                fPrecio = Float.parseFloat(precio);
            }

            String color = JOptionPane.showInputDialog("color");
            String marca = JOptionPane.showInputDialog("marca");

            //4. Y damos de alta
            gestorCoches.altaCoche(matricula, fPrecio, color, marca, dFecha);

        } catch (NumberFormatException n) {

            System.out.println("Debes introducir un precio válido");
        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    /**
     * Opción 2 del menú principal. 
     */
    public static void eliminarCoche() {

        GestorCoches gestorCoches1 = new GestorCoches();
        int idCoche = Integer.parseInt(JOptionPane.showInputDialog("Id. del coche a eliminar"
                + ""));
        gestorCoches1.bajaCoche(idCoche);

    }

    public static void updateCoche() {

        GestorCoches gestorCoches3 = new GestorCoches();
        int idCoche1 = Integer.parseInt(JOptionPane.showInputDialog("Id. del coche a modificar"));
        float precio1 = Float.parseFloat(JOptionPane.showInputDialog("Precio"));
        String color1 = JOptionPane.showInputDialog("color");
        String marca1 = JOptionPane.showInputDialog("marca");
        Date fecha1 = ParseFecha(JOptionPane.showInputDialog("fecha"));
        gestorCoches3.modificarCoche(idCoche1, precio1, color1, marca1, fecha1);
    }

    public static void insertarCliente() {

        GestorClientes gestorClientes = new GestorClientes();
        float fPrecio = 0;
        String nombre = null;
        String dni = "";
        boolean validarDni = true;
        boolean validarNombre = true;

        //1.Comprobamos que se introducen los parámetros obligatoriso
        try {
            do {
                //dni
                dni = JOptionPane.showInputDialog("DNI (obligatorio)");

                if (dni.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir el DNI",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    validarDni = false;

                } else {

                    validarDni = true;
                }

            } while (!validarDni);

            do {
                //Nombre
                nombre = JOptionPane.showInputDialog("Nombre (obligatorio)");
                if (nombre.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir el nombre",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    validarNombre = false;

                } else {

                    validarNombre = true;
                }

            } while (!validarNombre);

            //2.Introducimos los parámetros que no son obligatorios        
            String apellidos = JOptionPane.showInputDialog("Apellidos  ");
            String direccion = JOptionPane.showInputDialog("Dirección");
            String telefono = JOptionPane.showInputDialog("Teléfono");

            //4. Y damos de alta
            gestorClientes.altaCliente(dni, nombre, apellidos, direccion, telefono);

        } catch (NumberFormatException n) {

            System.out.println("Debes introducir un precio válido");
        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static void eliminarCliente() {

        GestorClientes gestorClientes1 = new GestorClientes();
        int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Id. del cliente a elimiar"));
        gestorClientes1.bajaCliente(idCliente);
    }

    public static void updateCliente() {

        GestorClientes gestorClientes2 = new GestorClientes();
        int idCliente1 = Integer.parseInt(JOptionPane.showInputDialog("Id. del cliente a modificar"));
        String direccion1 = JOptionPane.showInputDialog("Dirección");
        String tel1 = JOptionPane.showInputDialog("Teléfono");
        gestorClientes2.modificarCliente(idCliente1, direccion1, tel1);
    }

    public static void insertarReserva() {

        GestorReservas gestorReservas = new GestorReservas();
        float fPrecio = 0;
        int iIdCoche = 0;
        String idCoche = "";
        int iIdCliente = 0;
        String idCliente = "";
        Date dFechaInicio = null;
        Date dFechaDevolucion = null;
        float fPrecioReserva = 0;
        float fLitros = 0;
        boolean validarIdCliente = true;
        boolean validarIdCoche = true;
        boolean validarFechaInicio = true;
        boolean validarFechaDevolucion = true;

        //1.Comprobamos que se introducen los parámetros obligatoriso
        try {
            do {
                //ID DEL CLIENTE
                idCliente = JOptionPane.showInputDialog("Id del cliente (obligatorio)");

                if (idCliente.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir el id del cliente",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    validarIdCliente = false;

                } else {
                    iIdCliente = Integer.parseInt(idCliente);
                    validarIdCliente = true;
                }

            } while (!validarIdCliente);

            do {
                //ID DEL COCHE
                idCoche = JOptionPane.showInputDialog("Id del coche (obligatorio)");
                if (idCoche.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir el Id del coche",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    validarIdCoche = false;

                } else {
                    iIdCoche = Integer.parseInt(idCoche);
                    validarIdCoche = true;
                }

            } while (!validarIdCoche);

            do {
                //FECHA INICIO
                String fechaInicio = JOptionPane.showInputDialog("Fecha inicio(obligatorio)");
                if (fechaInicio.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir la fecha de inicio",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    validarFechaInicio = false;

                } else {
                    dFechaInicio = ParseFecha(fechaInicio);
                    validarFechaInicio = true;
                }

            } while (!validarFechaInicio);

            do {
                //FECHA DEVOLUCION
                String fechaDevolucion = JOptionPane.showInputDialog("Fecha de devolucion(obligatorio)");
                if (fechaDevolucion.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir la fecha de devolucion",
                            "Error", JOptionPane.INFORMATION_MESSAGE);
                    validarFechaDevolucion = false;

                } else {
                    dFechaDevolucion = ParseFecha(fechaDevolucion);
                    validarFechaDevolucion = true;
                }

            } while (!validarFechaDevolucion);

            //2.Introducimos los parámetros que no son obligatorios 
            String precioReserva = JOptionPane.showInputDialog("Precio");

            if (!precioReserva.equals("")) {
                fPrecioReserva = Float.parseFloat(precioReserva);
            }

            String litros = JOptionPane.showInputDialog("Litros");
            if (!litros.equals("")) {
                fLitros = Float.parseFloat(litros);
            }

            //4. Y damos de alta
            gestorReservas.altaReservas(iIdCliente, iIdCoche, dFechaInicio,
                    dFechaDevolucion, fPrecioReserva, fLitros);

        } catch (NumberFormatException n) {

            System.out.println("Debes introducir un número válido");
        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static void consultaClientes() {

        Consultas consultas = new Consultas();
        String nombre1 = JOptionPane.showInputDialog("Nombre");
        String apellidos1 = JOptionPane.showInputDialog("Apellidos");
        consultas.consultaClientes(nombre1, apellidos1);

    }

    public static void consultaCoches() {

        Consultas consultas1 = new Consultas();
        String matricula1 = JOptionPane.showInputDialog("Matrícula");
        String marca2 = JOptionPane.showInputDialog("Marca");
        consultas1.consultaCoches(matricula1, marca2);
    }

}
