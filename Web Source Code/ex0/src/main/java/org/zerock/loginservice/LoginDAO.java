package org.zerock.loginservice;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.zerock.domain.BoardVO;

public class LoginDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "org.zerock.mapper.BoardMapper";
	
	
	public void login(LoginVO login) throws Exception {
		session.insert(namespace + ".create", login);
	}

}
