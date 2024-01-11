package org.example;

public enum Option {
    CHARACTER_COUNT,
    WORD_COUNT,
    LINE_COUNT,
    ALL;
    
    public static Option parseOption(String optionType) {
        switch (optionType) {
            case "-w":
                return Option.WORD_COUNT;
            case "-c":
                return Option.CHARACTER_COUNT;
            case "-l":
                return Option.LINE_COUNT;
            default:
                return Option.ALL;
        }
    }
}