public class Lancero extends Soldado {
    private int longitudLanza;
    private static int contadorLanceros = 0; 

    public Lancero() {
        contadorLanceros++;
    }
    public static void reiniciarContadores() {
        contadorLanceros = 0;
    }
    public static int getContadorLanceros() {
        return contadorLanceros;
    }
    public int getLongitudLanza() {
        return longitudLanza;
    }
    public void schiltrom() {
        setNivelDefensa(getNivelDefensa() + 1);
        System.out.println(getNombre() + " ha activado schiltrom. Nivel de defensa aumentado.");
    }
}