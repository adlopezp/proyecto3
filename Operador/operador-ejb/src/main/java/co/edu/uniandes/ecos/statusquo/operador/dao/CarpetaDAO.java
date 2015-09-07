/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.dao;

import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Alvaro
 */
@LocalBean
@Stateless
public class CarpetaDAO extends AbstractDAO<Carpeta> {

    public CarpetaDAO() {
        super(Carpeta.class);
    }

    public List<Carpeta> consultarPorUsuario(Usuario usuario) {
        StringBuilder sb = new StringBuilder("select new java.lang.Long(c.id) ");
        sb.append("from Usuario u ");
        sb.append("join u.carpetaPersonal cp ");
        sb.append("join cp.carpetas c ");
        sb.append("where c.carpetaPadre is null and u.id=:id");
        
        Query query = em.createQuery(sb.toString());
        query.setParameter("id", usuario.getId());
        
        final List<Long> idCarpetas = query.getResultList();
        List<Carpeta> carpetas = new ArrayList<>();
        
        for (Long l: idCarpetas) {
            carpetas.add(this.buscar(l));
        }
        
        return carpetas;
    }
    
}
