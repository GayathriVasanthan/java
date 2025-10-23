import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class InstagramClone extends JFrame {
    private JButton uploadButton;
    private JPanel imagePanel;
    private JPanel filterPanel;
    private JComboBox<String> filterComboBox;

    public InstagramClone() {
        setTitle("Instagram Clone");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        uploadButton = new JButton("Upload Image");
        imagePanel = new JPanel();
        filterPanel = new JPanel();
        filterComboBox = new JComboBox<>(new String[]{"None", "Grayscale", "Sepia", "Invert"});

        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadImage();
            }
        });

        filterComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyFilter();
            }
        });

        filterPanel.add(new JLabel("Choose Filter:"));
        filterPanel.add(filterComboBox);

        add(uploadButton, BorderLayout.NORTH);
        add(filterPanel, BorderLayout.SOUTH);
        add(new JScrollPane(imagePanel), BorderLayout.CENTER);
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an Image");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            displayImage(file);
        }
    }

    private void displayImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            imagePanel.removeAll();
            imagePanel.add(label);
            imagePanel.revalidate();
            imagePanel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyFilter() {
        Component[] components = imagePanel.getComponents();
        if (components.length > 0) {
            JLabel label = (JLabel) components[0];
            ImageIcon icon = (ImageIcon) label.getIcon();
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();

            switch (filterComboBox.getSelectedItem().toString()) {
                case "Grayscale":
                    image = applyGrayscale(image);
                    break;
                case "Sepia":
                    image = applySepia(image);
                    break;
                case "Invert":
                    image = applyInvert(image);
                    break;
            }

            label.setIcon(new ImageIcon(image));
            imagePanel.revalidate();
            imagePanel.repaint();
        }
    }

    private BufferedImage applyGrayscale(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int p = image.getRGB(i, j);
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                int avg = (r + g + b) / 3;
                p = (avg << 16) | (avg << 8) | avg;
                image.setRGB(i, j, p);
            }
        }
        return image;
    }

    private BufferedImage applySepia(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int p = image.getRGB(i, j);
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int tr = (int)(0.393 * r + 0.769 * g + 0.189 * b);
                int tg = (int)(0.349 * r + 0.686 * g + 0.168 * b);
                int tb = (int)(0.272 * r + 0.534 * g + 0.131 * b);

                if (tr > 255) { tr = 255; }
                if (tg > 255) { tg = 255; }
                if (tb > 255) { tb = 255; }

                p = (tr << 16) | (tg << 8) | tb;
                image.setRGB(i, j, p);
            }
        }
        return image;
    }

    private BufferedImage applyInvert(BufferedImage image) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int p = image.getRGB(i, j);
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                p = (r << 16) | (g << 8) | b;
                image.setRGB(i, j, p);
            }
        }
        return image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InstagramClone().setVisible(true);
            }
        });
    }
}
