/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dev
 */
@Entity
@TableGenerator(name = "seqoperador", initialValue = 1, allocationSize = 1)
@Table(name = "OPERADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operador.findAll", query = "SELECT o FROM Operador o"),
    @NamedQuery(name = "Operador.findById", query = "SELECT o FROM Operador o WHERE o.id = :id"),
    @NamedQuery(name = "Operador.findByNombre", query = "SELECT o FROM Operador o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "Operador.findByDescripcion", query = "SELECT o FROM Operador o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Operador.findByUrl", query = "SELECT o FROM Operador o WHERE o.url = :url")})
public class Operador implements Serializable {
    @OneToMany(mappedBy = "operador")
    private List<EntidadOperador> entidadOperadorList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqoperador")
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private BigDecimal id;
    @Column(name = "NOMBRE", length = 225)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 225)
    private String descripcion;
    @Column(name = "URL", length = 225)
    private String url;
    @OneToMany(mappedBy = "operador", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList;

    public Operador() {
    }

    public Operador(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
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
        if (!(object instanceof Operador)) {
            return false;
        }
        Operador other = (Operador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecosstatusquo.centralizador.persistence.entity.Operador[ id=" + id + " ]";
    }

    @XmlTransient
    public List<EntidadOperador> getEntidadOperadorList() {
        return entidadOperadorList;
    }

    public void setEntidadOperadorList(List<EntidadOperador> entidadOperadorList) {
        this.entidadOperadorList = entidadOperadorList;
    }
    
}
