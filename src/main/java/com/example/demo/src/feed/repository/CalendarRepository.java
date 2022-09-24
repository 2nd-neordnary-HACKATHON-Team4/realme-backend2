package com.example.demo.src.feed.repository;

import com.example.demo.src.feed.DTO.FeedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CalendarRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<FeedDTO.calendarFeed> findByDate(Long userId, String date) {
        String getFeedsQuery = "select f.id, f.created_date from feed as f where f.user_idx = ? and date_format(f.created_date, '%Y-%m') = ?";

        Object[] params = new Object[]{userId, date};

        List<FeedDTO.calendarFeed> calendarFeedList = this.jdbcTemplate.query(getFeedsQuery, (rs, rowNum) -> new FeedDTO.calendarFeed(
                rs.getLong("id"),
                rs.getString("created_date")), params);

        return calendarFeedList;
    }

}
