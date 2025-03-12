public class Tripulante {
    private int identificador;
    private Clave clave;

    public Tripulante(int identificador, Clave clave) {
        this.identificador = identificador;
        this.clave = clave;
    }

    public int getIdentificador() {
        return identificador;
    }

    public Clave getClave() {
        return clave;
    }

    public String toString() {
        return "tripulante " + this.identificador + " clave: " + this.clave;
    }
}
