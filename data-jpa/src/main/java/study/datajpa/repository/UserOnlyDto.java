package study.datajpa.repository;

import lombok.Getter;

@Getter
public class UserOnlyDto {


    private final String username;


    public UserOnlyDto(String username) {
        this.username = username;
    }
}
