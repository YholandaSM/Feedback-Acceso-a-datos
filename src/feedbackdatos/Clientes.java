package feedbackdatos;
// Generated 11-dic-2019 17:06:40 by Hibernate Tools 4.3.1



/**
 * Clientes generated by hbm2java
 */
public class Clientes  implements java.io.Serializable {


     private byte idCliente;
     private String dni;
     private String nombre;
     private String apellidos;
     private String direccion;
     private String telefono;

    public Clientes() {
    }

	
    public Clientes(byte idCliente, String dni, String nombre) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
    }
    public Clientes(byte idCliente, String dni, String nombre, String apellidos, String direccion, String telefono) {
       this.idCliente = idCliente;
       this.dni = dni;
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.direccion = direccion;
       this.telefono = telefono;
    }
   
    public byte getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(byte idCliente) {
        this.idCliente = idCliente;
    }
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }




}


