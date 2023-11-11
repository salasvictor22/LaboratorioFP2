import java.util.ArrayList;
public class Mapa {
    private String tipoTerritorio;
    private String[][] tablero;
    private ArrayList<Ejercito> reinoA;
    private ArrayList<Ejercito> reinoB;
    private String[] nombresReinos = {"Inglaterra", "Francia", "Sacro", "Castilla", "Aragon", "Moros"};
    private String[] tiposTerritorio = {"bosque", "campo abierto", "montaña", "desierto", "playa"};

    public Mapa() {
        this.tablero = new String[10][10];
        reinoA = new ArrayList<>();
        reinoB = new ArrayList<>();
    }
    public void iniciarJuego() {
    	tipoTerritorio = tiposTerritorio[(int) (Math.random() * tiposTerritorio.length)];
        System.out.println("El tipo de territorio es: " + this.tipoTerritorio);
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
        Ejercito ganadorSoldados = determinarGanadorPorCantidadSoldados();
        if (ganadorSoldados != null) {
            System.out.println("El ganador por cantidad de soldados es: " + ganadorSoldados.getNombreReino());
        } else {
            System.out.println("Empate en la cantidad de soldados.");
        }
        Ejercito ganadorVida = determinarGanadorPorVidaSoldados();
        if (ganadorVida != null) {
            System.out.println("El ganador por la vida de los soldados es: " + ganadorVida.getNombreReino());
        } else {
            System.out.println("Empate en la vida de los soldados.");
        }
        Ejercito ganadorEjercitos = determinarGanadorPorNumeroEjercitos();
        if (ganadorEjercitos != null) {
            System.out.println("El ganador por número de ejércitos es: " + ganadorEjercitos.getNombreReino());
        } else {
            System.out.println("Empate en el número de ejércitos.");
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
    public static void crearEjercito(ArrayList<Ejercito> reinos, String nombreReino, String[][] tablero) {
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
                    tablero[fila][columna] = nombreReino + " " + ejercitoId;
                    posicionValida = true;
                }
            }
        }
    }
    public void imprimirTablero(String[][] tablero, ArrayList<Ejercito> reinoA, ArrayList<Ejercito> reinoB) {
    	for (char columna = 'A'; columna <= 'J'; columna++) {
            System.out.print("       " + columna);
        }
        System.out.println();
        System.out.println("   _______ _______ _______ _______ _______ _______ _______ _______ _______ _______ ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print((i + 1 < 10 ? " " : "") + (i + 1));
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null) {
                    String reinoEjercito = tablero[i][j];
                    String[] partes = reinoEjercito.split(" ");
                    String nombreReino = partes[0];
                    int indiceEjercito = Integer.parseInt(partes[1]);
                    Ejercito ejercitoEncontrado = null;
                    for (Ejercito ejercito : reinoA) {
                        if (ejercito.getNombreReino().equals(nombreReino) && reinoA.indexOf(ejercito) == indiceEjercito) {
                            ejercitoEncontrado = ejercito;
                            break;
                        }
                    }
                    if (ejercitoEncontrado == null) {
                        for (Ejercito ejercito : reinoB) {
                            if (ejercito.getNombreReino().equals(nombreReino) && reinoB.indexOf(ejercito) == indiceEjercito) {
                                ejercitoEncontrado = ejercito;
                                break;
                            }
                        }
                    }
                    if (ejercitoEncontrado != null) {
                        int totalSoldados = ejercitoEncontrado.getNumeroSoldados();
                        int vidaTotal = ejercitoEncontrado.getSumaVidaTotal();
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
    public Ejercito determinarGanadorPorNumeroEjercitos() {
        if (reinoA.size() > reinoB.size()) {
            return reinoA.get(0); 
        } else if (reinoB.size() > reinoA.size()) {
            return reinoB.get(0); 
        } else {
            return null; 
        }
    }

    public Ejercito determinarGanadorPorCantidadSoldados() {
        int soldadosReinoA = contarSoldados(reinoA);
        int soldadosReinoB = contarSoldados(reinoB);

        if (soldadosReinoA > soldadosReinoB) {
            return reinoA.get(0);
        } else if (soldadosReinoB > soldadosReinoA) {
            return reinoB.get(0);
        } else {
            return null;
        }
    }

    public Ejercito determinarGanadorPorVidaSoldados() {
        int vidaReinoA = sumarVidaSoldados(reinoA);
        int vidaReinoB = sumarVidaSoldados(reinoB);

        if (vidaReinoA > vidaReinoB) {
            return reinoA.get(0);
        } else if (vidaReinoB > vidaReinoA) {
            return reinoB.get(0); 
        } else {
            return null;
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