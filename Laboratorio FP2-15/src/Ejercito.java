import java.util.ArrayList;
public class Ejercito {
    private String nombreReino;
    private ArrayList<Soldado> misSoldados;

    public Ejercito(String nombreReino) {
        this.nombreReino = nombreReino;
        misSoldados = new ArrayList<>();
    }  
    public void crearEjercito() {
        int numSoldados = (int) (Math.random() * 10) + 1;
        for (int i = 0; i < numSoldados; i++) {
            Soldado soldado = new Soldado();
            soldado.setNombre("Soldado " + (i + 1));
            soldado.setNivelAtaque((int) (Math.random() * 5) + 1);
            soldado.setNivelDefensa((int) (Math.random() * 5) + 1);
            soldado.setVidaActual((int) (Math.random() * 5) + 1);
            soldado.setVelocidad((int) (Math.random() * 5) + 1);
            soldado.setActitud("ofensiva");
            soldado.setFila((int) (Math.random() * 10) + 1);
            soldado.setColumna((int) (Math.random() * 10) + 1);
            soldado.setNivelVida((int) (Math.random() * 5) + 1);
            agregarSoldado(soldado);
        }
    }
    public void agregarSoldado(Soldado soldado) {
        if (misSoldados.size() < 10) {
            misSoldados.add(soldado);
        } else {
            System.out.println("El ejército está completo. No se pueden agregar más soldados.");
        }
    }

    public void eliminarSoldado(int indice) {
        if (indice >= 0 && indice < misSoldados.size()) {
            misSoldados.remove(indice);
        } else {
            System.out.println("Índice de soldado no válido.");
        }
    }
    
    public void modificarSoldado(int indice, String nombre, int nivelAtaque, int nivelDefensa,
                                int velocidad, int fila, int columna, int nivelVida) {
        if (indice >= 0 && indice < misSoldados.size()) {
            Soldado soldado = misSoldados.get(indice);
            soldado.setNombre(nombre);
            soldado.setNivelVida(nivelVida);
            soldado.setNivelAtaque(nivelAtaque);
            soldado.setNivelDefensa(nivelDefensa);
            soldado.setVelocidad(velocidad);            
            soldado.setFila(fila);
            soldado.setColumna(columna);           
        } else {
            System.out.println("Índice de soldado no válido.");
        }
    }
    public void crearSoldado(String nombre, int nivelVida, int nivelAtaque, int nivelDefensa,
            int velocidad, int fila, int columna) {
    	Soldado soldado = new Soldado();
        soldado.setNombre(nombre);
        soldado.setNivelVida(nivelVida);
        soldado.setNivelAtaque(nivelAtaque);
        soldado.setNivelDefensa(nivelDefensa);
        soldado.setVelocidad(velocidad);            
        soldado.setFila(fila);
        soldado.setColumna(columna); 
        agregarSoldado(soldado);   
    }
    public Soldado getSoldadoConMayorAtaque() {
        Soldado soldadoConMayorAtaque = null;
        int maxAtaque = -1;

        for (Soldado soldado : misSoldados) {
            if (soldado.getNivelAtaque() > maxAtaque) {
                maxAtaque = soldado.getNivelAtaque();
                soldadoConMayorAtaque = soldado;
            }
        }

        return soldadoConMayorAtaque;
    }
    public void verRankingPoder() {
        System.out.println("Ranking de poder por nivel de vida:");
        int n = misSoldados.size();
        boolean ordenado;
        for (int i = 0; i < n - 1; i++) {
            ordenado = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (misSoldados.get(j).getNivelVida() < misSoldados.get(j + 1).getNivelVida()) {
                    Soldado temp = misSoldados.get(j);
                    misSoldados.set(j, misSoldados.get(j + 1));
                    misSoldados.set(j + 1, temp);
                    ordenado = true;
                }
            }
            if (!ordenado) {
                break;
            }
        }
        for (int i = 0; i < misSoldados.size(); i++) {
            System.out.println("Posición " + (i + 1) + ": " + misSoldados.get(i).getNombre() + " - Vida: " + misSoldados.get(i).getNivelVida());
        }
    }
    public String toString() {
        String result = "Ejército del Reino: " + nombreReino + "\n";       
        for (Soldado soldado : misSoldados) {
            result += "Nombre: " + soldado.getNombre() + "\n";
            result += "Nivel de Ataque: " + soldado.getNivelAtaque() + "\n";
            result += "Nivel de Defensa: " + soldado.getNivelDefensa() + "\n";
            result += "Vida Actual: " + soldado.getVidaActual() + "\n";
            result += "Velocidad: " + soldado.getVelocidad() + "\n";
            result += "Fila: " + soldado.getFila() + "\n";
            result += "Columna: " + soldado.getColumna() + "\n";
            result += "Nivel de Vida: " + soldado.getNivelVida() + "\n";
            result += "\n";
        }        
        return result;
    }
    public ArrayList<Soldado> getMisSoldados() {
        return misSoldados;
    }
}