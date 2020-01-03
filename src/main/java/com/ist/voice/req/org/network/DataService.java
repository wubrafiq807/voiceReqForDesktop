/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.network;

import com.ist.voice.req.org.util.Constant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author mhc
 */
public class DataService implements Constant {

    private static String createParams(Map<String, String> params) throws UnsupportedEncodingException {

        String urlParameters = "";
        for (Map.Entry< String, String> item : params.entrySet()) {
            if (!item.getKey().equals(FILE_NAME_KEY) && item.getValue() != null) {
                String key = URLEncoder.encode(item.getKey(), "UTF-8");
                String value = URLEncoder.encode(item.getValue(), "UTF-8");
                if (!urlParameters.contains("?")) {
                    urlParameters += "?" + key + "=" + value;
                } else {
                    urlParameters += "&" + key + "=" + value;
                }
            }

        }
        return urlParameters;
    }

    public static Map<String, Object> netWorkCall(String url, String method, Map<String, String> params) throws IOException {
        try {
            System.err.println(params);
            if (method.equals("GET") && !params.isEmpty()) {
                url += createParams(params);
            }
            int postDataLength = 0;
            byte[] postData = null;
            File uploadFile = null;
            if (!method.equals("GET") && !params.isEmpty()) {

                String urlParameters = createParams(params);
                urlParameters = urlParameters.replace("?", "").trim();
                postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                postDataLength = postData.length;

            }
            System.err.println(postData);

            URL urlCon = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlCon.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("x-api-key", API_KEY);
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestMethod(method);
            String boundary = Long.toHexString(System.currentTimeMillis());
            if (params.containsKey(FILE_NAME_KEY)) {
                uploadFile = new File(VOICE_RECORDING_FILE_STORAGE + "//" + params.get(FILE_NAME_KEY));
                con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            }

            con.setRequestProperty("charset", "utf-8");
            if (!method.equals("GET") && !params.isEmpty()) {
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                con.setUseCaches(false);
                try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                    wr.write(postData);
                    if (params.containsKey(FILE_NAME_KEY)) {
                        wr.writeBytes("--" + boundary + "\r\n");
                        wr.writeBytes("Content-Disposition: form-data; name=\"audioFile\"; filename=\"" + uploadFile.getName() + "\"\r\n\r\n");
                        wr.write(FileUtils.readFileToByteArray(uploadFile));
                        wr.writeBytes("\r\n");

                        wr.writeBytes("--" + boundary + "--\r\n");
                        wr.flush();

                    }
                }
            }

            String responseBody = "";
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
                responseBody = br.lines().collect(Collectors.joining());

            }
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("code", responseCode);
            responseMap.put("body", responseBody);

            return responseMap;
        } catch (Exception e) {
        }
        return null;

    }

}
