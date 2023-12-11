import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.epita.quiz.web.RootController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfiguration {


    @Bean("rootHandler")
    public HttpHandler rootHandler(){
        return new RootController();
    }

    @Bean
    public HttpServer getServer(@Qualifier("rootHandler") HttpHandler handler) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        int processors = Runtime.getRuntime().availableProcessors() / 2;
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        server.setExecutor(executor);
        HttpContext context = server.createContext("/test");
        context.setHandler(handler);
        return server;
    }
}
