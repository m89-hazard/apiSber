package utility;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static utility.LoadProperties.loadProperty;

public class RequestHelper {

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(loadProperty("baseUrl"))
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
    }

    public static Response sendGetRequest(String endpoint, Map<String, String> paramsTable, String key) {

        Response response = given()
                .spec(getRequestSpecification())
                .params(paramsTable)
                .params("key", key)
                .when()
                .get(endpoint)
                .prettyPeek();

        return response;
    }
}

