# Swedifying-the-other
Place-name database for the project *Swedifying the other* https://www.vr.se/english/swecris.html#/project/P20-0105_RJ

## Overview
The application consists of an SQL database holding place-name data as needed in the project *Swedifying the other* and a user interface to allow the user to interact with the database. The database exposed is exposed with a REST API through a web application (Java/Spring Boot), accesible to the user via a web browser user interface for adding data to and searching/filtering the database (HTML/JavaScript).

## Database diagram
Below is an Entity Relationship Diagram of the database.

![er-diagram](./docs/img/swedifying-the-other-erd-detailed.png)

## TODO as of 2023-02-26
Currently, the following tasks is left to do for a more complete version of the application:

- Finish the user interface for adding/editing/browsing the entities. Most are done, a couple still remain.
  - ~~Attestation~~
  - ~~Map Source~~
  - ~~Normalized Form~~
  - ~~Parish~~
  - ~~Precinct~~
  - ~~Source Finding~~
  - ~~Text Source~~
  - ~~Variant Form~~
- Improve the *Attestations* view, for creating/editing and searching/filtering. As this is the "main" view of the application, the presentation of the data and the searching/filtering needs to be improved here.
- ~~Connect to and store the data in a "real" database (as opposed to the volatile in memory database used during development), e.g. PostgreSQL or similar.~~
- ~~Add installation/setup instructions (and possibly scripts/tools) to set up and start using the database and application.~~
- Add documentation of the database and application to facilitate continued work
- (*Good to have:* Add script/tool for adding the data already collected to the database automatically, i.e. a tool reading a csv file with attestations and adding them to the database using the API.)

## Example output from GET /api/v1/attestations?projection=attestationView

Shown below is an example of the data returned when calling GET http://localhost:8080/api/attestations?projection=attestationView. This will show all attestations in a "flat" (and linked) structure (using the query parameter ```projection=attestationView```, without projection query parameter the attestations would be shown only with links to the database entities it depends on/builds upon).

```
{
  "_embedded" : {
    "attestations" : [ {
      "location" : {
        "latitude" : 0.0,
        "localityType" : {
          "localityTypeId" : 7,
          "localityTypeName" : "bebyggelse, by",
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/api/localityTypes/7{?projection}",
              "templated" : true
            }
          }
        },
        "longitude" : 0.0,
        "locationId" : 27,
        "districtOrParish" : {
          "name" : "Wolgasts distrikt",
          "type" : "District",
          "belongsToRegion" : {
            "regionId" : 10,
            "regionName" : "Pommern",
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/regions/10{?projection}",
                "templated" : true
              }
            }
          },
          "subRegionId" : 13,
          "_links" : {
            "belongsToRegion" : {
              "href" : "http://localhost:8080/api/districts/13/belongsToRegion{?projection}",
              "templated" : true
            },
            "self" : {
              "href" : "http://localhost:8080/api/districts/13{?projection}",
              "templated" : true
            }
          }
        },
        "modernLookupForm" : "Gross Ernsthof",
        "_links" : {
          "districtOrParish" : {
            "href" : "http://localhost:8080/api/locations/27/districtOrParish{?projection}",
            "templated" : true
          },
          "localityType" : {
            "href" : "http://localhost:8080/api/locations/27/localityType{?projection}",
            "templated" : true
          },
          "self" : {
            "href" : "http://localhost:8080/api/locations/27{?projection}",
            "templated" : true
          }
        }
      },
      "attestationId" : 36,
      "originalForm" : "Grosse Ernshoff[s] Åkermark Uthi Wolgasts District",
      "variantForm" : {
        "normalizedForm" : {
          "normalizedForm" : "Grosse Ernsthoff",
          "etymology" : {
            "languageId" : 4,
            "languageName" : "German",
            "languageCode" : "de",
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/languages/4{?projection}",
                "templated" : true
              }
            }
          },
          "normalizedFormId" : 30,
          "morphologicalNameType" : "COMPOSITION",
          "mediatingLanguage" : null,
          "morphologicalData" : {
            "determinationClauseInComposition" : "Ernst",
            "determinationClauseInPhrase" : null,
            "simpleRootMorpheme" : null,
            "mainClauseInPhrase" : null,
            "mainClauseInComposition" : "hof",
            "jointMorphemeInComposition" : null,
            "derivationMorpheme" : null,
            "derivationBase" : null
          },
          "morphologicalNameTypeIsShaky" : false,
          "comparativeFormInformation" : "1618 Ernsthoff, Lubinsche Karte",
          "_links" : {
            "mediatingLanguage" : {
              "href" : "http://localhost:8080/api/normalizedForms/30/mediatingLanguage{?projection}",
              "templated" : true
            },
            "etymology" : {
              "href" : "http://localhost:8080/api/normalizedForms/30/etymology{?projection}",
              "templated" : true
            },
            "self" : {
              "href" : "http://localhost:8080/api/normalizedForms/30{?projection}",
              "templated" : true
            }
          }
        },
        "variantForm" : "Grosse Ernsthoff",
        "variantFormId" : 33,
        "adaptationTypes" : [ ],
        "isAdaptedToSwedish" : "NO",
        "_links" : {
          "adaptationTypes" : {
            "href" : "http://localhost:8080/api/variantForms/33/adaptationTypes{?projection}",
            "templated" : true
          },
          "normalizedForm" : {
            "href" : "http://localhost:8080/api/variantForms/33/normalizedForm{?projection}",
            "templated" : true
          },
          "self" : {
            "href" : "http://localhost:8080/api/variantForms/33{?projection}",
            "templated" : true
          }
        }
      },
      "sourceFinding" : {
        "source" : {
          "type" : "MapSource",
          "dating" : "1694-06-19",
          "sourceId" : 24,
          "landSurveyor" : {
            "name" : "Simon Skragge",
            "landSurveyorId" : 16,
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/landSurveyors/16{?projection}",
                "templated" : true
              }
            }
          },
          "_links" : {
            "landSurveyor" : {
              "href" : "http://localhost:8080/api/mapSources/24/landSurveyor{?projection}",
              "templated" : true
            },
            "mapSignature" : {
              "href" : "http://localhost:8080/api/mapSources/24/mapSignature"
            },
            "self" : {
              "href" : "http://localhost:8080/api/mapSources/24{?projection}",
              "templated" : true
            }
          }
        },
        "sourceFindingId" : 25,
        "partOfSource" : {
          "partOfSourceName" : "Rubrik",
          "partOfSourceId" : 19,
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/api/partOfSources/19{?projection}",
              "templated" : true
            }
          }
        },
        "_links" : {
          "partOfSource" : {
            "href" : "http://localhost:8080/api/sourceFindings/25/partOfSource{?projection}",
            "templated" : true
          },
          "source" : {
            "href" : "http://localhost:8080/api/sourceFindings/25/source{?projection}",
            "templated" : true
          },
          "self" : {
            "href" : "http://localhost:8080/api/sourceFindings/25{?projection}",
            "templated" : true
          }
        }
      },
      "notes" : "Niemeyer 2:32f.",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/attestations/36"
        },
        "attestation" : {
          "href" : "http://localhost:8080/api/attestations/36{?projection}",
          "templated" : true
        },
        "variantForm" : {
          "href" : "http://localhost:8080/api/attestations/36/variantForm{?projection}",
          "templated" : true
        },
        "location" : {
          "href" : "http://localhost:8080/api/attestations/36/location{?projection}",
          "templated" : true
        },
        "sourceFinding" : {
          "href" : "http://localhost:8080/api/attestations/36/sourceFinding{?projection}",
          "templated" : true
        }
      }
    }, {
      "location" : {
        "latitude" : 0.0,
        "localityType" : {
          "localityTypeId" : 8,
          "localityTypeName" : "distrikt",
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/api/localityTypes/8{?projection}",
              "templated" : true
            }
          }
        },
        "longitude" : 0.0,
        "locationId" : 28,
        "districtOrParish" : {
          "name" : "Wolgasts distrikt",
          "type" : "District",
          "belongsToRegion" : {
            "regionId" : 10,
            "regionName" : "Pommern",
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/regions/10{?projection}",
                "templated" : true
              }
            }
          },
          "subRegionId" : 13,
          "_links" : {
            "belongsToRegion" : {
              "href" : "http://localhost:8080/api/districts/13/belongsToRegion{?projection}",
              "templated" : true
            },
            "self" : {
              "href" : "http://localhost:8080/api/districts/13{?projection}",
              "templated" : true
            }
          }
        },
        "modernLookupForm" : "",
        "_links" : {
          "districtOrParish" : {
            "href" : "http://localhost:8080/api/locations/28/districtOrParish{?projection}",
            "templated" : true
          },
          "localityType" : {
            "href" : "http://localhost:8080/api/locations/28/localityType{?projection}",
            "templated" : true
          },
          "self" : {
            "href" : "http://localhost:8080/api/locations/28{?projection}",
            "templated" : true
          }
        }
      },
      "attestationId" : 37,
      "originalForm" : "Grosse Ernshoff[s] Åkermark Uthi Wolgasts District",
      "variantForm" : {
        "normalizedForm" : {
          "normalizedForm" : "Wolgasts distrikt",
          "etymology" : {
            "languageId" : 4,
            "languageName" : "German",
            "languageCode" : "de",
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/languages/4{?projection}",
                "templated" : true
              }
            }
          },
          "normalizedFormId" : 31,
          "morphologicalNameType" : "PHRASE",
          "mediatingLanguage" : null,
          "morphologicalData" : {
            "determinationClauseInComposition" : null,
            "determinationClauseInPhrase" : "Wolgasts",
            "simpleRootMorpheme" : null,
            "mainClauseInPhrase" : "distrikt",
            "mainClauseInComposition" : null,
            "jointMorphemeInComposition" : null,
            "derivationMorpheme" : null,
            "derivationBase" : null
          },
          "morphologicalNameTypeIsShaky" : false,
          "comparativeFormInformation" : "Wolgastischer District, Amte Wolgast. Kahldensche Matrikel",
          "_links" : {
            "mediatingLanguage" : {
              "href" : "http://localhost:8080/api/normalizedForms/31/mediatingLanguage{?projection}",
              "templated" : true
            },
            "etymology" : {
              "href" : "http://localhost:8080/api/normalizedForms/31/etymology{?projection}",
              "templated" : true
            },
            "self" : {
              "href" : "http://localhost:8080/api/normalizedForms/31{?projection}",
              "templated" : true
            }
          }
        },
        "variantForm" : "Wolgasts distrikt",
        "variantFormId" : 34,
        "adaptationTypes" : [ {
          "name" : "morfologisk anpassning av förleden",
          "adaptationTypeId" : 17,
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/api/adaptationTypes/17{?projection}",
              "templated" : true
            }
          }
        } ],
        "isAdaptedToSwedish" : "YES",
        "_links" : {
          "adaptationTypes" : {
            "href" : "http://localhost:8080/api/variantForms/34/adaptationTypes{?projection}",
            "templated" : true
          },
          "normalizedForm" : {
            "href" : "http://localhost:8080/api/variantForms/34/normalizedForm{?projection}",
            "templated" : true
          },
          "self" : {
            "href" : "http://localhost:8080/api/variantForms/34{?projection}",
            "templated" : true
          }
        }
      },
      "sourceFinding" : {
        "source" : {
          "type" : "MapSource",
          "dating" : "1694-06-19",
          "sourceId" : 24,
          "landSurveyor" : {
            "name" : "Simon Skragge",
            "landSurveyorId" : 16,
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/landSurveyors/16{?projection}",
                "templated" : true
              }
            }
          },
          "_links" : {
            "landSurveyor" : {
              "href" : "http://localhost:8080/api/mapSources/24/landSurveyor{?projection}",
              "templated" : true
            },
            "mapSignature" : {
              "href" : "http://localhost:8080/api/mapSources/24/mapSignature"
            },
            "self" : {
              "href" : "http://localhost:8080/api/mapSources/24{?projection}",
              "templated" : true
            }
          }
        },
        "sourceFindingId" : 25,
        "partOfSource" : {
          "partOfSourceName" : "Rubrik",
          "partOfSourceId" : 19,
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/api/partOfSources/19{?projection}",
              "templated" : true
            }
          }
        },
        "_links" : {
          "partOfSource" : {
            "href" : "http://localhost:8080/api/sourceFindings/25/partOfSource{?projection}",
            "templated" : true
          },
          "source" : {
            "href" : "http://localhost:8080/api/sourceFindings/25/source{?projection}",
            "templated" : true
          },
          "self" : {
            "href" : "http://localhost:8080/api/sourceFindings/25{?projection}",
            "templated" : true
          }
        }
      },
      "notes" : "Distriktsnamnet är anpassat till sv dock ej bebyggelsenamnet Wolgast som har slavisk etymologi och som medierats via tyska. I notarum står inte sällan \"Wolgasts ampt\".",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/attestations/37"
        },
        "attestation" : {
          "href" : "http://localhost:8080/api/attestations/37{?projection}",
          "templated" : true
        },
        "variantForm" : {
          "href" : "http://localhost:8080/api/attestations/37/variantForm{?projection}",
          "templated" : true
        },
        "location" : {
          "href" : "http://localhost:8080/api/attestations/37/location{?projection}",
          "templated" : true
        },
        "sourceFinding" : {
          "href" : "http://localhost:8080/api/attestations/37/sourceFinding{?projection}",
          "templated" : true
        }
      }
    }, {
      "location" : {
        "latitude" : 0.0,
        "localityType" : {
          "localityTypeId" : 9,
          "localityTypeName" : "bebyggelse, stad",
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/api/localityTypes/9{?projection}",
              "templated" : true
            }
          }
        },
        "longitude" : 0.0,
        "locationId" : 29,
        "districtOrParish" : {
          "name" : "Wolgasts distrikt",
          "type" : "District",
          "belongsToRegion" : {
            "regionId" : 10,
            "regionName" : "Pommern",
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/regions/10{?projection}",
                "templated" : true
              }
            }
          },
          "subRegionId" : 13,
          "_links" : {
            "belongsToRegion" : {
              "href" : "http://localhost:8080/api/districts/13/belongsToRegion{?projection}",
              "templated" : true
            },
            "self" : {
              "href" : "http://localhost:8080/api/districts/13{?projection}",
              "templated" : true
            }
          }
        },
        "modernLookupForm" : "Wolgast",
        "_links" : {
          "districtOrParish" : {
            "href" : "http://localhost:8080/api/locations/29/districtOrParish{?projection}",
            "templated" : true
          },
          "localityType" : {
            "href" : "http://localhost:8080/api/locations/29/localityType{?projection}",
            "templated" : true
          },
          "self" : {
            "href" : "http://localhost:8080/api/locations/29{?projection}",
            "templated" : true
          }
        }
      },
      "attestationId" : 38,
      "originalForm" : "Wolgasts Stadz Grentz",
      "variantForm" : {
        "normalizedForm" : {
          "normalizedForm" : "Wolgast",
          "etymology" : {
            "languageId" : 6,
            "languageName" : "Slavic",
            "languageCode" : "slav",
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/languages/6{?projection}",
                "templated" : true
              }
            }
          },
          "normalizedFormId" : 32,
          "morphologicalNameType" : "DERIVATION",
          "mediatingLanguage" : {
            "languageId" : 4,
            "languageName" : "German",
            "languageCode" : "de",
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/languages/4{?projection}",
                "templated" : true
              }
            }
          },
          "morphologicalData" : {
            "determinationClauseInComposition" : null,
            "determinationClauseInPhrase" : null,
            "simpleRootMorpheme" : null,
            "mainClauseInPhrase" : null,
            "mainClauseInComposition" : null,
            "jointMorphemeInComposition" : null,
            "derivationMorpheme" : "j",
            "derivationBase" : "Voligost"
          },
          "morphologicalNameTypeIsShaky" : false,
          "comparativeFormInformation" : "1140 castra hec, scilicet … Wologost : Wolgast; 1186 usque Wolegost et a Wolegost. PUB 1:33, 130",
          "_links" : {
            "mediatingLanguage" : {
              "href" : "http://localhost:8080/api/normalizedForms/32/mediatingLanguage{?projection}",
              "templated" : true
            },
            "etymology" : {
              "href" : "http://localhost:8080/api/normalizedForms/32/etymology{?projection}",
              "templated" : true
            },
            "self" : {
              "href" : "http://localhost:8080/api/normalizedForms/32{?projection}",
              "templated" : true
            }
          }
        },
        "variantForm" : "Wolgast",
        "variantFormId" : 35,
        "adaptationTypes" : [ ],
        "isAdaptedToSwedish" : "NO",
        "_links" : {
          "adaptationTypes" : {
            "href" : "http://localhost:8080/api/variantForms/35/adaptationTypes{?projection}",
            "templated" : true
          },
          "normalizedForm" : {
            "href" : "http://localhost:8080/api/variantForms/35/normalizedForm{?projection}",
            "templated" : true
          },
          "self" : {
            "href" : "http://localhost:8080/api/variantForms/35{?projection}",
            "templated" : true
          }
        }
      },
      "sourceFinding" : {
        "source" : {
          "type" : "MapSource",
          "dating" : "1694-06-19",
          "sourceId" : 24,
          "landSurveyor" : {
            "name" : "Simon Skragge",
            "landSurveyorId" : 16,
            "_links" : {
              "self" : {
                "href" : "http://localhost:8080/api/landSurveyors/16{?projection}",
                "templated" : true
              }
            }
          },
          "_links" : {
            "landSurveyor" : {
              "href" : "http://localhost:8080/api/mapSources/24/landSurveyor{?projection}",
              "templated" : true
            },
            "mapSignature" : {
              "href" : "http://localhost:8080/api/mapSources/24/mapSignature"
            },
            "self" : {
              "href" : "http://localhost:8080/api/mapSources/24{?projection}",
              "templated" : true
            }
          }
        },
        "sourceFindingId" : 26,
        "partOfSource" : {
          "partOfSourceName" : "Gränsmarkering",
          "partOfSourceId" : 21,
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/api/partOfSources/21{?projection}",
              "templated" : true
            }
          }
        },
        "_links" : {
          "partOfSource" : {
            "href" : "http://localhost:8080/api/sourceFindings/26/partOfSource{?projection}",
            "templated" : true
          },
          "source" : {
            "href" : "http://localhost:8080/api/sourceFindings/26/source{?projection}",
            "templated" : true
          },
          "self" : {
            "href" : "http://localhost:8080/api/sourceFindings/26{?projection}",
            "templated" : true
          }
        }
      },
      "notes" : "Eichler & Walther 1988:300",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/attestations/38"
        },
        "attestation" : {
          "href" : "http://localhost:8080/api/attestations/38{?projection}",
          "templated" : true
        },
        "variantForm" : {
          "href" : "http://localhost:8080/api/attestations/38/variantForm{?projection}",
          "templated" : true
        },
        "location" : {
          "href" : "http://localhost:8080/api/attestations/38/location{?projection}",
          "templated" : true
        },
        "sourceFinding" : {
          "href" : "http://localhost:8080/api/attestations/38/sourceFinding{?projection}",
          "templated" : true
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/attestations?projection=attestationView"
    },
    "profile" : {
      "href" : "http://localhost:8080/api/profile/attestations"
    },
    "search" : {
      "href" : "http://localhost:8080/api/attestations/search"
    }
  }
}
```
