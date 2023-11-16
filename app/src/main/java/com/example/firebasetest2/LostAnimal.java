package com.example.firebasetest2;

public class LostAnimal {

    private String title;
    private String content;
    private String lostlocation;
    private String lostdate;
    private String losttime;
    private String image;

    public LostAnimal(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLostlocation() {
        return lostlocation;
    }

    public void setLostlocation(String lostlocation) {
        this.lostlocation = lostlocation;
    }

    public String getLostdate() {
        return lostdate;
    }

    public void setLostdate(String lostdate) {
        this.lostdate = lostdate;
    }

    public String getLosttime() {
        return losttime;
    }

    public void setLosttime(String losttime) {
        this.losttime = losttime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}