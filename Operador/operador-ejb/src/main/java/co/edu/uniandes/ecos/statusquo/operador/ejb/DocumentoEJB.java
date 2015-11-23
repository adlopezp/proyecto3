package co.edu.uniandes.ecos.statusquo.operador.ejb;

import co.edu.uniandes.ecos.statusquo.operador.dao.ArchivoDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.CarpetaDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.FormatoArchivoDAO;
import co.edu.uniandes.ecos.statusquo.operador.entity.Archivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.EstadoArchivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.FormatoArchivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private FormatoArchivoDAO formatoDAO;

    public List<Carpeta> traerCarpetasDeUsuario(final Usuario usuario) {
        List<Carpeta> carpetas = carpetaDAO.consultarPorUsuario(usuario);
        return carpetas;
    }

    /**
     * Crea un archivo en el sistema (Uploaded)
     *
     * @param archivo
     * @throws IOException
     */
    public void crearArchivo(Archivo archivo) throws IOException {
        File file = new File(archivo.getUrl());
        FileUtils.writeByteArrayToFile(file, archivo.getContenido());
        archivoDAO.actualizar(archivo);
    }

    public Carpeta traerCarpeta(long id) {
        return carpetaDAO.buscar(id);
    }

    /**
     * Retorna los archivos y de una carpeta seleccionada
     *
     * @param carpeta
     * @param usuario
     * @return
     */
    public List<Archivo> traerArchivosCarpeta(Carpeta carpeta, Usuario usuario) {
        long tipo = carpeta.getTipo().getId();
        List<Archivo> archivos;
        if (tipo == 1L) { //Carpeta tipo inbox
            archivos = archivoDAO.traerArchivosCarpeta(carpeta);
        } else if (tipo == 3L) {
            archivos = traerArchivosPapelera(traerCarpetasDeUsuario(usuario));
        } else {
            archivos = new ArrayList<>();
        }

        return archivos;
    }

    /**
     * Cambia el estado del archivo así: 1. Si el archivo es activo (1) lo
     * cambia a "papelera" (2) 2. Si el archivo es "papelera" (2) lo cambia a
     * "borrado" (3)
     *
     * @param archivo
     */
    public void borrarArchivo(Archivo archivo) {
        long estadoActual = archivo.getEstado().getId();
        EstadoArchivo nuevoEstado = new EstadoArchivo(estadoActual + 1);
        archivo.setEstadoId(nuevoEstado);
        archivoDAO.actualizar(archivo);
    }

    /**
     * Mueve el archivo a la papelera del usuario
     *
     * @param archivo
     */
    public void moverAPapelera(Archivo archivo) {
        EstadoArchivo estado = new EstadoArchivo(2L);
        archivo.setEstadoId(estado);
        archivoDAO.actualizar(archivo);
    }

    /**
     * Restaura un archivo a su carpeta original
     *
     * @param archivo
     */
    public void restaurarArchivo(Archivo archivo) {
        EstadoArchivo estado = new EstadoArchivo(1L);
        archivo.setEstadoId(estado);
        archivoDAO.actualizar(archivo);
    }

    /**
     * Retorna la lista de archivos eliminados de un usuario
     *
     * @param usuario
     * @return
     */
    private List<Archivo> traerArchivosPapelera(List<Carpeta> carpetas) {
        List<Archivo> archivos = new ArrayList<>();

        for (Carpeta carpeta : carpetas) {
            addFiles(carpeta, archivos, 2L);
        }

        return archivos;
    }

    /**
     * Adiciona en la lista de archivos
     *
     * @param carpeta Carpeta de dónde se exploran los archivos
     * @param archivos Lista de archivos que debe llenarse
     * @param state Código de estado de archivo
     */
    public void addFiles(Carpeta carpeta, List<Archivo> archivos, long state) {
        for (Archivo archivo : carpeta.getArchivos()) {
            if (archivo.getEstado().getId() == state ) {
                    archivos.add(archivo);
            }
            if (carpeta.getCarpetasHijas() != null && !carpeta.getCarpetasHijas().isEmpty()) {
                archivos.addAll(traerArchivosPapelera(carpeta.getCarpetasHijas()));
            }
        }
    }

    public FormatoArchivo obtenerFormato(final String extencion) {
        return formatoDAO.buscarPorExtencion(extencion);
    }

    public void crearFormato(final FormatoArchivo formato) {
        formatoDAO.insertar(formato);
    }

}
