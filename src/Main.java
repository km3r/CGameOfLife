import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Kyle on 11/6/2014.
 */
public class Main {
    static GUI gui;
    static JFrame jFrame;
    public static void main(String[] args) {
        jFrame = new JFrame();
        jFrame.setVisible(true);
        gui = new GUI();

        jFrame.setSize(650,740);
        jFrame.add(gui);

        //gui.setVisible(true);


        gui.init(gui);
        //gui.quickPaint();
        //JOptionPane.showMessageDialog(gui, "Click and drag to place\nSpace to pause steps", "Controls", JOptionPane.PLAIN_MESSAGE);

        //Thread thread = new Thread(gui);
        //thread.start();
    }
    public static void loop(){
        BufferStrategy bs = gui.getBufferStrategy();
        while (true) {
             if (gui.flag && System.currentTimeMillis() - gui.MS_DELAY > gui.time) {
                gui.count++;
                gui.time = System.currentTimeMillis();
                gui.board.update();
            }
            Graphics g = bs.getDrawGraphics();
            gui.paintNow(g);
            g.dispose();
            bs.show();

        }
    }

}
