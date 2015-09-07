package co.edu.uniandes.ecos.statusquo.operador.web.data.documento;

import java.util.List;



/**
 * Representa un documento o directorio en el sistema
 * @author Zamir
 * @version 1.0
 */
@Deprecated
public class Document {
	
	public enum Type {
		FOLDER, FILE
	}

	/**
	 * Identificador del archivo o direcotorio
	 */
	private long id;
	
	/**
	 * Nombre del archivo
	 */
	private String name;
	
	/**
	 * Tamaño en bytes del archivo
	 */
	private long size;
	
	/**
	 * Tipo de archivo
	 */
	private Type type;
	
	/**
	 * Indica si el documento está firmado digitalmente
	 */
	private boolean signed;
	
	/**
	 * Directorio al cual pertenece
	 */
	private Document parent;
	
	/**
	 * Si es un directorio, esta lista contiene los archivos y directorios
	 * a que haya lugar
	 */
	private List<Document> listDocuments;
	
	/**
	 * Metadatos del documento
	 */
	private DocumentMetaData metadata;
	
	/**
	 * Información sobre la manera en que se comparte el documento
	 */
	private List<ShareInfo> shareInfo;

	public Document(){
		type = Type.FILE;
	}
	
	public Document(long id, String name, long size, Type type,
			boolean signed, List<Document> listDocuments, DocumentMetaData metadata,
			List<ShareInfo> shareInfo) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.type = type;
		this.signed = signed;
		this.listDocuments = listDocuments;
		this.metadata = metadata;
		this.shareInfo = shareInfo;
	}



	public Document(String name, String string2, String type) {
		this.name = name;
	}

	public Document(long id, String name, Type type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isSigned() {
		return signed;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}

	public Document getParent() {
		return parent;
	}

	public void setParent(Document parent) {
		this.parent = parent;
	}

	public List<Document> getListDocuments() {
		return listDocuments;
	}

	public void setListDocuments(List<Document> listDocuments) {
		this.listDocuments = listDocuments;
	}

	public DocumentMetaData getMetadata() {
		return metadata;
	}

	public void setMetadata(DocumentMetaData metadata) {
		this.metadata = metadata;
	}

	public List<ShareInfo> getShareInfo() {
		return shareInfo;
	}

	public void setShareInfo(List<ShareInfo> shareInfo) {
		this.shareInfo = shareInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", name=" + name + "]";
	}
	

}