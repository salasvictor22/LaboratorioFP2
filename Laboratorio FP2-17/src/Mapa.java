import java.util.*;
import java.util.ArrayList;
public class Mapa {
    private String tipoTerritorio;
    private Ejercito[][] tablero;
    private ArrayList<Ejercito> reinoA;
    private ArrayList<Ejercito> reinoB;
    private String[] nombresReinos = {"Inglaterra", "Francia", "Sacro", "Castilla", "Aragon", "Moros"};
    private String[] tiposTerritorio = {"bosque", "campo abierto", "montaña", "desierto", "playa"};

    public Mapa() {
        this.tablero = new Ejercito[10][10];
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
    		crearEjercito(reinoA, reino1, tablero);
    		crearEjercito(reinoB, reino2, tablero);
    		bonificacion(reinoA);
    		bonificacion(reinoB);
    		imprimirTablero(tablero, reinoA, reinoB);     
    		boolean turnoReino1 = true; 
    		while (!reinoA.isEmpty() && !reinoB.isEmpty()) {
    			if (turnoReino1) {
    				System.out.println("Turno del Reino " + reinoA.get(0).getNombreReino());
    				moverEjercito(sc, tablero, reinoA, reinoB);
    				imprimirTablero(tablero, reinoA, reinoB);
    			} else {
    				System.out.println("Turno del Reino  " + reinoB.get(0).getNombreReino());
    				moverEjercito(sc, tablero, reinoB, reinoA);
    				imprimirTablero(tablero, reinoA, reinoB);
    			}
    			turnoReino1 = !turnoReino1; 
    		}
    		if (reinoA.isEmpty()) {
    			System.out.println("¡El Reino de " + reinoB.get(0).getNombreReino() + " ha ganado!");
    		} else {
    			System.out.println("¡El Reino de " + reinoA.get(0).getNombreReino() + " ha ganado!");
    		}
    		System.out.print("¿Quieres volver a jugar? (si/no): ");
            String respuesta = sc.next().toLowerCase();
            if (respuesta.equals("no")) {
                jugarDeNuevo = false;
                System.out.println("\nJuego terminado.");
            }
    	}
    }
    
    public void bonificacion(ArrayList<Ejercito> ejercitos) {
        for (Ejercito ejercito : ejercitos) {
            for (Soldado soldado : ejercito.getMisSoldados()) {
                String nombreReino = ejercito.getNombreReino();
                if (tipoTerritorio.equals("bosque") && (nombreReino.equals("Inglaterra") || nombreReino.equals("Sacro"))) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else if (tipoTerritorio.equals("campo abierto") && (nombreReino.equals("Francia") || nombreReino.equals("Sacro"))) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else if (tipoTerritorio.equals("montaña") && nombreReino.equals("Castilla") || nombreReino.equals("Aragon")) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else if (tipoTerritorio.equals("desierto") && nombreReino.equals("Moros")) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else if (tipoTerritorio.equals("playa") && nombreReino.equals("Sacro")) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                }
            }
        }
    }
    public static void crearEjercito(ArrayList<Ejercito> reinos, String nombreReino, Ejercito[][] tablero) {
        int numEjercitos = (int) (Math.random() * 10) + 1;
        for (int ejercitoId = 1; ejercitoId <= numEjercitos; ejercitoId++) {
            Ejercito ejercito = new Ejercito();
            ejercito.crearEjercito();
            ejercito.setNombreReino(nombreReino);
            reinos.add(ejercito);
            int fila;
            int columna;
            boolean posicionValida = false;
            while (!posicionValida) {
                fila = (int) (Math.random() * tablero.length);
                columna = (int) (Math.random() * tablero[0].length);
                if (tablero[fila][columna] == null) {
                    tablero[fila][columna] = ejercito;
                    posicionValida = true;
                }
            }
        }
    }
    public void imprimirTablero(Ejercito[][] tablero, ArrayList<Ejercito> reinoA, ArrayList<Ejercito> reinoB) {
        for (char columna = 'A'; columna <= 'J'; columna++) {
            System.out.print("       " + columna);
        }
        System.out.println();
        System.out.println("   _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print((i + 1 < 10 ? " " : "") + (i + 1));
            for (int j = 0; j < tablero[i].length; j++) {
                Ejercito ejercito = tablero[i][j];
                if (ejercito != null) {
                    String nombreReino = ejercito.getNombreReino();
                    int indexEjercito = -1;
                    if (reinoA.contains(ejercito)) {
                        indexEjercito = reinoA.indexOf(ejercito);
                    } else if (reinoB.contains(ejercito)) {
                        indexEjercito = reinoB.indexOf(ejercito);
                    }
                    if (indexEjercito != -1) {
                        int totalSoldados = ejercito.getNumeroSoldados();
                        int vidaTotal = ejercito.getSumaVidaTotal();
                        String info = totalSoldados + "-" + vidaTotal + "-" + nombreReino.charAt(0);
                        System.out.printf("|%1$-7s", info);
                    } else {
                        System.out.print("|_______");
                    }
                } else {
                    System.out.print("|_______");
                }
            }
            System.out.println("|");
        }
    }
    public void moverEjercito(Scanner sc, Ejercito[][] tablero, ArrayList<Ejercito> ejercitoMov, ArrayList<Ejercito> ejercitoOtro) {
        System.out.print("Ingrese la coordenada del ejército a mover (por ejemplo, d3): ");
        String coordenada = sc.next();
        int filaMov = Integer.parseInt(coordenada.substring(1)) - 1;
        char columnaChar = coordenada.charAt(0);
        int columnaMov = columnaChar - 'a';
        if (filaMov < 0 || filaMov >= tablero.length || columnaMov < 0 || columnaMov >= tablero[0].length) {
            System.out.println("Movimiento inválido: coordenada fuera del tablero.");
            return;
        }
        Ejercito ejercito = tablero[filaMov][columnaMov];
        if (ejercito == null || !ejercitoMov.contains(ejercito)) {
            System.out.println("Movimiento inválido: no hay un ejército del reino correspondiente en esa posición.");
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
            Ejercito ejercitoEnNuevaPos = tablero[nuevaFila][nuevaColumna];
            if (ejercitoOtro.contains(ejercitoEnNuevaPos)) {
                double probabilidadEjercito = ejercito.getSumaVidaTotal() * 100.0 / (ejercito.getSumaVidaTotal() + ejercitoEnNuevaPos.getSumaVidaTotal());
                double probabilidadEnemigo = ejercitoEnNuevaPos.getSumaVidaTotal() * 100.0 / (ejercito.getSumaVidaTotal() + ejercitoEnNuevaPos.getSumaVidaTotal());
                Ejercito ganadorBatalla = batalla(ejercito, ejercitoEnNuevaPos);
                System.out.println("¡Batalla!");
                System.out.println(ejercito.getNombreReino() + " - Vida: " + ejercito.getSumaVidaTotal() + " - Probabilidad de victoria: " + probabilidadEjercito + "%");
                System.out.println(ejercitoEnNuevaPos.getNombreReino() + " - Vida: " + ejercitoEnNuevaPos.getSumaVidaTotal() + " - Probabilidad de victoria: " + probabilidadEnemigo + "%");
                int vidaTotalGanador = sumarVidaSoldados(ejercitoMov);
                int vidaTotalPerdedor = sumarVidaSoldados(ejercitoOtro);
                int totalVida = vidaTotalGanador + vidaTotalPerdedor;
                if (ganadorBatalla == ejercito) {
                    tablero[nuevaFila][nuevaColumna] = ejercito;
                    tablero[filaMov][columnaMov] = null;
                    ejercitoOtro.remove(ejercitoEnNuevaPos);
                    for (Soldado soldado : ejercito.getMisSoldados()) {
                        soldado.setNivelVida(soldado.getNivelVida() + 1); 
                    }
                    ejercito.setSumaVidaTotal(ejercito.vidaTotalEjercito());
                    System.out.println("Ganador: " + ganadorBatalla.getNombreReino() + " - Vida: " + ejercito.vidaTotalEjercito());                 
                } else {
                    tablero[filaMov][columnaMov] = null;
                    ejercitoMov.remove(ejercito);
                    for (Soldado soldado : ejercitoEnNuevaPos.getMisSoldados()) {
                        soldado.setNivelVida(soldado.getNivelVida() + 1); 
                    }
                    ejercitoEnNuevaPos.setSumaVidaTotal(ejercitoEnNuevaPos.vidaTotalEjercito());
                    System.out.println("Ganador: " + ganadorBatalla.getNombreReino() + " - Vida: " + ejercitoEnNuevaPos.vidaTotalEjercito());
                }
                return;
            } else {
                System.out.println("Movimiento inválido: ya hay un ejército en la nueva posición.");
                return;
            }
        }
        tablero[filaMov][columnaMov] = null;
        tablero[nuevaFila][nuevaColumna] = ejercito;
    }
    public Ejercito batalla(Ejercito ejercito1, Ejercito ejercito2) {
        int totalVida = ejercito1.getSumaVidaTotal() + ejercito2.getSumaVidaTotal();
        double porcentaje1 = ejercito1.getSumaVidaTotal() * 100.0 / totalVida;
        double porcentaje2 = ejercito2.getSumaVidaTotal() * 100.0 / totalVida;
        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        if (numeroAleatorio <= porcentaje1) {
            return ejercito1;
        } else {
            return ejercito2;
        }
    }    
    public int contarSoldados(ArrayList<Ejercito> ejercitos) {
        int totalSoldados = 0;
        for (Ejercito ejercito : ejercitos) {
            totalSoldados += ejercito.getNumeroSoldados();
        }
        return totalSoldados;
    }
    public int sumarVidaSoldados(ArrayList<Ejercito> ejercitos) {
        int vidaTotal = 0;
        for (Ejercito ejercito : ejercitos) {
            vidaTotal += ejercito.getSumaVidaTotal();
        }
        return vidaTotal;
    }
}