package io.sever86.dao;

import io.sever86.domain.Types;
import io.sever86.domain.Viborka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public class DaoJdbcTemplate implements Dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Types> rowMapperTypes = (ResultSet rs, int rowNum) -> {
        Types types = new Types();
        types.setId(rs.getInt("id"));
        types.setName(rs.getString("name"));
        return types;
    };

    public List<Types> findAllTypes() {
        return jdbcTemplate.query("SELECT * FROM types ", rowMapperTypes);
    }

    private RowMapper<Viborka> rowMapperViborka = (ResultSet rs, int rowNum) -> {
        Viborka viborka = new Viborka();
        viborka.setName(rs.getString("Article_Name"));
        viborka.setOficial(rs.getInt("Count_Official_Text"));
        viborka.setEnglish(rs.getInt("Count_English_Text"));
        return viborka;
    };

    public List<Viborka> viborka(Integer id) {
        return jdbcTemplate.query(
                "select articles.name as \"Article_Name\",\n" +
                        "(SELECT COUNT(*) FROM Article_link\n" +
                        "WHERE articles.id = Article_link.article1_id and Article_link.article2_id in\n" +
                        " (SELECT articles.id from articles where typeid=4))AS Count_Official_Text, \n" +
                        "(SELECT COUNT(*) FROM Article_link\n" +
                        "WHERE articles.id = Article_link.article1_id and Article_link.article2_id in\n" +
                        " (SELECT articles.id from articles where typeid=5))AS Count_English_Text\n" +
                        "from Articles\n" +
                        "where typeid=? \n" +
                        "group by id", rowMapperViborka, id
        );
    }
}