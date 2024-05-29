package com.techpixe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.ImageElement;
import com.techpixe.entity.Template;
import com.techpixe.entity.TextElement;

public interface TextElementRepository extends JpaRepository<TextElement, Long> {

	List<TextElement> findByTemplate(Template template);

	List<TextElement> findByTemplateTemplateId(long templateId);
	List<TextElement> findAllByTemplateTemplateId(long templateId);

}
