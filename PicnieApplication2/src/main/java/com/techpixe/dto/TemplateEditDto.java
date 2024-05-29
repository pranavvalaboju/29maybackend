package com.techpixe.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateEditDto {
	 private int templateId;
	    private String templateName;
	    private String type;
	    private ProjectDTO project;
	    private List<TextElementDTO> textElements;
	    private List<ImageElementDTO> imageElements;
	    private List<ShapeDTO> shapes;
	    private List<IconDTO> icons;
}