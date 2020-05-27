import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

    private JPanel mainPanel;
    private JComboBox from;
    private JComboBox to;
    private JButton cheapestButton;
    private JButton bestButton;
    private JButton fastestButton;
    private JLabel ArrowImage;
    private JButton allFlightsButton;

    public Window() {

        JFrame frame = new JFrame("Flight Searcher");
        frame.add(mainPanel);

        from.addItem("Baku");
        from.addItem("Moscow");
        from.addItem("London");
        from.addItem("Saint Petersburg");
        from.addItem("Berlin");



        to.addItem("Baku");
        to.addItem("Moscow");
        to.addItem("London");
        to.addItem("Saint Petersburg");
        to.addItem("Berlin");



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        fastestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        bestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cheapestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        allFlightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {

        new Window();


    }

}
