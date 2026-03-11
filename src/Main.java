import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File; 
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Main {

// LANGUAGE SCANNER
    public static String getValue(String key) throws Exception {
        Scanner sc = new Scanner(new File("../lang/lang.json"));
        String value = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.startsWith("\"" + key + "\"")) {
                int firstQuote = line.indexOf("\"", key.length() + 2);
                int secondQuote = line.indexOf("\"", firstQuote + 1);
                value = line.substring(firstQuote + 1, secondQuote);
                break;
            }
        }
        sc.close();
        return value;
    }

    public static void main(String[] args) throws Exception {


// STRINGLIST
        String mtitle = getValue("Title");
        String mfile = getValue("File");
        String medit = getValue("Edit");
        String moptions = getValue("Options");


//WINDOW
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle maxBounds = ge.getMaximumWindowBounds(); // monitor + tálca

        int screenWidth = maxBounds.width;
        int screenHeight = maxBounds.height;
        int screenX = maxBounds.x;
        int screenY = maxBounds.y;


        JFrame window = new JFrame();
        window.setTitle(mtitle);
        window.setSize(screenWidth, screenHeight);
        window.setUndecorated(true); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setBackground(new Color(60, 60, 60));
        window.setMinimumSize(new Dimension(400, 300));

     
        final int[] mouseOffset = new int[2];
        JPanel Menu = new JPanel(new BorderLayout());
        Menu.setBackground(new Color(43, 43, 43));
        Menu.setPreferredSize(new Dimension(1080, 35));

        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 7));
        leftPanel.setOpaque(false);

// Logo
        ImageIcon icon = new ImageIcon("../gui/logo.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        leftPanel.add(new JLabel(new ImageIcon(scaledImg)));

// Menüpontok
        JLabel labelFile = new JLabel(mfile);
        labelFile.setFont(new Font("Arial", Font.PLAIN, 15));
        labelFile.setForeground(new Color(118, 118, 118));
        leftPanel.add(labelFile);

        JLabel labelEdit = new JLabel(medit);
        labelEdit.setFont(new Font("Arial", Font.PLAIN, 15));
        labelEdit.setForeground(new Color(118, 118, 118));
        leftPanel.add(labelEdit);

        JLabel labelOptions = new JLabel(moptions);
        labelOptions.setFont(new Font("Arial", Font.PLAIN, 15));
        labelOptions.setForeground(new Color(118, 118, 118));
        leftPanel.add(labelOptions);

        Menu.add(leftPanel, BorderLayout.WEST);


        JLabel closeBtn = new JLabel("✕");
        closeBtn.setPreferredSize(new Dimension(45, 43));
        closeBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        closeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        closeBtn.setVerticalAlignment(SwingConstants.CENTER);
        closeBtn.setForeground(new Color(118, 118, 118));
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.setOpaque(true);
        closeBtn.setBackground(new Color(43, 43, 43));

        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                closeBtn.setForeground(Color.WHITE);
                closeBtn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
                closeBtn.setBackground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                closeBtn.setForeground(new Color(118, 118, 118));
                closeBtn.setBackground(new Color(43, 43, 43));
            }
        });



        JLabel maximize = new JLabel("🗗");
        maximize.setPreferredSize(new Dimension(45, 43));
        maximize.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        maximize.setHorizontalAlignment(SwingConstants.CENTER);
        maximize.setVerticalAlignment(SwingConstants.CENTER);
        maximize.setForeground(new Color(118, 118, 118));
        maximize.setCursor(new Cursor(Cursor.HAND_CURSOR));
        maximize.setOpaque(true);
        maximize.setBackground(new Color(43, 43, 43));

        maximize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                maximize.setForeground(Color.WHITE);
                maximize.setFont(new Font("Noto Sans Symbols 2", Font.BOLD, 16));
                maximize.setBackground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                maximize.setForeground(new Color(118, 118, 118));
                maximize.setBackground(new Color(43, 43, 43));
            }
        });

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0,  0));
        rightPanel.setOpaque(false);
        rightPanel.add(maximize);
        rightPanel.add(closeBtn);
        Menu.add(rightPanel, BorderLayout.EAST);



 //Dupla katt resize

        Menu.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseOffset[0] = e.getX();
                mouseOffset[1] = e.getY();
            }
        });
        Menu.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                window.setLocation(e.getXOnScreen() - mouseOffset[0], e.getYOnScreen() - mouseOffset[1]);
            }
        });


        final Rectangle[] normalBounds = new Rectangle[1]; 
        normalBounds[0] = window.getBounds();
        Menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Rectangle current = window.getBounds();
                    if (current.width == maxBounds.width && current.height == maxBounds.height) {
                        window.setBounds(normalBounds[0]);
                    } else {
                        normalBounds[0] = current;
                        window.setBounds(maxBounds);
                    }
                }
            }
        });

 
        JPanel line1 = new JPanel();
        line1.setBackground(new Color(117, 117, 117));
        line1.setPreferredSize(new Dimension(1080, 1));

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(Menu, BorderLayout.NORTH);
        northPanel.add(line1, BorderLayout.SOUTH);

        window.add(northPanel, BorderLayout.NORTH);


        JPanel back = new JPanel();
        back.setBackground(new Color(90, 90, 90));
        back.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 7));
        window.add(back, BorderLayout.CENTER);



//RESIZE
        final int RESIZE_MARGIN = 5;
        final Rectangle[] startBounds = new Rectangle[1];
        final Point[] startMouse = new Point[1];

        window.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int w = window.getWidth();
                int h = window.getHeight();

                if (x < RESIZE_MARGIN && y < RESIZE_MARGIN) window.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                else if (x > w - RESIZE_MARGIN && y < RESIZE_MARGIN) window.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                else if (x < RESIZE_MARGIN && y > h - RESIZE_MARGIN) window.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                else if (x > w - RESIZE_MARGIN && y > h - RESIZE_MARGIN) window.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                else if (x < RESIZE_MARGIN) window.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                else if (x > w - RESIZE_MARGIN) window.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                else if (y < RESIZE_MARGIN) window.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                else if (y > h - RESIZE_MARGIN) window.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                else window.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (startBounds[0] != null && startMouse[0] != null) {
                    int dx = e.getXOnScreen() - startMouse[0].x;
                    int dy = e.getYOnScreen() - startMouse[0].y;
                    Rectangle b = new Rectangle(startBounds[0]);
                    int type = window.getCursor().getType();

                    switch (type) {
                        case Cursor.NW_RESIZE_CURSOR:
                            b.x += dx; b.width -= dx; b.y += dy; b.height -= dy; break;
                        case Cursor.NE_RESIZE_CURSOR:
                            b.width += dx; b.y += dy; b.height -= dy; break;
                        case Cursor.SW_RESIZE_CURSOR:
                            b.x += dx; b.width -= dx; b.height += dy; break;
                        case Cursor.SE_RESIZE_CURSOR:
                            b.width += dx; b.height += dy; break;
                        case Cursor.W_RESIZE_CURSOR:
                            b.x += dx; b.width -= dx; break;
                        case Cursor.E_RESIZE_CURSOR:
                            b.width += dx; break;
                        case Cursor.N_RESIZE_CURSOR:
                            b.y += dy; b.height -= dy; break;
                        case Cursor.S_RESIZE_CURSOR:
                            b.height += dy; break;
                    }

                    if (b.width < 200) b.width = 200;
                    if (b.height < 150) b.height = 150;

                    window.setBounds(b);
                }
            }
        });

        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int type = window.getCursor().getType();
                if (type != Cursor.DEFAULT_CURSOR) {
                    startBounds[0] = window.getBounds();
                    startMouse[0] = e.getLocationOnScreen();
                } else {
                    startBounds[0] = null;
                    startMouse[0] = null;
                }
            }
        });






        window.setVisible(true);
    }
}