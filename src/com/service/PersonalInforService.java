package com.service;

import com.domain.Librarian;
import com.domain.Reader;

public interface PersonalInforService {
	public Reader PersonalInfor(String reader_id);
	public boolean Modify(String id, String data, int flag, int user);
	public Librarian libPersonalInfor(String librarian_id);
}
