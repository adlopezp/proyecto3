/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ejb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *  EJB para manejar las propiedades de la aplicación del archivo app.properties
 * @author Zamir
 */
@Singleton
@Startup
public class PropertiesEJB {
    
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
    
    @PostConstruct
    public void init(){
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("app.properties");
        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
