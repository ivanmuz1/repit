package com.example.repit.entities;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "advertisement")
@Embeddable
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_advertisement")
    int id_Advertisement;
    @Column(name = "price")
    int price;
    @Column(name = "subject")
    String subject;

    public Advertisement(int price, String subject) {
        this.price = price;
        this.subject = subject;
    }

    public Advertisement(int id_Advertisement) {
        this.id_Advertisement = id_Advertisement;
    }

    public Advertisement() {
    }

    public int getId_Advertisement() {
        return id_Advertisement;
    }

    public int getPrice() {
        return price;
    }

    public String getSubject() {
        return subject;
    }

    public void setId_Advertisement(int id_Advertisement) {
        this.id_Advertisement = id_Advertisement;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tutor_id_tutor")
    private Tutor tutor;

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @OneToOne(mappedBy = "advertisement", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Lesson lesson;


    @Override
    public String toString() {
        return "Advertisement{" +
                "id_Advertisement=" + id_Advertisement +
                ", price=" + price +
                ", subject='" + subject + '\'' +
                '}';
    }
}
