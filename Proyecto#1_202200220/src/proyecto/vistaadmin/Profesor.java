/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.vistaadmin;

/**
 *
 * @author Paola
 */
public class Profesor {
    public String codigo;
    public String nombre;
    public String apellido;
    public String correo;                     
    public String contraseña;
    public String genero;

    
    public  Profesor(String codigo, String nombre, String apellido, String correo, String genero, String contraseña) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.genero = genero;

    }
    
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getGenero() {
        return genero;
    }
    public void setCodigo(String nuevoCodigo) {
        this.codigo = nuevoCodigo;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void setApellido(String nuevoApellido) {
        this.apellido = nuevoApellido;
    }

    public void setCorreo(String nuevoCorreo) {
        this.correo = nuevoCorreo;
    }

    public void setGenero(String nuevoGenero) {
        this.genero = nuevoGenero;
    }

    void setContraseña(String contraseñaPorDefecto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

//funciona bien
//funciona bien
//funciona bien
//funciona bien
//funciona bien
//funciona bien
//funciona perefecto