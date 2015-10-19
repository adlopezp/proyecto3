/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.ejb;

import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Zamir
 */
@Stateless
@LocalBean
public class SeguridadEJB {
    
    @EJB
    private PropertiesEJB propertiesEJB;
    
    /**
     * Genera el par de llaves públicas y privadas para firmar documentos.
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException 
     */
    public KeyPair generarLlavesAleatorias() throws NoSuchAlgorithmException,
                                                     NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        return keyGen.generateKeyPair();
    }
    
    
    public void guardarLlaves(KeyPair claves, Usuario usuario) throws IOException {
        String ruta_base = propertiesEJB.getProperty("almacenamiento.ruta") 
                + "/" + usuario.getDocumento() + "/";
        PrivateKey privada = claves.getPrivate();
        PublicKey publica = claves.getPublic();
        
        /* Guardar llave pública */
        byte[] bytesLlavePublica = publica.getEncoded();
        FileOutputStream publicaOS = 
                    new FileOutputStream(ruta_base + "public.key");
        publicaOS.write(bytesLlavePublica);
        publicaOS.close();
            
        /* Guardar llave privada */
        byte[] bytesLlavePrivada = privada.getEncoded();
        FileOutputStream privadaOS = new FileOutputStream(ruta_base + "private.key");
        privadaOS.write(bytesLlavePrivada);
        privadaOS.close();
    }
    
    public void escribirLlaves(Usuario usuario) 
            throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
        KeyPair llaves = generarLlavesAleatorias();
        guardarLlaves(llaves, usuario);
    }
    
}
