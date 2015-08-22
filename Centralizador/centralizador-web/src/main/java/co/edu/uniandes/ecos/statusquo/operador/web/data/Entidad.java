/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.data;

import java.io.Serializable;

/**
 *
 * @author Dev
 */
public class Entidad implements Serializable{
    public long id;
    public String nombre;
    public String descripcion;
    public int tipoEntidad;

    public Entidad() {
    }

    public Entidad(long id, String nombre, String descripcion, int tipoEntidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoEntidad = tipoEntidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(int tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }
    
    
}
