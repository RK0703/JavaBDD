Feature: Search Activity in FrontierX Application 
 
Background: 
   Given User is on Frontier X Web Home page "Stage"
  
   @ValidCredentials
   Scenario: Verify Users can seatch Records
      
    When User enters activity in seach text box  
    Then User should be able to see searched activity 

   
    
    Scenario: Verify Training Load Arrow is Expandable 
      
    When User Clicks on Training load Arrow in "Stage"
    Then Verify that Days Time line is displayed
    
    