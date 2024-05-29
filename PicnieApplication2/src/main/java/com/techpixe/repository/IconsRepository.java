package com.techpixe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.Icons;
import com.techpixe.entity.ImageElement;

public interface IconsRepository extends JpaRepository<Icons, Long> {
	List<Icons> findByTemplateTemplateId(long templateId);

	List<Icons> findAllByTemplateTemplateId(long templateId);
}
