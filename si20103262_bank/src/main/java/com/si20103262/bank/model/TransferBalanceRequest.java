package com.si20103262.bank.model;

/**
 * @author si20103262
 *
 */
public class TransferBalanceRequest {

	private Long fromAccountNumber;
	private Long toAccountNumber;
	private Double amount;
	/**
	 * @return the fromAccountNumber
	 */
	public Long getFromAccountNumber() {
		return fromAccountNumber;
	}
	/**
	 * @param fromAccountNumber the fromAccountNumber to set
	 */
	public void setFromAccountNumber(Long fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	/**
	 * @return the toAccountNumber
	 */
	public Long getToAccountNumber() {
		return toAccountNumber;
	}
	/**
	 * @param toAccountNumber the toAccountNumber to set
	 */
	public void setToAccountNumber(Long toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "TransferBalanceRequest [fromAccountNumber=" + fromAccountNumber + ", toAccountNumber=" + toAccountNumber
				+ ", amount=" + amount + "]";
	}
	
}
