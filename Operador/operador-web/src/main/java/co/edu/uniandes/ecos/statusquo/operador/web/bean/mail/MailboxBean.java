/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean.mail;

import co.edu.uniandes.ecos.statusquo.operador.web.data.mail.Mail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private List<Mail> mails;
    private List<Mail> mails2;
    private List<Mail> mails3;
    private Mail mail;

    @PostConstruct
    public void init() {
        mails = new ArrayList<Mail>();
        mails.add(new Mail("usuario@carpetaciudadana.com", "Solicitud 1", "Texto solicitud", new Date()));
        mails.add(new Mail("usuario@carpetaciudadana.com", "Solicitud 2", "Texto solicitud 2", new Date()));
        mails.add(new Mail("usuario@carpetaciudadana.com", "Solicitud 3", "Texto solicitud 3", new Date()));
        mails.add(new Mail("Entidad oficial", "Solicitud 4", "Texto solicitud 4", new Date()));
        
        mails2 = new ArrayList<Mail>();
        mails2.add(new Mail("usuario@carpetaciudadana.com", "Notificacion 1", "Texto notificacion", new Date()));
        mails2.add(new Mail("usuario@carpetaciudadana.com", "Notificacion 2", "Texto notificacion 2", new Date()));
        mails2.add(new Mail("usuario@carpetaciudadana.com", "Notificacion 3", "Texto notificacion 3", new Date()));
        mails2.add(new Mail("Entidad oficial", "Notificacion 4", "Texto notificacion 4", new Date()));
        
        mails3 = new ArrayList<Mail>();
        mails3.add(new Mail("usuario@carpetaciudadana.com", "Alerta 1", "Texto Alerta", new Date()));
        mails3.add(new Mail("usuario@carpetaciudadana.com", "Alerta 2", "Texto Alerta 2", new Date()));
        mails3.add(new Mail("usuario@carpetaciudadana.com", "Alerta 3", "Texto Alerta 3", new Date()));
        mails3.add(new Mail("Entidad oficial", "Alerta 4", "Texto Alerta 4", new Date()));
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }


    public List<Mail> getMails() {
        return mails;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Mail> getMails2() {
        return mails2;
    }

    public void setMails2(List<Mail> mails2) {
        this.mails2 = mails2;
    }

    public List<Mail> getMails3() {
        return mails3;
    }

    public void setMails3(List<Mail> mails3) {
        this.mails3 = mails3;
    }
    
    
    
}
