package co.edu.uniandes.ecos.statusquo.centralizador.ejb;

import co.edu.uniandes.ecos.statusquo.centralizador.percistence.facade.UsuarioFacade;
import co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.Usuario;
import java.math.BigDecimal;
import java.util.Hashtable;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Alvaro
 */
@Stateless
@LocalBean
public class UsuarioBean {
    
    @EJB
    private UsuarioFacade facade;

    public void createUsuario(Usuario user) throws Exception {
        if (user.getId() == null) {
            facade.create(user);
        } else {
            facade.edit(user);
        }
    }
    
    public Usuario consultarIdentificacion(String identificacion) throws Exception {
        Hashtable<String, Object> params = new Hashtable<String, Object>();
        params.put("numeroIdentificacion", identificacion);
        return facade.findSingleObjectByNamedQuery("Usuario.findByNumeroIdentificacion", params);
    }
}
