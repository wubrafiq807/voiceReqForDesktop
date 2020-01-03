/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.model;

/**
 *
 * @author mhc
 */
public class RecognitionViewModel extends CommonModel{
    private RecognitionViewResult result=new RecognitionViewResult();    

    public RecognitionViewResult getResult() {
        return result;
    }

    public void setResult(RecognitionViewResult result) {
        this.result = result;
    }
    
}
