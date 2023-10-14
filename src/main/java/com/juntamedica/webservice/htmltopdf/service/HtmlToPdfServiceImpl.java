package com.juntamedica.webservice.htmltopdf.service;

import com.juntamedica.utils.exception.HtmlToPdfDeleteErrorException;
import com.juntamedica.utils.exception.HtmlToPdfGenerateXhtmlException;
import com.juntamedica.utils.exception.HtmlToPdfReadFileException;
import com.juntamedica.webservice.htmltopdf.HtmlToPdfClient;
import com.juntamedica.webservice.htmltopdf.exception.HtmlToPdfErrorException;
import feign.FeignException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Service
public class HtmlToPdfServiceImpl implements HtmlToPdfService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlToPdfServiceImpl.class);
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    @Autowired
    private HtmlToPdfClient htmlToPdfClient;

    @Override
    public File convert(String html, String filename) {
        try {
            String pdfFilename = String.format("%s.pdf", filename);
            File htmlFile = this.generateHtmlFile(html, filename);
            InputStream stream =  new FileInputStream(htmlFile);
            MultipartFile multipartFileToSend = new MockMultipartFile(
                    "file",
                    htmlFile.getName(),
                    MediaType.TEXT_HTML_VALUE,
                    stream
            );
            File pdf = new File(String.format("%s/%s", TEMP_DIR, pdfFilename));
            FileUtils.writeByteArrayToFile(pdf, htmlToPdfClient.convert(multipartFileToSend));

            this.clean(htmlFile);

            return pdf;
        } catch (FeignException e) {
            e.printStackTrace();
            throw new HtmlToPdfErrorException(filename);
        } catch (IOException e) {
            throw new HtmlToPdfErrorException(filename);
        }
    }

    @Override
    public String getPdfBase64Value(File pdfFile) {
        if (Files.exists(pdfFile.toPath())) {
            try {
                byte[] fileContent = Files.readAllBytes(pdfFile.toPath());
                return Base64.getEncoder().encodeToString(fileContent);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
                throw new HtmlToPdfReadFileException();
            }
        }

        return "";
    }

    @Override
    public void clean(File file) {
        try {
            Files.deleteIfExists(Path.of(file.getAbsolutePath()));
        } catch (IOException | SecurityException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new HtmlToPdfDeleteErrorException();
        }
    }

    @Override
    public void clean(String filePath) {
        try {
            Files.deleteIfExists(Path.of(filePath));
        } catch (IOException | SecurityException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new HtmlToPdfDeleteErrorException();
        }
    }

    private File generateHtmlFile(String html, String name) {
        try {
            byte[] fileContent = html.getBytes();
            String filename = String.format("%s.html", name);

            File htmlFile = new File(String.format("%s/%s", TEMP_DIR, filename));
            FileUtils.writeByteArrayToFile(htmlFile, fileContent);

            return htmlFile;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new HtmlToPdfGenerateXhtmlException();
        }
    }
}
