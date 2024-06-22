package glue;

import account.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class AccountSteps {

    private Account account;

    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String accNo, String name) {
        account = new Account(accNo, name);
    }

    @And("^deposits are made$")
    public void depositsAreMade(DataTable depositsTable) {
        List<Map<String, String>> deposits = depositsTable.asMaps(String.class, String.class);
        for (Map<String, String> deposit : deposits) {
            String transactionId = deposit.get("Transaction");
            double amount = Double.parseDouble(deposit.get("Amount"));
            account.deposit(transactionId, amount);
        }
    }

    @And("^withdrawals are made$")
    public void withdrawalsAreMade(DataTable withdrawalsTable) {
        List<Map<String, String>> withdrawals = withdrawalsTable.asMaps(String.class, String.class);
        for (Map<String, String> withdrawal : withdrawals) {
            String transactionId = withdrawal.get("Transaction");
            double amount = Double.parseDouble(withdrawal.get("Amount"));
            account.withdraw(transactionId, amount);
        }
    }

    @When("^statement is produced$")
    public void statementIsProduced() {
        account.produceStatement();
    }

    @Then("^the statement includes \"([^\"]*)\"$")
    public void theStatementIncludes(String expectedText) {
        String statement = account.getStatement();
        assertTrue(statement.contains(expectedText));
    }

    @Then("^statement includes account details$")
    public void statementIncludesAccountDetails() {
        String statement = account.getStatement();
        assertTrue(statement.contains("Account: " + account.getAccountNumber()));
        assertTrue(statement.contains("Name: " + account.getAccountName()));
    }

    @Then("^balance is calculated on the statement$")
    public void balanceIsCalculatedOnTheStatement() {
        String statement = account.getStatement();
        assertTrue(statement.contains("Balance: " + account.getBalance()));
    }

    @Then("^statement includes transaction details$")
    public void statementIncludesTransactionDetails() {
        String statement = account.getStatement();
        for (String transactionId : account.getTransactions().keySet()) {
            assertTrue(statement.contains(transactionId));
        }
    }
}
