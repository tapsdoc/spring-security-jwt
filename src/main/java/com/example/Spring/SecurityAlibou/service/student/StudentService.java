package com.example.Spring.SecurityAlibou.service.student;

import com.example.Spring.SecurityAlibou.domain.Student;
import com.example.Spring.SecurityAlibou.repository.StudentRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public Integer uploadStudents(MultipartFile file) throws IOException {
        Set<Student> students = parseCsv(file);
        studentRepository.saveAll(students);
        return students.size();
    }

    private Set<Student> parseCsv(MultipartFile file) throws IOException {

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            HeaderColumnNameMappingStrategy<StudentCSVRepresentation> strategy =
                new HeaderColumnNameMappingStrategy<>();
            strategy.setType(StudentCSVRepresentation.class);
            CsvToBean<StudentCSVRepresentation> csvToBean = new CsvToBeanBuilder<StudentCSVRepresentation>(reader)
                .withMappingStrategy(strategy)
                .withIgnoreEmptyLine(true)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

            return csvToBean.parse()
                .stream()
                .map(csvLine -> Student.builder()
                    .firstName(csvLine.getFirstName())
                    .lastName(csvLine.getLastName())
                    .age(csvLine.getAge())
                    .build()
                )
                .collect(Collectors.toSet());
        }
    }
}
