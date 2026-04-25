Feature: Login Functionality

Background: 
	Given the user is on the sauceDemo login page


Scenario: Successful login with valid credentials 
	When the user enters username "standard_user" and password "secret_sauce"
	Then the user should be redirected to the "Products" header

	
Scenario: Login should fail with invalid credentials
	When the user enters username "locked_out_user" and password "wrong_password"
	Then then user should see the error message
	
Scenario: Add a product to the cart
	When the user enters username "standard_user" and password "secret_sauce"
	And the user adds the backpack to the cart
	Then the cart badge should show "1"