package org.example;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class WordCountImpl {
    
    byte[] fileBytes;
    
    public WordCountImpl(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }
    
    public String bytesToString(byte[] fileBytes) {
        return new String(fileBytes, StandardCharsets.UTF_8);
    }
    
    public int getCharacterCount() {
        return fileBytes.length;
    }
    
    public long getWordCount() {
        String data = bytesToString(fileBytes);
        String[] words = data.split("\\s");
        return Arrays.stream(words).filter(item -> !StringUtils.isBlank(item)).count();
    }
    
    public int getLineCount() {
        String data = bytesToString(fileBytes);
        return StringUtils.countMatches(data, "\n");
    }
    
}