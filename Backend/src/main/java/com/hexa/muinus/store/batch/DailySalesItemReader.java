package com.hexa.muinus.store.batch;

import com.hexa.muinus.store.domain.item.Item;
import com.hexa.muinus.store.domain.item.StoreItem;
import com.hexa.muinus.store.domain.item.repository.StoreItemRepository;
import com.hexa.muinus.store.domain.store.Store;
import com.hexa.muinus.store.domain.transaction.TransactionDetails;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DailySalesItemReader extends JdbcCursorItemReader<TransactionDetails> {

    private final StoreItemRepository storeItemRepository;

    public DailySalesItemReader(DataSource dataSource, StoreItemRepository storeItemRepository) {
        this.storeItemRepository = storeItemRepository;

        setDataSource(dataSource);
        // UNION으로 두 테이블의 데이터를 합침
        setSql("SELECT td.detail_id, td.transaction_id, td.store_item_id, td.quantity, td.unit_price " +
                "FROM transaction_details td " +
                "JOIN transactions t ON td.transaction_id = t.transaction_id " +
                "WHERE t.status = 'SUCCESS' " +
                "AND t.created_at >= CURDATE() - INTERVAL 1 DAY " +
                "AND t.created_at <= CURDATE() " +
                "UNION " +
                "SELECT gtd.detail_id, gtd.transaction_id, gtd.store_item_id, gtd.quantity, gtd.unit_price " +
                "FROM guest_transaction_details gtd " +
                "JOIN guest_transactions gt ON gtd.transaction_id = gt.transaction_id " +
                "WHERE gt.status = 'SUCCESS' " +
                "AND gt.created_at >= CURDATE() - INTERVAL 1 DAY " +
                "AND gt.created_at <= CURDATE()");

        // RowMapper 설정
        setRowMapper(new RowMapper<TransactionDetails>() {
            @Override
            public TransactionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                // store_item_id로 StoreItem 객체 찾기
                StoreItem storeItem = storeItemRepository.findById(rs.getInt("store_item_id")).orElse(null);

                // StoreItem을 통해 Store와 Item 정보 가져오기
                Store store = storeItem != null ? storeItem.getStore() : null;
                Item item = storeItem != null ? storeItem.getItem() : null;

                return TransactionDetails.builder()
                        .detailId(rs.getInt("detail_id"))
                        .storeItem(storeItem)
                        .quantity(rs.getInt("quantity"))
                        .unitPrice(rs.getInt("unit_price"))
                        .subTotal(rs.getInt("quantity") * rs.getInt("unit_price"))
                        .build();
            }
        });
    }
}
