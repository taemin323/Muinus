package com.hexa.muinus.store.batch;

import com.hexa.muinus.store.domain.item.Item;
import com.hexa.muinus.store.domain.item.StoreItem;
import com.hexa.muinus.store.domain.item.repository.StoreItemRepository;
import com.hexa.muinus.store.domain.store.Store;
import com.hexa.muinus.store.domain.transaction.DailySales;
import com.hexa.muinus.store.domain.transaction.TransactionDetails;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DailySalesItemReader extends JdbcCursorItemReader<DailySales> {

    private final StoreItemRepository storeItemRepository;

    private final static String query
            = "SELECT tt.store_no, tt.item_id, SUM(tt.quantity) AS total_quantity, SUM(tt.sub_total) AS total_amount "
            + "FROM ((  SELECT  si.store_no, si.item_id, IFNULL(td.quantity, 0) AS quantity , IFNULL(td.sub_total, 0) AS sub_total "
            + "         FROM transaction_details td "
            + "         JOIN transactions t ON td.transaction_id = t.transaction_id "
            + "         AND t.created_at >= CURDATE() - INTERVAL 1 DAY "
            + "         AND tt.created_at < CURDATE() "
            + "         RIGHT JOIN store_item si ON td.store_item_id = si.store_item_id "
            + "         JOIN item i ON si.item_id = i.item_id "
            + "     ) UNION "
            + "     (   SELECT  si.store_no, si.item_id, IFNULL(td.quantity, 0) AS quantity , IFNULL(td.sub_total, 0) AS sub_total "
            + "         FROM guest_transaction_details td "
            + "         JOIN guest_transactions t ON td.transaction_id = t.transaction_id "
            + "         AND t.created_at >= CURDATE() - INTERVAL 1 DAY "
            + "         AND tt.created_at < CURDATE() "
            + "         RIGHT JOIN store_item si ON td.store_item_id = si.store_item_id "
            + "         JOIN item i ON si.item_id = i.item_id "
            + "         )) tt"
            + "         GROUP BY tt.store_no, tt.item_id "
            + "         ORDER BY tt.store_no, tt.item_id "
            ;


    public DailySalesItemReader(DataSource dataSource, StoreItemRepository storeItemRepository) {
        this.storeItemRepository = storeItemRepository;

        setDataSource(dataSource);
        setSql(query);

        // RowMapper 설정
        setRowMapper(new RowMapper<DailySales>() {
            @Override
            public DailySales mapRow(ResultSet rs, int rowNum) throws SQLException {
                // store_item_id로 StoreItem 객체 찾기
                StoreItem storeItem = storeItemRepository.findById(rs.getInt("store_item_id")).orElse(null);

                // StoreItem을 통해 Store와 Item 정보 가져오기
                Store store = storeItem != null ? storeItem.getStore() : null;
                Item item = storeItem != null ? storeItem.getItem() : null;

                return DailySales.builder()
                        .item()
                        .build();
            }
        });
    }
}
