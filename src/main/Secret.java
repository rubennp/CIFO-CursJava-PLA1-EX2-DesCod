package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Secret {
	private static HashMap<String, String> secret = new HashMap<String, String>();
	private static char[] charsCodigo;

	public Secret() {
		secret.put("2", "ABC");
		secret.put("3", "DEF");
		secret.put("4", "GHI");
		secret.put("5", "JKL");
		secret.put("6", "MNO");
		secret.put("7", "PRS");
		secret.put("8", "TUV");
		secret.put("9", "WXY");
	}

	/*
	 * descodifica(String) -> ArrayList<String>
	 * 
	 * Genera todas las posibles cadenas de texto a partir de un código numérico.
	 */
	public static ArrayList<String> descodifica(String codigo) {
		ArrayList<String> resultado = new ArrayList<String>();
		
		int totalNumsCodigo = codigo.length();
		
		int totalPosibilidades = (int)Math.pow(3, totalNumsCodigo);
		String[] resultadoAux = new String[totalPosibilidades];
		
		for (int r = 0; r < resultadoAux.length; r++) resultadoAux[r] = "";				// inicializa resultadoAux a cadenas vacias
		
		for (int numCodigo = 0; numCodigo < totalNumsCodigo; numCodigo++ ) {			// recorre numeros del código
			String letrasNum = secret.get(codigo.substring(numCodigo, numCodigo+1));	// recupera letras relacionadas con número
			int repetirCiclo = (int)Math.pow(3, numCodigo);
			
			// guarda posición de letra en array.
			int lastPosicion = 0;
			
			// inicia ciclo de repetición de grupo de letras
			for (int c = 0; c < repetirCiclo; c++) {
				// cuantas veces tiene que repetirse la letra dentro de cada ciclo
				int repeticionesLetra = (int)Math.pow(3, (totalNumsCodigo-1)-numCodigo);
		
				// recorre letras
				for (int l = 0; l < letrasNum.length(); l++) {		
					String letra = letrasNum.substring(l, l+1);
					
					// guarda rp repeticiones de la misma letra
					for (int rp = 0; rp < repeticionesLetra; rp++) resultadoAux[lastPosicion++] += letra;
				}
			}
		}
		
		// guarda a ArrayList para retorno
		for (String r : resultadoAux) resultado.add(r);
		
		return resultado;
	}
	
	/*
	 * codifica(String) -> String
	 * 
	 * Texto a código numérico
	 */
	public static String codifica(String codigo) {
		String resultado = "";
		
		charsCodigo = codigo.toUpperCase().toCharArray();
		for (char letra : charsCodigo) {
			for (String num : secret.keySet()) {
				if (secret.get(num).contains(Character.toString(letra))) {
					resultado += num;
					break;
				}
				
			}
		}
		
		return resultado;
	}
}