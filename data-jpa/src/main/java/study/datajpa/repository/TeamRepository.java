package study.datajpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team , Long> {

    List<Team> findByTeem(String team);
}
