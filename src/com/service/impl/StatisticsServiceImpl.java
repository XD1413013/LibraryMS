package com.service.impl;

import com.dao.BorrowHistoryDao;
import com.dao.IncomeDao;
import com.dao.impl.BorrowHistoryDaoImpl;
import com.dao.impl.IncomeDaoImpl;
import com.domain.BorrowHistory;
import com.service.StatisticsService;
import com.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class StatisticsServiceImpl implements StatisticsService {
    @Override
    public double calcuPayment(String startTime, String endTime) {
        if (startTime.compareTo(endTime) > 0) {
            return 0.0;
        }

        IncomeDao incomeDao = new IncomeDaoImpl();

        double income = incomeDao.serchIncome(startTime, endTime);

        return income;
    }
}
