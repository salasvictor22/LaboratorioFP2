public class Soldado {
	private static int totalSoldadosCreados = 0;
    private static int soldadosEjercito1 = 0;
    private static int soldadosEjercito2 = 0;
    public static final int MAX_SOLDADOS_POR_EJERCITO = 10;
    private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int vidaActual;
	private int velocidad;
	private String actitud;
	private boolean vive;
	private int fila;
	private int columna;
	private int nivelVida;
	
	public static void incrementarSoldadosEjercito1() {
        soldadosEjercito1++;
        totalSoldadosCreados++;
    }
    public static void incrementarSoldadosEjercito2() {
        soldadosEjercito2++;
        totalSoldadosCreados++;
    }
    public static void reducirSoldadosEjercito1() {
        soldadosEjercito1--;
        totalSoldadosCreados--;
    }
    public static void reducirSoldadosEjercito2() {
        soldadosEjercito2--;
        totalSoldadosCreados--;
    }	
	public void setNombre(String n){
		nombre = n;
	}
	public void setNivelAtaque(int ataque){		
		nivelAtaque = ataque;
	}
	public void setNivelDefensa(int defensa){		
		nivelDefensa = defensa;
	}
	public void setVidaActual(int vidaAc) {
        vidaActual = vidaAc;
    }
	public void setFila(int f){		
		fila = f;
	}
	public void setColumna(int c){		
		columna = c;
	}
	public void setNivelVida(int vida){		
		nivelVida = vida;
	}
	public void setVelocidad(int veloci){		
		velocidad = veloci;
	}
	public String getNombre(){
		return nombre;
	}
	public int getFila(){
		return fila;
	}
	public int getColumna(){
		return columna;
	}
	public int getNivelVida(){
		return nivelVida;
	}
	public int getNivelAtaque(){
		return nivelAtaque;
	}
	public int getNivelDefensa(){
		return nivelDefensa;
	}
	public int getVelocidad(){
		return velocidad;
	}
	public int getVidaActual() {
		return vidaActual;
	}
	public void atacar() {
        avanzar();
        actitud = "ofensiva";
    }
    public void defender() {
        velocidad = 0;
        actitud = "defensiva";
    }
    public void avanzar() {
        velocidad++;
    }
    public void retroceder() {
        if (velocidad > 0) {
            velocidad = 0;
            actitud = "defensiva";
        } else {
            velocidad--;
        }
    }
    public void serAtacado(int puntosDaño) {
        vidaActual -= puntosDaño;
        if (vidaActual <= 0) {
            morir();
        }
    }
    public void huir() {
        velocidad += 2;
        actitud = "fuga";
    }
    public void morir() {
        vive = false;
    }   	   
    public static int getTotalSoldadosCreados() {
        return totalSoldadosCreados;
    }

    public static int getSoldadosEjercito1() {
        return soldadosEjercito1;
    }

    public static int getSoldadosEjercito2() {
        return soldadosEjercito2;
    }
}