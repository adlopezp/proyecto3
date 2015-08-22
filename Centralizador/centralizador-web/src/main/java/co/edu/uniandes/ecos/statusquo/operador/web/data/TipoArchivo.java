/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.data.documento;

import java.io.Serializable;

/**
 *
 * @author Dev
 */
public class TipoArchivo implements Serializable{
    public long id;
    public String Codigo;
    public String descripcion;

    public TipoArchivo() {
    }

    public TipoArchivo(long id, String Codigo, String descripcion) {
        this.id = id;
        this.Codigo = Codigo;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
