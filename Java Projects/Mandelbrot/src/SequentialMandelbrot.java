// Mandelbrot.java - Sequential version of Mandelbrot.
// Author: Chris Wilcox
// Date:   4/9/2017
// Class:  CS165

// Original code from http://code.activestate.com

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class SequentialMandelbrot extends Frame {

    // Required for java.awt.Frame
    private static final long serialVersionUID = 1L;

    // Image variables
    private static BufferedImage image; // image
    private static int width; // image width
    private static int height; // image height
    private static int pixels[][]; // shared by all threads

    // Main entry point
    public static void main(String[] args) {

        // Create image
        SequentialMandelbrot fractal = new SequentialMandelbrot();
        fractal.setSize(1200, 800);
        fractal.setVisible(true);
        fractal.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                fractal.dispose();
             }
        } );
        width = fractal.getWidth();
        height = fractal.getHeight();

        // Start timing
        long startTime = System.currentTimeMillis();

        // Fractal generation
        pixels = new int[height][width];
        try {
            fractal.computeFractal();
        } catch (InterruptedException e) {
            System.out.println("Mandelbrot program interrupted!");
        }

        // Stop timing
        long milliseconds = System.currentTimeMillis() - startTime;
        System.out.println("Execution time: " + milliseconds + " milliseconds");

        // Display image
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixels[y][x]);
            }
        }
        fractal.repaint();
    }

    // Fractal generation
    public void computeFractal() throws InterruptedException {

        // Thread pool
        Thread thread = new Thread(new Mandelbrot());
        thread.start();
        thread.join();
    }

    // Mandelbrot computation
    class Mandelbrot implements Runnable {

        final double xa = -2.0;
        final double xb = 1.0;
        final double ya = -1.5;
        final double yb = 1.5;

        public Mandelbrot() {
        }

        // Runnable method
        public void run() {

            double x0, x, y, a, b;
            int red, green, blue;
            int kx, ky, kc;

            // Each thread only calculates its own share of pixels!
            for (int pixel = 0; pixel < width * height; pixel++) {

                kx = pixel % width;
                ky = (pixel - kx) / width;
                a = (double) kx / width * (xb - xa) + xa;
                b = (double) ky / height * (yb - ya) + ya;
                x = a;
                y = b;

                for (kc = 0; kc < 256; kc++) {
                    
                    x0 = x * x - y * y + a;
                    y = 2 * x * y + b;
                    x = x0;

                    if (x * x + y * y > 4) {

                        // Translate into RGB value
                        red = 255 - (kc % 16) * 16;
                        green = (16 - kc % 16) * 16;
                        blue = (kc % 16) * 16;
                        
                        // Write RGB value to pixels
                        pixels[ky][kx] = (0xff000000 | blue << 16 | green << 8 | red);
                        break;
                    }
                }
            }
        }
    }

    // Paint image
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
