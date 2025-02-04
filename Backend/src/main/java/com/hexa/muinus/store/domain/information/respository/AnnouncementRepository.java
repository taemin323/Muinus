package com.hexa.muinus.store.domain.information.respository;

import com.hexa.muinus.store.domain.information.Announcement;
import com.hexa.muinus.store.dto.information.AnnouncementDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    Announcement findByStore_User_Email(String email);

    @Query("""
    SELECT new com.hexa.muinus.store.dto.information.AnnouncementDTO(
        a.boardId, a.title, a.content, a.boardImageUrl, a.createdAt, a.updatedAt)
    FROM Announcement a WHERE a.store.storeNo = :storeNo
""")
    List<AnnouncementDTO> findAnnouncementsByStore(@Param("storeNo") int storeNo);

    
    @Query(value = """
        SELECT a FROM Announcement a 
        JOIN a.store s 
        JOIN s.user u 
        WHERE u.userNo = :userNo 
        AND s.storeNo = :storeNo
        AND a.boardId = :boardId
    """)
    Optional<Announcement> findAnnouncementByUserNoAndStoreNoAndBoardId(@Param("userNo") Integer userNo,
                                                                        @Param("storeNo") Integer storeNo,
                                                                        @Param("boardId") Integer boardId);

    Announcement findByStore_User_EmailAndBoardId(String storeUserEmail, Integer boardId);

}
