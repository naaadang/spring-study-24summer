package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository=new MemoryMemberRepository();
    @AfterEach   //각 메서드가 끝날때마다 동작
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member=new Member();
        member.setName("spring");

        repository.save(member);
        Member result=repository.findById(member.getId()).get();
        //System.out.println("result = "+ (result==member));
        Assertions.assertEquals(member,result); //둘이 똑같은지 확인  (Expected,Actual)
        assertThat(member).isEqualTo(result);  //static import하고나면 다음부터 바로 asserThat(..)일케 쓸수있음
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);  //변수 이름 한번에 바꾸기: shift + F6

        Member result=repository.findByName("spring1").get(); //get하면 optional을 까서 꺼냄

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}

//테스트는 순서 보장 안됨. 순서랑 상관없이 메서드별로 따로 동작하도록 설계해야함. 순서에 의존적으로 설계하면 절대안됨.