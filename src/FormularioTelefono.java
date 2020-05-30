import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FormularioTelefono extends JFrame implements ActionListener {

    private JTextField txfPantalla;

    private JMenuBar mnuPrincipal;
    private JMenu mnuArchivo;
    private JMenuItem mnuGuardar;
    private JMenuItem mnuLeer;

    private JMenu mnuMovil;
    private JMenuItem mnuReset;
    private JMenuItem mnuSalir;

    private JMenu mnuOtros;
    private JMenuItem mnuAcercaDe;

    private JButton btn;
    private ArrayList<JButton> btnArray = new ArrayList<>();
    private String[] numeros = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#" };

    private JButton btnReset;

    public FormularioTelefono() {

        setTitle("Telefono");
        setLayout(null);
        setFocusable(true);
        requestFocus();
        agregarMenuFormulario();
        agregarTxfPantalla();
        agregarBotonesFormulario();
    }

    /**
     * Agrega la pantalla al formulario
     */
    private void agregarTxfPantalla() {
        txfPantalla = new JTextField();
        txfPantalla.setEditable(false);
        txfPantalla.setSize(170, 40);
        txfPantalla.setLocation(20, 10);
        add(txfPantalla);
    }

    public JTextField getTxfPantalla() {
        return this.txfPantalla;
    }

    /**
     * Agrega los botones al formulario y sus funcionalidades
     */
    public void agregarBotonesFormulario() {
        int x = 20;
        int y = 10;

        for (int i = 0; i < numeros.length; i++) {
            btn = new JButton(numeros[i]);
            btn.setSize(50, 50);
            btn.setLocation(x, y);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    String texto = ((JButton) e.getSource()).getText();
                    txfPantalla.setText(txfPantalla.getText() + texto);
                }

            });
            btn.addMouseListener(new EventosRaton());
            btnArray.add(btn);
            this.add(btn);

            if ((i) % 3 == 0) {
                y += 60;
                x = 20;
                btn.setLocation(x, y);
            }
            x += 60;

        }

        addKeyListener(new EventosTeclado());

        btnReset = new JButton("RESET");
        btnReset.setSize(170, 50);
        btnReset.setLocation(20, 310);
        btnReset.addActionListener(this);
        add(btnReset);
    }

    /**
     * Agrega el menú al formulario
     */
    public void agregarMenuFormulario() {

        mnuGuardar = new JMenuItem("Grabar número");
        mnuGuardar.setMnemonic('G');
        mnuGuardar.addActionListener(this);

        mnuLeer = new JMenuItem("Leer números");
        mnuLeer.setMnemonic('L');
        mnuLeer.addActionListener(this);

        mnuArchivo = new JMenu("Archivo");
        mnuArchivo.setMnemonic('H');
        mnuArchivo.add(mnuGuardar);
        mnuArchivo.add(mnuLeer);

        mnuReset = new JMenuItem("Reset");
        mnuReset.setMnemonic('R');
        mnuReset.addActionListener(this);

        mnuSalir = new JMenuItem("Salir");
        mnuSalir.setMnemonic('S');
        mnuSalir.addActionListener(this);

        mnuMovil = new JMenu("Movil");
        mnuMovil.setMnemonic('M');
        mnuMovil.add(mnuReset);
        mnuMovil.addSeparator();
        mnuMovil.add(mnuSalir);

        mnuAcercaDe = new JMenuItem("Acerca de..");
        mnuAcercaDe.setMnemonic('A');
        mnuAcercaDe.addActionListener(this);

        mnuOtros = new JMenu("Otros");
        mnuOtros.setMnemonic('O');
        mnuOtros.add(mnuAcercaDe);

        mnuPrincipal = new JMenuBar();
        mnuPrincipal.add(mnuArchivo);
        mnuPrincipal.add(mnuMovil);
        mnuPrincipal.add(mnuOtros);
        this.setJMenuBar(mnuPrincipal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnReset || e.getSource() == mnuReset) {
            for (JButton botones : btnArray) {
                botones.setBackground(null);
                botones.setForeground(null);
            }
            txfPantalla.setText("");
        }
        // TODO Auto-generated method stub
        if (e.getSource() == mnuGuardar) {

            FormularioCrearContacto f = new FormularioCrearContacto(this);
            f.pack();
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setVisible(true);

        }
        if (e.getSource() == mnuLeer) {
            Contacto p = new Contacto();
            try (Scanner f = new Scanner(new File("contactos\\Contactos.csv"))) {
                while (f.hasNext()) {
                    p.setNombre(f.nextLine());
                    p.setNumero(f.nextLine());
                    String texto = String.format("Nombre: %5s Numero: %s\n", p.getNombre(), p.getNumero());
                    JOptionPane.showMessageDialog(null, texto, "Contacto", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception er) {
                System.out.println("Error de acceso a archivo:" + er.getMessage());
            }
        }

        if (e.getSource() == mnuAcercaDe) {
           JOptionPane.showMessageDialog(null, "Programa creado por Emilio Briuela, Ejercicio 2 del Boletín 9 de Programación. V1.0", "Información", JOptionPane.INFORMATION_MESSAGE);

        }
        if (e.getSource() == mnuSalir) {
            int res = JOptionPane.showConfirmDialog(null, "Deseas cerrar el programa?", "Eventos Teclado",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.OK_OPTION) {
                dispose();
            }
        }
    }

    private class EventosTeclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            String c = Character.toString(e.getKeyChar());

            for (int i = 0; i < numeros.length; i++) {
                if (numeros[i].equals(c)) {
                    txfPantalla.setText(txfPantalla.getText() + c);
                }
                for (JButton boton : btnArray) {
                    if (boton.getText().equals(c)) {
                        boton.setBackground(Color.red);
                        boton.setForeground(Color.white);
                    }
                }
            }
        }
    }

    private class EventosRaton extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            if (((JButton) e.getSource()).getBackground() != Color.red) {
                ((JButton) e.getSource()).setBackground(Color.ORANGE);
                ((JButton) e.getSource()).setForeground(Color.white);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            if (((JButton) e.getSource()).getBackground() != Color.red) {
                ((JButton) e.getSource()).setBackground(null);
                ((JButton) e.getSource()).setForeground(Color.black);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            ((JButton) e.getSource()).setBackground(Color.red);
            ((JButton) e.getSource()).setForeground(Color.white);
        }
    }

}