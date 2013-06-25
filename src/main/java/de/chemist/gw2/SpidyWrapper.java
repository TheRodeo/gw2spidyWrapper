package de.chemist.gw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SpidyWrapper {

	public static final class BuyOrSell {
		public static final String BUY = "buy/";
		public static final String SELL = "sell/";
	}

	public static final class Format {
		public static final String CSV = "csv/";
		public static final String JSON = "json/";
	}

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

	public static String getDisciplines(String format)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_LIST_DISCIPLINE);
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Failed to get disciplines!", e);
		}
	}

	public static String getFullItemList(String format, int typeId)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_LIST_ITEM_FULL + typeId);
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Failed to get item list!", e);
		}
	}

	public static String getGemPrice(String format)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_GEM_PRICE);
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Failed to get gem price!", e);
		}
	}

	public static String getItemByID(String format, int itemId)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_ITEM + itemId);
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Item not found: " + itemId, e);
		}
	}

	public static String getItemListings(String format, int itemId,
			String buyOrSell, int pageOffset) throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format,
					buildItemListingsArgument(itemId, buyOrSell, pageOffset));
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Failed to get listings!", e);
		}
	}

	public static String getItemsByName(String format, String itemName,
			int pages) throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format,
					buildItemNameArgument(itemName, pages));
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Item not found: " + itemName, e);
		}
	}

	public static String getRarities(String format)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_LIST_RARITY);
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Failed to get rarities!", e);
		}
	}

	public static String getRecipeData(String format, int dataId)
			throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_RECIPE + dataId);
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Failed to get recipe!", e);
		}
	}

	public static String getRecipeList(String format, int disciplineId,
			int pageOffset) throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format,
					buildRecipeListArgument(disciplineId, pageOffset));
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Failed to get recipe list!", e);
		}
	}

	public static String getTypes(String format) throws SpidyWrapperException {
		try {
			URL url = buildAPIURL(format, API_LIST_TYPE);
			return readBufferedReader(url);
		} catch (IOException e) {
			throw new SpidyWrapperException("Failed to get types!", e);
		}
	}

	private static URL buildAPIURL(String format, String argument)
			throws MalformedURLException {
		StringBuilder bldr = new StringBuilder(BASE_URL);
		bldr.append(Version.V09);
		bldr.append(format.toString());
		bldr.append(argument);
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
}
