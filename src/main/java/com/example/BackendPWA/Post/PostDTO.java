package com.example.BackendPWA.Post;

import java.sql.Blob;

public class PostDTO {
    private long id;
    private String title;
    private String firstname;
    private String lastname;
    
    private String encodedFile;
    String selectedFile;

    private byte[] decodedFile;

    // Constructors
    public PostDTO() {
        // Default constructor
    }

    public PostDTO(long id, String title, String encodedFile, String selectedFile, byte[] decodedFile, String firstname, String lastname) {
        this.id = id;
        this.title = title;
        this.encodedFile = encodedFile;
        this.selectedFile = selectedFile;
        this.decodedFile = decodedFile;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public byte[] getDecodedFile() {
        return decodedFile;
    }

    public void setDecodedFile(byte[] decodedFile) {
        this.decodedFile = decodedFile;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEncodedFile() {
        return encodedFile;
    }

    public void setEncodedFile(String encodedFile) {
        this.encodedFile = encodedFile;
    }

    public String getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(String selectedFile) {
        this.selectedFile = selectedFile;
    }

    public void setFirstname(String firstname) {
    }

    public void setLastname(String lastname) {
    }
}
