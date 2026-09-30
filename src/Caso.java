import java.util.ArrayList;

public class Caso {
    private static final int MAX_UBICACIONES = 5;

    private final String nombre;
    private final String codigo;
    private final String detectiveResponsable;
    private final Ubicacion[] ubicaciones;
    private final ArrayList<Pista> pistas;

    public Caso(String nombre, String codigo, String detectiveResponsable) {
        validarTexto(nombre, "El nombre del caso");
        validarTexto(codigo, "El codigo del caso");
        validarTexto(detectiveResponsable, "El detective responsable");
        this.nombre = nombre.trim();
        this.codigo = codigo.trim();
        this.detectiveResponsable = detectiveResponsable.trim();
        this.ubicaciones = new Ubicacion[MAX_UBICACIONES];
        this.pistas = new ArrayList<Pista>();
    }

    public void registrarUbicacion(int posicion, Ubicacion ubicacion) {
        validarPosicion(posicion);
        if (ubicaciones[posicion] != null) {
            throw new IllegalArgumentException("La posicion seleccionada ya esta ocupada.");
        }
        if (ubicacion == null) {
            throw new IllegalArgumentException("La ubicacion no puede ser null.");
        }
        ubicaciones[posicion] = ubicacion;
    }

    public Ubicacion obtenerUbicacion(int posicion) {
        validarPosicion(posicion);
        if (ubicaciones[posicion] == null) {
            throw new IllegalArgumentException("La posicion esta vacia.");
        }
        return ubicaciones[posicion];
    }

    public void modificarUbicacion(int posicion, int riesgo, String estado) {
        obtenerUbicacion(posicion).actualizar(riesgo, estado);
    }

    public void descartarUbicacion(int posicion) {
        obtenerUbicacion(posicion);
        ubicaciones[posicion] = null;
    }

    public void mostrarUbicaciones() {
        boolean hayUbicaciones = false;
        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                System.out.println("Posicion " + i + ": " + ubicaciones[i]);
                hayUbicaciones = true;
            }
        }
        if (!hayUbicaciones) {
            System.out.println("No hay ubicaciones registradas.");
        }
    }

    public void registrarPista(Pista pista) {
        if (pista == null) {
            throw new IllegalArgumentException("La pista no puede ser null.");
        }
        if (buscarPista(pista.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe una pista con ese codigo.");
        }
        pistas.add(pista);
    }

    public Pista buscarPista(String codigoPista) {
        validarTexto(codigoPista, "El codigo de la pista");
        for (Pista pista : pistas) {
            if (pista.getCodigo().equalsIgnoreCase(codigoPista.trim())) {
                return pista;
            }
        }
        return null;
    }

    public Pista obtenerPista(String codigoPista) {
        Pista pista = buscarPista(codigoPista);
        if (pista == null) {
            throw new IllegalArgumentException("No se encontro una pista con ese codigo.");
        }
        return pista;
    }

    public void modificarPista(String codigoPista, String nuevoCodigo, String descripcion, String tipo,
                               int importancia, int confiabilidad) {
        Pista pista = obtenerPista(codigoPista);
        Pista pistaConNuevoCodigo = buscarPista(nuevoCodigo);
        if (pistaConNuevoCodigo != null && pistaConNuevoCodigo != pista) {
            throw new IllegalArgumentException("Ya existe una pista con el nuevo codigo.");
        }
        pista.actualizar(nuevoCodigo, descripcion, tipo, importancia, confiabilidad);
    }

    public void eliminarPista(String codigoPista) {
        pistas.remove(obtenerPista(codigoPista));
    }

    public void mostrarPistas() {
        if (pistas.isEmpty()) {
            System.out.println("No hay pistas registradas.");
            return;
        }
        for (Pista pista : pistas) {
            System.out.println(pista);
        }
    }

    public int cantidadUbicaciones() {
        int cantidad = 0;
        for (Ubicacion ubicacion : ubicaciones) {
            if (ubicacion != null) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int espaciosDisponibles() {
        return MAX_UBICACIONES - cantidadUbicaciones();
    }

    public Ubicacion ubicacionMayorRiesgo() {
        Ubicacion mayor = null;
        for (Ubicacion ubicacion : ubicaciones) {
            if (ubicacion != null && (mayor == null
                    || ubicacion.getNivelRiesgo() > mayor.getNivelRiesgo())) {
                mayor = ubicacion;
            }
        }
        return mayor;
    }

    public int cantidadPistas() {
        return pistas.size();
    }

    public Pista pistaMayorImportancia() {
        Pista mayor = null;
        for (Pista pista : pistas) {
            if (mayor == null || pista.getNivelImportancia() > mayor.getNivelImportancia()) {
                mayor = pista;
            }
        }
        return mayor;
    }

    public Pista pistaMayorConfiabilidad() {
        Pista mayor = null;
        for (Pista pista : pistas) {
            if (mayor == null || pista.getNivelConfiabilidad() > mayor.getNivelConfiabilidad()) {
                mayor = pista;
            }
        }
        return mayor;
    }

    public double promedioImportancia() {
        if (pistas.isEmpty()) {
            throw new IllegalArgumentException("No hay pistas para calcular el promedio.");
        }
        int suma = 0;
        for (Pista pista : pistas) {
            suma += pista.getNivelImportancia();
        }
        return (double) suma / pistas.size();
    }

    public String getResumen() {
        return nombre + " (" + codigo + ") - Detective: " + detectiveResponsable;
    }

    private static void validarPosicion(int posicion) {
        if (posicion < 0 || posicion >= MAX_UBICACIONES) {
            throw new IllegalArgumentException("La posicion debe estar entre 0 y 4.");
        }
    }

    private static void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacio.");
        }
    }
}
