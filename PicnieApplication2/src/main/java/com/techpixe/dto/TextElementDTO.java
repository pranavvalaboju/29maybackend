package com.techpixe.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.techpixe.entity.Template;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextElementDTO {
	
	private Long textElementId;

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
    private int minWidth;
    private double opacity;
    private String originX;
    private String originY;
    private boolean overline;
    private String paintFirst;
    private String path;
    private String pathAlign;
    private String pathSide;
    private int pathStartOffset;
    private double scaleX;
    private double scaleY;
    private String shadow;
    private int skewX;
    private int skewY;
    private boolean splitByGrapheme;
    private String stroke;
    private String strokeDashArray;
    private int strokeDashOffset;
    private String strokeLineCap;
    private String strokeLineJoin;
    private int strokeMiterLimit;
    private boolean strokeUniform;
    private int strokeWidth;
    private String text;
    private String textAlign;
    private String textBackgroundColor;
    private double top;
    private String type;
    private boolean underline;
    private String version;
    private boolean visible;
    private double width;

}
