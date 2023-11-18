// Ejercicio - 1
// Autor: Juan Victor Salas Aguilar
// Colaboro: Marco Aedo
// Tiempo:
import java.util.*;
import java.util.ArrayList;
public class Ejercicio1 {
    public static void main(String[] args) {  	
    	Scanner sc = new Scanner(System.in);
        boolean jugarDeNuevo = true;
        while (jugarDeNuevo) {
        	ArrayList<Espadachin> espadachines = new ArrayList<>();
        	ArrayList<Arquero> arqueros = new ArrayList<>();
        	ArrayList<Caballero> caballeros = new ArrayList<>();        
        	ArrayList<Soldado> ejercito1 = crearEjercito1(espadachines, arqueros, caballeros);
        	ArrayList<Soldado> ejercito2 = crearEjercito2(espadachines, arqueros, caballeros);

        	System.out.println("Ejército 1:");
        	mostrarEjercito(ejercito1);
        	System.out.println("\nEl soldado con mayor vida es: ");
        	Soldado mayorVida = soldadoConMayorVida(ejercito1);
        	System.out.println("Nombre: " + mayorVida.getNombre());
        	System.out.println("Vida: " + mayorVida.getNivelVida());
        	double promedioVida = calcularPromedioDeVida(ejercito1);
        	System.out.println("\nPromedio de vida de todos los soldados: " + promedioVida);     
        	int sumaVidas = calcularSumaDeVidas(ejercito1);
        	System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaVidas);
        	ordenamientoBurbuja(ejercito1);
        	System.out.println("\nEjército 2:");
        	mostrarEjercito(ejercito2);
        	System.out.println("\nEl soldado con mayor vida es: ");
        	Soldado mayorV = soldadoConMayorVida(ejercito2);
        	System.out.println("Nombre: " + mayorV.getNombre());
        	System.out.println("Vida: " + mayorV.getNivelVida());
        	double promedioV = calcularPromedioDeVida(ejercito2);
        	System.out.println("\nPromedio de vida de todos los soldados: " + promedioV);     
        	int sumaV = calcularSumaDeVidas(ejercito2);
        	System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaV);
        	ordenamientoBurbuja(ejercito2);
        	System.out.println("\n");
        	Soldado[][] tablero = crearTablero(ejercito1, ejercito2);
        	imprimirTablero(tablero, ejercito1, ejercito2);
        	System.out.println("\nBatalla entre el ejrcito1 vs el ejercito2");
        	batalla(ejercito1, ejercito2);
        	System.out.println("¿Quieres jugar de nuevo? (si/no): ");
            String respuesta = sc.next().toLowerCase();
            if (!respuesta.equals("si")) {
                System.out.println("Fin del programa. ¡Hasta luego!");
                jugarDeNuevo = false;
            } else {              
                espadachines.clear();
                arqueros.clear();
                caballeros.clear();
                ejercito1 = crearEjercito1(espadachines, arqueros, caballeros);
                ejercito2 = crearEjercito2(espadachines, arqueros, caballeros);
            }
        }
    }
    public static void imprimirTablero(Soldado[][] tablero, ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
    	System.out.print(" ");
    	for (char columna = 'A'; columna <= 'J'; columna++) {
    	    System.out.print("   " + columna);
    	}
    	System.out.println();
    	System.out.println("   ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ");

    	for (int i = 0; i < tablero.length; i++) {
    	    System.out.print((i + 1 < 10 ? " " : "") + (i + 1));
    	    for (int j = 0; j < tablero[i].length; j++) {
    	        if (tablero[i][j] != null) {
    	            Soldado soldado = tablero[i][j];
    	            char inicialTipo = obtenerInicialTipoSoldado(soldado);
    	            int numeroEjercito = soldado.getNombre().contains("X1") ? 1 : 2;
    	            System.out.print("|" + numeroEjercito + inicialTipo + soldado.getNivelVida());
    	        } else {
    	            System.out.print("|___");
    	        }
    	    }
    	    System.out.println("|");
    	}
    }
    public static void ordenamientoBurbuja(ArrayList<Soldado> ejercito) {
    	System.out.println("\nRanking de poder por ordenamiento burbuja:");
        int n = ejercito.size();
        boolean ordenado;
        for (int i = 0; i < n - 1; i++) {
            ordenado = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (ejercito.get(j).getNivelVida() < ejercito.get(j + 1).getNivelVida()) {
                    Soldado temp = ejercito.get(j);
                    ejercito.set(j, ejercito.get(j + 1));
                    ejercito.set(j + 1, temp);
                    ordenado = true;
                }
            }
            if (!ordenado) {
                break;
            }
        }
        for (int i = 0; i < ejercito.size(); i++) {
            System.out.println("Posición " + (i + 1) + ": " + ejercito.get(i).getNombre() + " - Vida: " + ejercito.get(i).getNivelVida());
        }
    }
    public static double calcularPromedioDeVida(ArrayList<Soldado> ejercito) {
        int totalVida = 0;
        for (Soldado soldado : ejercito) {
            totalVida += soldado.getNivelVida();
        }
        return (double) totalVida / ejercito.size();
    }
    public static char obtenerInicialTipoSoldado(Soldado soldado) {
        if (soldado instanceof Espadachin) {
            return 'E';
        } else if (soldado instanceof Arquero) {
            return 'A';
        } else if (soldado instanceof Caballero) {
            return 'C';
        } else {
            return 'S';
        }
    }
    public static int calcularSumaDeVidas(ArrayList<Soldado> ejercito) {
        int sumaVidas = 0;
        for (Soldado soldado : ejercito) {
            sumaVidas += soldado.getNivelVida();
        }
        return sumaVidas;
    }
    public static Soldado soldadoConMayorVida(ArrayList<Soldado> ejercito) {
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
    public static ArrayList<Soldado> crearEjercito1(ArrayList<Espadachin> espadachines, ArrayList<Arquero> arqueros, ArrayList<Caballero> caballeros) {
        ArrayList<Soldado> ejercito = new ArrayList<>();
        int numSoldados = (int) (Math.random() * 10) + 1;

        for (int i = 0; i < numSoldados; i++) {
            int tipoSoldado = (int) (Math.random() * 3) + 1;
            Soldado soldado;
        	int d = (int)(Math.random() * 5 + 1);
        	int a = (int)(Math.random() * 5 + 1);        	        	
        	int v = (int)(Math.random() * 5 + 1);
        	int f = (int)(Math.random() * 10 + 1);        	        	
        	int c = (int)(Math.random() * 10 + 1);
            switch (tipoSoldado) {
                case 1:
                    soldado = new Espadachin();
                    espadachines.add((Espadachin) soldado);
                    break;
                case 2:
                    soldado = new Arquero();
                    arqueros.add((Arquero) soldado);
                    break;
                case 3:
                    soldado = new Caballero();
                    caballeros.add((Caballero) soldado);
                    break;
                default:
                    soldado = new Espadachin();
                    caballeros.add((Caballero) soldado);
            }
            String nombre = soldado.getClass().getSimpleName() + " " + i + "X1";
            int vida;
            if (soldado instanceof Espadachin) {
                vida = (int) (Math.random() * 2) + 3; 
            } else if (soldado instanceof Arquero) {
                vida = (int) (Math.random() * 3) + 1; 
            } else if (soldado instanceof Caballero) {
                vida = (int) (Math.random() * 3) + 3; 
            } else {
                vida = 3; 
            }
            soldado.setNombre(nombre);                  
            soldado.setNivelAtaque(a);
            soldado.setNivelDefensa(d);         
            soldado.setVelocidad(v);
            soldado.setNivelVida(vida);
            soldado.setFila(f);
            soldado.setColumna(c); 

            while (posicionSoldado(ejercito, soldado.getFila(), soldado.getColumna())) {
                soldado.setFila((int) (Math.random() * 10) + 1);
                soldado.setColumna((int) (Math.random() * 10) + 1);
            }
            ejercito.add(soldado);
        }
        return ejercito;
    }
    private static Soldado[][] crearTablero(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
        Soldado[][] tablero = new Soldado[10][10];

        for (Soldado soldado : ejercito1) {
            int fila = soldado.getFila() - 1;
            int columna = soldado.getColumna() - 1;
            tablero[fila][columna] = soldado;
        }

        for (Soldado soldado : ejercito2) {
            int fila = soldado.getFila() - 1;
            int columna = soldado.getColumna() - 1;
            tablero[fila][columna] = soldado;
        }

        return tablero;
    }
    public static ArrayList<Soldado> crearEjercito2(ArrayList<Espadachin> espadachines, ArrayList<Arquero> arqueros, ArrayList<Caballero> caballeros) {
        ArrayList<Soldado> ejercito = new ArrayList<>();
        int numSoldados = (int) (Math.random() * 10) + 1;

        for (int i = 0; i < numSoldados; i++) {
            int tipoSoldado = (int) (Math.random() * 3) + 1;
            Soldado soldado;
        	int d = (int)(Math.random() * 5 + 1);
        	int a = (int)(Math.random() * 5 + 1);        	        	
        	int v = (int)(Math.random() * 5 + 1);
        	int f = (int)(Math.random() * 10 + 1);        	        	
        	int c = (int)(Math.random() * 10 + 1);
            switch (tipoSoldado) {
                case 1:
                    soldado = new Espadachin();
                    espadachines.add((Espadachin) soldado);
                    break;
                case 2:
                    soldado = new Arquero();
                    arqueros.add((Arquero) soldado);
                    break;
                case 3:
                    soldado = new Caballero();
                    caballeros.add((Caballero) soldado);
                    break;
                default:
                    soldado = new Espadachin();
                    caballeros.add((Caballero) soldado);
            }
            String nombre = soldado.getClass().getSimpleName() + " " + i + "X2";
            int vida;
            if (soldado instanceof Espadachin) {
                vida = (int) (Math.random() * 2) + 3; 
            } else if (soldado instanceof Arquero) {
                vida = (int) (Math.random() * 3) + 1; 
            } else if (soldado instanceof Caballero) {
                vida = (int) (Math.random() * 3) + 3; 
            } else {
                vida = 3; 
            }
            soldado.setNombre(nombre);                  
            soldado.setNivelAtaque(a);
            soldado.setNivelDefensa(d);         
            soldado.setVelocidad(v);
            soldado.setNivelVida(vida);
            soldado.setFila(f);
            soldado.setColumna(c); 

            while (posicionSoldado(ejercito, soldado.getFila(), soldado.getColumna())) {
                soldado.setFila((int) (Math.random() * 10) + 1);
                soldado.setColumna((int) (Math.random() * 10) + 1);
            }
            ejercito.add(soldado);
        }
        return ejercito;
    }
    public static void batalla(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
        int numSoldadosEjercito1 = ejercito1.size();
        int numSoldadosEjercito2 = ejercito2.size();

        if (numSoldadosEjercito1 > numSoldadosEjercito2) {
            System.out.println("¡Ejército 1 ha ganado la batalla!");
            System.out.println("Número de soldados: " + numSoldadosEjercito1);
            System.out.println("Ejército 2 ha perdido.");
            System.out.println("Número de soldados: " + numSoldadosEjercito2);
        } else if (numSoldadosEjercito2 > numSoldadosEjercito1) {
            System.out.println("¡Ejército 2 ha ganado la batalla!");
            System.out.println("Número de soldados: " + numSoldadosEjercito2);
            System.out.println("Ejército 1 ha perdido.");
            System.out.println("Número de soldados: " + numSoldadosEjercito1);
        } else {
            System.out.println("¡La batalla ha terminado en empate!");
            System.out.println("Ambos ejércitos tienen el mismo número de soldados: " + numSoldadosEjercito1);
        }
    }
    public static void mostrarEjercito(ArrayList<Soldado> ejercito) {
        for (Soldado soldado : ejercito) {
            System.out.println("nombre: " + soldado.getNombre());
            System.out.println("fila: " + soldado.getFila());
            System.out.println("columna: " + soldado.getColumna());
            System.out.println("vida: " + soldado.getNivelVida());
            System.out.println("nivel de Ataque: " + soldado.getNivelAtaque());
            System.out.println("nivel de Defensa: " + soldado.getNivelDefensa());
            System.out.println("velocidad: " + soldado.getVelocidad());
            System.out.println();
        }
    }
    public static <T extends Soldado> boolean posicionSoldado(ArrayList<T> soldados, int fila, int columna) {
        for (Soldado soldado : soldados) {
            if (soldado.getFila() == fila && soldado.getColumna() == columna) {
                return true;
            }
        }
        return false;
    }
}