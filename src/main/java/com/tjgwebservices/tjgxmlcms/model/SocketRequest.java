package com.tjgwebservices.tjgxmlcms.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.input.CharSequenceReader;

public class SocketRequest implements WebSocket {

    private CompletableFuture<Reader> reader;
    private String subProtocol;
    private boolean outputClosed;
    private boolean inputClosed;

    @Override
    public CompletableFuture<WebSocket> sendText(CharSequence data, boolean last) {        
        CharSequenceReader csr = new CharSequenceReader(data);       
        BufferedReader bufferedreader = new BufferedReader(csr);
        CompletableFuture<WebSocket> readFuture = new CompletableFuture<>();
        try {
            String line = bufferedreader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(SocketRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        reader.thenAccept(reader -> {
            readFuture.isDone();
        });
        return readFuture;
    }

    @Override
    public CompletableFuture<WebSocket> sendBinary(ByteBuffer data, boolean last) {
        CompletableFuture<WebSocket> readFuture = new CompletableFuture<>();
        reader.thenAccept(reader -> {
            int i = data.get();
            if (!data.hasRemaining())
            readFuture.isDone();
        });
        return readFuture;                
    }

    @Override
    public CompletableFuture<WebSocket> sendPing(ByteBuffer message) {
        CompletableFuture<WebSocket> readFuture = new CompletableFuture<>();
        reader.thenAccept(reader -> {
            int i = message.get();
            if (!message.hasRemaining())
            readFuture.isDone();
        });
        return readFuture;
    }

    @Override
    public CompletableFuture<WebSocket> sendPong(ByteBuffer message) {
        CompletableFuture<WebSocket> readFuture = new CompletableFuture<>();
        reader.thenAccept(reader -> {
            int i = message.get();
            if (!message.hasRemaining())
            readFuture.isDone();
        });
        return readFuture;
    }
    
    public enum StatusCodes {
        UNAUTHORIZED,
        BAD_REQUEST,
        FORBIDDEN,
        NOT_FOUND
    }

    @Override
    public CompletableFuture<WebSocket> sendClose(int statusCode, String reason) {
        CompletableFuture<WebSocket> readFuture = new CompletableFuture<>();
        boolean requestType = false;
        String[] requests = {"Unauthorized","Bad_Request",
        "Forbidden","Not_Found"};                
        reader.thenAccept(reader -> {
            Arrays.stream(requests).anyMatch(t -> t.equals(reason));
            readFuture.isDone();
        });
        return readFuture;
    }

    @Override
    public void request(long n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSubprotocol() {
        return subProtocol;
    }

    @Override
    public boolean isOutputClosed() {
        return outputClosed;
    }

    @Override
    public boolean isInputClosed() {
        return inputClosed;
    }

    @Override
    public void abort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CompletableFuture<Reader> getReader() {
        return reader;
    }

    public void setReader(CompletableFuture<Reader> reader) {
        this.reader = reader;
    }

    public void setSubProtocol(String subProtocol) {
        this.subProtocol = subProtocol;
    }
    
}
