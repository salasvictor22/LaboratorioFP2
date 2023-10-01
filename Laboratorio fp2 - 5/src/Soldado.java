public class Soldado {
	private String nombre;
	private int fila;
	private int columna;
	private int nivelVida;
	// Metodos mutadores
	public void setNombre(String n){
		nombre = n;
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
	// Metodos accesores
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
}
