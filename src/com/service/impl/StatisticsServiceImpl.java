package com.service.impl;

import com.dao.BorrowHistoryDao;
import com.dao.impl.BorrowHistoryDaoImpl;
import com.domain.BorrowHistory;
import com.service.StatisticsService;

import java.util.List;


public class StatisticsServiceImpl implements StatisticsService {
    @Override
    public double calcuPayment(String startTime, String endTime) {
        double income = 0.0;

        if (startTime.compareTo(endTime) > 0) {
            return 0.0;
        }

        BorrowHistoryDao borrowHistoryDao = new BorrowHistoryDaoImpl();
        List<BorrowHistory> borrowHistories = borrowHistoryDao.viewHistory(startTime, endTime);
        for (BorrowHistory borrowHistory: borrowHistories) {
            income += borrowHistory.getPay_money();
        }

        return income;
    }
}
