package com.disney.studios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import com.github.alexdlaird.ngrok.conf.JavaNgrokConfig;

/**
 * Bootstraps the Spring Boot com.disney.studios.Application
 *
 * Created by fredjean on 9/21/15.
 */
@SpringBootApplication
public class Application {

//    private static String authToken = "xxx";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        setNgrok();
    }

//    TODO Client tracking, NGrok Authentication

    /*
    *  Upon startup ngrok urls created automatically
    * */
    private static void setNgrok() {
        final NgrokClient ngrokClient = new NgrokClient.Builder().build();
//        ngrokClient.setAuthToken(authToken);
        final CreateTunnel createTunnel = new CreateTunnel.Builder()
                .withAddr(8080)
                .build();
        final Tunnel tunnel = ngrokClient.connect(createTunnel);
    }
}
