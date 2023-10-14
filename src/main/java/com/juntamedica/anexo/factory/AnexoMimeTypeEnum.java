package com.juntamedica.anexo.factory;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public enum AnexoMimeTypeEnum {
    AAC("audio/aac", "Arquivo de audio AAC"),
    ARC("application/octet-stream", "Archive document (multiple files embedded)"),
    AVI("video/x-msvideo", "Arquivo de audio e vídeo Intercalar AVI"),
    BIN("application/octet-stream", "Qualquer tipo de dados binários"),
    BZ("application/x-bzip", "Arquivo compactado BZip"),
    BZ2("application/x-bzip2", "Arquivo compactado BZip2"),
    CSV("text/csv", "Comma-separated values (CSV)"),
    DOC("application/msword", "Microsoft Word"),
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "Microsoft Word"),
    GIF("image/gif", "Graphics Interchange Format (GIF)"),
    JPEG("image/jpeg", "JPEG images"),
    JSON("application/json", "JSON format"),
    MPEG("video/mpeg", "MPEG Video"),
    MP4("video/mp4", "Video MP4"),
    ODP("application/vnd.oasis.opendocument.presentation", "OpenDocument presentation document"),
    ODS("application/vnd.oasis.opendocument.spreadsheet", "OpenDocument spreadsheet document"),
    ODT("application/vnd.oasis.opendocument.text", "OpenDocument text document"),
    OGA("audio/ogg", "OGG audio"),
    OGV("video/ogg", "OGG video"),
    PNG("image/png", "Portable Network Graphics"),
    PDF("application/pdf", "Adobe Portable Document Format (PDF)"),
    PPT("application/vnd.ms-powerpoint", "Microsoft PowerPoint"),
    RAR("application/x-rar-compressed", "RAR archive"),
    RTF("application/rtf", "Rich Text Format (RTF)"),
    SVG("image/svg+xml", "Scalable Vector Graphics (SVG)"),
    TAR("application/x-tar", "Tape Archive (TAR)"),
    WAV("audio/x-wav", "Waveform Audio Format"),
    WEBA("audio/webm", "WEBM audio"),
    WEBM("video/webm", "WEBM video"),
    WOFF("font/woff", "Web Open Font Format (WOFF)"),
    WOFF2("font/woff2", "Web Open Font Format (WOFF)"),
    XLS("application/vnd.ms-excel", "Microsoft Excel"),
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "Microsoft Excel"),
    ZIP("application/zip", "ZIP archive"),
    SEVEN_Z("application/x-7z-compressed", "7-zip archive");

    private final String value;
    private final String descricao;

    private static final Map<String, AnexoMimeTypeEnum> lookup = new HashMap<>();

    static {
        for (AnexoMimeTypeEnum a : AnexoMimeTypeEnum.values()) {
            lookup.put(a.getValue(), a);
        }
    }

    AnexoMimeTypeEnum(String value, String descricao) {
        this.value = value;
        this.descricao = descricao;
    }

    public static AnexoMimeTypeEnum get(String value) {
        AnexoMimeTypeEnum anexoMimeTypeEnum = lookup.get(value);

        return Objects.isNull(anexoMimeTypeEnum) ? AnexoMimeTypeEnum.BIN : anexoMimeTypeEnum;
    }
}
