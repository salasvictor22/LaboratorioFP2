public class Caballero extends Soldado {
    private String tipoArmadura;
    private String arma;
    private boolean montando;
    private static int contadorCaballeros = 0; 

    public Caballero() {
        super();
        contadorCaballeros++;
    }
    public static void reiniciarContadores() {
        contadorCaballeros = 0;
    }
    public static int getContadorCaballeros() {
        return contadorCaballeros;
    }
    public void setTipoArmadura(String tipo) {
        tipoArmadura = tipo;
    }

    public String getTipoArmadura() {
        return tipoArmadura;
    }
    public void setMontando(boolean estado) {
        montando = estado;
    }

    public boolean isMontando() {
        return montando;
    }
    public void montar() {
        if (isMontando()) {
            System.out.println("El caballero ya está montando.");
        } else {
            setMontando(true);
            setActitud("montando");
            cambiarArma("lanza");
            System.out.println("El caballero ha montado y cambió a la lanza.");
        }
    }

    public void desmontar() {
        if (!isMontando()) {
            System.out.println("El caballero ya está desmontado.");
        } else {
            setMontando(false);
            setActitud("desmontado");
            cambiarArma("espada");
            defender();
            System.out.println("El caballero ha desmontado, cambió a la espada y defendió.");
        }
    }

    public void envestir() {
        if (isMontando()) {
            atacar();
            atacar();
            atacar(); 
            System.out.println("El caballero ha envestido y atacado 3 veces estando montando.");
        } else {
            atacar();
            atacar();
            System.out.println("El caballero ha envestido y atacado 2 veces estando desmontado.");
        }
    }

    private void cambiarArma(String nuevaArma) {
        arma = nuevaArma;
        System.out.println("El caballero cambió su arma a: " + nuevaArma);
    }
    public void defender() {
        super.defender();
        System.out.println("Caballero defendiéndose con su armadura.");
    }
}