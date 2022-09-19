package com.wanghao.biz.util;

import java.io.*;
import java.io.FileReader;

public class FileUtils {

    public static String readFileToString(File file) throws IOException {
        java.io.FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();

        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            sb.append(temp).append("\n");
        }
        bufferedReader.close();

        return sb.toString();
    }

    public static void writeStringToFile(String str, File file) throws IOException {
        file.setWritable(true);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(str);
        fileWriter.close();
    }

}
