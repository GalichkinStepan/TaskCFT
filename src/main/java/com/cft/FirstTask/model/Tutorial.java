package com.cft.FirstTask.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tutorials")
public class Tutorial {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  private int title;

  @Column(name = "description")
  private int description;

  public Tutorial() {

  }

  public Tutorial(int title, int description) {
    this.title = title;
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public int getTitle() {
    return title;
  }

  public void setTitle(int title) {
    this.title = title;
  }

  public int getDescription() {
    return description;
  }

  public void setDescription(int description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + "]";
  }

}
