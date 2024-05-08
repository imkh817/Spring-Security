package org.example.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {

    @Id // @Id 애노테이션은 JPA 엔티티 객체의 식별자로 사용할 필드에 적용하며, 유니크한 DB의 컬럼과 맵핑
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /* 1) GenerationType.IDENTITY
       - 기본 키 생성을 데이터베이스에 위임한다.
       - 주로 MySQL, PostgreSQL, SQL Server, DB2에서 사용한다. (예: MySQL의 AUTO_ INCREMENT)
     2) GenerationType.SEQUENCE
       - DB의 시퀀스를 활용하여 Id값을 증가시킨다.
       - 데이터베이스 시퀀스는 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트(예: 오라클 시퀀스)
       - 오라클, PostgreSQL, DB2, H2 데이터베이스에서 사용한다.
     3) GenerationType.TABLE
       - 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉내내는 전략
       - 모든 데이터베이스에 적용 가능하나, 성능적인 손해가 있어서 잘 쓰지 않는다.*/
    private int id;

    private String username;
    private String password;

    private String role;

}
