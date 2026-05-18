@hybrid
Feature: Hybrid API-UI Chaining

Scenario: Fetch user data from API and perform UI Login
	Given I fetch the user details for ID 2 from the API
	And I navigate to the SauceDemo login page
	When I login with the user name fetched from API and password "secret_sauce"
	