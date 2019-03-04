package diplomWork.tests;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContactPanelTest extends JPanel{
    private JPanel rootPanel;
    private JLabel messageDate;
    private JLabel avatar;
    private JLabel nameContact;
    private JLabel lastMessage;
    private JPanel centerPanel;

    static Image maskGray = null;
    static {
        try {
            maskGray = ImageIO.read(new File("img/mask-gray.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
    public ContactPanelTest(Icon icon, String name, String messageDate, String lastMessage){
        this.avatar.setIcon(icon);
        this.nameContact.setText(name);
        this.messageDate.setText(messageDate);
        if(lastMessage.length() > 15){
            lastMessage = lastMessage.substring(0,12) + "...";
        }
        this.lastMessage.setText(lastMessage);
        //this.nameContact.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));

    }

    private void createUIComponents() {
        this.avatar = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(maskGray,0,0,100,100,null);
            }
        };
    }
}
