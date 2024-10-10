@tag
Feature: ErrorValidation on login page of Ecommmerce website

	Background:
	Given I landed on Ecommerce Page

  @ErrorValidation
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed on Loginpage

    Examples: 
      | name 								| password 	  |
      | testkapil1@test.com | test1pil1A@ |
