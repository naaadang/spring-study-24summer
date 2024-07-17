package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * 서비스는 비즈니스 용어로 네이밍
 * 레포지토리는 단순히,기계적으로 용어 설계
 */

//Test 만드는 단축기 : 클래스누르고 Ctrl+Shift+T
//@Service
public class MemberService {

   // private final MemberRepository memberRepository=new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //memberRepository를 직접 생성하는게 아니라 외부에서 넣어주도록 바꿈
    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member){

        validateDuplicateMember(member);  //중복회원 검증
        //이런경우엔 메서드로 뽑는게 좋음. 단축기 : Ctrl + Shift+Alt+T
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m ->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });//findByName으로 반환되는게 어차피 Optional이니까 바로 isPresent써도됨

//        Optional<Member> result =  memberRepository.findByName(member.getName()); //단축기 Ctrl+Alt+V
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });  //ifPresent : null이 아니라 어떤 값이 있으면 동작함. Optional이기 때문에 가능
    }

    /***
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    /***
     * 특정 회원 1명 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
