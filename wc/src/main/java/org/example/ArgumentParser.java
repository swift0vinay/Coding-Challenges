package org.example;

public class ArgumentParser {
    
    private String[] args;
    
    public Option option;
    
    public String fileName;
    
    public ArgumentParser(String[] args) {
        this.args = args;
        this.parse();
    }
    
    public void parse() {
        if (args.length == 0 || args.length > 2) {
            throw new RuntimeException("Unexpected number of arguments");
        }
        if (args.length == 1) {
            this.option = Option.ALL;
            this.fileName = args[0];
        } else {
            this.option = Option.parseOption(args[0]);
            this.fileName = args[1];
        }
    }
    
    
}