package br.com.model.misc;

public class SearchCriteria {
	
	public SearchCriteria(String key, OperationCriteria operation, Object value) {
		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	private String key;
	private OperationCriteria operation;
	private Object value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public OperationCriteria getOperation() {
		return operation;
	}

	public void setOperation(OperationCriteria operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
