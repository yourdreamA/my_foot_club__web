package com.abd.mfc.exception;

import java.util.List;

import com.abd.mfc.vo.MessageVO;

public class ApplicationAbdException extends Exception {
	
	/**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public ApplicationAbdException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public ApplicationAbdException(String message) {
        super(message);
    }

	private String codeErreur;
	private List<MessageVO> messages;
	
	public String getCodeErreur() {
		return codeErreur;
	}
	public void setCodeErreur(String codeErreur) {
		this.codeErreur = codeErreur;
	}
	public List<MessageVO> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageVO> messages) {
		this.messages = messages;
	}
	
	
}
