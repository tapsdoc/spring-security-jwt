package com.example.Spring.SecurityAlibou.controller;

import com.example.Spring.SecurityAlibou.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;
    private final JobLauncher jobLauncher;
    private final Job job;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> upload(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadStudents(file));
    }

    @PostMapping
    public ResponseEntity<?> importCsvToDBJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters parameters = new JobParametersBuilder()
            .addLong("startAt", System.currentTimeMillis())
            .toJobParameters();

        jobLauncher.run(job, parameters);
        return ResponseEntity.ok("Students successfully added");
    }
}
