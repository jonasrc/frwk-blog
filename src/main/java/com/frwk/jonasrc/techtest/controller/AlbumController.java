package com.frwk.jonasrc.techtest.controller;

import com.frwk.jonasrc.techtest.dto.AlbumDTO;
import com.frwk.jonasrc.techtest.dto.PhotoDTO;
import com.frwk.jonasrc.techtest.model.*;
import com.frwk.jonasrc.techtest.service.AlbumService;
import com.frwk.jonasrc.techtest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Álbuns, Fotos", value = "albumController", description="Cadastro, listagem e deleção de álbuns de fotos do blog")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/albums")
    @ApiOperation(value = "Buscar lista completa de álbuns de fotos")
    public ResponseEntity<List<AlbumDTO>> findAll() {
        List<Album> albumList = albumService.findAll();
        List<AlbumDTO> albumDTOList = albumList.stream().map(this::convertAlbumToDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(albumDTOList);
    }

    @PostMapping("/albums")
    @ApiOperation(value = "Cadastro de novos álbuns de fotos")
    public ResponseEntity<AlbumDTO> create(
            @ApiParam(value = "Álbum a ser criado", required = true)
            @RequestBody AlbumDTO albumDTO) {
        Album newAlbum = albumService.create(convertAlbumToEntity(albumDTO));
        AlbumDTO newAlbumDTO = convertAlbumToDTO(newAlbum);
        return ResponseEntity.created(URI.create(newAlbumDTO.getId().toString())).body(newAlbumDTO);
    }

    private AlbumDTO convertAlbumToDTO(Album album) {
        return modelMapper.map(album, AlbumDTO.class);
    }

    private PhotoDTO convertPhotoToDTO(Photo photo) {
        return modelMapper.map(photo, PhotoDTO.class);
    }

    private Album convertAlbumToEntity(AlbumDTO albumDTO) {
        Album album = modelMapper.map(albumDTO, Album.class);
        User user = userService.findById(albumDTO.getUser().getId());
        album.setAlbumUser(user);
        return album;
    }

    private Photo convertPhotoToEntity(PhotoDTO photoDTO) {
        Photo photo = modelMapper.map(photoDTO, Photo.class);
        Album album = albumService.findById(photoDTO.getAlbum().getId());
        photo.setAlbum(album);
        return photo;
    }
}
