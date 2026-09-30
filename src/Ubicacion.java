public class Ubicacion {
    private final String codigo;
    private final String nombre;
    private final String direccion;
    private int nivelRiesgo;
    private String estado;

    public Ubicacion(String codigo, String nombre, String direccion, int nivelRiesgo, String estado) {
        validarTexto(codigo, "El codigo");
        validarTexto(nombre, "El nombre");
        validarTexto(direccion, "La direccion");
        validarRiesgo(nivelRiesgo);
        validarTexto(estado, "El estado");
        this.codigo = codigo.trim();
        this.nombre = nombre.trim();
        this.direccion = direccion.trim();
        this.nivelRiesgo = nivelRiesgo;
        this.estado = estado.trim();
    }

    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void actualizar(int nivelRiesgo, String estado) {
        validarRiesgo(nivelRiesgo);
        validarTexto(estado, "El estado");
        this.nivelRiesgo = nivelRiesgo;
        this.estado = estado.trim();
    }

    private static void validarRiesgo(int nivelRiesgo) {
        if (nivelRiesgo < 1 || nivelRiesgo > 10) {
            throw new IllegalArgumentException("El nivel de riesgo debe estar entre 1 y 10.");
        }
    }

    private static void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacio.");
        }
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + ", nombre: " + nombre + ", direccion: " + direccion
                + ", riesgo: " + nivelRiesgo + ", estado: " + estado;
    }
}
