import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Mapa2 extends JFrame {

    private JButton[][] botones;
    private Random random;

    private Principal principal;
    private FantasmaTipo1 fantasmaTipo1;
    private FantasmaTipo2 fantasmaTipo2;
    private FantasmaTipo3 fantasmaTipo3;

    public Mapa2() {
        setTitle("Tablero con Personaje y Fantasmas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(10, 10));
        botones = new JButton[10][10];
        random = new Random();
        crearPersonajes();
        imprimirInformacionPersonajes();
        initBotones();
        setVisible(true);
    }

    private void initBotones() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBackground(Color.WHITE);
                botones[i][j].addActionListener(new BotonActionListener(i, j));
                add(botones[i][j]);
            }
        }
        colocarIconos();
    }

    private void crearPersonajes() {
        // Crear personaje principal
        String nombrePrincipal = JOptionPane.showInputDialog("Introduce el nombre del personaje principal:");
        principal = new Principal();
        principal.setNombre(nombrePrincipal);
        principal.setVida((int) (Math.random() * 3) + 1);
        principal.setFila(0);
        principal.setColumna(0);

        // Crear fantasmas
        fantasmaTipo1 = new FantasmaTipo1();
        fantasmaTipo2 = new FantasmaTipo2();
        fantasmaTipo3 = new FantasmaTipo3();
    }

    private void imprimirInformacionPersonajes() {
        JOptionPane.showMessageDialog(null, "Información del Personaje Principal:\n"
                + "Nombre: " + principal.getNombre() + "\n"
                + "Vida: " + principal.getVida() + "\n"
                + "Posición - Fila: " + principal.getFila() + ", Columna: " + principal.getColumna());
        JOptionPane.showMessageDialog(null, "Información del Fantasma Tipo 1:\n"
                + "Color: " + fantasmaTipo1.getColor() + "\n"
                + "Habilidad: " + fantasmaTipo1.getHabilidad() + "\n"
                + "Nivel de Ataque: " + fantasmaTipo1.getNivelAtaque() + "\n"
                + "Posición - Fila: " + fantasmaTipo1.getFila() + ", Columna: " + fantasmaTipo1.getColumna());
        JOptionPane.showMessageDialog(null, "Información del Fantasma Tipo 2:\n"
                + "Color: " + fantasmaTipo2.getColor() + "\n"
                + "Habilidad: " + fantasmaTipo2.getHabilidad() + "\n"
                + "Nivel de Ataque: " + fantasmaTipo2.getNivelAtaque() + "\n"
                + "Posición - Fila: " + fantasmaTipo2.getFila() + ", Columna: " + fantasmaTipo2.getColumna());
        JOptionPane.showMessageDialog(null, "Información del Fantasma Tipo 3:\n"
                + "Color: " + fantasmaTipo3.getColor() + "\n"
                + "Habilidad: " + fantasmaTipo3.getHabilidad() + "\n"
                + "Nivel de Ataque: " + fantasmaTipo3.getNivelAtaque() + "\n"
                + "Posición - Fila: " + fantasmaTipo3.getFila() + ", Columna: " + fantasmaTipo3.getColumna());
    }

    private class BotonActionListener implements ActionListener {
        private int fila;
        private int columna;

        public BotonActionListener(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        public void actionPerformed(ActionEvent e) {
            if (esCeldaAdyacente(fila, columna)) {
                borrarIconos();
                principal.setFila(fila);
                principal.setColumna(columna);
                moverFantasmasAleatoriamente();
                colocarIconos();
            }
        }
    }

    private boolean esCeldaAdyacente(int filaClic, int columnaClic) {
        return (Math.abs(filaClic - principal.getFila()) == 1 && columnaClic == principal.getColumna()) ||
                (Math.abs(columnaClic - principal.getColumna()) == 1 && filaClic == principal.getFila());
    }

    private void moverFantasmasAleatoriamente() {
        moverFantasmaAleatoriamente(fantasmaTipo1);
        moverFantasmaAleatoriamente(fantasmaTipo2);
        moverFantasmaAleatoriamente(fantasmaTipo3);
    }

    private void moverFantasmaAleatoriamente(Fantasma fantasma) {
        int movimiento = random.nextInt(4);
        switch (movimiento) {
            case 0:
                if (fantasma.getFila() > 0) {
                    fantasma.setFila(fantasma.getFila() - 1);
                }
                break;
            case 1:
                if (fantasma.getFila() < 9) {
                    fantasma.setFila(fantasma.getFila() + 1);
                }
                break;
            case 2:
                if (fantasma.getColumna() > 0) {
                    fantasma.setColumna(fantasma.getColumna() - 1);
                }
                break;
            case 3:
                if (fantasma.getColumna() < 9) {
                    fantasma.setColumna(fantasma.getColumna() + 1);
                }
                break;
        }
    }
    private void colocarIconos() {
        ImageIcon iconoPersonaje = cargarIcono("D:\\Unsa semestre 2\\programas\\imagen personajes\\cazador.png");
        ImageIcon iconoFantasma1 = cargarIcono("D:\\Unsa semestre 2\\programas\\imagen personajes\\verde.png");
        ImageIcon iconoFantasma2 = cargarIcono("D:\\Unsa semestre 2\\programas\\imagen personajes\\rojo.png");
        ImageIcon iconoFantasma3 = cargarIcono("D:\\Unsa semestre 2\\programas\\imagen personajes\\azul.jpg");
        botones[principal.getFila()][principal.getColumna()].setIcon(iconoPersonaje);
        botones[fantasmaTipo1.getFila()][fantasmaTipo1.getColumna()].setIcon(iconoFantasma1);
        botones[fantasmaTipo2.getFila()][fantasmaTipo2.getColumna()].setIcon(iconoFantasma2);
        botones[fantasmaTipo3.getFila()][fantasmaTipo3.getColumna()].setIcon(iconoFantasma3);
    }
    private void borrarIconos() {
        botones[principal.getFila()][principal.getColumna()].setIcon(null);
        botones[fantasmaTipo1.getFila()][fantasmaTipo1.getColumna()].setIcon(null);
        botones[fantasmaTipo2.getFila()][fantasmaTipo2.getColumna()].setIcon(null);
        botones[fantasmaTipo3.getFila()][fantasmaTipo3.getColumna()].setIcon(null);
    }
    private ImageIcon cargarIcono(String ruta) {
        try {
            return new ImageIcon(ruta);
        } catch (Exception e) {            
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        new Mapa2();
    }
}