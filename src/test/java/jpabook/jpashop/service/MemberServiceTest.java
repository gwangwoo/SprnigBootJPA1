package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Test
  public void 회원가입() throws Exception {
    //given
    Member member = new Member();
    member.setName("song");

    //when
    Long savedId = memberService.join(member);

    //then
    assertThat(savedId).isEqualTo(memberRepository.findOne(savedId).getId());

  }


  @Test
  public void 중복_회원_예외() throws Exception {
    //given
    Member member1 = new Member();
    member1.setName("song");

    Member member2 = new Member();
    member2.setName("song");

    //when
    memberService.join(member1);

    //then
    IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    assertEquals("이미 존재하는 회원입니다." , thrown.getMessage());
  }

}