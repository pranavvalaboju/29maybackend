package com.techpixe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Shapes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shapeId;

	
	  private String backgroundColor;
	    private String fill;
	    private String fillRule;
	    private boolean flipX;
	    private boolean flipY;
	    private String globalCompositeOperation;
	    private double height;
	    @Column(name = "`left`")
	    private double left;

	    private double opacity;
	    private String originX;
	    private String originY;
	    private String paintFirst;
	    private double rx;
	    private double ry;
	    private double scaleX;
	    private double scaleY;
	    private String shadow;
	    private double skewX;
	    private double skewY;
	    private String stroke;
	    private String strokeDashArray;
	    private double strokeDashOffset;
	    private String strokeLineCap;
	    private String strokeLineJoin;
	    private int strokeMiterLimit;
	    private boolean strokeUniform;
	    private int strokeWidth;
	    private double top;
	    private String type;
	    private String version;
	    private boolean visible;
	    private double width;


	// private Long aspectRatio;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "templateId")
	private Template template;

}
