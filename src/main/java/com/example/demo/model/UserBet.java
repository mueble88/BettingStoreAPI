package com.example.demo.model;

// modelo de apuesta del usuario:

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="user_bet")
@EntityListeners(AuditingEntityListener.class)
public class UserBet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long bet;

    public UserBet(){
    }

    public UserBet(long id, long bet) {
        this.id = id;
        this.bet = bet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBet() {
        return bet;
    }

    public void setBet(long bet) {
        this.bet = bet;
    }

    public String toString(){
        return "UserBet [id="+id+",bet="+bet+"]";
    }
}

