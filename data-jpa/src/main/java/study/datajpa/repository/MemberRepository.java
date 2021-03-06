package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member , Long> , MemberRepositoryCustom {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);


    @Query("select m from Member  m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username , @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id , m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where  m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);


//    List<Member>  findByListMember(String username); //  컬렉션
//    List<Member>  findByUserListMember(String username); // 단건
//    Optional<Member> findMemberByListMember(String username); // 단건 optional

    Page<Member> findByAge(int age , Pageable pageable);


//    @Modifying
//    @Query("update Member m  set m.age =  +1 where  m.age >= :age" )
//    int bulkAgePlus(@Param("age") int age);

    @Query("select m from  Member  m left join fetch m.team")
    List<Member> findByMemberFetchJoin();


    @Override
    @EntityGraph(attributePaths = ("team"))
    List<Member> findAll();


//    @Query("select m from  Member  m")
//    @EntityGraph(attributePaths = ("team"))
//    List<Member> findMemberEntityGraph();

}
