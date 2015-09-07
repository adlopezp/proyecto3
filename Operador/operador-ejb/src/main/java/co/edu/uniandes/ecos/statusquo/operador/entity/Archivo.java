/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "archivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archivo.findAll", query = "SELECT a FROM Archivo a")})
public class Archivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "size_archivo")
    private Integer sizeArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Column(name = "firmado")
    private Boolean firmado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "archivo")
    private List<Etiqueta> etiquetas;
    @JoinColumn(name = "tipo_archivo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoArchivo tipo;
    @JoinColumn(name = "formato_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FormatoArchivo formato;
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoArchivo estado;
    @JoinColumn(name = "carpeta_personal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CarpetaPersonal carpetaPersonal;
    @JoinColumn(name = "carpeta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Carpeta carpetaPadre;

    public Archivo() {
    }

    public Archivo(Long id) {
        this.id = id;
    }

    public Archivo(Long id, String nombre, int sizeArchivo, String url, boolean firmado) {
        this.id = id;
        this.nombre = nombre;
        this.sizeArchivo = sizeArchivo;
        this.url = url;
        this.firmado = firmado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSizeArchivo() {
        return sizeArchivo;
    }

    public void setSizeArchivo(Integer sizeArchivo) {
        this.sizeArchivo = sizeArchivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getFirmado() {
        return firmado;
    }

    public void setFirmado(Boolean firmado) {
        this.firmado = firmado;
    }

    @XmlTransient
    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public TipoArchivo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArchivo tipo) {
        this.tipo = tipo;
    }

    public FormatoArchivo getFormato() {
        return formato;
    }

    public void setFormato(FormatoArchivo formato) {
        this.formato = formato;
    }

    public EstadoArchivo getEstado() {
        return estado;
    }

    public void setEstadoId(EstadoArchivo estado) {
        this.estado = estado;
    }

    public CarpetaPersonal getCarpetaPersonal() {
        return carpetaPersonal;
    }

    public void setCarpetaPersonal(CarpetaPersonal carpetaPersonal) {
        this.carpetaPersonal = carpetaPersonal;
    }

    public Carpeta getCarpetaPadre() {
        return carpetaPadre;
    }

    public void setCarpetaPadreId(Carpeta carpetaPadre) {
        this.carpetaPadre = carpetaPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Archivo)) {
            return false;
        }
        Archivo other = (Archivo) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.ecos.statusquo.operador.entity.Archivo[ id=" + id + " ]";
    }

}
