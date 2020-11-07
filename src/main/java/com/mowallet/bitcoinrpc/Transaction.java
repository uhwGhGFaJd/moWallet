package com.mowallet.bitcoinrpc;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by uhwGhGFaJd@protonmail.com on 2020/10/29
 * Github       : https://github.com/uhwGhGFaJd
 */
public class Transaction {
	
	public enum Category {
		@SerializedName("receive")
	    RECEIVE,
		@SerializedName("send")
		SEND,
		@SerializedName("conflicted")
		CONFLICTED,
		@SerializedName("move")
	    MOVE;
	    
	    /*private String text;

	    Category(String text) {
	      this.text = text;
	    }

	    final String value() {
	        return this.text;
	    }

	    public String getText() {
	      return this.text;
	    }

	    public static Category fromString(String text) {
	      if (text != null) {
	        for (Category b : Category.values()) {
	          if (text.equalsIgnoreCase(b.text)) {
	            return b;
	          }
	        }
	      }
	      return null;
	    }*/
	}


	private BigDecimal fee;
	private BigDecimal amount;
	private long blockindex;
	private Category category;
	private long confirmations;
	private long time;
	private long timereceived;
	private long blocktime;
	private List<String> walletconflicts;
	private List<Transaction> details;
	private String address;
    private String txid;
    private long block;
    private String hex;
    private String blockhash;
    private String account;
    private String otheraccount;
    private String comment;
    private String to;
    
    
	public String getOtheraccount() {
		return otheraccount;
	}
	public Transaction setOtheraccount(String otheraccount) {
		this.otheraccount = otheraccount;
		return this;
	}
	public String getComment() {
		return comment;
	}
	public Transaction setComment(String comment) {
		this.comment = comment;
		return this;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public Transaction setFee(BigDecimal fee) {
		this.fee = fee;
		return this;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public Transaction setAmount(BigDecimal amount) {
		this.amount = amount;
		return this;
	}
	public long getBlockindex() {
		return blockindex;
	}
	public List<String> getWalletconflicts() {
		return walletconflicts;
	}
	public Transaction setWalletconflicts(List<String> walletconflicts) {
		this.walletconflicts = walletconflicts;
		return this;
	}
	public Transaction setBlockindex(long blockindex) {
		this.blockindex = blockindex;
		return this;
	}
	public Category getCategory() {
		return category;
	}
	public Transaction setCategory(Category category) {
		this.category = category;
		return this;
	}
	public long getConfirmations() {
		return confirmations;
	}
	public Transaction setConfirmations(long confirmations) {
		this.confirmations = confirmations;
		return this;
	}
	public String getAddress() {
		return address;
	}
	public Transaction setAddress(String address) {
		this.address = address;
		return this;
	}
	public String getTxid() {
		return txid;
	}
	public Transaction setTxid(String txid) {
		this.txid = txid;
		return this;
	}
	public long getBlock() {
		return block;
	}
	public Transaction setBlock(long block) {
		this.block = block;
		return this;
	}
	
	public String getHex() {
		return hex;
	}
	public Transaction setHex(String hex) {
		this.hex = hex;
		return this;
	}
	public String getBlockhash() {
		return blockhash;
	}
	public Transaction setBlockhash(String blockhash) {
		this.blockhash = blockhash;
		return this;
	}
	public List<Transaction> getDetails() {
		return details;
	}
	public Transaction setDetails(List<Transaction> details) {
		this.details = details;
		return this;
	}
	public String getAccount() {
		return account;
	}
	public Transaction setAccount(String account) {
		this.account = account;
		return this;
	}
	public long getTime() {
		return time;
	}
	public Transaction setTime(long time) {
		this.time = time;
		return this;
	}
	public Date getDateTime() {
		return new Date(getTime()*1000);
	}

	public long getTimereceived() {
		return timereceived;
	}
	public Transaction setTimereceived(long timereceived) {
		this.timereceived = timereceived;
		return this;
	}
	public long getBlocktime() {
		return blocktime;
	}
	public Transaction setBlocktime(long blocktime) {
		this.blocktime = blocktime;
		return this;
	}
	public String getTo() {
		return to;
	}
	public Transaction setTo(String to) {
		this.to = to;
		return this;
	}
    
    
}
