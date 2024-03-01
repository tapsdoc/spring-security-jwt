package com.example.Spring.SecurityAlibou.domain;

import com.example.Spring.SecurityAlibou.commons.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;
    private boolean expired;
    private boolean revoked;
    @ManyToOne
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token token1)) return false;
        return expired == token1.expired && revoked == token1.revoked && Objects.equals(id, token1.id) && Objects.equals(token, token1.token) && tokenType == token1.tokenType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, tokenType, expired, revoked);
    }
}
