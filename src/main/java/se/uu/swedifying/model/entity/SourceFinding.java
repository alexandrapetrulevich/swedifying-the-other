package se.uu.swedifying.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "SOURCE_FINDING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class SourceFinding {
  @Id
  @GeneratedValue
  @Column(name = "SOURCE_FINDING_ID")
  private Long sourceFindingId;

  @ManyToOne
  @JoinColumn(name = "SOURCE_ID")
  private Source source;

  @ManyToOne
  @JoinColumn(name = "PART_OF_SOURCE_TYPE_ID")
  private PartOfSource partOfSource;
}