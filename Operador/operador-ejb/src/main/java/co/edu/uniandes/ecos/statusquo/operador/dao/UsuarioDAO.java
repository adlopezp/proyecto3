/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.dao;

import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alvaro
 */
public class UsuarioDAO extends AbstractDAO<Usuario> {

    public UsuarioDAO(Class<Usuario> clase, EntityManager em) {
        super(clase, em);
    }

    public UsuarioDAO(Class<Usuario> clase, EntityManagerFactory emf) {
        super(clase, emf);
    }

}
