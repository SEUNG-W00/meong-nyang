package com.example.meong_nyang;

public class LostAnimal {

    private String title;
    private String content;
    private String lostlocation;
    private String lostdate;
    private String losttime;
    private String image;
    private String name;
    private String species;
    private String callnum;

    public LostAnimal() {}

    public LostAnimal(String title, String content, String lostlocation, String lostdate, String losttime, String image, String name, String species, String callnum) {
        this.title = title;
        this.content = content;
        this.lostlocation = lostlocation;
        this.lostdate = lostdate;
        this.losttime = losttime;
        this.image = image;
        this.name = name;
        this.species = species;
        this.callnum = callnum;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getCallnum() {
        return callnum;
    }

    public void setCallnum(String callnum) {
        this.callnum = callnum;
    }
}