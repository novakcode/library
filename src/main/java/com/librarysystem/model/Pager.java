package com.librarysystem.model;

import java.util.ArrayList;
import java.util.List;

/* Little pagination i created.Not the best but does the work i need */

public class Pager {

	/* List of navigationPages that will be on page */
	private List<Integer> navigationPages = new ArrayList<Integer>();

	/* Constructor that gets needed values for pagination */
	public Pager(int navigationPagesSize, int page, int totalNavigationPages) {

		// Starting and ending page of navigation pages
		int startPage = 0;
		int endPage = 0;

		int currentPage = page - 1 < 1 ? 1 : page;

		/*
		 * Lets say we are at the page 5 we want to see navigation pages like
		 * this {3,4,5,6,7}.Thats why we get half of the navigationPagesSize so the current page is in middle(most of times).
		 * 
		 */
		int half = navigationPagesSize / 2;

		
		int lowHalf = currentPage - half;
		int highHalf = currentPage + half;

		if (lowHalf >= 1 && highHalf <= totalNavigationPages) {

			startPage = lowHalf;
			endPage = highHalf;

		} else if (totalNavigationPages <= navigationPagesSize) {
			startPage = 1;
			endPage = totalNavigationPages;

		} else if (highHalf > totalNavigationPages) {
			startPage = totalNavigationPages - half * 2;
			endPage = totalNavigationPages;
		} else {
			startPage = 1;
			endPage = navigationPagesSize;
		}

		setNavigationPages(startPage, endPage);

	}

	private void setNavigationPages(int startPage, int endPage) {

		for (int i = startPage; i <= endPage; i++) {
			navigationPages.add(i);
		}
	}

	public List<Integer> getNavigationPages() {
		return navigationPages;
	}

}
