package org.techtown.booksns;


public class MemberInfo {
    private String name;
    private String phoneNum;
    private String birth;
    private String address;
    private String photoUrl;

    public MemberInfo(String name, String phoneNum, String birth, String address, String photoUrl){
        this.name=name;
        this.phoneNum=phoneNum;
        this.birth=birth;
        this.address=address;
        this.photoUrl=photoUrl;
    }
    public MemberInfo(String name, String phoneNum, String birth, String address){
        this.name=name;
        this.phoneNum=phoneNum;
        this.birth=birth;
        this.address=address;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPhoneNum(){
        return this.phoneNum;
    }
    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }
    public String getBirth(){
        return this.birth;
    }
    public void setBirth(String birth){
        this.birth = birth;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
}
