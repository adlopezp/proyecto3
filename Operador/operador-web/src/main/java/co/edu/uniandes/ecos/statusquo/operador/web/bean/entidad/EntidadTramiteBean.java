/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean.entidad;

import co.edu.uniandes.ecos.statusquo.operador.web.bean.UtilBean;
import co.edu.uniandes.ecos.statusquo.operador.web.data.entidad.Tramite;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Alvaro
 */
@ManagedBean(name = "entidadTramitesBean")
@ViewScoped
public class EntidadTramiteBean {

    private Tramite seleccionado;
    private boolean selected = false;
    private List<Tramite> tramites;

    @PostConstruct
    public void init() {
        setSeleccionado(new Tramite());
        tramites = new ArrayList<>();
        tramites.add(new Tramite("Andres Gomez", "123678587", "Tramite 1", "Pendiete Documentos", "Usuario 1", "2015-01-22"));
        tramites.add(new Tramite("Sandra Pardo", "16537358", "Tramite 3", "Pendiete Documentos", "Usuario 1", "2015-02-15"));
        tramites.add(new Tramite("Carlos Martinez", "125794694", "Tramite 2", "Pendiete Documentos", "Usuario 4", "2015-03-19"));
        tramites.add(new Tramite("Juan Vasquez", "9845643", "Tramite 1", "Pendiete Documentos", "Usuario 2", "2015-04-28"));
    }

    public List<Tramite> getLista() {
        return tramites;
    }

    public void onRowSelect(SelectEvent event) {
        setSeleccionado((Tramite) event.getObject());
        this.selected = true;
    }

    public void onRowUnselect(UnselectEvent event) {
        reset();
        this.selected = false;
    }

    public void reset() {
        setSeleccionado(new Tramite());
    }

    public void remove() {
        if (isSelected()) {
            tramites.remove(getSeleccionado());
            UtilBean.printMensajeInfo("Se elimino el registro con exito");
        } else {
            UtilBean.printMensajeWarn("Debe seleccionar uno de los registros de la tabla");
        }
    }

    public void save() {
        System.out.println("PINTAR");
        if (isSelected()) {
//            tramites.add(seleccionado);
            UtilBean.printMensajeInfo("El registro fue ingresado exitosamente");
        } else {
            UtilBean.printMensajeInfo("El registro fue actualizado exitosamente");
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public Tramite getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Tramite seleccionado) {
        this.seleccionado = seleccionado;
    }
}
