package com.abd.mfc.manager;

import com.abd.mfc.dao.CommentDao;
import com.abd.mfc.entities.Comment;
import com.abd.mfc.vo.CommentVO;

public interface CommentManager extends BaseManager<Comment, CommentVO, Long, CommentDao> {

}
