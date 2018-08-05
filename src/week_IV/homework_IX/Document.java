package week_IV.homework_IX;

import java.util.Date;

public abstract class Document {

    private String title;
    private String author;
    private Date date;
    private PublishingLocation publishingLocation;

    public Document(String title, String author, Date date, String city,
                    String state, String postCode) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.publishingLocation = new PublishingLocation(city, state, postCode);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return this.publishingLocation.getCity();
    }

    public String getState() {
        return this.publishingLocation.getState();
    }

    public String getPostCode() {
        return this.publishingLocation.getPostCode();
    }

    public boolean sameAuthor(Document article){
        return this.author.equals(article.author);
    }

    public int compareDates(Document article){
        return this.date.compareTo(article.date);
    }

    public int compareWithGeneralDate(Date date){
        return this.date.compareTo(date);
    }
}
