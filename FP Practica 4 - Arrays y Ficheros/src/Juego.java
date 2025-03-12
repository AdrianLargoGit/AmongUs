import java.io.*;

public class Juego {
    //Realizado por Adrián Largo Monteagudo con matrícula BU0257
    private Clave claveReactor;
    private Tripulante[] tripulacion;
    private final int TOTAL_TRIPULANTES;
    private int numTripulantes;
    private Impostor impostor;

    public Juego(int numTripulantes, int longitudClave) {
        // TODO 4 inicializar convenientemente los atributos
        this.TOTAL_TRIPULANTES = numTripulantes;
        this.numTripulantes = numTripulantes;
        claveReactor = new Clave(longitudClave);
        tripulacion = new Tripulante[numTripulantes];
        for (int i = 0; i<tripulacion.length; i++){
            Tripulante tripulante = new Tripulante(100 +i, claveReactor.getDistorsionada());
            tripulacion[i] = tripulante;
        }
        impostor = new Impostor(numTripulantes, tripulacion[aleatorio(0, numTripulantes)]);
        System.out.println("Te escondes como tripulante " + impostor.getIdentificador() + ", que no te descubran");
    }

    private static int aleatorio(int minimo, int maximo) {
        return (int)(Math.random() * (maximo - minimo) + minimo);
    }

    public void jugar() {
        boolean finJuego = false;
        boolean explotada = false;
        do {
            System.out.println("\nLos tripulantes realizan sus actividades en la nave ...");
            this.atacarTripulante();
            if (impostor.quiereJugar()) {
                System.out.println("Iniciando autodestrucci�n ...");
                if (impostor.adivinaClave(claveReactor)) {
                    System.out.println("EXPLOT� LA NAVE. HAS TENIDO �XITO");
                    explotada = true;
                    finJuego = true;
                } else {
                    System.out.println("SUENAN LAS ALARMAS");
                    System.out.println("ERES EXPULSADO DE LA NAVE. FRACASASTE EN LA MISI�N");
                    this.expulsarImpostor();
                    finJuego = true;
                }
            } else {
                int idExpulsado = this.expulsarSospechoso();
                if (idExpulsado == impostor.getIdentificador()) {
                    System.out.println("ERES EXPULSADO DE LA NAVE. FRACASASTE EN LA MISI�N");
                    finJuego = true;
                }
            }
        } while (!finJuego && numTripulantes > 1);
        if (!finJuego) {
            System.out.println("TE HAS QUEDADO SOLO EN LA NAVE. TRIUNFASTE EN LA MISI�N");
        }
        if (!explotada) {
            boolean respuesta = Teclado.leerBoolean("�Guardar los datos de la nave (S/N)? ");
            if (respuesta) {
                String ruta = Teclado.leerString("Nombre del archivo: ");
                this.guardarEstadoNave(ruta);
            }
        }
    }

    public void atacarTripulante() {
        // TODO 5 El impostor ataca a un tripulante para robarle la clave.
        int aleatorio = aleatorio(0, numTripulantes);
        while (tripulacion[aleatorio].getIdentificador()== impostor.getIdentificador()) {
            aleatorio = aleatorio(0, numTripulantes);
        }
        System.out.println("De pronto, el tripulante " + tripulacion[aleatorio].getIdentificador() + " es atacado y eliminado");
        impostor.robarClave(tripulacion[aleatorio]);
        eliminarTripulante(aleatorio);
    }

    public void eliminarTripulante(int posicion) {
        // TODO 6 El tripulante que ocupa la posicion recibida en el array es eliminado.
        for (int i=0; i<numTripulantes; i++){
            if(i==posicion){
                numTripulantes--;
                for (int d=i; d<numTripulantes; d++){
                    tripulacion[d] = tripulacion[d+1];
                }
            }
        }
    }

    public void expulsarImpostor() {
        // TODO 7 Expulsar de la nave al tripulante impostor (lo eliminamos)
        for (int i=0; i<numTripulantes; i++){
            if(tripulacion[i].getIdentificador() == impostor.getIdentificador()){
                eliminarTripulante(i);
            }
        }
    }
    public int expulsarSospechoso() {
        // TODO 8 Los tripulantes de la nave se reunen e intentan determinar
        //        quien es el impostor para echarle de la nave.
        boolean expulsar = (aleatorio(0, 2) == 1);
        int identificador = -1;
        if (expulsar){
            int aleatorio = aleatorio(0, numTripulantes);
            identificador = tripulacion[aleatorio].getIdentificador();
            System.out.println("Se ha decido echar de la nave al tripulante " + identificador + " por sospechoso");
            eliminarTripulante(aleatorio);
        }
        else System.out.println("No se ha llegado a un acuerdo para expulsar a alguien");
        return identificador;
    }

    public void guardarEstadoNave(String ruta) {
        // TODO 9 Almacenar en un fichero el estado de la nave
        PrintWriter salida = null;
        try {
            File myFile = new File(ruta);
            salida = new PrintWriter(myFile);
            salida.print("Clave del reactor: ");
            salida.println(claveReactor);
            salida.print("Tripulacion de la nave: ");
            salida.println(TOTAL_TRIPULANTES);
            salida.print("Tripulantes eliminados: ");
            salida.println(TOTAL_TRIPULANTES - numTripulantes);
            salida.println("Tripulantes supervivientes: ");
            for (int i=0; i<numTripulantes; i++){
                salida.println("\t" + tripulacion[i]);
            }
        } catch(IOException ex) {
            ex.getMessage();
        } finally {
            if (salida != null) {
                salida.close();
            }
        }
    }

    public static void main(String[] args) {
        int numTripulantes = Teclado.leerEntero(5, 15,
                "�Cu�ntos tripulantes tendr� la nave (min=5 y max=15)? ");
        int longitudClave = Teclado.leerEntero(2, 4,
                "�Cu�l es el tama�o de cada parte de la clave del reactor (min=2 y max=4)? ");
        Juego juego = new Juego(numTripulantes, longitudClave);
        juego.jugar();
    }
}
