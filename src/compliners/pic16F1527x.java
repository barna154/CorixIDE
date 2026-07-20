package compliners;

import editor.TextEditor;
import compliners.Instruction;
import menus.ConsolePanel;
import java.util.List;
import java.util.ArrayList;

public class pic16F1527x {

    private final TextEditor editor;
    private final ConsolePanel console;

    private String cpu;
    private String config;
    private String setup;
    private String loop;

    private String config1;
    private String config2;
    private String config3;
    private String config4;
    private String config5;

    private List<Instruction> parseInstructions(String block) {
    List<Instruction> instructions = new ArrayList<>();


            for (String rawLine : block.split(";")) {
                String line = rawLine.trim();
                if (line.isEmpty()) continue;

            
                int open = line.indexOf('(');
                int close = line.lastIndexOf(')');

                if (open == -1 || close == -1 || close < open) {
                    console.println("Figyelmeztetés: nem sikerült értelmezni: " + line);
                    continue;
                }

                String funcName = line.substring(0, open).trim();
                String argsPart = line.substring(open + 1, close).trim();

                List<String> args = new ArrayList<>();
                if (!argsPart.isEmpty()) {
                    for (String arg : argsPart.split(",")) {
                        args.add(arg.trim());
                    }
                }

                instructions.add(new Instruction(funcName, args));
            }

            return instructions;
        }

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

            config1 = "CD3F";
            config2 = "E53F";

            console.println("----------------");
            console.println("CPU: " + cpu);

            List<Instruction> setupInstructions = parseInstructions(setup);
            List<Instruction> loopInstructions = parseInstructions(loop);
            List<Instruction> configInstructions = parseInstructions(config);


                console.println("Config utasítások:");
                for (Instruction instr : configInstructions) {
                    console.println("  -> " + instr);
                    String asm = generateAsmForInstruction(instr);
                    if (!asm.isEmpty()) {
                        console.println("     " + asm);
                    }
                }

                console.println("SETUP utasítások:");
                for (Instruction instr : setupInstructions) {
                    console.println("  -> " + instr);
                    String asm = generateAsmForInstruction(instr);
                    if (!asm.isEmpty()) {
                        console.println("     " + asm);
                    }
                }

                console.println("LOOP utasítások:");
                for (Instruction instr : loopInstructions) {
                    console.println("  -> " + instr);
                    String asm = generateAsmForInstruction(instr);
                    if (!asm.isEmpty()) {
                        console.println("     " + asm);
                    }
                }
            console.println("----------------");
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



    private String generateAsmForInstruction(Instruction instr) {
        switch (instr.name) {
            case "setOsc":
                return generateSetOsc(instr.args);
            case "setAnalogRange":
                return generatesetAnalogRange(instr.args);
            case "setClockOut":
                return generatesetClockOut(instr.args);    
            case "setOverflowReset":
                 return generateSetOverflowReset(instr.args);  
            case "setPeripheralLock":
                 return generateSetPeripheralLock(instr.args);
            case "setBrownOutVoltage":
                 return generateSetBrownOutVoltage(instr.args);   
            case "setPin":
                return generateSetPin(instr.args);
            case "outPin":
                return generateOutPin(instr.args);
            default:
                console.println("Ismeretlen utasítás: " + instr.name);
                return "";
        }
    }


    private String generateSetOsc(List<String> args) {
        if (args.size() != 1) {
            console.println("Wrong parmeter count " + args);
            return "";
        }
            
        String arg = args.get(0);

            StringBuilder sb = new StringBuilder(config1);

            if (arg.equals("IN1MHZ")) {
                sb.setCharAt(0, 'F');
                sb.setCharAt(1, 'D');
            }
            else if (arg.equals("IN32MHZ")) {
                sb.setCharAt(0, 'C');
                sb.setCharAt(1, 'D');
            }
            else if (arg.equals("EXTL")) {
                sb.setCharAt(0, 'F');
                sb.setCharAt(1, 'E');
            }
            else if (arg.equals("EXTH")) {
                sb.setCharAt(0, 'F');
                sb.setCharAt(1, 'F');
            }
            else if (arg.equals("LPIN")) {
                sb.setCharAt(0, 'D');
                sb.setCharAt(1, 'D');
            }
            else {
                return "Not recognizable argument: " + args;
            }

            config1 = sb.toString();
            return config1;
        }

    private String generatesetAnalogRange(List<String> args) {
        if (args.size() != 1) {
            console.println("Wrong parameter count: " + args);
            return "";
        }
            
        String arg = args.get(0);

            StringBuilder sb = new StringBuilder(config1);

            if (arg.equals("HIGH")) {
                sb.setCharAt(2, '3');
            }
            else if (arg.equals("LOW")) {
                sb.setCharAt(2, '2');
            }
            else {
                return "Not recognizable argument: " + args;
            }

            config1 = sb.toString();
            return config1;
        }

    private String generatesetClockOut(List<String> args) {
        if (args.size() != 1) {
            console.println("Wrong parameter count: " + args);
            return "";
        }
            
        String arg = args.get(0);

            StringBuilder sb = new StringBuilder(config1);

            if (arg.equals("TRUE")) {
                sb.setCharAt(3, 'E');
            }
            else if (arg.equals("FALSE")) {
                sb.setCharAt(3, 'F');
            }
            else {
                return "Not recognizable argument: " + args;
            }

            config1 = sb.toString();
            return config1;
        }
    

    private String generateSetOverflowReset(List<String> args) {
        if (args.size() != 1) {
            console.println("Wrong parameter count: " + args);
            return "";
        }
            
        String arg = args.get(0);

            StringBuilder sb = new StringBuilder(config2);

            if (arg.equals("TRUE")) {
                sb.setCharAt(2, '3');
            }
            else if (arg.equals("FALSE")) {
                sb.setCharAt(2, '2');
            }
            else {
                return "Not recognizable argument: " + args;
            }

            config2 = sb.toString();
            return config2;
        }
    
    private String generateSetPeripheralLock(List<String> args) {
        if (args.size() != 1) {
            console.println("Wrong parameter count: " + args);
            return "";
        }
            
        String arg = args.get(0);

            StringBuilder sb = new StringBuilder(config2);

            if (arg.equals("TRUE")) {
                    if (config2.charAt(3) == 'F') {
                            sb.setCharAt(3, 'F');
                        }

                    else if (config2.charAt(3) == 'D') {
                            sb.setCharAt(3, 'D');
                        }

                    else if (config2.charAt(3) == '7') {
                            sb.setCharAt(3, 'F');
                        }
                    
                    else if (config2.charAt(3) == '5') {
                            sb.setCharAt(3, 'D');
                        }
            }
            else if (arg.equals("FALSE")) {
                    if (config2.charAt(3) == 'F') {
                            sb.setCharAt(3, '7');
                        }

                    else if (config2.charAt(3) == 'D') {
                            sb.setCharAt(3, '5');
                        }

                    else if (config2.charAt(3) == '7') {
                            sb.setCharAt(3, '7');
                        }
                    
                    else if (config2.charAt(3) == '5') {
                            sb.setCharAt(3, '5');
                        }
            }
            else {
                return "Not recognizable argument: " + args;
            }

            config2 = sb.toString();
            return config2;
        }

    private String generateSetBrownOutVoltage(List<String> args) {
        if (args.size() != 1) {
            console.println("Wrong parameter count: " + args);
            return "";
        }
            
        String arg = args.get(0);

            StringBuilder sb = new StringBuilder(config2);

            if (arg.equals("LOW")) {
                    if (config2.charAt(3) == 'F') {
                            sb.setCharAt(3, 'F');
                        }

                    else if (config1.charAt(3) == 'D') {
                            sb.setCharAt(3, 'F');
                        }

                    else if (config1.charAt(3) == '7') {
                            sb.setCharAt(3, '7');
                        }
                    
                    else if (config1.charAt(3) == '5') {
                            sb.setCharAt(3, '7');
                        }
            }
            else if (arg.equals("HIGH")) {
                    if (config2.charAt(3) == 'F') {
                            sb.setCharAt(3, 'D');
                        }

                    else if (config1.charAt(3) == 'D') {
                            sb.setCharAt(3, 'D');
                        }

                    else if (config1.charAt(3) == '7') {
                            sb.setCharAt(3, '5');
                        }
                    
                    else if (config1.charAt(3) == '5') {
                            sb.setCharAt(3, '5');
                        }
            }
            else {
                return "Not recognizable argument: " + args;
            }

            config2 = sb.toString();
            return config2;
        }








    private String generateSetPin(List<String> args) {
        if (args.size() != 2) {
            console.println("setPin hibás paraméterszám: " + args);
            return "";
        }

        String pin = args.get(0);
        String direction = args.get(1); 

        return "F4E0";
    }

    private String generateOutPin(List<String> args) {
        if (args.size() != 2) {
            console.println("outPin hibás paraméterszám: " + args);
            return "";
        }

        String pin = args.get(0);
        String value = args.get(1); // "TRUE"/"FALSE"

        return "; outPin(" + pin + ", " + value + ") -> LATx/PORTx beállítás ide";
    }
}