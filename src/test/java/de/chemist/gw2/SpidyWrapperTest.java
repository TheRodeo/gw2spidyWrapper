package de.chemist.gw2;

import org.junit.Test;

public class SpidyWrapperTest {

	@Test
	public void testGetDisciplines() {
		System.out.print("Disciplines: ");
		try {
			String result = SpidyWrapper
					.getDisciplines(SpidyWrapper.Format.JSON);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetFullItemList() {
		System.out.print("Full item list: ");
		try {
			String result = SpidyWrapper.getFullItemList(
					SpidyWrapper.Format.JSON, 1);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetGemPrice() {
		System.out.print("Gem price: ");
		try {
			String result = SpidyWrapper.getGemPrice(SpidyWrapper.Format.JSON);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetItem() {
		System.out.print("Item by ID: ");
		try {
			String result = SpidyWrapper
					.getItem(SpidyWrapper.Format.JSON, 1234);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetItemListings() {
		System.out.print("Listings: ");
		try {
			String result = SpidyWrapper.getItemListings(
					SpidyWrapper.Format.JSON, SpidyWrapper.BuyOrSell.BUY, 1234,
					1);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetItems() {
		System.out.print("Items by name: ");
		try {
			String result = SpidyWrapper.getItems(SpidyWrapper.Format.JSON,
					"Eternity", 1);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetRarities() {
		System.out.print("Rarities: ");
		try {
			String result = SpidyWrapper.getRarities(SpidyWrapper.Format.JSON);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetRecipeData() {
		System.out.print("Recipe Data: ");
		try {
			String result = SpidyWrapper.getRecipeData(
					SpidyWrapper.Format.JSON, 1000);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetRecipeList() {
		System.out.print("Recipe List: ");
		try {
			String result = SpidyWrapper.getRecipeList(
					SpidyWrapper.Format.JSON, 100, 10);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetTypes() {
		System.out.print("Types: ");
		try {
			String result = SpidyWrapper.getTypes(SpidyWrapper.Format.JSON);
			System.out.println(result);
		} catch (SpidyWrapperException swe) {
			System.out.println(swe.getMessage());
		}
	}

}
