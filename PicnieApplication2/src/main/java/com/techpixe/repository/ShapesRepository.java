package com.techpixe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.ImageElement;
import com.techpixe.entity.Shapes;
import com.techpixe.entity.Template;

public interface ShapesRepository extends JpaRepository<Shapes, Long> {

	List<Shapes> findByTemplate(Template template);

	List<Shapes> findByTemplateTemplateId(long templateId);
	List<Shapes> findAllByTemplateTemplateId(long templateId);

}
