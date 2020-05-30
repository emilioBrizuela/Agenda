import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        FormularioTelefono t = new FormularioTelefono();
        t.setSize(230,410);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);
    }
}
