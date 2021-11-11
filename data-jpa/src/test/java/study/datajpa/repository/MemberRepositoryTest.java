package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;


@SpringBootTest
@Transactional
class MemberRepositoryTest {


    @Autowired MemberRepository memberRepository;
    @Autowired TeamJpaRepository teamJpaRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);


        //단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);


        //리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deleteCount = memberRepository.count();
        assertThat(deleteCount).isEqualTo(0);


    }
    @Test
    public void findByUsernameAndAgeGreaterThen(){

        Member m1 = new Member("AAA", 20);
        Member m2 = new Member("BBB", 10);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void testQuery(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 10);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA" , 10);

        assertThat(result.get(0)).isEqualTo(m1);


    }


    @Test
    public void findUsernameList(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();


        for (String s  : usernameList){
            System.out.println("==" +s);
        }

    }

    @Test
    public void findMemberDto(){
        Team team =  new Team("teamA");
        teamJpaRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<String> usernameList = memberRepository.findUsernameList();
        for (String s  : usernameList){
            System.out.println("==" +s);
        }

    }

    @Test
    public void findByNames(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> usernameList = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));


        for (Member s  : usernameList){
            System.out.println("==" +s);
        }


    }


    @Test
    public void paging(){

        Member m1  =  new Member("AAA" , 10);
        Member m2  =  new Member("BBB" , 10);

        memberRepository.save(m1);
        memberRepository.save(m2);

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "username");

        Page<Member> page = memberRepository.findByAge(age, pageRequest);


        List<Member> content = page.getContent();

        Page<MemberDto> toMap = page.map(m -> new MemberDto(m.getId(), m.getUsername(), null));


        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(3);
    }


    @Test
    public void  bulkUpdate(){

        memberRepository.save(new Member("mamber1"  , 10));
        memberRepository.save(new Member("mamber2"  , 20));
        memberRepository.save(new Member("mamber3"  , 30));
        memberRepository.save(new Member("mamber4"  , 40));
        memberRepository.save(new Member("mamber5"  , 50));

        int resultCount =  memberRepository.bulkAgePlus(20);


        assertThat(resultCount).isEqualTo(3);
    }


    @Test
    public void Membercall(){
        List<Member> result = memberRepository.findMemberCustom();
    }
}