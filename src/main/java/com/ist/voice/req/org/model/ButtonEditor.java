/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.model;

import com.ist.voice.req.org.form.RecognitionList;
import com.ist.voice.req.org.form.RecognitionView;
import com.ist.voice.req.org.util.Constant;
import static com.ist.voice.req.org.util.Constant.REQ_DELETE_CONFIMATION_TEXT;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ButtonEditor extends DefaultCellEditor implements Constant {
    
    protected JButton button;
    private final DeleteButtonListener bListener = new DeleteButtonListener();
    private ArrayList<RecognitionResult> list;
    Function siteFunction = new Function();
    private  int type;

    /**
     * Constructeur avec une checkBox
     *
     * @param checkBox
     * @param list
     * @param type
     */
    @SuppressWarnings("deprecation")
    public ButtonEditor(JCheckBox checkBox, ArrayList<RecognitionResult> list, int type) {
      
        super(checkBox);      
        button = new JButton();
        button.setOpaque(true);      
        button.addActionListener(bListener);
        this.list = list;
        this.type = type;
        
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
            System.out.print(type);
            switch (type) {
                case REQ_ACTION_DELETE:
                    actionPerformedDelete(event);
                    break;
                case REQ_ACTION_VIEW:
                    actionPerformedView(event);
                    break;
            }
            
        }
        
        private void actionPerformedDelete(ActionEvent event) {
            if (siteFunction.confirmBeforeAction(REQ_DELETE_CONFIMATION_TEXT) == 0) {
                if (table.getRowCount() > 0) {                   
                    ((DefaultTableModel) table.getModel()).removeRow(this.row);
                    ButtonEditor.this.cancelCellEditing();                    
                    try {
                        RecognitionListModel reqModel = siteFunction.deleteRecongnition(list.get(this.row).getVoiceReqId());
                        siteFunction.showAlertMessage(reqModel.getMessage());
                    } catch (IOException ex) {
                        Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        }

        private void actionPerformedView(ActionEvent event) {
           
            try {              
                new RecognitionList().close();                            
                new RecognitionView(list.get(this.row).getVoiceReqId()).setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(ButtonEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
}
