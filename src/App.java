import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        Telefono t = new Telefono();
        t.setSize(230,350);
        t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t.setVisible(true);
    }
}
