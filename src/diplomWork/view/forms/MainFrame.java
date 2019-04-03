package diplomWork.view.forms;

import diplomWork.Configs;
import diplomWork.Loader;
import diplomWork.Log;
import diplomWork.view.components.OverlayHandler;

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
    private OverlayHandler handler  = new OverlayHandler();


    public static synchronized MainFrame getInstance(){
        if(frame == null){
            frame = new MainFrame();
        }
        return frame;
    }

    private MainFrame() {
        layeredPane = getLayeredPane();
        setTitle("Javagram");
        setDefaultCloseOperation(3);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setIconImage(Configs.ICON_FRAME);
        setOverlayAsDefault();
    }

    @Override
    public void setContentPane(Container contentPane) {
        changeContentPanel(contentPane);

//        super.setContentPane(contentPane);
        refreshForm();
    }

    public void setOverlayAsDefault(){
        rootPanel.add(handler, BorderLayout.CENTER);
        super.setContentPane(rootPanel);
    }

    public void changeContentPanel(Container contentPanel) {
        handler.setContentPanel(contentPanel);
    }

    public void changeOverlayPanel(Container overlayPanel) {
        handler.setOverlayPanel(overlayPanel);
    }

    void setFloatButton(JPanel floatAddContactButton){
        this.floatAddContactButton = floatAddContactButton;
        this.floatAddContactButton.setBounds(20, frame.getHeight() - 120, 48, 48);
        this.floatAddContactButton.setOpaque(false);
        layeredPane.add(floatAddContactButton, JLayeredPane.MODAL_LAYER-1, 50);
    }

    public void showFloatButton() {
        floatAddContactButton.setVisible(true);
    }

    public void hideFloatButton() {
        floatAddContactButton.setVisible(false);
    }

    void refreshForm(){
        revalidate();
        repaint();
        setVisible(true);
    }
}
