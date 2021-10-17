package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;

import java.sql.*;

import java.util.*;

import com.todo.service.DbConnect;

public class TodoList {
	Connection conn;

	public TodoList() {
		this.conn = DbConnect.getConnection();
	}

	public int addItem(TodoItem t) {
		String sql = "insert into list (category, title, memo, due_date, current_date, priority) values (?,?,?,?,?,?);";
		PreparedStatement pstmt;

		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getCategory());
			pstmt.setString(2, t.getTitle());
			pstmt.setString(3, t.getMemo());
			pstmt.setString(4, t.getDue_date());
			pstmt.setString(5, t.getCurrent_date());
			pstmt.setString(6, t.getPriority());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public int addSecretItem(TodoItem t) {
		String sql = "insert into list (category, title, memo, due_date, current_date, priority, is_secret) values (?,?,?,?,?,?,?);";
		PreparedStatement pstmt;

		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getCategory());
			pstmt.setString(2, t.getTitle());
			pstmt.setString(3, t.getMemo());
			pstmt.setString(4, t.getDue_date());
			pstmt.setString(5, t.getCurrent_date());
			pstmt.setString(6, t.getPriority());
			pstmt.setInt(7, t.getIs_secret());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public int deleteItem(int index) {
		String sql = "delete from list where id=?";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int updateItem(TodoItem t) {
		String sql = "update list set category=?, title=?, memo=?, due_date=?, current_date=?, priority=? where id=?;";

		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getCategory());
			pstmt.setString(2, t.getTitle());
			pstmt.setString(3, t.getMemo());
			pstmt.setString(4, t.getDue_date());
			pstmt.setString(5, t.getCurrent_date());
			pstmt.setString(6, t.getPriority());
			pstmt.setInt(7, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public TodoItem getItem(String index) {
		TodoItem item = new TodoItem(null, null, null, null, null);

		String sql = "select * from list where id=?;";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, index);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String priority = rs.getString("priority");
				int is_completed = rs.getInt("is_completed");
				item = new TodoItem(category, title, memo, due_date, priority);
				item.setId(id);
				item.setCurrent_date(current_date);
				item.setIs_completed(is_completed);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return item;
	}
	
	public ArrayList<TodoItem> getSecretList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;

		try {
			stmt = conn.createStatement();
			String sql = "select * from list;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String priority = rs.getString("priority");
				int is_completed = rs.getInt("is_completed");
				int is_secret = rs.getInt("is_secret");
				TodoItem t = new TodoItem(category, title, memo, due_date, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				if (is_secret == 1)
					list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;

		try {
			stmt = conn.createStatement();
			String sql = "select * from list;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String priority = rs.getString("priority");
				int is_completed = rs.getInt("is_completed");
				int is_secret = rs.getInt("is_secret");
				TodoItem t = new TodoItem(category, title, memo, due_date, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				if (is_secret == 0)
					list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<TodoItem> getSearchedList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%" + keyword + "%";
		try {
			String sql = "select * from list where title like ? or memo like ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String priority = rs.getString("priority");
				int is_completed = rs.getInt("is_completed");
				int is_secret = rs.getInt("is_secret");
				TodoItem t = new TodoItem(category, title, memo, due_date, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				if (is_secret == 0)
					list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<TodoItem> getCategorySearchedList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "select * from list where category=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String priority = rs.getString("priority");
				int is_completed = rs.getInt("is_completed");
				int is_secret = rs.getInt("is_secret");
				TodoItem t = new TodoItem(category, title, memo, due_date, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				if (is_secret == 0)
					list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<TodoItem> getOrderedList(String orderBy, boolean isDesc) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;

		try {
			stmt = conn.createStatement();
			String sql = "select * from list order by " + orderBy;
			if (isDesc)
				sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String priority = rs.getString("priority");
				int is_completed = rs.getInt("is_completed");
				int is_secret = rs.getInt("is_secret");
				TodoItem t = new TodoItem(category, title, memo, due_date, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				if (is_secret == 0)
					list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<TodoItem> getCompletedList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;

		try {
			stmt = conn.createStatement();
			String sql = "select * from list where is_completed=1;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String priority = rs.getString("priority");
				int is_completed = rs.getInt("is_completed");
				int is_secret = rs.getInt("is_secret");
				TodoItem t = new TodoItem(category, title, memo, due_date, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				if (is_secret == 0)
					list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<TodoItem> getPriorityList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "select * from list where priority=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String priority = rs.getString("priority");
				int is_completed = rs.getInt("is_completed");
				int is_secret = rs.getInt("is_secret");
				TodoItem t = new TodoItem(category, title, memo, due_date, priority);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				if (is_secret == 0)
					list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ArrayList<String> getCategories() {
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "select distinct category from list where is_secret=0;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String category = rs.getString("category");
				list.add(category);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "select count(id) from list where is_secret=0;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int setCompleted(String id) {
		String sql = "update list set is_completed=1 where id=?;";

		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// .txt 파일에서 .db 파일로 데이터를 옮기는 기능
//	public void importData(String filename) {
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//			String line;
//			String sql = "insert into list (category, title, memo, due_date, current_date) values (?,?,?,?,?);";
//			int records = 0;
//
//			while ((line = br.readLine()) != null) {
//				StringTokenizer st = new StringTokenizer(line, "##");
//				String category = st.nextToken();
//				String title = st.nextToken();
//				String memo = st.nextToken();
//				String due_date = st.nextToken();
//				String current_date = st.nextToken();
//
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, category);
//				pstmt.setString(2, title);
//				pstmt.setString(3, memo);
//				pstmt.setString(4, due_date);
//				pstmt.setString(5, current_date);
//				int count = pstmt.executeUpdate();
//				if (count > 0)
//					records++;
//				pstmt.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
	public boolean isDuplicate(String title) {
		int count = 0;
		PreparedStatement pstmt;
		try {
			String sql = "select count(id) from list where title=?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt("count(id)");
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count > 0)
			return true;
		else
			return false;
	}
}
