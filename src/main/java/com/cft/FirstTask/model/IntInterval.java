package com.cft.FirstTask.model;

import jakarta.persistence.*;

@Entity
@Table(name = "intintervals")
public class IntInterval{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "start1")
  private int start;

  @Column(name = "end1")
  private int end;

  public IntInterval() {

  }

  public IntInterval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public long getId() {
    return id;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  @Override
  public String toString() {
    return start + ":" + end;
  }

}
