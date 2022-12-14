package se.uu.swedifying.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.uu.swedifying.model.api.CreateLocalityTypeRequest;
import se.uu.swedifying.model.api.LocalityTypeDto;
import se.uu.swedifying.model.entity.LocalityType;
import se.uu.swedifying.repository.LocalityTypeRepository;
import se.uu.swedifying.service.LocalityTypeService;

import java.util.List;

@Service
public class LocalityTypeServiceImpl implements LocalityTypeService {

  private final LocalityTypeRepository localityTypeRepository;

  @Autowired
  LocalityTypeServiceImpl(LocalityTypeRepository localityTypeRepository) {
    this.localityTypeRepository = localityTypeRepository;
  }

  @Override
  public LocalityTypeDto createLocalityType(CreateLocalityTypeRequest createLocalityTypeRequest) {
    return localityTypeToLocalityTypeDto(
      localityTypeRepository.save(
        LocalityTypeConversionHelper.createLocalityTypeRequestToLocalityType(createLocalityTypeRequest)));
  }

  @Override
  public List<LocalityTypeDto> getAllLocalityTypes() {
    return localityTypeRepository
      .findAll()
      .stream()
      .map(localityType -> localityTypeToLocalityTypeDto(localityType))
      .toList();
  }

  @Override
  public void deleteLocalityTypeById(long id) {
    localityTypeRepository.deleteById(id);
  }

  @Override
  public List<LocalityTypeDto> getFilteredLocalityTypes(String filterText) {
    List<LocalityType> filteredLocalityTypes = filterText.startsWith("^") ?
      localityTypeRepository.findByLocalityTypeNameStartsWith(filterText.substring(1))
      : filterText.endsWith("$") ?
      localityTypeRepository.findByLocalityTypeNameEndsWith(
        filterText.substring(0, filterText.length() - 1))
      : localityTypeRepository.findByLocalityTypeNameContains(filterText);
    return filteredLocalityTypes
      .stream()
      .map(localityType -> localityTypeToLocalityTypeDto(localityType))
      .toList();
  }

  @Override
  public LocalityTypeDto getLocalityTypeById(long id) {
    return localityTypeToLocalityTypeDto(localityTypeRepository.findById(id).orElseThrow());
  }
  private LocalityTypeDto localityTypeToLocalityTypeDto(LocalityType localityType) {
    return LocalityTypeConversionHelper.localityTypeToLocalityTypeDto(localityType);
  }

}
