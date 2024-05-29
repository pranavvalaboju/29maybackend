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
public class Icons {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iconId;
	
	private int angle;
    private String backgroundColor;
    private int charSpacing;
    private String direction;
    private String fill;
    private String fillRule;
    private boolean flipX;
    private boolean flipY;
    private String fontFamily;
    private int fontSize;
    private String fontStyle;
    private String fontWeight;
    private String globalCompositeOperation;
    private double height;
    @Column(name = "`left`")
    private double left;

    private double lineHeight;
    private boolean linethrough;
    private double opacity;
    private String originX;
    private String originY;
    private boolean overline;
    private String paintFirst;
    private String path;
    private String pathAlign;
    private String pathSide;
    private double pathStartOffset;
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
    private double strokeMiterLimit;
    private boolean strokeUniform;
    private double strokeWidth;
    private String styles;
    private String text;
    private String textAlign;
    private String textBackgroundColor;
    private double top;
    private String type;
    private boolean underline;
    private String version;
    private boolean visible;
    private double width;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "templateId")
	private Template template;

}