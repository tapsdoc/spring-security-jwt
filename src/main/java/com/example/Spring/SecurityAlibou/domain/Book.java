package com.example.Spring.SecurityAlibou.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Book extends BaseEntity  {

    private String title;
    private String author;
    private LocalDate published;

    @Override
    public String toString() {
        return "Book{" +
            "title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", published=" + published +
            "} " + super.toString();
    }
}
