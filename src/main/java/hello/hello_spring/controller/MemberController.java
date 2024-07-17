package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MemberController {
    //private final MemberService memberService=new MemberService();
    //이렇게 스프링이 관리하게 되면 다 스프링 컨테인에 등록하고 컨테이너에서 받아와서 써야함.
    //멤버컨트롤러 말고 다른 컨트롤러도 멤버서비스를 가져다 쓸수 있음. 근데 여러개 생성할 필요가 없다. 하나만 생성해서 같이쓰면됨
    //이렇게 new로 생성하는것보다, 스프링 컨테이너에 하나만 등록하고 쓰기

    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //생성자에 @Autowired라고 되어있으면 스프링이 컨테이너에 있는 멤버서비스하고 연결시켜줌.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")   //url은 똑같지만 메서드가 get이냐 post에 따라 다르게 맵핑
    public String create(MemberForm form){
        Member member=new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
