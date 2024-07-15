package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import utility.CurrentWhetherSource;
import utility.VariableContainer;
import utility.models.ApiError;
import utility.models.CurrentWhether;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static utility.LoadProperties.loadProperty;
import static utility.RequestHelper.sendGetRequest;

public class ApiSteps {
    public static final VariableContainer variableContainer = new VariableContainer();
    public static Response response;

    @И("проверить в теле ответа {string} код ошибки {int} и сообщение ошибки {string}")
    public void checkErrorBodyRequest (String varName, Integer code, String message) {
        response = variableContainer.getVar(varName);
        ApiError errorBody = response
                .then().extract().as(ApiError.class);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(errorBody.getError().getCode()).as("code").isEqualTo(errorBody.getError().getCode());
        softly.assertThat(errorBody.getError().getMessage()).as("message").isEqualTo(errorBody.getError().getMessage());
        softly.assertAll();

    }

    @И("выполнить GET запрос на эндпоинт {string} с параметрами из таблицы и ответ записать в переменную {string}")
    public void sendGetRequestWithParameters (String endpoint, String varName, DataTable table) {
        Map<String, String> parameters = table.asMap();
        response = sendGetRequest(loadProperty(endpoint), parameters, loadProperty("key"));
        variableContainer.setVar(varName, response);
    }

    @И("выполнить GET запрос с невалидным API Key на эндпоинт {string} с параметрами из таблицы и ответ записать в переменную {string}")
    public void sendGetRequestWithoutApiKey (String endpoint, String varName, DataTable table) {
        Map<String, String> parameters = table.asMap();
        response = sendGetRequest(loadProperty(endpoint), parameters, "");
        variableContainer.setVar(varName, response);
    }

    @Тогда("статус код ответа равен {int}")
    public void checkStatusCode(int expectedCode) {
        assertThat(response.statusCode()).withFailMessage("В ответе некорректный статус-код [" + response.statusCode() + "]").isEqualTo(expectedCode);
    }

    @И("проверить ответ из {string} на соответсвие эталонному значению текущей погоды")
    public void checkCurrentWhether(String varName) {
        response = variableContainer.getVar(varName);
        CurrentWhether ActualWhether = response
                .then().extract().as(CurrentWhether.class);
        CurrentWhether expectWhether = CurrentWhetherSource.whether;

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(ActualWhether.getLocation().getName()).as("name").isEqualTo(expectWhether.getLocation().getName());
        softly.assertThat(ActualWhether.getLocation().getRegion()).as("region").isEqualTo(expectWhether.getLocation().getRegion());
        softly.assertThat(ActualWhether.getLocation().getCountry()).as("country").isEqualTo(expectWhether.getLocation().getCountry());
        softly.assertThat(ActualWhether.getLocation().getLat()).as("lat").isEqualTo(expectWhether.getLocation().getLat());
        softly.assertThat(ActualWhether.getLocation().getLon()).as("lon").isEqualTo(expectWhether.getLocation().getLon());
        softly.assertThat(ActualWhether.getLocation().getTz_id()).as("tz_id").isEqualTo(expectWhether.getLocation().getTz_id());
        softly.assertThat(ActualWhether.getLocation().getLocaltime_epoch()).as("localtime_epoch").isEqualTo(expectWhether.getLocation().getLocaltime_epoch());
        softly.assertThat(ActualWhether.getLocation().getLocaltime()).as("localtime").isEqualTo(expectWhether.getLocation().getLocaltime());
        softly.assertThat(ActualWhether.getCurrent().getLast_updated_epoch()).as("last_updated_epoch").isEqualTo(expectWhether.getCurrent().getLast_updated_epoch());
        softly.assertThat(ActualWhether.getCurrent().getLast_updated_epoch()).as("last_updated_epoch").isEqualTo(expectWhether.getCurrent().getLast_updated_epoch());
        softly.assertThat(ActualWhether.getCurrent().getLast_updated()).as("last_updated").isEqualTo(expectWhether.getCurrent().getLast_updated());
        softly.assertThat(ActualWhether.getCurrent().getTemp_c()).as("temp_c").isEqualTo(expectWhether.getCurrent().getTemp_c());
        softly.assertThat(ActualWhether.getCurrent().getTemp_f()).as("temp_f").isEqualTo(expectWhether.getCurrent().getTemp_f());
        softly.assertThat(ActualWhether.getCurrent().getIs_day()).as("is_day").isEqualTo(expectWhether.getCurrent().getIs_day());
        softly.assertThat(ActualWhether.getCurrent().getCondition().getText()).as("text").isEqualTo(expectWhether.getCurrent().getCondition().getText());
        softly.assertThat(ActualWhether.getCurrent().getCondition().getIcon()).as("icon").isEqualTo(expectWhether.getCurrent().getCondition().getIcon());
        softly.assertThat(ActualWhether.getCurrent().getCondition().getCode()).as("code").isEqualTo(expectWhether.getCurrent().getCondition().getCode());
        softly.assertThat(ActualWhether.getCurrent().getWind_mph()).as("wind_mph").isEqualTo(expectWhether.getCurrent().getWind_mph());
        softly.assertThat(ActualWhether.getCurrent().getWind_kph()).as("wind_kph").isEqualTo(expectWhether.getCurrent().getWind_kph());
        softly.assertThat(ActualWhether.getCurrent().getWind_degree()).as("wind_degree").isEqualTo(expectWhether.getCurrent().getWind_degree());
        softly.assertThat(ActualWhether.getCurrent().getWind_dir()).as("wind_dir").isEqualTo(expectWhether.getCurrent().getWind_dir());
        softly.assertThat(ActualWhether.getCurrent().getPressure_mb()).as("pressure_mb").isEqualTo(expectWhether.getCurrent().getPressure_mb());
        softly.assertThat(ActualWhether.getCurrent().getPressure_in()).as("pressure_in").isEqualTo(expectWhether.getCurrent().getPressure_in());
        softly.assertThat(ActualWhether.getCurrent().getPrecip_mm()).as("precip_mm").isEqualTo(expectWhether.getCurrent().getPrecip_mm());
        softly.assertThat(ActualWhether.getCurrent().getPrecip_in()).as("precip_in").isEqualTo(expectWhether.getCurrent().getPrecip_in());
        softly.assertThat(ActualWhether.getCurrent().getHumidity()).as("humidity").isEqualTo(expectWhether.getCurrent().getHumidity());
        softly.assertThat(ActualWhether.getCurrent().getCloud()).as("cloud").isEqualTo(expectWhether.getCurrent().getCloud());
        softly.assertThat(ActualWhether.getCurrent().getFeelslike_c()).as("feelslike_c").isEqualTo(expectWhether.getCurrent().getFeelslike_c());
        softly.assertThat(ActualWhether.getCurrent().getFeelslike_f()).as("feelslike_f").isEqualTo(expectWhether.getCurrent().getFeelslike_f());
        softly.assertThat(ActualWhether.getCurrent().getWindchill_c()).as("windchill_c").isEqualTo(expectWhether.getCurrent().getWindchill_c());
        softly.assertThat(ActualWhether.getCurrent().getWindchill_f()).as("windchill_f").isEqualTo(expectWhether.getCurrent().getWindchill_f());
        softly.assertThat(ActualWhether.getCurrent().getHeatindex_c()).as("heatindex_c").isEqualTo(expectWhether.getCurrent().getHeatindex_c());
        softly.assertThat(ActualWhether.getCurrent().getHeatindex_f()).as("heatindex_f").isEqualTo(expectWhether.getCurrent().getHeatindex_f());
        softly.assertThat(ActualWhether.getCurrent().getDewpoint_c()).as("dewpoint_c").isEqualTo(expectWhether.getCurrent().getDewpoint_c());
        softly.assertThat(ActualWhether.getCurrent().getDewpoint_f()).as("dewpoint_f").isEqualTo(expectWhether.getCurrent().getDewpoint_f());
        softly.assertThat(ActualWhether.getCurrent().getVis_km()).as("vis_km").isEqualTo(expectWhether.getCurrent().getVis_km());
        softly.assertThat(ActualWhether.getCurrent().getVis_miles()).as("vis_miles").isEqualTo(expectWhether.getCurrent().getVis_miles());
        softly.assertThat(ActualWhether.getCurrent().getUv()).as("uv").isEqualTo(expectWhether.getCurrent().getUv());
        softly.assertThat(ActualWhether.getCurrent().getGust_mph()).as("gust_mph").isEqualTo(expectWhether.getCurrent().getGust_mph());
        softly.assertThat(ActualWhether.getCurrent().getGust_kph()).as("gust_kph").isEqualTo(expectWhether.getCurrent().getGust_kph());
        softly.assertAll();
    }
}
