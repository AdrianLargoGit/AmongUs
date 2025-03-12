public class Impostor {
    private Clave[] claves;
    private int numClaves;
    private Tripulante tripulanteImpostor;

    public Impostor(int numTripulantes, Tripulante tripulanteImpostor) {
        // TODO 1 Completar constructor inicializando convenientemente los atributos
        this.tripulanteImpostor = tripulanteImpostor;
        claves = new Clave[numTripulantes];
        claves[0] = tripulanteImpostor.getClave();
        numClaves++;
    }

    public int getIdentificador(){
        return tripulanteImpostor.getIdentificador();
    }

    public void robarClave(Tripulante tripulante) {
        // TODO 2 Robar la clave del tripulante
        claves[numClaves] = tripulante.getClave();
        numClaves++;
        System.out.println("Has obtenido una nueva clave del reactor. Ya tienes las siguientes:");
        this.mostrarClaves();
    }

    private void mostrarClaves() {
        // TODO 3 Visualizar las claves que tiene el impostor en su poder
        for (int i = 0; i<numClaves ; i++){
            System.out.println(claves[i]);
        }
    }

    public boolean quiereJugar() {
        return Teclado.leerBoolean("Quieres tratar de adivinar la clave del reactor (S/N)? ");
    }

    public boolean adivinaClave(Clave claveReactor) {
        boolean resultado;
        String claveLetras = Teclado.leerString("Introduce la parte alfab�tica de la clave: ");
        String claveDigitos = Teclado.leerString("Introduce la parte num�rica de la clave: ");
        resultado = claveReactor.equals(new Clave(claveLetras, claveDigitos));
        if (!resultado) {
            System.out.println("Has fallado. La clave del reactor era " + claveReactor);
        }
        return resultado;
    }

}
