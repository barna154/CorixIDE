package compliners;

import editor.TextEditor;
import menus.ConsolePanel;

public class pic16F1527x {

    private final TextEditor editor;
    private final ConsolePanel console;

    private String cpu;
    private String config;
    private String setup;
    private String loop;

    public pic16F1527x(TextEditor editor, ConsolePanel console) {       
        this.editor = editor;
        this.console = console;
    }

    public void compile() {

        String content = editor.getTextComponent().getText();

        cpu = getCpu(content);
        config = getSection(content, "config");
        setup = getSection(content, "setup");
        loop = getSection(content, "loop");

        console.println("----------------");
        console.println("CPU: " + cpu);
        console.println("CONFIG:\n" + config);
        console.println("SETUP:\n" + setup);
        console.println("LOOP:\n" + loop);
        console.println("----------------");



        // Itt kezdődhet majd a valódi fordítás

    }

    private String getCpu(String content) {

        for (String line : content.split("\\R")) {

            line = line.trim();

            if (line.startsWith("CPU=")) {
                return line.substring(4).replace(";", "").trim();
            }
        }

        return null;
    }

    private String getSection(String content, String section) {

        int pos = content.indexOf(section);

        if (pos == -1)
            return "";

        int open = content.indexOf('{', pos);

        if (open == -1)
            return "";

        int close = content.indexOf('}', open);

        if (close == -1)
            return "";

        return content.substring(open + 1, close).trim();
    }

    public String getCpu() {
        return cpu;
    }

    public String getConfig() {
        return config;
    }

    public String getSetup() {
        return setup;
    }

    public String getLoop() {
        return loop;
    }
}