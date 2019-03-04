package diplomWork.view.components;

import diplomWork.tests.ContactPanelTest;

import javax.swing.*;
import java.awt.*;

public class ContactListRenderer extends DefaultListCellRenderer {
    Font font = new Font("Open Sans", Font.BOLD, 18);
    Color selected = new Color(0, 179, 230);
    Color unselected = new Color(195, 195, 195);

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        //FakeContact label = (FakeContact) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        ContactPanelTest label = (ContactPanelTest) value;

        /*
        FakeContact label = (FakeContact) value;
        label.setIcon(label.getAvatar()); //аватарка
        label.setText(FakeContact.setTextFormat(label.getName(), label.getLastMessage()));  //имя + последнее сообщение
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        */
        if(isSelected){
            label.getRootPanel().setBorder(BorderFactory.createMatteBorder(0,0,0,2,selected));
        } else {
            label.getRootPanel().setBorder(BorderFactory.createMatteBorder(1,0,1,0,unselected));
        }
        return label.getRootPanel();
    }
}
