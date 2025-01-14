package net.scit.spring7.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageNavigator {
	private final int pagePerGroup = 10;	// 그룹 당 페이지 수
	private int pageLimit;					// 한 페이지 당 글 개수
	private int page;						// 사용자가 요청한 페이지
	private int totalPages;					// 총 페이지 수
	private int totalGroupCount;			// 총 그룹 수
	private int currentGroup;				// 사용자가 요청한 페이지의 그룹
	private int startPageGroup;				// 현재 그룹의 첫 페이지
	private int endPageGroup;				// 현재 그룹의 마지막 페이지
	
	public PageNavigator(int pageLimit, int page, int totalPages) {
		// 멤버 초기화
		this.pageLimit = pageLimit;
		this.page = page;
		this.totalPages = totalPages;
		
		// 총 그룹 수 계산
		totalGroupCount = totalPages / pagePerGroup;
		totalGroupCount += (totalPages % pagePerGroup == 0) ? 0 : 1;
		
		System.out.println("=== totalGroupCount(총 그룹수): " + totalGroupCount);
		
		// 사용자가 요청한 페이지의 첫 번째, 마지막
		startPageGroup = (int)(Math.ceil((double)page / pageLimit) - 1) * pageLimit + 1;
		System.out.println("=== 요청한 페이지그룹의 첫페이지(총 그룹수): " + startPageGroup);
		
		endPageGroup = (startPageGroup + pagePerGroup - 1) >= totalPages ? totalPages : (startPageGroup + pagePerGroup - 1);
		if (endPageGroup ==  0) endPageGroup = 1;
		System.out.println("=== endPageGroup: " + endPageGroup);
		
		currentGroup = (page - 1) / pagePerGroup + 1;
		System.out.println("=== currentGroup: " + currentGroup);
	}
}
