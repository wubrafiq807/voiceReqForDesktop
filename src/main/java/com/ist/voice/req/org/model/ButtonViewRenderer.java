/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

 public class ButtonViewRenderer extends JButton implements
            TableCellRenderer {

        private static final Color beige = new Color(218, 217, 158);

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean isFocus, int row,
                int col) {
            setBackground(beige);
           
            setText(value != null ? value.toString() : "");
           
            return this;
        }
    }