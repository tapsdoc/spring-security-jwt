package com.example.Spring.SecurityAlibou.service.student;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCSVRepresentation {
    @CsvBindByName(column = "firstname")
    String firstName;
    @CsvBindByName(column = "lastname")
    String lastName;
    @CsvBindByName(column = "age")
    int age;
}
