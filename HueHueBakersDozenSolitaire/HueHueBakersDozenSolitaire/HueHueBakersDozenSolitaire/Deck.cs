using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace HueHueBakersDozenSolitaire
{
  
    class Deck
    {

        private List<Card> cardList;
        
        /// <summary>
        /// Deck constructor
        /// </summary>
        public Deck()
        {
            cardList = new List<Card>();
        }

        /// <summary>
        /// Add card c to list
        /// </summary>
        /// <param name="x"></param>
        public void addCard(Card c)
        {
            cardList.Add(c);
        }

        /// <summary>
        /// Get card at position
        /// </summary>
        /// <param name="position"></param>
        /// <returns></returns>
        public Card getCard(int position)
        {
            return cardList.ElementAt(position);
        }

        /// <summary>
        /// Returns the card at a given vector
        /// </summary>
        /// <param name="v"></param>
        /// <returns></returns>
        public Card getCardAtVector(Vector2 v)
        {
            for(int i = 0;i<cardList.Count;i++)
            {
                if( cardList.ElementAt(i).getVector().Equals(v))
                {
                    return cardList.ElementAt(i);
                }
            }
            return null;
        }

        /// <summary>
        /// Return a card if given point is in card bounds
        /// </summary>
        /// <param name="x"></param>
        /// <param name="y"></param>
        /// <returns></returns>
        public Card getCardAtBounds(int x, int y) 
        {
            for (int i = 0; i < cardList.Count; i++)
            {
                Rectangle r = new Rectangle((int) cardList.ElementAt(i).getVector().X, (int)cardList.ElementAt(i).getVector().Y, 72, 97);

                if (r.Contains(new Point(x,y)))
                {
                    return cardList.ElementAt(i);
                }
            }
            return null;
        }

        /// <summary>
        /// Remove card from Deck
        /// </summary>
        /// <param name="position"></param>
        public void removeCard(int position)
        {
            cardList.RemoveAt(position);
        }

        /// <summary>
        /// return length of the deck.
        /// </summary>
        /// <returns></returns>
        public int getDeckSize()
        {
            return cardList.Count;
        }

        /// <summary>
        /// randomizes the deck
        /// </summary>
        /// <param name="testDeck"></param>
        /// <returns></returns>
        public Deck swapCards(Deck testDeck)
        {
            int i = 0;
            int k;
            Deck tempDeck = new Deck();
            Random rand = new Random();
            while (i < 52)
            {
                i++;
                k = rand.Next(0, testDeck.getDeckSize());
                Card c = testDeck.getCard(k);
                tempDeck.addCard(c);
                testDeck.removeCard(k);
            }
            return tempDeck;
        }

    }
}
