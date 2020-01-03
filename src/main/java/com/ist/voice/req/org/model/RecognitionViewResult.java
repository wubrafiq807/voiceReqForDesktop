/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mhc
 */
public class RecognitionViewResult extends RecognitionResult {

    private String phones;
    private String names;
    private String emails;
    private String caller_phone_no;
    private String receiver_phone_no;
    private String duration; 

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getCallerPhoneNo() {
        return caller_phone_no;
    }

    public void setCallerPhoneNo(String caller_phone_no) {
        this.caller_phone_no = caller_phone_no;
    }

    public String getReceiverPhoneNo() {
        return receiver_phone_no;
    }

    public void setReceiverPhoneNo(String receiver_phone_no) {
        this.receiver_phone_no = receiver_phone_no;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    

}
