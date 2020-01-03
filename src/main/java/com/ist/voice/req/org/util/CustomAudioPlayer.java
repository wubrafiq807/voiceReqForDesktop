/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author mhc
 */
public class CustomAudioPlayer {

    AudioStream ikon;
    InputStream in = null;

    public CustomAudioPlayer(String filePath) {

        try {
            in = new FileInputStream(new File(filePath));
            ikon = new AudioStream(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomAudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomAudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void startAudio() {
        AudioPlayer.player.start(ikon);
    }

    public void stopAudio() {
        AudioPlayer.player.stop(ikon);       
    }
    
   

}
