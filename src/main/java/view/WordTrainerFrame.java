package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import controller.ApplicationController;

public class WordTrainerFrame extends JFrame {
    final private static String TITLE = "Word Trainer";
    private final JLabel imageLabel = new JLabel();
    private final JTextField guessTextField = new JTextField();

    public WordTrainerFrame(ApplicationController controller) {
        super(TITLE);

        setLayout(new BorderLayout());

        add(imageLabel, BorderLayout.CENTER);
        guessTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    controller.checkGuess(guessTextField.getText());
                }
            }
        });

        guessTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        add(imageLabel, BorderLayout.CENTER);
        add(guessTextField, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Method to refresh the image in the frame
     * @param imageURL URL of the image
     * @throws IOException If the image cannot be read
     */
    public void refreshImage(URL imageURL) throws IOException {
        BufferedImage before = ImageIO.read(imageURL);

        int SCALED_IMAGEWIDTH = 800;
        int SCALED_IMAGEHEIGHT = 500;

        BufferedImage scaledImage = new BufferedImage(SCALED_IMAGEWIDTH, SCALED_IMAGEHEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(before, 0, 0, SCALED_IMAGEWIDTH, SCALED_IMAGEHEIGHT, null);
        graphics2D.dispose();
        int w = before.getWidth();
        int h = before.getHeight();
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }
}
