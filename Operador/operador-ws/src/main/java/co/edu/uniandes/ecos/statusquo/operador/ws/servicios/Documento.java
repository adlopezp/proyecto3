/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ws.servicios;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Alvaro
 */
@WebService(serviceName = "Documento")
public class Documento {

    @WebMethod(operationName = "getDocumento")
    public String getDocumento(@WebParam(name = "nombre") String nombre) {
        return "Documento " + nombre + " !";
    }
}
