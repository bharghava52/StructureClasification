package com.cm.cbds.StructureClasification.Service;

import com.cm.cbds.StructureClasification.to.FileDetailsTo;
import com.cm.cbds.StructureClasification.to.FileReturnTo;

public interface DocumentService {

    public FileReturnTo getComparitionDetails(FileDetailsTo fileDetailsTo);

}
