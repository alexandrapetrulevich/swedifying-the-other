package se.uu.swedifying;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.uu.swedifying.model.entity.*;
import se.uu.swedifying.model.util.IsAdaptedToSwedishType;
import se.uu.swedifying.model.util.MorphologicalData;
import se.uu.swedifying.model.util.MorphologicalNameType;
import se.uu.swedifying.repository.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class SwedifyingTheOtherApplication implements CommandLineRunner {
  @Autowired
  AttestationRepository attestationRepository;
  @Autowired
  LocalityTypeRepository localityTypeRepository;
  @Autowired
  LocationRepository locationRepository;
  @Autowired
  NormalizedFormRepository normalizedFormRepository;
  @Autowired
  AdaptationTypeRepository adaptationTypeRepository;
  @Autowired
  LanguageRepository languageRepository;
  @Autowired
  RegionRepository regionRepository;
  @Autowired
  SubRegionRepository subRegionRepository;
  @Autowired
  LandSurveyorRepository landSurveyorRepository;
  @Autowired
  PartOfSourceRepository partOfSourceRepository;
  @Autowired
  SourceRepository sourceRepository;
  @Autowired
  SourceFindingRepository sourceFindingRepository;

  @Autowired
  VariantFormRepository variantFormRepository;

  @Autowired
  MapSignatureRepository mapSignatureRepository;

  public static void main(String[] args) {
    SpringApplication.run(SwedifyingTheOtherApplication.class, args);
  }

  @Override
  public void run(String... args) {
    addLanguages();
    addLocalityTypes();
    addRegions();
    addSubRegions();
    addLandSurveyors();
    addAdaptationTypes();
    addPartsOfSources();
    Source source = addSources();
    List<SourceFinding> sourceFindings = addSourceFindings(source);
    addLocations();
    addNormalizedForms();
    addVariantForms();

    attestationRepository.save(
      Attestation
        .builder()
        .originalForm("Grosse Ernshoff[s] ??kermark Uthi Wolgasts District")
        .variantForm(variantFormRepository.findByVariantForm("Grosse Ernsthoff"))
        .location(locationRepository.findByModernLookupForm("Gross Ernsthof"))
        .sourceFinding(sourceFindings.get(0))
        .notes("Niemeyer 2:32f.")
        .build());
    attestationRepository.save(
      Attestation
        .builder()
        .originalForm("Grosse Ernshoff[s] ??kermark Uthi Wolgasts District")
        .variantForm(variantFormRepository.findByVariantForm("Wolgasts distrikt"))
        .location(locationRepository.findByModernLookupForm(""))
        .sourceFinding(sourceFindings.get(0))
        .notes("Distriktsnamnet ??r anpassat till sv dock ej bebyggelsenamnet Wolgast som har slavisk etymologi och som medierats via tyska. I notarum st??r inte s??llan \"Wolgasts ampt\".")
        .build());
    attestationRepository.save(
      Attestation
        .builder()
        .originalForm("Wolgasts Stadz Grentz")
        .variantForm(variantFormRepository.findByVariantForm("Wolgast"))
        .location(locationRepository.findByModernLookupForm("Wolgast"))
        .sourceFinding(sourceFindings.get(1))
        .notes("Eichler & Walther 1988:300")
        .build());
  }

  private void addVariantForms() {
    variantFormRepository
      .save(VariantForm
        .builder()
        .variantForm("Grosse Ernsthoff")
        .isAdaptedToSwedish(IsAdaptedToSwedishType.NO)
        .normalizedForm(normalizedFormRepository.findByNormalizedForm("Grosse Ernsthoff"))
        .build());
    variantFormRepository
      .save(VariantForm
        .builder()
        .variantForm("Wolgasts distrikt")
        .isAdaptedToSwedish(IsAdaptedToSwedishType.YES)
        .adaptationType(
          adaptationTypeRepository.findByName("morfologisk anpassning av f??rleden"))
        .normalizedForm(normalizedFormRepository.findByNormalizedForm("Wolgasts distrikt"))
        .build());
    variantFormRepository
      .save(VariantForm
        .builder()
        .variantForm("Wolgast")
        .isAdaptedToSwedish(IsAdaptedToSwedishType.NO)
        .normalizedForm(normalizedFormRepository.findByNormalizedForm("Wolgast"))
        .build());
  }

  private void addNormalizedForms() {
    normalizedFormRepository.save(
      NormalizedForm
        .builder()
        .normalizedForm("Grosse Ernsthoff")
        //.morphologicalNameType(MorphologicalNameType.PHRASE)
        .morphologicalNameType(MorphologicalNameType.COMPOSITION)
        .morphologicalNameTypeIsShaky(false)
        .morphologicalData(
          MorphologicalData
            .builder()
            .determinationClauseInComposition("Ernst")
            .mainClauseInComposition("hof")
            .build())
        .comparativeFormInformation("1618 Ernsthoff, Lubinsche Karte")
        .etymology(languageRepository.findByLanguageCode("de"))
        .mediatingLanguage(null)
        .build());
    normalizedFormRepository.save(
      NormalizedForm
        .builder()
        .normalizedForm("Wolgasts distrikt")
        .morphologicalNameType(MorphologicalNameType.PHRASE)
        .morphologicalNameTypeIsShaky(false)
        .morphologicalData(
          MorphologicalData
            .builder()
            .determinationClauseInPhrase("Wolgasts")
            .mainClauseInPhrase("distrikt")
            .build())
        .comparativeFormInformation("Wolgastischer District, Amte Wolgast. Kahldensche Matrikel")
        .etymology(languageRepository.findByLanguageCode("de"))
        .mediatingLanguage(null)
        .build());

    normalizedFormRepository.save(
      NormalizedForm
        .builder()
        .normalizedForm("Wolgast")
        .morphologicalNameType(MorphologicalNameType.DERIVATION)
        .morphologicalNameTypeIsShaky(false)
        .morphologicalData(
          MorphologicalData
            .builder()
            .derivationBase("Voligost")
            .derivationMorpheme("j")
            .build())
        .comparativeFormInformation("1140 castra hec, scilicet ??? Wologost : Wolgast; 1186 usque Wolegost et a Wolegost. PUB 1:33, 130")
        .etymology(languageRepository.findByLanguageCode("slav"))
        .mediatingLanguage(languageRepository.findByLanguageCode("de"))
        .build());
  }

  private void addLocations() {
    locationRepository.save(
      Location
        .builder()
        .modernLookupForm("Gross Ernsthof")
        .districtOrParish(subRegionRepository.findByName("Wolgasts distrikt"))
        .localityType(localityTypeRepository.findByLocalityTypeName("bebyggelse, by"))
        .build());
    locationRepository.save(
      Location
        .builder()
        .modernLookupForm("")
        .districtOrParish(subRegionRepository.findByName("Wolgasts distrikt"))
        .localityType(localityTypeRepository.findByLocalityTypeName("distrikt"))
        .build());
    locationRepository.save(
      Location
        .builder()
        .modernLookupForm("Wolgast")
        .districtOrParish(subRegionRepository.findByName("Wolgasts distrikt"))
        .localityType(localityTypeRepository.findByLocalityTypeName("bebyggelse, stad"))
        .build());
  }

  private List<SourceFinding> addSourceFindings(Source source) {
    return List.of(
      sourceFindingRepository.save(
        SourceFinding
          .builder()
          .partOfSource(partOfSourceRepository.findByPartOfSourceName("Rubrik"))
          .source(source)
          .build())
      , sourceFindingRepository.save(
        SourceFinding
          .builder()
          .partOfSource(partOfSourceRepository.findByPartOfSourceName("Gr??nsmarkering"))
          .source(source)
          .build()));
  }

  private MapSource addSources() {
    MapSignature mapSignature = mapSignatureRepository
      .save(MapSignature.builder().mapSignature("BI").build());
    return sourceRepository.save(
      MapSource
        .builder()
        .mapSignature(mapSignature)
        .mapSheet(1)
        .dating(LocalDate.of(1694, Month.JUNE, 20))
        .landSurveyor(landSurveyorRepository.findByName("Simon Skragge"))
        .build());
  }

  private void addPartsOfSources() {
    partOfSourceRepository.save(
      PartOfSource
        .builder()
        .partOfSourceName("Rubrik")
        .build());
    partOfSourceRepository.save(
      PartOfSource
        .builder()
        .partOfSourceName("Kartkroppen")
        .build());
    partOfSourceRepository.save(
      PartOfSource
        .builder()
        .partOfSourceName("Gr??nsmarkering")
        .build());
    partOfSourceRepository.save(
      PartOfSource
        .builder()
        .partOfSourceName("Utanf??r kartkroppen")
        .build());
  }

  private void addAdaptationTypes() {
    adaptationTypeRepository.save(
      AdaptationType
        .builder()
        .name("morfologisk anpassning av f??rleden")
        .build());
    adaptationTypeRepository.save(
      AdaptationType
        .builder()
        .name("??vers??ttning av huvudleden")
        .build());
  }

  private void addLandSurveyors() {
    landSurveyorRepository.save(LandSurveyor.builder().name("Simon Skragge").build());
  }

  private void addSubRegions() {
    subRegionRepository.save(
      District
        .builder()
        .belongsToRegion(regionRepository.findByRegionName("Pommern"))
        .name("Wolgasts distrikt")
        .build());
    Precinct nyland = subRegionRepository.save(
      Precinct
        .builder()
        .belongsToRegion(regionRepository.findByRegionName("Finland"))
        .name("Nyland")
        .build()
    );
    subRegionRepository.save(
      Parish
        .builder()
        .belongsToPrecinct(nyland)
        .belongsToRegion(regionRepository.findByRegionName("Finland"))
        .name("Helsingfors socken")
        .build()
    );
  }

  private void addRegions() {
    regionRepository.save(Region.builder().regionName("Pommern").build());
    regionRepository.save(Region.builder().regionName("Finland").build());
    regionRepository.save(Region.builder().regionName("V??sterbotten").build());
  }

  private void addLocalityTypes() {
    localityTypeRepository.save(LocalityType.builder().localityTypeName("bebyggelse, by").build());
    localityTypeRepository.save(LocalityType.builder().localityTypeName("distrikt").build());
    localityTypeRepository.save(LocalityType.builder().localityTypeName("bebyggelse, stad").build());
  }

  private void addLanguages() {
    //INSERT INTO LANGUAGE (LANGUAGE_ID, LANGUAGE_NAME, LANGUAGE_CODE) VALUES (1, 'Swedish', 'sv');
    //INSERT INTO LANGUAGE(LANGUAGE_ID, LANGUAGE_NAME, LANGUAGE_CODE) VALUES(2, 'Latin', 'la');
    //INSERT INTO LANGUAGE(LANGUAGE_ID, LANGUAGE_NAME, LANGUAGE_CODE) VALUES(3, 'Finnish', 'fi');
    //INSERT INTO LANGUAGE(LANGUAGE_ID, LANGUAGE_NAME, LANGUAGE_CODE) VALUES(4, 'German', 'de');
    //INSERT INTO LANGUAGE(LANGUAGE_ID, LANGUAGE_NAME, LANGUAGE_CODE) VALUES(5, 'English', 'en');
    languageRepository.save(Language
      .builder()
      .languageName("Swedish")
      .languageCode("sv")
      .build());
    languageRepository.save(Language
      .builder()
      .languageName("Latin")
      .languageCode("la")
      .build());
    languageRepository.save(Language
      .builder()
      .languageName("Finnish")
      .languageCode("fi")
      .build());
    languageRepository.save(Language
      .builder()
      .languageName("German")
      .languageCode("de")
      .build());
    languageRepository.save(Language
      .builder()
      .languageName("English")
      .languageCode("en")
      .build());
    languageRepository.save(Language
      .builder()
      .languageName("Slavic")
      .languageCode("slav")
      .build());
  }

}
