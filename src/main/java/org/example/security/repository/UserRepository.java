package org.example.security.repository;

import org.example.security.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // 인터페이스로 만들고 JpaRepository를 상속받으면된다.
    // 이 때 내가 사용할 Entity와 기본 키의 타입을 정의해주면 된다.
    boolean existsByUsername(String UserName); //existsBy 메소드를 통해 내가 원하는 필드를 넣고 중복검사 가능

}
