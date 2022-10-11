package fr.hstaedelin.electoexpo.repositories;

import fr.hstaedelin.electoexpo.models.job.Exposition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpositionRepository extends CrudRepository<Exposition, Integer> { }
