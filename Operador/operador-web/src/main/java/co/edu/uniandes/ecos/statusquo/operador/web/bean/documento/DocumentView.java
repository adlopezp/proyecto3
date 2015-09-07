package co.edu.uniandes.ecos.statusquo.operador.web.bean.documento;

import co.edu.uniandes.ecos.statusquo.operador.ejb.DocumentoEJB;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.web.bean.UtilBean;
import co.edu.uniandes.ecos.statusquo.operador.web.util.TreeNodeHelper;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import java.io.Serializable;
import javax.ejb.EJB;

@ManagedBean(name = "documentView")
@SessionScoped
public class DocumentView implements Serializable {

    private TreeNode root;

    private List<Carpeta> carpetasUsuario;

    @EJB
    private DocumentoEJB documentoEJB;

    @PostConstruct
    public void init() {
        Usuario usuario = UtilBean.getUsuarioActual();
        System.out.println("login:" + usuario.getNombre());
        carpetasUsuario = documentoEJB.traerCarpetasDeUsuario(usuario);
        root = TreeNodeHelper.toTreeNode(carpetasUsuario);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void onNodeSelect(NodeSelectEvent event) {
        System.out.println("Node select");
    }

    public void onRowSelect(SelectEvent event) {
        System.out.println("Row select");
    }

}
