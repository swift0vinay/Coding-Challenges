package org.example;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    
    
    public static void main(String[] args) throws IOException {
        ArgumentParser argumentParser = new ArgumentParser(args);
        
        File file = new File(argumentParser.fileName);
        if (!file.exists()) {
            System.out.println("File " + argumentParser.fileName + " not found.");
            System.exit(1);
        }
        
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        WordCountImpl wordCount = new WordCountImpl(fileBytes);
        
        StringBuilder ans = new StringBuilder();
        switch (argumentParser.option) {
            case CHARACTER_COUNT: {
                ans.append(wordCount.getCharacterCount());
                break;
            }
            case WORD_COUNT: {
                ans.append(wordCount.getWordCount());
                break;
            }
            case LINE_COUNT: {
                ans.append(wordCount.getLineCount());
                break;
            }
            case ALL: {
                ans.append(wordCount.getCharacterCount() + " ");
                ans.append(wordCount.getWordCount() + " ");
                ans.append(wordCount.getLineCount());
                break;
            }
        }
        System.out.println(ans.toString());
    }
    
}