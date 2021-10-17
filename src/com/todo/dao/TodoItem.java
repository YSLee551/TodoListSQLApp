package com.todo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
	private String category;
	private String title;
	private String memo;
	private String due_date;
	private String current_date;
	private int is_completed;
	private String priority;
	private int is_secret;

	public TodoItem(String category, String title, String memo, String due_date, String priority) {
		this.category = category;
		this.title = title;
		this.memo = memo;
		this.due_date = due_date;
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
		this.current_date = f.format(new Date());
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(String current_date) {
		this.current_date = current_date;
	}

	public String getDue_date() {
		return due_date;
	}

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getIs_secret() {
		return is_secret;
	}

	public void setIs_secret(int is_secret) {
		this.is_secret = is_secret;
	}

	@Override
	public String toString() {
		long date_difference = this.getLeftDays();

		if (this.is_completed == 1)
			if (date_difference < 0)
				return "[V] " + id + ". " + priority + " [" + category + "] " + title + " : " + memo + " - " + due_date
						+ "까지 " + Math.abs(date_difference) + "일 지남 (" + current_date + " 추가됨)";
			else
				return "[V] " + id + ". " + priority + " [" + category + "] " + title + " : " + memo + " - " + due_date
						+ "까지 " + date_difference + "일 남음 (" + current_date + " 추가됨)";
		else if (date_difference < 0)
			return "[X] " + id + ". " + priority + " [" + category + "] " + title + " : " + memo + " - " + due_date
					+ "까지 " + Math.abs(date_difference) + "일 지남 (" + current_date + " 추가됨)";
		else
			return "[ ] " + id + ". " + priority + " [" + category + "] " + title + " : " + memo + " - " + due_date
					+ "까지 " + date_difference + "일 남음 (" + current_date + " 추가됨)";
	}

	public long getLeftDays() {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
		long date_difference = 0;
		try {
			Date due_date = sdformat.parse(this.getDue_date());
			String current_dat_st = sdformat.format(new Date());
			Date current_date = sdformat.parse(current_dat_st);
			date_difference = (due_date.getTime() - current_date.getTime()) / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date_difference;
	}
}
