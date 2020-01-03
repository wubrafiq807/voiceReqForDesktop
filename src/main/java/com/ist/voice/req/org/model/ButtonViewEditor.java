/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.model;

import com.ist.voice.req.org.util.Constant;
import com.ist.voice.req.org.util.Function;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ButtonViewEditor extends DefaultCellEditor implements Constant{

        protected JButton button;
        private final DeleteButtonListener bListener = new DeleteButtonListener();
        ArrayList<RecognitionResult> list;
        Function siteFunction=new Function();

        /**
         * Constructeur avec une checkBox
         * 
         * @param checkBox
         * @param count
         */
        @SuppressWarnings("deprecation")
        public ButtonViewEditor(JCheckBox checkBox,ArrayList<RecognitionResult> list) {
          
            super(checkBox);
            
            button = new JButton();
            button.setOpaque(true);
           
            button.addActionListener(bListener);
            this.list=list;

        }

        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
           
            bListener.setRow(row);
            
            bListener.setTable(table);

            button.setText(value == null ? "" : value.toString());
           
            return button;
        }

        class DeleteButtonListener implements ActionListener {

            private int row;
            private JTable table;

            public void setRow(int row) {
                this.row = row;
            }

            public void setTable(JTable table) {
                this.table = table;
            }

            @Override
            public void actionPerformed(ActionEvent event) {
             
                
            }
        }
    }
