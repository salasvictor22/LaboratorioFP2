public class Personaje {
    private String nombre;
    private int fila;
    private int columna;

    public void setNombre(String n) {
    	nombre = n;
    }
    public void setFila(int f) {
    	fila = f;
    }
    public void setColumna(int c) {
    	columna = c;
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
}