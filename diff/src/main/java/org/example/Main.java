package org.example;

import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException {
        ArgumentParser argumentParser = new ArgumentParser(args);
        
        DiffTool lcs = new DiffTool();
        
        lcs.generateDiff(argumentParser.originalFile, argumentParser.newFile);
    }
    
}