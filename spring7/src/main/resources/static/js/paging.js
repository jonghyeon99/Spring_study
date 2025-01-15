/**
 * 페이징 호출 코드
 */

function pageFormSubmit(page) {
	$('#requestPage').val(page);
	$('#searchForm').submit();
	// location.href=`/board/boardList?page=${page}`	// 검색 후 페이징을 하면 검색이 풀림
}
