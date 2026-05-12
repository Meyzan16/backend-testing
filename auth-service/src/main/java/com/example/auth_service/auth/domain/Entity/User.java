package com.example.auth_service.auth.domain.Entity;

import com.example.auth_service.auth.domain.ValueObject.Email;
import com.example.auth_service.auth.domain.ValueObject.Password;
import com.example.auth_service.auth.domain.ValueObject.Uid;
import com.example.auth_service.auth.domain.ValueObject.UserRole;
import java.time.LocalDateTime;


public class User {

    private final Long id;
    private final Uid uid;
    private final Email email;
    private final Password password;
    private final String fullName;
    private UserRole role;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // private constract
    private User(Builder builder) {
        this.id = builder.id;
        this.uid = builder.uid;
        this.email = builder.email;
        this.password = builder.password;
        this.fullName = builder.fullName;
        this.role = builder.role;
        this.active = builder.active;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    // getters

    public Long getId() { return id; }
    public Uid getUid() { return uid; }
    public Email getEmail() { return email; }
    public Password getPassword() { return password; }
    public String getFullName() { return fullName; }
    public UserRole getRole() { return role; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // ---- EQUALITY BY IDENTITY (uid), bukan semua field ----

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return uid.equals(user.uid);
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }


    // ---- BUILDER PATTERN ----

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Uid uid;
        private Email email;
        private Password password;
        private String fullName;
        private UserRole role = UserRole.USER;
        private boolean active = true;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder uid(Uid uid) { this.uid = uid; return this; }
        public Builder email(Email email) { this.email = email; return this; }
        public Builder password(Password password) { this.password = password; return this; }
        public Builder fullName(String fullName) { this.fullName = fullName; return this; }
        public Builder role(UserRole role) { this.role = role; return this; }
        public Builder active(boolean active) { this.active = active; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public User build() {
            return new User(this);
        }
    }





}
