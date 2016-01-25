package com.abd.mfc.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abd.mfc.dao.CommentDao;
import com.abd.mfc.entities.Comment;
import com.abd.mfc.exception.ApplicationAbdException;
import com.abd.mfc.manager.CommentManager;
import com.abd.mfc.vo.CommentVO;

@Service("commentManager")
public class CommentManagerImpl extends BaseManagerImpl<Comment, CommentVO, Long, CommentDao>
		implements CommentManager {

	@Autowired
	private CommentDao dao;
	
	@Override
	public Class<Comment> getClazz() {
		return Comment.class;
	}

	@Override
	public CommentVO convertEntityToVO(Comment e)
			throws ApplicationAbdException {
		return new CommentVO(e);
	}

	@Override
	public CommentDao getDao() {
		return dao;
	}
	
}
