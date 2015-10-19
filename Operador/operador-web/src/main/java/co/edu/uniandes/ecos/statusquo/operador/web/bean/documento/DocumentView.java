package co.edu.uniandes.ecos.statusquo.operador.web.bean.documento;

import co.edu.uniandes.ecos.statusquo.operador.ejb.DocumentoEJB;
import co.edu.uniandes.ecos.statusquo.operador.entity.Archivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.web.bean.UtilBean;
import co.edu.uniandes.ecos.statusquo.operador.web.util.TreeNodeHelper;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "documentView")
@SessionScoped
public class DocumentView implements Serializable {

    private TreeNode root;

    private List<Carpeta> carpetasUsuario;

    private List<Archivo> archivosUsuario;

    private Carpeta carpetaSeleccionada;

    private Archivo selectedDocument;

    private Archivo nuevoDocumento;

    @EJB
    private DocumentoEJB documentoEJB;

    @PostConstruct
    public void init() {
        Usuario usuario = UtilBean.getUsuarioActual();
        System.out.println("login:" + usuario.getNombre1() + " " + usuario.getNombre2() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
        carpetasUsuario = documentoEJB.traerCarpetasDeUsuario(usuario);
        root = TreeNodeHelper.toTreeNode(carpetasUsuario);
    }

    public TreeNode getRoot() {
        return root;
    }

    public Archivo getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(Archivo selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public List<Archivo> getArchivosUsuario() {
        return archivosUsuario;
    }

    public void setArchivosUsuario(List<Archivo> archivosUsuario) {
        this.archivosUsuario = archivosUsuario;
    }

    public Archivo getNuevoDocumento() {
        return nuevoDocumento;
    }

    public void setNuevoDocumento(Archivo nuevoDocumento) {
        this.nuevoDocumento = nuevoDocumento;
    }

    /* Métodos basados en eventos */
    /**
     * Método invocado cuando se selecciona una carpeta
     *
     * @param event
     */
    public void onNodeSelect(NodeSelectEvent event) {
        carpetaSeleccionada = (Carpeta) event.getTreeNode().getData();
        archivosUsuario = carpetaSeleccionada.getArchivos();
    }

    /**
     * Método invocado cuando se selecciona un archivo
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        System.out.println("Row select");
    }

    /**
     * Método invocado para subir un archivo
     *
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage();
        try {
            Archivo archivo = new Archivo();
            archivo.setCarpetaPadreId(null); //Poner la carpeta seleccionada
            archivo.setCarpetaPersonal(null);//Poner la carpeta del usuario actual
            archivo.setEstadoId(null);//Poner el estado
            archivo.setEtiquetas(null); //Poner etiquetas
            archivo.setFirmado(false); //Originalemente no está firmado
            archivo.setFormato(null); //Poner el formato
            archivo.setNombre(event.getFile().getFileName());
            archivo.setSizeArchivo(event.getFile().getSize());
            archivo.setContenido(event.getFile().getContents());

            archivo.setCarpetaPadreId(carpetaSeleccionada);
            archivo.setCarpetaPersonal(carpetaSeleccionada.getCarpetaPersonal());

            documentoEJB.crearArchivo(archivo);

            //Mensaje en JSF
            message.setSummary("Succesful");
            message.setDetail(event.getFile().getFileName() + " is uploaded.");
            message.setSeverity(FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            message.setSummary("Succesful");
            message.setDetail(event.getFile().getFileName() + " couldn't be uploaded");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
