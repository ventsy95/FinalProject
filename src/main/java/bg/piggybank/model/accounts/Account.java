package bg.piggybank.model.accounts;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.iban4j.*;


import bg.piggybank.model.exeptions.*;
import bg.piggybank.model.BasicInfo;

public class Account extends BasicInfo {
	private String name;
	private String IBAN;
	private AccountType type;
	private CurrencyType currency;
	private List<Card> cards = new ArrayList<Card>();

	public Account(String name, String IBAN, AccountType type, CurrencyType currency, double sum) {
		super(sum);
		try {
			if (isValidString(name)) {
				this.name = name;
			} else {
				throw new InvalidAccountInfoException("Invalid name");
			}
//		
			if (isValidType(type)) {
				this.type = type;
			} else {
				throw new InvalidAccountInfoException("Invalid type");
			}
			if (isValidType(currency)) {
				this.currency = currency;
			} else {
				throw new InvalidAccountInfoException("Invalid currency");
			}
			setIBAN();
			
		} catch (InvalidAccountInfoException e) {
			e.printStackTrace();
		}
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(this.cards);
	}

	public void addCard(Card card) {
		if (card != null) {
			this.cards.add(card);
		} else {
			System.out.println("Card doesn't exist");
		}
	}

	private boolean isValidType(Object type) {
		if (type != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isValidString(String string) {
		if (string != null && !string.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValidIBAN(String IBAN) {
		String ibanPattern = "^[DE]{2}([0-9a-zA-Z]{20})$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ibanPattern);
		java.util.regex.Matcher m = p.matcher(IBAN);
		return m.matches();
	}
	
	public double increaseAmount(double sumToAdd) {
		if (sumToAdd > 0) {
			this.sum += sumToAdd;
		}
		return this.sum;
	}
	
	public double decreaseAmount(double sumToRemove) throws NotEnoughMoneyException {		
		if (sumToRemove > 0 && sumToRemove < this.sum) {
			this.sum -= sumToRemove;
		} else { 
			throw new NotEnoughMoneyException("Not enough money to make a transaction");
		}
		return this.sum;
	}

	public static String cryptIban(String iban) {
		// Create key and cipher
		String enc = null;
		try {
			String key = "Bar12345Bar12345";
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(iban.getBytes());

			StringBuilder sb = new StringBuilder();
			for (byte b : encrypted) {
				sb.append((char) b);
			}

			// the encrypted String
			enc = sb.toString();
			// System.out.println("encrypted:" + enc);

		} catch (Exception e) {
			System.out.println("problem");
		}

		return enc;
	}

	public static String decryptIban(String iban) {
		// Create key and cipher
		String decrypted = null;
		try {
			String key = "Bar12345Bar12345"; // 128 bit key
			// Create key and cipher
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(iban.getBytes());

			StringBuilder sb = new StringBuilder();
			for (byte b : encrypted) {
				sb.append((char) b);
			}
			// the encrypted String
			String enc = sb.toString();

			// now convert the string to byte array
			// for decryption
			byte[] bb = new byte[enc.length()];
			for (int i = 0; i < enc.length(); i++) {
				bb[i] = (byte) enc.charAt(i);
			}

			// decrypt the text
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decrypted = new String(cipher.doFinal(bb));
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return decrypted;
	}
	
	void setIBAN(){
		Integer number= 11000064 + AccountDAO.getNumberOfAccounts() ;
		String accountNumber= number.toString();
		Iban iban= new Iban.Builder().countryCode(CountryCode.BG).bankCode("BNBG").branchCode("4545").accountType("20").accountNumber(accountNumber).build() ;
		this.IBAN = iban.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIBAN() {
		return IBAN;
	}

	public AccountType getType() {
		return type;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

}