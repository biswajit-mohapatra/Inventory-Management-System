package com.gl.microservices.poc.util;

public class InventoryType {

	public enum Size {

		ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10);

		private int value;

		Size(int i) {
			this.value = i;
		}

		public int getValue() {
			return value;
		}
	}

	public enum StockStatus {

		IN("in"), OUT("out");

		private String value;

		StockStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum Color {

		BLACK, RED, YELLOW, BLUE, GREEN, WHITE, PURPLE;

	}

	public enum ProductState {

		HIDDEN, VISIBLE, OUT_OF_STOCK, DISCONTINUED, ACTIVATE_ON, PROCESSING, DELETED;

	}

	public enum StyleState {

		PROCESSING, VISIBLE, OUT_OF_STOCK, INACTIVE, HIBERNATE;

	}

}
