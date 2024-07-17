package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//이전에 돌렸던걸 다시 돌릴때 Shift + f10
class MemberServiceTest {
    //MemberService memberService=new MemberService();
    //MemoryMemberRepository memberRepository=new MemoryMemberRepository();

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    } //각각 테스트를 실행하기 전에 생성.. DI


    @AfterEach   //각 메서드가 끝날때마다 동작
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {  //test는 한글로 적어도 무방.
        //given
        Member member=new Member();
        member.setName("spring");

        //when
        Long saveId=memberService.join(member);  //단축기 ctrl+Alt+v

        //then
        Member findMember=memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);  //선언된 곳으로 이동 : Ctrl+B  돌아오는거는 Ctrl+Shift+[
        //방법1
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //(A,B) 로직B를 실행할때 예외 A가 터져야한다는 뜻

        //방법2
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //방법3
//        try{
//            memberService.join(member2); //똑같은 라인 복붙 : Ctrl+D
//            fail("예외가 발생해야 합니다.");
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}