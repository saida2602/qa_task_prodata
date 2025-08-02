Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given I open the login page
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page
    And I select product id to the cart:
      | 0 |
      | 3 |
    And I go to the cart page
    Then I should be on the cart page
    And I go to the checkout page
    Then I should be on the checkout page
    And I enter firstname "Saida"
    And I enter lastname "Aliyeva"
    And I enter postal-code "123"
    And I click continue button
    Then I should be redirected to the cart page
    And I click finish order button
    Then I should be redirected to the checkout-complete page