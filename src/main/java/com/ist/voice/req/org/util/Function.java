/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ist.voice.req.org.form.Login;
import com.ist.voice.req.org.form.RecognitionView;
import com.ist.voice.req.org.model.LoginModel;
import com.ist.voice.req.org.model.RecognitionCreationModel;
import com.ist.voice.req.org.model.RecognitionListModel;
import com.ist.voice.req.org.model.RecognitionViewModel;

import com.ist.voice.req.org.network.DataService;
import static com.ist.voice.req.org.network.DataService.netWorkCall;
import com.ist.voice.req.org.util.Constant;
import static com.ist.voice.req.org.util.Constant.VOICE_RECORDING_FILE_STORAGE;
import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import static org.apache.tools.ant.types.resources.MultiRootFileSet.SetType.file;
import org.json.JSONObject;

/**
 *
 * @author mhc
 */
public class Function implements Constant {

    private Pattern regexPattern;
    private Matcher regMatcher;
    private String fromDate = null;
    private String toDate = null;

    public boolean validateEmailAddress(String emailAddress) {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher = regexPattern.matcher(emailAddress);
        if (regMatcher.matches()) {
            return true;
        }
        return false;
    }

    public void showAlertMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    //function for checking login validation for manually
    public Map<String, Object> checkLoginValidation(String email, String password) throws IOException {
        Map<String, Object> result = new HashMap<>();
        String message = "";
        String url = API_BASE_URL + "/api-v1/users/";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);
        Map<String, Object> rsponse = DataService.netWorkCall(url, "GET", params);
        int responseCode = (int) rsponse.get("code");
        LoginModel userLoginModel = new LoginModel();
        boolean error = true;
        if (responseCode == 200) {
            message = "";
            error = false;

            System.out.println(rsponse.get("body"));
            Gson gson = new Gson();

            Type type = new TypeToken<LoginModel>() {
            }.getType();
            String str = rsponse.get("body").toString();
            userLoginModel = gson.fromJson(str, type);
            // check is login failed
            if (userLoginModel.isError()) {
                error = true;
            }
            message = userLoginModel.getMessage();

        } else {
            message = "Internal server error code:" + responseCode;
        }

        result.put("error", error);
        result.put("body", userLoginModel);
        return result;
    }

    //function for checking login validation for manually
    public Map<String, Object> signUpuser(String email, String password) throws IOException {
        Map<String, Object> result = new HashMap<>();

        String url = API_BASE_URL + "/api-v1/users/";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);
        params.put("login_type", "1");
        Map<String, Object> rsponse = DataService.netWorkCall(url, "POST", params);
        int responseCode = (int) rsponse.get("code");
        LoginModel userLoginModel = new LoginModel();
        boolean error = true;
        if (responseCode == 200) {

            error = false;

            System.out.println(rsponse.get("body"));
            Gson gson = new Gson();

            Type type = new TypeToken<LoginModel>() {
            }.getType();
            String str = rsponse.get("body").toString();
            userLoginModel = gson.fromJson(str, type);
            // check is login failed
            if (userLoginModel.isError()) {
                error = true;
            }

        }

        result.put("error", error);
        result.put("body", userLoginModel);
        return result;
    }

    //function for checking login validation for manually
    public LoginModel getUserByID() throws IOException {
        Map<String, Object> result = new HashMap<>();

        String url = API_BASE_URL + "/api-v1/users/" + this.loadSessionData().get(LOGIN_USER_ID) + "/";
        HashMap<String, String> params = new HashMap<String, String>();

        Map<String, Object> rsponse = DataService.netWorkCall(url, "GET", params);
        int responseCode = (int) rsponse.get("code");
        LoginModel userLoginModel = new LoginModel();
        boolean error = true;
        if (responseCode == 200) {

            error = false;

            System.out.println(rsponse.get("body"));
            Gson gson = new Gson();

            Type type = new TypeToken<LoginModel>() {
            }.getType();
            String str = rsponse.get("body").toString();
            userLoginModel = gson.fromJson(str, type);
            // check is login failed
            if (userLoginModel.isError()) {
                error = true;
            }

        }

        return userLoginModel;
    }

    //function for checking login validation for manually
    public LoginModel updateUser(String email, String password, String name, String phone) throws IOException {

        String url = API_BASE_URL + "/api-v1/users/" + this.loadSessionData().get(LOGIN_USER_ID) + "/";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);
        params.put("phone", phone);
        params.put("name", name);
        Map<String, Object> rsponse = DataService.netWorkCall(url, "PUT", params);
        int responseCode = (int) rsponse.get("code");
        LoginModel userLoginModel = new LoginModel();
        boolean error = true;
        if (responseCode == 200) {

            error = false;

            System.out.println(rsponse.get("body"));
            Gson gson = new Gson();

            Type type = new TypeToken<LoginModel>() {
            }.getType();
            String str = rsponse.get("body").toString();
            userLoginModel = gson.fromJson(str, type);
            // check is login failed
            if (userLoginModel.isError()) {
                error = true;
            }

        }

        return userLoginModel;
    }

    //function for checking login validation for manually
    public LoginModel sendRequestToForgetPassword(String email) throws IOException {

        String url = API_BASE_URL + "/api-v1/get-password/users/" + email;
        HashMap<String, String> params = new HashMap<String, String>();

        Map<String, Object> rsponse = DataService.netWorkCall(url, "POST", params);
        int responseCode = (int) rsponse.get("code");
        LoginModel userLoginModel = new LoginModel();
        boolean error = true;
        if (responseCode == 200) {

            error = false;

            System.out.println(rsponse.get("body"));
            Gson gson = new Gson();

            Type type = new TypeToken<LoginModel>() {
            }.getType();
            String str = rsponse.get("body").toString();
            userLoginModel = gson.fromJson(str, type);
            // check is login failed
            if (userLoginModel.isError()) {
                error = true;
            }

        }
        userLoginModel.setError(error);

        return userLoginModel;
    }

    //function for checking login validation for manually
    public RecognitionListModel getRecognitionList(String userid) throws IOException {

        String url = API_BASE_URL + "/api-v1/voiceReqs/";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("user_id", userid);
        params.put("fromDate", this.fromDate);
        params.put("toDate", this.toDate);

        Map<String, Object> rsponse = DataService.netWorkCall(url, "GET", params);
        int responseCode = (int) rsponse.get("code");
        RecognitionListModel recognitionListModel = new RecognitionListModel();
        boolean error = true;
        if (responseCode == 200) {

            error = false;

            System.out.println(rsponse.get("body"));
            Gson gson = new Gson();

            Type type = new TypeToken<RecognitionListModel>() {
            }.getType();
            String str = rsponse.get("body").toString();
            recognitionListModel = gson.fromJson(str, type);
            // check is login failed
            if (recognitionListModel.isError()) {
                error = true;
            }

        }
        recognitionListModel.setError(error);
        if (recognitionListModel.isError() && !recognitionListModel.getMessage().equals("success")) {
            this.showAlertMessage(recognitionListModel.getMessage());
        }

        return recognitionListModel;
    }

    public RecognitionListModel getRecognitionList(String userid, String fromDate, String toDate) throws IOException {
        this.fromDate = fromDate;
        this.toDate = toDate;
        return this.getRecognitionList(userid);
    }

    public RecognitionListModel deleteRecongnition(String recognition_id) throws IOException {

        String url = API_BASE_URL + "/api-v1/voiceReqs/" + recognition_id + "/";
        HashMap<String, String> params = new HashMap<String, String>();
        Map<String, Object> rsponse = DataService.netWorkCall(url, "DELETE", params);
        int responseCode = (int) rsponse.get("code");
        RecognitionListModel recognitionListModel = new RecognitionListModel();
        boolean error = true;
        if (responseCode == 200) {

            error = false;

            System.out.println(rsponse.get("body"));
            Gson gson = new Gson();

            Type type = new TypeToken<RecognitionListModel>() {
            }.getType();
            String str = rsponse.get("body").toString();
            recognitionListModel = gson.fromJson(str, type);
            // check is login failed
            if (recognitionListModel.isError()) {
                error = true;
            }

        }
        recognitionListModel.setError(error);

        return recognitionListModel;
    }

    public RecognitionViewModel getRecongnition(String recognition_id) throws IOException {

        String url = API_BASE_URL + "/api-v1/voiceReqs/" + recognition_id + "/";
        HashMap<String, String> params = new HashMap<String, String>();
        Map<String, Object> rsponse = DataService.netWorkCall(url, "GET", params);
        int responseCode = (int) rsponse.get("code");
        RecognitionViewModel recognitionModel = new RecognitionViewModel();
        boolean error = true;
        if (responseCode == 200) {

            error = false;

            System.out.println(rsponse.get("body"));
            Gson gson = new Gson();

            Type type = new TypeToken<RecognitionViewModel>() {
            }.getType();
            String str = rsponse.get("body").toString();
            recognitionModel = gson.fromJson(str, type);
            // check is login failed
            if (recognitionModel.isError()) {
                error = true;
            }

        }
        recognitionModel.setError(error);

        return recognitionModel;
    }

    public RecognitionCreationModel createRecognition(String fileName, String recordStartTime, String recordEndTime) throws IOException {

        System.err.println("FileName" + fileName);
        System.err.println("");
        String charset = "UTF-8";
        String requestURL = API_BASE_URL + "/api-v1/voiceReqs/";
        String sourceFileUri = VOICE_RECORDING_FILE_STORAGE + "\\" + fileName;
        System.err.println("usr:" + sourceFileUri);
        MultipartUtility multipart = new MultipartUtility(requestURL, charset);
        multipart.addFormField("user_id", loadSessionData().get(LOGIN_USER_ID));
        multipart.addFormField("record_type", "1");
        multipart.addFormField("record_start_time", recordStartTime);
        multipart.addFormField("record_end_time ", recordEndTime);
        multipart.addFilePart("audioFile", new File(sourceFileUri));
        multipart.addHeaderField("x-api-key", API_KEY);
        String response = multipart.finish();
        System.err.println(response);
        RecognitionCreationModel recognitionModel = new RecognitionCreationModel();
        Gson gson = new Gson();
        Type type = new TypeToken<RecognitionCreationModel>() {
        }.getType();
        recognitionModel = gson.fromJson(response, type);

        return recognitionModel;
    }

    public void saveSession(Map<String, String> prams) {
        try (OutputStream output = new FileOutputStream("/config.properties")) {
            Properties prop = new Properties();
            for (Map.Entry<String, String> entrySet : prams.entrySet()) {
                // set the properties value
                prop.setProperty(entrySet.getKey(), entrySet.getValue());
            }
            // save properties to project root folder
            prop.store(output, null);
            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
    // method for saving login data into user inviromnet

    public void saveLoginSession(String user_id, String name, String email) {
        Map<String, String> params = new HashMap<>();
        params.put(LOGIN_USER_ID, user_id);
        params.put(LOGIN_USER_NAME, name);
        params.put(LOGIN_USER_EMAIL, email);
        this.saveSession(params);
    }
// function for unset the login data

    public void unsetLoginData() {
        Map<String, String> params = new HashMap<>();
        params.put(LOGIN_USER_ID, "");
        params.put(LOGIN_USER_NAME, "");
        params.put(LOGIN_USER_EMAIL, "");
        this.saveSession(params);
    }
// method for loading session data

    public Map<String, String> loadSessionData() {

        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("/config.properties")) {
            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Map<String, String> map = new HashMap<String, String>((Map) prop);
        return map;
    }
// methood cheking login

    public boolean isLogin() {
        if (!this.loadSessionData().get(LOGIN_USER_ID).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String getDateFormate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new Date(dateString);
        return formatter.format(date);
    }

    public int confirmBeforeAction(String message) {
        // 0=yes, 1=no
        return JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
    }

    public void confirmDirectory(String directory, String filepath) throws IOException {
        File fileNEw = new File(filepath);
        confirmDirectory(directory);
        if (!fileNEw.isFile()) {
            fileNEw.createNewFile();
        }
    }

    public void confirmDirectory(String directory) throws IOException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

    }

    public void updatedRecordingFlag(int flag) throws IOException {
        confirmDirectory(VOICE_RECORDING_FOLDER_NAME, VOICE_RECORDING_FILE_NAME);
        JSONObject obj = new JSONObject();
        obj.put(VOICE_RECORDING_FLAG, "" + flag + "");
        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter(VOICE_RECORDING_FILE_NAME)) {
            file.write(obj.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        } catch (IOException ex) {
            Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public java.util.List<FileOrder> getFileFromDirectory(String path) {
        java.util.List<FileOrder> list = new ArrayList<FileOrder>();
        File dir = new File(path);
        for (File file : dir.listFiles()) {
            list.add(new FileOrder(file.getName(), file.lastModified()));
        }
        Collections.sort(list);
        return list;
    }

    public int confirDeleteORPlay() {
        Object[] options1 = {"Play", "Delte",
            "Quit"};
        ImageIcon icon = new ImageIcon("src/images/deleteorPlay.png");
        JPanel panel = new JPanel();
        JLabel textField = new JLabel();
        textField.setText("Play or Delete?.If you click on Delt Button .You recording will be deleted for ever .");
        panel.add(textField);
        int result = JOptionPane.showOptionDialog(null, panel, "Play or Delete record confirmation.",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                icon, options1, null);
        return result;
    }

    public void deleteFile(String path) {
        File xx = new File(path);
        if (xx.exists()) {
            try {
                System.gc();//Added this part
                Thread.sleep(2000);////This part gives the Bufferedreaders and the InputStreams time to close Completely
                xx.delete();
            } catch (InterruptedException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String getCurrentTime() {
        try {
            return convertTime(new Date().getTime()).substring(10).trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void disposeJFrame(JFrame frame) {
        frame.setVisible(false);
        frame.dispose();
    }

}
