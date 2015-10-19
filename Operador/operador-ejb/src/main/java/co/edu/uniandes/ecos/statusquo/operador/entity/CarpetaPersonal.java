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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "carpeta_personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarpetaPersonal.findAll", query = "SELECT c FROM CarpetaPersonal c")})
public class CarpetaPersonal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "seq_carpeta_personal", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carpeta_personal")
    @Column(name = "id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carpetaPersonal")
    private List<Archivo> archivos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carpetaPersonal")
    private List<Carpeta> carpetas;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Usuario usuario;

    public CarpetaPersonal() {
    }

    public CarpetaPersonal(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    @XmlTransient
    public List<Carpeta> getCarpetas() {
        return carpetas;
    }

    public void setCarpetaList(List<Carpeta> carpetas) {
        this.carpetas = carpetas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CarpetaPersonal)) {
            return false;
        }
        CarpetaPersonal other = (CarpetaPersonal) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "CarpetaPersonal{" + "id=" + id + ", archivos=" + archivos + ", carpetas=" + carpetas + ", usuario=" + usuario + '}';
    }

  

}
