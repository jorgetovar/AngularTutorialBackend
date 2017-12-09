package co.com.jetprogramming.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the user_log database table.
 * 
 */
@Entity
@Table(name = "user_log")
public class UserLog  {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name = "date_log")
	private LocalDate dateLog;

	@Column(name = "datetime_log")
	private LocalDateTime datetimeLog;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateLog() {
		return dateLog;
	}

	public void setDateLog(LocalDate dateLog) {
		this.dateLog = dateLog;
	}

	public LocalDateTime getDatetimeLog() {
		return datetimeLog;
	}

	public void setDatetimeLog(LocalDateTime datetimeLog) {
		this.datetimeLog = datetimeLog;
	}

}