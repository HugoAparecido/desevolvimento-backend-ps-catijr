package com.catijr.backend.Services;

import com.catijr.backend.DTOs.Album.GetAlbumDTO;
import com.catijr.backend.Entities.Music;
import com.catijr.backend.Mappers.AlbumMapper;
import com.catijr.backend.Repositories.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    public List<Music> getMusicsByAlbumId(UUID albumId) {
        var album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return album.getMusics();
    }

    public GetAlbumDTO getAlbumById(UUID albumId) {
        var album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found"));

        return albumMapper.toDTO(album);
    }
}
