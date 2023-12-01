# Poker Tracker App

## Project Proposal

My boyfriend and I host weekly poker nights with our friends and have been using the Notes app on our phones
to keep track of buy-ins and winnings at the end of the night. With every new game this can get tedious to set
up, so I want to design a project that we can use moving forward to keep track of all of our poker games and that
is easy for us to set up each time we play again. The application will allow us to create a new poker event and
add the players (want to be able to have a dropdown menu of all previous players if possible, but also be able
to add a new player if needed), the total dollar amount of buy-ins for each player (this should update the total
number for the night), the winnings/losses in dollar amount for each player at the end of the event, the best 
hand played for the event (with the associated player), the time of each game (to keep track of winnings/hour), 
along with all-of-time statistics to look at how each player has done since they first started playing.


## User Stories:
- As a user, I want to be able to add a player to an existing poker game
- As a user, I want to be able to add a new poker event to my total collection of poker events
- As a user, I want to be able to view the list of poker events in my poker collection
- As a user, I want to be able to remove a player from an existing poker game
- As a user, I want to be able to remove a poker event from my poker collection
- As a user, I want to be able to select a poker event from my collection and view list the players that played in that 
poker event and their associated statistics, including buy-ins, cash-outs, and total earnings
- As a user, I want to be able to save my poker game collection to file (if I so choose)
- As a user, I want to be able to load my poker game collection from file (if I so choose)

User stories that will be implemented in the future:
- As a user, I want to be able to select a poker event from my collection and view list the players that played in that
  poker event and their associated statistics, including buy-ins, cash-outs, and total earnings

## Instructions for Grader:
- You can generate the first required action related to adding Xs to a Y by clicking the button labelled
    "New Poker Game" to add a new poker game to the poker game collection
- You can generate the second required action related to adding Xs to a Y by selecting a poker game from the list on 
  the left and then clicking the button labelled "Remove Poker Game" to remove the selected poker game
- You can locate my visual component by running the application, there is an image of cards in the top right corner
- You can save the state of my application by clicking the button labelled "Save Poker Collection"
- You can reload the state of my application by clicking the button labelled "Load Poker Collection"

## Phase 4: Task 2
Fri Dec 01 03:07:13 PST 2023
10/SEP/2023 poker game added to poker game collection
Fri Dec 01 03:07:24 PST 2023
10 added to totalBuyIns for Johnny
Fri Dec 01 03:07:24 PST 2023
10 added to totalCashOuts for Johnny
Fri Dec 01 03:07:24 PST 2023
Johnny added to 10/SEP/2023 poker game.
Fri Dec 01 03:07:24 PST 2023
10 added to buyIns for 10/SEP/2023poker game.
Fri Dec 01 03:07:24 PST 2023
10 added to cashOuts for 10/SEP/2023poker game.
Fri Dec 01 03:16:39 PST 2023
10/SEP/2023 poker game removed from poker game collection

## Phase 4: Task 3
If I had more time to work on this project, I would refactor many of the methods in the Player and PokerGame classes so 
that they utilize an abstract method since there is a good amount of code duplication. I would also refactor the 
creation of a new poker game within the PokerGameGUI class since I now dislike the use of the JOptionPane in prompting 
the user for the date. Instead, I would want to have a single new JFrame open that the user could input the date and an 
initial player with their buy-in and cash-out values, with functionality to then add more "add player" JTextFields if 
the user wished to do so. I would also split up some of the longer methods so that they have helper functions to do a 
specific part (eg. in the ApplicationGUI constructor I would make a call to createButtons() which would be a helper 
function that creates each button, rather than keeping each call to make a button in the constructor).