/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedbackdatos.aplicacion;

import feedbackdatos.Reservas;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose
 */

@XmlRootElement()
public class ListaReservas {
    
    private ArrayList<Reservas> listaReserva;
  
    public ListaReservas(ArrayList<Reservas> listaReserva ){
        
        super();
        this.listaReserva=listaReserva;
         
        
        
        
    }
    
    public ListaReservas(){}
    
    
    @XmlElementWrapper(name="listaReserva")
    @XmlElement(name="Reserva")
    public ArrayList<Reservas> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(ArrayList<Reservas> listaReserva) {
        this.listaReserva = listaReserva;
    }
    
    
   
        
        
        
     
    
}
