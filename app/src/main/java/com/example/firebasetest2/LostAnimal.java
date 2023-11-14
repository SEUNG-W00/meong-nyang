package com.example.firebasetest2;

public class LostAnimal {
    String title, content, lostlocation, lostdate, losttime, image;

    public LostAnimal(String title, String content, String lostlocation, String lostdate, String losttime, String image) {
        this.title = title;
        this.content = content;
        this.lostlocation = lostlocation;
        this.lostdate = lostdate;
        this.losttime = losttime;
        this.image = image;
    }

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