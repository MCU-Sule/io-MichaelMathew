package com.example.tugas_teori7_fileio_2072007.model;

public class FileIO {
    private String Username;
    private String Comment;

    public FileIO(String username, String comment) {
        Username = username;
        Comment = comment;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    @Override
    public String toString() {
        return Username + " - " + Comment;
    }
}
