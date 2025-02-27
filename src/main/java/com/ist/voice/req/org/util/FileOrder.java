/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.util;

public class FileOrder implements Comparable<FileOrder> {
        private String fileName;
        private Long updationTIme = 0l;

        @Override
        public String toString() {
            return "FileOrder [fileName=" + fileName + ", updationTIme=" + updationTIme + "]";
        }

        public FileOrder(String fileName, Long updationDate) {
            super();
            this.fileName = fileName;
            this.updationTIme = updationDate;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public Long getUpdationDate() {
            return updationTIme;
        }

        public void setUpdationDate(Long updationDate) {
            this.updationTIme = updationDate;
        }

        @Override
        public int compareTo(FileOrder o) {
            return o.getUpdationDate().compareTo(this.getUpdationDate());
        }

    }