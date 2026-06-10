import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File; 
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import menus.newProject;

public class Main {


    public static void main(String[] args) throws Exception {
        LanguageManager.load("../lang/lang.json");

// STRINGLIST
        String mtitle = LanguageManager.get("Title");
        String mfile = LanguageManager.get("File");
        String medit = LanguageManager.get("Edit");
        String moptions = LanguageManager.get("Options");
        String terminalname = LanguageManager.get("Terminal");
        String mboards = LanguageManager.get("Boards");
        String newp = LanguageManager.get("New Project");
        String newf = LanguageManager.get("New file");
        String openp = LanguageManager.get("Open Project");
        String mainp = LanguageManager.get("Main Project");

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
        final Rectangle[] normalBounds = new Rectangle[1]; 
        normalBounds[0] = window.getBounds();

        
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 7));
        leftPanel.setOpaque(false);

// Border
            JPanel resizeBorder = new JPanel();
            resizeBorder.setOpaque(false);
            resizeBorder.setLayout(new BorderLayout());
            resizeBorder.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            window.setContentPane(resizeBorder);

// Logo
        ImageIcon icon = new ImageIcon("../gui/logo.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        leftPanel.add(new JLabel(new ImageIcon(scaledImg)));

// Menüpontok
        JLabel labelFile = new JLabel(mfile);
        labelFile.setFont(new Font("Arial", Font.PLAIN, 15));
        labelFile.setForeground(new Color(118, 118, 118));
        labelFile.addMouseListener(new MouseAdapter() {
         @Override
            public void mouseClicked(MouseEvent e) { 
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                labelFile.setForeground(new Color(60, 60, 60));
                labelFile.setBackground(new Color(60, 60, 60));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                labelFile.setForeground(new Color(118, 118, 118));
                labelFile.setBackground(new Color(43, 43, 43));
            }
        });
        leftPanel.add(labelFile);

     
        JLabel labelEdit = new JLabel(medit);
        labelEdit.setFont(new Font("Arial", Font.PLAIN, 15));
        labelEdit.setForeground(new Color(118, 118, 118));
        labelEdit.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent e) { 
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                labelEdit.setForeground(new Color(60, 60, 60));
                labelEdit.setBackground(new Color(60, 60, 60));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                labelEdit.setForeground(new Color(118, 118, 118));
                labelEdit.setBackground(new Color(43, 43, 43));
            }
        });
        leftPanel.add(labelEdit);




        JLabel labelOptions = new JLabel(moptions);
        labelOptions.setFont(new Font("Arial", Font.PLAIN, 15));
        labelOptions.setForeground(new Color(118, 118, 118));
        labelOptions.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) { 
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    labelOptions.setForeground(new Color(60, 60, 60));
                    labelOptions.setBackground(new Color(60, 60, 60));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    labelOptions.setForeground(new Color(118, 118, 118));
                    labelOptions.setBackground(new Color(43, 43, 43));
                }
            });
        leftPanel.add(labelOptions);


        JLabel labelBoards = new JLabel(mboards);
        labelBoards.setFont(new Font("Arial", Font.PLAIN, 15));
        labelBoards.setForeground(new Color(118, 118, 118));
        labelBoards.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) { 
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    labelBoards.setForeground(new Color(60, 60, 60));
                    labelBoards.setBackground(new Color(60, 60, 60));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    labelBoards.setForeground(new Color(118, 118, 118));
                    labelBoards.setBackground(new Color(43, 43, 43));
                }
            });
        leftPanel.add(labelBoards);

        JLabel complinelb = new JLabel("☑️");
        complinelb.setPreferredSize(new Dimension(26, 23));
        complinelb.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        complinelb.setHorizontalAlignment(SwingConstants.CENTER);
        complinelb.setVerticalAlignment(SwingConstants.CENTER);
        complinelb.setForeground(new Color(118, 118, 118));
        complinelb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        complinelb.setOpaque(true);
        complinelb.setBackground(new Color(43, 43, 43));

        complinelb.addMouseListener(new MouseAdapter() {
           @Override
                public void mouseClicked(MouseEvent e) { 
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    complinelb.setForeground(new Color(0, 28, 150));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    complinelb.setForeground(new Color(118, 118, 118));
                }
            });

        leftPanel.add(complinelb);

        JLabel uploadb = new JLabel("⬆️");
        uploadb.setPreferredSize(new Dimension(26, 23));
        uploadb.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        uploadb.setHorizontalAlignment(SwingConstants.CENTER);
        uploadb.setVerticalAlignment(SwingConstants.CENTER);
        uploadb.setForeground(new Color(118, 118, 118));
        uploadb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        uploadb.setOpaque(true);
        uploadb.setBackground(new Color(43, 43, 43));

        uploadb.addMouseListener(new MouseAdapter() {
           @Override
                public void mouseClicked(MouseEvent e) { 
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    uploadb.setForeground(new Color(0, 168, 0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    uploadb.setForeground(new Color(118, 118, 118));
                }
            });

        leftPanel.add(uploadb);
            
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
                closeBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
                closeBtn.setBackground(new Color(43, 43, 43));
            }
        });



        JLabel maximize = new JLabel("🗗");
        maximize.setPreferredSize(new Dimension(43, 43));
        maximize.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        maximize.setFont(new Font("Noto Sans Symbols 2", Font.PLAIN, 22));
        maximize.setHorizontalAlignment(SwingConstants.CENTER);
        maximize.setVerticalAlignment(SwingConstants.CENTER);
        maximize.setForeground(new Color(118, 118, 118));
        maximize.setCursor(new Cursor(Cursor.HAND_CURSOR));
        maximize.setOpaque(true);
        maximize.setBackground(new Color(43, 43, 43));
        maximize.addMouseListener(new MouseAdapter() {
          
            @Override
            public void mouseClicked(MouseEvent e) { 
                Rectangle current = window.getBounds();
                if (current.width == maxBounds.width && current.height == maxBounds.height) {
                        window.setBounds(normalBounds[0]);
                    } else {
                        normalBounds[0] = current;
                        window.setBounds(maxBounds);
                    }
                 }
            @Override
            public void mouseEntered(MouseEvent e) {
                maximize.setForeground(Color.WHITE);
                maximize.setFont(new Font("Noto Sans Symbols 2", Font.BOLD, 22));
                maximize.setBackground(new Color(60, 60, 60));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                maximize.setForeground(new Color(118, 118, 118));
                maximize.setFont(new Font("Noto Sans Symbols 2", Font.PLAIN, 22));
                maximize.setBackground(new Color(43, 43, 43));
            }
        });


        JLabel minimize = new JLabel("_");
        minimize.setPreferredSize(new Dimension(43, 43));
        minimize.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        minimize.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setVerticalAlignment(SwingConstants.CENTER);
        minimize.setForeground(new Color(118, 118, 118));
        minimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimize.setOpaque(true);
        minimize.setBackground(new Color(43, 43, 43));
        minimize.addMouseListener(new MouseAdapter() {
          
            @Override
            public void mouseClicked(MouseEvent e) { 
                window.setState(JFrame.ICONIFIED);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                minimize.setForeground(Color.WHITE);
                minimize.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
                minimize.setBackground(new Color(60, 60, 60));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                minimize.setForeground(new Color(118, 118, 118));
                minimize.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
                minimize.setBackground(new Color(43, 43, 43));
            }
        });

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(minimize);
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
        

        Menu.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Menu.setCursor(Cursor.getDefaultCursor());
            }
        });

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
        line1.setBackground(new Color(100, 100, 100));
        line1.setPreferredSize(new Dimension(screenWidth, 1));
    

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(Menu, BorderLayout.NORTH);
        northPanel.add(line1, BorderLayout.SOUTH);
        resizeBorder.add(northPanel, BorderLayout.NORTH);

      


        JPanel back = new JPanel();
        back.setBackground(new Color(90, 90, 90));
        back.setLayout(new BorderLayout());
//fájlkezelő
        UIManager.put("MenuItem.background", new Color(30, 30, 30));
        UIManager.put("MenuItem.selectionBackground", new Color(60, 60, 60));
        UIManager.put("MenuItem.selectionForeground", new Color(230, 230, 230));
        UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());
        UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());

        JPopupMenu dataexplorer = new JPopupMenu();
        dataexplorer.setOpaque(true);
        dataexplorer.setBackground(new Color(30, 30, 30));
        dataexplorer.setForeground(new Color(30, 30, 30));
        dataexplorer.setBorder(new RoundedBorder(10, new Color(20, 20, 20)));
        dataexplorer.putClientProperty("JPopupMenu.consumeEventOnClose", Boolean.TRUE);

        JMenuItem item1 = new JMenuItem(newp);
            item1.setMargin(new Insets(0, 0, 0, 0));
            item1.setBackground(new Color(30, 30, 30));
            item1.setFont(new Font("Arial", Font.PLAIN, 15));
            item1.setForeground(new Color(230, 230, 230));
            item1.setOpaque(true);
        JMenuItem item2 = new JMenuItem(newf);
            item2.setMargin(new Insets(0, 0, 0, 0));
            item2.setBackground(new Color(30, 30, 30));
            item2.setFont(new Font("Arial", Font.PLAIN, 15));
            item2.setForeground(new Color(230, 230, 230));
            item2.setOpaque(true);
        JMenuItem item3 = new JMenuItem(openp);
            item3.setMargin(new Insets(0, 0, 0, 0));
            item3.setBackground(new Color(30, 30, 30));
            item3.setFont(new Font("Arial", Font.PLAIN, 15));
            item3.setForeground(new Color(230, 230, 230));
            item3.setOpaque(true);
        JMenuItem item4 = new JMenuItem(mainp);
            item4.setMargin(new Insets(0, 0, 0, 0));
            item4.setBackground(new Color(30, 30, 30));
            item4.setFont(new Font("Arial", Font.PLAIN, 15));
            item4.setForeground(new Color(230, 230, 230));
            item4.setOpaque(true);

        JSeparator sep = new JSeparator();
            sep.setOpaque(true);
            sep.setForeground(new Color(80, 80, 80));
            sep.setBackground(new Color(30, 30, 30));

        dataexplorer.add(item1);
        dataexplorer.add(item2);
        dataexplorer.add(sep);
        dataexplorer.add(item3);
        dataexplorer.add(item4);


        JPanel explolerp = new JPanel();
        explolerp.setBackground(new Color(30, 33, 30));
        explolerp.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        explolerp.setPreferredSize(new Dimension(250, screenHeight));
                        final int PANEL_RESIZE_MARGIN = 5;
                        final Point[] panelStartMouse = new Point[1];
                        final int[] panelStartWidth = new int[1];

                 explolerp.addMouseMotionListener(new MouseMotionAdapter() {
                            @Override
                            public void mouseMoved(MouseEvent e) {
                                int x = e.getX();
                                int w = explolerp.getWidth();

                                if (x > w - PANEL_RESIZE_MARGIN) {
                                    explolerp.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                                } else {
                                    explolerp.setCursor(Cursor.getDefaultCursor());
                                }
                            }

                            @Override
                            public void mouseDragged(MouseEvent e) {
                                if (panelStartMouse[0] != null) {
                                    int dx = e.getXOnScreen() - panelStartMouse[0].x;
                                    int newWidth = panelStartWidth[0] + dx;

                                    if (newWidth < 120) newWidth = 120; 
                                    if (newWidth > 800) newWidth = 800;

                                    explolerp.setPreferredSize(new Dimension(newWidth, explolerp.getHeight()));
                                    explolerp.revalidate();
                                }
                            }

                            
                        });

                        explolerp.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                if (e.isPopupTrigger()) dataexplorer.show(explolerp, e.getX(), e.getY());

                                int x = e.getX();
                                int w = explolerp.getWidth();

                                if (x > w - PANEL_RESIZE_MARGIN) {
                                    panelStartMouse[0] = e.getLocationOnScreen();
                                    panelStartWidth[0] = explolerp.getWidth();
                                } else {
                                    panelStartMouse[0] = null;
                                }
                            }
                            @Override
                            public void mouseReleased(MouseEvent e) {
                                    if (e.isPopupTrigger()) dataexplorer.show(explolerp, e.getX(), e.getY());
                                }
                        }); 
        
        FileExplorer fe = new FileExplorer();
        fe.init(explolerp);

        



        
//Konzol

        JPanel console = new JPanel(new BorderLayout());
        console.setBackground(new Color(23, 24, 23));
        console.setPreferredSize(new Dimension(0, 250));

                JLabel terminalp = new JLabel(terminalname);
                terminalp.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
                terminalp.setFont(new Font("Arial", Font.PLAIN, 18));
                terminalp.setForeground(new Color(255, 255, 255));


                          final int CONSOLE_RESIZE_MARGIN = 5;
                            final Point[] consoleStartMouse = new Point[1];
                            final int[] consoleStartHeight = new int[1];

                            console.addMouseMotionListener(new MouseMotionAdapter() {
                                @Override
                                public void mouseMoved(MouseEvent e) {
                                    int y = e.getY();

                                    if (y < CONSOLE_RESIZE_MARGIN) {
                                        console.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                                    } else {
                                        console.setCursor(Cursor.getDefaultCursor());
                                    }
                                }

                                @Override
                                public void mouseDragged(MouseEvent e) {
                                    if (consoleStartMouse[0] != null) {
                                        int dy = consoleStartMouse[0].y - e.getYOnScreen();
                                        int newHeight = consoleStartHeight[0] + dy;

                                        // minimum és maximum magasság
                                        if (newHeight < 100) newHeight = 100;
                                        if (newHeight > 600) newHeight = 600;

                                        console.setPreferredSize(new Dimension(console.getWidth(), newHeight));
                                        console.revalidate();
                                    }
                                }
                            });

                            console.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    int y = e.getY();

                                    if (y < CONSOLE_RESIZE_MARGIN) {
                                        consoleStartMouse[0] = e.getLocationOnScreen();
                                        consoleStartHeight[0] = console.getHeight();
                                    } else {
                                        consoleStartMouse[0] = null;
                                    }
                                }
                            }); 

        JPanel consoleWrapper = new JPanel(new BorderLayout());
        consoleWrapper.setBackground(new Color(255, 255, 255));

        JPanel line2 = new JPanel();
        line2.setBackground(new Color(100, 100, 100));
        line2.setPreferredSize(new Dimension(0, 1));

        consoleWrapper.add(line2, BorderLayout.NORTH);
        consoleWrapper.add(console, BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(new Color(255, 255, 255));
   
//Editor

        JPanel editor = new JPanel();
        editor.setBackground(new Color(40, 43, 40));
        editor.setLayout(new BorderLayout());


        
        TextEditor te = new TextEditor();
        te.init(editor);



        JPanel line3 = new JPanel();
        line3.setBackground(new Color(100, 100, 100));
        line3.setPreferredSize(new Dimension(1, 0)); 

        JPanel leftWrapper = new JPanel(new BorderLayout());
        leftWrapper.add(explolerp, BorderLayout.CENTER);
        leftWrapper.add(line3, BorderLayout.EAST);

        back.add(leftWrapper, BorderLayout.WEST);

        console.add(terminalp, BorderLayout.WEST);
        center.add(editor, BorderLayout.CENTER);
        center.add(consoleWrapper, BorderLayout.SOUTH);

        back.add(center, BorderLayout.CENTER);
        resizeBorder.add(back, BorderLayout.CENTER);


        //TESZT
        newProject npp = new newProject();
        npp.init(back);



//RESIZE

final int RESIZE_MARGIN = 5;
final Rectangle[] startBounds = new Rectangle[1];
final Point[] startMouse = new Point[1];

resizeBorder.addMouseMotionListener(new MouseMotionAdapter() {
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

resizeBorder.addMouseListener(new MouseAdapter() {
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

class RoundedBorder implements Border {
    private int radius;
    private Color color;

    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius, radius, radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}