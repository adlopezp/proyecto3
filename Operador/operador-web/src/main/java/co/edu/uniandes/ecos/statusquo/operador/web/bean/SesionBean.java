/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean;

import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.util.DatosConstantes;
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
        LoginBean.limpiarSesion();
        UtilBean.redirectLogin();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isAdministrador() {
        return usuario.getTipo().getNombre().equals(DatosConstantes.ADMINISTRADOR);
    }

    public static SesionBean getInstanciaActual() {
        HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        SesionBean bean = null;
        if (sesion != null) {
            bean = (SesionBean) sesion.getAttribute("sesionBean");
        }
        return bean;
    }
}
