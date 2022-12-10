package org.techtown.booksns;


import java.util.ArrayList;
import java.util.Date;

public class WriteInfo {
    private String title;
    private String textContent;
    private ArrayList<String> bookInfo;
    private ArrayList<String> contents;
    private String publisher;
    private Date createdAt;


    public WriteInfo(String title, String textContent, ArrayList<String> bookInfo,ArrayList<String> contents, String publisher, Date createdAt){
        this.title=title;
        this.textContent=textContent;
        this.bookInfo=bookInfo;
        this.contents=contents;
        this.publisher=publisher;
        this.createdAt = createdAt;
    }

    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTextContent(){
        return this.textContent;
    }
    public void setTextContent(String textContent){
        this.textContent = textContent;
    }
    public ArrayList<String> getBookInfo(){
        return this.bookInfo;
    }
    public void setBookInfo(ArrayList<String> bookInfo){
        this.bookInfo = bookInfo;
    }
    public ArrayList<String> getContents(){
        return this.contents;
    }
    public void setContents(ArrayList<String> contents) { this.contents = contents; }
    public String getPublisher(){
        return this.publisher;
    }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public Date getCreatedAt(){
        return this.createdAt;
    }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }


}
