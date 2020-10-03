package com.example.currencywebservice;

import com.example.currencywebservice.model.Account;
import com.medium.article.GetConversionAmountResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.*;

public class SwissBank {

    static final String endPointGetAll = "http://localhost:8080/account";
    static final String endPointGetAccount = "http://localhost:8080/account/{id}";
    static final RestTemplate restTemplate = new RestTemplate();
    static final HttpHeaders headers = new HttpHeaders();


    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        System.out.println("Welcome to SwissBank, a safe heaven for you money ");
        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        while (run) {
            System.out.println("0: Exit");
            System.out.println("1: See all accounts");
            System.out.println("2: Get account");
            System.out.println("3: Update account");
            System.out.println("4: Create account");
            System.out.println("5: Delete account");
            System.out.println("6: Convert currency");
            switch (scanner.next()) {
                case "0":
                    run = false;
                    break;
                case "1":
                    getAllAccounts();
                    break;
                case "2":
                    System.out.println("Enter id of account...");
                    long id = scanner.nextLong();
                    System.out.println(getAccount(id));
                    break;
                case "3":
                    System.out.println("Enter id of account...");
                    String id2 = scanner.next();
                    System.out.println("Enter amount of account...");
                    double amount = scanner.nextDouble();
                    System.out.println("Enter currency of account...");
                    String currency = scanner.next();
                    System.out.println(putAccount(id2, amount, Enum.valueOf(Account.Currency.class, currency)));
                    break;
                case "4":
                    System.out.println("Enter amount of account...");
                    double amount2 = scanner.nextDouble();
                    System.out.println("Enter currency of account...");
                    String currency2 = scanner.next();
                    System.out.println(postAccount(amount2, Enum.valueOf(Account.Currency.class, currency2)));
                    break;
                case "5":
                    System.out.println("Enter id of account...");
                    String id3 = scanner.next();
                    System.out.println(deleteAccount(id3));
                    break;
                case "6":
                    System.out.println("Enter currency to convert from...");
                    String currencyFrom = scanner.next();
                    System.out.println("Enter currency to convert to...");
                    String currencyTo = scanner.next();
                    System.out.println("Enter amount to convert...");
                    double amountToConvert = scanner.nextDouble();
                    try{
                        System.out.println(convertCurrency(currencyFrom,currencyTo,amountToConvert));
                    }catch(Exception ex) {
                        ex.printStackTrace();
                        System.out.println("something went wrong");
                    }
                    break;
            }
        }
    }

    private static Account deleteAccount(String id) {
        return restTemplate.exchange(endPointGetAccount, HttpMethod.DELETE,null,Account.class, id).getBody();
    }

    public static void getAllAccounts() {
        Account[] accounts = restTemplate.getForEntity(endPointGetAll, Account[].class).getBody();
        for (Account account : accounts) {
            System.out.println(account.toString());
        }
    }

    private static Object convertCurrency(String currencyFrom,String currencyTo,double amount ) throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now =
                datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ConverterClientConfig.class);
        ConverterClient client = annotationConfigApplicationContext.getBean(ConverterClient.class);
        GetConversionAmountResponse response = client.getConversionAmount(currencyFrom, currencyTo, now, new BigDecimal(amount));
        return response.getGetConversionAmountResult().toString();
    }

    public static Account getAccount(long id) {
        return restTemplate.getForEntity(endPointGetAccount, Account.class, id).getBody();
    }

    public static Account putAccount(String id, double amount, Account.Currency currency) {

        Account account = new Account(Long.parseLong(id), currency, amount);
        return restTemplate.exchange(endPointGetAccount, HttpMethod.PUT,setHeadersAndBody(account),Account.class, id).getBody();

    }

    public static Account postAccount( double amount, Account.Currency currency) {

        Account account = new Account(currency, amount);
        return restTemplate.exchange(endPointGetAll, HttpMethod.POST,setHeadersAndBody(account),Account.class).getBody();

    }

    public static HttpEntity<Account> setHeadersAndBody(Account account) {
        // Managing HttpHeaders


        // Preparing the headers
        headers.set("BankRequest", "Bank Request");
        // Client wants to send request in JSON format
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // Client expects response in JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);

        // The response is a set of strings
        HttpEntity<Account> request = new HttpEntity<Account>(account, headers);
        return request;
    }
}
