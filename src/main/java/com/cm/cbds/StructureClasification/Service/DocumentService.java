package com.cm.cbds.StructureClasification.Service;

import com.cm.cbds.StructureClasification.to.FileDetailsTo;
import com.cm.cbds.StructureClasification.to.FileReturnTo;

import org.springframework.stereotype.Service;

@Service
public interface DocumentService {

    public FileReturnTo getComparitionDetails(FileDetailsTo fileDetailsTo);

}
