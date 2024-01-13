package org.example;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DiffTool {
    
    private List<List<DiffItem>> backtrackLCS(int[][] dp, String[] src, String[] dest, int start, int src_end, int dest_end) {
        List<DiffItem> insertions = new ArrayList<>();
        List<DiffItem> deletions = new ArrayList<>();
        int X = src_end + 1, Y = dest_end + 1;
        while (X >= start + 1 && Y >= start + 1) {
            if (StringUtils.equals(src[X - 1], dest[Y - 1])) {
                X -= 1;
                Y -= 1;
            } else if (dp[X - 1][Y] > dp[X][Y - 1]) {
                deletions.add(new DiffItem(X - 1, "- " + src[X - 1]));
                X -= 1;
            } else {
                insertions.add(new DiffItem(Y - 1, "+ " + dest[Y - 1]));
                Y -= 1;
            }
        }
        while (X >= start + 1) {
            deletions.add(new DiffItem(X - 1, "- " + src[X - 1]));
            X -= 1;
        }
        while (Y >= start + 1) {
            insertions.add(new DiffItem(Y - 1, "+ " + dest[Y - 1]));
            Y -= 1;
        }
        Collections.reverse(insertions);
        Collections.reverse(deletions);
        return Arrays.asList(insertions, deletions);
    }
    
    private List<List<DiffItem>> computeLCS(String[] src, String[] dest) {
        int start = 0, end = 0, srcLength = src.length, destLength = dest.length;
        int src_end = srcLength - 1, dest_end = destLength - 1;
        
        // Skip prefixes
        while (start <= src_end && start <= dest_end && StringUtils.equals(src[start], dest[start])) {
            ++start;
        }
        
        // Skip suffixes
        while (start <= src_end && start <= dest_end && StringUtils.equals(src[src_end], dest[dest_end])) {
            src_end -= 1;
            dest_end -= 1;
        }
        
        int[][] dp = new int[srcLength + 1][destLength + 1];
        for (int i = start + 1; i <= src_end + 1; i++) {
            for (int j = start + 1; j <= dest_end + 1; j++) {
                if (StringUtils.equals(src[i - 1], dest[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return backtrackLCS(dp, src, dest, start, src_end, dest_end);
    }
    
    private String readStringFromFile(File file) throws IOException {
        return new String(new FileInputStream(file).readAllBytes(), StandardCharsets.UTF_8);
    }
    
    private void processDiffItems(List<DiffItem> insertions, List<DiffItem> deletion) {
        int ins = 0, del = 0;
        while (ins < insertions.size() && del < deletion.size()) {
            if (deletion.get(del).index <= insertions.get(ins).index) {
                System.out.println("Ln: " + (deletion.get(del).index + 1) + " " + deletion.get(del).val);
                ++del;
            } else {
                System.out.println("Ln: " + (insertions.get(ins).index + 1) + " " + insertions.get(ins).val);
                ++ins;
            }
        }
        while (ins < insertions.size()) {
            System.out.println("Ln: " + (insertions.get(ins).index + 1) + " " + insertions.get(ins).val);
            ins += 1;
        }
        while (del < deletion.size()) {
            System.out.println("Ln: " + (deletion.get(del).index + 1) + " " + deletion.get(del).val);
            del += 1;
        }
    }
    
    public void generateDiff(File src, File dest) throws IOException {
        String[] srcList = readStringFromFile(src).split("\n");
        String[] destList = readStringFromFile(dest).split("\n");
        
        List<List<DiffItem>> diffs = computeLCS(srcList, destList);
        
        processDiffItems(diffs.get(0), diffs.get(1));
    }
    
}