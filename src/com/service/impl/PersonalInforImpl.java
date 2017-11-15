package com.service.impl;

import com.dao.UserInformationDao;
import com.dao.impl.UserInformationDaoImpl;
import com.domain.Librarian;
import com.domain.Reader;
import com.service.PersonalInforService;

public class PersonalInforImpl implements PersonalInforService {
	
	private UserInformationDao userInformationDao = new UserInformationDaoImpl();
	@Override
	public Reader PersonalInfor(String reader_id) {
		return userInformationDao.searchReader(reader_id);
	}
	@Override
	public boolean Modify(String id, String data, int flag, int user) {
		return userInformationDao.Modify(id, data, flag, user);
	}

	@Override
	public Librarian libPersonalInfor(String librarian_id) {
		return userInformationDao.searchLibrarian(librarian_id);
	}
}
