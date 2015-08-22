package co.edu.uniandes.ecos.statusquo.operador.web.bean.documento;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import co.edu.uniandes.ecos.statusquo.operador.web.data.documento.Document;
 
@ManagedBean(name="documentView")
@SessionScoped
public class DocumentView {
     
    private TreeNode root;
    
    private List<Document> currentDocuments;
    
    private Document selectedDocument;
     
    @ManagedProperty("#{documentService}")
    private DocumentService service;
     
    @PostConstruct
    public void init() {
        root = service.createDocuments();
    }
 
    public void setService(DocumentService service) {
        this.service = service;
    }
 
    public TreeNode getRoot() {
        return root;
    }
    
	public List<Document> getCurrentDocuments() {
		return currentDocuments;
	}

	public void setCurrentDocuments(List<Document> currentDocuments) {
		this.currentDocuments = currentDocuments;
	}
	
	public Document getSelectedDocument() {
		return selectedDocument;
	}

	public void setSelectedDocument(Document selectedDocument) {
		this.selectedDocument = selectedDocument;
	}

	public void onNodeSelect(NodeSelectEvent event) {
		currentDocuments = ((Document) event.getTreeNode().getData()).getListDocuments();
    }
	
	public void onRowSelect(SelectEvent event) {
		selectedDocument = (Document) event.getObject();
	}

}