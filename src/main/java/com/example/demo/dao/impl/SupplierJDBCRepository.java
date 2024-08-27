package com.example.demo.dao.impl;

import com.example.demo.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierJDBCRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    //public Supplier

    public void save(Supplier supplier){

        SqlParameterSource namedParams = new MapSqlParameterSource().addValue("name",supplier.getName());
        jdbcTemplate.update("INSERT INTO SUPPLIER(ID, NAME) values ( nextval('supplier_seq'), :name)",namedParams);

    }

    public List<Supplier> findAll(){

        return jdbcTemplate.query("SELECT ID, NAME FROM SUPPLIER ", (rs, rowNum) -> new Supplier(rs.getInt("ID"), rs.getString("NAME")));
    }
}
