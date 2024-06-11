package org.spring.hackathon.plogging.entity;

import lombok.*;
import org.spring.hackathon.baseTime.BaseEntity;
import org.spring.hackathon.users.entity.MemberEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ploggingPartyReply")
public class PloggingPartyReplyEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "replyNo")
    private Long replyNo;

    //댓글 내용
    @Column(nullable = false)
    private String replyContent;

    //댓글 작성자 매핑
    @ManyToOne
    @JoinColumn(name = "member_pk")
    private MemberEntity replyWriter;

    //댓글이 작성된 단체방 매핑
    @ManyToOne
    @JoinColumn(name = "party_pk")
    private PloggingPartyEntity ploggingParty;

}
