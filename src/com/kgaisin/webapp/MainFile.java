package com.kgaisin.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        showDirs("./src");
    }

    public static void showDirs(String filePath) {
        File dir = new File(filePath);
        List<File> filesInDir = Arrays.asList(Objects.requireNonNull(dir.listFiles()));

        filesInDir.forEach(file -> {
            if(file.isDirectory()) {
                showDirs(file.getAbsolutePath());
                System.out.println("Dir: " + file);
            } else {
                System.out.println("File: " + file);
            }
        });
    }
}
