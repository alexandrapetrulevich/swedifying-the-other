package se.uu.swedifying.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.uu.swedifying.model.entity.MapSource;
import se.uu.swedifying.model.projection.MapSourceView;

import java.util.List;

@RepositoryRestResource(excerptProjection = MapSourceView.class)
public interface MapSourceRepository extends CrudRepository<MapSource, Long> {
  @Override
  List<MapSource> findAll();
}