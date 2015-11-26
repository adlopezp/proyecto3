/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.operador.util;

import co.edu.uniandes.ecos.statusquo.operador.entity.Usuario;
import co.edu.uniandes.ecos.statusquo.operador.ws.dto.UsuarioDTO;

/**
 *
 * @author Alvaro
 */
public class DTOUtil {

    public static UsuarioDTO getUsuarioDTO(final Usuario usuario) {

        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setApellido1(usuario.getApellido1());
        usuarioDto.setApellido2(usuario.getApellido2());
        usuarioDto.setCorreo(usuario.getCorreo());
        usuarioDto.setNombre1(usuario.getNombre1());
        usuarioDto.setNombre2(usuario.getNombre2());
        usuarioDto.setDocumento(usuario.getDocumento());
        usuarioDto.setTelefono(usuario.getTelefono());
        usuarioDto.setTipoDocumento(usuario.getTipoDocumento().toString());
        return usuarioDto;
    }

}
