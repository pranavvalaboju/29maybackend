package com.techpixe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
	   private int projectId;
	    private String projectName;
	    private String status;
	    private String created;
	    
}
