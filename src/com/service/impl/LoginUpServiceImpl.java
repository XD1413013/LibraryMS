package com.service.impl;

import java.sql.Connection;

import com.dao.UserInformationDao;
import com.dao.impl.UserInformationDaoImpl;
import com.service.LoginUpService;
import com.utils.DBUtils;

public class LoginUpServiceImpl implements LoginUpService {
	private UserInformationDao userInformationDao = new UserInformationDaoImpl();

	public boolean update(String id,String urn,String sex, String pwd) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			int code = userInformationDao.loginUp(conn,id, urn,sex, pwd);
			if (code == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
