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
public class RecognitionResult {
    private String voice_req_id;
        private String text;
        private String audio_file_name;
        private String record_start_time;
        private String record_end_time;
        private String created_date;
        private String user_id;
        private int record_type; 

        public String getVoiceReqId() {
            return voice_req_id;
        }

        public void setVoiceReqId(String voice_req_id) {
            this.voice_req_id = voice_req_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAudioFileName() {
            return audio_file_name;
        }

        public void setAudioFileName(String audio_file_name) {
            this.audio_file_name = audio_file_name;
        }

        public String getRecordStartTime() {
            return record_start_time;
        }

        public void setRecordStartTime(String record_start_time) {
            this.record_start_time = record_start_time;
        }

        public String getRecordEndTime() {
            return record_end_time;
        }

        public void setRecordEndTime(String record_end_time) {
            this.record_end_time = record_end_time;
        }

        public String getCreatedDate() {
            return created_date;
        }

        public void setCreatedDate(String created_date) {
            this.created_date = created_date;
        }

        public String getUserID() {
            return user_id;
        }

        public void setUserID(String user_id) {
            this.user_id = user_id;
        }

        public int getRecordType() {
            return record_type;
        }

        public void setRecordType(int record_type) {
            this.record_type = record_type;
        }
}
