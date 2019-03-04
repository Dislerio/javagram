package diplomWork.view.components;

import javax.swing.*;
import java.awt.*;

public class ChatListRenderer extends DefaultListCellRenderer {

    Color selected = new Color(0, 179, 230);
    Color unselected = new Color(195, 195, 195);

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ChatLabel label = (ChatLabel) value;
        //label.setFont(font);
       /*
        if(isSelected){
            label.setBorder(BorderFactory.createMatteBorder(0,12,0,2,selected));
        } else {
            label.setBorder(BorderFactory.createMatteBorder(1,12,1,0,unselected));
        }
        */
        return label;
    }
}
