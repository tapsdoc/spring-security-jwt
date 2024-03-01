package com.example.Spring.SecurityAlibou.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Student extends BaseEntity {

    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        return "Student{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", age=" + age +
            "} " + super.toString();
    }
}
