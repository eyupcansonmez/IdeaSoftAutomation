package com.ideasoft.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ideasoft.model.ElementInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.openqa.selenium.By;

public class ElementHelper {

    private ConcurrentHashMap<String, ElementInfo> elementMapList;

    public ElementHelper() {
        elementMapList = new ConcurrentHashMap<>();
    }

    public void initMap(List<File> fileList) throws FileNotFoundException {
        Type elementType = new TypeToken<List<ElementInfo>>() {
        }.getType();
        Gson gson = new Gson();
        List<ElementInfo> elementInfoList;
        for (File file : fileList) {
            try {
                FileReader fileReader = new FileReader(file);
                elementInfoList = gson.fromJson(fileReader, elementType);
                elementInfoList.parallelStream().forEach(elementInfo -> elementMapList.put(elementInfo.getKey(), elementInfo));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<File> getFileList(String directoryName) throws IOException {
        List<File> dirList = new ArrayList<>();
        try (Stream<Path> walkStream = Files.walk(Paths.get(directoryName))) {
            walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
                if (f.toString().endsWith(".json")) {
                    dirList.add(f.toFile());
                }
            });
        }
        return dirList;
    }

    public ElementInfo findElementInfoByKey(String key) {
        return elementMapList.get(key);
    }

    public By getElementInfoBy(ElementInfo elementInfo) {
        By by = null;
        if (elementInfo.getType().equals("css")) {
            by = By.cssSelector(elementInfo.getValue());
        } else if (elementInfo.getType().equals("xpath")) {
            by = By.xpath(elementInfo.getValue());
        } else if (elementInfo.getType().equals("id")) {
            by = By.id(elementInfo.getValue());
        }
        return by;
    }
}
