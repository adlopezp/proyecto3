package co.edu.uniandes.ecos.statusquo.operador.web.data.documento;

import co.edu.uniandes.ecos.statusquo.operador.web.data.documento.Document;
import java.util.Map;



/**
 * Meta datos de un documento
 * 
 * @author Zamir
 * @version 1.0
 */
@Deprecated
public class DocumentMetaData {
	
	/**
	 * Id metadata
	 */
	private long id;

	/**
	 * Version del documento
	 */
	private int version;
	
	/**
	 * Información adicional
	 */
	private Map<String,String> customData;
	
	private Document document;

	public DocumentMetaData(){
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Map<String, String> getCustomData() {
		return customData;
	}

	public void setCustomData(Map<String, String> customData) {
		this.customData = customData;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}