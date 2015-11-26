package co.edu.uniandes.ecos.statusquo.operador.ws.dto;

/**
 *
 * @author Alvaro
 */
public class UsuarioDTO {

    private Long id;

    private String nombre1;

    private String nombre2;

    private String apellido1;

    private String apellido2;

    private String tipoDocumento;

    private String documento;

    private String correo;

    private String telefono;

    private String celular;

    private String direccion;

    private boolean usuarioLocal;

    private OperadorDTO operador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombreCompleto() {
        return nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public OperadorDTO getOperador() {
        return operador;
    }

    public void setOperador(OperadorDTO operador) {
        this.operador = operador;
    }

    public boolean isUsuarioLocal() {
        return usuarioLocal;
    }

    public void setUsuarioLocal(boolean usuarioLocal) {
        this.usuarioLocal = usuarioLocal;
    }
}
