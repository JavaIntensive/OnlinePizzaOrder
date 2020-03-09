package com.example.demo;

import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    private String dough;

    //    @Size(min = 3)
//    private String dough;
//
//    private String sauce;
//
//    private String cheese;

//    @Nullable
//    private String spinach;
//
//    @Nullable
//    private String bacon;
//
//    @Nullable
//    private String mushroom;
//
//    @Nullable
//    private String tomato;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
//        user = new User();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public String getDough() {
//        return dough;
//    }
//
//    public void setDough(String dough) {
//        this.dough = dough;
//    }
//    public String getSauce() {
//        return sauce;
//    }
//
//    public void setSauce(String sauce) {
//        this.sauce = sauce;
//    }
//
//    public String getCheese() {
//        return cheese;
//    }
//
//    public void setCheese(String cheese) {
//        this.cheese = cheese;
//    }


//    public String getSpinach() {
//        return spinach;
//    }
//
//    public void setSpinach(String spinach) {
//        this.spinach = spinach;
//    }
//
//    public String getBacon() {
//        return bacon;
//    }
//
//    public void setBacon(String bacon) {
//        this.bacon = bacon;
//    }
//
//    public String getMushroom() {
//        return mushroom;
//    }
//
//    public void setMushroom(String mushroom) {
//        this.mushroom = mushroom;
//    }
//
//    public String getTomato() {
//        return tomato;
//    }
//
//    public void setTomato(String tomato) {
//        this.tomato = tomato;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
