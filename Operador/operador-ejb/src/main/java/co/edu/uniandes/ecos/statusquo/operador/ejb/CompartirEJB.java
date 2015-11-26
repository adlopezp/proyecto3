package co.edu.uniandes.ecos.statusquo.operador.ejb;

import co.edu.uniandes.ecos.statusquo.operador.dao.ArchivoDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.CarpetaDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.EstadoArchivoDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.FormatoArchivoDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.MensajeDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.PermisoDAO;
import co.edu.uniandes.ecos.statusquo.operador.dao.TipoMensajeDAO;
import co.edu.uniandes.ecos.statusquo.operador.entity.Archivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.CarpetaPersonal;
import co.edu.uniandes.ecos.statusquo.operador.entity.Mensaje;
import co.edu.uniandes.ecos.statusquo.operador.entity.Permiso;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.ws.dto.UsuarioDTO;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Alvaro
 */
@Stateless
@LocalBean
public class CompartirEJB {

    @EJB
    private CarpetaDAO carpetaDAO;

    @EJB
    private ArchivoDAO archivoDAO;

    @EJB
    private FormatoArchivoDAO formatoDAO;

    @EJB
    private PermisoDAO permisoDAO;

    @EJB
    private MensajeDAO mensajeDAO;

    @EJB
    private TipoMensajeDAO tipoMensajeDAO;

    @EJB
    private EstadoArchivoDAO estadoArchivoDAO;

    @EJB
    private UsuarioEJB usuarioEJB;

    public void compartirArchivo(final UsuarioDTO usuario, final Archivo archivo) {
        registrarPermisoCompartido(archivo, usuario.getDocumento());
        if (usuario.isUsuarioLocal()) {

        }
    }

    public Archivo registrarArchivoRemoto(final CarpetaPersonal carpetaPersonal, final Archivo archivoOriginal, final String identificacionPropietario, final String idArchivoRemoto) {

        Carpeta carpeta = carpetaDAO.getCarpetaPrincipal(carpetaPersonal.getId());

        Archivo archivo = new Archivo();
        archivo.setCarpetaPadreId(carpeta);
        archivo.setCarpetaPersonal(carpetaPersonal);
        archivo.setEstadoId(estadoArchivoDAO.getEstadoActivo());
        archivo.setFecha(new Date());
        archivo.setFirmado(Boolean.FALSE);
        archivo.setFormato(archivoOriginal.getFormato());
        archivo.setIdentificacionPropietario(identificacionPropietario);
        archivo.setNombre(archivoOriginal.getNombre());
        archivo.setSizeArchivo(archivoOriginal.getSizeArchivo());
        archivo.setTipo(archivoOriginal.getTipo());
        archivo.setUrl(idArchivoRemoto);
        archivoDAO.insertar(archivo);
        carpeta.addArchivo(archivo);
        return archivo;
    }

    public void registrarNotificacionCompartido(final String documentoPropietario, final String documentoRemitente, final Archivo archivoCompartido) {

        Usuario usuario = usuarioEJB.obtenerUsuario(documentoPropietario);
        Archivo archivoNuevo = null;

        Mensaje notificacion = new Mensaje();
        notificacion.setTipo(tipoMensajeDAO.getTipoNotificacionCompartido());
        notificacion.setCarpetaPersonal(usuario.getCarpetaPersonal());
        notificacion.setIdentificacionRemitente(documentoRemitente);
        notificacion.setTexto("El usuario " + documentoRemitente + " le compartió el archivo " + archivoCompartido.getNombre());
        notificacion.setArchivo(archivoNuevo);

    }

    public void registrarPermisoCompartido(final Archivo archivo, final String identificacion) {
        Permiso permiso = new Permiso();
        permiso.setArchivo(archivo);
        permiso.setFechaVencimiento(null);
        permiso.setIdentificacionUsuario(identificacion);
        permisoDAO.insertar(permiso);
    }

}
