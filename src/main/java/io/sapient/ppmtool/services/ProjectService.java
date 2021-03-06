package io.sapient.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sapient.ppmtool.exceptions.ProjectIdException;
import io.sapient.ppmtool.domain.Project;
import io.sapient.ppmtool.repostiories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdate(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch(Exception e) {
			throw new ProjectIdException("Project ID "+ project.getProjectIdentifier().toUpperCase() + " already exits");
		}
		
	}
}
