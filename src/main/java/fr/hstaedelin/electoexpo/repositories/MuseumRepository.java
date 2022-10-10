package fr.hstaedelin.electoexpo.repositories;

import fr.hstaedelin.electoexpo.models.job.Museum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuseumRepository extends CrudRepository<Museum, Integer> { }
