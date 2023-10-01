// Ejercicio - 1
// Autor: Juan Victor Salas Aguilar
// Colaboro: Marco Aedo
// Tiempo:
public class VideoJuego2 {
	public static void main(String[] args) {		
		Soldado[][] tablero = new Soldado[10][10];
		int n = (int)(Math.random() * 10) + 1;
		Soldado[] soldados = new Soldado[n];
		for(int i = 0; i < n; i++) {
			String nombre = "soldado" + i;
			int columna = (int)(Math.random() * 10) + 1;
			int fila = (int)(Math.random() * 10) + 1;
			int vida = (int)(Math.random() * 5) + 1;
			soldados[i] = new Soldado();
			soldados[i].setNombre(nombre);
			soldados[i].setFila(fila);
			soldados[i].setColumna(columna);
			soldados[i].setNivelVida(vida);	
			tablero[fila - 1][columna - 1] = soldados[i];
		}
		System.out.println("\nSoldados creados");
		imprimirSoldados(soldados);
		imprimirTablero(tablero);
		System.out.println("\nEl soldado con mayor vida es: ");
		Soldado mayorVida = soldadoConMayorVida(soldados);
	    System.out.println("Nombre: " + mayorVida.getNombre());
	    System.out.println("Vida: " + mayorVida.getNivelVida());
	    double promedioVida = calcularPromedioDeVida(soldados);
	    System.out.println("\nPromedio de vida de todos los soldados: " + promedioVida);
	    int sumaVidas = calcularSumaDeVidas(soldados);
	    System.out.println("\nEl nivel de vida de todo el ejercito es: " + sumaVidas);
	    System.out.println("\nRanking de poder por ordenamiento burbuja:");
	    ordenamientoBurbuja(soldados);
	    System.out.println("\nRanking de poder por ordenamiento de seleccion:");
	    ordenamientoSeleccion(soldados);

	}
	public static void imprimirSoldados(Soldado[] ejercito) {
		for(int i = 0; i < ejercito.length; i++) {
			System.out.println("nombre: "+ejercito[i].getNombre());
			System.out.println("fila: "+ejercito[i].getFila());
			System.out.println("columna: "+ejercito[i].getColumna());
			System.out.println("vida: "+ejercito[i].getNivelVida());
		}
		System.out.println();
	}
	public static void imprimirTablero(Soldado[][] tablero) {
		System.out.println(" _ _ _ _ _ _ _ _ _ _");
        for (int i = 0; i < tablero.length; i++) {        	
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null) {               	
                    System.out.print("|S");
                } else {
                    System.out.print("|_");
                }
            }
            System.out.println("|");
        }
    }
	public static Soldado soldadoConMayorVida(Soldado[] ejercito) {
	    Soldado mayorVida = null;
	    int maxVida = 0;
	    for (Soldado soldado : ejercito) {
	        if (soldado.getNivelVida() > maxVida) {
	            maxVida = soldado.getNivelVida();
	            mayorVida = soldado;
	        }
	    }
	    return mayorVida;
	}
	public static double calcularPromedioDeVida(Soldado[] ejercito) {
	    int totalVida = 0;
	    for (Soldado soldado : ejercito) {
	        totalVida += soldado.getNivelVida();
	    }
	    return (double) totalVida / ejercito.length;
	}
	public static int calcularSumaDeVidas(Soldado[] ejercito) {
	    int sumaVidas = 0;
	    for (Soldado soldado : ejercito) {
	        sumaVidas += soldado.getNivelVida();
	    }
	    return sumaVidas;
	}
	public static void ordenamientoBurbuja(Soldado[] ejercito) {
	    int n = ejercito.length;
	    boolean ordenado;    
	    for (int i = 0; i < n - 1; i++) {
	        ordenado = false;	        
	        for (int j = 0; j < n - 1 - i; j++) {
	            if (ejercito[j].getNivelVida() < ejercito[j + 1].getNivelVida()) {
	                Soldado temp = ejercito[j];
	                ejercito[j] = ejercito[j + 1];
	                ejercito[j + 1] = temp;
	                ordenado = true;
	            }
	        }
	        // Si no hubo intercambios en esta pasada, el arreglo ya está ordenado
	        if (!ordenado) {
	            break;
	        }
	    }
	    for (int i = 0; i < ejercito.length; i++) {
	        System.out.println("Posición " + (i + 1) + ": " + ejercito[i].getNombre() + " - Vida: " + ejercito[i].getNivelVida());
	    }
	}
	public static void ordenamientoSeleccion(Soldado[] ejercito) {
	    int n = ejercito.length;
	    for (int i = 0; i < n - 1; i++) {
	        int max = i;
	        for (int j = i + 1; j < n; j++) {
	            if (ejercito[j].getNivelVida() > ejercito[max].getNivelVida()) {
	                max = j;
	            }
	        }
	        Soldado temp = ejercito[i];
	        ejercito[i] = ejercito[max];
	        ejercito[max] = temp;
	    }
	    for (int i = 0; i < ejercito.length; i++) {
	        System.out.println("Posición " + (i + 1) + ": " + ejercito[i].getNombre() + " - Vida: " + ejercito[i].getNivelVida());
	    }
	}
}
