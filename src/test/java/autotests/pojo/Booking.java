package autotests.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}
