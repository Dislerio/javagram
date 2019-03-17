package diplomWork.tests;

import diplomWork.Configs;

import javax.swing.*;

public class ChatPanel extends JPanel{
    private JPanel rootPanel;
    private JLabel outcoming;
    private JLabel incoming;
    //private boolean income;

    public ChatPanel(String text, boolean income){
        if(income){
            incoming.setText(text);
            incoming.setFont(Configs.font22);
            incoming.setVisible(true);
            outcoming.setVisible(false);
        } else {
            outcoming.setText(text);
            outcoming.setFont(Configs.font22);
            incoming.setVisible(false);
            outcoming.setVisible(true);
        }

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
