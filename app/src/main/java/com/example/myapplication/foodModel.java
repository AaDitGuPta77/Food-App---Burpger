package com.example.myapplication;

public class foodModel {
    int pic,price;
    String text;

    String btnId;

    public foodModel(int pic, String text, String btnId,int price) {
        this.pic = pic;
        this.text = text;
        this.btnId = btnId;
        this.price = price;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBtnId() {
        return btnId;
    }

    public void setBtnId(String text) {
        this.text = btnId;
    }

}
