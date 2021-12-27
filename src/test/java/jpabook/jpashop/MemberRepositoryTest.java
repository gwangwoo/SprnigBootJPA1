//package jpabook.jpashop;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.*;
//
//@SpringBootTest
//public class MemberRepositoryTest {
//
//  @Autowired
//  MemberRepository memberRepository;
//
//  @Test
//  @DisplayName("test Member")
//  @Transactional
//  @Rollback(value = false)
//  public void testMember() throws Exception {
//    //given
//    Member member = new Member();
//    member.setUsername("memberA");
//    //when
//    Long saveId = memberRepository.save(member);
//    Member findMember = memberRepository.find(saveId);
//    //then
//    assertThat(findMember.getId()).isEqualTo(member.getId());
//    assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//
//  }
//
//}