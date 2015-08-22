/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import co.edu.uniandes.ecos.statusquo.operador.web.utils.HelperMethods;

/**
 *
 * @author Dev
 */
@ManagedBean(name = "crudBean")
//@ViewScoped TODO los constroladores deberian ser viewscope cuando se desarrolle correctamente la app
@SessionScoped
public class CrudBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 9142389918676838222L;
	
	private String title = "Crud";
    /*@EJB
     private Facade facade;*/
    private List<String> list=new ArrayList<String>();
    private String selectedRecord;
    private boolean selected = false;
    private int row ;

    public CrudBean() {
    }

    @PostConstruct
    public void iniciarBean() {
        consultList();
    }

    private void consultEdit() {
    }

    public void initNew() {
        reset();
        consultEdit();
    }

    public void initEdit() {
        consultEdit();
    }

    public void save() {
        if (selected == false) {
            list.add(selectedRecord);
            //facade.create(selectedRecord);
            refresh();
            HelperMethods.showMessageGrowl("OK", "El registro fue ingresado exitosamente");
        } else {
            //facade.edit(selectedRecord);
            HelperMethods.showMessageGrowl("OK", "El registro fue actualizado exitosamente");
        }
    }

    public void remove() {
        if (selectedRecord == null) {
            HelperMethods.showMessageGrowl("Alerta", "Debe seleccionar uno de los registros de la tabla.", FacesMessage.SEVERITY_WARN);
        } else {
            list.remove(selectedRecord);
            //facade.remove(selectedRecord);
            refresh();
            HelperMethods.showMessageGrowl("OK", "Se elimino el registro con exito.");
        }
    }

    public void onRowSelect(SelectEvent event) {
        setSelectedRecord((String) event.getObject());
        setSelected(true);
    }

    public void onRowUnselect(UnselectEvent event) {
        reset();
    }

    public void refresh() {
        consultList();
        reset();
    }

    public void reset() {
        setSelectedRecord(new String());
        setSelected(false);
    }

    private void consultList() {
        //setList(facade.findAll());
        setList(list);
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getSelectedRecord() {
        if (selectedRecord == null) {
            selectedRecord = new String();
        }
        return selectedRecord;
    }

    public void setSelectedRecord(String selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
    
}
