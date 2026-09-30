public class Pista {
    private String codigo;
    private String descripcion;
    private String tipoEvidencia;
    private int nivelImportancia;
    private int nivelConfiabilidad;

    public Pista(String codigo, String descripcion, String tipoEvidencia,
                 int nivelImportancia, int nivelConfiabilidad) {
        validarTexto(codigo, "El codigo");
        validarDatos(descripcion, tipoEvidencia, nivelImportancia, nivelConfiabilidad);
        this.codigo = codigo.trim();
        this.descripcion = descripcion.trim();
        this.tipoEvidencia = tipoEvidencia.trim();
        this.nivelImportancia = nivelImportancia;
        this.nivelConfiabilidad = nivelConfiabilidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getNivelImportancia() {
        return nivelImportancia;
    }

    public int getNivelConfiabilidad() {
        return nivelConfiabilidad;
    }

    public void actualizar(String codigo, String descripcion, String tipoEvidencia,
                           int nivelImportancia, int nivelConfiabilidad) {
        validarTexto(codigo, "El codigo");
        validarDatos(descripcion, tipoEvidencia, nivelImportancia, nivelConfiabilidad);
        this.codigo = codigo.trim();
        this.descripcion = descripcion.trim();
        this.tipoEvidencia = tipoEvidencia.trim();
        this.nivelImportancia = nivelImportancia;
        this.nivelConfiabilidad = nivelConfiabilidad;
    }

    private static void validarDatos(String descripcion, String tipoEvidencia,
                                     int nivelImportancia, int nivelConfiabilidad) {
        validarTexto(descripcion, "La descripcion");
        validarTexto(tipoEvidencia, "El tipo de evidencia");
        if (nivelImportancia < 1 || nivelImportancia > 10) {
            throw new IllegalArgumentException("La importancia debe estar entre 1 y 10.");
        }
        if (nivelConfiabilidad < 0 || nivelConfiabilidad > 100) {
            throw new IllegalArgumentException("La confiabilidad debe estar entre 0 y 100.");
        }
    }

    private static void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacio.");
        }
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + ", descripcion: " + descripcion + ", tipo: " + tipoEvidencia
                + ", importancia: " + nivelImportancia + ", confiabilidad: " + nivelConfiabilidad + "%";
    }
}
