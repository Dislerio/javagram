package diplomWork.view.forms;

import diplomWork.Loader;
import diplomWork.Log;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{      //++
    static final int FRAME_WIDTH = 900;
    static final int FRAME_HEIGHT = 630;
    static MainFrame frame;
    private JLayeredPane layeredPane;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JLabel minButton;
    private JLabel closeButton;
    private JPanel floatAddContactButton;
    //private JFrame frame;


    private MainFrame() {
        layeredPane = getLayeredPane();
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

    void setFloatButton(JPanel floatAddContactButton){
        this.floatAddContactButton = floatAddContactButton;
        this.floatAddContactButton.setBounds(20, frame.getHeight() - 120, 48, 48);
        this.floatAddContactButton.setOpaque(false);
    }

    public void showFloatButton() {
        if (floatAddContactButton != null) {
            layeredPane.add(floatAddContactButton, JLayeredPane.MODAL_LAYER-1, 50);
        } else {
            Log.info("Не установлены плавающие компоненты формы! floatComponents не заданы!");
        }
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
