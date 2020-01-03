/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.model;

import com.ist.voice.req.org.util.Constant;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mhc
 */
//public class  ButtonRenderer extends JButton implements TableCellRenderer {
//
//    public ButtonRenderer() {
//        setOpaque(false);
//        setBackground(Color.red);
//    }
//
//  
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//           if (isSelected) {
//            setForeground(table.getSelectionForeground());
//            setBackground(table.getSelectionBackground());
//        } else {
//            setForeground(table.getForeground());
//            setBackground(UIManager.getColor("Button.background"));
//        }
//        setText((value == null) ? "" : value.toString());
//        return this;
//    }
//    
//}
public class ButtonRenderer extends JButton implements
        TableCellRenderer,Constant {
    int type;
    public ButtonRenderer(int type){
    this.type=type;
    }

    private static final Color beige = new Color(218, 217, 158);

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean isFocus, int row,
            int col) {
        switch(type){
        case REQ_ACTION_DELETE:
             setBackground(beige);
            break;
        case REQ_ACTION_VIEW:
             setBackground(new Color(144, 239, 140));
            break;
        }
       
        // On écrit dans le bouton ce que contient la cellule
        setText(value != null ? value.toString() : "");
        // on retourne notre bouton
        return this;
    }
}
