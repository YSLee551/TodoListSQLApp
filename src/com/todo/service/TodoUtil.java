package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {

	public static void createItem(TodoList l) {

		String title, memo, category, due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print("\n===[할 일 추가]===\n" + "제목 >> ");
		title = sc.nextLine().trim();

		// 나중에 DB에서 조회하는 방식으로 변경할 부분
//		if (l.isDuplicate(title)) {
//			System.out.printf("같은 제목이 이미 있습니다.");
//			return;
//		}

		System.out.print("카테고리 >> ");
		category = sc.next().trim();

		sc.nextLine();

		System.out.print("내용 >> ");
		memo = sc.nextLine().trim();

		System.out.print("마감일자(YYYY/MM/DD) >> ");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(category, title, memo, due_date);
		if (l.addItem(t) > 0)
			System.out.println("할 일이 성공적으로 추가되었습니다.");
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

		TodoItem t = new TodoItem(new_category, new_title, new_memo, new_due_date);
		t.setId(num);
		if (l.updateItem(t) > 0)
			System.out.println("할 일이 성공적으로 수정되었습니다.");
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
}
