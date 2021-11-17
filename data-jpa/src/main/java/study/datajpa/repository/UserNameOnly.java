package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNameOnly{

    @Value("#{target.username + ' ' + target.age}")
    String getUsername();
}
