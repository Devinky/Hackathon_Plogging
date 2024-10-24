package org.spring.hackathon.plogging.domain;

import lombok.*;
import org.spring.hackathon.baseTime.BaseEntity;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plogging_location")
public class PloggingLocationEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "plogging_location_no")
  private Long locationNo;

  @Column(nullable = false)
  private double latitude;

  @Column(nullable = false)
  private double longitude;

  @Column(nullable = false)
  private double distance;

  //플로깅 기록 테이블과 연관 매핑(N:1)
  //플로깅을 하는 유저는 일정 시간 마다 위치 좌표가 측정, 기록되며 기록된 모든 좌표는 하나의 플로깅 기록에 묶인다
  @ManyToOne
  @JoinColumn(name = "record_location_fk")
  private PloggingRecordEntity locationJoinRecord;

}
