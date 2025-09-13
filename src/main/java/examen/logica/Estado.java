package examen.logica;

public enum Estado {
    ABIERTA("Abierta"),
    EN_PROGRESO("En Progreso"),
    EN_REVISION("En Revisión"),
    RESUELTA("Resuelta");
    private final String descripcion;
    private Estado(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
}
