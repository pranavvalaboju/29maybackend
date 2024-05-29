package com.techpixe.dto;

import java.util.List;

import com.techpixe.entity.Icons;
import com.techpixe.entity.ImageElement;
import com.techpixe.entity.Shapes;
import com.techpixe.entity.Template;
import com.techpixe.entity.TextElement;

import lombok.Data;

@Data
public class TemplateResponseDTO {
	private Template template;
	private List<TextElement> textElements;
	private List<ImageElement> imageElements;
	private List<Shapes> shapes;
	private List<Icons> icons;

}
