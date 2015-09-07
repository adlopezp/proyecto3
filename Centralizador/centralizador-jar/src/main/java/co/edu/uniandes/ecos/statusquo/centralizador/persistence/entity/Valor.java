/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "VALOR")
@TableGenerator(name = "seqvalor", initialValue = 1, allocationSize = 1)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valor.findAll", query = "SELECT v FROM Valor v"),
    @NamedQuery(name = "Valor.findById", query = "SELECT v FROM Valor v WHERE v.id = :id"),
    @NamedQuery(name = "Valor.findByNombre", query = "SELECT v FROM Valor v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Valor.findByDescripcion", query = "SELECT v FROM Valor v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Valor.findByTipoDato", query = "SELECT v FROM Valor v WHERE v.tipoDato = :tipoDato"),
    @NamedQuery(name = "Valor.findByFormato", query = "SELECT v FROM Valor v WHERE v.formato = :formato")})
public class Valor implements Serializable {
    @OneToMany(mappedBy = "codigoValor")
    private List<DetalleValor> detalleValorList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "seqvalor")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private BigDecimal id;
    @Column(name = "NOMBRE", length = 250)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 250)
    private String descripcion;
    @Column(name = "TIPO_DATO")
    private BigInteger tipoDato;
    @Column(name = "FORMATO", length = 250)
    private String formato;

    public Valor() {
    }

    public Valor(BigDecimal id) {
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

    public BigInteger getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(BigInteger tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
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
        if (!(object instanceof Valor)) {
            return false;
        }
        Valor other = (Valor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.Valor[ id=" + id + " ]";
    }

    @XmlTransient
    public List<DetalleValor> getDetalleValorList() {
        return detalleValorList;
    }

    public void setDetalleValorList(List<DetalleValor> detalleValorList) {
        this.detalleValorList = detalleValorList;
    }
    
}
