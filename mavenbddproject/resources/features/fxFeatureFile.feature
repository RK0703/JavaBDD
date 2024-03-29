Feature: Login to FrontierX Application 
 
Background: 
   Given User is on Frontier X Web Login page "Stage"
  
   @ValidCredentials
   Scenario: Login with valid credentials
      
    When User enters username password 
    Then User should be able to login sucessfully and new page open

    Examples:
  | userName   | passWord    | errorMessage                       |
  | quality+automation@fourthfrontier.com        | P@ssw0rd  | Valid credentials |
    