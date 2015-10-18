/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dev
 */
@Entity
@Table(name = "ENTIDAD",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"RAZON_SOCIAL"})})
@TableGenerator(name = "seqentidad", initialValue = 1, allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entidad.findAll", query = "SELECT e FROM Entidad e"),
    @NamedQuery(name = "Entidad.findById", query = "SELECT e FROM Entidad e WHERE e.id = :id"),
    @NamedQuery(name = "Entidad.findByRazonSocial", query = "SELECT e FROM Entidad e WHERE e.razonSocial = :razonSocial"),
    @NamedQuery(name = "Entidad.findByDescripcion", query = "SELECT e FROM Entidad e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Entidad.findByTipoEntidad", query = "SELECT e FROM Entidad e WHERE e.tipoEntidad = :tipoEntidad"),
    @NamedQuery(name = "Entidad.findByDireccion", query = "SELECT e FROM Entidad e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Entidad.findByTelefono", query = "SELECT e FROM Entidad e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Entidad.findByFechaConstitucion", query = "SELECT e FROM Entidad e WHERE e.fechaConstitucion = :fechaConstitucion")})
public class Entidad implements Serializable {
    @JoinColumn(name = "TIPO_ENTIDAD", referencedColumnName = "ID")
    @ManyToOne
    private DetalleValor tipoEntidad;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqentidad")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private BigDecimal id;
    @Column(name = "RAZON_SOCIAL", length = 250)
    private String razonSocial;
    @Column(name = "DESCRIPCION", length = 250)
    private String descripcion;
    @Column(name = "DIRECCION", length = 250)
    private String direccion;
    @Column(name = "TELEFONO", length = 250)
    private String telefono;
    @Column(name = "FECHA_CONSTITUCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConstitucion;
    @OneToMany(mappedBy = "entidad")
    private List<Servicio> servicioList;
    @OneToMany(mappedBy = "entidad")
    private List<EntidadOperador> entidadOperadorList;

    public Entidad() {
    }

    public Entidad(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaConstitucion() {
        return fechaConstitucion;
    }

    public void setFechaConstitucion(Date fechaConstitucion) {
        this.fechaConstitucion = fechaConstitucion;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    @XmlTransient
    public List<EntidadOperador> getEntidadOperadorList() {
        return entidadOperadorList;
    }

    public void setEntidadOperadorList(List<EntidadOperador> entidadOperadorList) {
        this.entidadOperadorList = entidadOperadorList;
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
        if (!(object instanceof Entidad)) {
            return false;
        }
        Entidad other = (Entidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.Entidad[ id=" + id + " ]";
    }

    public DetalleValor getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(DetalleValor tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }
    
}
