package org.example;

import java.io.File;

public class ArgumentParser {
    
    private String[] args;
    
    public File originalFile;
    
    public File newFile;
    
    
    public ArgumentParser(String[] args) {
        this.args = args;
        this.parse();
    }
    
    public void parse() {
        if (args.length != 2) {
            throw new RuntimeException("Unexpected number of arguments");
        }
        this.originalFile = new File(args[0]);
        if (!this.originalFile.exists()) {
            throw new RuntimeException("File not found: " + args[0]);
        }
        this.newFile = new File(args[1]);
        if (!this.newFile.exists()) {
            throw new RuntimeException("File not found: " + args[1]);
        }
    }
    
    
}