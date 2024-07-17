package hello.hello_spring.repository;
import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
//구현체. MemberRepository인터페이스를 구현하는 구현체
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store=new HashMap<>();
    private static long sequence=0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);  //스토어에 넣기전에 member의 id값 세팅. 이미 이름은 넘어온 상태
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values가 member들임..
    }

    public void clearStore(){
        store.clear();
    }
}
