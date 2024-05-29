package com.techpixe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long videoId;

	private String fileName;

	@Lob
	@Column(name = "src", columnDefinition = "LONGBLOB")
	private byte[] src;
}
