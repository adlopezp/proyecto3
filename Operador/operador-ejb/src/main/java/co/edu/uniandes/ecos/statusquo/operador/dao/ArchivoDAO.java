/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.dao;

import co.edu.uniandes.ecos.statusquo.operador.entity.Archivo;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Zamir
 */
@LocalBean
@Stateless
public class ArchivoDAO extends AbstractDAO<Archivo>{
    
    public ArchivoDAO(){
        super(Archivo.class);
    }
    
}
