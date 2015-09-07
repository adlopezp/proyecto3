/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean;

import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.io.IOException;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alvaro
 */
@ManagedBean(name = "utilBean")
@ApplicationScoped
public class UtilBean {

    public Date getCurrentTime() {
        return new Date();
    }

    public static void redirect(final String url) {
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(url);
        } catch (final IOException ex) {
        }
    }

    public static void redirectLogin() {
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final HttpServletRequest request = (HttpServletRequest) context.getRequest();
        final String path = request.getContextPath();
        try {
            context.redirect(path + "/index.jsf");
        } catch (final IOException ex) {
        }
    }

    public static void printException(final Exception ex) {
        ex.printStackTrace(System.out);
        final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Grave", "Ocurrió un error en el sistema");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void printMensajeWarn(final String msj) {
        final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", msj);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void printMensajeInfo(final String msj) {
        final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", msj);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static void printMensajeError(final String msj) {
        final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msj);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public static Usuario getUsuarioActual(){
        Usuario usuario = null;
        try {
            HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
            usuario = (Usuario)sesion.getAttribute("Usuario");
        } catch (Exception e) {
            printMensajeError("Imposible reconocer el usuario en la sesión");
        }
        return usuario;
        
    }
}
