package com.frwk.jonasrc.techtest.dto;

public class PhotoDTO {
    private Long id;

    private String path;

    private String creationDate;

    private AlbumDTO album;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO albumDTO) {
        this.album = albumDTO;
    }
}
