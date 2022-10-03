package fr.hstaedelin.electoexpo.repositories;

import fr.hstaedelin.electoexpo.models.job.Object;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends CrudRepository<Object, Integer> { }
