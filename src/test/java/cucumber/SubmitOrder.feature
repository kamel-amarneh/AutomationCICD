@tag
Feature: Pusrchase the order

Background:
Given I landed on Ecomarce Page

@Regression

  Scenario Outline: Positive Test of submitting the order 
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    
    	Examples:
			| name                     |  password		| productName |
			| noritof738@coursora.com  |  Hello1234*	| ZARA COAT 3 |