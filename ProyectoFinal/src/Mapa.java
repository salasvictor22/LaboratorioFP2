import java.util.Scanner;
public class Mapa {
	private Personaje[][] tablero;
    private Principal principal;
    private FantasmaTipo1 fantasmaTipo1;
    private FantasmaTipo2 fantasmaTipo2;
    private FantasmaTipo3 fantasmaTipo3;

    public Mapa() {
    	tablero = new Personaje[15][15];
        crearPersonajes();
    }
    public void crearPersonajes() {
        Scanner sc = new Scanner(System.in);
        // Crear personaje principal
        System.out.print("Introduce el nombre del personaje principal: ");
        String nombrePrincipal = sc.nextLine();
        principal = new Principal();
        principal.setNombre(nombrePrincipal);
        principal.setVida((int) (Math.random() * 3) + 1);
        principal.setFila(0);
        principal.setFila(0);      
        // Crear fantasmas
        fantasmaTipo1 = new FantasmaTipo1();
        fantasmaTipo2 = new FantasmaTipo2();
        fantasmaTipo3 = new FantasmaTipo3();
        tablero[0][0] = principal;
        tablero[fantasmaTipo1.getFila()][fantasmaTipo1.getColumna()] = fantasmaTipo1;
        tablero[fantasmaTipo2.getFila()][fantasmaTipo2.getColumna()] = fantasmaTipo2;
        tablero[fantasmaTipo3.getFila()][fantasmaTipo3.getColumna()] = fantasmaTipo3;
    }

    public void imprimirInformacionPersonajes() {
        System.out.println("Información del Personaje Principal:");
        System.out.println("Nombre: " + principal.getNombre());
        System.out.println("Vida: " + principal.getVida());
        System.out.println("Posición - Fila: " + principal.getFila() + ", Columna: " + principal.getColumna());
        System.out.println();

        System.out.println("Información del Fantasma Tipo 1:");
        imprimirInformacionFantasma(fantasmaTipo1);
        System.out.println();

        System.out.println("Información del Fantasma Tipo 2:");
        imprimirInformacionFantasma(fantasmaTipo2);
        System.out.println();

        System.out.println("Información del Fantasma Tipo 3:");
        imprimirInformacionFantasma(fantasmaTipo3);
    }

    public void imprimirInformacionFantasma(Fantasma fantasma) {
        System.out.println("Color: " + fantasma.getColor());
        System.out.println("Habilidad: " + fantasma.getHabilidad());
        System.out.println("Nivel de Ataque: " + fantasma.getNivelAtaque());
        System.out.println("Posición - Fila: " + fantasma.getFila() + ", Columna: " + fantasma.getColumna());
    }
    
    public void imprimirTablero() {
    	System.out.println();
    	System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
            	if(tablero[i][j] != null) {
            		Personaje personaje = tablero[i][j];
                    char representacion = obtenerInicialTipoPersonaje(personaje);
                    System.out.print(String.format("%1s", String.format("|%1s", representacion)));
            		
            	} else {
            		System.out.print("|_");
            	}
            }
            System.out.println("|");
        }
    }
   
    private char obtenerInicialTipoPersonaje(Personaje personaje) {
        if (personaje instanceof Principal) {
            return Character.toUpperCase(personaje.getNombre().charAt(0));
        } else if (personaje instanceof FantasmaTipo1) {
            return 'V';
        } else if (personaje instanceof FantasmaTipo2) {
            return 'R';
        } else if (personaje instanceof FantasmaTipo3) {
            return 'A';
        }
        return ' '; 
    }
}