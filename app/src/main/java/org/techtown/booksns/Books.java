package org.techtown.booksns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Books
{
    @Expose
    @SerializedName("items")
    public List<Items> items;

    @SerializedName("total")
    public String total;

    @SerializedName("lastBuildDate")
    public String lastBuildDate;

    @SerializedName("display")
    public String display;

    @SerializedName("start")
    public String start;



    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getLastBuildDate ()
    {
        return lastBuildDate;
    }

    public void setLastBuildDate (String lastBuildDate)
    {
        this.lastBuildDate = lastBuildDate;
    }

    public String getDisplay ()
    {
        return display;
    }

    public void setDisplay (String display)
    {
        this.display = display;
    }

    public String getStart ()
    {
        return start;
    }

    public void setStart (String start)
    {
        this.start = start;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", lastBuildDate = "+lastBuildDate+", display = "+display+", start = "+start+", items = "+items+"]";
    }
}