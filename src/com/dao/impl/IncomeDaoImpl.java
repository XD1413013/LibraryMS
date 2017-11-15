package com.dao.impl;

import com.dao.IncomeDao;
import com.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IncomeDaoImpl implements IncomeDao{
    @Override
    public double serchIncome(String startTime, String endTime) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        double income = 0.0;

        String sql = "SELECT cost FROM income WHERE pay_time < ? AND pay_time >= ?";
        try{
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, endTime);
            pstmt.setString(2, startTime);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                income += rs.getDouble(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(rs, stmt, pstmt, conn);
        }

        return income;
    }
}
