package com.ljheee.util.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class FileOperate {


    public static File openFromResources(String fileName) throws Exception {
        return new File(FileOperate.class.getResource(fileName).toURI());
    }

    public static File openFromAbsolutePath(String fileName) {
        return new File(fileName);
    }

    public static List<String> readAllLines(File file) throws Exception {
        return Files.readAllLines(Paths.get(file.getPath()));
    }







    public static void main(String[] args) throws Exception {

        File file = FileOperate.openFromResources("/openFromResources.txt");
        FileOperate.readAllLines(file).forEach(System.out::println);
    }


}
