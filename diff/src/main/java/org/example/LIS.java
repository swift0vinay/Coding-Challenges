package org.example;

public class LIS {
    
    private String src;
    
    private int srcLength;
    
    private String dest;
    
    private int destLength;
    
    private int length;
    
    public LIS(String src, String dest) {
        this.src = src;
        this.srcLength = src.length();
        this.dest = dest;
        this.destLength = dest.length();
        computeLis();
    }
    
    public void computeLis() {
        int dp[][] = new int[srcLength][destLength];
        
    }
    
    public int getLength() {
        return this.length;
    }
    
}