package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Essay;

public interface EssayRepository extends JpaRepository<Essay, Long> {
        @Transactional
        @Modifying
        @Query(value = """
                        Insert into essay (title,content,score,feedback)
                        Values(:title,:content,:score,:feedback)
                        """, nativeQuery = true)
        void createEssay(
                        @Param("title") String title,
                        @Param("content") String content,
                        @Param("score") Double score,
                        @Param("feedback") String feedback);

        @Transactional
        @Modifying
        @Query(value = """
                        Update essay
                        Set title=:title,
                        content=:content,
                        score=:score,
                        feedback=:feedback
                        where id=:id
                        """, nativeQuery = true)
        void updateEssay(
                        @Param("id") long id,
                        @Param("title") String title,
                        @Param("content") String content,
                        @Param("score") Double score,
                        @Param("feedback") String feedback);

        @Transactional
        @Modifying
        @Query(value = """
                        Delete from essay
                        where id=:id
                        """, nativeQuery = true)
        void deleteEssay(@Param("id") long id);

        @Query(value = """
                        Select * from essay
                        """, nativeQuery = true)
        List<Essay> getAllEssay();

        @Query(value = """
                        Select * from essay where id=:id
                        """, nativeQuery = true)
        Optional<Essay> getEssayById(@Param("id") long id);
}
