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
        	int sumaVidas = calcularSumaDeVidas(reinoA);
        	System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaVidas);      	
        	System.out.println();
        	reinoB = crearReino2(reino2, espadachines, arqueros, caballeros, lanceros);
        	bonificacion(reinoB);
        	System.out.println("\nReino de " + reino2 + ":");
        	imprimirContadores();
    		reiniciarContadores();        
        	int sumaV = calcularSumaDeVidas(reinoB);
        	System.out.println("\nEl nivel de vida de todo el ejército es: " + sumaV);       	
        	System.out.println("\n");
        	tablero = crearTablero(reinoA, reinoB);
        	jugar(sc, tablero, reinoA, reinoB);
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
    public void jugar(Scanner sc, Soldado[][] tablero, ArrayList<Soldado> reinoA, ArrayList<Soldado> reinoB) {
        boolean turnoReinoA = true;
        while (true) {
            imprimirTablero(tablero, reinoA, reinoB);
            if (turnoReinoA) {
                System.out.println("Turno del Reino 1");
                moverSoldado(sc, tablero, reinoA, reinoB);
            } else {
                System.out.println("Turno del Reino 2");
                moverSoldado(sc, tablero, reinoB, reinoA);
            }
            turnoReinoA = !turnoReinoA;
            if (reinoA.isEmpty()) {
                System.out.println("¡El Reino 2 ha ganado!");
                break;
            } else if (reinoB.isEmpty()) {
                System.out.println("¡El Reino 1 ha ganado!");
                break;
            }
        }
    }
    public static void imprimirTablero(Soldado[][] tablero, ArrayList<Soldado> reinoA, ArrayList<Soldado> reinoB) {
    	System.out.print(" ");
    	for (char columna = 'A'; columna <= 'J'; columna++) {
    		System.out.print(String.format("%5s", columna));
    	}
    	System.out.println();
    	System.out.println("   ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ");
    	for (int i = 0; i < tablero.length; i++) {
    		System.out.print((i + 1 < 10 ? " " : "") + (i + 1));
    	    for (int j = 0; j < tablero[i].length; j++) {
    	    	if (tablero[i][j] != null) {
    	    		Soldado soldado = tablero[i][j];
    	            String inicialTipo = obtenerInicialTipoSoldado(soldado);
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
    public static void moverSoldado(Scanner sc, Soldado[][] tablero, ArrayList<Soldado> ejercitoMov, ArrayList<Soldado> ejercitoOtro) {
        System.out.print("Ingrese la coordenada del soldado a mover (por ejemplo, d3): ");
        String coordenada = sc.next();
        int filaMov = Integer.parseInt(coordenada.substring(1)) - 1;
        char columnaChar = coordenada.charAt(0);
        int columnaMov = columnaChar - 'a';
        if (filaMov < 0 || filaMov >= tablero.length || columnaMov < 0 || columnaMov >= tablero[0].length) {
            System.out.println("Movimiento inválido: coordenada fuera del tablero.");
            return;
        }
        Soldado soldado = tablero[filaMov][columnaMov];
        if (soldado == null || !ejercitoMov.contains(soldado)) {
            System.out.println("Movimiento inválido: no hay un soldado del ejército correspondiente en esa posición.");
            return;
        }
        System.out.print("Ingrese la dirección del movimiento (w: arriba, a: izquierda, s: abajo, d: derecha): ");
        char direccion = sc.next().charAt(0);
        int nuevaFila = filaMov;
        int nuevaColumna = columnaMov;
        if (direccion == 'w') {
            nuevaFila--;
        } else if (direccion == 'a') {
            nuevaColumna--;
        } else if (direccion == 's') {
            nuevaFila++;
        } else if (direccion == 'd') {
            nuevaColumna++;
        } else {
            System.out.println("Movimiento inválido: dirección no válida.");
            return;
        }
        if (nuevaFila < 0 || nuevaFila >= tablero.length || nuevaColumna < 0 || nuevaColumna >= tablero[0].length) {
            System.out.println("Movimiento inválido: movimiento fuera del tablero.");
            return;
        }
        if (tablero[nuevaFila][nuevaColumna] != null) {
            Soldado soldadoEnNuevaPos = tablero[nuevaFila][nuevaColumna];
            if (ejercitoOtro.contains(soldadoEnNuevaPos)) {
                double probabilidadSoldado = soldado.getNivelVida() * 100.0 / (soldado.getNivelVida() + soldadoEnNuevaPos.getNivelVida());
                double probabilidadEnemigo = soldadoEnNuevaPos.getNivelVida() * 100.0 / (soldado.getNivelVida() + soldadoEnNuevaPos.getNivelVida());
                Soldado ganadorBatalla = batalla(soldado, soldadoEnNuevaPos);
                System.out.println("¡Batalla!");
                System.out.println(soldado.getNombre() + " - Vida: " + soldado.getNivelVida() + " - Probabilidad de victoria: " + probabilidadSoldado + "%");
                System.out.println(soldadoEnNuevaPos.getNombre() + " - Vida: " + soldadoEnNuevaPos.getNivelVida() + " - Probabilidad de victoria: " + probabilidadEnemigo + "%");

                if (ganadorBatalla == soldado) {
                    tablero[nuevaFila][nuevaColumna] = soldado;
                    tablero[filaMov][columnaMov] = null;
                    ejercitoOtro.remove(soldadoEnNuevaPos);
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else {
                	tablero[filaMov][columnaMov] = null;
                    ejercitoMov.remove(soldado);
                    soldadoEnNuevaPos.setNivelVida(soldadoEnNuevaPos.getNivelVida() + 1);
                }
                System.out.println("Ganador: " + ganadorBatalla.getNombre() + " - Vida: " + ganadorBatalla.getNivelVida());
                if (ejercitoOtro.isEmpty()) {
                    System.out.println("¡El ejército " + ejercitoMov.get(0).getNombre().substring(0, ejercitoMov.get(0).getNombre().length() - 2) + " ha ganado!");
                }
                return;
            } else {
                System.out.println("Movimiento inválido: ya hay un soldado en la nueva posición.");
                return;
            }
        }
        tablero[filaMov][columnaMov] = null;
        tablero[nuevaFila][nuevaColumna] = soldado;
    }
    public static Soldado batalla(Soldado soldado1, Soldado soldado2) {
        int totalVida = soldado1.getNivelVida() + soldado2.getNivelVida();
        double porcentaje1 = (soldado1.getNivelVida() * 100.0) / totalVida;
        double porcentaje2 = (soldado2.getNivelVida() * 100.0) / totalVida;
        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        if (numeroAleatorio <= porcentaje1) {
            return soldado1;
        } else {
            return soldado2;
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
    
    public static String obtenerInicialTipoSoldado(Soldado soldado) {
        if (soldado instanceof Espadachin) {
            return "e";
        } else if (soldado instanceof Arquero) {
            return "a";
        } else if (soldado instanceof Caballero) {
            return "c";
        } else if (soldado instanceof Lancero) {
            return "l";
        } else if (soldado instanceof EspadachinReal) {
            return "R";
        } else if (soldado instanceof EspadachinConquistador) {
            return "E";
        } else if (soldado instanceof EspadachinTeutonico) {
            return "T";
        } else if (soldado instanceof CaballeroFranco) {
            return "C";
        } else if (soldado instanceof CaballeroMoro) {
            return "M";
        } else {
            return "S";
        }
    }
    public static int calcularSumaDeVidas(ArrayList<Soldado> ejercito) {
        int sumaVidas = 0;
        for (Soldado soldado : ejercito) {
            sumaVidas += soldado.getNivelVida();
        }
        return sumaVidas;
    }
   
    public static ArrayList<Soldado> crearReino1(String nombreR, ArrayList<Espadachin> espadachines, ArrayList<Arquero> arqueros, ArrayList<Caballero> caballeros, ArrayList<Lancero> lanceros) {
    	String nombreReino = nombreR;
        System.out.println("Reino creado: " + nombreReino);
        ArrayList<Soldado> ejercito = new ArrayList<>();
        int f1 = (int)(Math.random() * 10 + 1);        	        	
    	int c1 = (int)(Math.random() * 10 + 1);
        switch (nombreReino) {
        case "Inglaterra":
            Soldado espadachinReal = new EspadachinReal();
            espadachinReal.setNivelVida(12);
            espadachinReal.setNombre("EspadachinReal" + "X1");
            espadachinReal.setFila(f1);
            espadachinReal.setColumna(c1);
            ejercito.add(espadachinReal);
            break;
        case "Sacro":
            Soldado espadachinTeutonico = new EspadachinTeutonico();
            espadachinTeutonico.setNivelVida(13);
            espadachinTeutonico.setNombre("EspadachinTeutonico" + "X1");
            espadachinTeutonico.setFila(f1);
            espadachinTeutonico.setColumna(c1);
            ejercito.add(espadachinTeutonico);
            break;
        case "Francia":
            Soldado caballeroFranco = new CaballeroFranco();
            caballeroFranco.setNivelVida(15);
            caballeroFranco.setNombre("CaballeroFranco" + "X1");
            caballeroFranco.setFila(f1);
            caballeroFranco.setColumna(c1);
            ejercito.add(caballeroFranco);
            break;
        case "Castilla":
        	Soldado espadachinConquistador = new EspadachinConquistador();
            espadachinConquistador.setNivelVida(14);
            espadachinConquistador.setNombre("EspadachinConquistador" + "X1");
            espadachinConquistador.setFila(f1);
            espadachinConquistador.setColumna(c1);
            ejercito.add(espadachinConquistador);
            break;
        case "Aragon":
            Soldado espadachinConquistador1 = new EspadachinConquistador();
            espadachinConquistador1.setNivelVida(14);
            espadachinConquistador1.setNombre("EspadachinConquistador" + "X1");
            espadachinConquistador1.setFila(f1);
            espadachinConquistador1.setColumna(c1);
            ejercito.add(espadachinConquistador1);
            break;
        case "Moros":
            Soldado caballeroMoro = new CaballeroMoro();
            caballeroMoro.setNivelVida(13);
            caballeroMoro.setNombre("CaballeroMoro Moros" + "X1");
            caballeroMoro.setFila(f1);
            caballeroMoro.setColumna(c1);
            ejercito.add(caballeroMoro);
            break;
        default:
            break;
    }
        int numSoldados = (int) (Math.random() * 9) + 1;
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
        int f1 = (int)(Math.random() * 10 + 1);        	        	
    	int c1 = (int)(Math.random() * 10 + 1);
        switch (nombreReino.toLowerCase()) {
        case "inglaterra":
            EspadachinReal espadachinReal = new EspadachinReal();
            espadachinReal.setNivelVida(12);
            espadachinReal.setNombre("EspadachinReal" + "X2");
            espadachinReal.setFila(f1);
            espadachinReal.setColumna(c1);
            ejercito.add(espadachinReal);
            break;
        case "sacro":
            EspadachinTeutonico espadachinTeutonico = new EspadachinTeutonico();
            espadachinTeutonico.setNivelVida(13);
            espadachinTeutonico.setNombre("EspadachinTeutonico" + "X2");
            espadachinTeutonico.setFila(f1);
            espadachinTeutonico.setColumna(c1);
            ejercito.add(espadachinTeutonico);
            break;
        case "francia":
            CaballeroFranco caballeroFranco = new CaballeroFranco();
            caballeroFranco.setNivelVida(15);
            caballeroFranco.setNombre("CaballeroFranco" + "X2");
            caballeroFranco.setFila(f1);
            caballeroFranco.setColumna(c1);
            ejercito.add(caballeroFranco);
            break;
        case "castilla":
        case "aragon":
            EspadachinConquistador espadachinConquistador = new EspadachinConquistador();
            espadachinConquistador.setNivelVida(14);
            espadachinConquistador.setNombre("EspadachinConquistador" + "X2");
            espadachinConquistador.setFila(f1);
            espadachinConquistador.setColumna(c1);
            ejercito.add(espadachinConquistador);
            break;
        case "moros":
            CaballeroMoro caballeroMoro = new CaballeroMoro();
            caballeroMoro.setNivelVida(13);
            caballeroMoro.setNombre("CaballeroMoro Moros" + "X2");
            caballeroMoro.setFila(f1);
            caballeroMoro.setColumna(c1);
            ejercito.add(caballeroMoro);
            break;
        default:
            break;
    }
        int numSoldados = (int) (Math.random() * 9) + 1;
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
        System.out.println("EspadachinReal: " + EspadachinReal.getContadorEspadachin());
        System.out.println("EspadachinTeutonico: " + EspadachinTeutonico.getContadorEspadachin());
        System.out.println("EspadachinConquistador: " + EspadachinConquistador.getContadorEspadachin());
        System.out.println("Arqueros: " + Arquero.getContadorArqueros());
        System.out.println("Lanceros: " + Lancero.getContadorLanceros());
        System.out.println("Caballeros: " + Caballero.getContadorCaballeros());
        System.out.println("CaballeroMoro: " + CaballeroMoro.getContadorCaballero()); 
        System.out.println("CaballeroFranco: " + CaballeroFranco.getContadorCaballero()); 
    }
    public void reiniciarContadores() {
        Soldado.reiniciarContadores();
        Espadachin.reiniciarContadores();
        EspadachinReal.reiniciarContadores();
        EspadachinTeutonico.reiniciarContadores();
        EspadachinConquistador.reiniciarContadores();
        Arquero.reiniciarContadores();
        Lancero.reiniciarContadores();
        Caballero.reiniciarContadores();
        CaballeroMoro.reiniciarContadores();
        CaballeroFranco.reiniciarContadores();
    }   
}