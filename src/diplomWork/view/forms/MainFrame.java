package diplomWork.view.forms;

import diplomWork.Loader;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{      //++
    static final int FRAME_WIDTH = 900;
    static final int FRAME_HEIGHT = 630;
    static MainFrame frame;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JLabel minButton;
    private JLabel closeButton;
    //private JFrame frame;


    private MainFrame() {

        //frame = new MainFrame();
        setTitle("Javagram");
        setDefaultCloseOperation(3);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
    }

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
        //rootPanel.add(contentPane, BorderLayout.CENTER, 0);
        refreshForm();
    }

    void refreshForm(){
        revalidate();
        repaint();
        setVisible(true);
    }

    public static synchronized MainFrame getInstance(){
        if(frame == null){
            frame = new MainFrame();
        }
        return frame;
    }
}
