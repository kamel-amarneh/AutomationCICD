@tag
Feature: Error validation

@tag2

  Scenario Outline: Nigative Test of login 
    Given I landed on Ecomarce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed on LoginPage
    
    	Examples:
			| name                     |  password		|
			| 1noritof738@coursora.com  |  Hello1234*	|