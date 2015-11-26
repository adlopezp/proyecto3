/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.dao;

import co.edu.uniandes.ecos.statusquo.operador.entity.EstadoArchivo;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Alvaro
 */
@LocalBean
@Stateless
public class EstadoArchivoDAO extends AbstractDAO<EstadoArchivo> {

    public EstadoArchivoDAO() {
        super(EstadoArchivo.class);
    }

    public EstadoArchivo getEstadoActivo() {
        return buscar(1l);
    }

}
