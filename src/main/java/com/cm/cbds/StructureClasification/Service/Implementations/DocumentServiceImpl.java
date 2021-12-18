package com.cm.cbds.StructureClasification.Service.Implementations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cm.cbds.StructureClasification.Service.DocumentService;
import com.cm.cbds.StructureClasification.to.FileDetailsTo;
import com.cm.cbds.StructureClasification.to.FileReturnTo;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Service
public class DocumentServiceImpl implements DocumentService {

    static float fullcount = 0, successcount = 0;

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
        fileReturnTo.setFileData(fileData);
        return fileReturnTo;
    }

    private String compareTwoFile(File firstFile, File secondFile) {
        try {
            if (checkIfFileIsHtmlorXML(firstFile) && checkIfFileIsHtmlorXML(secondFile)) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                DocumentBuilder db2 = dbf.newDocumentBuilder();
                Document doc1 = db.parse(firstFile);
                Document doc2 = db2.parse(secondFile);
                doc1.getDocumentElement();
                fullcount = 0;
                successcount = 0;
                compareDocuments(doc1.getDocumentElement(), doc2.getDocumentElement());
                String str = String.format("%.02f", (successcount / fullcount) * 100);
                return str + "%";
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return "0%";
    }

    private void compareDocuments(Node doc1, Node doc2) {
        try {
            // System.out.println("root1:" + doc1.getNodeName());
            fullcount++;
            if (doc1 != null && doc2 != null && doc1.getNodeName().equalsIgnoreCase(doc2.getNodeName())) {
                successcount++;
            }
            int length = 0;
            if (doc1 != null && doc2 != null) {
                length = getGratestLength(doc1.getChildNodes().getLength(), doc2.getChildNodes().getLength());
            }
            if (doc1 != null && doc2 == null) {
                length = doc1.getChildNodes().getLength();
            }
            if (doc2 != null && doc1 == null) {
                length = doc2.getChildNodes().getLength();
            }
            if (doc1 != null && doc1.getChildNodes().getLength() > 0) {
                for (int i = 0; i < length; i++) {
                    if (!doc1.getChildNodes().item(i).getNodeName().equalsIgnoreCase("#text")) {
                        compareDocuments(doc1.getChildNodes().item(i),
                                doc2.getChildNodes().item(i));
                    }
                }
            }
            if (doc1 == null && doc2 != null && doc2.getChildNodes().getLength() > 0) {
                for (int i = 0; i < length; i++) {
                    if (!doc2.getChildNodes().item(i).getNodeName().equalsIgnoreCase("#text")) {
                        compareDocuments(doc1.getChildNodes().item(i),
                                doc2.getChildNodes().item(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int getGratestLength(int doc1, int doc2) {
        return doc1 > doc2 ? doc1 : doc2;
    }

    private boolean checkIfFileIsHtmlorXML(File firstFile) {
        if (firstFile.getName().contains(".html") || firstFile.getName().contains(".xml")) {
            return true;
        }
        return false;
    }
}