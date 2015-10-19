/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alvaro
 */
@Entity
@Table(name = "autenticacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autenticacion.findAll", query = "SELECT a FROM Autenticacion a"),
    @NamedQuery(name = "Autenticacion.findByLogin", query = "SELECT a FROM Autenticacion a where a.codigo = ?1"),
    @NamedQuery(name = "Autenticacion.autenticar", query = "SELECT a FROM Autenticacion a where a.codigo = ?1 and a.password = ?2")})
public class Autenticacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "seq_autenticacion", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_autenticacion")
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @NotNull
    @OneToOne(optional = false)
    private Usuario usuario;

    public Autenticacion() {
    }

    public Autenticacion(Long id) {
        this.id = id;
    }

    public Autenticacion(Long id, String codigo, String password) {
        this.id = id;
        this.codigo = codigo;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof Autenticacion)) {
            return false;
        }
        Autenticacion other = (Autenticacion) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.ecos.statusquo.operador.entity.Autenticacion[ id=" + id + " ]";
    }

}
