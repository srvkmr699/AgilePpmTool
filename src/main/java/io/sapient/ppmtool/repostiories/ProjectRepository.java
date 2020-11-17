package io.sapient.ppmtool.repostiories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.sapient.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

	@Override
	Iterable<Project> findAllById(Iterable<Long> ids);
}
