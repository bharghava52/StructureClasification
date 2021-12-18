package com.cm.cbds.StructureClasification.Service.Implementations;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cm.cbds.StructureClasification.Service.DocumentService;
import com.cm.cbds.StructureClasification.to.FileDetailsTo;
import com.cm.cbds.StructureClasification.to.FileReturnTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class DocumentServiceImpl implements DocumentService {

    public FileReturnTo getComparitionDetails(FileDetailsTo fileDetailsTo) {
        FileReturnTo fileReturnTo = new FileReturnTo();
        Map<String, String> fileData = new HashMap<>();
        List<String> fileString = fileDetailsTo.getFileData();
        int size = fileDetailsTo.getFileData().size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                File firstFile = new File(fileString.get(i));
                File secondFile = new File(fileString.get(j));
                String Name = firstFile.getName() + "-" + secondFile.getName();
                fileData.put(Name, compareTwoFile(firstFile, secondFile));
            }
        }
        return fileReturnTo;
    }

    private String compareTwoFile(File firstFile, File secondFile) {
        if (checkIfFileIsHtmlorXML(firstFile) && checkIfFileIsHtmlorXML(secondFile)) {
        }
        return "0%";
    }

    private boolean checkIfFileIsHtmlorXML(File firstFile) {
        if (firstFile.getName().contains("html") || firstFile.getName().contains("xml")) {
            return true;
        }
        return false;
    }
}
