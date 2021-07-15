package com.gittigidiyor.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteToText {

    public static void write(String fileName,  String information) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append( information + "\n");
            writer.close();
        } catch (Exception e) {
        }
    }
}
