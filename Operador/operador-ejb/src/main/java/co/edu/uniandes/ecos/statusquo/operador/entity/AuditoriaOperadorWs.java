/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev
 */
@Entity
@SequenceGenerator(name = "seq_auditoriaws", initialValue = 1, allocationSize = 1)
@Table(name = "auditoria_operador_ws")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditoriaOperadorWs.findAll", query = "SELECT a FROM AuditoriaOperadorWs a"),
    @NamedQuery(name = "AuditoriaOperadorWs.findById", query = "SELECT a FROM AuditoriaOperadorWs a WHERE a.id = :id"),
    @NamedQuery(name = "AuditoriaOperadorWs.findByFechaIn", query = "SELECT a FROM AuditoriaOperadorWs a WHERE a.fechaIn = :fechaIn"),
    @NamedQuery(name = "AuditoriaOperadorWs.findByMensajeIn", query = "SELECT a FROM AuditoriaOperadorWs a WHERE a.mensajeIn = :mensajeIn"),
    @NamedQuery(name = "AuditoriaOperadorWs.findByHostActor", query = "SELECT a FROM AuditoriaOperadorWs a WHERE a.hostActor = :hostActor"),
    @NamedQuery(name = "AuditoriaOperadorWs.findByFechaOut", query = "SELECT a FROM AuditoriaOperadorWs a WHERE a.fechaOut = :fechaOut"),
    @NamedQuery(name = "AuditoriaOperadorWs.findByMensajeOut", query = "SELECT a FROM AuditoriaOperadorWs a WHERE a.mensajeOut = :mensajeOut"),
    @NamedQuery(name = "AuditoriaOperadorWs.findByIdcliente", query = "SELECT a FROM AuditoriaOperadorWs a WHERE a.idcliente = :idcliente"),
    @NamedQuery(name = "AuditoriaOperadorWs.findByInOut", query = "SELECT a FROM AuditoriaOperadorWs a WHERE a.inOut = :inOut")})
public class AuditoriaOperadorWs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_auditoria_operador", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_auditoria_operador")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fecha_in")
    @Temporal(TemporalType.DATE)
    private Date fechaIn;

    @Size(max = 10485760)
    @Column(name = "mensaje_in", length = 10485760)
    private String mensajeIn;

    @Size(max = 250)
    @Column(name = "host_actor", length = 250)
    private String hostActor;

    @Column(name = "fecha_out")
    @Temporal(TemporalType.DATE)
    private Date fechaOut;

    @Size(max = 10485760)
    @Column(name = "mensaje_out", length = 10485760)
    private String mensajeOut;

    @Size(max = 250)
    @Column(name = "idcliente", length = 250)
    private String idcliente;

    @Column(name = "in_out")
    private Boolean inOut;

    public AuditoriaOperadorWs() {
    }

    public AuditoriaOperadorWs(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Boolean getInOut() {
        return inOut;
    }

    public void setInOut(Boolean inOut) {
        this.inOut = inOut;
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
        if (!(object instanceof AuditoriaOperadorWs)) {
            return false;
        }
        AuditoriaOperadorWs other = (AuditoriaOperadorWs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.ecos.statusquo.operador.entity.AuditoriaOperadorWs[ id=" + id + " ]";
    }

}
