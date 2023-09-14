/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.vistaadmin;

/**
 *
 * @author Paola
 */
public class Alumno {
    
    public String codigo;
    public String nombre;
    public String apellido;
    public String correo;
    public String genero;
    

    
    public  Alumno(String codigo, String nombre, String apellido, String correo, String genero, String string) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
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
}
