package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {

	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		// l.importData("todolist.txt");
		boolean quit = false;
		Menu.displaymenu();
		do {
			Menu.prompt();

			String raw_command = sc.nextLine().trim();
			String[] command_array = raw_command.split(" ");
			String command = command_array[0];
			String keyword = "";
			String password = "hello";
			if (command_array.length >= 2)
				keyword = command_array[1];

			switch (command) {

			case "add":
				if (keyword.equals(password))
					TodoUtil.createSecretItem(l);
				else
					TodoUtil.createItem(l);
				break;

			case "del":
				TodoUtil.deleteItem(l);
				break;

			case "edit":
				TodoUtil.updateItem(l);
				break;

			case "ls":
				if (keyword.equals(password))
					TodoUtil.listAllSecret(l);
				else
					TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				System.out.println("리스트를 제목순으로 정렬했습니다.");
				TodoUtil.listAllOrdered(l, "title", false);
				break;

			case "ls_name_desc":
				System.out.println("리스트를 제목역순으로 정렬했습니다.");
				TodoUtil.listAllOrdered(l, "title", true);
				break;

			case "ls_date":
				System.out.println("리스트를 마감날짜가 이른순으로 정렬했습니다.");
				TodoUtil.listAllOrdered(l, "due_date", false);
				break;

			case "ls_date_desc":
				System.out.println("리스트를 마감날짜가 늦은순으로 정렬했습니다.");
				TodoUtil.listAllOrdered(l, "due_date", true);
				break;

			case "ls_priority":
				TodoUtil.listPriority(l, keyword);
				break;

			case "ls_days_left":
				TodoUtil.listDaysLeft(l, keyword);
				break;

			case "find":
				TodoUtil.find(l, keyword);
				break;

			case "find_cate":
				TodoUtil.find_cate(l, keyword);
				break;

			case "ls_cate":
				TodoUtil.ls_cate(l);
				break;

			case "comp":
				TodoUtil.set_completed(l, keyword);
				break;

			case "ls_comp":
				TodoUtil.listAllCompleted(l);
				break;

			case "help":
				Menu.displaymenu();
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("\n정확한 명령어를 입력하세요. 명령어 목록을 보시려면 \"help\"를 입력하세요.");
				break;
			}
		} while (!quit);
		System.out.println("앱을 종료합니다.");
	}
}
