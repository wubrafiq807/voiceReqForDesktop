package com.ist.voice.req.org.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.ist.voice.req.org.util.Constant.START_RECORDING;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import edu.umd.cs.findbugs.ba.Path;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.security.SecureRandom;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import org.apache.http.HttpRequest;
import static org.apache.tools.ant.types.resources.MultiRootFileSet.SetType.file;
import org.json.JSONObject;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Utility implements Constant {

    public static String getGmailData() throws FileNotFoundException {        
        String state = new BigInteger(130, new SecureRandom()).toString(32);  
        return new Scanner(new File("index.html"), "UTF-8")
                .useDelimiter("\\A").next()
                .replaceAll("[{]{2}\\s*CLIENT_ID\\s*[}]{2}", "247819638124-0j5blnl15n00bt6oiagi436fupc61sgj.apps.googleusercontent.com")
                .replaceAll("[{]{2}\\s*STATE\\s*[}]{2}", state)
                .replaceAll("[{]{2}\\s*APPLICATION_NAME\\s*[}]{2}",
                        "voice-req");    
    }
    @SuppressWarnings({"unchecked"})
    public static void updateEnv(String name, String val) throws ReflectiveOperationException {
        Map<String, String> env = System.getenv();
        Field field = env.getClass().getDeclaredField("m");
        field.setAccessible(true);
        ((Map<String, String>) field.get(env)).put(name, val);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {       
        Date date = new Date();
        long time = date.getTime();
        System.out.println("Time in Milliseconds: " + new Function().convertTime(time).substring(10).trim());           
    }
}
