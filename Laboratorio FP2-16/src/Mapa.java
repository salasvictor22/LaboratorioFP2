import java.util.ArrayList;
public class Mapa {
    private Soldado[][] tablero;
    private String tipoTerritorio;

    public Mapa(String tipoTerritorio) {
        this.tablero = new Soldado[10][10];
        this.tipoTerritorio = tipoTerritorio;        
        posicionarEjercitos();
    }
    private void posicionarEjercitos() {
        for (int i = 0; i < 2; i++) { 
            ArrayList<Soldado> ejercito = new ArrayList<>();
            int numSoldados = (int) (Math.random() * 10) + 1; 
            for (int j = 0; j < numSoldados; j++) {
                //ejercito.add(new Soldado("Soldado " + (j + 1)));
            }
            int fila, columna;
            do {
                fila = (int) (Math.random() * 10);
                columna = (int) (Math.random() * 10);
            } while (tablero[fila][columna] != null);
            for (Soldado soldado : ejercito) {
                soldado.setTerritorioBeneficiado(tipoTerritorio);
            }
            tablero[fila][columna] = ejercito.get(0);
        }
    }
    public void imprimirMapa() {
        System.out.print("  ");
        for (char columna = 'A'; columna <= 'J'; columna++) {
            System.out.print("  " + columna);
        }
        System.out.println();
        System.out.println("   __ __ __ __ __ __ __ __ __ __ ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print((i + 1 < 10 ? " " : "") + (i + 1));
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null) {
                    Soldado soldado = tablero[i][j];
                    char letra = (soldado.getTerritorioBeneficiado().equals(tipoTerritorio)) ? 's' : 'c';
                    System.out.print("|" + letra + soldado.getNivelVida());
                } else {
                    System.out.print("|__");
                }
            }
            System.out.println("|");
        }
    }
}