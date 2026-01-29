package com.example.registerspringjpa.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@SequenceGenerator(name = "JPAMember_SEQ_GEN",
sequenceName = "JPAMember_SEQ",
        initialValue = 1,
        allocationSize = 1)
@Table(name = "JPAMember")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "JPABOARD_SEQ_GEN")
    @Column(name = "NO")
    private long no;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private int age;


}
