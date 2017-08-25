package com.rita.activitytable3.bean;

import com.rita.activitytable3.bean.Issue;
import com.rita.activitytable3.bean.Note;
import com.rita.activitytable3.bean.Pull_request;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * 事件里面包含的issue、pr、note实体类
 * @created 2014-07-14
 * @author 火蚁（http://my.oschina.net/LittleDY）
 *
 */
@SuppressWarnings("serial")
public class Events implements Serializable {
	
	@JsonProperty("issue")
	private Issue _issue;
	
	@JsonProperty("note")
	private Note _note; 
	
	@JsonProperty("pull_request")
	private Pull_request _pull_request;

	public Issue getIssue() {
		return _issue;
	}

	public void setIssue(Issue issue) {
		this._issue = issue;
	}

	public Note getNote() {
		return _note;
	}

	public void setNote(Note note) {
		this._note = note;
	}

	public Pull_request getPull_request() {
		return _pull_request;
	}

	public void setPull_request(Pull_request pull_request) {
		this._pull_request = pull_request;
	}
}
