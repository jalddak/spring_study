package hello.core.order;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        /*
        생성자 주입을 하면, 순수 자바 테스트 할 때에 따로 변경하거나 할 것 없이 의존관계가 명확히 보인다.
        하지만 수정자 (setter) 주입을 한다면 자바 테스트시에 수정자도 써줘야하고 생성자만으로 의존관계파악이 어렵다.
         */

        Member member = new Member(1L, "name", Grade.VIP);

        // 1. 스프링 컨테이너 가져와서 하는것은 생성자 주입이나 수정자 주입이나 다를게 없음
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        MemberService memberService = ac.getBean(MemberService.class);
//        OrderService orderService = ac.getBean(OrderService.class);
//        memberService.join(member);

        // 2. 하지만 순수 자바 코드로 테스트할 경우, 수정자 주입으로 설정할 경우엔, 생성자만 만들어서는, 필요한 멤버변수 객체가 null이라서 오류가남.
//        OrderServiceImpl orderService = new OrderServiceImpl();

        // 3. 생성자 주입으로 순수 자바 테스트를 한다면, 생성자만 해도 다 된다.
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(member);
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());

        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}