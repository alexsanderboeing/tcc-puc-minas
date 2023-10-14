package com.juntamedica.webservice.htmltopdf.service;

import java.io.File;

public interface HtmlToPdfService {

    File convert(String html, String filename);

    String getPdfBase64Value(File pdfFile);

    void clean(File pdfFile);

    void clean(String filePath);
}
