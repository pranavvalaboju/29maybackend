package com.techpixe.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.techpixe.entity.Video;
import com.techpixe.repository.VideoRepository;
import com.techpixe.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	VideoRepository videoRepository;

	@Override
	public Video saveVideo(MultipartFile file) {
		Video video = new Video();
		video.setFileName(file.getOriginalFilename());
		try {
			video.setSrc(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return videoRepository.save(video);
	}

	@Override
	public Video getByVideo(Long videoId) {
		Optional<Video> fetchedVideo = videoRepository.findById(videoId);
		if (fetchedVideo.isPresent()) {
			Video video = fetchedVideo.get();
			return video;
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Video is not Present with this Id " + videoId);
		}
	}

	@Override
	public List<Video> all() {
		List<Video> fetchedAll = videoRepository.findAll();
		if (!fetchedAll.isEmpty()) {
			return fetchedAll;
		}
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No video are Present");
	}

	@Override
	public void deleteVideoById(Long videoId) {
		videoRepository.deleteById(videoId);

	}
}
