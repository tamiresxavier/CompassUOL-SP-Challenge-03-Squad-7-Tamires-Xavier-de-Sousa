package br.com.compassuol.pb.challenge.notification.dtos;

import br.com.compassuol.pb.challenge.notification.entities.Email;

public class EmailDto {
    private String fromEmail;
    private String fromName;
    private String replyTo;
    private String to;
    private String subject;
    private String body;
    private String contentType;

    public EmailDto() {
    }

    public EmailDto(Email email) {
        this.fromEmail = email.getFromEmail();
        this.fromName = email.getFromName();
        this.replyTo = email.getReplyTo();
        this.to = email.getTo();
        this.subject = email.getSubject();
        this.body = email.getBody();
        this.contentType = email.getContentType();
    }

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}