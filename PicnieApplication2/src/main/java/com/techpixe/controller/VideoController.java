package com.techpixe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.entity.Video;
import com.techpixe.service.VideoService;

@RestController
@RequestMapping("/video")
public class VideoController {
	@Autowired
	VideoService videoService;

	@PostMapping("/save")
	public ResponseEntity<Video> saveVideo(@RequestParam MultipartFile file) {
		Video savedVideo = videoService.saveVideo(file);
		return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
	}

	@GetMapping("/getByVideo/{videoId}")
	public ResponseEntity<?> getByVideo(@PathVariable Long videoId) {
		Video fetchedVideo = videoService.getByVideo(videoId);
		return ResponseEntity.ok(fetchedVideo);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Video>> all() {
		List<Video> fetchedAll = videoService.all();
		return new ResponseEntity<List<Video>>(fetchedAll, HttpStatus.OK);
	}

	@DeleteMapping("/deleteById/{videoId}")
	public ResponseEntity<Void> deleteByVideo(@PathVariable Long videoId) {
		Video getVideo = videoService.getByVideo(videoId);
		if (getVideo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			videoService.deleteVideoById(videoId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}
