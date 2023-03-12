# Poker-Hands-Kata

The Program for accomplishing the Kata is developed with a number of classes organized by the following structure:

1) ***PokerHands.java*** is the main class which provides the compare() method for ranking the Poker hands of two players. It calls a listof pattern matchers for the different categories of poker hands. It then identify if there is any one player is of a higher category and becomes winner. If both players are of same category, it calls the ranking method of that respective category to compare and identify the winner.

2) A **Player class and Winner class** are created and grouped into the **Players folder**. The Player class will instantiate each player object,and provide the key information about the cards on hand of each player. One of the player will become the winner. A Winnder extends from the Player class,and it has additional information of the category identifed and the winning card and suit (hence the trump card)

3)  Enum have been created for the **Card values, Card suit and Categories**. These provide immediate codes to setup a score of each card, and each category (ranking from High Card as lowest, to Straight Flush highest).

4) **A Matcher class for each category** is created e.g. StraightFlishCategory, FullHouseCategory, etc., all these classes are grouped into the **Category package/folder**. An **ICategory** interface is developed to define method signature for matching and ranking (two methods) of each player's Poker hand. All the matcher classes implement the ICategory interface.

6) A list of **common tools** used by most of the Matcher classes is identified. For examples, tools for lookup pair, three-of-a-kind, four-of-a-kind, tool for comparing the highest card value between a range of cards of each player, etc. These methods are kept in a **PokerTool class**, for future easy maintenance.

8) **A MessageDisplay class** is also created, with the role to format Pokerhands comparison result into the output message. This class is then separated into a 'View' folder, and it can be enhanced to suit future user interface requirement. It takes input about the winner class identity and then prepare the information to annouce the winner with his/her winning and category cards.

Development Process
===================
I have used TDD approach, starting from each category expected result, to test and code all the classes to complete the kata requirements. 

Throghout the TDD process, codes are continuingly enhanced and refactored, to adhere with DRY principle and arrive at modular structure. I have also applied SOLID principles to maximize maintainability and readability of the codes. In particular, the "Open for Extension and Close for Modification" SOLID principle is important for the program. For this purpose, all the eight Categories classes are inserted to a matcher lists of the Poker Hand class. The Poker hand class depends on common ICategory interface methods of each category class. This ensure that any modification on matching rules or score of any category will not impact the Poker hand application or other category matches. In future if any additional category is to be added, similarly it can be coded as an extension and will not require change in logic of all other classes.

Testing of the Program
======================
The **PokerHandCompareTest class in Test folder** includes simple test and parameterized test cases, using csv files in resources folder, for testing the program. The test class can be run to verify program passes all 50+ test cases. The test cases for invalid input of poker hands are also included, in the InvalidCard csv file. The Poker Hand program will throw exceptions if any invalid card value, suit or number of cards are input.
