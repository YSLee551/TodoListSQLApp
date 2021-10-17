package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createSecretItem(TodoList l) {
		String title, memo, category, due_date, priority;

		Scanner sc = new Scanner(System.in);

		System.out.print("\n===[비밀 할 일 추가]===\n" + "제목 >> ");
		title = sc.nextLine().trim();

		if (l.isDuplicate(title)) {
			System.out.printf("같은 제목이 이미 있습니다.\n");
			return;
		}

		System.out.print("카테고리 >> ");
		category = sc.next().trim();

		sc.nextLine();

		System.out.print("내용 >> ");
		memo = sc.nextLine().trim();

		System.out.print("마감일자(YYYY/MM/DD) >> ");
		due_date = sc.nextLine().trim();

		System.out.print("중요도(상/중/하) >> ");
		priority = sc.next().trim();

		TodoItem t = new TodoItem(category, title, memo, due_date, priority);
		t.setIs_secret(1);
		if (l.addSecretItem(t) > 0)
			System.out.println("비밀 할 일이 성공적으로 추가되었습니다.");
	}

	public static void createItem(TodoList l) {
		String title, memo, category, due_date, priority;

		Scanner sc = new Scanner(System.in);

		System.out.print("\n===[할 일 추가]===\n" + "제목 >> ");
		title = sc.nextLine().trim();

		if (l.isDuplicate(title)) {
			System.out.printf("같은 제목이 이미 있습니다.\n");
			return;
		}

		System.out.print("카테고리 >> ");
		category = sc.next().trim();

		sc.nextLine();

		System.out.print("내용 >> ");
		memo = sc.nextLine().trim();

		System.out.print("마감일자(YYYY/MM/DD) >> ");
		due_date = sc.nextLine().trim();

		System.out.print("중요도(상/중/하) >> ");
		priority = sc.next().trim();

		TodoItem t = new TodoItem(category, title, memo, due_date, priority);
		if (l.addItem(t) > 0)
			System.out.println("할 일이 성공적으로 추가되었습니다.");

		// 코드상에서 데이터 추가할 때 쓰는 코드
//		String title, memo, category, due_date;
//		TodoItem t;
//
//		title = "초록 안경";
//		category = "쇼핑";
//		memo = "인터넷으로 초록색 안경 사기";
//		due_date = "2021/10/15";
//		t = new TodoItem(category, title, memo, due_date);
//		if (l.addItem(t) > 0)
//			System.out.println("할 일이 성공적으로 추가되었습니다.");
//		try {
//			TimeUnit.SECONDS.sleep(14);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("\n===[할 일 삭제]===\n" + "삭제할 항목의 번호 >> ");
		int num = sc.nextInt();

//		if (num > l.getList().size() || num <= 0) {
//			System.out.println("해당 항목을 찾지 못했습니다.");
//			return;
//		}
//
//		TodoItem del_item = l.getList().get(num - 1);
//
//		System.out.println((l.indexOf(del_item) + 1) + ". " + del_item.toString());
//		while (true) {
//			System.out.print("위 항목을 삭제하시겠습니까?(y/n) >> ");
//			String confirm_del = sc.next();
//			switch (confirm_del) {
//			case "y":
//				l.deleteItem(del_item);
//				System.out.println("할 일이 성공적으로 삭제되었습니다.");
//				return;
//			case "n":
//				System.out.println("삭제를 취소했습니다.");
//				return;
//			default:
//				System.out.println("\n정확한 명령어를 입력하세요.");
//			}

		if (l.deleteItem(num) > 0)
			System.out.println("할 일이 성공적으로 삭제되었습니다.");
	}

	public static void updateItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("\n===[할 일 수정]===\n" + "수정할 항목의 번호 >> ");
		int num = sc.nextInt();

//		if (num > l.getList().size() || num <= 0) {
//			System.out.println("해당 항목을 찾지 못했습니다.");
//			return;
//		}
//
//		TodoItem edit_item = l.getList().get(num - 1);
//
//		System.out.println((l.indexOf(edit_item) + 1) + ". " + edit_item.toString());
//		System.out.println("위 항목을 수정합니다.");
//		
//		String new_title;
//		while (true) {
//			System.out.print("새 제목 >> ");
//			new_title = sc.next().trim();
//			if (!l.isDuplicate(new_title))
//				break;
//			System.out.println("같은 제목이 이미 있습니다. 다시 입력해주세요.");
//		}
		sc.nextLine();

		System.out.print("새 제목 >> ");
		String new_title = sc.nextLine().trim();

		System.out.print("새 카테고리 >> ");
		String new_category = sc.next().trim();

		sc.nextLine();

		System.out.print("새 내용 >> ");
		String new_memo = sc.nextLine().trim();

		System.out.print("새 마감일자(YYYY/MM/DD) >> ");
		String new_due_date = sc.nextLine().trim();

		System.out.print("새 중요도(상/중/하) >> ");
		String new_priority = sc.next().trim();

		TodoItem t = new TodoItem(new_category, new_title, new_memo, new_due_date, new_priority);

		if (l.updateItem(t) > 0)
			System.out.println("할 일이 성공적으로 수정되었습니다.");
	}
	
	public static void listAllSecret(TodoList l) {
		ArrayList<TodoItem> list = l.getSecretList();
		int count = list.size();
		System.out.println("\n***[비밀 목록]***\n" + "총 " + count + "개의 할 일이 있습니다.");
		for (TodoItem item : list) {
			System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l) {
		int count = l.getCount();
		System.out.println("\n***[전체 목록]***\n" + "총 " + count + "개의 할 일이 있습니다.");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void listAllOrdered(TodoList l, String orderBy, boolean isDesc) {
		int count = l.getCount();
		System.out.println("\n***[전체 목록]***\n" + "총 " + count + "개의 할 일이 있습니다.");
		for (TodoItem item : l.getOrderedList(orderBy, isDesc)) {
			System.out.println(item.toString());
		}
	}

	public static void listAllCompleted(TodoList l) {
		ArrayList<TodoItem> list = l.getCompletedList();
		int count = list.size();

		System.out.println("\n***[완료 목록]***\n" + "총 " + count + "개의 완료된 할 일이 있습니다.");
		for (TodoItem item : list) {
			System.out.println(item.toString());
		}
	}

	public static void listDaysLeft(TodoList l, String keyword) {
		ArrayList<TodoItem> list = l.getSearchedList(null);
		if (keyword == "") {
			System.out.println("\n정확한 명령어를 입력하세요. 명령어 목록을 보시려면 \"help\"를 입력하세요.");
			return;
		}
		int compare_num = Integer.parseInt(keyword);
		for (TodoItem item : l.getList()) {
			if (item.getLeftDays() < compare_num && item.getLeftDays() >= 0 && item.getIs_completed() == 0)
				list.add(item);
		}
		int count = list.size();

		System.out.println("\n***[" + keyword + "일 이내 끝내야 할 목록]***\n" + "총 " + count + "개의 할 일이 있습니다.");

		for (TodoItem item : list) {
			System.out.println(item.toString());
		}
	}

	public static void find(TodoList l, String keyword) {
		int count = 0;

		System.out.println("\n***[검색 결과]***");
		for (TodoItem item : l.getSearchedList(keyword)) {
			System.out.println(item.toString());
			count++;
		}

		System.out.println("총 " + count + "개의 할 일을 찾았습니다.");
	}

	public static void find_cate(TodoList l, String keyword) {
		int count = 0;
		System.out.println("\n***[검색 결과]***");
		for (TodoItem item : l.getCategorySearchedList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("총 " + count + "개의 할 일 찾았습니다.");
	}

	public static void ls_cate(TodoList l) {
		int count = 0;

		for (String category : l.getCategories()) {
			System.out.print(category + "  ");
			count++;
		}

		System.out.println("\n총 " + count + "개의 카테고리를 찾았습니다.");
	}

	public static void set_completed(TodoList l, String index) {
		if (l.setCompleted(index) > 0)
			System.out.println(l.getItem(index).toString());
		System.out.println("위 할 일이 성공적으로 완료 처리 되었습니다.");
	}

	public static void listPriority(TodoList l, String keyword) {
		int count = 0;

		System.out.println("\n***[중요도 " + keyword + " 목록]***");
		for (TodoItem item : l.getPriorityList(keyword)) {
			System.out.println(item.toString());
			count++;
		}

		System.out.println("총 " + count + "개의 할 일을 찾았습니다.");
	}
}
