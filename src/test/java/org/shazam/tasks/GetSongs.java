package org.shazam.tasks;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.util.EnvironmentVariables;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetSongs implements Task {

    private EnvironmentVariables environmentVariables;
    private final String id;

    public GetSongs(String id) {
        this.id = id;
    }

    public static Performable getSongID(String id){
        return instrumented(GetSongs.class, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("base.url");
        String apiKey = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("apiKey");
        String host = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("host");

        actor.whoCan(CallAnApi.at(baseUrl));

        actor.attemptsTo(
                Get.resource("/songs/v2/get-details").with(requestSpecification -> requestSpecification
                                .header("X-Rapidapi-Host" , host)
                                .header("X-Rapidapi-Key" , apiKey)
                                .param("id", id))
        );



//        actor.should(seeThatResponse(
//                validatableResponse -> validatableResponse.statusCode(200)
//                )
//        );

    }
}
