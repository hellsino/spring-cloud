package com.domain.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("mysql")
public class MultiServiceImpl/* implements MultiService */ {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List selectMySQL() {
        return jdbcTemplate.queryForList("select 1 from dual");
    }

    @DS("dev")
    public List selectDev() {
        return jdbcTemplate.queryForList("select * from SYNC_ERP_DATA");
    }

    @DS("uat")
    public List selectUat() {
        return jdbcTemplate.queryForList("select * from SYNC_ERP_DATA");
    }
}