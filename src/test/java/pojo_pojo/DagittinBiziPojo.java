package pojo_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DagittinBiziPojo {

    private Integer bookingid;
    private OuterPojo booking;

    public DagittinBiziPojo(Integer bookingid, OuterPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public DagittinBiziPojo() {
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public OuterPojo getBooking() {
        return booking;
    }

    public void setBooking(OuterPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "DagittinBiziPojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
