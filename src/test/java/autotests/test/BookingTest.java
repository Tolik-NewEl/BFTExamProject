package autotests.test;

import autotests.pojo.AuthRequest;
import autotests.pojo.Booking;
import autotests.pojo.BookingDates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
        return given()
                .header("Content-Type", "application/json")
                .body(authRequest)
                .post("/auth")
                .then()
                .extract()
                .jsonPath()
                .getString("token");
    }

    private int createTestBooking() {
        Booking bookingRequest = new Booking("Jim", "Brown", 111, true,
                new BookingDates("2018-01-01", "2019-01-01"), "Breakfast");
        return  given()
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .post("/booking")
                .then()
                .extract()
                .jsonPath()
                .getInt("bookingid");
    }

    @Test
    public void whenCreateBookingThenReturn200(){
        Booking bookingRequest = new Booking("Jim", "Brown", 111, true,
                new BookingDates("2018-01-01", "2019-01-01"), "Breakfast");

        Booking response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingRequest)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("booking", Booking.class);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(response.getFirstname()).isEqualTo("Jim");
        softly.assertThat(response.getLastname()).isEqualTo("Brown");
        softly.assertThat(response.getTotalprice()).isEqualTo(111);
        softly.assertThat(response.isDepositpaid()).isTrue();
        softly.assertThat(response.getBookingdates().getCheckin()).isEqualTo("2018-01-01");
        softly.assertThat(response.getBookingdates().getCheckout()).isEqualTo("2019-01-01");
        softly.assertThat(response.getAdditionalneeds()).isEqualTo("Breakfast");
        softly.assertAll();
    }

    @Test
    public void whenUpdateBookingThenReturn200AndUpdatedData(){
        int bookingId = createTestBooking();
        String token = getAuthToken();

        Booking updatedBooking = new Booking("Donald", "Trump", 666, false,
                new BookingDates("2018-05-01", "2019-05-01"), "Dinner");

        Booking response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .cookie("token", token)
                .body(updatedBooking)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getFirstname()).isEqualTo("Donald");
        softly.assertThat(response.getLastname()).isEqualTo("Trump");
        softly.assertThat(response.getTotalprice()).isEqualTo(666);
        softly.assertThat(response.isDepositpaid()).isFalse();
        softly.assertThat(response.getBookingdates().getCheckin()).isEqualTo("2018-05-01");
        softly.assertThat(response.getBookingdates().getCheckout()).isEqualTo("2019-05-01");
        softly.assertThat(response.getAdditionalneeds()).isEqualTo("Dinner");
        softly.assertAll();
    }

    @Test
    void whenGetBookingThenReturn200AndCorrectData() {
        int bookingId = createTestBooking();

        Booking response = given()
                .header("Accept", "application/json")
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getFirstname()).isEqualTo("Jim");
        softly.assertThat(response.getLastname()).isEqualTo("Brown");
        softly.assertThat(response.getTotalprice()).isEqualTo(111);
        softly.assertThat(response.isDepositpaid()).isTrue();
        softly.assertThat(response.getBookingdates().getCheckin()).isEqualTo("2018-01-01");
        softly.assertThat(response.getBookingdates().getCheckout()).isEqualTo("2019-01-01");
        softly.assertThat(response.getAdditionalneeds()).isEqualTo("Breakfast");
        softly.assertAll();
    }

    @Test
    void whenGetBookingIdsThenReturn200AndNonEmptyList() {
        int testBookingId = createTestBooking();

        List<Integer> bookingIds = given()
                .get("/booking")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("bookingid");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(bookingIds).isNotEmpty();
        softly.assertThat(bookingIds).contains(testBookingId);
        softly.assertAll();
    }

    @Test
    void whenDeleteBookingThenReturn201() {
        int bookingId = createTestBooking();
        String token = getAuthToken();

        given()
                .header("Accept", "application/json")
                .cookie("token", token)
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);

        given()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(404);
    }
}
