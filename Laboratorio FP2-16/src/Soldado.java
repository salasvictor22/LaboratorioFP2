public class Soldado {
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
	public void setActitud(String ac) {
		actitud = ac;
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
}