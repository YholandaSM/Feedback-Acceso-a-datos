package feedbackdatos.aplicacion;

import feedbackdatos.Reservas;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Clase  que genera un fichero XML
 * @author Yolanda
 */
public class GeneraXML {
    
    
    /**
     * Método que genera un fichero XML con los datos de la tabla Reservas
     */
    public static void generarXml() {
         
        
         ArrayList<Reservas> lista= new ArrayList<Reservas>();
         
         lista = Consultas.getReservas();
        ListaReservas reservas = new ListaReservas(lista );
        JAXBContext context;
        Marshaller m;
        try {
            //Creamos el contexto indicando la clase raíz
            context = JAXBContext.newInstance(ListaReservas.class);
            //Creamos el Marshaller, convierte el javaBean en una cadena XML
            m = context.createMarshaller();

            //Formateamos el XML
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Visualizamos el XML
            m.marshal(reservas, System.out);

            //Escribimos en el archivo
            m.marshal(reservas, new File("listaReservas.xml"));

        } catch (JAXBException ex) {
            ex.printStackTrace();
            
           
        }

    }

}
