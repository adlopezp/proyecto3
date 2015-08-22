/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Alvaro
 */
@WebService(serviceName = "Usuario")
public class Usuario {

    @WebMethod(operationName = "getUsuario")
    public String getDocumento(@WebParam(name = "identificacion") String identificacion) {
        return "Usuario " + identificacion + " !";
    }
}
