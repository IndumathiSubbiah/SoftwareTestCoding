@addAndDeleteItemTestProject
Feature: Add and Delete Items in Todo List App

  Background:
    Given Login using your github account

  #Scenario 1
  Scenario: Add and Delete Items in Todo List App

  And create 10 to do list with random strings
  Then upon completion log out
  And  Login again with the same account
  When Delete your list from 5 - 10
  Then Logout upon completion
  Then Close the Browser

