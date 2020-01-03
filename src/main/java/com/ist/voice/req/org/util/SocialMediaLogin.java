/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ist.voice.req.org.util;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * FXML Controller class
 *
 * @author Azeem Tariq'
 */
public class SocialMediaLogin {

    public Map getloginDataFacebook() {
        String domain = "https://www.google.com";
        String appId = "116959219004019";

        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=public_profile,email";

        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        Map map = new HashMap();
        driver.get(authUrl);
        String accessToken = "";
        while (true) {
            if (!driver.getCurrentUrl().contains("facebook.com")) {
                String url = driver.getCurrentUrl();

                accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
                accessToken = accessToken.substring(0, accessToken.lastIndexOf("&"));
                System.out.println(accessToken);

                FBGraph fBGraph = new FBGraph(accessToken);
                map = fBGraph.getGraphData(fBGraph.getFBGraph());
                
                break;

            }

        }
        return map;

    }
}
