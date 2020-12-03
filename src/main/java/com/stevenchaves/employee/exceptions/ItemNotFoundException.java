package com.stevenchaves.employee.exceptions;

public class ItemNotFoundException extends RuntimeException {
    /**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException(Long id) {
        super("Could not find item: " + id);
    }

	public ItemNotFoundException(String type, String id) {
		super(String.format("Could not find %s: %s", type, id));
	}
}
