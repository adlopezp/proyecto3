/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean;

import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alvaro
 */
@ManagedBean
@SessionScoped
public class SesionBean implements Serializable {

    private final Usuario usuario;

    public SesionBean() {
        HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        usuario = (Usuario) sesion.getAttribute("Usuario");
    }

    public void cerrarCesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("sesionBean");
        session.removeAttribute("Usuario");
        UtilBean.redirect("index.jsf");
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
