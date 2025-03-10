package org.spring.hackathon.member.constructor;

import org.spring.hackathon.member.domain.MemberEntity;
import org.spring.hackathon.member.domain.MemberImageEntity;
import org.springframework.web.multipart.MultipartFile;

public class MemberImageConstructor {

  public static MemberImageEntity memberImageTransfer(MultipartFile image, String newImageName, MemberEntity memberEntity) {

    MemberImageEntity memberImage = new MemberImageEntity();

    memberImage.setMemberImageNameOrigin(image.getOriginalFilename());
    memberImage.setMemberImageNameNew(newImageName);
    memberImage.setMemberJoinImage(memberEntity);

    return memberImage;

  }
}
