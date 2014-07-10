using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace HueHueBakersDozenSolitaire
{
    class Card
    {
        int value; // Num/Char (A,2,3,4,5,6,7,8,9,10,J,Q,K)
        String suit; // Suit (Spade, Club, Heart, Diamond)
        Texture2D cardSprite; // Image for the card
        Vector2 vector; // Vector for moving sprite
        int currentTableu = -1;
        int previousTableu = -1;

        /// <summary>
        /// Main Card Object.
        /// </summary>
        public Card()
        {
            
        }

        /// <summary>
        /// Main Card Object
        /// </summary>
        /// <param name="s"></param>
        /// <param name="num"></param>
        /// <param name="img"></param>
        /// <param name="v"></param>
        public Card(String s, int num, Texture2D img, Vector2 v)
        {
            suit = s;
            value = num;
            cardSprite = img;
            vector = v;
        }

        public Card(String s, int num, Texture2D img)
        {
            suit = s;
            value = num;
            cardSprite = img;
            vector = new Vector2(0, 0);
        }

        /// <summary>
        /// String representation of Card
        /// </summary>
        /// <returns></returns>
        public String toString()
        {
            string c = value.ToString();

            switch (c)
            {
                case "1": c = "Ace"; break;
                case "11": c = "Jack"; break;
                case "12": c = "Queen"; break;
                case "13": c = "King"; break;

            }

            return c +" of "+ suit;
        }

        /// <summary>
        /// Get the Suit of a Card
        /// </summary>
        /// <returns>suit</returns>
        public String getSuit()
        {
            return suit;
        }

        /// <summary>
        /// Set the suit of a Card
        /// </summary>
        /// <param name="t"></param>
        public void setSuit(String t)
        {
            suit = t;
        }

        /// <summary>
        /// Get the value of a card.
        /// </summary>
        /// <returns>value</returns>
        public int getValue()
        {
            return value;
        }

        /// <summary>
        /// Set the value of a card.
        /// </summary>
        /// <param name="v"></param>
        public void setValue(int v)
        {
            value = v;
        }

        /// <summary>
        /// Get the image of the card
        /// </summary>
        /// <returns></returns>
        public Texture2D getSprite()
        {
            return cardSprite;
        }

        /// <summary>
        /// Set the image of a card
        /// </summary>
        /// <param name="img"></param>
        public void setSprite(Texture2D img)
        {
            cardSprite = img;
        }

        /// <summary>
        /// Get card vector
        /// </summary>
        /// <returns></returns>
        public Vector2 getVector()
        {
            return vector;
        }

        /// <summary>
        /// Set card vector
        /// </summary>
        /// <param name="v"></param>
        public void setVector(Vector2 v)
        {
            vector = v;
        }

        /// <summary>
        /// Check if Card equals another card.
        /// </summary>
        /// <param name="c"></param>
        /// <returns></returns>
        public Boolean Equals(Card c)
        {
            if (c == null) return false;
            else if ((suit.Equals(c.getSuit())) && (value == c.getValue()))
            {
                return true;
            }
            else return false;
        }

        /// <summary>
        /// Check if card is 1 less than given card
        /// </summary>
        /// <param name="c"></param>
        /// <returns></returns>
        public Boolean is1LessThan(Card c)
        {
            if (getValue()-c.getValue() == 1)
            {
                return true;
            }
            return false;
        }

        /// <summary>
        /// Check if card is 1 More than given card
        /// </summary>
        /// <param name="c"></param>
        /// <returns></returns>
        public Boolean is1MoreThan(Card c)
        {
            if (c.getValue()-getValue() == 1)
            {
                return true;
            }
            return false;
        }
        
        /// <summary>
        /// Checks if suit matches
        /// </summary>
        /// <param name="c"></param>
        /// <returns></returns>
        public Boolean suitMatches(Card c)
        {
            if (suit.Equals(c.getSuit()))
            {
                return true;
            }
            else return false;
        }

        /// <summary>
        /// Check if values match
        /// </summary>
        /// <param name="c"></param>
        /// <returns></returns>
        public Boolean valueMatches(Card c)
        {
            if (getValue() == c.getValue()) return true;
            else return false;
        }

        /// <summary>
        /// Check if card is Ace
        /// </summary>
        /// <returns></returns>
        public Boolean isAce()
        {
            if (value == 1) return true;
            else return false; 
        }

        /// <summary>
        /// Check if card is king
        /// </summary>
        /// <returns></returns>
        public Boolean isKing()
        {
            if (value == 13) return true;
            else return false; 
        }

        public int getTableu()
        {
            return currentTableu;
        }


        public void setTableu(int i){
            previousTableu = currentTableu;
            currentTableu = i;
        }

    }
}
