/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev
 */
@Entity
@Table(name = "ENTIDAD_OPERADOR")
@TableGenerator(name = "seqentidadoperador", initialValue = 1, allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntidadOperador.findAll", query = "SELECT e FROM EntidadOperador e"),
    @NamedQuery(name = "EntidadOperador.findById", query = "SELECT e FROM EntidadOperador e WHERE e.id = :id"),
    @NamedQuery(name = "EntidadOperador.findByPrincipal", query = "SELECT e FROM EntidadOperador e WHERE e.principal = :principal")})
public class EntidadOperador implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqentidadoperador")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private BigDecimal id;
    @Column(name = "PRINCIPAL")
    private boolean principal;
    @JoinColumn(name = "OPERADOR", referencedColumnName = "ID")
    @ManyToOne
    private Operador operador;
    @JoinColumn(name = "ENTIDAD", referencedColumnName = "ID")
    @ManyToOne
    private Entidad entidad;

    public EntidadOperador() {
    }

    public EntidadOperador(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
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
        if (!(object instanceof EntidadOperador)) {
            return false;
        }
        EntidadOperador other = (EntidadOperador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.EntidadOperador[ id=" + id + " ]";
    }
    
}
