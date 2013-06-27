/*
 * gw2spidyWrapper - A simple Wrapper for the Guild Wars 2 Spidy API.
 * Copyright 2013 Maximilian Werling
 * 
 * This file is part of gw2spidyWrapper.
 *
 * GWTradeWrapper is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * gw2spidyWrapper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with gw2spidyWrapper.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.chemist.gw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author Maximilian Werling
 * @version 1.0
 */
public class SpidyWrapper {

	/**
	 * Holds constants for the
	 * {@code SpidyWrapper#getItemListings(String, int, String, int)} method.
	 */
	public static final class BuyOrSell {
		public static final String BUY = "buy/";
		public static final String SELL = "sell/";
	}

	/**
	 * Holds constants for choosing the output format.
	 */
	public static final class Format {
		public static final String CSV = "csv/";
		public static final String JSON = "json/";
	}

	/**
	 * Holds constants for choosing the API version.
	 */
	public static final class Version {
		public static final String V09 = "v0.9/";
	}

	private static final String API_GEM_PRICE = "gem-price";
	private static final String API_ITEM = "item/";
	private static final String API_LIST_DISCIPLINE = "disciplines";
	private static final String API_LIST_ITEM_FULL = "all-items/";
	private static final String API_LIST_RARITY = "rarities";
	private static final String API_LIST_RECIPE = "recipies/";
	private static final String API_LIST_TYPE = "types";
	private static final String API_LISTINGS = "listings/";
	private static final String API_RECIPE = "recipe/";
	private static final String API_SEARCH_ITEM = "item-search/";
	private static final String BASE_URL = "http://www.gw2spidy.com/api/";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1003.1 Safari/535.19 Awesomium/1.7.1";

	/**
	 * Returns a list of all crafting disciplines.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getDisciplines(String format)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_LIST_DISCIPLINE);
			return checkResult(readBufferedReader(url));
		} catch (IOException e) {
			throw new SpidyWrapperException("No connection!", e);
		}
	}

	/**
	 * Returns a list of items or a single item, if typeId is specified.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @param typeId
	 *            ID of the item type. -1 will get you the full list.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getFullItemList(String format, int typeId)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_LIST_ITEM_FULL
					+ (typeId == -1 ? "all" : typeId));
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("No items for type ID: " + typeId);
		}
	}

	/**
	 * Returns the current gem price for 100 gems and the amount of gold you get
	 * for exchanging 100 gems.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getGemPrice(String format)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_GEM_PRICE);
			return checkResult(readBufferedReader(url));
		} catch (IOException e) {
			throw new SpidyWrapperException("No connection!", e);
		}
	}

	/**
	 * Returns the item with the specified ID.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @param itemId
	 *            ID you want the information for.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getItem(String format, int itemId)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_ITEM + itemId);
			return checkResult(readBufferedReader(url));
		} catch (IOException e) {
			throw new SpidyWrapperException("No connection!" + itemId, e);
		}
	}

	/**
	 * Returns the item listings for the item with the specified ID.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @param buyOrSell
	 *            see {@code BuyOrSell} for alternatives.
	 * @param itemId
	 *            ID you want the listings for.
	 * @param pages
	 *            determines the amount of returned items. One page contains 50
	 *            items.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getItemListings(String format, String buyOrSell,
			int itemId, int pages) throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format,
					buildItemListingsArgument(itemId, buyOrSell, pages));
			return checkResult(readBufferedReader(url));
		} catch (IOException e) {
			throw new SpidyWrapperException("No connection!", e);
		}
	}

	/**
	 * Returns a list of items containing the specified search string.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @param itemName
	 *            search string
	 * @param pages
	 *            determines the amount of returned items. One page contains 50
	 *            items.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getItems(String format, String itemName, int pages)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format,
					buildItemNameArgument(itemName, pages));
			return checkResult(readBufferedReader(url));
		} catch (IOException e) {
			throw new SpidyWrapperException("No connection!" + itemName, e);
		}
	}

	/**
	 * Returns information about the rarities.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getRarities(String format)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_LIST_RARITY);
			return checkResult(readBufferedReader(url));
		} catch (IOException e) {
			throw new SpidyWrapperException("No connection!", e);
		}
	}

	/**
	 * Returns the recipe with the specified ID.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @param dataId
	 *            ID of the recipe you want information for.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getRecipeData(String format, int dataId)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_RECIPE + dataId);
			return checkResult(readBufferedReader(url));
		} catch (IOException e) {
			throw new SpidyWrapperException("No connection!", e);
		}
	}

	/**
	 * Returns a list of recipies for the given crafting discipline.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @param disciplineId
	 *            discipline ID you want the recipes for.
	 * @param pages
	 *            determines the amount of returned items. One page contains 50
	 *            items.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getRecipeList(String format, int disciplineId,
			int pages) throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format,
					buildRecipeListArgument(disciplineId, pages));
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("No recipes for discipline ID: " + disciplineId);
		}
	}

	/**
	 * Returns information about the types and subtypes.
	 * 
	 * @param format
	 *            the format you wish the result to be in. See {@code Format}
	 *            class for alternatives.
	 * @return String in chosen format.
	 * @throws SpidyWrapperException
	 *             if something went wrong.
	 */
	public static String getTypes(String format) throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_LIST_TYPE);
			return checkResult(readBufferedReader(url));
		} catch (IOException e) {
			throw new SpidyWrapperException("No connection!", e);
		}
	}

	private static URL buildAPIURL(String format, String argument)
			throws MalformedURLException {
		StringBuilder bldr = new StringBuilder(BASE_URL);
		bldr.append(Version.V09).append(format).append(argument);
		return new URL(bldr.toString());
	}

	private static String buildItemListingsArgument(int itemId,
			String buyOrSell, int pageOffset) {
		StringBuilder bldr = new StringBuilder(API_LISTINGS);
		bldr.append(itemId).append("/").append(buyOrSell).append(pageOffset);
		return bldr.toString();
	}

	private static String buildItemNameArgument(String name, int pageOffset) {
		StringBuilder bldr = new StringBuilder(API_SEARCH_ITEM);
		bldr.append(name).append("/").append(pageOffset);
		return bldr.toString();
	}

	private static String buildRecipeListArgument(int disciplineId,
			int pageOffset) {
		StringBuilder bldr = new StringBuilder(API_LIST_RECIPE);
		bldr.append(disciplineId).append("/").append(pageOffset);
		return bldr.toString();
	}

	private static String readBufferedReader(URL url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader bf = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String result = bf.readLine();
		bf.close();
		return result;
	}

	private static String checkResult(String result)
			throws SpidyWrapperException {
		if (result.equals("{}")) {
			throw new SpidyWrapperException("Result was empty!");
		} else {
			return result;
		}
	}
}
