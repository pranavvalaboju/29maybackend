package com.techpixe.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.techpixe.dto.TemplateEditDto;
import com.techpixe.dto.TemplateRequestDTO;
import com.techpixe.dto.TemplateResponseDTO;
import com.techpixe.entity.Template;

import jakarta.persistence.spi.TransformerException;

public interface TemplateService {

	ResponseEntity<TemplateResponseDTO> getTemplateById(long templateId);

	Template saveTemplate(TemplateRequestDTO templateRequestDTO, Long projectId) throws IOException;

	ResponseEntity<byte[]> downloadTemplateImageAsJPEG(long templateId) throws TransformerException;

	ResponseEntity<byte[]> downloadTemplate1(long templateId);

	public Template templateGetter(long templateId);
	
	public Template editTemplate(Template templateRequestDTO, Long templateId);
}
