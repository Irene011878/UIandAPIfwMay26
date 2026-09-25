package requests_api;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import io.qameta.allure.Allure;


public class ApiClient {

    public Response get(String endpoint) {

        addRequestDetails(
                "GET",
                endpoint,
                null
        );

        Response response =
                given()
                        .when()
                        .get(endpoint);

        addResponseDetails(response);

        return response;
    }

    public Response get(
            String endpoint,
            String parameterName,
            String parameterValue) {

        addRequestDetails(
                "GET",
                endpoint,
                parameterName + "=" + parameterValue
        );

        Response response =
                given()
                        .queryParam(
                                parameterName,
                                parameterValue
                        )
                        .when()
                        .get(endpoint);

        addResponseDetails(response);

        return response;
    }

    public Response post(String endpoint) {

        addRequestDetails(
                "POST",
                endpoint,
                null
        );

        Response response =
                given()
                        .when()
                        .post(endpoint);

        addResponseDetails(response);

        return response;
    }

    public Response post(
            String endpoint,
            String parameterName,
            String parameterValue) {

        addRequestDetails(
                "POST",
                endpoint,
                parameterName + "=" + parameterValue
        );

        Response response =
                given()
                        .formParam(
                                parameterName,
                                parameterValue
                        )
                        .when()
                        .post(endpoint);

        addResponseDetails(response);

        return response;
    }

    public Response post(
            String endpoint,
            String parameterName1,
            String parameterValue1,
            String parameterName2,
            String parameterValue2) {

        addRequestDetails(
                "POST",
                endpoint,
                parameterName1 + "=" + parameterValue1
                        + ", "
                        + parameterName2 + "=" + parameterValue2
        );

        Response response =
                given()
                        .formParam(
                                parameterName1,
                                parameterValue1
                        )
                        .formParam(
                                parameterName2,
                                parameterValue2
                        )
                        .when()
                        .post(endpoint);

        addResponseDetails(response);

        return response;
    }

    public Response post(
            String endpoint,
            Map<String, String> parameters) {

        addRequestDetails(
                "POST",
                endpoint,
                parameters.toString()
        );

        Response response =
                given()
                        .formParams(parameters)
                        .when()
                        .post(endpoint);

        addResponseDetails(response);

        return response;
    }

    public Response put(String endpoint) {

        addRequestDetails(
                "PUT",
                endpoint,
                null
        );

        Response response =
                given()
                        .when()
                        .put(endpoint);

        addResponseDetails(response);

        return response;
    }

    public Response put(
            String endpoint,
            Map<String, String> parameters) {

        addRequestDetails(
                "PUT",
                endpoint,
                parameters.toString()
        );

        Response response =
                given()
                        .formParams(parameters)
                        .when()
                        .put(endpoint);

        addResponseDetails(response);

        return response;
    }

    public Response delete(String endpoint) {

        addRequestDetails(
                "DELETE",
                endpoint,
                null
        );

        Response response =
                given()
                        .when()
                        .delete(endpoint);

        addResponseDetails(response);

        return response;
    }

    public Response delete(
            String endpoint,
            Map<String, String> parameters) {

        addRequestDetails(
                "DELETE",
                endpoint,
                parameters.toString()
        );

        Response response =
                given()
                        .formParams(parameters)
                        .when()
                        .delete(endpoint);

        addResponseDetails(response);

        return response;
    }

    private void addRequestDetails(
            String method,
            String endpoint,
            String parameters) {

        Allure.addAttachment(
                "HTTP Method",
                "text/plain",
                method
        );

        Allure.addAttachment(
                "Request Endpoint",
                "text/plain",
                endpoint
        );

        if (parameters != null && !parameters.isEmpty()) {

            String safeParameters =
                    parameters.replaceAll(
                            "(?i)(password=)[^,]+",
                            "$1********"
                    );

            Allure.addAttachment(
                    "Request Parameters",
                    "text/plain",
                    safeParameters
            );
        }
    }

    private void addResponseDetails(Response response) {

        Allure.addAttachment(
                "Response Body",
                "application/json",
                response.asPrettyString(),
                ".json"
        );
    }

}
