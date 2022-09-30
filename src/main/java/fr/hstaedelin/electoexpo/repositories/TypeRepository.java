package fr.hstaedelin.electoexpo.repositories;

import fr.hstaedelin.electoexpo.models.job.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends CrudRepository<Type, Integer> { }
