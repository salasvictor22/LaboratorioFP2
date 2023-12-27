public class FantasmaTipo3 extends Fantasma{
	
	public FantasmaTipo3() {
		setHabilidad("matar de una");
        setNivelAtaque(1);
        asignarPosicionAleatoria();
        setColor("azul");
	}
	private void asignarPosicionAleatoria() {
        setFila((int) (Math.random() * 15));
        setColumna((int) (Math.random() * 15));
    }
}
