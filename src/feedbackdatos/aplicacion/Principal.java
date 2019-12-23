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
        try {
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
                        getCochesCliente();
                        break;

                    case 13://Veces que se ha reservado un coche
                        getNumeroVecesCocheReservado();
                        break;

                    case 14://Generar fichero reservas.xml
                        GeneraXML.generarXml();

                        break;
                    case 15:
                        System.exit(0);
                        break;
                }

            } while (opcion != 15);

        } catch (NumberFormatException n) {

            System.out.println("Debe intorducir un nº válido");
            System.out.println("Error: " + n.toString());
        }

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
     * Opción 1 del menú: valida los datos introducidos por el usuario y si todo
     * es correcto, inserta un coche en BBDD
     *
     * @throws NumberFormatException
     */
    public static void insertarCoche() throws NumberFormatException {

        GestorCoches gestorCoches = new GestorCoches();
        float fPrecio = 0;
        Date dFecha = null;
        String matricula = "";
        boolean validarMatricula = true;
        boolean validarFecha = true;

        //1.Comprobamos que se introducen los parámetros obligatoriso
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

    }

    /**
     * Opción 2 del menú principal. Comprueba si el usuario ha introducido el id
     * del coche, y si es así lo elimina de la BBDD
     *
     * @throws NumberFormatException
     */
    public static void eliminarCoche() throws NumberFormatException {
        String idCoche = "";
        int iIdCoche = 0;
        boolean validarIdCoche = true;
        GestorCoches gestorCoches = new GestorCoches();

        do {
            //ID DEL COCHE
            idCoche = JOptionPane.showInputDialog("Id del coche a eliminar(obligatorio)");
            if (idCoche.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe introducir el Id del coche",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                validarIdCoche = false;

            } else {
                iIdCoche = Integer.parseInt(idCoche);
                validarIdCoche = true;
            }

        } while (!validarIdCoche);
        gestorCoches.bajaCoche(iIdCoche);

    }

    /**
     * Opción 3 del menú. Comprueba los datos que introduce el usuario y si son
     * correctos modifica el coche.
     *
     * @throws NumberFormatException
     */
    public static void updateCoche() throws NumberFormatException {
        float fPrecio = 0;
        String precio = "";
        String fecha = "";
        Date dFecha = null;
        boolean validarIdCoche = true;
        GestorCoches gestorCoches = new GestorCoches();
        String idCoche = "";
        int iIdCoche = 0;

        //Validamos el id
        do {
            //ID DEL COCHE
            idCoche = JOptionPane.showInputDialog("Id del coche a modificar(obligatorio)");

            if (idCoche.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe introducir el Id del coche",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                validarIdCoche = false;

            } else {
                iIdCoche = Integer.parseInt(idCoche);
                validarIdCoche = true;
            }

        } while (!validarIdCoche);
        //Formateamos el campo precio si viene informado, sino se deja a 0.
        precio = JOptionPane.showInputDialog("Precio");
        if (!precio.equals("")) {
            fPrecio = Float.parseFloat(JOptionPane.showInputDialog("Precio"));
        }

        String color = JOptionPane.showInputDialog("color");
        String marca = JOptionPane.showInputDialog("marca");
        fecha = JOptionPane.showInputDialog("fecha");
        if (!fecha.equals("")) {
            dFecha = ParseFecha(fecha);
        }

        gestorCoches.modificarCoche(iIdCoche, fPrecio, color, marca, dFecha);
    }

    /**
     * Opción 4 del menú: comprueba que los datos introducidos por el usuario
     * sean correctos y, si es así, inserta un nuevo cliente en BBDD
     *
     * @throws NumberFormatException
     */
    public static void insertarCliente() throws NumberFormatException {

        GestorClientes gestorClientes = new GestorClientes();
        float fPrecio = 0;
        String nombre = null;
        String dni = "";
        boolean validarDni = true;
        boolean validarNombre = true;

        //1.Comprobamos que se introducen los parámetros obligatoriso
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

    }

    /**
     * Opción 5 del menú: valida que el usuario haya introducido el id del
     * cliente correctamente y lo elimina de la BBDD.
     *
     * @throws NumberFormatException
     */
    public static void eliminarCliente() throws NumberFormatException {

        GestorClientes gestorClientes = new GestorClientes();
        String idCliente = "";
        int iIdCliente = 0;
        boolean validatIdCliente = true;

        do {
            //ID DEL CLIENTE
            idCliente = JOptionPane.showInputDialog("Id del cliente a eliminar(obligatorio)");
            if (idCliente.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe introducir el Id del "
                        + "cliente a eliminar",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                validatIdCliente = false;

            } else {
                iIdCliente = Integer.parseInt(idCliente);
                validatIdCliente = true;
            }

        } while (!validatIdCliente);
        gestorClientes.bajaCliente(iIdCliente);
    }

    /**
     * Opción 6 del menú: comprueba que el usuario haya introducido el id del
     * cliente y lo modifica en la BBDD con los datos del usuario
     *
     * @throws NumberFormatException
     */
    public static void updateCliente() throws NumberFormatException {

        GestorClientes gestorClientes = new GestorClientes();

        String idCliente = "";
        int iIdCliente = 0;
        boolean validatIdCliente = true;

        do {
            //ID DEL CLIENTE
            idCliente = JOptionPane.showInputDialog("Id del cliente a modificar(obligatorio)");
            if (idCliente.equals("")) {
                JOptionPane.showMessageDialog(null, "Debe introducir el Id del "
                        + "cliente a nodificar",
                        "Error", JOptionPane.INFORMATION_MESSAGE);
                validatIdCliente = false;

            } else {
                iIdCliente = Integer.parseInt(idCliente);
                validatIdCliente = true;
            }

        } while (!validatIdCliente);
        String direccion1 = JOptionPane.showInputDialog("Dirección");
        String tel1 = JOptionPane.showInputDialog("Teléfono");
        gestorClientes.modificarCliente(iIdCliente, direccion1, tel1);
    }

    /**
     * Opción 7 del menú: comprueba que el usuario haya introducido los datos
     * correctos e inserta una reserva en BBDD.
     *
     * @throws NumberFormatException
     */
    public static void insertarReserva() throws NumberFormatException {

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

    }

    /**
     * Opción 8 del menú: método que permite consultar clientes por nombre o
     * apellidos.
     */
    public static void consultaClientes() {

        Consultas consultas = new Consultas();
        String nombre1 = JOptionPane.showInputDialog("Nombre");
        String apellidos1 = JOptionPane.showInputDialog("Apellidos");
        consultas.consultaClientes(nombre1, apellidos1);

    }

    /**
     * Opción 9 del menú: método que permite consultar coches por matrícula o
     * marca.
     */
    public static void consultaCoches() {

        Consultas consultas1 = new Consultas();
        String matricula1 = JOptionPane.showInputDialog("Matrícula");
        String marca2 = JOptionPane.showInputDialog("Marca");
        consultas1.consultaCoches(matricula1, marca2);
    }

    /**
     * Opción 12 del menú: muestra el número de coches reservados a un cliente
     * determinado.
     *
     * @throws NumberFormatException
     */
    public static void getCochesCliente() throws NumberFormatException {
        boolean validarIdCliente = true;
        String idCliente = "";
        int iIdCliente = 0;
        //int id//Cliente3 = Integer.parseInt(JOptionPane.showInputDialog("Id. del cliente"));

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

        Consultas.consultaCochesCliente(iIdCliente);

    }

    /**
     * Opción 13 del menú: muestra el número de veces que se ha reservado un
     * coche determinado
     *
     * @throws NumberFormatException
     */
    public static void getNumeroVecesCocheReservado() throws NumberFormatException {
        boolean validarIdCoche = true;
        String idCoche = "";
        int iIdCoche = 0;

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

        Consultas.consultaNumeroReservasCoche(iIdCoche);

    }
}
