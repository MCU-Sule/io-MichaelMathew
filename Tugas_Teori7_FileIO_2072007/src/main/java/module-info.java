module com.example.tugas_teori7_fileio_2072007 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.tugas_teori7_fileio_2072007 to javafx.fxml;
    exports com.example.tugas_teori7_fileio_2072007;
    opens com.example.tugas_teori7_fileio_2072007.model to com.google.gson;
    exports com.example.tugas_teori7_fileio_2072007.model;
}