public class FantasmaTipo2 extends Fantasma{
	
	public FantasmaTipo2() {
		setHabilidad("Teletransportarse");
        setNivelAtaque(1);
        asignarPosicionAleatoria();
        setColor("rojo");
	}
	private void asignarPosicionAleatoria() {
        setFila((int) (Math.random() * 15));
        setColumna((int) (Math.random() * 15));
    }
}
