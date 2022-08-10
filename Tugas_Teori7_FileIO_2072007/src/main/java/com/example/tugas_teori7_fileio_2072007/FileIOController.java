package com.example.tugas_teori7_fileio_2072007;

import com.example.tugas_teori7_fileio_2072007.model.FileIO;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIOController {
    public ListView<FileIO> ListComment;
    public TextField txtUsername;
    public TextArea txtComment;

    private ObservableList<FileIO> oList;
    private ObservableList<String> sList;

    public void initialize() {
        oList = FXCollections.observableArrayList();
        ShowData();
    }

    public void ShowData() {
        ListComment.setItems(oList);
    }
    public void AddComment(ActionEvent actionEvent) {
        oList.add(new FileIO(txtUsername.getText(),txtComment.getText()));
    }


    public void SaveComment1() {
        BufferedWriter writer;
        String filename = "data/comment.txt";
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            Gson g = new Gson();
            String json = g.toJson(ListComment.getItems());
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void LoadComment1(ActionEvent actionEvent) {
        BufferedReader reader;
        String filename = "data/comment.txt";
        try {
            reader = new BufferedReader(new FileReader(filename));
            String json = reader.readLine();
            Gson g = new Gson();
            FileIO[] file = g.fromJson(json, FileIO[].class);
            ListComment.getItems().addAll(file);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SaveComment2(ActionEvent actionEvent) throws IOException {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Documents", "*.txt");
        chooser.getExtensionFilters().add(extensionFilter);
        chooser.setSelectedExtensionFilter(extensionFilter);
        File f = chooser.showSaveDialog(txtComment.getScene().getWindow());

        if(f != null) {
            Path p = Paths.get(f.toURI());
            Gson g = new Gson();
            String json = g.toJson(ListComment.getItems());
            Files.write(p,json.getBytes());
        }
    }

    public void LoadComment2(ActionEvent actionEvent) throws IOException {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Documents", "*.txt");
        chooser.getExtensionFilters().add(extensionFilter);
        chooser.setSelectedExtensionFilter(extensionFilter);
        File f = chooser.showOpenDialog(txtComment.getScene().getWindow());

        if(f != null){
            Path p = Paths.get(f.toURI());
            Gson g = new Gson();
            String json = Files.readString(p);
            FileIO[] file = g.fromJson(json, FileIO[].class);
            ListComment.getItems().addAll(file);
        }

    }

    public void DeleteFile(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Delete");
        File f = chooser.showOpenDialog(txtComment.getScene().getWindow());
        if (f != null) {
            Path p = Paths.get(f.toURI());
            try {
                boolean result = Files.deleteIfExists(p);
                if (result) {
                    System.out.println("File is deleted!");
                } else {
                    System.out.println("Sorry, unable to delete the file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}