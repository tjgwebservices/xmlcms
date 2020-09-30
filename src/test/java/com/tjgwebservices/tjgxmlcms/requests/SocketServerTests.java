package com.tjgwebservices.tjgxmlcms.requests;

import com.tjgwebservices.tjgxmlcms.model.MessageListener;
import com.tjgwebservices.tjgxmlcms.model.SocketClient;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.websocket.server.ServerEndpoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


@ServerEndpoint("/socket")
public class SocketServerTests {

    private static final Logger logger
            = Logger.getLogger(SocketServerTests.class.getPackage().getName());
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    
    @Test
    public void testMessage() throws Exception {
        Optional <Duration> d;
        SocketClient client = new SocketClient();
        
        
    }
   
    
    @Test
    public void testCompletion() throws Exception {
        String message = "test";
        CompletionStage<?> cs = CompletableFuture.completedFuture(message)
                .thenAccept(System.out::println);
        IntStream.range(0,10).forEach(i -> {
        CompletableFuture<Void> tasks = CompletableFuture
                .supplyAsync(taskExecutor(i),executorService)
                .thenAcceptAsync(
                        resultsInt
                        -> sendMessage("test")
                );
        }); 
        Assertions.assertTrue(cs.toCompletableFuture().isDone());
    }

    @Test
    public void testAsyncronization() throws Exception {
        CompletableFuture<String> cf1 =
                CompletableFuture.completedFuture("one");
        CountDownLatch cdl = new CountDownLatch(1);
        CompletableFuture<String> cf2 =
        cf1.thenCompose(
                str->
                        CompletableFuture.supplyAsync(() ->
                        {
                            while(true) {
                                try {
                                    cdl.await();
                                    break;
                                    
                                } catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                            }
                            return str+", two";

                        }));
        cdl.countDown();
        String value = cf2.get();
        Assertions.assertNotNull(value);
        Assertions.assertEquals("one, two",value);
    }

    
    
    @Test
    public void testMessageStream() throws Exception {
        OutputStream os = OutputStream.nullOutputStream();
        DataOutputStream dataout = new DataOutputStream(
                new BufferedOutputStream(
            new FilterOutputStream(os)));
        for (int i=0;i<10; i++) {
            dataout.writeInt(i);
        }
        List<DataOutputStream> streamList = new ArrayList<DataOutputStream>();
        streamList.add(dataout);
        streamList.stream()
                .filter(i->i.size()>5)
                .map(pm->pm.getClass())
                .forEach(System.out::println);
        
    }
    
    
    private Supplier<Integer> taskExecutor(int i) {
        Supplier<Integer> func = () -> {
            int rnd = (int) (Math.random() * 100);
            try {
                Thread.sleep(rnd);
            } catch (InterruptedException ex) {
                Logger.getLogger(SocketServerTests.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rnd;
        };
        return func;
    }
    
    private boolean sendMessage(String message){
        MessageListener ml = new MessageListener();
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create("/topics"), ml); 
        return ws.isDone();
    }
    
    
    
}
