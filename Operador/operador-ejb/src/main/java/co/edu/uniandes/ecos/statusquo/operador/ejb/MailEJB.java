/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ejb;

import co.edu.uniandes.ecos.statusquo.operador.dao.MensajeDAO;
import co.edu.uniandes.ecos.statusquo.operador.entity.Mensaje;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dev
 */
@Stateless
@LocalBean
public class MailEJB {

    @EJB
    private MensajeDAO dao;

    public List<Mensaje> consultarMensajesporusuario(List<Object> params) {
        return dao.consultarNamedQuery("Mensaje.findByTipo", params);
    }
}
