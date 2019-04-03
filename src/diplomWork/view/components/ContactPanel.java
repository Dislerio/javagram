package diplomWork.view.components;

import diplomWork.Configs;
import diplomWork.presenter.objects.Person;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
    private Person contact;
    private String initiates = "";
    private Color color;
    private Image userPhoto;

    static Image maskGray = null;
    static {
        try {
            maskGray = ImageIO.read(new File("img/mask-gray.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ContactPanel(Person contact){
        this.contact = contact;
        this.userId = contact.getId();
        this.nameContact.setText(contact.getFullName());
        if(contact.getFirstName().length() != 0 && contact.getLastName().length() != 0){
            this.initiates = contact.getFirstName().substring(0,1).toUpperCase() + contact.getLastName().substring(0,1).toUpperCase();
        }

        if (this.contact.getPhotoSmall() == null) {
            if(this.color == null) this.color = Color.MAGENTA;
            avatar.setBackground(color);
            avatar.setText(this.initiates);
        } else {
            avatar.setText("");
            avatar.setIcon(new ImageIcon(contact.getPhotoSmall()));
        }
    }

    private void createUIComponents() {
        this.avatar = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (userPhoto != null) {
                    userPhoto = userPhoto.getScaledInstance(this.getPreferredSize().width,
                                    this.getPreferredSize().width, Image.SCALE_SMOOTH);
                    g.drawImage(userPhoto, 0, 0, null);
                } else {
//                    g.setColor(color);                //Todo рандомные цвета для пустых аватарок
//                    g.fillOval(0,0,41,41);
                }

                g.drawImage(maskGray,0,0,41,41,null);
            }
        };
        this.avatar.setFont(Configs.getFont(16));
    }

    private static Color getColor() {
        return new Color(((int)(Math.random()*253)),((int)(Math.random()*253)),((int)(Math.random()*253)));
    }

    public void setMessageDate(String date) {
        if(messageDate != null) this.messageDate.setText(date);
    }

    public void setLastMessage(String lastMessage) {
        if(lastMessage != null){
            if(lastMessage.length() > 15){
                lastMessage = lastMessage.substring(0,12) + "...";
            }
            this.lastMessage.setText(lastMessage);
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public int getUserId() {
        return userId;
    }


}
