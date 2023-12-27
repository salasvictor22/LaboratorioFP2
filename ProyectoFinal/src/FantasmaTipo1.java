public class FantasmaTipo1 extends Fantasma{
	
	public FantasmaTipo1() {
		setHabilidad("Moverse 2 espacios");
        setNivelAtaque(1);
        asignarPosicionAleatoria();
        setColor("verde");
	}
	private void asignarPosicionAleatoria() {
        setFila((int) (Math.random() * 10));
        setColumna((int) (Math.random() * 10));
    }
}
