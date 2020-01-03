/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.util;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author mhc
 */
public interface Constant {
// Constant for Api 

    public static final String API_KEY = "23c7d82c-824a-4669-ac1b-92520c5934bf";
    public static final String API_BASE_URL = "http://192.168.100.74:8080";
    //Constants for login sesssion
    public static final String ABSOLUTE_PATH=new File("").getAbsolutePath();
    public static final String LOGIN_USER_ID = "user.id";
    public static final String LOGIN_USER_NAME = "user.name";
    public static final String LOGIN_USER_EMAIL = "user.email";
    public static final String REQ_LIST_DELETE_TEXT = "Delete";
    public static final String REQ_LIST_VIEW_TEXT = "Show Details";
    public static final String REQ_DELETE_CONFIMATION_TEXT = "Are you sure,you want to delete?";
    public static final int REQ_ACTION_DELETE = 1;
    public static final int REQ_ACTION_VIEW = 2;
    public static final String VOICE_RECORDING_FLAG ="voice_record_closed_flag";
    public static final String VOICE_RECORDING_FOLDER_NAME =ABSOLUTE_PATH+"\\voice\\sytem";  
    public static final String VOICE_RECORDING_FILE_NAME =ABSOLUTE_PATH+"\\voice\\sytem\\jsonData.json";
    public static final String VOICE_RECORDING_FILE_STORAGE =ABSOLUTE_PATH+"\\voice\\sytem\\files";
    public static final int STOP_RECORDING = 1;
    public static final int START_RECORDING = 0;
    public static final String PYTHON_PATH=ABSOLUTE_PATH+"\\python\\python.exe";
    public static final int RECORD_DELETE_FILE = 1;
    public static final int RECORD_PLAY_FILE = 0;
   //constant for file upload 
      public static final String FILE_NAME_KEY="fileName";
    
    
}
