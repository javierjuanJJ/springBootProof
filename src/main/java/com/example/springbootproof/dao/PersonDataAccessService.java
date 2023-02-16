package com.example.springbootproof.dao;

import com.example.springbootproof.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(Person person) {
        String sql = "" +
                "INSERT INTO person (" +
                " id, " +
                " name) " +
                "VALUES (?, ?)";
        return jdbcTemplate.update(
                sql,
                person.getId().toString(),
                person.getName()
        );
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sqlQuery = "SELECT id, name FROM person";
        List<Person> people = jdbcTemplate.query(sqlQuery, (rs, rowNum) -> {
            UUID id = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            return new Person(id, name);
        });
        return people;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sqlQuery = "SELECT id, name FROM person WHERE id=?";
        Person person = jdbcTemplate.queryForObject(sqlQuery,new Object[]{id}, (rs, rowNum) -> {
            UUID id1 = UUID.fromString(rs.getString("id"));
            String name = rs.getString("name");
            return new Person(id1, name);
        });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        String sql = "" +
                "DELETE FROM person " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        String sql = "" +
                "UPDATE person " +
                "SET name = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, person.getId(), person.getName());
    }
}
