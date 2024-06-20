package com.example.demo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="matches")
@EntityListeners(AuditingEntityListener.class)
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="date_matches")
    @CreatedDate
    private Date dateMatches;

    @Column(name="goals_local")
    private int goalsLocal;

    @Column(name="goals_visitor")
    private int goalsVisitor;

    @Column(name="played")
    private boolean played = false;

    @ManyToOne
    @JoinColumn(name ="local_team_id", nullable = false)
    private Team localTeam;

    @ManyToOne
    @JoinColumn(name ="visitor_team_id", nullable = false)
    private Team visitorTeam;

    public Matches(){}

    public Matches(long id, Date dateMatches, int goalsLocal, int goalsVisitor, boolean played, Team localTeam, Team visitorTeam) {
        this.id = id;
        this.dateMatches = dateMatches;
        this.goalsLocal = goalsLocal;
        this.goalsVisitor = goalsVisitor;
        this.played = played;
        this.localTeam = localTeam;
        this.visitorTeam = visitorTeam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateMatches() {
        return dateMatches;
    }

    public void setDateMatches(Date dateMatches) {
        this.dateMatches = dateMatches;
    }

    public int getGoalsLocal() {
        return goalsLocal;
    }

    public void setGoalsLocal(int goalsLocal) {
        this.goalsLocal = goalsLocal;
    }

    public int getGoalsVisitor() {
        return goalsVisitor;
    }

    public void setGoalsVisitor(int goalsVisitor) {
        this.goalsVisitor = goalsVisitor;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public Team getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(Team localTeam) {
        this.localTeam = localTeam;
    }

    public Team getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(Team visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    public String toString(){
        return "Matches[id="+id+",dateMatches="+dateMatches+",goalsLocal="+goalsLocal+",goalsVisitor="+goalsVisitor+",played="+played+",localTeam="+localTeam+",visitorTeam="+visitorTeam+"]";
    }
}
