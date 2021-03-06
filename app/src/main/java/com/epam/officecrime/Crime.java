package com.epam.officecrime;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Dmytro_Torianik on 1/31/2019.
 */

public class Crime {

    private UUID id;
    private String title;
    private Date date;
    private boolean solved;
    private boolean requiresPolice;

    public Crime() {
        id = UUID.randomUUID();
        date = new Date();
    }

    public String getFormattedDate() {
        return new DateFormat().format("E, MMM d, yyyy", date).toString();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isRequiresPolice() {
        return requiresPolice;
    }

    public void setRequiresPolice(boolean requiresPolice) {
        this.requiresPolice = requiresPolice;
    }

}
