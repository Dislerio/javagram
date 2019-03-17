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
        ContactPanelTest label = (ContactPanelTest) value;

        if(isSelected){
            label.getRootPanel().setBorder(BorderFactory.createMatteBorder(0,0,0,3,selected));

        } else {
            label.getRootPanel().setBorder(BorderFactory.createMatteBorder(1,0,1,0,unselected));
        }
        return label.getRootPanel();
    }
}
