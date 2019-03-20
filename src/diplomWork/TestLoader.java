package diplomWork;

import diplomWork.tests.FakeChat;
import diplomWork.view.components.ChatListRenderer;
import diplomWork.view.forms.*;

import javax.swing.*;

public class TestLoader {
    public static void main(String[] args) {

        JFrame frame = new JFrame();

        MainFrame inputNumber = new MainFrame();

        //frame.setContentPane(inputNumber.getRootPanel());
        frame.setDefaultCloseOperation(3);
        frame.setSize(900,630);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
