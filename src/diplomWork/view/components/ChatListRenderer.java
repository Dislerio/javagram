package diplomWork.view.components;

import javax.swing.*;
import java.awt.*;

public class ChatListRenderer extends DefaultListCellRenderer {

//    Color selected = new Color(0, 179, 230);
//    Color unselected = new Color(195, 195, 195);

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ChatPanel panel = (ChatPanel) value;
        return panel.getRootPanel();
    }
}
