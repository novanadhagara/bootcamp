package apiengine;

import helper.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.config.Config;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import io.cucumber.java.en.*;

public class Endpoints {

    public Endpoints() {
        // Set the base URI for the API
        String baseUrl = ConfigManager.getBaseUrl();
        RestAssured.baseURI = baseUrl;
    }
 Response response;

    @Given("I set GET endpoint to {string}")
    public void set_get_endpoint(String endpoint) {
        response = given().when().get(endpoint);
    }

    private RestAssured given() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'given'");
    }

    @When("I send the request")
    public void send_the_request() {
        response.then().log().all();
    }

    @Then("the response code should be {int}")
    public void response_code_should_be(int code) {
        response.then().statusCode(code);
    }

    @And("the response should contain {string}")
    public void response_should_contain(String key) {
        response.then().body(containsString(key));
    }
    public Response registerEmployee(String bodyRequest){
        Response responseCreateEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/webhook/employee/add");
        return responseCreateEmployee;
    }

    public Response loginEmployee(String bodyRequest) {
        Response responseLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/webhook/employee/login");
        return responseLogin;
    }

    public Response getEmployee(String token) {
        Response responseGetEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/employee/get");
        return responseGetEmployee;
    }

    public Response updateEmployee(String bodyRequest, String token) {
        Response responseUpdateEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyRequest)
                .log().all()
                .when()
                .put("/webhook/employee/update");
        return responseUpdateEmployee;
    }

    public Response deleteEmployee(String token) {
        Response responseDeleteEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .delete("/webhook/employee/delete/");
        return responseDeleteEmployee;
    }

    public Response getAllEmployee(){
        Response responseGetAllEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .get("/webhook/employee/get_all");
        return responseGetAllEmployee;
    }

    public Response getObjectsById(String token, String id) {
        Response responseGetObjectsById = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/employee/get/" + id);
        return responseGetObjectsById;
    }

    public Response addObject(String token, String bodyRequest){
        Response responseAddObject = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyRequest)
                .log().all()
                .when()
                .post("/webhook/employee/add_object");
        return responseAddObject;
    }
}
