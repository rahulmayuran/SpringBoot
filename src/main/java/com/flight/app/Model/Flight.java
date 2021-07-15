package com.flight.app.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String from;
    private String To;
    private Date journeyDate;
    private Date returnDate;

    @Column(name = "PNR")
    private String PNR_number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public Date getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(Date journeyDate) {
        this.journeyDate = journeyDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getPNR_number() {
        return PNR_number;
    }

    public void setPNR_number(String PNR_number) {
        this.PNR_number = PNR_number;
    }
}
