package compliners;

import java.util.List;

public class Instruction {
    public final String name;
    public final List<String> args;

    public Instruction(String name, List<String> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public String toString() {
        return name + "(" + String.join(", ", args) + ")";
    }
}