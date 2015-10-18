/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ejb;

import co.edu.uniandes.ecos.statusquo.centralizador.percistence.facade.TipoDocumentoFacade;
import co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.TipoDocumento;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Dev
 */
@Stateless
public class TipoDocumentoBean {

    @EJB
    private TipoDocumentoFacade facade;

    public void createTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
        if (tipoDocumento.getId() == null) {
            facade.create(tipoDocumento);
        } else {
            facade.edit(tipoDocumento);
        }
    }
    
    public List<TipoDocumento> consultarTipoDocumento () throws Exception {
        return facade.findAll();
    }
}
