/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.dao;

import co.edu.uniandes.ecos.statusquo.operador.entity.Archivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

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

     /**
     * Retorna los archivos y de una carpeta seleccionada
     * @param carpetaSeleccionada
     * @return 
     */
    public List<Archivo> traerArchivosCarpeta(Carpeta carpetaSeleccionada) {
        StringBuilder sb = new StringBuilder();
        sb.append("from Archivo a ");
        sb.append("where a.carpetaPadre.id = :carpetaId");
        Query query = em.createQuery(sb.toString());
        query.setParameter("carpetaId", carpetaSeleccionada.getId());
        return query.getResultList();
    }

}
