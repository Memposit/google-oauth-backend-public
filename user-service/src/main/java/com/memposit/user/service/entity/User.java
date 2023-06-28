package com.memposit.user.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.memposit.user.service.enums.Provider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * The User entity.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users",uniqueConstraints = { @UniqueConstraint(columnNames = { "email"}) })
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name",nullable = false)
    private String name;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "emailVerified" ,nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "provider" ,nullable = false)
    private Provider provider;

    private String providerId;

    private boolean enabled;

    private String stipeAccountId;

    private String stripeCustomerId;

    /**
     * The Created date.
     */
    @Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    /**
     * The Modified date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnore
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
