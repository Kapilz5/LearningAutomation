@tag
Feature: Purchase the order from Ecommerce website

	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | name 								| password 		 | productName |
      | testkapil1@test.com | testkapil1A@ | ZARA COAT 3 |
