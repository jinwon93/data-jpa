package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id" , "username" , "age"})
public class Member extends JpaBaseEntity {


    @Id @GeneratedValue
    @Column(name = "member_id")
    private  Long id;
    private String username;
    private  int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    teamprotected Member(){
//
//    }

    public Member(String username){
        this.username = username;
    }

    public Member(String username, int age) {
        this.username  = username;
        this.age = age;
    }


    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
