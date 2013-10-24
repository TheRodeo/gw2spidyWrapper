package de.chemist.gw2;

import java.util.List;

import org.junit.Test;

import rodeo.base.dao.pojos.Discipline;

public class SpidyWrapperTest {

	@Test
	public void testGetDisciplines() {
		System.out.print("Disciplines: ");
		try {
			final List<Discipline> disciplines = SpidyWrapper.getDisciplines(SpidyFormat.JSON);
			System.out.println(disciplines);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetFullItemList() {
		System.out.print("Full item list: ");
		try {
			final String result = SpidyWrapper.getFullItemList(SpidyFormat.JSON, 1);
			System.out.println(result);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetGemPrice() {
		System.out.print("Gem price: ");
		try {
			final String result = SpidyWrapper.getGemPrice(SpidyFormat.JSON);
			System.out.println(result);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetItem() {
		System.out.print("Item by ID: ");
		try {
			final String result = SpidyWrapper.getItem(SpidyFormat.JSON, 1234);
			System.out.println(result);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetItemListings() {
		System.out.print("Listings: ");
		try {
			final String result = SpidyWrapper.getItemListings(SpidyFormat.JSON, SpidyBuyOrSell.BUY, 1234, 1);
			System.out.println(result);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetItems() {
		System.out.print("Items by name: ");
		try {
			final String result = SpidyWrapper.getItems(SpidyFormat.JSON, "Eternity", 1);
			System.out.println(result);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetRarities() {
		System.out.print("Rarities: ");
		try {
			final String result = SpidyWrapper.getRarities(SpidyFormat.JSON);
			System.out.println(result);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetRecipeData() {
		System.out.print("Recipe Data: ");
		try {
			final String result = SpidyWrapper.getRecipeData(SpidyFormat.JSON, 1000);
			System.out.println(result);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetRecipeList() {
		System.out.print("Recipe List: ");
		try {
			final String result = SpidyWrapper.getRecipeList(SpidyFormat.JSON, 100, 10);
			System.out.println(result);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

	@Test
	public void testGetTypes() {
		System.out.print("Types: ");
		try {
			final String result = SpidyWrapper.getTypes(SpidyFormat.JSON);
			System.out.println(result);
		} catch (final SpidyException swe) {
			System.out.println(swe.getMessage());
		}
	}

}
