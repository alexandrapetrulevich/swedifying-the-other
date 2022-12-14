package se.uu.swedifying.service.impl;

import org.junit.jupiter.api.Test;
import se.uu.swedifying.model.api.AttestationDto;
import se.uu.swedifying.model.api.CreateAttestationRequest;
import se.uu.swedifying.model.api.LocationDto;
import se.uu.swedifying.model.entity.Attestation;
import se.uu.swedifying.model.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class AttestationConversionHelperTest {

  @Test
  void testCreateAttestationRequestToAttestationNoRelationsOk() {
    String expectedOriginalForm = "originalForm";
    CreateAttestationRequest createAttestationRequest = new CreateAttestationRequest(
      expectedOriginalForm
      , null
      , null);
    Attestation attestation = AttestationConversionHelper.createAttestationRequestToAttestation(
      createAttestationRequest
      , null);
    assertEquals(expectedOriginalForm, attestation.getOriginalForm());
    assertNull(attestation.getAttestationId());
    assertNull(attestation.getLocation());
  }

  @Test
  void testCreateAttestationRequestToAttestationCreateAttestationRequestNok() {
    assertThrows(NullPointerException.class
      , () ->
        AttestationConversionHelper.createAttestationRequestToAttestation(
          null
          , null));
  }

  @Test
  void testCreateAttestationRequestToAttestationWithLocationOk() {
    String expectedOriginalForm = "originalForm";
    LocationDto locationDto = LocationDto
      .builder()
      .locationId(1L)
      .englishForm("location")
      .latitude(1.0)
      .longitude(1.0)
      .build();
    CreateAttestationRequest createAttestationRequest = new CreateAttestationRequest(
      expectedOriginalForm
      , 1L
      , null);
    Attestation attestation = AttestationConversionHelper.createAttestationRequestToAttestation(
      createAttestationRequest
      , locationDto);
    assertEquals(expectedOriginalForm, attestation.getOriginalForm());
    assertNull(attestation.getAttestationId());
    Location location = attestation.getLocation();
    assertNull(location.getLocalityType());
    assertEquals(locationDto.locationId(), location.getLocationId());
    assertEquals(locationDto.englishForm(), location.getModernLookupForm());
    assertEquals(locationDto.latitude(), location.getLatitude());
    assertEquals(locationDto.longitude(), location.getLongitude());
  }

  @Test
  void testAttestationToAttestationDtoAttestationNullNok() {
    assertThrows(NullPointerException.class
      , () ->
        AttestationConversionHelper.attestationToAttestationDto(
          null));
  }

  @Test
  void testAttestationToAttestationDtoNoLocationOk() {
    String expectedOriginalForm = "originalForm";
    Attestation attestation = Attestation
      .builder()
      .attestationId(1L)
      .location(null)
      .originalForm(expectedOriginalForm)
      .build();
    AttestationDto attestationDto = AttestationConversionHelper.attestationToAttestationDto(attestation);
    assertEquals(attestation.getAttestationId(), attestationDto.attestationId());
    assertEquals(attestation.getOriginalForm(), attestationDto.originalForm());
  }

  @Test
  void testAttestationToAttestationDtoWithLocationOk() {
    String expectedOriginalForm = "originalForm";
    Attestation attestation = Attestation
      .builder()
      .attestationId(1L)
      .location(Location
        .builder()
        .locationId(1L)
        .modernLookupForm("form")
        .longitude(12)
        .latitude(13)
        .build())
      .originalForm(expectedOriginalForm)
      .build();
    AttestationDto attestationDto = AttestationConversionHelper.attestationToAttestationDto(attestation);
    assertEquals(attestation.getAttestationId(), attestationDto.attestationId());
    assertEquals(attestation.getOriginalForm(), attestationDto.originalForm());
    assertNotNull(attestationDto.location());
    assertEquals(attestation.getLocation().getLocationId(), attestationDto.location().locationId());
  }
}