
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import util.LanguageManager;
import util.AppPath;

public class FileExplorer {

    private JTree tree;
    private JScrollPane scrollPane;
    private DefaultMutableTreeNode rootNode;
    private WatchService watchService;
    private final Map<WatchKey, Path> watchKeys = new HashMap<>();

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

        rootNode = new DefaultMutableTreeNode(rootDir);
        buildTree(rootNode, rootDir);

        tree = new JTree(rootNode);
        tree.setRootVisible(true);
        tree.setShowsRootHandles(true);
        tree.setBackground(new Color(30, 33, 30));
        tree.setForeground(new Color(170, 170, 170));
        tree.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        tree.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

        tree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

                File file = (File) ((DefaultMutableTreeNode) value).getUserObject();
                    if (file.isDirectory()) {
                            setText("📂 " + file.getName());
                    } else if (file.getName().endsWith(".crxprjct")) {
                            String name = file.getName().replaceFirst("\\.crxprjct$", "");
                            setText("🧩 " + name);
                    } else {
                            setText("📄 " + file.getName());
                    }
                setBackgroundNonSelectionColor(new Color(30, 33, 30));
                setBackgroundSelectionColor(new Color(60, 66, 60));
                setTextNonSelectionColor(new Color(170, 170, 170));
                setTextSelectionColor(new Color(255, 255, 255));

                setIcon(null);

                return this;
            }
        });

        scrollPane = new JScrollPane(tree);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(30, 33, 30));

        filePanel.add(scrollPane, BorderLayout.CENTER);

        startWatching(rootDir.toPath());
    }

    public void refresh() {
        File rootDir = new File(AppPath.basePath);

        rootNode.removeAllChildren();
        buildTree(rootNode, rootDir);

        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.reload();
    }

    private void buildTree(DefaultMutableTreeNode node, File file) {
        if (!file.isDirectory()) return;

        File[] children = file.listFiles();
        if (children == null) return;

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

    private void startWatching(Path root) {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            registerRecursive(root);

            Thread watchThread = new Thread(this::processEvents);
            watchThread.setDaemon(true); 
            watchThread.start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void registerRecursive(Path start) throws IOException {
        Files.walk(start)
             .filter(Files::isDirectory)
             .forEach(this::registerDir);
    }

    private void registerDir(Path dir) {
        try {
            WatchKey key = dir.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
            watchKeys.put(key, dir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void processEvents() {
        while (true) {
            WatchKey key;
            try {
                key = watchService.take(); 
            } catch (InterruptedException e) {
                return;
            }

            Path dir = watchKeys.get(key);
            if (dir == null) {
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    Path created = dir.resolve((Path) event.context());
                    if (Files.isDirectory(created)) {
                        try {
                            registerRecursive(created);
                        } catch (IOException ignored) {}
                    }
                }
            }

            key.reset();
            SwingUtilities.invokeLater(this::refresh);
        }
    }
    public JTree getTree() {
            return tree;
    }
    public JScrollPane getScrollPane() {
             return scrollPane;
    }
}