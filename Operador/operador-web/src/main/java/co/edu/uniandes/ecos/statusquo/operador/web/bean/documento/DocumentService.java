package co.edu.uniandes.ecos.statusquo.operador.web.bean.documento;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import co.edu.uniandes.ecos.statusquo.operador.web.data.documento.Document;
 
@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService {
	
	private List<Document> documentList;
	
	public DocumentService(){
		documentList = createDocumentsList();
	}
	
    public List<Document> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<Document> documentList) {
		this.documentList = documentList;
	}



	public TreeNode createDocuments() {
    	TreeNode root = new DefaultTreeNode(null, null);
    	fillTreeNode(documentList, root);
        return root;
    }

	private void fillTreeNode(List<Document> docList, TreeNode root) {
		
		for (Document doc: docList) {
			TreeNode node = null;
			if (doc.getType() == Document.Type.FOLDER) {
				node = new DefaultTreeNode(doc,root);
				fillTreeNode(doc.getListDocuments(), node);
			}
		}
		
	}

	private List<Document> createDocumentsList() {
		List<Document> documents = new ArrayList<>(); //Root
		
		Document unreadedDocuments = 
				new Document(1, "Documentos sin leer", Document.Type.FOLDER);
		
		unreadedDocuments.setListDocuments(createDocuments(30, unreadedDocuments));
		
		
		documents.add(unreadedDocuments);
		
		
		return documents;
	}

	private List<Document> createDocuments(int num, Document folder) {
		List<Document> documents = new ArrayList<>();
		for (int i = 0; i < num; i++ ) {
			Document document = new Document();
			document.setId(i);
			document.setName("File" + i + ".pdf");
			document.setParent(folder);
			documents.add(document);
		}
		return documents;
	}
}