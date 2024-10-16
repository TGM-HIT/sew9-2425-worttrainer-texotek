package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private final JLabel statsLabel = new JLabel();

    public WordTrainerFrame(ApplicationController controller) {
        super(TITLE);

        setLayout(new BorderLayout());

        add(imageLabel, BorderLayout.CENTER);
        guessTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    controller.checkGuess(guessTextField.getText());
                    guessTextField.setText("");
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.exitApplication();
            }
        });

        guessTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        add(statsLabel, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);
        add(guessTextField, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }

    /**
     * Method to refresh statistics on the frame
     * @param total Total guesses
     * @param correct Correct guesses
     * @param wrong Wrong guesses
     */
    public void refreshStatistics(int total, int correct, int wrong) {
        statsLabel.setText(String.format("Total Guesses: %d | Correct: %d | Wrong: %d", total, correct, wrong));
    }
}
