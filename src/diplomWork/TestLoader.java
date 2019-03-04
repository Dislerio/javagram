package diplomWork;

import diplomWork.view.forms.*;

import javax.swing.*;

public class TestLoader {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        MainForm inputNumber = new MainForm();

        frame.setContentPane(inputNumber.getRootPanel());
        frame.setDefaultCloseOperation(3);
        frame.setSize(900,630);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
