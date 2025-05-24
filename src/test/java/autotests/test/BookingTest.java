package autotests.test;

import autotests.pojo.AuthRequest;
import autotests.pojo.Booking;
import autotests.pojo.BookingDates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookingTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    private String getAuthToken() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(authRequest)
                .post("/auth");
        return response.jsonPath().getString("token");
    }

    private int createTestBooking() {
        Booking bookingRequest = new Booking();
        bookingRequest.setFirstname("Jim");
        bookingRequest.setLastname("Brown");
        bookingRequest.setTotalprice(111);
        bookingRequest.setDepositpaid(true);
        BookingDates dates = new BookingDates();
        dates.setCheckin("2018-01-01");
        dates.setCheckout("2019-01-01");
        bookingRequest.setBookingdates(dates);
        bookingRequest.setAdditionalneeds("Breakfast");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .post("/booking");

        return response.jsonPath().getInt("bookingid");
    }

    @Test
    public void whenCreateBookingThenReturn200(){
        Booking bookingRequest = new Booking();
        bookingRequest.setFirstname("Jim");
        bookingRequest.setLastname("Brown");
        bookingRequest.setTotalprice(111);
        bookingRequest.setDepositpaid(true);
        BookingDates dates = new BookingDates();
        dates.setCheckin("2018-01-01");
        dates.setCheckout("2019-01-01");
        bookingRequest.setBookingdates(dates);
        bookingRequest.setAdditionalneeds("Breakfast");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingRequest)
                .when()
                .post("/booking");

        response.then().statusCode(200);

        int bookingId = response.jsonPath().getInt("bookingid");
        Booking bookingResponse = response.jsonPath().getObject("booking", Booking.class);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(bookingId).isPositive();
        softly.assertThat(bookingResponse.getFirstname()).isEqualTo("Jim");
        softly.assertThat(bookingResponse.getLastname()).isEqualTo("Brown");
        softly.assertThat(bookingResponse.getTotalprice()).isEqualTo(111);
        softly.assertThat(bookingResponse.isDepositpaid()).isTrue();
        softly.assertThat(bookingResponse.getBookingdates().getCheckin()).isEqualTo("2018-01-01");
        softly.assertThat(bookingResponse.getBookingdates().getCheckout()).isEqualTo("2019-01-01");
        softly.assertThat(bookingResponse.getAdditionalneeds()).isEqualTo("Breakfast");
        softly.assertAll();
    }

    @Test
    public void whenUpdateBookingThenReturn200AndUpdatedData(){
        int bookingId = createTestBooking();
        String token = getAuthToken();

        Booking updatedBooking = new Booking();
        updatedBooking.setFirstname("Donald");
        updatedBooking.setLastname("Trump");
        updatedBooking.setTotalprice(666);
        updatedBooking.setDepositpaid(false);
        BookingDates newDates = new BookingDates();
        newDates.setCheckin("2018-05-01");
        newDates.setCheckout("2019-05-01");
        updatedBooking.setBookingdates(newDates);

        updatedBooking.setAdditionalneeds("Dinner");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .cookie("token", token)
                .body(updatedBooking)
                .when()
                .put("/booking/" + bookingId);

        response.then().statusCode(200);

        Booking responseBooking = response.as(Booking.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(responseBooking.getFirstname()).isEqualTo("Donald");
        softly.assertThat(responseBooking.getLastname()).isEqualTo("Trump");
        softly.assertThat(responseBooking.getTotalprice()).isEqualTo(666);
        softly.assertThat(responseBooking.isDepositpaid()).isFalse();
        softly.assertThat(responseBooking.getBookingdates().getCheckin()).isEqualTo("2018-05-01");
        softly.assertThat(responseBooking.getBookingdates().getCheckout()).isEqualTo("2019-05-01");
        softly.assertThat(responseBooking.getAdditionalneeds()).isEqualTo("Dinner");
        softly.assertAll();
    }

    @Test
    void whenGetBookingThenReturn200AndCorrectData() {
        int bookingId = createTestBooking();

        Response response = given()
                .header("Accept", "application/json")
                .get("/booking/" + bookingId);

        Booking booking = response.as(Booking.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.statusCode()).isEqualTo(200);
        softly.assertThat(booking.getFirstname()).isEqualTo("Jim");
        softly.assertThat(booking.getLastname()).isEqualTo("Brown");
        softly.assertThat(booking.getTotalprice()).isEqualTo(111);
        softly.assertThat(booking.isDepositpaid()).isTrue();
        softly.assertThat(booking.getBookingdates().getCheckin()).isEqualTo("2018-01-01");
        softly.assertThat(booking.getBookingdates().getCheckout()).isEqualTo("2019-01-01");
        softly.assertThat(booking.getAdditionalneeds()).isEqualTo("Breakfast");
        softly.assertAll();
    }

    @Test
    void whenGetBookingIdsThenReturn200AndNonEmptyList() {
        int testBookingId = createTestBooking();

        Response response = given()
                .get("/booking");

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.statusCode()).isEqualTo(200);
        softly.assertThat(bookingIds).isNotEmpty();
        softly.assertThat(bookingIds).contains(testBookingId);
        softly.assertAll();
    }

    @Test
    void whenDeleteBookingThenReturn201() {
        int bookingId = createTestBooking();
        String token = getAuthToken();

        Response deleteResponse = given()
                .header("Accept", "application/json")
                .cookie("token", token)
                .delete("/booking/" + bookingId);

        deleteResponse.then().statusCode(201);
        given()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(404);
    }
}
