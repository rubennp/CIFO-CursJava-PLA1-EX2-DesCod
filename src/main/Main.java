package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
	
	private static final byte 
		ERRORNUM = -3, 		// código introducido contiene números no válidos
		ERRORLETRA = -2,	// código introducido contiene letras no válidas (Q, Z, Ñ, Ç)
		ERRORMEZCLA = -1, 	// código introducido contiene mezcla de números y letras
		OK = 0, 			// para el control de errores (bucle do-while de introducción)
		CODIFICA = 1, 		// código introducido contiene letras
		DESCODIFICA = 2; 	// código introducido contiene números

	static Secret secret = new Secret();
	
	/*
	 * Codifica palabras en código numérico o descodifica código numérico a posibles opciones.
	 * 
	 * <-> 2:ABC 3:DEF 4:GHI 5:JKL 6:MNO 7:PRS 8:TUV 9:WXY
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String codigo;
		byte queHacer = OK;
		
		// pide palabra o código numérico a usuario y comprueba errores
		do {
			System.out.println("===========================================================================");
			System.out.println("Introduce el código a codificar o descodificar (código numérico o palabra)");
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("  !-> No puedes utilizar la Q, la Ñ, la Z ni la Ç.");
			System.out.println("  !-> Los espacios y otros caracteres especiales seran eliminados.");
			System.out.println("  !-> Se pueden utilizar los números del 2 al 9.");
			System.out.println("===========================================================================");
			codigo = in.nextLine();
			queHacer = queHacer(codigo);
			
			// errores de entrada
			switch(queHacer) {
				case ERRORMEZCLA:
					System.out.println("-> ¡Debes introducir solo números o letras, no los dos tipos a la vez!");
					break;
				case ERRORNUM:
					System.out.println("-> ¡Debes introducir solo números del 2 al 9!");
					break;
				case ERRORLETRA:
					System.out.println("-> ¡No puedes usar Q, Ñ, Z, Ç!");
					break;
			}
		} while(queHacer < OK);
		in.close();
		
		// prepara respuesta
		ArrayList<String> resultado = new ArrayList<String>();
		switch(queHacer) {
			case CODIFICA:
				resultado.add(Secret.codifica(codigo));
				break;
			case DESCODIFICA:
				resultado.addAll(Secret.descodifica(codigo));
				break;
		}
		
		// imprime resultados
		for (String r : resultado) System.out.println("-> " + r);
		
	}
		
	/*
	 * queCodigo(String) -> Byte
	 * 
	 * Analiza el código introducido y decide si se puede codificar o descodificar.
	 * 
	 * Si el código contiene tanto numéros como letras -> ERRORMEZCLA
	 * Si el código contiene letras no válidas (Q i Z) -> ERRORLETRA
	 * Si el código contiene números no válidos -> ERRORNUM
	 * Si el código contiene letras -> CODIFICA
	 * Si el código contiene números -> DESCODIFICA
	 */
	private static byte queHacer(String codigo) {
		if (Pattern.matches("\\D+", codigo)) {
			return (Pattern.matches("[^QqÑñZzÇç]+", codigo)) ? CODIFICA : ERRORLETRA;
		}
		else if (Pattern.matches("\\d+", codigo)) {
			return (Pattern.matches("[2-9]+", codigo)) ? DESCODIFICA : ERRORNUM;
		}
		else {
			return ERRORMEZCLA;
		}
	}
}
