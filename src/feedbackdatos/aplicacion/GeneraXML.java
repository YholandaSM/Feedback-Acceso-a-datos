package feedbackdatos.aplicacion;

import com.sun.xml.internal.ws.util.Pool;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Jose
 */
public class GeneraXML {

    public static void generarXml() {

        ListaReservas lista= new ListaReservas();
        JAXBContext context;
         Marshaller m;
        try {
            //Creamos el contexto indicando la clase raíz
            context = JAXBContext.newInstance(ListaReservas.class);
            //Creamos el Marshaller, convierte el javaBean en una cadena XML
             m = context.createMarshaller();
            
            //Formateamos el XML
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE );
            
            //Visualizamos el XML
            m.marshal(lista, System.out);
            
            //Escribimos en el archivo
            m.marshal(lista, new File("./listaReservas.xml"));
            
        } catch (JAXBException ex) {
            Logger.getLogger(GeneraXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
