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
public class RecognitionListModel extends CommonModel{

   
    private List<RecognitionResult> result=new ArrayList<RecognitionResult>();    

    public List<RecognitionResult> getResult() {
        return result;
    }

    public void setResult(List<RecognitionResult> result) {
        this.result = result;
    }
   

   
}
