/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev
 */
@Entity
@Table(name = "AUDITORIA_WS")
@TableGenerator(name = "seqauditoriaws", initialValue = 1, allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditoriaWs.findAll", query = "SELECT a FROM AuditoriaWs a"),
    @NamedQuery(name = "AuditoriaWs.findById", query = "SELECT a FROM AuditoriaWs a WHERE a.id = :id"),
    @NamedQuery(name = "AuditoriaWs.findByFechaIn", query = "SELECT a FROM AuditoriaWs a WHERE a.fechaIn = :fechaIn"),
    @NamedQuery(name = "AuditoriaWs.findByHostActor", query = "SELECT a FROM AuditoriaWs a WHERE a.hostActor = :hostActor"),
    @NamedQuery(name = "AuditoriaWs.findByFechaOut", query = "SELECT a FROM AuditoriaWs a WHERE a.fechaOut = :fechaOut"),
    @NamedQuery(name = "AuditoriaWs.findByIdcliente", query = "SELECT a FROM AuditoriaWs a WHERE a.idcliente = :idcliente")})
public class AuditoriaWs implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqauditoriaws")
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "FECHA_IN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIn;
    @Lob
    @Column(name = "MENSAJE_IN")
    private String mensajeIn;
    @Column(name = "HOST_ACTOR")
    private String hostActor;
    @Column(name = "FECHA_OUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOut;
    @Lob
    @Column(name = "MENSAJE_OUT")
    private String mensajeOut;
    @Column(name = "IDCLIENTE")
    private String idcliente;
    @Column(name = "IN_OUT")
    private boolean inOut;

    public AuditoriaWs() {
    }

    public AuditoriaWs(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public String getMensajeIn() {
        return mensajeIn;
    }

    public void setMensajeIn(String mensajeIn) {
        this.mensajeIn = mensajeIn;
    }

    public String getHostActor() {
        return hostActor;
    }

    public void setHostActor(String hostActor) {
        this.hostActor = hostActor;
    }

    public Date getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(Date fechaOut) {
        this.fechaOut = fechaOut;
    }

    public String getMensajeOut() {
        return mensajeOut;
    }

    public void setMensajeOut(String mensajeOut) {
        this.mensajeOut = mensajeOut;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditoriaWs)) {
            return false;
        }
        AuditoriaWs other = (AuditoriaWs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.AuditoriaWs[ id=" + id + " ]";
    }

    public boolean getInOut() {
        return inOut;
    }

    public void setInOut(boolean inOut) {
        this.inOut = inOut;
    }
    
}
