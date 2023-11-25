import java.util.ArrayList;
import java.util.*;
public class Mapa {
    private Soldado[][] tablero;
    private String tipoTerritorio;
    private ArrayList<Soldado> reinoA;
    private ArrayList<Soldado> reinoB;
    private static String[] nombresReinos = {"Inglaterra", "Francia", "Sacro", "Castilla", "Aragon", "Moros"};
    private static String[] tiposTerritorio = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
    
    public Mapa() {        
        this.tablero = new Soldado[10][10]; 
        reinoA = new ArrayList<>();
        reinoB = new ArrayList<>();      
    }
    public void iniciarJuego() {
    	Scanner sc = new Scanner(System.in);
    	boolean jugarDeNuevo = true;
    	while (jugarDeNuevo) {
    		tipoTerritorio = tiposTerritorio[(int) (Math.random() * tiposTerritorio.length)];
    		System.out.println("\nEl tipo de territorio es: " + this.tipoTerritorio);
    		System.out.println();
    		String reino1 = nombresReinos[(int) (Math.random() * nombresReinos.length)];
    		String reino2;
    		do {
    			reino2 = nombresReinos[(int) (Math.random() * nombresReinos.length)];
    		} while (reino2.equals(reino1));
    		ArrayList<Espadachin> espadachines = new ArrayList<>();
        	ArrayList<Lancero> lanceros = new ArrayList<>();
        	ArrayList<Arquero> arqueros = new ArrayList<>();
        	ArrayList<Caballero> caballeros = new ArrayList<>();
    		reinoA = crearReino1(reino1, espadachines, arqueros, caballeros, lanceros);
    		bonificacion(reinoA);
    		System.out.println("\nReino de " + reino1 + ":");
    		imprimirContadores();
    		reiniciarContadores();
        	mostrarEjercito(reinoA);
        	System.out.println("\nEl soldado con mayor vida es: ");
        	Soldado mayorVida = soldadoConMayorVida(reinoA);
        	System.out.println("Nombre: " + mayorVida.getNombre());
        	System.out.println("Vida: " + mayorVida.getNivelVida());
        	double promedioVida = calcularPromedioDeVida(reinoA);
        	System.out.println("\nPromedio de vida de todos los soldados: " + promedioVida);     
        	int sumaVidas = calcularSumaDeVidas(reinoA);
        	System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaVidas);
        	ordenamientoBurbuja(reinoA);
        	System.out.println();
        	reinoB = crearReino2(reino2, espadachines, arqueros, caballeros, lanceros);
        	bonificacion(reinoB);
        	System.out.println("\nReino de " + reino2 + ":");
        	imprimirContadores();
    		reiniciarContadores();
        	mostrarEjercito(reinoB);
        	System.out.println("\nEl soldado con mayor vida es: ");
        	Soldado mayorV = soldadoConMayorVida(reinoB);
        	System.out.println("Nombre: " + mayorV.getNombre());
        	System.out.println("Vida: " + mayorV.getNivelVida());
        	double promedioV = calcularPromedioDeVida(reinoB);
        	System.out.println("\nPromedio de vida de todos los soldados: " + promedioV);     
        	int sumaV = calcularSumaDeVidas(reinoB);
        	System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaV);
        	ordenamientoBurbuja(reinoB);
        	System.out.println("\n");
        	tablero = crearTablero(reinoA, reinoB);
        	imprimirTablero(tablero, reinoA, reinoB);
        	batalla(reinoA, reino1, reinoB, reino2);
        	System.out.println("¿Quieres jugar de nuevo? (si/no): ");
            String respuesta = sc.next().toLowerCase();
            if (!respuesta.equals("si")) {
                System.out.println("Fin del programa. ¡Hasta luego!");
                jugarDeNuevo = false;
            } else {              
                espadachines.clear();
                arqueros.clear();
                caballeros.clear();
                lanceros.clear();   
            }
    	}
    }   
    public static void imprimirTablero(Soldado[][] tablero, ArrayList<Soldado> reinoA, ArrayList<Soldado> reinoB) {
    	System.out.print(" ");
    	for (char columna = 'A'; columna <= 'J'; columna++) {
    		System.out.print(String.format("%4s", columna));
    	}
    	System.out.println();
    	System.out.println("   ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ");
    	for (int i = 0; i < tablero.length; i++) {
    		System.out.print((i + 1 < 10 ? " " : "") + (i + 1));
    	    for (int j = 0; j < tablero[i].length; j++) {
    	    	if (tablero[i][j] != null) {
    	    		Soldado soldado = tablero[i][j];
    	            char inicialTipo = obtenerInicialTipoSoldado(soldado);
    	            int numeroEjercito = soldado.getNombre().contains("X1") ? 1 : 2;
    	            String cuadrado = String.format("%4s", String.format("|%d%s%2d", numeroEjercito, inicialTipo, soldado.getNivelVida()));
    	            System.out.print(cuadrado);
    	        } else {
    	            System.out.print(String.format("%4s", "|____"));
    	        }
    	    }
    	    System.out.println("|");
    	}
    }
    private static Soldado[][] crearTablero(ArrayList<Soldado> reinoA, ArrayList<Soldado> reinoB) {
        Soldado[][] tablero = new Soldado[10][10];

        for (Soldado soldado : reinoA) {
            int fila = soldado.getFila() - 1;
            int columna = soldado.getColumna() - 1;
            tablero[fila][columna] = soldado;
        }
        for (Soldado soldado : reinoB) {
            int fila = soldado.getFila() - 1;
            int columna = soldado.getColumna() - 1;
            tablero[fila][columna] = soldado;
        }
        return tablero;
    }
    
    public static char obtenerInicialTipoSoldado(Soldado soldado) {
        if (soldado instanceof Espadachin) {
            return 'E';
        } else if (soldado instanceof Arquero) {
            return 'A';
        } else if (soldado instanceof Caballero) {
            return 'C';
        } else if (soldado instanceof Lancero) {
            return 'L';
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
    public static double calcularPromedioDeVida(ArrayList<Soldado> ejercito) {
        int totalVida = 0;
        for (Soldado soldado : ejercito) {
            totalVida += soldado.getNivelVida();
        }
        return (double) totalVida / ejercito.size();
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
    public static ArrayList<Soldado> crearReino1(String nombreR, ArrayList<Espadachin> espadachines, ArrayList<Arquero> arqueros, ArrayList<Caballero> caballeros, ArrayList<Lancero> lanceros) {
    	String nombreReino = nombreR;
        System.out.println("Reino creado: " + nombreReino);
        ArrayList<Soldado> ejercito = new ArrayList<>();
        int numSoldados = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < numSoldados; i++) {           
            Soldado soldado = getRandomSoldado();
            int f = (int)(Math.random() * 10 + 1);        	        	
        	int c = (int)(Math.random() * 10 + 1);
            String nombre = soldado.getClass().getSimpleName() + " " + i + "X1";
            int vida, ataque = 0, defensa = 0;
            if (soldado instanceof Espadachin) {
                vida = (int) (Math.random() * 3) + 8; 
                ataque = 10;
                defensa = 8;
            } else if (soldado instanceof Arquero) {
            	ataque = 7;
            	defensa = 3;
                vida = (int) (Math.random() * 3) + 3;
            } else if (soldado instanceof Caballero) {
            	ataque = 13;
            	defensa = 7;
                vida = (int) (Math.random() * 3) + 10; 
            } else if (soldado instanceof Lancero) {
            	ataque = 5;
            	defensa = 10;
            	vida = (int) (Math.random() * 3) + 5;   
            	
            } else {
            	vida = 3;
            }
            soldado.setNombre(nombre);                  
            soldado.setNivelAtaque(ataque);
            soldado.setNivelDefensa(defensa);                    
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
    public static ArrayList<Soldado> crearReino2(String nombreR, ArrayList<Espadachin> espadachines, ArrayList<Arquero> arqueros, ArrayList<Caballero> caballeros, ArrayList<Lancero> lanceros) {
    	String nombreReino = nombreR;
        System.out.println("Reino creado: " + nombreReino);
        ArrayList<Soldado> ejercito = new ArrayList<>();
        int numSoldados = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < numSoldados; i++) {           
            Soldado soldado = getRandomSoldado();
            int f = (int)(Math.random() * 10 + 1);        	        	
        	int c = (int)(Math.random() * 10 + 1);
            String nombre = soldado.getClass().getSimpleName() + " " + i + "X2";
            int vida, ataque = 0, defensa = 0;
            if (soldado instanceof Espadachin) {
                vida = (int) (Math.random() * 3) + 8; 
                ataque = 10;
                defensa = 8;
            } else if (soldado instanceof Arquero) {
            	ataque = 7;
            	defensa = 3;
                vida = (int) (Math.random() * 3) + 3;
            } else if (soldado instanceof Caballero) {
            	ataque = 13;
            	defensa = 7;
                vida = (int) (Math.random() * 3) + 10; 
            } else if (soldado instanceof Lancero) {
            	ataque = 5;
            	defensa = 10;
            	vida = (int) (Math.random() * 3) + 5;   
            	
            } else {
            	vida = 3;
            }
            soldado.setNombre(nombre);                  
            soldado.setNivelAtaque(ataque);
            soldado.setNivelDefensa(defensa);                    
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
    public void bonificacion(ArrayList<Soldado> ejercito) {
        for (Soldado soldado : ejercito) {
            String nombreReino = obtenerNombreReinoPorSoldado(soldado);
            if (tipoTerritorio.equals("bosque") && (nombreReino.equals("Inglaterra") || nombreReino.equals("Sacro"))) {
                soldado.setNivelVida(soldado.getNivelVida() + 1);
            } else if (tipoTerritorio.equals("campo abierto") && (nombreReino.equals("Francia") || nombreReino.equals("Sacro"))) {
                soldado.setNivelVida(soldado.getNivelVida() + 1);
            } else if (tipoTerritorio.equals("montaña") && (nombreReino.equals("Castilla") || nombreReino.equals("Aragon"))) {
                soldado.setNivelVida(soldado.getNivelVida() + 1);
            } else if (tipoTerritorio.equals("desierto") && nombreReino.equals("Moros")) {
                soldado.setNivelVida(soldado.getNivelVida() + 1);
            } else if (tipoTerritorio.equals("playa") && nombreReino.equals("Sacro")) {
                soldado.setNivelVida(soldado.getNivelVida() + 1);
            }
        }
    }  
    public static Soldado getRandomSoldado() {
        int tipoSoldado = (int) (Math.random() * 4);
        switch (tipoSoldado) {
            case 0:
                return new Espadachin();
            case 1:
                return new Arquero();
            case 2:
                return new Lancero();
            case 3:
                return new Caballero();
            default:
                return new Espadachin();
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
    public static void batalla(ArrayList<Soldado> ejercito1, String nombreReino1, ArrayList<Soldado> ejercito2, String nombreReino2) {
        int totalVida1 = calcularSumaDeVidas(ejercito1);
        int totalVida2 = calcularSumaDeVidas(ejercito2);
        double porcentaje1 = (totalVida1 * 100.0) / (totalVida1 + totalVida2);
        double porcentaje2 = (totalVida2 * 100.0) / (totalVida1 + totalVida2);
        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        System.out.println("\nEjercito 1: " + nombreReino1 + ": " + totalVida1 + " " + porcentaje1 + "% de probabilidad de victoria");
        System.out.println("Ejercito 2: " + nombreReino2 + ": " + totalVida2 + " " + porcentaje2 + "% de probabilidad de victoria");

        if (numeroAleatorio <= porcentaje1) {           
        	System.out.println("El ganador es el ejercito 1 de: " + nombreReino1 + ". Ya que al generar los porcentajes de probabilidad"
        			+ " de victoria basada en los" + "\nniveles de vida de sus soldados y aplicando un experimento aleatorio salió "
        					+ "vencedor. (Aleatorio generado: " + numeroAleatorio + ")");
        } else {
            System.out.println("El ganador es el ejercito 2 de: " + nombreReino2 + ". Ya que al generar los porcentajes de probabilidad"
            		+ " de victoria basada en los" + "\nniveles de vida de sus soldados y aplicando un experimento aleatorio salió"
            		+ " vencedor. (Aleatorio generado: " + numeroAleatorio + ")");
        }
    }
    private String obtenerNombreReinoPorSoldado(Soldado soldado) {
        if (soldado.getNombre().contains("X1")) {
            return reinoA.get(0).getNombre(); 
        } else if (soldado.getNombre().contains("X2")) {
            return reinoB.get(0).getNombre();
        } else {
            return "";
        }
    }
    public void imprimirContadores() {
    	System.out.println("Soldados: " + Soldado.getContadorSoldados());
        System.out.println("Espadachines: " + Espadachin.getContadorEspadachines());
        System.out.println("Arqueros: " + Arquero.getContadorArqueros());
        System.out.println("Lanceros: " + Lancero.getContadorLanceros());
        System.out.println("Caballeros: " + Caballero.getContadorCaballeros());      
    }
    public void reiniciarContadores() {
        Soldado.reiniciarContadores();
        Espadachin.reiniciarContadores();
        Arquero.reiniciarContadores();
        Lancero.reiniciarContadores();
        Caballero.reiniciarContadores();
    }   
}