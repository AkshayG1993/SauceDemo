@ignore
Feature: Inventory Page Functionality

  Background:
    Given the user is logged in as a standard user

  # Functional Scenarios


  Scenario: User can add multiple items to the cart
    When the user adds "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    Then the cart icon should show 2 items

  Scenario: User can remove an item from the cart
    Given the user has added "Sauce Labs Backpack" to the cart
    When the user removes "Sauce Labs Backpack" from the cart
    Then the cart icon should show 0 items

  Scenario: User can view item details by clicking on an item
    When the user clicks on "Sauce Labs Backpack"
    Then the item details page for "Sauce Labs Backpack" should be displayed

  Scenario: User can sort items by name and price
    When the user sorts items by "Name (A to Z)"
    Then items should be sorted alphabetically
    When the user sorts items by "Price (low to high)"
    Then items should be sorted by ascending price

  Scenario: User can log out from the Inventory page
    When the user logs out
    Then the login page should be displayed

  Scenario: User can navigate to the cart page from the Inventory page
    When the user clicks the cart icon
    Then the cart page should be displayed

  # Edge Cases
  Scenario: Inventory page loads with zero items
    Given the inventory is empty
    When the user visits the Inventory page
    Then a message "No items available" should be displayed

  Scenario: Inventory page loads with maximum allowed items
    Given the inventory has 100 items
    When the user visits the Inventory page
    Then all 100 items should be displayed

  Scenario: Item with missing image, name, or price is displayed
    Given an item is missing an image
    When the user visits the Inventory page
    Then a placeholder image should be shown for that item

    Given an item is missing a name
    When the user visits the Inventory page
    Then the item should display "Unnamed Item"

    Given an item is missing a price
    When the user visits the Inventory page
    Then the item should display "Price not available"

  Scenario: Item with special characters or long names is displayed
    Given an item with a long name or special characters exists
    When the user visits the Inventory page
    Then the item name should be displayed correctly

  Scenario: Item with price as zero or negative value
    Given an item with price "0" or "-5" exists
    When the user visits the Inventory page
    Then the item price should be displayed as "Free" or "Invalid price"

  Scenario: Add/remove item rapidly and check cart count consistency
    When the user rapidly adds and removes "Sauce Labs Backpack" 5 times
    Then the cart icon should show the correct item count

  Scenario: Add item to cart, then log out and log in again
    When the user adds "Sauce Labs Backpack" to the cart
    And the user logs out and logs in again
    Then the cart should be empty

  Scenario: Try to access Inventory page directly without login
    Given the user is not logged in
    When the user navigates to the Inventory page URL
    Then the login page should be displayed

  Scenario: Use browser back/forward after login/logout
    When the user logs out
    And the user clicks the browser back button
    Then the login page should be displayed

  Scenario: Network interruption while loading inventory
    Given the network is interrupted while loading the Inventory page
    Then an error message "Unable to load inventory" should be displayed

  Scenario: Manipulate inventory data via browser dev tools
    When the user tries to modify inventory data in the browser
    Then the changes should not be saved or reflected

  Scenario: Attempt to add out-of-stock item
    Given "Sauce Labs Backpack" is out of stock
    When the user tries to add it to the cart
    Then an "Out of stock" message should be displayed

  Scenario: Test with different user roles
    Given the user is logged in as a "locked_out_user"
    When the user visits the Inventory page
    Then an error message should be displayed

    Given the user is logged in as a "problem_user"
    When the user visits the Inventory page
    Then the page should load with known issues

    Given the user is logged in as a "performance_glitch_user"
    When the user visits the Inventory page
    Then the page should load slowly

  Scenario: Test with slow network or large images
    Given the network is slow and images are large
    When the user visits the Inventory page
    Then images should load progressively or show placeholders

  Scenario: Test with browser zoom in/out and different screen sizes
    When the user resizes the browser window or zooms in/out
    Then the Inventory page layout should remain usable

  Scenario: Test with browser autofill or form resubmission
    When the user refreshes the Inventory page after login
    Then the page should not resubmit the login form

  # Negative Scenarios
  Scenario: User tries to add item to cart when not logged in
    Given the user is not logged in
    When the user tries to add "Sauce Labs Backpack" to the cart
    Then the login page should be displayed

  Scenario: User tries to access inventory page with expired session
    Given the user's session has expired
    When the user visits the Inventory page
    Then the login page should be displayed

  Scenario: User tries to add/remove item when inventory service is down
    Given the inventory service is down
    When the user tries to add or remove an item
    Then an error message "Service unavailable" should be displayed

  # Security/Usability
  Scenario: Inventory page does not expose sensitive data in the URL or page source
    When the user inspects the Inventory page URL and source
    Then no sensitive data should be visible

  Scenario: Inventory page is not cached after logout
    When the user logs out
    And the user clicks the browser back button
    Then the login page should be displayed

  Scenario: All buttons and links are accessible via keyboard
    When the user navigates using the keyboard
    Then all buttons and links should be accessible

  Scenario: All images have alt text for accessibility
    When the user inspects images on the Inventory page
    Then all images should have descriptive alt text