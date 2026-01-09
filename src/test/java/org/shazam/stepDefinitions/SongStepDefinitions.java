package org.shazam.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;
import org.shazam.questions.LastResponseStatusCode;
import org.shazam.tasks.GetSongs;
import org.shazam.tasks.PostSongs;
import org.shazam.utils.CargaPayload;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.MatcherAssert.assertThat;

public class SongStepDefinitions {
    private int statusCode;
    private EnvironmentVariables environmentVariables;

    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("consulto los detalles de la cancion con {string}")
    public void consulto_los_detalles_de_la_cancion_con(String ID) {

        theActorCalled("user").attemptsTo(GetSongs.getSongID(ID));

    }
    @Then("el {int} de respuesta debe ser exitoso")
    public void el_de_respuesta_debe_ser_exitoso(int codigo) {
        int actualStatus = SerenityRest.lastResponse().getStatusCode();

        SerenityRest.lastResponse().then().statusCode(codigo);
        Serenity.recordReportData().withTitle("codigo obtenido").andContents("El codigo es: " + actualStatus);

    }
    @Then("la respuesta debe contener el titulo de la cancion")
    public void la_respuesta_debe_contener_el_titulo_de_la_cancion() {

        String nombreCancion = SerenityRest.lastResponse().jsonPath().getString("data[0].attributes.name");
        String nombreArtista = SerenityRest.lastResponse().jsonPath().getString("data[0].attributes.artistName");
        Serenity.recordReportData().withTitle("cancion consultada").andContents("" + nombreCancion);
        Serenity.recordReportData().withTitle("artista consultado").andContents("" + nombreArtista);

    }

    @Given("ejecuto el servicio Post con un txt de entrada para detectar una cancion")
    public void ejecuto_el_servicio_post_con_un_txt_de_entrada_para_detectar_una_cancion() {

        theActorCalled("user").attemptsTo(PostSongs.PostSongsTexto());
    }

    @Then("el servicio responde con un codigo exitoso")
    public void el_servicio_responde_con_un_codigo_exitoso() {

        theActorCalled("user").should(
                seeThat(LastResponseStatusCode.code())
        );
    }

    @Then("la respuesta debe contener el track de la cancion")
    public void la_respuesta_debe_contener_el_track_de_la_cancion() {
        String nombreCancion = SerenityRest.lastResponse().jsonPath().getString("track.title");
        String nombreArtista = SerenityRest.lastResponse().jsonPath().getString("track.subtitle");
        Serenity.recordReportData().withTitle("cancion consultada").andContents("" + nombreCancion);
        Serenity.recordReportData().withTitle("artista consultado").andContents("" + nombreArtista);

    }


}
