package com.techpixe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageElement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int angle;
    private String backgroundColor;
    private int cropX;
    private int cropY;
    private String crossOrigin;
    private String fill;
    private String fillRule;
 //   private String[] filters;
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
	
	
	
	
	
	@Lob()
	private byte[] src;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Template template; // Many-to-one relationship with Template

}