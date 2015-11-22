/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.centralizador.ws.servicios;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback;
import javax.ejb.Stateless;

/**
 *
 * @author Dev
 */
public class PasswordCallBack implements
        PasswordValidationCallback.PasswordValidator {
	
    @Override
    public boolean validate(PasswordValidationCallback.Request request)
            throws PasswordValidationCallback.PasswordValidationException {

        PasswordValidationCallback.PlainTextPasswordRequest plainTextRequest 
            = (PasswordValidationCallback.PlainTextPasswordRequest) request;
          if ("wsitUser".equals(plainTextRequest.getUsername())
                && "changeit".equals(plainTextRequest.getPassword())) {
            return true;
        
        }else{
        	throw new PasswordValidationCallback.PasswordValidationException("Invalid credentials provided. Authentication failed");
        }
        
        //return false;
    }
}
