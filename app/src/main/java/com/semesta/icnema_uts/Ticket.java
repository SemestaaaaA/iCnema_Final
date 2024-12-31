package com.semesta.icnema_uts;

public class Ticket {
    private String title;
    private String overview;
    private String releaseDate;
    private String time; // Add time field
    private String seats;
    private int price;
    private String imageUrl;

    public Ticket(String title, String overview, String releaseDate, String time, String seats, int price, String imageUrl) {
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.time = time; // Initialize time field
        this.seats = seats;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTime() {
        return time; // Add getter for time
    }

    public String getSeats() {
        return seats;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}