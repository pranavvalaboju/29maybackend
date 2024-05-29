package com.techpixe.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techpixe.entity.Shapes;
import com.techpixe.entity.TextElement;


public class TemplateDto {

	private String templateName;

	private String type;

//	private Project project;

	private List<TextElement> textElements;

	private List<MultipartFile> imageElements;

	private List<Shapes> shapes;

	public TemplateDto() {
		super();
	}

	public TemplateDto(String templateName, String type, List<TextElement> textElements,
			List<MultipartFile> imageElements, List<Shapes> shapes) {
		super();
		this.templateName = templateName;
		this.type = type;
		this.textElements = textElements;
		this.imageElements = imageElements;
		this.shapes = shapes;
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

	public List<TextElement> getTextElements() {
		return textElements;
	}

	public void setTextElements(List<TextElement> textElements) {
		this.textElements = textElements;
	}

	public List<MultipartFile> getImageElements() {
		return imageElements;
	}

	public void setImageElements(List<MultipartFile> imageElements) {
		this.imageElements = imageElements;
	}

	public List<Shapes> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shapes> shapes) {
		this.shapes = shapes;
	}

	@Override
	public String toString() {
		return "TemplateDto [templateName=" + templateName + ", type=" + type + ", textElements=" + textElements
				+ ", imageElements=" + imageElements + ", shapes=" + shapes + "]";
	}

}
