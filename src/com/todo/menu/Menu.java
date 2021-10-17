package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("***[명령어 메뉴]***");
        System.out.println("할 일 추가 ( 입력: add )");
        System.out.println("할 일 삭제 ( 입력: del )");
        System.out.println("할 일 수정 ( 입력: edit )");
        System.out.println("완료 처리 ( 입력: comp <번호> )");
        System.out.println("전체 목록 보기 ( 입력: ls )");
        System.out.println("중요도별 목록 보기 ( 입력: ls_priority <중요도> )");
        System.out.println("마감 며칠 남은 목록 보기 ( 입력: ls_days_left <남은 일 수> )");
        System.out.println("제목순 정렬 ( 입력: ls_name_asc )");
        System.out.println("제목역순 정렬 ( 입력: ls_name_desc )");
        System.out.println("마감날짜 이른순 정렬 ( 입력: ls_date )");
        System.out.println("마감날짜 늦은순 정렬 ( 입력: ls_date_desc )");
        System.out.println("키워드 검색 ( 입력: find <키워드> )");
        System.out.println("카테고리 검색 ( 입력: find_cate <키워드> )");
        System.out.println("카테고리 목록 출력 ( 입력: ls_cate )");
        System.out.println("완료 목록 출력 ( 입력: ls_comp )");
        System.out.println("종료 ( 입력: exit 또는 esc 버튼 )");
    }
    
    public static void prompt()
    {
    	System.out.print("\n실행할 메뉴 >> ");
    }
}

//[기능] 오늘 날짜에서 due_date까지 남은 날 - 완료
//[기능] O일 남은 목록 - 완료
//[필드] 중요도 상/중/하 필드 추가 - 완료
//[기능] 중요도 상/중/하 리스트 보기 - 완료
//[필드] 비밀 할 일 int 필드 추가 - 완료
//[기능] 비밀 할 일 추가 및 조회 - 완료