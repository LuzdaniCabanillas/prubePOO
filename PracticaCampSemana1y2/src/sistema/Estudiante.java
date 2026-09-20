
package sistema;

import java.util.ArrayList;

public class Estudiante {
    private String codigo;
    private String nombre;
    private String correo;
    private String curso;
    private String carrera;
    private ArrayList<Double> calificaciones;

    public Estudiante(String codigo, String nombre, String correo, String curso, String carrera) {
        
        if(!codigo.matches("N[0-9]{8}")){
         throw new IllegalArgumentException(
                    "El código debe iniciar la letra N seguidos de 8 números" 
         );
        }
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
        this.curso = curso;
        this.carrera = carrera;
        this.calificaciones = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public ArrayList<Double> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<Double> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    
    public void registrarCalificacion(double calificacion) {

        if (calificacion < 0 || calificacion > 20) {
            throw new IllegalArgumentException(
                "La calificación debe ser entre 0 y 20."
            );
        }

        calificaciones.add(calificacion);
    }

    
    public double calcularPromedio() {

        if (calificaciones.isEmpty()) {
            return 0;
        }

        double suma = 0;

        for (double calificacion : calificaciones) {
            suma += calificacion;
        }

        return suma / calificaciones.size();
        
    }
    
    
    public void mostrarDatos() {

        System.out.println("Código: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Curso: " + curso);
        System.out.println("Carrera:ING DE SISTEMAS COMPUTACIONALES");
        System.out.printf("Promedio: %.1f%n", calcularPromedio());
        System.out.println("-----------------------");

    }
    
    
    
}
