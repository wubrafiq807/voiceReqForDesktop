/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.util;

import static com.ist.voice.req.org.util.Constant.API_KEY;
import groovy.util.logging.Log;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author mhc
 */
public class Test {

    public static void main(String[] args) throws IOException {
//        Function siteFuntion = new Function();
//        HttpURLConnection connection = null;
//        DataOutputStream outputStream = null;
//        DataInputStream inputStream = null;
//
//        //  String pathToOurFile = sourceFileUri;
//        String urlServer = "http://192.168.100.74:8080/api-v1/voiceReqs/";
//        String lineEnd = "\r\n";
//        String twoHyphens = "--";
//        String boundary = "*****";
//
//        int bytesRead, bytesAvailable, bufferSize;
//        byte[] buffer;
//        int maxBufferSize = 1 * 1024 * 1024;
//
//        try {
//            String sourceFileUri = "E:\\ai_project\\speech-req-sys-desktop\\maven\\master\\voice\\sytem\\files\\513b3e64913d44539825bc9132adee95.wav";
//
//            FileInputStream fileInputStream = new FileInputStream(new File(sourceFileUri));
//
//            URL url = new URL(urlServer);
//            connection = (HttpURLConnection) url.openConnection();
//            String urlParameters = "user_id=13bf1980-3a0d-41dd-bc8c-b2b80b4c1a5f&param2=b&param3=c";
//            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
//            int postDataLength = postData.length;
//            // Allow Inputs & Outputs
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setUseCaches(false);
//            connection.setRequestProperty("x-api-key", API_KEY);
//            connection.setRequestProperty("x-api-key", API_KEY);
//
//            // Enable POST method
//            connection.setRequestMethod("POST");
//
//            connection.setRequestProperty("Connection", "Keep-Alive");
//            connection.setRequestProperty("Content-Type",
//                    "multipart/form-data;boundary=" + boundary);
//            connection.setRequestProperty("charset", "utf-8");
//            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
//            outputStream = new DataOutputStream(connection.getOutputStream());
//            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
//            outputStream
//                    .writeBytes("Content-Disposition: form-data; name=\"audioFile\";filename=\""
//                            + sourceFileUri + "\"" + lineEnd);
//            outputStream.writeBytes(lineEnd);
//             outputStream.write( postData );
//
//            bytesAvailable = fileInputStream.available();
//            bufferSize = Math.min(bytesAvailable, maxBufferSize);
//            buffer = new byte[bufferSize];
//
//            // Read file
//            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//            while (bytesRead > 0) {
//
//                outputStream.write(buffer, 0, bufferSize);
//                bytesAvailable = fileInputStream.available();
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//            }
//
//            outputStream.writeBytes(lineEnd);
//            outputStream.writeBytes(twoHyphens + boundary + twoHyphens
//                    + lineEnd);
//
//            // Responses from the server (code and message)
//            int serverResponseCode = connection.getResponseCode();
//            String serverResponseMessage = connection.getResponseMessage();
//            String responseBody = "";
//            System.err.println("Test:" + serverResponseCode + "   " + serverResponseMessage);
//            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
//            responseBody = br.lines().collect(Collectors.joining());
//            fileInputStream.close();
//            outputStream.flush();
//            outputStream.close();
//            System.err.println("result:" + responseBody);
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//            // Exception handling
//        } // end upLoad2Serve
//
//        
//        
//        

        String charset = "UTF-8";
        String requestURL = "http://192.168.100.74:8080/api-v1/voiceReqs/";
        String sourceFileUri = "E:\\ai_project\\speech-req-sys-desktop\\maven\\master\\voice\\sytem\\files\\513b3e64913d44539825bc9132adee95.wav";
        MultipartUtility multipart = new MultipartUtility(requestURL, charset);
        multipart.addFormField("user_id", "13bf1980-3a0d-41dd-bc8c-b2b80b4c1a5f");
        multipart.addFormField("record_type", "1");
        multipart.addFormField("record_start_time", "14:10:12");
        multipart.addFormField("record_end_time ", "17:15:12");
        multipart.addFilePart("audioFile", new File(sourceFileUri));
        multipart.addHeaderField("x-api-key", API_KEY);
        String response = multipart.finish(); // response from server.
        System.err.println(response);
       
    }

    public void whenSendMultipartRequestUsingHttpClient_thenCorrect()
            throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("username", "John");
        builder.addTextBody("password", "pass");
//    builder.addBinaryBody("file", new File("test.txt"),
//      ContentType.APPLICATION_OCTET_STREAM, "file.ext");

        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
    }
}
