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
@Table(name = "carpeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carpeta.findAll", query = "SELECT c FROM Carpeta c")})
public class Carpeta implements Serializable {

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
    @Column(name = "principal")
    private Boolean principal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carpetaPadre")
    private List<Archivo> archivos;
    @JoinColumn(name = "tipo_carpeta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoCarpeta tipo;
    @JoinColumn(name = "estado_carpeta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EstadoCarpeta estado;
    @JoinColumn(name = "carpeta_personal_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CarpetaPersonal carpetaPersonal;
    @OneToMany(mappedBy = "carpetaPadre")
    private List<Carpeta> carpetasHijas;
    @JoinColumn(name = "carpeta_padre_id", referencedColumnName = "id")
    @ManyToOne
    private Carpeta carpetaPadre;

    public Carpeta() {
    }

    public Carpeta(Long id) {
        this.id = id;
    }

    public Carpeta(Long id, String nombre, boolean principal) {
        this.id = id;
        this.nombre = nombre;
        this.principal = principal;
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

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    @XmlTransient
    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivoList(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public TipoCarpeta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarpeta tipo) {
        this.tipo = tipo;
    }

    public EstadoCarpeta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarpeta estado) {
        this.estado = estado;
    }

    public CarpetaPersonal getCarpetaPersonal() {
        return carpetaPersonal;
    }

    public void setCarpetaPersonal(CarpetaPersonal carpetaPersonal) {
        this.carpetaPersonal = carpetaPersonal;
    }

    @XmlTransient
    public List<Carpeta> getCarpetasHijas() {
        return carpetasHijas;
    }

    public void setCarpetasHijas(List<Carpeta> carpetasHijas) {
        this.carpetasHijas = carpetasHijas;
    }

    public Carpeta getCarpetaPadre() {
        return carpetaPadre;
    }

    public void setCarpetaPadre(Carpeta carpetaPadre) {
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
        if (!(object instanceof Carpeta)) {
            return false;
        }
        Carpeta other = (Carpeta) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta[ id=" + id + " ]";
    }

}
