package co.edu.uniandes.ecos.statusquo.operador.web.bean.documento;

import co.edu.uniandes.ecos.statusquo.operador.ejb.DocumentoEJB;
import co.edu.uniandes.ecos.statusquo.operador.ejb.PropertiesEJB;
import co.edu.uniandes.ecos.statusquo.operador.entity.Archivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.EstadoArchivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.web.bean.UtilBean;
import co.edu.uniandes.ecos.statusquo.operador.web.util.TreeNodeHelper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "documentView")
@ViewScoped
public class DocumentView implements Serializable {

    private TreeNode root;

    private List<Carpeta> carpetasUsuario;

    private List<Archivo> archivosUsuario;

    private Carpeta carpetaSeleccionada;

    private Archivo selectedDocument;
    
    private StreamedContent contenidoDescarga;

    private Archivo nuevoDocumento;

    @EJB
    private DocumentoEJB documentoEJB;
    
    @EJB
    private PropertiesEJB propertiesEJB;

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

    public StreamedContent getContenidoDescarga() {
        return contenidoDescarga;
    }

    public void setContenidoDescarga(StreamedContent contenidoDescarga) {
        this.contenidoDescarga = contenidoDescarga;
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
        System.out.println("archivo seleccionado");
        try {
            InputStream is = new FileInputStream(selectedDocument.getUrl());
            contenidoDescarga = 
                    new DefaultStreamedContent(is,null,selectedDocument.getNombre());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DocumentView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Método invocado para subir un archivo
     *
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage();
        try {
            Usuario usuario = UtilBean.getUsuarioActual();
            EstadoArchivo estado = new EstadoArchivo(1L); //Activo
            
            Archivo archivo = new Archivo();
            archivo.setNombre(event.getFile().getFileName());
            archivo.setSizeArchivo(event.getFile().getSize());
            String url = propertiesEJB.getProperty("almacenamiento.ruta")
                    + "/" + usuario.getDocumento() 
                    + "/" +archivo.getNombre();
            archivo.setUrl(url);
            archivo.setEstadoId(estado);
            archivo.setCarpetaPadreId(carpetaSeleccionada);
            archivo.setCarpetaPersonal(usuario.getCarpetaPersonal());
            archivo.setContenido(event.getFile().getContents());
            
            archivo.setFirmado(false);//Por defecto no sube firmado
            
            documentoEJB.crearArchivo(archivo);
            
            carpetaSeleccionada.getArchivos().add(archivo);

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
    
    public void borrarArchivo(){
        System.out.println("Borrando archivo: " + selectedDocument.getNombre());
        archivosUsuario.remove(selectedDocument);
    }

}
