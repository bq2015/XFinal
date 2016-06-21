package com.bq2015.oknet.model;

import java.io.File;
import java.io.Serializable;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.MediaType;

/**
 * 请求参数的包装类
 */
public class HttpParams implements Serializable {

    /**
     * 普通的键值对参数
     */
    public ConcurrentHashMap<String, String> urlParamsMap;

    /**
     * 文件的键值对参数
     */
    public ConcurrentHashMap<String, FileWrapper> fileParamsMap;

    public ConcurrentHashMap<String,BytesWraper> bytesParamsMap;

    private void init() {
        urlParamsMap = new ConcurrentHashMap<>();
        fileParamsMap = new ConcurrentHashMap<>();
        bytesParamsMap = new ConcurrentHashMap<>();
    }

    public HttpParams() {
        init();
    }

    public HttpParams(String key, String value) {
        init();
        put(key, value);
    }

    public HttpParams(String key, File file) {
        init();
        put(key, file);
    }

    public void put(HttpParams params) {
        if (params != null) {
            if (params.urlParamsMap != null && !params.urlParamsMap.isEmpty())
                urlParamsMap.putAll(params.urlParamsMap);
            if (params.fileParamsMap != null && !params.fileParamsMap.isEmpty())
                fileParamsMap.putAll(params.fileParamsMap);
        }
    }

    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParamsMap.put(key, value);
        }
    }

    public void put(String key,byte[] value){
        if (key != null && value != null) {
            bytesParamsMap.put(key, new BytesWraper(value,guessMimeType(null)));
        }
    }

    public void put(String key, File file) {
        put(key, file, file.getName());
    }

    public void put(String key, File file, String fileName) {
        put(key, file, fileName, guessMimeType(fileName));
    }

    public void put(String key, File file, String fileName, MediaType contentType) {
        if (key != null) {
            fileParamsMap.put(key, new FileWrapper(file, fileName, contentType));
        }
    }

    public void removeUrl(String key) {
        urlParamsMap.remove(key);
    }

    public void removeFile(String key) {
        fileParamsMap.remove(key);
    }

    public void clear() {
        urlParamsMap.clear();
        fileParamsMap.clear();
    }

    private MediaType guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentType = fileNameMap.getContentTypeFor(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return MediaType.parse(contentType);
    }



    /**
     * 文件类型的包装类
     */
    public static class FileWrapper {
        public File file;
        public String fileName;
        public MediaType contentType;
        public long fileSize;

        public FileWrapper(File file, String fileName, MediaType contentType) {
            this.file = file;
            this.fileName = fileName;
            this.contentType = contentType;
            this.fileSize = file.length();
        }

        public String getFileName() {
            if (fileName != null) {
                return fileName;
            } else {
                return "nofilename";
            }
        }
    }

    public static class BytesWraper{
        public byte[] bytes;
        public MediaType contentType;
        public long fileSize;

        public BytesWraper(byte[] bytes,  MediaType contentType) {
            this.bytes = bytes;
            this.contentType = contentType;
            this.fileSize = bytes.length;
        }

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParamsMap.entrySet()) {
            if (result.length() > 0) result.append("&");
            result.append(entry.getKey()).append("=").append(entry.getValue());
        }
        for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParamsMap.entrySet()) {
            if (result.length() > 0) result.append("&");
            result.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return result.toString();
    }
}