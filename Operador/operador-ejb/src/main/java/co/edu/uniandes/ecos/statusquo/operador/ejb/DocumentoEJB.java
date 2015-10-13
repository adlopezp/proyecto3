package co.edu.uniandes.ecos.statusquo.operador.ejb;

import co.edu.uniandes.ecos.statusquo.operador.dao.ArchivoDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.CarpetaDAO;
import co.edu.uniandes.ecos.statusquo.operador.entity.Archivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;
import org.eclipse.persistence.tools.file.FileUtil;

/**
 *
 * @author Alvaro
 */
@Stateless
@LocalBean
public class DocumentoEJB {
    
    @EJB
    private CarpetaDAO carpetaDAO;
    
    @EJB
    private ArchivoDAO archivoDAO;
    
    @EJB
    private PropertiesEJB propertiesEJB;

    public void businessMethod() {
        System.out.println("llamado EJB");
    }

    public List<Carpeta> traerCarpetasDeUsuario(final Usuario usuario) {
        List<Carpeta> carpetas = carpetaDAO.consultarPorUsuario(usuario);
        return carpetas;
    }

    /**
     * Crea un archivo en el sistema (Uploaded)
     * @param archivo 
     */
    public void crearArchivo(Archivo archivo) {
        try {
            File file = new File(
                    propertiesEJB.getProperties().getProperty("almacenamiento.ruta")
                            + "/" +archivo.getCarpetaPersonal().getUsuario().getDocumento()
                            + "/" + archivo.getNombre());
            FileUtils.writeByteArrayToFile(file, archivo.getContenido());
            
            //archivoDAO.insertar(archivo); FIXME
            
        } catch (IOException ex) {
            Logger.getLogger(DocumentoEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
