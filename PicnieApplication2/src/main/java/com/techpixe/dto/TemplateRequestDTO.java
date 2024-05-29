package com.techpixe.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techpixe.entity.Icons;
import com.techpixe.entity.ImageElement;
import com.techpixe.entity.Shapes;
import com.techpixe.entity.Template;
import com.techpixe.entity.TextElement;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class TemplateRequestDTO {
	private String templateName;
	private String type;
	private Long projectId;
	private List<TextElement> textElements;
	private List<ImageElement> imageElements;
	private List<Shapes> shapes;
	private List<MultipartFile> images;
	private List<Icons> icons;
	public TemplateRequestDTO() {
		super();
	}
	public TemplateRequestDTO(String templateName, String type, Long projectId, List<TextElement> textElements,
			List<ImageElement> imageElements, List<Shapes> shapes, List<MultipartFile> images, List<Icons> icons) {
		super();
		this.templateName = templateName;
		this.type = type;
		this.projectId = projectId;
		this.textElements = textElements;
		this.imageElements = imageElements;
		this.shapes = shapes;
		this.images = images;
		this.icons = icons;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public List<TextElement> getTextElements() {
		return textElements;
	}
	public void setTextElements(List<TextElement> textElements) {
		this.textElements = textElements;
	}
	public List<ImageElement> getImageElements() {
		return imageElements;
	}
	public void setImageElements(List<ImageElement> imageElements) {
		this.imageElements = imageElements;
	}
	public List<Shapes> getShapes() {
		return shapes;
	}
	public void setShapes(List<Shapes> shapes) {
		this.shapes = shapes;
	}
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	public List<Icons> getIcons() {
		return icons;
	}
	public void setIcons(List<Icons> icons) {
		this.icons = icons;
	}
	@Override
	public String toString() {
		return "TemplateRequestDTO [templateName=" + templateName + ", type=" + type + ", projectId=" + projectId
				+ ", textElements=" + textElements + ", imageElements=" + imageElements + ", shapes=" + shapes
				+ ", images=" + images + ", icons=" + icons + "]";
	}
	
	
}