package com.librarysystem.pager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.librarysystem.model.Pager;

public class PagerTests {

	
	@Test
	public void navigationPagesSize5Page1Total10(){
		int[] pages = pages(5,1,10);
		assertEquals(5,pages.length);
		assertEquals(1,pages[0]);
		assertEquals(5,pages[4]);
	}
	
	@Test
	public void navigationPagesSize5Page5Total10(){
		int[] pages = pages(5,5,10);
		assertEquals(5,pages.length);
		assertEquals(3,pages[0]);
		assertEquals(7,pages[4]);
	}
	
	@Test
	public void navigationPagesSize5Page10Total10(){
		int[] pages = pages(5,10,10);
		assertEquals(5,pages.length);
		assertEquals(6,pages[0]);
		assertEquals(10,pages[4]);
	}
	
	@Test
	public void navigationPagesSize2Page10Total20(){
		int[] pages = pages(2,10,20);
		assertEquals(3,pages.length);
		assertEquals(9,pages[0]);
		assertEquals(11,pages[2]);
	}
	
	@Test
	public void navigationPagesSize2Page20Total20(){
		int[] pages = pages(2,20,20);
		assertEquals(3,pages.length);
		assertEquals(18,pages[0]);
		assertEquals(20,pages[2]);
	}
	
	private int[] pages(int navigationPagesSize,int page,int totalPages){
		Pager pager = new Pager(navigationPagesSize	,page,totalPages);
		return pager.getNavigationPages().stream().mapToInt(Integer::intValue).toArray();
	}
}
