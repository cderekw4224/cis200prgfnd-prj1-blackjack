// Project 1
/* Proj1.java
 * Author: Derek Christensen
 * Date: 20180903
 * 
 * Program will simulate a Blackjack game between Dealer and User.
*/

import java. util.*;     // required to use Scanner and Random classes

public class Proj1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner (System.in);
		Random r = new Random();    // generate a random cardValNameArr
		
		String[] cardValSuitArr = { "Clubs", "Diamonds", "Hearts", "Spades" }; // suit
		String[] cardValNameArr = { "2", "3", "4", "5", "6", "7", "8", "9", "10",
				         "Jack", "Queen", "King", "Ace", "Ace1" };  // cardVal
		
		char hitOrStay = 'H';
		
		final int MAXcardValS = 22;  //Max number of cardVals in a hand
		int[] dealerHandArr = new int[MAXcardValS];
		int[] userHandArr = new int[MAXcardValS];
		
		int dealerSum = 0;
		int userSum = 0;
		
		int userHandArrIndex = 2;
		int dealerHandArrIndex = 2;
		
		String name;
		System.out.print("Enter player's name: ");
		name = in.nextLine();
		System.out.println();
		
		
		//**********
		//DEAL 1ST 2 CARDS
		//**********
		
		for (int i = 0; i < 2; i++) { // Iterate through both players
			int tempSum = 0;
			if (i == 0) {
				System.out.println("Dealer has:");
			}
			else if (i == 1) {
				System.out.println("\n" + name + " has:");
			}
			
			for (int j = 0; j < 2; j++) {  // assign 2 cardVals
				int suit = r.nextInt(4);  // randomly select suit cardValSuitArr
				int cardVal = r.nextInt(13);  // randomly select cardVal cardValNameArr
				
				if (i == 0) {
					dealerHandArr[j] = cardVal;
				}
				else if (i == 1) {
					userHandArr[j] = cardVal;
				}
				
				System.out.println("\t" + cardValNameArr[cardVal] + " of " + cardValSuitArr[suit]);
				if (cardVal < 9) {  // add face cardValNameArr of cardVal
					tempSum += cardVal+2;
				}
				else if (cardVal == 12) {  // add 11 for Ace
					tempSum += 11;
				}
				else {
					tempSum += 10;  // add 10 for 10 or face cardVal
				}
			} // end for
			
			if (i == 0) {
				dealerSum = tempSum;
				System.out.println("Dealer total: " + dealerSum);
				//System.out.println();
			}
			else if (i == 1) {
				userSum = tempSum;
				System.out.println(name + "'s total: " + userSum);
				//System.out.println();
			}
		} // end for
		
		
		//*********
		//DEAL EXTRA CARDS TO PLAYER
		//*********
		
		if (userSum != 21) {  // determine if user wants more cardVals
			do
			{	
				System.out.print("\nEnter (h)it or (s)tay: ");
				String newInput = in.nextLine();
				//response = (in.nextLine()).toUpperCase();
				String newResponse = newInput.toUpperCase();
				hitOrStay = newResponse.charAt(0);
				
				//System.out.println();
				
				if (hitOrStay == 'H') {
					// assign 1 cardVal
					int suit = r.nextInt(4);
					int cardVal = r.nextInt(13);  // randomly select cardVal cardValNameArr
					
					if (cardVal < 9) {
						userSum += cardVal+2;
					}
					else if (cardVal == 12) {
						userSum += 11;
					}
					else {
						userSum += 10;
					}
					
					System.out.println("\n" + name + " draws: " + cardValNameArr[cardVal] + " of " + cardValSuitArr[suit]);
					userHandArr[userHandArrIndex] = cardVal;
					userHandArrIndex++;
					
					// ACCOUNT FOR ACE IF OVER 21
					
					int checkAceIndex = 0;
					
					while (userSum > 21 && checkAceIndex < userHandArr.length) {
						if (userHandArr[checkAceIndex] == 12) {
							userSum = userSum - 10;
							userHandArr[checkAceIndex] = 13;
						}
						checkAceIndex++;
					}
					
					System.out.println("\n" + name + "'s total: " + userSum);

				}  // end if
			} while (userSum < 21 && hitOrStay == 'H'); //end do-while	
		}  //  end if
		
		//*********
		//DEAL EXTRA cardValS TO DEALER
		//*********
		
		if (userSum <= 21 && dealerSum < 17) {  // determine if dealer needs more cardVals
			do
			{	
				// assign 1 cardVal
				int suit = r.nextInt(4);
				int cardVal = r.nextInt(13);  // randomly select cardVal cardValNameArr
				
				if (cardVal < 9) {
					dealerSum += cardVal+2;
				}
				else if (cardVal == 12) {
					dealerSum += 11;
				}
				else {
					dealerSum += 10;
				}
				
				System.out.println("\nDealer draws: " + cardValNameArr[cardVal] + " of " + cardValSuitArr[suit]);
				dealerHandArr[dealerHandArrIndex] = cardVal;
				dealerHandArrIndex++;
				
				// ACCOUNT FOR ACE IF OVER 21
				
				int checkAceIndex = 0;
				
				while (dealerSum > 21 && checkAceIndex < dealerHandArr.length) {
					if (dealerHandArr[checkAceIndex] == 12) {
						dealerSum = dealerSum - 10;
						dealerHandArr[checkAceIndex] = 13;
					}
					checkAceIndex++;
				}
				
				System.out.println("\nDealer's total: " + dealerSum);
			
			} while (dealerSum < 17); //end do-while	
		}  //  end if
		
		System.out.println();
		System.out.println(name + "'s total: " + userSum);		
		System.out.println("Dealer total: " + dealerSum);
		//System.out.println();
		
		//***********
		//OUTCOME
		//***********
		
		if ((dealerSum <= 21) && (userSum > 21 || dealerSum >= userSum) && (userSum != 21)) {
			System.out.print("\nDealer wins.");
		}
		else if (dealerSum > 21 || userSum > dealerSum) {
			System.out.print("\n" + name + " wins!");
		}
		else if (dealerSum == 21 && userSum == 21) {
			System.out.print("\nIt's a draw!");
		}
		else {
			System.out.print("\nERROR");
		}
		
		System.out.println();
		
		// close the scanner
		//System.out.println("\nClosing Scanner...");
	    in.close();
	    //System.out.println("Scanner Closed.");
		
	} // end main
} // end class
