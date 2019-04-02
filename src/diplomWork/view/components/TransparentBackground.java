package diplomWork.view.components;

import javax.swing.*;
import java.awt.*;

public class TransparentBackground extends JPanel {

    private Color overlayColor;

    public TransparentBackground(Color overlayColor) {
        super();
        this.overlayColor = overlayColor;
    }

    public TransparentBackground() {
        this(makeTransparent(Color.black, 0.7f));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if(overlayColor != null) {
            graphics.setColor(overlayColor);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }

    public static Color makeTransparent(Color color, float transparency) {
        if(transparency < 0.0f || transparency > 1.0f)
            throw new IllegalArgumentException();
        return new Color(color.getRed(),color.getGreen(), color.getBlue(), (int)Math.round(color.getAlpha() * transparency));
    }
}
