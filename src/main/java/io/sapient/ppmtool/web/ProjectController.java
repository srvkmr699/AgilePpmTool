package io.sapient.ppmtool.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sapient.ppmtool.domain.Project;
import io.sapient.ppmtool.services.MapValidationErrorService;
import io.sapient.ppmtool.services.ProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {

		ResponseEntity<Map<String,String>> errorMap = mapValidationErrorService.mapValidationError(bindingResult);
		if(errorMap != null) {
			return errorMap;
		}
		
		Project createdProject = projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(createdProject, HttpStatus.CREATED);
	}

}
