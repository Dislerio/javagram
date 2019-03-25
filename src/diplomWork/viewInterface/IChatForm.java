package diplomWork.viewInterface;

import diplomWork.view.components.ChatPanel;
import diplomWork.view.components.ContactPanel;

import java.util.ArrayList;

public interface IChatForm extends IView{
 void showInfo(String strError);
 void setContactList(ArrayList<ContactPanel> panels);
 void setChatList(ArrayList<ChatPanel> panels);
}
