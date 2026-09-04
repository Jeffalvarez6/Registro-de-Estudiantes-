import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaEstudiantes lista = new ListaEstudiantes();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========== SISTEMA DE GESTIÓN DE ESTUDIANTES ==========");
            System.out.println("1. Mostrar lista de estudiantes");
            System.out.println("2. Insertar al inicio");
            System.out.println("3. Insertar al final");
            System.out.println("4. Buscar por código");
            System.out.println("5. Obtener posición por código");
            System.out.println("6. Eliminar por código");
            System.out.println("7. Ordenar estudiantes por promedio (Merge Sort)");
            System.out.println("8. Ejecutar prueba experimental de rendimiento (Carga masiva)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n--- LISTA ACTUAL DE ESTUDIANTES ---");
                    lista.mostrarLista();
                    break;

                case 2:
                    System.out.println("\n--- INSERTAR AL INICIO ---");
                    lista.insertarInicio(leerEstudiante(scanner));
                    System.out.println("Estudiante insertado al inicio con éxito.");
                    break;

                case 3:
                    System.out.println("\n--- INSERTAR AL FINAL ---");
                    lista.insertarFinal(leerEstudiante(scanner));
                    System.out.println("Estudiante insertado al final con éxito.");
                    break;

                case 4:
                    System.out.print("\nIngrese el código a buscar: ");
                    String codBuscar = scanner.nextLine();
                    Estudiante encontrado = lista.buscarPorCodigo(codBuscar);
                    if (encontrado != null) {
                        System.out.println("Estudiante encontrado: " + encontrado);
                    } else {
                        System.out.println("Estudiante con código " + codBuscar + " no existe.");
                    }
                    break;

                case 5:
                    System.out.print("\nIngrese el código para consultar posición: ");
                    String codPos = scanner.nextLine();
                    int pos = lista.obtenerPosicion(codPos);
                    if (pos != -1) {
                        System.out.println("El estudiante está en la posición (índice): " + pos);
                    } else {
                        System.out.println("Estudiante no encontrado. Posición: " + pos);
                    }
                    break;

                case 6:
                    System.out.print("\nIngrese el código del estudiante a eliminar: ");
                    String codEliminar = scanner.nextLine();
                    boolean eliminado = lista.eliminarPorCodigo(codEliminar);
                    if (eliminado) {
                        System.out.println("Estudiante eliminado correctamente.");
                    } else {
                        System.out.println("Error: No se encontró ningún estudiante con ese código o la lista está vacía.");
                    }
                    break;

                case 7:
                    System.out.println("\n--- ORDENANDO POR PROMEDIO (MAYOR A MENOR) ---");
                    lista.ordenarPorPromedio();
                    System.out.println("Lista ordenada correctamente.");
                    lista.mostrarLista();
                    break;

                case 8:
                    ejecutarPruebaRendimiento(scanner);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static Estudiante leerEstudiante(Scanner sc) {
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Programa: ");
        String programa = sc.nextLine();
        System.out.print("Promedio (ejemplo 4,2 o 4.2): ");
        double promedio = sc.nextDouble();
        sc.nextLine(); // Limpiar buffer
        return new Estudiante(codigo, nombre, programa, promedio);
    }

    private static void ejecutarPruebaRendimiento(Scanner sc) {
        System.out.print("\nIngrese la cantidad de registros a generar para la prueba (ej: 50000): ");
        int n = sc.nextInt();
        sc.nextLine();

        ListaEstudiantes listaMasiva = new ListaEstudiantes();
        Random rnd = new Random();

        System.out.println("Generando e insertando " + n + " registros...");
        long inicioInsert = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            listaMasiva.insertarFinal(new Estudiante("E" + i, "Estudiante " + i, "Sistemas", 1.0 + (5.0 - 1.0) * rnd.nextDouble()));
        }
        long finInsert = System.currentTimeMillis();
        System.out.println("Tiempo de inserción: " + (finInsert - inicioInsert) + " ms.");

        System.out.println("Ejecutando Merge Sort en " + n + " nodos...");
        long inicioSort = System.currentTimeMillis();
        listaMasiva.ordenarPorPromedio();
        long finSort = System.currentTimeMillis();
        System.out.println("Tiempo de ordenamiento: " + (finSort - inicioSort) + " ms.");
    }
}