/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.util;

import java.awt.Component;
import static javafx.scene.input.KeyCode.Y;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mhc
 */
public class TableRender  extends DefaultTableCellRenderer{
    DefaultTableModel mtm;

    public TableRender(DefaultTableModel mtm) {
        this.mtm = mtm;
    }
    
//    @Override
// public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
//    super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
//    this.mtm = (DefaultTableModel)table.getModel();
//    switch(column){
//        case Y : 
//            if(mla.getValueAt(row,X)!=null)
//                setHorizontalAlignment(SwingConstants.RIGHT);
//            else
//                setHorizontalAlignment(SwingConstants.LEFT);
//            break;
//     }
//    return this;
//   }

    
}
