import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Kyle on 11/6/2014.
 */
public class GUI extends GameEngine implements Runnable{
    Graphics g2;
    final int dX = 20;
    final int dY = 40;
    final int CELL_SIZE = 3; // must be size/int= cell_size(int)
    final static int SIZE = 600; // keep multiple of 20
    final int MS_DELAY = 50;
    Board board;
    //Canvas drawArea;
    //BufferStrategy bufferStrategy;
    Component mla;

    public static void main(String[] args) {
        //JFrame jFrame = new JFrame();
        //jFrame.setVisible(true);
        GUI gui = new GUI();
        gui.setVisible(true);
        //jFrame.setSize(650,700);
        //jFrame.add(gui);

        //gui.setVisible(true);


        gui.init(gui);
    }


    GUI()
    {

//        init();
        board = new Board(SIZE,CELL_SIZE);
        setBackground(Color.BLACK);
        setSize(SIZE+ 2*dX, SIZE+ 2*dY);
        //setTitle("Game Of Life");
        //setResizable(false);
        setIgnoreRepaint(true);
        //setVisible(true);

        //loop();

    }

    int count = 0;
    boolean flag = true;
    long time = System.currentTimeMillis();
    public void loop(){
        while (true) {

            //repaint();
            if (flag && System.currentTimeMillis() - MS_DELAY > time ) {
                count++;
                time = System.currentTimeMillis();
                board.update();
                repaint();
            }
        }
    }


    public void paintNow(Graphics g){


        g.setColor(Color.black);
        g.fillRect(0,  0,SIZE+dX+dX,SIZE+dY+dY);
        g.setColor(Color.WHITE);
        g.fillRect(dX, dY, SIZE, SIZE);


        //g.setColor(Color.black);
        //g.fillRect(dX+dX,  SIZE + dY + (dY/2),200,200);

        g.setColor(Color.WHITE);
        g.drawString("STEPS: " + count,dX+dX,  SIZE + dY + (dY/2));
        for (int i = 0; i < SIZE/CELL_SIZE; i++)
        {
            for (int j = 0; j < SIZE/CELL_SIZE; j ++)
            {
                if (board.spaces[i][j].isOccupied) {
                    g.setColor(Color.GREEN);
                    //else g.setColor(Color.WHITE);
                    g.fillRect(dX + i * CELL_SIZE, dY + j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
    }

    public void go() {
//        g2 = getGraphics();

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        KeyListener k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == ' ')flag = !flag;
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }


            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        drawArea.addKeyListener(k);

        MouseListener m = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    board.spaces[(e.getX() - dX) / CELL_SIZE][(e.getY() - dY) / CELL_SIZE].isOccupied
                            = !board.spaces[(e.getX() - dX) / CELL_SIZE][(e.getY() - dY) / CELL_SIZE].isOccupied;
                }
                quickPaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        MouseMotionListener ml = new MouseMotionListener() {


            @Override
            public void mouseDragged(MouseEvent e) {
                //if ( e.getButton() == MouseEvent.BUTTON1){
                if (e.getX() < dX || e.getX() >= SIZE + dX || e.getY() < dY || e.getY() >= SIZE + dY) {

                    return;
                }
                if (board.spaces[(e.getX() - dX) / CELL_SIZE][ (e.getY() - dY) / CELL_SIZE].isOccupied) {

                    return;
                }
                    board.spaces[(e.getX() - dX) / CELL_SIZE][ (e.getY() - dY) / CELL_SIZE].isOccupied = true;

                    quickPaint();
                    //JOptionPane.showMessageDialog(StartMain.s, "You win, resetting board.....","WINNER!", JOptionPane.PLAIN_MESSAGE);
                    //repaint();
                //}

            }


            //int mouseX = -1,mouseY = -1;
            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };
        drawArea.addMouseListener(m);
        drawArea.addMouseMotionListener(ml);

    }

    @Override
    public void draw(Graphics2D g) {
        paintNow(g);
    }

    @Override
    public void update(long time) {
        while (!(flag && System.currentTimeMillis() - MS_DELAY > this.time)) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        this.time = System.currentTimeMillis();
        board.update();
    }

    public void quickPaint(){
        BufferStrategy bs = getBufferStrategy();
            Graphics g = bs.getDrawGraphics();
            paintNow(g);
            g.dispose();
            bs.show();


    }
    public void init(Component mla){
        this.mla = mla;
        super.init();
//        createBufferStrategy(2);
        go();
    }
}
