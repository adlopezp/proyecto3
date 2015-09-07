package co.edu.uniandes.ecos.statusquo.operador.web.data.documento;


import java.util.Date;



/**
 * Informacion acerca de la manera de compartir un documento
 * 
 * @author Zamir
 * @version 1.0
 */
@Deprecated
public class ShareInfo {

	/**
	 * Id de info de compartir
	 */
	private long id;
	
	/**
	 * Fecha desde la cual se inicia a compartir el documento
	 */
	private Date starDate;
	
	/**
	 * Fecha desde la cual se termina de compartir el documento
	 */
	private Date endDate;
	
	/**
	 * Identificacion de la persona o entidad que puede acceder a su informacion
	 */
	private long authUserId;
	
	/**
	 * Documento
	 */
	private Document document;
	
	public ShareInfo(){
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStarDate() {
		return starDate;
	}

	public void setStarDate(Date starDate) {
		this.starDate = starDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(long authUserId) {
		this.authUserId = authUserId;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

}