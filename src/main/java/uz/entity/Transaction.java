package uz.entity;

import java.sql.Date;

public class Transaction {
    private int id;
    private int cash;
    private int card;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Transaction(int id, int cash, int card, Date date) {
        this.id = id;
        this.cash = cash;
        this.card = card;
        this.date = date;
    }

    public Transaction() {
    }
}
