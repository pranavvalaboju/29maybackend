package com.techpixe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
