Feature: The user can change their own password

  Rule: As a user
  I want to be able to change my own password within the guidelines of password policy
  So that the integrity of my account security requirements are met.

    Scenario: Successful password change
      Given the user is logged in
      And the user navigates to the change password page
      When the user enters their username "currentUsername"
      And the user enters their current password "currentPassword"
      And the user enters a new password "newPassword"
      And the user verifies the new password by re-typing "newPassword"
      And the user clicks on the "Submit" button
      Then the password should be successfully changed
      And a confirmation message should be displayed

    Scenario: New password does not meet length requirement
      Given the user is logged in
      And the user navigates to the change password page
      When the user enters their username "currentUsername"
      And the user enters their current password "currentPassword"
      And the user enters a new password "short"
      And the user verifies the new password by re-typing "short"
      And the user clicks on the "Submit" button
      Then the password should not be changed
      And an error message should be displayed indicating the password must be at least 3 characters long

    Scenario: New password does not contain a digit
      Given the user is logged in
      And the user navigates to the change password page
      When the user enters their username "currentUsername"
      And the user enters their current password "currentPassword"
      And the user enters a new password "password"
      And the user verifies the new password by re-typing "password"
      And the user clicks on the "Submit" button
      Then the password should not be changed
      And an error message should be displayed indicating the password must contain at least one digit

    Scenario: New password and verify password do not match
      Given the user is logged in
      And the user navigates to the change password page
      When the user enters their username "currentUsername"
      And the user enters their current password "currentPassword"
      And the user enters a new password "newPassword123"
      And the user verifies the new password by re-typing "differentPassword"
      And the user clicks on the "Submit" button
      Then the password should not be changed
      And an error message should be displayed indicating the passwords do not match

    Scenario: Cancel password change
      Given the user is logged in
      And the user navigates to the change password page
      When the user clicks on the "Cancel" button
      Then the user should be redirected to the account settings page
      And the password should not be changed
