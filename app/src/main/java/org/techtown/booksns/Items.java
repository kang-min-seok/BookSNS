package org.techtown.booksns;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items
{
    @Expose
    @SerializedName("title") private String title;
    @Expose
    @SerializedName("image") private String image;
    @Expose
    @SerializedName("author") private String author;
    @Expose
    @SerializedName("isbn") private String isbn;
    @Expose
    @SerializedName("link") private String link;
    @Expose
    @SerializedName("discount") private String discount;
    @Expose
    @SerializedName("publisher") private String publisher;
    @Expose
    @SerializedName("description") private String description;
    @Expose
    @SerializedName("pubdate") private String pubdate;

    public Items(String title, String image, String author, String isbn, String link
                , String discount, String publisher, String description, String pubdate){
        this.title=title;
        this.image = image;
        this.author= author;
        this.isbn=isbn;
        this.link=link;
        this.discount=discount;
        this.publisher=publisher;
        this.description=description;
        this.pubdate=pubdate;
    }



    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getIsbn ()
    {
        return isbn;
    }

    public void setIsbn (String isbn)
    {
        this.isbn = isbn;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getDiscount ()
    {
        return discount;
    }

    public void setDiscount (String discount)
    {
        this.discount = discount;
    }

    public String getPublisher ()
    {
        return publisher;
    }

    public void setPublisher (String publisher)
    {
        this.publisher = publisher;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getPubdate ()
    {
        return pubdate;
    }

    public void setPubdate (String pubdate)
    {
        this.pubdate = pubdate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [image = "+image+", author = "+author+", isbn = "+isbn+", link = "+link+", discount = "+discount+", publisher = "+publisher+", description = "+description+", title = "+title+", pubdate = "+pubdate+"]";
    }
}