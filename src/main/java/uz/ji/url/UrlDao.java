package uz.ji.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
@Transactional
public class UrlDao {

    @Autowired
     JdbcTemplate template;

//    public void setTemplate(JdbcTemplate template) {
//        this.template = template;
//    }

    public void save(UrlDomain urlDomain) {
        String query = "insert into UrlDomain(originalurl, shortenedurl, createdat, description, validtill)" +
                " values('" + urlDomain.getOriginalUrl() + "','" + urlDomain.getShortenedUrl() + "','" + urlDomain.getCreatedAt() + "'," +
                "'" + urlDomain.getDescription() + "','" + urlDomain.getValidTill() + "')";
        template.update(query);
    }


//    public int save(Emp p){
//        String sql="insert into Emp99(name,salary,designation) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";
//        return template.update(sql);
//    }

//    public void save(UrlDomain urlDomain) {
//        URL_DOMAIN_LIST.add(urlDomain);
//    }

    public List<UrlDomain> getAll() {
        String query = "select * from UrlDomain";
        return template.query(query, new RowMapper<UrlDomain>() {
            public UrlDomain mapRow(ResultSet resultSet, int i) throws SQLException {
                UrlDomain urlDomain = new UrlDomain();

                urlDomain.setId(resultSet.getLong(1));
                urlDomain.setOriginalUrl(resultSet.getString(2));
                urlDomain.setShortenedUrl(resultSet.getString(3));
                urlDomain.setCreatedAt(resultSet.getTimestamp(4));
                urlDomain.setDescription(resultSet.getString(5));
                urlDomain.setValidTill(resultSet.getTimestamp(6));
                return urlDomain;
            }
        });
    }

    public UrlDomain findByShortenedUrl(String shortenedUrl) {
//        // TODO: 7/25/2022 customize exception
//        return URL_DOMAIN_LIST.stream().filter(urlDomain -> urlDomain.getShortenedUrl().equals(shortenedUrl))
//                .findFirst().orElseThrow(() -> new RuntimeException("URL not found"));

        String query = "select * from UrlDomain  where shortenedUrl like ?";
        return template.queryForObject(query, new Object[]{shortenedUrl}, new BeanPropertyRowMapper<UrlDomain>(UrlDomain.class));
//        String sql="select * from emp99 where id=?";`
//        return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));
//
    }
}
