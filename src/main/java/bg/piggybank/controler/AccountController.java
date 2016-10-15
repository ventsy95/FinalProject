package bg.piggybank.controler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bg.piggybank.model.accounts.Account;
import bg.piggybank.model.accounts.AccountDAO;
import bg.piggybank.model.accounts.AccountType;
import bg.piggybank.model.accounts.CurrencyType;
import bg.piggybank.model.exeptions.AccountException;
import bg.piggybank.model.exeptions.FailedConnectionException;
import bg.piggybank.model.exeptions.IncorrectContactInfoException;
import bg.piggybank.model.exeptions.InvalidAccountException;
import bg.piggybank.model.exeptions.InvalidAccountInfoException;
import bg.piggybank.model.exeptions.UserInfoException;
import bg.piggybank.model.transactions.Transaction;
import bg.piggybank.model.user.User;
import bg.piggybank.model.user.UserDAO;

@Controller
public class AccountController {

	@Autowired
	private AccountDAO accountDao;
	@Autowired
	private UserDAO userDao;

	@RequestMapping(value = "/myAccounts", method = RequestMethod.GET)
	public String show(HttpServletRequest request, HttpServletResponse response) {
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountDao.getAllMyAccounts((String) request.getSession(false).getAttribute("username"));
		request.setAttribute("accounts", accounts);
		return "myAccounts";
	}

	@RequestMapping(value = "/makeAccount", method = RequestMethod.GET)
	public String create(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("currencies", accountDao.getAllCurrencies());
		request.setAttribute("accounts", accountDao.getAllAccountTypes());
		return "makeAccount";
	}

	@RequestMapping(value = "/makeAccount", method = RequestMethod.POST)
	public String createAccount(HttpServletRequest request, HttpServletResponse response)
			throws IncorrectContactInfoException {
		String username = (String) request.getSession(false).getAttribute("username");
		User user = userDao.getUserByUsername(username);
		String nameOfAccount = request.getParameter("name");
		CurrencyType currency = accountDao.getCurrencyType(request.getParameter("currency"));
		AccountType type = accountDao.getAccountType(request.getParameter("type"));
		double sum = Double.parseDouble(request.getParameter("sum"));
		Account account = new Account(nameOfAccount, type, currency, sum);
		try {
			accountDao.registrateUserAccount(user, account);
			request.setAttribute("successMessage", "Успешна трансакция");
			return show(request, response);
		} catch (InvalidAccountInfoException e) {
			request.setAttribute("errorMessage", "Невалиден тип на сметката");
			return create(request, response);
		} catch (InvalidAccountException e) {
			request.setAttribute("errorMessage", "Невалидна информация");
			return create(request, response);
		} catch (UserInfoException e) {
			return create(request, response);
		} catch (AccountException e) {
			return create(request, response);
		}
	}
}