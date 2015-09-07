/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.percistence.facade;

import co.edu.uniandes.ecos.statusquo.centralizador.persistence.entity.EntidadOperador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dev
 */
@Stateless
public class EntidadOperadorFacade extends AbstractFacade<EntidadOperador> {

    @PersistenceContext(unitName = "statusquo.centralizadorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntidadOperadorFacade() {
        super(EntidadOperador.class);
    }
}
