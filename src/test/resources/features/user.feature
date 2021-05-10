Feature: Users can create an account
	
	Scenario: A user can create an account when access the user POST route
		Given user data to create an account
		When user post then on the endpoint /users
		Then server return 201 and created user data