/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.vistaadmin;

/**
 *
 * @author Paola
 */
public class Curso {
    public String codigo;
    public String nombre;
    public String creditos;
    public String alumnos;
    public String profesor;
    

    
    public  Curso(String codigo, String nombre, String creditos, String alumnos, String profesor, String string) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.alumnos = alumnos;
        this.profesor = profesor;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCreditos() {
        return creditos;
    }

    public String getAlumnos() {
        return alumnos;
    }
    
    public String getProfesor() {
        return profesor;
    }

    public void setCodigo(String nuevoCodigo) {
        this.codigo = nuevoCodigo;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void setCreditos(String nuevoCreditos) {
        this.creditos = nuevoCreditos;
    }

    public void setAlumnos(String nuevoAlumnos) {
        this.alumnos = nuevoAlumnos;
    }
    
    public void setProfesor(String nuevoProfesor) {
        this.profesor = nuevoProfesor;
    }
}
//funciona bien
//funciona bien
//funciona perfecto