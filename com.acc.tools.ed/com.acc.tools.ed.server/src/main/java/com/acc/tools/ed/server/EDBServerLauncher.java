package com.acc.tools.ed.server;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class EDBServerLauncher {
	
	private JFrame frame;

    private EDBServerLauncher create() {
        frame = createFrame();
        frame.getContentPane().add(createContent());

        return this;
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame(getClass().getName());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	frame.setUndecorated(true);
        return frame;
    }

    private void show() {
		frame.pack();
		// make the frame half the height and width
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		frame.setSize(width/2, height/2);

		// here's the part where frame center the screen
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	
    }

    private Component createContent() {
        //final Image image = requestImage();

        JPanel panel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //g.drawImage(image, 0, 0, null);
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        for (String label : new String[]{"One", "Dois", "Drei", "Quatro", "Peace"}) {
            JButton button = new JButton(label);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createRigidArea(new Dimension(15, 15)));
            panel.add(button);
        }

        panel.setPreferredSize(new Dimension(500, 500));

        return panel;
    }

    private Image requestImage() {
        Image image = null;

        try {
            image = ImageIO.read(ClassLoader.getSystemResource( "images/images1.jpg" ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EDBServerLauncher().create().show();
            }
        });
    }
}
