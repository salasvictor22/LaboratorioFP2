public class Fantasma extends Personaje {
	private int nivelAtaque;
	private String color;
	private String habilidad;

	public void setColor(String c) {
		color = c;
	}
	public void setNivelAtaque(int ataque) {
		nivelAtaque = ataque;
	}
	
	public void setHabilidad(String h) {
		habilidad = h;
	}
	public String getColor() {
		return color;
	}
	public String getHabilidad() {
		return habilidad;
	}
	public int getNivelAtaque() {
		return nivelAtaque;
	}
}
