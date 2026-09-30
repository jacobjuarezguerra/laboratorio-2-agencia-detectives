import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Caso caso = solicitarCasoInicial();
        int opcion = 0;

        while (opcion != 13) {
            mostrarMenu(caso);
            try {
                opcion = leerEntero("Seleccione una opcion: ");
                switch (opcion) {
                    case 1:
                        caso = solicitarCaso();
                        System.out.println("Caso creado correctamente.");
                        break;
                    case 2:
                        registrarUbicacion(caso);
                        break;
                    case 3:
                        caso.mostrarUbicaciones();
                        break;
                    case 4:
                        consultarUbicacion(caso);
                        break;
                    case 5:
                        modificarUbicacion(caso);
                        break;
                    case 6:
                        descartarUbicacion(caso);
                        break;
                    case 7:
                        registrarPista(caso);
                        break;
                    case 8:
                        caso.mostrarPistas();
                        break;
                    case 9:
                        buscarPista(caso);
                        break;
                    case 10:
                        modificarPista(caso);
                        break;
                    case 11:
                        eliminarPista(caso);
                        break;
                    case 12:
                        mostrarReporte(caso);
                        break;
                    case 13:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un numero entero.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        SCANNER.close();
    }

    private static Caso solicitarCasoInicial() {
        System.out.println("=== REGISTRO DEL CASO ===");
        while (true) {
            try {
                return solicitarCaso();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + " Intente nuevamente.");
            }
        }
    }

    private static Caso solicitarCaso() {
        String nombre = leerTexto("Nombre del caso: ");
        String codigo = leerTexto("Codigo de identificacion: ");
        String detective = leerTexto("Detective responsable: ");
        return new Caso(nombre, codigo, detective);
    }

    private static void registrarUbicacion(Caso caso) {
        int posicion = leerEntero("Posicion del arreglo (0-4): ");
        String codigo = leerTexto("Codigo: ");
        String nombre = leerTexto("Nombre: ");
        String direccion = leerTexto("Direccion o descripcion: ");
        int riesgo = leerEntero("Nivel de riesgo (1-10): ");
        String estado = leerTexto("Estado: ");
        caso.registrarUbicacion(posicion, new Ubicacion(codigo, nombre, direccion, riesgo, estado));
        System.out.println("Ubicacion registrada.");
    }

    private static void consultarUbicacion(Caso caso) {
        int posicion = leerEntero("Posicion a consultar (0-4): ");
        System.out.println(caso.obtenerUbicacion(posicion));
    }

    private static void modificarUbicacion(Caso caso) {
        int posicion = leerEntero("Posicion a modificar (0-4): ");
        int riesgo = leerEntero("Nuevo nivel de riesgo (1-10): ");
        String estado = leerTexto("Nuevo estado: ");
        caso.modificarUbicacion(posicion, riesgo, estado);
        System.out.println("Ubicacion modificada.");
    }

    private static void descartarUbicacion(Caso caso) {
        int posicion = leerEntero("Posicion a descartar (0-4): ");
        caso.descartarUbicacion(posicion);
        System.out.println("Ubicacion descartada.");
    }

    private static void registrarPista(Caso caso) {
        caso.registrarPista(solicitarPista());
        System.out.println("Pista registrada.");
    }

    private static Pista solicitarPista() {
        String codigo = leerTexto("Codigo: ");
        String descripcion = leerTexto("Descripcion: ");
        String tipo = leerTexto("Tipo de evidencia: ");
        int importancia = leerEntero("Nivel de importancia (1-10): ");
        int confiabilidad = leerEntero("Nivel de confiabilidad (0-100): ");
        return new Pista(codigo, descripcion, tipo, importancia, confiabilidad);
    }

    private static void buscarPista(Caso caso) {
        String codigo = leerTexto("Codigo de la pista: ");
        Pista pista = caso.buscarPista(codigo);
        System.out.println(pista == null ? "Pista no encontrada." : pista);
    }

    private static void modificarPista(Caso caso) {
        String codigo = leerTexto("Codigo de la pista a modificar: ");
        String nuevoCodigo = leerTexto("Nuevo codigo: ");
        String descripcion = leerTexto("Nueva descripcion: ");
        String tipo = leerTexto("Nuevo tipo de evidencia: ");
        int importancia = leerEntero("Nueva importancia (1-10): ");
        int confiabilidad = leerEntero("Nueva confiabilidad (0-100): ");
        caso.modificarPista(codigo, nuevoCodigo, descripcion, tipo, importancia, confiabilidad);
        System.out.println("Pista modificada.");
    }

    private static void eliminarPista(Caso caso) {
        String codigo = leerTexto("Codigo de la pista a eliminar: ");
        caso.eliminarPista(codigo);
        System.out.println("Pista eliminada.");
    }

    private static void mostrarReporte(Caso caso) {
        System.out.println("\n=== REPORTE DE INVESTIGACION ===");
        System.out.println("Ubicaciones registradas: " + caso.cantidadUbicaciones());
        System.out.println("Espacios disponibles: " + caso.espaciosDisponibles());
        Ubicacion peligrosa = caso.ubicacionMayorRiesgo();
        System.out.println("Ubicacion con mayor riesgo: "
                + (peligrosa == null ? "Ninguna" : peligrosa));
        System.out.println("Pistas registradas: " + caso.cantidadPistas());
        if (caso.cantidadPistas() == 0) {
            System.out.println("Pista con mayor importancia: Ninguna");
            System.out.println("Pista con mayor confiabilidad: Ninguna");
            System.out.println("Promedio de importancia: No disponible");
        } else {
            System.out.println("Pista con mayor importancia: " + caso.pistaMayorImportancia());
            System.out.println("Pista con mayor confiabilidad: " + caso.pistaMayorConfiabilidad());
            System.out.printf("Promedio de importancia: %.2f%n", caso.promedioImportancia());
        }
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            throw e;
        } finally {
            // Siempre limpia la linea, tanto en lecturas correctas como incorrectas.
            SCANNER.nextLine();
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return SCANNER.nextLine();
    }

    private static void mostrarMenu(Caso caso) {
        System.out.println("\n=== AGENCIA DE DETECTIVES ===");
        System.out.println("Caso actual: " + caso.getResumen());
        System.out.println("1. Nuevo caso");
        System.out.println("2. Registrar ubicacion");
        System.out.println("3. Consultar ubicaciones");
        System.out.println("4. Consultar una ubicacion");
        System.out.println("5. Modificar ubicacion");
        System.out.println("6. Descartar ubicacion");
        System.out.println("7. Registrar pista");
        System.out.println("8. Consultar pistas");
        System.out.println("9. Buscar pista");
        System.out.println("10. Modificar pista");
        System.out.println("11. Eliminar pista");
        System.out.println("12. Mostrar reporte de investigacion");
        System.out.println("13. Salir");
    }
}
