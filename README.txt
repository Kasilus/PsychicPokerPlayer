# PsychicPokerPlayer

  This app chooses the best poker combination for a player, who is psychic.

	He has 5 cards in his hand, for example : 2H AD 5H AC 7H ( 2 Hearts, Ace Diams, 5 Hearts, Ace Clubs, 7 Hearts).
But he also can see top five cards on the top of deck, for example : AH 6H 9H 4H 3C ( Ace Hearts, 6 Hearts, 9 Hearts, 4 Hearts,
3 Clubs). Order in the deck starts from the first card in input (AH).
	So, our player can swap his cards with cards on the top before game starts. He can change any cards from his hand with top 
cards, but with one rule : any card (cards) from his hand with an appropriate number of cards from top, keeping correct order.

Example 1 : He left all cards.
Example 2 : 2H AC 7H from his hand to the top. Now his hand seems like AD 5H AH 6H 9H
Example 3 : He swaped all cards.

The main purpose for our player - get the best combination from 32 possible.
Example 4 ( best combination for him ) : 2H AD 5H AC from his hand to the top. The best combination is in his hand now : 
7H AH 6H 9H 4H = flush

Main characters and designations in app:
1) Faces : 2-9, T=10, J=Jack, Q=Queen, K=King, A=Ace.
2) Suits : C=Clubs, D=Diamonds, H=Hearts, S=Spades.
3) Combinations ( from the strongest to the weakest ) : RoyalFlush, StraightFlush, FourOfAKind, FullHouse, Flush,
    Straight, ThreeOfAKind, TwoPairs, OnePair, HighestCard.
    
Input : 
  You can input how many lines you want. Input is finished, when you input empty line.
Output :
  Hand: {your cards} Deck: {cards on the top of deck} Best hand: {best hand}
Exception output :
  Incorrect input!
  
  
Example 1 : 
  Input 1 :
AC 2D 9C 3S KD 5S 4D KS AS 4C
  
First five cards are in the player hand, other five are on the top of deck ( 5S - top card in the deck)

  Output 1 :
Hand: AC 2D 9C 3S KD Deck: 5S 4D KS AS 4C Best hand: straight
    
    
Example 2 :
  Input 2 : 
KS AH 2H 3C 4H KC 2C TC 2D AS
AH 2C 9S AD 3C QH KS JS JD KD
    
  Output 2 : 
Hand: KS AH 2H 3C 4H Deck: KC 2C TC 2D AS Best hand: three-of-a-kind
Hand: AH 2C 9S AD 3C Deck: QH KS JS JD KD Best hand: two-pairs
    

Example 3 ( all combinations ) :
  Input 3 : 
TH JH QC QD QS QH KH AH 2S 6S
TH JH QC QD QS QH KH 9H 2S 6S
2H 2S 3H 3S 3C 2D 3D 6C 9C TH
2H 2S 3H 3S 3C 2D 9C 3D 6C TH
2H AD 5H AC 7H AH 6H 9H 4H 3C
AC 2D 9C 3S KD 5S 4D KS AS 4C
KS AH 2H 3C 4H KC 2C TC 2D AS
AH 2C 9S AD 3C QH KS JS JD KD
6C 9C 8C 2D 7C 2H TC 4C 9S AH
3D 5S 2H QD TD 6S KH 9H AD QH

  Output 3 :
Hand: TH JH QC QD QS Deck: QH KH AH 2S 6S Best hand: royal-flush
Hand: TH JH QC QD QS Deck: QH KH 9H 2S 6S Best hand: straight-flush
Hand: 2H 2S 3H 3S 3C Deck: 2D 3D 6C 9C TH Best hand: four-of-a-kind
Hand: 2H 2S 3H 3S 3C Deck: 2D 9C 3D 6C TH Best hand: full-house
Hand: 2H AD 5H AC 7H Deck: AH 6H 9H 4H 3C Best hand: flush
Hand: AC 2D 9C 3S KD Deck: 5S 4D KS AS 4C Best hand: straight
Hand: KS AH 2H 3C 4H Deck: KC 2C TC 2D AS Best hand: three-of-a-kind
Hand: AH 2C 9S AD 3C Deck: QH KS JS JD KD Best hand: two-pairs
Hand: 6C 9C 8C 2D 7C Deck: 2H TC 4C 9S AH Best hand: one-pair
Hand: 3D 5S 2H QD TD Deck: 6S KH 9H AD QH Best hand: highest-card
  
  

