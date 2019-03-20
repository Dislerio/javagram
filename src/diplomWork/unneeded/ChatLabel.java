package diplomWork.unneeded;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChatLabel extends JLabel {
    private static Image messageInBottom = null;
    private static Image messageInTop = null;
    private static Image messageInLeft = null;
    private static Image messageOutBottom = null;
    private static Image messageOutTop = null;
    private static Image messageOutRight = null;
    private static ImageIcon icon = null;
    private static Color bg = new Color(0, 169, 219);
    private static Color bg2 = new Color(75, 65, 172);
    private static Color fg = new Color(255,255,255);
    static Font font = new Font("Open Sans", Font.BOLD, 14);
    boolean income = true;
    static int arrowSize = 14;
    static int lineSize = 10;


    static {
        try {
            messageInBottom = ImageIO.read(new File("img/message-in-bottom.png"));
            messageInLeft = ImageIO.read(new File("img/message-in-left.png"));
            messageInTop = ImageIO.read(new File("img/message-in-top.png"));

            messageOutBottom = ImageIO.read(new File("img/message-out-bottom.png"));
            messageOutRight = ImageIO.read(new File("img/message-out-right.png"));
            messageOutTop = ImageIO.read(new File("img/message-out-top.png"));
            icon = new ImageIcon(messageInLeft);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (income){
            g.drawImage(messageInBottom, arrowSize, this.getHeight() - lineSize, null);
            g.drawImage(messageInTop, arrowSize, 0, null);
            g.setColor(bg);
            g.fillRect(arrowSize, messageInTop.getHeight(null), messageInTop.getWidth(null), this.getHeight()-messageInBottom.getHeight(null)*2);
            super.paintComponent(g);
        } else {
            g.drawImage(messageOutBottom, this.getWidth() - messageOutBottom.getWidth(null) - arrowSize, this.getHeight() - lineSize,null);
            g.drawImage(messageOutTop, this.getWidth()- messageOutBottom.getWidth(null) - arrowSize, 0, null);
            g.setColor(bg2);
            g.fillRect(this.getWidth()-messageOutBottom.getWidth(null) - arrowSize,messageOutTop.getHeight(null),
                    messageOutTop.getWidth(null), this.getHeight() - messageOutBottom.getHeight(null)*2);
            g.drawImage(messageOutRight, this.getWidth() - arrowSize, this.getHeight() / 2 - 6, null);
            g.setColor(fg);
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setIcon(null);
            super.paintComponent(g);
        }
    }

    public ChatLabel(String text){
        this.setText(setBreaksInText(text));
        //this.setPreferredSize(new Dimension(120, 50));
        this.setBorder(BorderFactory.createEmptyBorder(8,7,8,7));
        this.setOpaque(false);
        this.setBackground(bg);
        this.setIcon(icon);
        this.setIconTextGap(8);
        this.setFont(font);
        this.setForeground(fg);
    }
    public static String setBreaksInText(String text){
        int len = 25;
        if(text.length() < len) return "<html>" + text + "</html>";

        String result = "";
        int left;
        int lastSpace = 0;
        boolean found = false;

            for (int i = 0; i < text.length();i +=len){
                if(i == 0 && text.length() > len) {
                    result = "<html>";
                    lastSpace = -1;
                    continue;
                }
                for (int j = 1; j < len; j++){
                    left = i - j;
                    if(text.charAt(i) == ' '){
                        result = result + text.substring(lastSpace==0 ? lastSpace : lastSpace + 1, i) + "<br>";
                        lastSpace = i;
                        found = true;
                        break;
                    } else if (text.charAt(left) == ' '){
                        result = result + text.substring(lastSpace==0 ? lastSpace : lastSpace + 1, left) + "<br>";
                        lastSpace = left;
                        found = true;
                        break;
                    }
                }
                if(!found){
                    result = result + text.substring(lastSpace, i) + "<br>";
                    lastSpace = i-1;
                    found = false;
                }
            }
            result = result + text.substring(lastSpace+1) + "</html>";
            return result;
        }
}
