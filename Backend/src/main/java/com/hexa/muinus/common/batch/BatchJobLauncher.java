//package com.hexa.muinus.common.batch;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BatchJobLauncher implements CommandLineRunner {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job dailySalesJob;
//
//    @Override
//    public void run(String... args) throws Exception {
//        jobLauncher.run(dailySalesJob, new org.springframework.batch.core.JobParameters());
//    }
//}