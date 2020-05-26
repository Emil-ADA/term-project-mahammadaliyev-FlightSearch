import javax.swing.*;

public class Window {

    private JPanel mainPanel;
    private JComboBox From;
    private JComboBox To;
    private JButton cheapestButton;
    private JButton bestButton;
    private JButton fastestButton;
    private JLabel ArrowImage;

    public Window() {

        JFrame frame = new JFrame("Flight Searcher");
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {

        new Window();


    }


}
