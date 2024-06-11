package org.spring.hackathon.plogging.entity;

import lombok.*;
import org.spring.hackathon.baseTime.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ploggingPartyReply")
public class PloggingPartyReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "replyNo")
    private Long replyNo;

    @Column(nullable = false)
    private String replyContent;


}
