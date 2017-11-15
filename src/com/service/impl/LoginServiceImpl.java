package com.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;

import com.dao.UserInformationDao;
import com.dao.impl.UserInformationDaoImpl;
import com.service.LoginService;
import com.utils.DBUtils;

public class LoginServiceImpl implements LoginService {
	private UserInformationDao userInformationDao = new UserInformationDaoImpl();

	public String check(String id, String pwd, String isLibrarian) {
		Connection conn = null;
		String a = null;
		try {
			conn = DBUtils.getConnection();
			ResultSet resultSet = null;
			if (isLibrarian.equals("true")) {
				resultSet = userInformationDao.getLibrarian(conn, id, pwd);
				if(resultSet.next()) {
					a="1";
				}else {
					a="0";
				}
			} else {
				a = userInformationDao.get(conn, id, pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return a;

	}
}
