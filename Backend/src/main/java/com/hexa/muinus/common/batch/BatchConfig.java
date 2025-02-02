package com.hexa.muinus.common.batch;

import com.hexa.muinus.store.batch.TransactionToDailySalesProcessor;
import com.hexa.muinus.store.batch.DailySalesItemReader;
import com.hexa.muinus.store.batch.DailySalesItemWriter;
import com.hexa.muinus.store.domain.transaction.TransactionDetails;
import com.hexa.muinus.store.domain.transaction.DailySales;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig extends DefaultBatchConfiguration {

    @Bean
    public Job testJob(JobRepository jobRepository,PlatformTransactionManager transactionManager) throws DuplicateJobException {
        Job job = new JobBuilder("testJob",jobRepository)
                .start(testStep(jobRepository,transactionManager))
                .build();
        return job;
    }

    public Step testStep(JobRepository jobRepository,PlatformTransactionManager transactionManager){
        Step step = new StepBuilder("testStep",jobRepository)
                .tasklet(testTasklet(),transactionManager)
                .build();
        return step;
    }

    public Tasklet testTasklet(){
        return ((contribution, chunkContext) -> {
            System.out.println("***** hello batch! *****");
            // 원하는 비지니스 로직 작성
            return RepeatStatus.FINISHED;
        });
    }

    // Job 설정
//    @Bean
//    public Job dailySalesJob(JobRepository jobRepository, Step dailySalesStep) {
//        return new JobBuilder("dailySalesJob", jobRepository)
//                .start(dailySalesStep)
//                .build();
//    }
//
//    // Step 설정
//    @Bean
//    public Step dailySalesStep(JobRepository jobRepository,
//                               DailySalesItemReader itemReader,
//                               TransactionToDailySalesProcessor itemProcessor,
//                               DailySalesItemWriter itemWriter) {
//        return new StepBuilder("dailySalesStep", jobRepository)
//                .<TransactionDetails, DailySales>chunk(10)  // chunk 크기 설정
//                .reader(itemReader)
//                .processor(itemProcessor)
//                .writer(itemWriter)
//                .build();
//    }
//
//    @Bean
//    public Tasklet dailySalesTasklet() {
//        return (contribution, chunkContext) -> {
//            System.out.println("\n *********** daily sales batch ***********");
//            return RepeatStatus.FINISHED;
//        };
//    }
}