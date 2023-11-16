public class Arquero extends Soldado {
    private int numeroFlechas;

    public void setNumeroFlechas(int flechas) {
        numeroFlechas = flechas;
    }
    public int getNumeroFlechas() {
        return numeroFlechas;
    }
    public void dispararFlecha() {
        if (numeroFlechas > 0) {
            System.out.println("Arquero disparó una flecha.");
            numeroFlechas--;
            if (numeroFlechas == 0) {
                recargarFlechas();
            }
        } else {
            System.out.println("Arquero no tiene flechas para disparar. Recargando automáticamente.");
            recargarFlechas();
        }
    }
    private void recargarFlechas() {      
        numeroFlechas = (int) (Math.random() * 5) + 1;
        System.out.println("Arquero recargó flechas. Nuevo total de flechas: " + numeroFlechas);
    }
}