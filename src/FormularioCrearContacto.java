import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FormularioCrearContacto extends JDialog {

    private JLabel lblNombre;
    private JTextField txtNombre;
    private JButton btnGuardar;

    public FormularioCrearContacto(FormularioTelefono f) {
        super(f, true);
        setLayout(new FlowLayout());

        lblNombre = new JLabel("NOMBRE:");
        add(lblNombre);

        txtNombre = new JTextField(20);
        add(txtNombre);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Contacto c = new Contacto();
                c.setNombre(txtNombre.getText());
                System.err.println(f.getTxfPantalla().getText());
                c.setNumero(f.getTxfPantalla().getText());
                guardarDato(c, "Contactos\\contactos.csv");
                JOptionPane.showMessageDialog(null, "Contacto Creado", "Contacto Nuevo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }

        });
        add(btnGuardar);
    }

    public static boolean guardarDato(Contacto c, String archivo) {
        try (PrintWriter f = new PrintWriter(new FileWriter(archivo, true))) {
            // f.printf("%s,%d%n",c.getNombre(),c.getNumero());
            f.println(c.getNombre());
            f.println(c.getNumero());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}