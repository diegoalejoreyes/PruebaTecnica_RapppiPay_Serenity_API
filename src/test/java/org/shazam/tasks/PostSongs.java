package org.shazam.tasks;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.util.EnvironmentVariables;
import org.shazam.utils.CargaPayload;

import javax.inject.Inject;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class PostSongs implements Task {

    @Inject
    EnvironmentVariables environmentVariables;
//    private final String bodytxt;
//
//    public PostSongs(String bodytxt) {
//        this.bodytxt = bodytxt;
//    }

    public static Performable PostSongsTexto(){
        return instrumented(PostSongs.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("base.url");
        String apiKey = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("apiKey");
        String host = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("host");
        String payloadPath = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("payload");

        String body = CargaPayload.cargaArchivo(payloadPath);

        actor.whoCan(CallAnApi.at(baseUrl));
        actor.attemptsTo(
                Post.to("/songs/v2/detect").with(requestSpecification -> requestSpecification
                        .header("X-Rapidapi-Host" , host)
                        .header("X-Rapidapi-Key" , apiKey)
                        .header("Content-Type" , "text/plain")
                        .body(body))
        );

    }
}
