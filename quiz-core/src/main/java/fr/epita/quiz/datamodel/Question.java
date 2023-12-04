package fr.epita.quiz.datamodel;

//reference project description here:
// https://thomas-broussard.fr/work/java/courses/project/fundamental.xhtml
public class Question {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
