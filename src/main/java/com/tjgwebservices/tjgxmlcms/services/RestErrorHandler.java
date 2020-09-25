package com.tjgwebservices.tjgxmlcms.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import jdk.internal.org.jline.utils.InputStreamReader;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class RestErrorHandler extends DefaultResponseErrorHandler{

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        InputStream isb = clientHttpResponse.getBody();
        byte[] b = isb.readAllBytes();
        String body = new String(b);
        //InputStreamReader isr = new InputStreamReader(isb);
        //BufferedReader br = new BufferedReader(isr);
        //String body = br.lines().collect(Collectors.joining("\n"));
        String message;
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        System.out.println("Status"+clientHttpResponse.getStatusCode().series());
        
        if (statusCode.series() == HttpStatus.Series.CLIENT_ERROR) {
            message = "Client error: "+body;
        } else if (statusCode.series() == HttpStatus.Series.SERVER_ERROR) {
            message = "Server error: "+body; 
        } else if (statusCode == NOT_FOUND) {
            message = "Not found: "+body;             
        } else {
            message = "Other error";
        }
        System.out.println(message);
        try {
            throw new RestCustomException(message);
        } catch (Exception ex) {
            Logger.getLogger(RestErrorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
