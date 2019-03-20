package diplomWork.view.forms;

import diplomWork.Loader;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private JPanel rootPanel;
    private JPanel topPanel;
    private JLabel minButton;
    private JLabel closeButton;
    private JFrame frame;


    public MainFrame(){

        frame = new JFrame("Javagram");
        //frame.setContentPane(phoneNumber.getRootPanel());
        frame.setDefaultCloseOperation(3);
        frame.setSize(900, 630);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
        refreshForm();
    }

    void refreshForm(){
        revalidate();
        repaint();
    }
}
