public class Soldado {
	private static int totalSoldadosCreados = 0;
    private static int soldadosCreadosPorEjercito1 = 0;
    private static int soldadosCreadosPorEjercito2 = 0;
    public static final int MAX_SOLDADOS_POR_EJERCITO = 10;
    	
	private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int velocidad;
	private String actitud;
	private boolean vive;
	private int fila;
	private int columna;
	private int nivelVida;
	private int ejercito;
	
	public Soldado(int ejercito) {
        this.ejercito = ejercito;
        totalSoldadosCreados++;
        if (ejercito == 1) {
            soldadosCreadosPorEjercito1++;
        } else if (ejercito == 2) {
            soldadosCreadosPorEjercito2++;
        }
        this.nivelAtaque = (int) (Math.random() * 5 + 1 );
        this.nivelDefensa = (int) (Math.random() * 5 + 1 );
        this.velocidad = (int) (Math.random() * 5 + 1 );
        this.nivelVida = (int) (Math.random() * 5 + 1 );
        this.fila = (int) (Math.random() * 10 + 1 );
        this.columna = (int) (Math.random() * 10 + 1 );      
	}
	
	public void setNombre(String n){
		nombre = n;
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

    public static int getSoldadosCreadosPorEjercito1() {
        return soldadosCreadosPorEjercito1;
    }

    public static int getSoldadosCreadosPorEjercito2() {
        return soldadosCreadosPorEjercito2;
    }
}