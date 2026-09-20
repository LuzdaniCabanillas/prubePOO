package sistema;

import java.util.Scanner;

public class SistemaEstudiante {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GestorEstudiantes gestorEst = new GestorEstudiantes();

        // DATOS DE PRUEBA
        Estudiante estudiante1 = new Estudiante(
                "N12345678",
                "Juan Pérez",
                "juan@gmail.com",
                "Programación",
                "Ingeniería de Sistemas"
        );

        Estudiante estudiante2 = new Estudiante(
                "N87654321",
                "María López",
                "maria@gmail.com",
                "Programación",
                "Ingeniería de Sistemas"
        );

        gestorEst.registrar(estudiante1);
        gestorEst.registrar(estudiante2);

        gestorEst.registrarCalificacion("N12345678", 15);
        gestorEst.registrarCalificacion("N12345678", 18);
        gestorEst.registrarCalificacion("N12345678", 17);

        gestorEst.registrarCalificacion("N87654321", 19);
        gestorEst.registrarCalificacion("N87654321", 16);
        gestorEst.registrarCalificacion("N87654321", 18);

        //MENU
        int opcion;

        do {

            System.out.println("\n==============================");
            System.out.println("   SISTEMA DE ESTUDIANTES");
            System.out.println("==============================");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante por código");
            System.out.println("4. Registrar calificación");
            System.out.println("5. Salir");
            System.out.println("==============================");
            System.out.print("Seleccione una opción: ");

            try {

                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {

                    case 1:
                        registrarEstudiante(teclado, gestorEst);
                        break;

                    case 2:
                        System.out.println("\n=== LISTA DE ESTUDIANTES ===");
                        gestorEst.listar();
                        break;

                    case 3:
                        buscarEstudiante(teclado, gestorEst);
                        break;

                    case 4:
                        registrarCalificacion(teclado, gestorEst);
                        break;

                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opción no válida.");

                }

            } catch (NumberFormatException e) {

                System.out.println("Error: debe ingresar un número.");

                opcion = 0;
            }

        } while (opcion != 5);

        teclado.close();
    }

    // REGISTRAR ESTUDIANTE
    public static void registrarEstudiante(
            Scanner teclado,
            GestorEstudiantes gestorEst) {

        try {

            System.out.println("\n=== REGISTRAR ESTUDIANTE ===");

            String codigo;
            while (true) {

                System.out.print("Código (N + 8 números): ");
                codigo = teclado.nextLine().trim();

                if (codigo.isEmpty()) {
                    System.out.println("Error: el código no puede estar vacío.");
                    continue;
                }

                if (!codigo.matches("N[0-9]{8}")) {
                    System.out.println(
                            "Error: el código debe iniciar con N y tener 8 números."
                    );
                    continue;
                }

                if (gestorEst.buscar(codigo) != null) {
                    System.out.println(
                            "Error: ya existe un estudiante con el código: "
                            + codigo
                    );
                    continue;
                }

                break;
            }

            String nombre;

            while (true) {

                System.out.print("Nombre: ");
                nombre = teclado.nextLine().trim();

                if (nombre.isEmpty()) {
                    System.out.println("Error: el nombre no puede estar vacío.");
                    continue;
                }

                break;
            }

            String correo = codigo + "@upn.pe";
            System.out.println(
                    "Correo generado automáticamente: "
                    + correo
            );

            /*
            System.out.print("Correo: ");
            String correo = teclado.nextLine();*/
            
            String curso;

            while (true) {

                System.out.print("Curso: ");
                curso = teclado.nextLine().trim();

                if (curso.isEmpty()) {
                    System.out.println("Error: el curso no puede estar vacío.");
                    continue;
                }

                break;
            }

            String carrera = "ING DE SISTEMAS COMPUTACIONALES";

            
            Estudiante estudiante = new Estudiante(
                    codigo,
                    nombre,
                    correo,
                    curso,
                    carrera
            );

            
            gestorEst.registrar(estudiante);

            System.out.println("\nEstudiante registrado correctamente.");

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage()
            );
        }
    }

    // BUSCAR ESTUDIANTE
    public static void buscarEstudiante(
            Scanner teclado,
            GestorEstudiantes gestorEst) {

        System.out.println("\n=== BUSCAR ESTUDIANTE ===");

        System.out.print("Ingrese el código: ");
        String codigo = teclado.nextLine();

        Estudiante encontrado = gestorEst.buscar(codigo);

        if (encontrado != null) {

            System.out.println("\nEstudiante encontrado:");
            encontrado.mostrarDatos();

        } else {

            System.out.println(
                    "No existe un estudiante con el código: " + codigo
            );
        }
    }

    // REGISTRAR CALIFICACIÓN
    public static void registrarCalificacion(
            Scanner teclado,
            GestorEstudiantes gestorEst) {

        try {

            System.out.println("\n=== REGISTRAR CALIFICACIÓN ===");

            String codigo;

            while (true) {
                System.out.print("Código del estudiante: ");
                codigo = teclado.nextLine().trim();

                if (codigo.isEmpty()) {
                    System.out.println(
                            "Error: el código no puede estar vacío."
                    );
                    continue;
                }

                if (!codigo.matches("N[0-9]{8}")) {
                    System.out.println(
                            "Error: el código debe iniciar con N y tener 8 números."
                    );
                    continue;
                }

                if (gestorEst.buscar(codigo) == null) {
                    System.out.println(
                            "Error: no existe un estudiante con el código: "
                            + codigo
                    );
                    continue;
                }

                
                break;

            }

            double calificacion;

            while (true) {

                System.out.print("Calificación (0 - 20): ");

                String entrada = teclado.nextLine().trim();

                if (entrada.isEmpty()) {
                    System.out.println(
                            "Error: la calificación no puede estar vacía."
                    );
                    continue;
                }

                try {

                    calificacion = Double.parseDouble(entrada);

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Error: debe ingresar una calificación numérica."
                    );
                    continue;
                }

                if (calificacion < 0 || calificacion > 20) {
                    System.out.println(
                            "Error: la calificación debe estar entre 0 y 20."
                    );
                    continue;
                }

                break;
            }

            gestorEst.registrarCalificacion(
                    codigo,
                    calificacion
            );

            System.out.println(
                    "Calificación registrada correctamente."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
    
}