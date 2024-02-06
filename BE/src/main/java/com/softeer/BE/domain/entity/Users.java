package com.softeer.BE.domain.entity;

import com.softeer.BE.domain.entity.enums.License;
import com.softeer.BE.domain.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "user")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseEntity{
    @Id
    private String id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private License license;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> commentList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Participation> participationList;
}
