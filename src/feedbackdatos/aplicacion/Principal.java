/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedbackdatos.aplicacion;

import java.util.Date;

/**
 *
 * @author Jose
 */
public class Principal {
    
    
    public static void main(String[]  args){
        
      
        
       //GestorCoches gestorCoches=new GestorCoches();
       // gestorCoches.altaCoche("21115F", 8000, "Verde", "Renaulr", new Date("2016/09/10"));
        
        
        GestorClientes gClientes= new GestorClientes();
        gClientes.altaCliente("45620111A", "Pepe", "Gómez", "Calle del Zapato", "123456789");
        
        
    }
    
}
