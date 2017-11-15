package com.service.impl;

import com.dao.UserInformationDao;
import com.dao.impl.UserInformationDaoImpl;
import com.domain.Reader;
import com.service.ReaderService;

public class ReaderServiceImpl implements ReaderService {
    UserInformationDao ui = new UserInformationDaoImpl();

    @Override
    public boolean activateReader(String id) {
        return ui.changeStatus(id, "1");
    }
}
