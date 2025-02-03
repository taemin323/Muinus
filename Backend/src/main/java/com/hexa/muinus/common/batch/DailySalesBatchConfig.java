package com.hexa.muinus.common.batch;

import com.hexa.muinus.store.domain.transaction.DailySales;
import com.hexa.muinus.store.domain.transaction.repository.DailySalesRepository;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DailySalesBatchConfig {

    private final JobRepository jobRepository;

    @Qualifier("metaTransactionManager")
    private final PlatformTransactionManager platformTransactionManager;

    @Qualifier("metaDBSource")
    private final DataSource metaDBSource;

    @Qualifier("dataDBSoucre")
    private final DataSource dataDBSource;

    @Qualifier("dataEntityManager")
    private final EntityManagerFactory dataEntityManagerFactory;

    private final DailySalesRepository dailySalesRepository;

    public DailySalesBatchConfig(JobRepository jobRepository,
                                 PlatformTransactionManager platformTransactionManager,
                                 DataSource metaDBSource,
                                 DataSource dataDBSource,
                                 EntityManagerFactory dataEntityManagerFactory,
                                 DailySalesRepository dailySalesRepository) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
        this.metaDBSource = metaDBSource;
        this.dataDBSource = dataDBSource;
        this.dataEntityManagerFactory = dataEntityManagerFactory;
        this.dailySalesRepository = dailySalesRepository;
    }

    @Bean
    public Job DailySalesBatchJob(){
        log.info("Daily Sales Batch Job");
        return new JobBuilder("DailySalesBatchJob", jobRepository)
                .start(dailySalesStep())
                .build();
    }

    @Bean
    public Step dailySalesStep() {
        log.info("Daily Sales Step");
        return new StepBuilder("dailySalesStep", jobRepository)
                .<DailySales, DailySales> chunk(10, platformTransactionManager)
                .allowStartIfComplete(true)
                .reader(dailySalesReader(dataDBSource))
                .writer(dailySalesWriter(dataDBSource))
                .build();
    }

    @Bean
    public DailySalesItemReader dailySalesReader(@Qualifier("dataDBSource") DataSource dataDBSource) {
        return new DailySalesItemReader(dataDBSource);
    }

    @Bean
    public JdbcBatchItemWriter<DailySales> dailySalesWriter(@Qualifier("dataDBSource") DataSource dataDBSource) {
        return new JdbcBatchItemWriterBuilder<DailySales>()
                .dataSource(dataDBSource)
                .sql("""
                    INSERT INTO hexa.daily_sales (sale_date, store_no, item_id, total_quantity, total_amount) 
                    VALUES (:saleDate, :storeNo, :itemId, :totalQuantity, :totalAmount) 
                    ON DUPLICATE KEY UPDATE
                    total_quantity = VALUES(total_quantity),
                    total_amount = VALUES(total_amount)
                    """)
                .beanMapped()
                .build();
    }

}