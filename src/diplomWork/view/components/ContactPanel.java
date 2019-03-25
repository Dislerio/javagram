package diplomWork.view.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ContactPanel extends JPanel{
    private JPanel rootPanel;
    private JLabel messageDate;
    private JLabel avatar;
    private JLabel nameContact;
    private JLabel lastMessage;
    private JPanel centerPanel;
    private int userId;

    static Image maskGray = null;
    static {
        try {
            maskGray = ImageIO.read(new File("img/mask-gray.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ContactPanel(BufferedImage img, String name, String messageDate, String lastMessage, int userId){
        Icon icon = new ImageIcon(img);
        this.userId = userId;
        this.avatar.setIcon(icon);
        this.nameContact.setText(name);
        if(messageDate != null) this.messageDate.setText(messageDate);
        if(lastMessage != null){
            if(lastMessage.length() > 15){
                lastMessage = lastMessage.substring(0,12) + "...";
            }
            this.lastMessage.setText(lastMessage);
        }

        //this.nameContact.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));

    }
    private void createUIComponents() {
        this.avatar = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(maskGray,0,0,41,41,null);
            }
        };
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public int getUserId() {
        return userId;
    }
}
