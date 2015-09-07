/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dev
 */
@Entity
@Table(name = "DETALLE_VALOR", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO_VALOR", "CODIGO"})})
@TableGenerator(name = "seqdetallevalor", initialValue = 1, allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleValor.findAll", query = "SELECT d FROM DetalleValor d"),
    @NamedQuery(name = "DetalleValor.findById", query = "SELECT d FROM DetalleValor d WHERE d.id = :id"),
    @NamedQuery(name = "DetalleValor.findByValor", query = "SELECT d FROM DetalleValor d WHERE d.valor = :valor"),
    @NamedQuery(name = "DetalleValor.findByDescripcion", query = "SELECT d FROM DetalleValor d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DetalleValor.findByCodigo", query = "SELECT d FROM DetalleValor d WHERE d.codigo = :codigo")})
public class DetalleValor implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqdetallevalor")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private BigDecimal id;
    @Column(name = "VALOR", length = 250)
    private String valor;
    @Column(name = "DESCRIPCION", length = 250)
    private String descripcion;
    @Column(name = "CODIGO", length = 20)
    private String codigo;
    @JoinColumn(name = "CODIGO_VALOR", referencedColumnName = "ID")
    @ManyToOne
    private Valor codigoValor;

    public DetalleValor() {
    }

    public DetalleValor(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Valor getCodigoValor() {
        return codigoValor;
    }

    public void setCodigoValor(Valor codigoValor) {
        this.codigoValor = codigoValor;
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
        if (!(object instanceof DetalleValor)) {
            return false;
        }
        DetalleValor other = (DetalleValor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.DetalleValor[ id=" + id + " ]";
    }
    
}
