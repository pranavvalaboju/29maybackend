package com.techpixe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.ImageElement;
import com.techpixe.entity.Template;

public interface ImageElementRepository extends JpaRepository<ImageElement, Long> {

	List<ImageElement> findByTemplate(Template template);

	List<ImageElement> findByTemplateTemplateId(long templateId);
	
	List<ImageElement> findAllByTemplateTemplateId(long templateId);
}
