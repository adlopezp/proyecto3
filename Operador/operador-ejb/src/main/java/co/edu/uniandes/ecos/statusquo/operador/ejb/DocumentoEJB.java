package co.edu.uniandes.ecos.statusquo.operador.ejb;

import co.edu.uniandes.ecos.statusquo.operador.dao.ArchivoDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.CarpetaDAO;
import co.edu.uniandes.ecos.statusquo.operador.entity.Archivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;

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
     * @throws IOException
     */
    public void crearArchivo(Archivo archivo) throws IOException {
        
        File file = new File(archivo.getUrl());
        FileUtils.writeByteArrayToFile(file, archivo.getContenido());
        archivoDAO.actualizar(archivo);
        System.out.println(archivo);
    }

    public Carpeta traerCarpeta(long id) {
        return carpetaDAO.buscar(id);
    }

    
}
