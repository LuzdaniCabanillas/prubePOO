<h1 align="center"> Caso integrador 🗃️</h1>
<h1 align="center"> Sistema de Gestión Estudiantes</h1>

Una institución educativa necesita desarrollar un sistema que permita registrar estudiantes y administrar sus calificaciones.

🧰 Herramientas usadas

[![My Skills](https://skillicons.dev/icons?i=java,git,idea,github,&theme=light)](https://skillicons.dev)

---
# Guía de Análisis y Diseño de Software: Sistema de Gestión de Estudiantes

## 1. Descripción del Problema
Una institución educativa requiere una solución de software que permita la administración eficiente de sus estudiantes y el registro de sus calificaciones numéricas (en escala de 0 a 20). El sistema cuenta con un menú interactivo por consola que permite:
* Registrar nuevos estudiantes solicitando código, nombre, curso y carrera.
* Autogenerar el correo institucional (`codigo@upn.pe`).
* Listar todos los estudiantes registrados junto con sus datos y promedio actual.
* Buscar estudiantes específicos mediante su código.
* Registrar múltiples calificaciones numéricas por estudiante y calcular su promedio general.

Además, la aplicación previene registros duplicados, valida la estructura del código universitario (patrón `N` seguido de 8 dígitos) e implementa un control exhaustivo de excepciones de entrada por teclado para mantener la estabilidad del programa.

---

## 2. Identificación de Objetos
Basado en el código fuente implementado dentro del paquete `sistema`, se identifican las siguientes entidades principales:

* **`Estudiante`**: Representa la entidad individual del alumno. Encapsula los atributos personales (código, nombre, correo, curso, carrera) y su historial de calificaciones (`ArrayList<Double>`). Es responsable de calcular su propio promedio y mostrar sus datos.
* **`GestorEstudiantes`**: Actúa como la capa lógica/controlador del sistema. Se encarga de administrar la colección general de alumnos (`ArrayList<Estudiante>`), validar la unicidad de los registros por código, realizar búsquedas y delegar el registro de notas.
* **`SistemaEstudiante`**: Representa la vista/interfaz de usuario por consola (`Main`). Maneja la interacción por menú, la captura de datos con `Scanner` y el control inmediato de errores de formato de entrada.

---

## 3. Atributos de Cada Clase

### Clase: `Estudiante`
| Atributo | Tipo de Dato | Descripción / Restricciones |
| :--- | :--- | :--- |
| `codigo` | `String` | Identificador único del estudiante. Debe cumplir con la expresión regular `N[0-9]{8}`. |
| `nombre` | `String` | Nombre completo del estudiante. |
| `correo` | `String` | Correo institucional autogenerado (`codigo@upn.pe`). |
| `curso` | `String` | Nombre de la asignatura que cursa. |
| `carrera` | `String` | Nombre de la carrera profesional. |
| `calificaciones` | `ArrayList<Double>` | Lista dinámica de notas numéricas individuales (entre 0 y 20). |

### Clase: `GestorEstudiantes`
| Atributo | Tipo de Dato | Descripción |
| :--- | :--- | :--- |
| `estudiantes` | `ArrayList<Estudiante>` | Colección de todos los estudiantes registrados en el sistema. |

---

## 4. Responsabilidades y Métodos

### Clase: `Estudiante`
* **Responsabilidades:**
    * Validar el formato del código en el constructor (`N` + 8 números).
    * Almacenar datos personales y lista de notas.
    * Validar que la calificación a agregar esté en el rango $[0, 20]$.
    * Calcular y retornar el promedio aritmético de sus notas.
    * Imprimir sus datos formateados en consola.
* **Métodos principales:**
    * `Estudiante(codigo, nombre, correo, curso, carrera)`: Constructor con validación de código por Regex.
    * `registrarCalificacion(double calificacion)`: Agrega una nota validando rango $[0, 20]$.
    * `double calcularPromedio()`: Retorna la suma de notas dividida entre la cantidad (o `0` si está vacía).
    * `mostrarDatos()`: Imprime en consola el resumen de información del alumno.
    * Getters y Setters correspondientes.

### Clase: `GestorEstudiantes`
* **Responsabilidades:**
    * Evitar el registro de estudiantes con códigos duplicados.
    * Buscar estudiantes secuencialmente por código.
    * Delegar el registro de notas al estudiante correspondiente.
    * Recorrer y listar todos los estudiantes activos.
* **Métodos principales:**
    * `registrar(Estudiante estudiante)`: Agrega al estudiante previa comprobación de duplicado.
    * `listar()`: Recorre e invoca `mostrarDatos()` para cada integrante.
    * `Estudiante buscar(String codigo)`: Retorna la referencia del estudiante o `null` si no existe.
    * `registrarCalificacion(String codigo, double calificacion)`: Busca al alumno y le asigna la nota.

### Clase: `SistemaEstudiante`
* **Responsabilidades:**
    * Controlar el flujo general mediante el menú interactivo (`do-while` / `switch`).
    * Validar entradas nulas o con mal formato desde la consola (`Scanner`).
    * Preparar datos iniciales de prueba (Mock Data).

---

## 5. Reglas de Negocio
1. **Formato de Código Único:** Todo código debe iniciar con la letra `N` seguida exactamente de 8 dígitos numéricos (ej. `N12345678`).
2. **Unicidad de Registros:** No se permite guardar dos estudiantes con el mismo código.
3. **Generación Automática de Correo:** El correo institucional se autogenera agregando `@upn.pe` al código del estudiante.
4. **Rango de Notas:** Las calificaciones deben ser valores numéricos dentro del intervalo continuo de $0.0$ a $20.0$.
5. **Promedio por Defecto:** Si un estudiante no posee calificaciones registradas, su promedio reportado es $0.0$.

---

## 6. Colección Seleccionada y Justificación

### Colección: `ArrayList<T>`
* **Uso en `GestorEstudiantes`:** Se utilizó `ArrayList<Estudiante>` para almacenar la lista de alumnos.
* **Uso en `Estudiante`:** Se utilizó `ArrayList<Double>` para la lista de calificaciones por alumno.
* **Justificación:**
    * **Acceso Secuencial Dinámico:** Permite agregar un número indeterminado de elementos de forma elástica sin necesidad de definir un tamaño fijo estático.
    * **Facilidad de Iteración:** Facilita la aplicación de bucles *for-each* tanto para recorrer la lista global de alumnos como para realizar la sumatoria de calificaciones en el cálculo del promedio.
    * **Simplicidad:** Se amolda a la estructura básica del proyecto requerida por el entorno académico.

---

## 7. Errores Potenciales y Estrategia de Manejo

| Situación de Error | Causa / Excepción | Estrategia de Manejo Implementada |
| :--- | :--- | :--- |
| **Formato de Código Incorrecto** | Regex `N[0-9]{8}` no coincide. | Lanza `IllegalArgumentException` en la entidad / Bucle `while` en consola que exige reingreso. |
| **Código Duplicado** | Intento de registrar un código existente en el `GestorEstudiantes`. | Lanza `IllegalArgumentException` / El menú detecta la existencia previa con `gestorEst.buscar(codigo) != null`. |
| **Nota Fuera de Rango** | Valor menor a 0 o mayor a 20. | Lanza `IllegalArgumentException` / Validación previa en consola antes de invocar la lógica. |
| **Ingreso de Texto por Número** | Inserción de letras al solicitar opción de menú o nota. | Captura de `NumberFormatException` con `try-catch` informando al usuario sin pausar la aplicación. |
| **Entradas Vacías** | Presionar *Enter* sin ingresar caracteres. | Validación con `.trim().isEmpty()` pidiendo de nuevo el dato dentro de bucles `while`. |

---

## 8. Implementación Funcional en Java

El código del proyecto se encuentra estructurado modularmente en el paquete `sistema`:

* `Estudiante.java`: Encapsulamiento de datos, validación básica y lógica de promedios.
* `GestorEstudiantes.java`: Métodos CRUD básicos sobre la colección `ArrayList`.
* `SistemaEstudiante.java`: Interfaz por consola con gestión completa del flujo de usuario.

---

## 9. Pruebas Básicas

A continuación se resumen los casos de prueba ejecutados dentro del método `main`:

1. **Carga Inicial de Datos de Prueba:**
    * Registro de `N12345678` (Juan Pérez) y `N87654321` (María López).
    * Registro de calificaciones:
        * Juan Pérez: $15, 18, 17
          ightarrow$ Promedio Esperado: $16.7$.
        * María López: $19, 16, 18
          ightarrow$ Promedio Esperado: $17.7$.
2. **Validación de Código:**
    * Intento de ingresar `12345` o `ABC12345` $
      ightarrow$ El sistema rechaza el valor notificando el formato requerido (`N` + 8 números).
3. **Validación de Nota:**
    * Intento de registrar nota `25` o `-5` $
      ightarrow$ El sistema muestra mensaje de error y solicita el valor en el rango $[0, 20]$.
4. **Búsqueda Inexistente:**
    * Consulta de código `N00000000` $
      ightarrow$ El sistema devuelve el mensaje *"No existe un estudiante con el código: N00000000"*.