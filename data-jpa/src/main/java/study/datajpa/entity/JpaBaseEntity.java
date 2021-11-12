package study.datajpa.entity;




import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;


@MappedSuperclass
public class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updatetime;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdDate =  now;
        updatetime = now;

    }
    @PrePersist
    public  void  preUpdate(){
        updatetime =  LocalDateTime.now();
    }
}
