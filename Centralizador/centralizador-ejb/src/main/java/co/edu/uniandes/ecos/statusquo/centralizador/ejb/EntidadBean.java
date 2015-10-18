/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ejb;

import co.edu.uniandes.ecos.statusquo.centralizador.percistence.facade.EntidadFacade;
import co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.Entidad;
import java.util.Hashtable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Dev
 */
public class EntidadBean {
    
    @EJB
    private EntidadFacade facade;

    public void createEntidad(Entidad entidad) throws Exception {
        if (entidad.getId() == null) {
            facade.create(entidad);
        } else {
            facade.edit(entidad);
        }
    }
    
    public Entidad consultarRazonSocial(String razonSocial) throws Exception {
        Hashtable<String, Object> params = new Hashtable<String, Object>();
        params.put("razonSocial", razonSocial);
        return facade.findByNamedQuery("Entidad.findByRazonSocial", params).get(0);
    }

    public List<Entidad> consultarEntidades() throws Exception {
        return facade.findAll();
    }
}
