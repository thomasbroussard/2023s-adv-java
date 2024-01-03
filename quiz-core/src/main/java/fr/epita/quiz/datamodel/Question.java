package fr.epita.quiz.datamodel;

//reference project description here:
// https://thomas-broussard.fr/work/java/courses/project/fundamental.xhtml

import jakarta.persistence.*;

@Entity
@Table(name="QUESTIONS")
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QUEST_ID")
    private int id;


    @Column(name = "QUEST_TITLE")
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
