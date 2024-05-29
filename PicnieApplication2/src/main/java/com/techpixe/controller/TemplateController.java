package com.techpixe.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.dto.TemplateRequestDTO;
import com.techpixe.dto.TemplateResponseDTO;
import com.techpixe.entity.ImageElement;
import com.techpixe.entity.Template;
import com.techpixe.service.TemplateService;

import jakarta.persistence.spi.TransformerException;

@CrossOrigin
@RestController
@RequestMapping("/templates")
public class TemplateController {
	@Autowired
	private TemplateService templateService;

	@PostMapping("/create/{projectId}")
	public ResponseEntity<Template> createTemplate(@PathVariable(name = "projectId") Long projectId,
			@RequestPart("template") TemplateRequestDTO templateRequestDTO,
			@RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {
		
		System.err.println(images + " images recevied ");

		if (images != null && !images.isEmpty()) {
			// Set the uploaded images to the template request DTO
			templateRequestDTO.setImages(images);
		}
		Template savedTemplate = templateService.saveTemplate(templateRequestDTO, projectId);
		return new ResponseEntity<>(savedTemplate, HttpStatus.CREATED);
	}

	@GetMapping("/{templateId}")
	public ResponseEntity<ResponseEntity<TemplateResponseDTO>> getTemplateById(@PathVariable Long templateId) {
		ResponseEntity<TemplateResponseDTO> template = templateService.getTemplateById(templateId);
		return ResponseEntity.ok(template);
	}

	@GetMapping("/{templateId}/download")
	public ResponseEntity<byte[]> downloadTemplateImageAsJPEG(@PathVariable long templateId)
			throws TransformerException {
		return templateService.downloadTemplateImageAsJPEG(templateId);
	}

	@GetMapping("/download/{templateId}")
	public ResponseEntity<byte[]> downloadTemplateImage(@PathVariable long templateId) throws TransformerException {
		return templateService.downloadTemplate1(templateId);
	}
	
	@GetMapping("/get/{templateId}")
	public ResponseEntity<Template> templateGetter(@PathVariable long templateId) {
	    try {
	        Template template = templateService.templateGetter(templateId);
	        return ResponseEntity.ok(template);
	    } catch (Exception e) {
	        // Assuming TemplateNotFoundException is a custom exception you have defined
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}

	@PutMapping("/edit/{templateId}")
	public ResponseEntity<Template> editTemplate(@PathVariable(name = "templateId") Long templateId,
			@RequestPart("template") Template templateRequestDTO,
			@RequestPart(value = "images", required = false) List<MultipartFile> images) throws IOException {
		System.err.println(templateRequestDTO + "template recevied");
		System.err.println(images + " images recevied ");

		List<ImageElement> imageElements =	templateRequestDTO.getImageElements();
		for(int i = 0; i <= images.size() - 1 ; i ++) {
			imageElements.get(i).setSrc(images.get(i).getBytes());
		}
		
		Template savedTemplate = templateService.editTemplate(templateRequestDTO, templateId);
		return new ResponseEntity<>(savedTemplate, HttpStatus.CREATED);
	}
	
}
