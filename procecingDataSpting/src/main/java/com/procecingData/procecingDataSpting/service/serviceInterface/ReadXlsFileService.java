package com.procecingData.procecingDataSpting.service.serviceInterface;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ReadXlsFileService {
    List<Map<String,String>> readFile(MultipartFile multipartFile);
}
