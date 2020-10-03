###Created by:
* Martin Høigaard Rasmussen
* Simon Bojesen
* Kenneth Leo Hansen

### Summary
This is VERY simple bank application for storing different accounts. Each Account has an accountNumber, a currency and amount which describes how much money is on the account.
We have implemented our own REST API, which allows the user to see all of his accounts. He can also use the api to create, delete or edit his accounts.
We have also implemented a SOAP client, that consumes an online currency exchange service. We use this service to convert a specified currency to another currency.

###Installation Instructions
1. Install maven and add it as an environment variable.
2. In the terminal type: mvn compile.
3. Run CurrencyWebServiceApplication
4. Run SwissBank
5. Follow the instructions int the console for SwissBank

