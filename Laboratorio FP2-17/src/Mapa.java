public class Mapa {
    private String[][] tablero = new String[10][10];
    private String tipoTerritorio;
    private Ejercito[] ejercitos = new Ejercito[2];

    public void generarMapa() {
    	tipoTerritorio = obtenerTerritorio();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = tipoTerritorio;
            }
        }
        for (Ejercito ejercito : ejercitos) {
            for (Soldado soldado : ejercito.getMisSoldados()) {
                if (tipoTerritorio.equals("bosque") && (ejercito.getNombreReino().equals("Inglaterra") || ejercito.getNombreReino().equals("Sacro Imperio Romano-Germanico"))) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else if (tipoTerritorio.equals("campo abierto") && (ejercito.getNombreReino().equals("Francia") || ejercito.getNombreReino().equals("Sacro Imperio Romano-Germanico"))) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else if (tipoTerritorio.equals("montaña") && ejercito.getNombreReino().equals("Castilla-Aragón")) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else if (tipoTerritorio.equals("desierto") && ejercito.getNombreReino().equals("Moros")) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                } else if (tipoTerritorio.equals("playa") && (ejercito.getNombreReino().equals("Sacro Imperio Romano-Germanico"))) {
                    soldado.setNivelVida(soldado.getNivelVida() + 1);
                }
            }
        }
    }
    private String obtenerTerritorio() {
        String[] territorios = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
        int territorioIndex = (int) (Math.random() * territorios.length);
        return territorios[territorioIndex];
    }

    public void posicionarEjercitos() {
        
    }

    public void imprimirMapa() {
        
    }
}