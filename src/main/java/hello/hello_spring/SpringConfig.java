package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean //스프링 빈을 직접 등록
    public MemberService memberService(){
        //return new MemberService();
        //생성자에 memberRepository 넣어줘야함 -> memberRepository도 빈에 등록
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
        //memberRepository는 인터페이스라서 new로 생성안됨
        //구현체인 MemoryMemberService 리턴
    }
}
