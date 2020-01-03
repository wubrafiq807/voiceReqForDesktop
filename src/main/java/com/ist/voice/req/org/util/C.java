/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.util;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class C extends JFrame {
  JPanel panel = new JPanel();
  JLabel label = new JLabel("Edit the text below");
  JTextField jtf = new JTextField("Text to change Label text");
  C() {
    label.setBorder(new LineBorder(Color.BLACK));
    panel.setLayout(new FlowLayout());
    panel.add(label);
    jtf.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        String txt = ((JTextField)ae.getSource()).getText();
        label.setText(txt);
        ((JTextField)ae.getSource()).setText("");
      }
    });
    setLayout(new BorderLayout());
    add(panel, BorderLayout.CENTER);
    add(jtf, BorderLayout.SOUTH);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  public static void main(String[] args) {
     C c = new C();
  }
}