/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.web.util;

import co.edu.uniandes.ecos.statusquo.operador.entity.Archivo;
import co.edu.uniandes.ecos.statusquo.operador.entity.Carpeta;
import co.edu.uniandes.ecos.statusquo.operador.web.data.documento.Document;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Zamir
 */
public class TreeNodeHelper {
    
    public static TreeNode toTreeNode(List<Carpeta> carpetas){
        TreeNode root =  new DefaultTreeNode("Root", null);
        
        for (Carpeta carpeta: carpetas) {
            addFolderToTree(root, carpeta);
        }
        
        
        
        return root;
    }

    private static void addFolderToTree(TreeNode root, Carpeta carpeta) {
        TreeNode nodoCarpeta = new DefaultTreeNode(carpeta, root);
        List<Carpeta> subcarpetas = carpeta.getCarpetasHijas();
        
        if (subcarpetas != null && !subcarpetas.isEmpty()) {
            for (Carpeta subcarpeta: subcarpetas) {
                addFolderToTree(nodoCarpeta, subcarpeta);
            }
        }
    }
    
}
