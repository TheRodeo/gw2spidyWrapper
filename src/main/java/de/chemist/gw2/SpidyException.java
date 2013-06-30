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

/**
 * 
 * @author Maximilian Werling
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SpidyException extends Exception {

	public SpidyException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SpidyException(String message) {
		super(message);
	}
}
