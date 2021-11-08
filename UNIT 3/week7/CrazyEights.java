import java.util.Scanner;

public class CrazyEights {

   private static final int NUM_SUITS = 4;
   private static final String HEARTS = "H";
   private static final String DIAMONDS = "D";
   private static final String CLUBS = "C";
   private static final String SPADES = "S";
   private static final double CARDS_PER_SUIT = 13;
   private static final String ACE = "A";
   private static final String JACK = "J";
   private static final String QUEEN = "Q";
   private static final String KING = "K";
   public static String TOPCARD = getCard();
   public static int cardsDrawn = 0; 

   public static void main(String[] args) {
      int p1Points = 0, c1Points = 0, c2Points = 0;
      Scanner in = new Scanner(System.in);
      

      while (!gameOver(p1Points, c1Points, c2Points)) { // while game isnt over the game will play new rounds
         String result = playRound(in);
         int firstDash = result.indexOf("-");
         int secondDash = result.indexOf("-", firstDash + 1);
         p1Points += Integer.parseInt(result.substring(0, firstDash));
         c1Points += Integer.parseInt(result.substring(firstDash + 1, secondDash));
         c2Points += Integer.parseInt(result.substring(secondDash + 1));
  
         System.out.println("Current Score: " + p1Points + " " + c1Points + " " + c2Points);
      }
   }

   private static String playRound(Scanner in) { // plays a round until end
      String p1Hand = "";
      String c1Hand = "";
      String c2Hand = "";

      for (int i = 0; i <5; i++){
         p1Hand += getCard() + " ";
         c1Hand += getCard() + " ";
         c2Hand += getCard() + " ";
      }
      p1Hand.trim();
      c1Hand.trim();
      c2Hand.trim();      
      
      while (p1Hand.length() > 0|| c1Hand.length() > 0|| c2Hand.length() > 0) {
         p1Hand = processPlayer(in, p1Hand, TOPCARD);
         c1Hand = processComputer(c1Hand, TOPCARD);
         c2Hand = processComputer(c2Hand, TOPCARD);
         p1Hand.trim();
         c1Hand.trim();
         c2Hand.trim();      
         
      }
      
      return getScore(p1Hand, c1Hand, c2Hand);
      // "20-20-20"; // Returns score
   }
  
   private static String getScore(String p1Hand, String c1Hand, String c2Hand) { // sets the score for each hand
      int score1 = 0;
      int score2 = 0;
      int score3 = 0;

      for (int i = 0; i < p1Hand.length() -1; i++){ 
         for (int j = 0; j < 10; j++){
            if (p1Hand.charAt(i) == j){
               score1 += j;
            }
         }
         if (p1Hand.substring(i, i+1).equals("J") || p1Hand.substring(i, i+1).equals("Q") || p1Hand.substring(i, i+1).equals("K")){
            score1 += 10;
         }
         if (p1Hand.substring(i, i+1).equals("8")){
            score1 += 40;
         }
      }
      for (int i = 0; i < c1Hand.length() -1; i++){
         for (int j = 0; j < 10; j++){
            if (c1Hand.charAt(i) == j){
               score1 += j;
            }
         }
         if (c1Hand.substring(i, i+1).equals("J") || c1Hand.substring(i, i+1).equals("Q") || c1Hand.substring(i, i+1).equals("K")){
            score1 += 10;
         }
         if (c1Hand.substring(i, i+1).equals("8")){
            score1 += 40;
         }
      }
      for (int i = 0; i < c2Hand.length() -1; i++){
         for (int j = 0; j < 10; j++){
            if (c2Hand.charAt(i) == j){
               score1 += j;
            }
         }
         if (c2Hand.substring(i, i+1).equals("J") || c2Hand.substring(i, i+1).equals("Q") || c2Hand.substring(i, i+1).equals("K")){
            score1 += 10;
         }
         if (c2Hand.substring(i, i+1).equals("8")){
            score1 += 40;
         }
      }


      return score1 + "-" + score2 + "-" + score3;
   }

   private static String getCard() { // generates card using getFace and getFace functions
      String card = getFace() + getSuit();
      return card;
   }

   private static String getSuit() { // Generates suit of card
      int suit = (int) (Math.random() * NUM_SUITS);

      if (suit == 0)
         return HEARTS;
      else if (suit == 1)
         return DIAMONDS;
      else if (suit == 2)
         return CLUBS;
      else
         return SPADES;
   
   }

   private static String getFace() { // Generates face of card 
      int suit = (int) (Math.random() * CARDS_PER_SUIT);
      if (suit >= 2 && suit <= 10)
         return suit + "";
      else if (suit == 1)
         return ACE;
      else if (suit == 11)
         return JACK;
      else if (suit == 12)
         return QUEEN;
      else
         return KING;

   }

   private static String processComputer(String hand, String topCard) { // How the computer runs

      String topCardSuit = topCard.substring(topCard.length()-1);
      String topCardRank = topCard.substring(0, topCard.length()-1);
         if (hand.indexOf(topCardSuit) >= 0){ // if they have a card of the suit on the top of the stock-pile and it is not an eight, they will play it
            
            if (hand.indexOf(topCardSuit) <= 2){ 
               topCard = hand.substring(0 , hand.indexOf(topCardSuit)+1);
               hand = hand.replace(topCard, "");
            } else if (hand.indexOf(topCardSuit) < hand.length()-2){
               topCard = hand.substring(hand.indexOf(topCardSuit) - 1, hand.indexOf(topCardSuit)+1);
               hand = hand.replace(topCard, "");
            } else {
               topCard = hand.substring(hand.indexOf(topCardSuit) -2);
               hand = hand.replace(topCard, "");
            }
         } else if (hand.indexOf(topCardRank) >= 0){ // if the hand has a FaceCard of the same rank
            topCard = hand.substring(hand.indexOf(topCardRank), hand.indexOf(topCardRank)+2);
            hand = hand.replace(topCard, "");
         } else {
            drawCardComputer(hand, topCard); // if cant play it draws a card using the drawCardComputer Function
         }

  
      
      CrazyEights.cardsDrawn = 0; // sets the cards drawn value to zero so the player cannot draw over max
      topCardValue(topCard.trim());
      System.out.println("Computer Played");
      return hand.trim();
   }

   private static String processPlayer(Scanner in, String hand, String topCard) {


      System.out.println("Your hand: " + hand + " Top Card: " + topCard);
      System.out.print("Which Card would you like to play? Enter [Draw] to Draw Card: ");
      String answer = in.nextLine().toUpperCase();


      if ((answer.substring(answer.length()-1).equals(topCard.substring(topCard.length()-1)) && (hand.indexOf(answer) >= 0)) || (answer.substring(answer.length()).equals(topCard.substring(topCard.length()))) && (hand.indexOf(answer) >= 0)){
         topCard = answer;
         hand = hand.replace(answer, "");

      } else if (answer.equals("DRAW")){ // if player enters draw it will draw card
            drawCardPlayer(in, hand, topCard);
      } else { // if incorrect input will re run the functiom
         System.out.println("Your Input is Incorrect, try again.");
         processPlayer(in, hand, topCard);
      }


      if (answer.substring(0, answer.length()-1).equals("8")){ //if card placed is an eight it will ask what suit it wants to change it to 
         System.out.println("What Suit Would You Like to Change to?: ");
         String answerTemp = in.nextLine().toUpperCase();
         
         if (answerTemp.equals("HEARTS") || answerTemp.equals("H")){
            topCard = topCard.replace(topCard.substring(topCard.length()-1), "H");
         } else if (answerTemp.equals("DIAMONDS") || answerTemp.equals("D")){
            topCard = topCard.replace(topCard.substring(topCard.length()-1), "D");
         } else if (answerTemp.equals("CLUBS") || answerTemp.equals("C")){
            topCard = topCard.replace(topCard.substring(topCard.length()-1), "C");
         } else if (answerTemp.equals("SPADES") || answerTemp.equals("S")){
            topCard = topCard.replace(topCard.substring(topCard.length()-1), "S");
         }

      }
      
      topCardValue(topCard);
      //String card = getCard();

      //hand += ' ' + card;
      //cardsDrawn++;
      //System.out.println("You Drew a " + card);
      return hand.trim();
   }

   private static void drawCardPlayer(Scanner in, String hand, String topCard) {
      if (CrazyEights.cardsDrawn < 5){
         hand += " " + getCard();
         cardsDrawn+=1;
         processPlayer(in, hand, topCard);
      } else if (CrazyEights.cardsDrawn >= 5){
         System.out.println("Max Cards Drawn");
      }
   }

   private static void drawCardComputer(String hand, String topCard) { // draws card for player
      if (CrazyEights.cardsDrawn < 5){
         hand += " " + getCard();
         cardsDrawn+=1;
         processComputer(hand, topCard);
      } else if (CrazyEights.cardsDrawn >= 5){
         topCardValue(topCard);
         System.out.println("Max Cards Drawn");
      }
   }

   private static void topCardValue(String topCard) { // sets the topCard value
      CrazyEights.TOPCARD = topCard.trim();
   }

   private static boolean gameOver(int p1Points, int c1Points, int c2Points) { // if the score is over or equal to 100 for someone, the game is over
      if (p1Points >= 100 || c1Points >= 100 || c2Points >= 100){
         return true;
      } else {
        return false;
      }
   }
   
}