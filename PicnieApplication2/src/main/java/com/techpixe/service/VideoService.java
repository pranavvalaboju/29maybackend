package com.techpixe.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.entity.Video;

public interface VideoService {
	Video saveVideo(MultipartFile file);

	Video getByVideo(Long videoId);

	List<Video> all();

	void deleteVideoById(Long videoId);
}
