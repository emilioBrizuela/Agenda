import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class Telefono extends JFrame implements KeyListener, ActionListener {

    private JTextField txf;
    private JButton btn;
    private String[] numeros = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#" };

    public Telefono() {
        setTitle("Telefono");
        setLayout(null);
        setFocusable(true);
        requestFocus();

        txf = new JTextField();
        txf.setEditable(false);
        txf.setSize(170,40);
        txf.setLocation(20, 10);
        add(txf);

        int x = 20;
        int y = 10;
        
        for (int i = 0; i < numeros.length; i++) {
            btn = new JButton(numeros[i]);
            btn.setSize(50, 50);
            btn.setLocation(x, y);
            btn.addActionListener(this);
            btn.addMouseListener(new EventosRaton());
            this.add(btn);
            
            if ((i) % 3 == 0) {
                y += 60;
                x = 20;
                btn.setLocation(x, y);
            }
            x += 60;
            
            
        }
        addKeyListener(this);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            JOptionPane.showMessageDialog(null, "Has pulsado Enter");
        }
        if (e.getKeyCode() == KeyEvent.VK_0 || e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_2
                || e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_5
                || e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_8
                || e.getKeyCode() == KeyEvent.VK_9 || e.getKeyCode() == KeyEvent.VK_NUMBER_SIGN
                || e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            // System.err.println("PRESIONE");
            btn.setBackground(Color.red);
            btn.setForeground(Color.white);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    private class EventosRaton extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            btn.setBackground(Color.ORANGE);
            btn.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            btn.setBackground(Color.LIGHT_GRAY);
            btn.setForeground(Color.black);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            btn.setBackground(Color.red);
            btn.setForeground(Color.white);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == btn){
            txf.setText(txf.getText()+btn.getText());
        }
    }
}