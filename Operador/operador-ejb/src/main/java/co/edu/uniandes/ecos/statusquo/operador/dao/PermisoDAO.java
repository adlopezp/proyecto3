/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.dao;

import co.edu.uniandes.ecos.statusquo.operador.entity.Permiso;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Alvaro
 */
@LocalBean
@Stateless
public class PermisoDAO extends AbstractDAO<Permiso> {

    public PermisoDAO() {
        super(Permiso.class);
    }

}
