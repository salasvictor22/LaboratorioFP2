import java.util.ArrayList;
public class Ejercito {
    private String nombreReino;
    private ArrayList<Soldado> misSoldados;
    private int fila;
    private int columna;
    private int numeroSoldados;
    private int sumaVidaTotal;
    
    public Ejercito() {       
        misSoldados = new ArrayList<>();
        numeroSoldados = 0;
        sumaVidaTotal = 0;      
    }  
    public void crearEjercito() {   	
    	 misSoldados = new ArrayList<>();
    	 numeroSoldados = 0;
         sumaVidaTotal = 0;
         int numSoldados = (int)(Math.random() * 10) + 1;
         for (int i = 0; i < numSoldados; i++) {
             Soldado soldado = new Soldado();
             soldado.setNombre("Soldado " + (i + 1));
             soldado.setNivelAtaque((int)(Math.random() * 5) + 1);
             soldado.setNivelDefensa((int)(Math.random() * 5) + 1);
             soldado.setVidaActual((int)(Math.random() * 5) + 1);
             soldado.setVelocidad((int)(Math.random() * 5) + 1);
             soldado.setActitud("ofensiva");
             soldado.setFila((int)(Math.random() * 10) + 1);
             soldado.setColumna((int)(Math.random() * 10) + 1);
             soldado.setNivelVida((int)(Math.random() * 5) + 1);           
             misSoldados.add(soldado);
             numeroSoldados++;
             sumaVidaTotal += soldado.getNivelVida();
         }
    }
    public void agregarSoldado(Soldado soldado) {
        if (misSoldados.size() < 10) {
            misSoldados.add(soldado);
        } else {
            System.out.println("El ejército está completo. No se pueden agregar más soldados.");
        }
    }
    public int vidaTotalEjercito() {
        int vidaTotal = 0;
        for (Soldado soldado : misSoldados) {
            vidaTotal += soldado.getNivelVida();
        }
        return vidaTotal;
    }
    public ArrayList<Soldado> getMisSoldados() {
        return misSoldados;
    }
    public void setFila(int f) {
        fila = f;
    }

    public void setColumna(int c) {
        columna = c;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    public void setNombreReino(String nombreReino) {
        this.nombreReino = nombreReino;
    }
    public String getNombreReino() {
        return nombreReino;
    }
    public int getNumeroSoldados() {
        return numeroSoldados;
    }

    public int getSumaVidaTotal() {
        return sumaVidaTotal;
    }
}