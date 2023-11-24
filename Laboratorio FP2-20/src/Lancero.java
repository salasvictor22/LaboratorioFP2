public class Lancero extends Soldado {
    private int longitudLanza;
    
    public void setLongitudLanza(int l) {
    	longitudLanza = l;
    }
    public int getLongitudLanza() {
        return longitudLanza;
    }
    public void schiltrom() {
        setNivelDefensa(getNivelDefensa() + 1);
        System.out.println(getNombre() + " ha activado schiltrom. Nivel de defensa aumentado.");
    }
}