/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.dao;

import co.edu.uniandes.ecos.statusquo.operador.entity.FormatoArchivo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Alvaro
 */
@LocalBean
@Stateless
public class FormatoArchivoDAO extends AbstractDAO<FormatoArchivo> {

    public FormatoArchivoDAO() {
        super(FormatoArchivo.class);
    }

    public FormatoArchivo buscarPorExtencion(final String extencion) {
        List<Object> parametros = new ArrayList<>();
        parametros.add(extencion);
        return buscarNamedQuery("FormatoArchivo.findByExtencion", parametros);
    }
}
