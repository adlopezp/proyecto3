/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean.mail;

import co.edu.uniandes.ecos.statusquo.operador.ejb.MailEJB;
import co.edu.uniandes.ecos.statusquo.operador.entity.Mensaje;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.web.bean.UtilBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Dev
 */
@ManagedBean(name = "mailboxBean")
@ViewScoped
public class MailboxBean implements Serializable {

    private String title = "Buzon de Correo";
    private List<Mensaje> mails;
    private List<Mensaje> mails2;
    private List<Mensaje> mails3;
    private Mensaje mail;
    private Usuario usuario=UtilBean.getUsuarioActual();
    
    @EJB
    private MailEJB dao;

    @PostConstruct
    public void init() {
        List<Object> params=new ArrayList<Object>();
        params.add(new Long(1));
        params.add(usuario.getId());
        setMails(dao.consultarMensajesporusuario(params));
        params.clear();
        params.add(new Long(2));
        params.add(usuario.getId());
        setMails2(dao.consultarMensajesporusuario(params));
        /*mails = new ArrayList<Mail>();
        mails.add(new Mail("1016008913", "Solicitud Para Compartir Documentos", "Compartir Documento Cedula", new Date()));

        mails2 = new ArrayList<Mail>();
        mails2.add(new Mail("1016008913", "Notificacion de Documento Compartido", "Compartido Documento RUT", new Date()));

        mails3 = new ArrayList<Mail>();
        mails3.add(new Mail("usuario@carpetaciudadana.com", "Alerta 1", "Texto Alerta", new Date()));
        mails3.add(new Mail("usuario@carpetaciudadana.com", "Alerta 2", "Texto Alerta 2", new Date()));
        mails3.add(new Mail("usuario@carpetaciudadana.com", "Alerta 3", "Texto Alerta 3", new Date()));
        mails3.add(new Mail("Entidad oficial", "Alerta 4", "Texto Alerta 4", new Date()));*/
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Mensaje> getMails() {
        return mails;
    }

    public void setMails(List<Mensaje> mails) {
        this.mails = mails;
    }

    public List<Mensaje> getMails2() {
        return mails2;
    }

    public void setMails2(List<Mensaje> mails2) {
        this.mails2 = mails2;
    }

    public List<Mensaje> getMails3() {
        return mails3;
    }

    public void setMails3(List<Mensaje> mails3) {
        this.mails3 = mails3;
    }

    public Mensaje getMail() {
        return mail;
    }

    public void setMail(Mensaje mail) {
        this.mail = mail;
    }
}
