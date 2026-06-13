import javax.swing.*;
import java.awt.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import javax.swing.tree.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File; 
import java.util.Scanner;
import util.LanguageManager;
import util.AppPath;


public class FileExplorer {

    public void init(JPanel filePanel) throws Exception {
        LanguageManager.load("../lang/lang.json");
        
        String sourcecont = LanguageManager.get("Explorer");

        filePanel.setLayout(new BorderLayout());


        JLabel sourcecon = new JLabel(sourcecont);
        sourcecon.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        sourcecon.setFont(new Font("Arial", Font.PLAIN, 15));
        sourcecon.setForeground(new Color(118, 118, 118));
        filePanel.add(sourcecon, BorderLayout.NORTH);


        File rootDir = new File(AppPath.basePath);
        if (!rootDir.exists()) {
            rootDir.mkdirs();
        }

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootDir);
        buildTree(rootNode, rootDir);

        JTree tree = new JTree(rootNode);
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        tree.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        tree.setBackground(new Color(30, 33, 30));
        tree.setForeground(new Color(220, 220, 220));
        tree.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 17));

        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

                File file = (File) ((DefaultMutableTreeNode) value).getUserObject();
                setText(file.getName().isEmpty() ? file.getPath() : file.getName());

                setBackgroundNonSelectionColor(new Color(30, 33, 30));
                setBackgroundSelectionColor(new Color(60, 60, 60));
                setTextNonSelectionColor(new Color(220, 220, 220));
                setTextSelectionColor(new Color(255, 255, 255));

                setIcon(null); 

                return this;
            }
        });

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(30, 33, 30));

        filePanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void buildTree(DefaultMutableTreeNode node, File file) {
        if (!file.isDirectory()) return;

        File[] children = file.listFiles();
        if (children == null) return;

        // Mappák előre, majd fájlok, mindkettő ABC sorrendben
        java.util.Arrays.sort(children, (a, b) -> {
            if (a.isDirectory() && !b.isDirectory()) return -1;
            if (!a.isDirectory() && b.isDirectory()) return 1;
            return a.getName().compareToIgnoreCase(b.getName());
        });

        for (File child : children) {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
            node.add(childNode);
            if (child.isDirectory()) {
                buildTree(childNode, child);
            }
        }
    
    }

}