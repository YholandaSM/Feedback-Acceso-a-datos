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

@XmlRootElement(name="alquileres")
public class ListaReservas {
    
    private ArrayList<Reservas> listaReserva = new ArrayList();
  
    public ListaReservas(ArrayList<Reservas> listaReserva ){
       
        this.listaReserva=listaReserva;
         
        
        
        
    }
    
    public ListaReservas(){}
    
    
    @XmlElementWrapper(name="listaReservas")
    @XmlElement(name="reserva")
    public ArrayList<Reservas> getListaReserva() {
        return listaReserva;
    }

    public void setListaReserva(ArrayList<Reservas> listaReserva) {
        this.listaReserva = listaReserva;
    }
    
    
   
        
        
        
     
    
}
