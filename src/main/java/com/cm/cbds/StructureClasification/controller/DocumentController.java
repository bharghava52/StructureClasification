package com.cm.cbds.StructureClasification.controller;

import com.cm.cbds.StructureClasification.Service.DocumentService;
import com.cm.cbds.StructureClasification.to.FileDetailsTo;
import com.cm.cbds.StructureClasification.to.FileReturnTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@CrossOrigin
@ComponentScan
@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/compareDocs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FileReturnTo addCustomCode(@RequestBody FileDetailsTo fileDetailsTo) {
        try {
            log.debug("received compareDocs : {}", fileDetailsTo);
            return documentService.getComparitionDetails(fileDetailsTo);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Exception stack trace: {}", e.getStackTrace());
        }
        return null;
    }

}
