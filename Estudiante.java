public class Estudiante {
    private String codigo;
    private String nombre;
    private String programa;
    private double promedio;

    public Estudiante(String codigo, String nombre, String programa, double promedio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.programa = programa;
        this.promedio = promedio;
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

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return String.format("Código: %-8s | Nombre: %-20s | Programa: %-15s | Promedio: %.2f", 
                             codigo, nombre, programa, promedio);
    }
}