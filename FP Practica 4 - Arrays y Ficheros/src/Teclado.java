import java.util.Scanner;

public class Teclado {

	private static Scanner entrada = new Scanner(System.in);

	public static int leerEntero(int menor, int mayor, String mensaje) {
		int resultado = 0;
		boolean correcto = false;
		do {
			System.out.print(mensaje);
			if (entrada.hasNextInt()) {
				resultado = entrada.nextInt();
				correcto = resultado >= menor && resultado <= mayor;
			}
			entrada.nextLine();
		} while (!correcto);
		return resultado;
	}

	public static String leerString(String mensaje) {
		System.out.print(mensaje);
		String resultado = entrada.next();
		entrada.nextLine();
		return resultado;
	}


	public static boolean leerBoolean(String mensaje) {
		boolean resultado = false;;
		boolean correcto = false;
		do {
			System.out.print(mensaje);
			String cadena = entrada.nextLine();
			if (Character.toUpperCase(cadena.charAt(0)) == 'S') {
				resultado = true;
				correcto = true;
			} else if (Character.toUpperCase(cadena.charAt(0)) == 'N') {
				resultado = false;
				correcto = true;
			}
		} while (!correcto);
		return resultado;
	}

}
