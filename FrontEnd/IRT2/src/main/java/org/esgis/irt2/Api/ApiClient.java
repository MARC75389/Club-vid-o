package org.esgis.irt2.Api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.esgis.irt2.Model.Abonne;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ApiClient {

    //Ici nous allons essyer d'appeler notre Backend

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String BASE_URL = "http://localhost:8080/api/abonnes";

    // Retourne un futur : la promesse qu'on aura une liste plus tard
    public CompletableFuture<List<Abonne>> getAbonnesAsync() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    try {
                        return objectMapper.readValue(response.body(), new TypeReference<List<Abonne>>() {});
                    } catch (Exception e) {
                        throw new RuntimeException("Erreur de conversion JSON", e);
                    }
                });
    }
}
