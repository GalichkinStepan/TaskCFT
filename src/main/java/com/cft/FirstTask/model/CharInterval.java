package com.cft.FirstTask.model;

import jakarta.persistence.*;

@Entity
@Table(name = "charintervals")
public class CharInterval {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "start1")
    private char start;

    @Column(name = "end1")
    private char end;

    public CharInterval() {

    }

    public CharInterval(char start, char end) {
        this.start = start;
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return start + ":" + end;
    }
}
