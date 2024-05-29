package com.techpixe.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.dto.TemplateEditDto;
import com.techpixe.dto.TemplateRequestDTO;
import com.techpixe.dto.TemplateResponseDTO;
import com.techpixe.entity.Icons;
import com.techpixe.entity.ImageElement;
import com.techpixe.entity.Shapes;
import com.techpixe.entity.Template;
import com.techpixe.entity.TextElement;
import com.techpixe.repository.IconsRepository;
import com.techpixe.repository.ImageElementRepository;
import com.techpixe.repository.ProjectRepository;
import com.techpixe.repository.ShapesRepository;
import com.techpixe.repository.TemplateRepository;
import com.techpixe.repository.TextElementRepository;
import com.techpixe.service.TemplateService;

import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	TemplateRepository templateRepository;

	@Autowired
	ShapesRepository shapesRepository;

	@Autowired
	TextElementRepository textElementRepository;

	@Autowired
	ImageElementRepository imageElementRepository;

	@Autowired
	IconsRepository iconRepository;

	@Override
	@Transactional
	public Template saveTemplate(TemplateRequestDTO templateRequestDTO, Long projectId) {

		Template template1 = new Template();
		template1.setTemplateName(templateRequestDTO.getTemplateName());
		template1.setType(templateRequestDTO.getType());
		template1.setProject(projectRepository.findById(projectId).orElse(null));

		Template template = templateRepository.save(template1);

		List<TextElement> textElements = templateRequestDTO.getTextElements();
		if (textElements != null && !textElements.isEmpty()) {
			for (TextElement textElement : textElements) {
				textElement.setTemplate(template);
			}
			textElementRepository.saveAll(textElements);
		}

		List<ImageElement> imageElements = templateRequestDTO.getImageElements();
		List<MultipartFile> images = templateRequestDTO.getImages();

		for (int i = 0; i <= imageElements.size() - 1; i++) {
			ImageElement imageElement = imageElements.get(i);
			MultipartFile imageFile = images.get(i);

			try {
				// Save the image file to a location accessible via URL
				// For example, you can save it to a directory within your application
				// Replace "/path/to/save/image" with the actual path where you want to save the
				// image
//				Path imagePath = Paths.get("C://Users//pc//Desktop//images//", imageFile.getOriginalFilename());
//				Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
//
//				// Construct the URL for the saved image
	//		String imageUrl = "https://picnie.com/data/api_raw_assets/" + imageFile.getOriginalFilename();

				// Set the image URL in the ImageElement entity
		//		imageElement.setImageUrl(imageUrl);
				imageElement.setSrc(images.get(i).getBytes());

			} catch (IOException e) {
				e.printStackTrace();
				// Handle exception
			}

			// Set the template for the image element
			imageElement.setTemplate(template);
		}

		// Save the ImageElement entities with updated URLs
		template.setImageElements(imageElements);
		imageElementRepository.saveAll(imageElements);

		List<Shapes> shapes = templateRequestDTO.getShapes();
		if (shapes != null && !shapes.isEmpty()) {
			for (Shapes shape : shapes) {
				shape.setTemplate(template);
			}
			shapesRepository.saveAll(shapes);
		}

		List<Icons> icons = templateRequestDTO.getIcons();
		if (icons != null && !icons.isEmpty()) {
			for (Icons icon : icons) {
				icon.setTemplate(template);
			}
			iconRepository.saveAll(icons);
		}

		return template;
	}

	@Override
	public ResponseEntity<TemplateResponseDTO> getTemplateById(long templateId) {
		Optional<Template> templateOptional = templateRepository.findById(templateId);
		if (templateOptional.isPresent()) {
			Template template = templateOptional.get();
			TemplateResponseDTO responseDTO = new TemplateResponseDTO();
			responseDTO.setTemplate(template);
			responseDTO.setTextElements(template.getTextElements());
			responseDTO.setImageElements(template.getImageElements());
			responseDTO.setShapes(template.getShapes());
			responseDTO.setIcons(template.getIcons());
			return ResponseEntity.ok(responseDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<byte[]> downloadTemplateImageAsJPEG(long templateId) {

		// Set up EdgeDriver using WebDriverManager
		WebDriverManager.edgedriver().setup();

		// Create a new instance of the EdgeDriver
		WebDriver driver = new EdgeDriver();

		try {
			// Set the window size to the desired dimensions
			driver.manage().window().setSize(new Dimension(1024, 768));

			// Load the HTML content
			String htmlContent = generateTemplateHtml(templateId);

			// Load the HTML content into the browser
			driver.get("data:text/html;charset=utf-8," + htmlContent);

			// Take a screenshot of the rendered page
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Read the screenshot file into byte array
			byte[] imageData = Files.readAllBytes(screenshot.toPath());

			// Prepare response headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			headers.setContentDispositionFormData("attachment", "template.jpg");

			// Return ResponseEntity with image data
			return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println("Error capturing screenshot: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} finally {
			// Close the browser
			if (driver != null) {
				driver.quit();
			}
		}
	}
//
	private String generateTemplateHtml(long templateId) {
		Optional<Template> templateOptional = templateRepository.findById(templateId);
		System.out.println("3");
		if (templateOptional.isPresent()) {
			Template template = templateOptional.get();
			StringBuilder htmlBuilder = new StringBuilder();

			// Append HTML for text elements
			for (TextElement textElement : template.getTextElements()) {
				htmlBuilder.append("<div style=\"");
				// Append styles based on text element properties
				htmlBuilder.append("left: ").append(textElement.getLeft()).append("%; ");
				htmlBuilder.append("top: ").append(textElement.getTop()).append("%; ");
				// Add more style properties as needed
				htmlBuilder.append("\">");
				htmlBuilder.append(textElement.getText());
				htmlBuilder.append("</div>");
			}

			for (ImageElement imageElement : template.getImageElements()) {
			    // Convert the image to base64 format
			    String base64Image = convertImageToBase64(imageElement.getSrc());

			    // Append the base64-encoded image to the HTML content
			    htmlBuilder.append("<img src=\"data:image/png;base64,").append(base64Image).append("\" ");

			    // Append image element properties as HTML attributes
			    htmlBuilder.append("style=\"");
			    htmlBuilder.append("angle: ").append(imageElement.getAngle()).append("; ");
			    htmlBuilder.append("backgroundColor: ").append(imageElement.getBackgroundColor()).append("; ");
			    htmlBuilder.append("cropX: ").append(imageElement.getCropX()).append("; ");
			    htmlBuilder.append("cropY: ").append(imageElement.getCropY()).append("; ");
			    htmlBuilder.append("crossOrigin: ").append(imageElement.getCrossOrigin()).append("; ");
			    htmlBuilder.append("fill: ").append(imageElement.getFill()).append("; ");
			    htmlBuilder.append("fillRule: ").append(imageElement.getFillRule()).append("; ");
			    htmlBuilder.append("flipX: ").append(imageElement.isFlipX()).append("; ");
			    htmlBuilder.append("flipY: ").append(imageElement.isFlipY()).append("; ");
			    htmlBuilder.append("globalCompositeOperation: ").append(imageElement.getGlobalCompositeOperation()).append("; ");
			    htmlBuilder.append("height: ").append(imageElement.getHeight()).append("; ");
			    htmlBuilder.append("left: ").append(imageElement.getLeft()).append("; ");
			    htmlBuilder.append("opacity: ").append(imageElement.getOpacity()).append("; ");
			    htmlBuilder.append("originX: ").append(imageElement.getOriginX()).append("; ");
			    htmlBuilder.append("originY: ").append(imageElement.getOriginY()).append("; ");
			    htmlBuilder.append("paintFirst: ").append(imageElement.getPaintFirst()).append("; ");
			    htmlBuilder.append("scaleX: ").append(imageElement.getScaleX()).append("; ");
			    htmlBuilder.append("scaleY: ").append(imageElement.getScaleY()).append("; ");
			    htmlBuilder.append("shadow: ").append(imageElement.getShadow()).append("; ");
			    htmlBuilder.append("skewX: ").append(imageElement.getSkewX()).append("; ");
			    htmlBuilder.append("skewY: ").append(imageElement.getSkewY()).append("; ");
			    htmlBuilder.append("stroke: ").append(imageElement.getStroke()).append("; ");
			    htmlBuilder.append("strokeDashArray: ").append(imageElement.getStrokeDashArray()).append("; ");
			    htmlBuilder.append("strokeDashOffset: ").append(imageElement.getStrokeDashOffset()).append("; ");
			    htmlBuilder.append("strokeLineCap: ").append(imageElement.getStrokeLineCap()).append("; ");
			    htmlBuilder.append("strokeLineJoin: ").append(imageElement.getStrokeLineJoin()).append("; ");
			    htmlBuilder.append("strokeMiterLimit: ").append(imageElement.getStrokeMiterLimit()).append("; ");
			    htmlBuilder.append("strokeUniform: ").append(imageElement.isStrokeUniform()).append("; ");
			    htmlBuilder.append("strokeWidth: ").append(imageElement.getStrokeWidth()).append("; ");
			    htmlBuilder.append("top: ").append(imageElement.getTop()).append("; ");
			    htmlBuilder.append("type: ").append(imageElement.getType()).append("; ");
			    htmlBuilder.append("version: ").append(imageElement.getVersion()).append("; ");
			    htmlBuilder.append("visible: ").append(imageElement.isVisible()).append("; ");
			    htmlBuilder.append("width: ").append(imageElement.getWidth()).append("; ");
			    // Add more style properties as needed
			    htmlBuilder.append("\"/>");
			}

			// Append HTML for shapes
			for (Shapes shape : template.getShapes()) {
				htmlBuilder.append("<div style=\"");
				// Append styles based on shape properties
				htmlBuilder.append("background-color: ").append(shape.getFill()).append("; ");
				htmlBuilder.append("width: ").append(shape.getWidth()).append("px; ");
				htmlBuilder.append("height: ").append(shape.getHeight()).append("px; ");
				// Add more style properties as needed
				htmlBuilder.append("\"></div>");
			}

			  for (Icons icon : template.getIcons()) {
			        htmlBuilder.append("<i class=\"").append(icon.getText()).append("\" style=\"");

			        // Append all fields
			        htmlBuilder.append("angle:").append(icon.getAngle()).append("; ");
			        htmlBuilder.append("backgroundColor:").append(icon.getBackgroundColor()).append("; ");
			        htmlBuilder.append("charSpacing:").append(icon.getCharSpacing()).append("; ");
			        htmlBuilder.append("direction:").append(icon.getDirection()).append("; ");
			        htmlBuilder.append("fill:").append(icon.getFill()).append("; ");
			        htmlBuilder.append("fillRule:").append(icon.getFillRule()).append("; ");
			        htmlBuilder.append("flipX:").append(icon.isFlipX()).append("; ");
			        htmlBuilder.append("flipY:").append(icon.isFlipY()).append("; ");
			        htmlBuilder.append("fontFamily:").append(icon.getFontFamily()).append("; ");
			        htmlBuilder.append("fontSize:").append(icon.getFontSize()).append("; ");
			        htmlBuilder.append("fontStyle:").append(icon.getFontStyle()).append("; ");
			        htmlBuilder.append("fontWeight:").append(icon.getFontWeight()).append("; ");
			        htmlBuilder.append("globalCompositeOperation:").append(icon.getGlobalCompositeOperation()).append("; ");
			        htmlBuilder.append("height:").append(icon.getHeight()).append("; ");
			        htmlBuilder.append("left:").append(icon.getLeft()).append("; ");
			        htmlBuilder.append("lineHeight:").append(icon.getLineHeight()).append("; ");
			        htmlBuilder.append("linethrough:").append(icon.isLinethrough()).append("; ");
			        htmlBuilder.append("opacity:").append(icon.getOpacity()).append("; ");
			        htmlBuilder.append("originX:").append(icon.getOriginX()).append("; ");
			        htmlBuilder.append("originY:").append(icon.getOriginY()).append("; ");
			        htmlBuilder.append("overline:").append(icon.isOverline()).append("; ");
			        htmlBuilder.append("paintFirst:").append(icon.getPaintFirst()).append("; ");
			        htmlBuilder.append("path:").append(icon.getPath()).append("; ");
			        htmlBuilder.append("pathAlign:").append(icon.getPathAlign()).append("; ");
			        htmlBuilder.append("pathSide:").append(icon.getPathSide()).append("; ");
			        htmlBuilder.append("pathStartOffset:").append(icon.getPathStartOffset()).append("; ");
			        htmlBuilder.append("scaleX:").append(icon.getScaleX()).append("; ");
			        htmlBuilder.append("scaleY:").append(icon.getScaleY()).append("; ");
			        htmlBuilder.append("shadow:").append(icon.getShadow()).append("; ");
			        htmlBuilder.append("skewX:").append(icon.getSkewX()).append("; ");
			        htmlBuilder.append("skewY:").append(icon.getSkewY()).append("; ");
			        htmlBuilder.append("stroke:").append(icon.getStroke()).append("; ");
			        htmlBuilder.append("strokeDashArray:").append(icon.getStrokeDashArray()).append("; ");
			        htmlBuilder.append("strokeDashOffset:").append(icon.getStrokeDashOffset()).append("; ");
			        htmlBuilder.append("strokeLineCap:").append(icon.getStrokeLineCap()).append("; ");
			        htmlBuilder.append("strokeLineJoin:").append(icon.getStrokeLineJoin()).append("; ");
			        htmlBuilder.append("strokeMiterLimit:").append(icon.getStrokeMiterLimit()).append("; ");
			        htmlBuilder.append("strokeUniform:").append(icon.isStrokeUniform()).append("; ");
			        htmlBuilder.append("strokeWidth:").append(icon.getStrokeWidth()).append("; ");
			        htmlBuilder.append("styles:").append(icon.getStyles()).append("; ");
			        htmlBuilder.append("text:").append(icon.getText()).append("; ");
			        htmlBuilder.append("textAlign:").append(icon.getTextAlign()).append("; ");
			        htmlBuilder.append("textBackgroundColor:").append(icon.getTextBackgroundColor()).append("; ");
			        htmlBuilder.append("top:").append(icon.getTop()).append("; ");
			        htmlBuilder.append("type:").append(icon.getType()).append("; ");
			        htmlBuilder.append("underline:").append(icon.isUnderline()).append("; ");
			        htmlBuilder.append("version:").append(icon.getVersion()).append("; ");
			        htmlBuilder.append("visible:").append(icon.isVisible()).append("; ");
			        htmlBuilder.append("width:").append(icon.getWidth()).append("; ");

			        htmlBuilder.append("\"></i>");
			    }

			return htmlBuilder.toString();
		} else {
			return "template not found"; // Handle template not found
		}
	}

	private String convertImageToBase64(byte[] imageBytes) {
		return Base64.getEncoder().encodeToString(imageBytes);
	}


	@Override
	public ResponseEntity<byte[]> downloadTemplate1(long templateId) {
		Optional<Template> templateOptional = templateRepository.findById(templateId);
		if (templateOptional.isPresent()) {
			Template template = templateOptional.get();
			// String htmlContent = generateHtmlContent(template, id);

			String htmlcontent = generateTemplateHtml(templateId);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.TEXT_HTML);
			headers.setContentDispositionFormData("attachment", "template.html");

			return new ResponseEntity<>(htmlcontent.getBytes(), headers, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@Override
	public Template templateGetter(long templateId) {
		return templateRepository.findById(templateId).get();
	}

	@Transactional
	@Override
	public Template editTemplate(Template template, Long templateId) {
	    System.out.print(template);

	    // Find the existing template
	    Template existingTemplate = templateRepository.findById(templateId)
	            .orElseThrow(() -> new EntityNotFoundException("Template not found"));

	    // Update fields except the ID
	    existingTemplate.setTemplateName(template.getTemplateName());
	    existingTemplate.setProject(template.getProject());

	    // Update icons
	    List<Icons> iconsList = template.getIcons();
	    for (Icons icon : iconsList) {
	        icon.setTemplate(existingTemplate);
	    }
	    iconRepository.saveAll(iconsList);

	    // Update shapes
	    List<Shapes> shapesList = template.getShapes();
	    for (Shapes shape : shapesList) {
	        shape.setTemplate(existingTemplate);
	    }
	    shapesRepository.saveAll(shapesList);

	    // Update text elements
	    List<TextElement> textElementList = template.getTextElements();
	    for (TextElement textElement : textElementList) {
	        textElement.setTemplate(existingTemplate);
	    }
	    textElementRepository.saveAll(textElementList);

	    // Update image elements
	    List<ImageElement> imageElementList = template.getImageElements();
	    for (ImageElement imageElement : imageElementList) {
	        imageElement.setTemplate(existingTemplate);
	    }
	    imageElementRepository.saveAll(imageElementList);

	    // Save the updated template
	    Template updatedTemplate = templateRepository.save(existingTemplate);

	    return updatedTemplate;
	}

}