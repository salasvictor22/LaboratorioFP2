// Ejercicio - 1
// Autor: Juan Victor Salas Aguilar
// Colaboro: Marco Aedo
// Tiempo:
import java.util.ArrayList;
public class Ejercicio1 {
    public static void main(String[] args) {
        ArrayList<Espadachin> espadachines = new ArrayList<>();
        ArrayList<Arquero> arqueros = new ArrayList<>();
        ArrayList<Caballero> caballeros = new ArrayList<>();
        
        ArrayList<Soldado> ejercito1 = crearEjercito1(espadachines, arqueros, caballeros);
        ArrayList<Soldado> ejercito2 = crearEjercito2(espadachines, arqueros, caballeros);

        System.out.println("Ejército 1:");
        mostrarEjercito(ejercito1);
        System.out.println("\nEjército 2:");
        mostrarEjercito(ejercito2);
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