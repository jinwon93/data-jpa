package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id" , "username" , "age"})
public class Member {


    @Id @GeneratedValue
    @Column(name = "member_id")
    private  Long id;
    private String userName;
    private  int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;


//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    teamprotected Member(){
//
//    }

    public Member(String userName){
        this.userName = userName;
    }


    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
