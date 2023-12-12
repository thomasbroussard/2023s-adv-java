package fr.epita.quiz.web.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epita.quiz.datamodel.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UserHttpClient {

    ObjectMapper mapper = new ObjectMapper();
    private final String url;

    public UserHttpClient(String url) {
        this.url = url;
    }

    private List<User> getUsers() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url)
                .openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        System.out.println("response code - " + responseCode);
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        String responseText = new String(bytes);

        List<User> users = mapper.readValue(responseText, new TypeReference<List<User>>() {
        });

        connection.disconnect();
        return users;
    }

    //client side
    private void createUser(User user) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url)
                .openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        String jsonString = mapper.writeValueAsString(user);
        connection.getOutputStream().write(jsonString.getBytes());
        connection.connect();
        int responseCode = connection.getResponseCode();
        System.out.println("response code - " + responseCode);
        System.out.println(new String(connection.getInputStream().readAllBytes()));
        connection.disconnect();
    }
}
