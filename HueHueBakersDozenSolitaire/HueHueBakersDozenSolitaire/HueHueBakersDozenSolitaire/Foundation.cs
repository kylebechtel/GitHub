using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace HueHueBakersDozenSolitaire
{
    class Foundation
    {
        /// <summary>
        /// List of cards in Foundation
        /// </summary>
        public List<Card> foundationList;

        /// <summary>
        /// Name of Foundation
        /// </summary>
        public int foundationName;

        /// <summary>
        /// Texture of Foundation
        /// </summary>
        public Texture2D foundationTexture;

        /// <summary>
        /// Vector of Foundation
        /// </summary>
        public Vector2 foundationVector;

        /// <summary>
        /// Construct a new Foundation with Texture at Vector
        /// </summary>
        public Foundation()
        {
            foundationList = new List<Card>();
            foundationVector = new Vector2(0, 0);
            foundationName = 0;
        }

        /// <summary>
        /// Construct new Foundation at Vector (0,0)
        /// </summary>
        /// <param name="name"></param>
        /// <param name="t"></param>
        /// <param name="v"></param>
        public Foundation(int name, Texture2D t, Vector2 v)
        {
            foundationList = new List<Card>();
            foundationName = name;
            foundationTexture = t;
            foundationVector = v;
        }

        /// <summary>
        /// String representation of Foundation
        /// </summary>
        /// <returns></returns>
        public String toString()
        {
            return "Foundation " + foundationName;
        }

        public int getFoundationSize()
        {
            return foundationList.Count;
        }

        /// <summary>
        /// Add card c to foundation
        /// </summary>
        /// <param name="x"></param>
        public void addCardToFoundation(Card c)
        {
            c.setVector(new Vector2((int)foundationVector.X, (int)foundationVector.Y));
            foundationList.Add(c);
        }

        /// <summary>
        /// Remove card c from foundation
        /// </summary>
        /// <param name="x"></param>
        public void removeCard(Card c)
        {
            //for (int i = 0; i < foundationList.Count; i++)
            //{
            //    if (foundationList.ElementAt(i).Equals(c))
            //    {
            //        foundationList.RemoveAt(i);
            //    }
            //}

            foundationList.RemoveAt(getFoundationSize() - 1);

        }

        /// <summary>
        /// Return card at position
        /// </summary>
        /// <param name="i"></param>
        /// <returns></returns>
        public Card getFoundationCard(int i)
        {
            try
            {
                return foundationList.ElementAt(i);
            }
            catch
            {
                return null;
            }
        }

        /// <summary>
        /// Check if vector is within bounds of foundation
        /// </summary>
        /// <param name="v"></param>
        /// <returns></returns>
        public Boolean contains(Vector2 v)
        {
            Rectangle c = new Rectangle((int)foundationVector.X, (int)foundationVector.Y, 72, 97);
            if (c.Contains((int)v.X, (int)v.Y))
            {
                return true;
            }
            return false;
        }

        /// <summary>
        /// Get Foundation Vector
        /// </summary>
        /// <returns></returns>
        public Vector2 getFoundationVector()
        {
            return foundationVector;
        }

        /// <summary>
        /// Set Foundation vector
        /// </summary>
        /// <param name="v"></param>
        public void setFoundationVector(Vector2 v)
        {
            foundationVector = v;
        }

        /// <summary>
        /// Get Foundation Texture
        /// </summary>
        /// <returns></returns>
        public Texture2D getFoundationTexture()
        {
            return foundationTexture;
        }

        /// <summary>
        /// Set Foundation vector
        /// </summary>
        /// <param name="t"></param>
        public void setFoundationTexture(Texture2D t)
        {
            foundationTexture = t;
        }

        /// <summary>
        /// Get Foundation Name
        /// </summary>
        /// <returns></returns>
        public int getFoundationName()
        {
            return foundationName;
        }

        /// <summary>
        /// Set Foundation vector
        /// </summary>
        /// <param name="n"></param>
        public void setFoundationName(int n)
        {
            foundationName = n;
        }

        /// <summary>
        /// Return the top card of foundation
        /// </summary>
        /// <returns></returns>
        public Card getTopCard()
        {
            // empty
            if (foundationList.Count == 0)
            {
                return new Card("suit", 0, null);
            }

            else return foundationList.ElementAt(foundationList.Count - 1);
        }

        /// <summary>
        /// Check if tableu is empty
        /// </summary>
        /// <returns></returns>
        public Boolean isEmpty()
        {
            if (getFoundationSize() == 0) return true;
            else return false;
        }

        public Boolean isFull()
        {
            if (getFoundationSize() == 13) return true;
            else return false;
        }
    }
}
