package sistema;

import java.util.ArrayList;

public class GestorEstudiantes {

    private ArrayList<Estudiante> estudiantes;

    public GestorEstudiantes() {
        estudiantes = new ArrayList<>();
    }

    //registrar estudiante
    public void registrar(Estudiante estudiante) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equals(estudiante.getCodigo())) {
                throw new IllegalArgumentException(
                        "Estudiante ya existe");
            }

        }
        estudiantes.add(estudiante);
    }

    public void listar() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante e : estudiantes) {
            e.mostrarDatos();
        }
    }

    public Estudiante buscar(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    public void registrarCalificacion(String codigo, double calificacion) {

        Estudiante estudiante = buscar(codigo);

        if (estudiante == null) {
            throw new IllegalArgumentException(
                    "No existe un estudiante con el código: " + codigo
            );
        }
        estudiante.registrarCalificacion(calificacion);
        
    }

}
