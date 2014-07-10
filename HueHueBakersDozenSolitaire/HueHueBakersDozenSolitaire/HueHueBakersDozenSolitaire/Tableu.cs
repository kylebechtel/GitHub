using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace HueHueBakersDozenSolitaire
{
    class Tableu
    {

        /// <summary>
        /// List of cards in Tableu
        /// </summary>
        public List<Card> tableuList;

        /// <summary>
        /// Name of Tableu
        /// </summary>
        public int tableuName;

        /// <summary>
        /// Texture of Tableu
        /// </summary>
        public Texture2D tableuTexture;

        /// <summary>
        /// Vector of Tableu
        /// </summary>
        public Vector2 tableuVector;

        /// <summary>
        /// Construct a new Tableu with Texture at Vector
        /// </summary>
        public Tableu()
        {
            tableuList = new List<Card>();
            tableuVector = new Vector2(0, 0);
            tableuName = 0;
        }

        /// <summary>
        /// Construct new Tableu at Vector (0,0)
        /// </summary>
        /// <param name="name"></param>
        /// <param name="t"></param>
        /// <param name="v"></param>
        public Tableu(int name, Texture2D t, Vector2 v)
        {
            tableuList = new List<Card>();
            tableuName = name;
            tableuTexture = t;
            tableuVector = v;
        }
        
        /// <summary>
        /// String representation of Tableu
        /// </summary>
        /// <returns></returns>
        public String toString()
        {
            return "Tableu " + tableuName;
        }

        public int getTableuSize()
        {
            return tableuList.Count;
        }

        /// <summary>
        /// Add card c to tableu
        /// </summary>
        /// <param name="x"></param>
        public void addCardToTableu(Card c)
        {
            Boolean inTableu = false;

            for (int i = 0; i < getTableuSize(); i++)
            {
                if (tableuList.ElementAt(i).Equals(c))
                {
                    inTableu = true;
                    break;
                }
            }

            if (inTableu)
            {
                c.setVector(new Vector2((int)tableuVector.X, (int)tableuVector.Y + ((getTableuSize()-1) * 20)));
            }

            else c.setVector(new Vector2((int)tableuVector.X, (int)tableuVector.Y + (getTableuSize() * 20)));
           
            c.setTableu(tableuName);

            tableuList.Add(c);
        }

        /// <summary>
        /// Remove card c from tableu
        /// </summary>
        /// <param name="x"></param>
        public void removeCard(Card c)
        {
            tableuList.RemoveAt(getTableuSize()-1);
        }

        /// <summary>
        /// Return card at position
        /// </summary>
        /// <param name="i"></param>
        /// <returns></returns>
        public Card getTableuCard(int i)
        {
            try
            {
                return tableuList.ElementAt(i);
            }
            catch
            {
                return null;
            }
        }

        /// <summary>
        /// Check if vector is within bounds of tableu
        /// </summary>
        /// <param name="v"></param>
        /// <returns></returns>
        public Boolean contains(Vector2 v)
        {
            int height = ((tableuList.Count-1)*20+77);
            Rectangle c = new Rectangle((int)tableuVector.X, (int)tableuVector.Y, 72, height);
            if (c.Contains((int)v.X, (int)v.Y))
            {
                return true;
            }
            return false;
        }

        /// <summary>
        /// Get Tableu Vector
        /// </summary>
        /// <returns></returns>
        public Vector2 getTableuVector()
        {
            return tableuVector;
        }

        /// <summary>
        /// Set Tableu vector
        /// </summary>
        /// <param name="v"></param>
        public void setTableuVector(Vector2 v)
        {
            tableuVector = v;
        }

        /// <summary>
        /// Get Tableu Texture
        /// </summary>
        /// <returns></returns>
        public Texture2D getTableuTexture()
        {
            return tableuTexture;
        }

        /// <summary>
        /// Set Tableu vector
        /// </summary>
        /// <param name="t"></param>
        public void setTableuTexture(Texture2D t)
        {
            tableuTexture = t;
        }

        /// <summary>
        /// Get Tableu Name
        /// </summary>
        /// <returns></returns>
        public int getTableuName()
        {
            return tableuName;
        }

        /// <summary>
        /// Set Tableu vector
        /// </summary>
        /// <param name="n"></param>
        public void setTableuName(int n)
        {
            tableuName = n;
        }

        /// <summary>
        /// Return the top card of tableu
        /// </summary>
        /// <returns></returns>
        public Card getTopCard()
        {
            try
            {
                return tableuList.ElementAt(tableuList.Count - 1);
            }
            catch (Exception)
            {
                
                throw;
            }
        }

        /// <summary>
        /// If this tableu has a king in it, then make it the bottom most card.
        /// </summary>
        public void makeKingBottom(int j)
        {
            Card king = new Card();
            List<Card> tempTableu = new List<Card>();
            int pos = 0;
            Boolean isKing = false;

           // Find where king is
            for (int i = j; i < getTableuSize(); i++)
            {
                if (tableuList.ElementAt(i).isKing())
                {
                    king = new Card(tableuList.ElementAt(i).getSuit(), tableuList.ElementAt(i).getValue(), tableuList.ElementAt(i).getSprite());
                    pos = i;
                    isKing = true;
                    break;
                }
            }

            if (isKing)
            {
                //Remove king
                tableuList.RemoveAt(pos);

                // Make temp Tableu
                tempTableu.AddRange(tableuList);

                // Empty tableu list;
                tableuList.RemoveRange(0, tableuList.Count);

                // Add king
                addCardToTableu(king);

                for (int i = 0; i < tempTableu.Count; i++)
                {
                    if (tempTableu.ElementAt(i) != null) addCardToTableu(tempTableu.ElementAt(i));
                } 
            }
               
        }

        /// <summary>
        /// Check if tableu is empty
        /// </summary>
        /// <returns></returns>
        public Boolean isEmpty()
        {
            if (getTableuSize() == 0) return true;
            else return false;
        }

    }
}
