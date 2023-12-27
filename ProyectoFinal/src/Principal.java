public class Principal extends Personaje {
	private int vida;
	private static int personasRescatadas = 0;
	
	public static void reiniciarContador() {
		personasRescatadas = 0;
    }
	public static int getContador() {
        return personasRescatadas;
    }
	public void setVida(int v) {
		vida = v;
	}
	public int getVida() {
		return vida;
	}
}
