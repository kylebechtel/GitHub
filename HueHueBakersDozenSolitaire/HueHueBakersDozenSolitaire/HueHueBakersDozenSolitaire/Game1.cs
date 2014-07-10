using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace HueHueBakersDozenSolitaire
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        Rectangle BackgroundR;
        Texture2D BGT;

        // Game Testing
        Boolean dragging = false;
        int x;
        int y;
        int cardWidth = 72;
        int cardHeight = 97;
        int screenWidth = 870;
        int screenHeight = 480;
        Deck testDeck = new Deck();
        List<Tableu> gameTableus = new List<Tableu>();
        List<Foundation> gameFoundations = new List<Foundation>();
        Card temp = new Card();
        Tableu tempTableu;
        Vector2[] testCardPlacement = new Vector2[52];
        Boolean readyToPlay = false;
        int playerScore = 0;
        SpriteFont gameFont; 
        int scoreDisplayX = 10;
        int scoreDisplayY = 435;
        int winDisplayX = 200;
        int winDisplayY = 240;
        Boolean win = false;
        Boolean help = false;
        Texture2D helpTexture;
        ///////////////////////////////////////////////////
        
        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";


        }

        /// <summary>
        /// Set the size of the screen,
        /// </summary>
        /// <param name="width"></param>
        /// <param name="height"></param>
        public void screenSize(int width, int height)
        {
            graphics.PreferredBackBufferWidth = width;
            graphics.PreferredBackBufferHeight = height;

            graphics.ApplyChanges();
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // Background Rectangle
            BackgroundR = new Rectangle(0, 0, graphics.PreferredBackBufferWidth, graphics.PreferredBackBufferHeight);

            // Make mouse pointer visible
            this.IsMouseVisible = true;

            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>


        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            BGT = Content.Load<Texture2D>("Untitled");
            spriteBatch = new SpriteBatch(GraphicsDevice);

            helpTexture = Content.Load<Texture2D>("help");

            LoadCards();

            SetupTableus();

            SetupFoundations();

            MoveKings();

            readyToPlay = true;
 
        }
             

        /// <summary>
        /// Load cards into testDeck
        /// </summary>
        private void LoadCards()
        {
            testDeck.addCard(new Card("Club", 1, Content.Load<Texture2D>("ClubsAce")));
            testDeck.addCard(new Card("Club", 2, Content.Load<Texture2D>("ClubsTwo")));
            testDeck.addCard(new Card("Club", 3, Content.Load<Texture2D>("ClubsThree")));
            testDeck.addCard(new Card("Club", 4, Content.Load<Texture2D>("ClubsFour")));
            testDeck.addCard(new Card("Club", 5, Content.Load<Texture2D>("ClubsFive")));
            testDeck.addCard(new Card("Club", 6, Content.Load<Texture2D>("ClubsSix")));
            testDeck.addCard(new Card("Club", 7, Content.Load<Texture2D>("ClubsSeven")));
            testDeck.addCard(new Card("Club", 8, Content.Load<Texture2D>("ClubsEight")));
            testDeck.addCard(new Card("Club", 9, Content.Load<Texture2D>("ClubsNine")));
            testDeck.addCard(new Card("Club", 10, Content.Load<Texture2D>("ClubsTen")));
            testDeck.addCard(new Card("Club", 11, Content.Load<Texture2D>("ClubsJack")));
            testDeck.addCard(new Card("Club", 12, Content.Load<Texture2D>("ClubsQueen")));
            testDeck.addCard(new Card("Club", 13, Content.Load<Texture2D>("ClubsKing")));
            testDeck.addCard(new Card("Diamond", 1, Content.Load<Texture2D>("DiamondsAce")));
            testDeck.addCard(new Card("Diamond", 2, Content.Load<Texture2D>("DiamondsTwo")));
            testDeck.addCard(new Card("Diamond", 3, Content.Load<Texture2D>("DiamondsThree")));
            testDeck.addCard(new Card("Diamond", 4, Content.Load<Texture2D>("DiamondsFour")));
            testDeck.addCard(new Card("Diamond", 5, Content.Load<Texture2D>("DiamondsFive")));
            testDeck.addCard(new Card("Diamond", 6, Content.Load<Texture2D>("DiamondsSix")));
            testDeck.addCard(new Card("Diamond", 7, Content.Load<Texture2D>("DiamondsSeven")));
            testDeck.addCard(new Card("Diamond", 8, Content.Load<Texture2D>("DiamondsEight")));
            testDeck.addCard(new Card("Diamond", 9, Content.Load<Texture2D>("DiamondsNine")));
            testDeck.addCard(new Card("Diamond", 10, Content.Load<Texture2D>("DiamondsTen")));
            testDeck.addCard(new Card("Diamond", 11, Content.Load<Texture2D>("DiamondsJack")));
            testDeck.addCard(new Card("Diamond", 12, Content.Load<Texture2D>("DiamondsQueen")));
            testDeck.addCard(new Card("Diamond", 13, Content.Load<Texture2D>("DiamondsKing")));
            testDeck.addCard(new Card("Hearts", 1, Content.Load<Texture2D>("HeartsAce")));
            testDeck.addCard(new Card("Hearts", 2, Content.Load<Texture2D>("HeartsTwo")));
            testDeck.addCard(new Card("Hearts", 3, Content.Load<Texture2D>("HeartsThree")));
            testDeck.addCard(new Card("Hearts", 4, Content.Load<Texture2D>("HeartsFour")));
            testDeck.addCard(new Card("Hearts", 5, Content.Load<Texture2D>("HeartsFive")));
            testDeck.addCard(new Card("Hearts", 6, Content.Load<Texture2D>("HeartsSix")));
            testDeck.addCard(new Card("Hearts", 7, Content.Load<Texture2D>("HeartsSeven")));
            testDeck.addCard(new Card("Hearts", 8, Content.Load<Texture2D>("HeartsEight")));
            testDeck.addCard(new Card("Hearts", 9, Content.Load<Texture2D>("HeartsNine")));
            testDeck.addCard(new Card("Hearts", 10, Content.Load<Texture2D>("HeartsTen")));
            testDeck.addCard(new Card("Hearts", 11, Content.Load<Texture2D>("HeartsJack")));
            testDeck.addCard(new Card("Hearts", 12, Content.Load<Texture2D>("HeartsQueen")));
            testDeck.addCard(new Card("Hearts", 13, Content.Load<Texture2D>("HeartsKing")));
            testDeck.addCard(new Card("Spades", 1, Content.Load<Texture2D>("SpadesAce")));
            testDeck.addCard(new Card("Spades", 2, Content.Load<Texture2D>("SpadesTwo")));
            testDeck.addCard(new Card("Spades", 3, Content.Load<Texture2D>("SpadesThree")));
            testDeck.addCard(new Card("Spades", 4, Content.Load<Texture2D>("SpadesFour")));
            testDeck.addCard(new Card("Spades", 5, Content.Load<Texture2D>("SpadesFive")));
            testDeck.addCard(new Card("Spades", 6, Content.Load<Texture2D>("SpadesSix")));
            testDeck.addCard(new Card("Spades", 7, Content.Load<Texture2D>("SpadesSeven")));
            testDeck.addCard(new Card("Spades", 8, Content.Load<Texture2D>("SpadesEight")));
            testDeck.addCard(new Card("Spades", 9, Content.Load<Texture2D>("SpadesNine")));
            testDeck.addCard(new Card("Spades", 10, Content.Load<Texture2D>("SpadesTen")));
            testDeck.addCard(new Card("Spades", 11, Content.Load<Texture2D>("SpadesJack")));
            testDeck.addCard(new Card("Spades", 12, Content.Load<Texture2D>("SpadesQueen")));
            testDeck.addCard(new Card("Spades", 13, Content.Load<Texture2D>("SpadesKing")));

        }

        

        /// <summary>
        /// Set up the tableus
        /// </summary>
        private void SetupTableus()
        {
            // Texture for tableu

            Deck randDeck = new Deck();
            randDeck = testDeck.swapCards(testDeck);

            Texture2D tableuBG = Content.Load<Texture2D>("Empty2");

            // Make Tableus
            gameTableus.Add(new Tableu(0, tableuBG, new Vector2(24, 14)));
            gameTableus.Add(new Tableu(1, tableuBG, new Vector2(118, 14)));
            gameTableus.Add(new Tableu(2, tableuBG, new Vector2(210, 14)));
            gameTableus.Add(new Tableu(3, tableuBG, new Vector2(300, 14)));
            gameTableus.Add(new Tableu(4, tableuBG, new Vector2(394, 14)));
            gameTableus.Add(new Tableu(5, tableuBG, new Vector2(484, 14)));
            gameTableus.Add(new Tableu(6, tableuBG, new Vector2(580, 14)));
            gameTableus.Add(new Tableu(7, tableuBG, new Vector2(72, 275)));
            gameTableus.Add(new Tableu(8, tableuBG, new Vector2(166, 275)));
            gameTableus.Add(new Tableu(9, tableuBG, new Vector2(258, 275)));
            gameTableus.Add(new Tableu(10, tableuBG, new Vector2(350, 275)));
            gameTableus.Add(new Tableu(11, tableuBG, new Vector2(442, 275)));
            gameTableus.Add(new Tableu(12, tableuBG, new Vector2(534, 275)));


            // Add cards to tableu from testDeck
            for (int i = 0; i < gameTableus.Count; i++)
            {
                for (int m = 0; m < 4; m++)
                {
                    // Add Card to Tableu
                    gameTableus.ElementAt(i).addCardToTableu(randDeck.getCard((4 * i) + m));
                    
                }
            }



            // Empty Deck by creating new instance.
            testDeck = new Deck();
        }

        /// <summary>
        /// Setup the foundations
        /// </summary>
        private void SetupFoundations()
        {
            // Texture for tableu
            Texture2D foundationBG = Content.Load<Texture2D>("Empty2");

            // Make Foundations
            gameFoundations.Add(new Foundation(0, foundationBG, new Vector2(706, 14)));
            gameFoundations.Add(new Foundation(1, foundationBG, new Vector2(706, 141)));
            gameFoundations.Add(new Foundation(2, foundationBG, new Vector2(706, 261)));
            gameFoundations.Add(new Foundation(3, foundationBG, new Vector2(706, 372)));
        }

        /// <summary>
        /// Make all kings the bottom most card of the tableus
        /// </summary>
        private void MoveKings()
        {
            for (int i = 0; i < gameTableus.Count; i++)
            {
                // Make it the first card in the tableu
                for (int j = 0; j < 3; j++)
                {
                    gameTableus.ElementAt(i).makeKingBottom(j);
                }
            }
        }


        private Boolean FoundationsAreFull()
        {
            int counter = 0; 

            for (int i = 0; i < gameFoundations.Count; i++)
            {
                if (gameFoundations.ElementAt(i).isFull())
                {
                    counter++;
                }
                
            }

            if (counter == 3) return true;
            else return false;
        }
        
        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here

        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            
            // get location of the mouse
            x = Mouse.GetState().X;
            y = Mouse.GetState().Y;

            // Allows the game to exit
            if (Keyboard.GetState().IsKeyDown(Keys.Escape))
                this.Exit();

            // Allows the game to reset
            if (Keyboard.GetState().IsKeyDown(Keys.R))
            {
                readyToPlay = false;
                win = false;
                this.gameTableus = new List<Tableu>(); ;
                this.gameFoundations = new List<Foundation>(); ;
                this.playerScore = 0;
                this.Initialize();
            }

            // Allows the game to reset
            if (Keyboard.GetState().IsKeyDown(Keys.H))
            {
                help = true;
            }
            else
            {
                help = false;
            }
                

            Boolean draggedToTableu = false;
            Boolean draggedToFoundation = false;

            //Right clicking will check if selected card can fit on a tableu, and if so, will move to legal tableu
           /* if (!dragging && (Mouse.GetState().RightButton == ButtonState.Pressed))
           
            * */


            // If not dragging and mouse left button is pressed, check if mouse is within card bounds.
            if (!dragging && (Mouse.GetState().LeftButton == ButtonState.Pressed))
            {
                tempTableu = null;
                temp = null;

                // Find original tableu
                for (int i = 0; i < gameTableus.Count; i++)
                {
                    if(gameTableus.ElementAt(i).contains(new Vector2(x,y)))
                    {
                        tempTableu = gameTableus.ElementAt(i);
                    }
                }

                // If tableu is not empty then get the top card
                if (tempTableu != null) temp = tempTableu.getTopCard();

                // If card exist under mouse pointer pick up the card
                if (temp!=null && tempTableu != null)
                {
                    dragging = true;
                }
            }

            // Stop dragging if button is released.
            if (dragging && Mouse.GetState().LeftButton == ButtonState.Released)
            { 
                dragging = false;

                // Moving card to Tableus
                for (int i = 0; i < gameTableus.Count; i++)
                {
                   if (gameTableus.ElementAt(i).contains(new Vector2(x, y)))
                    {
                        // check if move is valid
                        // topCard is not null (tableu is not empty)
                        // dragged card is 1 less than topCard
                        if (gameTableus.ElementAt(i).getTopCard() != null && gameTableus.ElementAt(i).getTopCard().is1LessThan(temp))
                        {
                            // moved to a new Tableu
                            draggedToTableu = true;
                            tempTableu.removeCard(temp);
                            gameTableus.ElementAt(i).addCardToTableu(temp);
                            
                        }
                    }
                }

                // Moving card to Foundation
                for (int i = 0; i < gameFoundations.Count; i++)
                {
                    if (gameFoundations.ElementAt(i).contains(new Vector2(x, y)))
                    {
                        // If the foundation is empty and the card an Ace, allow it to be place on an empty foundation.
                        if (gameFoundations.ElementAt(i).isEmpty() && temp.isAce())
                        {
                            // moved to a new Foundation
                            gameFoundations.ElementAt(i).addCardToFoundation(temp);
                            tempTableu.removeCard(temp);
                            draggedToFoundation = true;
                            playerScore += 10;
                        }
                        // else if the suit matched the foundation and it is one more than previous, allow it to be placed.
                        else if (gameFoundations.ElementAt(i).getTopCard().suitMatches(temp) && gameFoundations.ElementAt(i).getTopCard().is1MoreThan(temp))
                        {
                            // moved to a new Foundation
                            gameFoundations.ElementAt(i).addCardToFoundation(temp);
                            tempTableu.removeCard(temp);
                            draggedToFoundation = true;
                            playerScore += 10;
                        }
                    }
                }

                // Return card to original location.
                if (draggedToTableu == false && draggedToFoundation == false)
                {
                    tempTableu.addCardToTableu(temp);
                    tempTableu.removeCard(temp);
                }

            }

            DragCard();
           

            // remove all empty tableus
            RemoveEmptyTableus();
            int count = 0;
            //Check for Win
            for (int i = 0; i < gameFoundations.Count; i++)
            {
                if (gameFoundations.ElementAt(i).isFull())
                {
                    count++;
                }
            }
            if (count == 4) win = true;

            // Debug: Where is the mouse, sprite, and vector at.
             //try
             //   {
             //       System.Diagnostics.Debug.Print("Update: Mouse at: " + x + ", " + y + ". Card at: " + temp.getSprite().Bounds + ". Vector at: " + temp.getVector());
             //   }
             //   catch (Exception)
             //   {
             //       System.Diagnostics.Debug.Print("Update: Mouse at: " + x + ", " + y + ". Card is Null");
             //   }

            base.Update(gameTime);
        }
        
        private void DragCard()
        {
            // Pick the card up and move to mouse location when left clicked if true
            if (dragging)
            {
                if ((x > 0) && (y > 0) && (x + cardWidth / 2 < screenWidth) && (y + cardHeight / 2 < screenHeight + cardHeight / 2))
                {
                    Vector2 v = new Vector2(x - cardWidth / 2, y - cardHeight / 2);
                    temp.setVector(v);
                }
            }
        }

        private void RemoveEmptyTableus()
        {
            for (int i = 0; i < gameTableus.Count(); i++)
            {
                if (gameTableus.ElementAt(i).isEmpty())
                {
                    gameTableus.RemoveAt(i);
                }
            }
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {

            if (readyToPlay)
            {
                GraphicsDevice.Clear(Color.CornflowerBlue);
                spriteBatch.Begin();

                spriteBatch.Draw(BGT, BackgroundR, Color.White);

                // Draw Tableus
                for (int i = 0; i < gameTableus.Count; i++)
                {
                    spriteBatch.Draw(gameTableus.ElementAt(i).getTableuTexture(), gameTableus.ElementAt(i).getTableuVector(), Color.White);

                    for (int j = 0; j < gameTableus.ElementAt(i).getTableuSize(); j++)
                    {
                        Card tempDraw = gameTableus.ElementAt(i).getTableuCard(j);
                        spriteBatch.Draw(tempDraw.getSprite(), tempDraw.getVector(), Color.White);
                    }
                }

                // Draw Foundations
                for (int i = 0; i < gameFoundations.Count; i++)
                {
                    spriteBatch.Draw(gameFoundations.ElementAt(i).getFoundationTexture(), gameFoundations.ElementAt(i).getFoundationVector(), Color.White);

                    for (int j = 0; j < gameFoundations.ElementAt(i).getFoundationSize(); j++)
                    {
                        Card tempDraw = gameFoundations.ElementAt(i).getFoundationCard(j);
                        spriteBatch.Draw(tempDraw.getSprite(), tempDraw.getVector(), Color.White);
                    }
                }

                // Draw the card currently in hand
                if (dragging && temp != null)
                {
                    spriteBatch.Draw(temp.getSprite(), temp.getVector(), Color.White);
                }
               
             
                //Code to display score; each card placed on tableu counts as 10 points
            
                gameFont = Content.Load<SpriteFont>("Courier New");
                spriteBatch.DrawString(gameFont, "Score: \n" + playerScore.ToString(), new Vector2(scoreDisplayX, scoreDisplayY), Color.White);

                if (win)
                {
                    spriteBatch.DrawString(gameFont, "YOU WIN!!! Press 'R' to restart!", new Vector2(winDisplayX, winDisplayY), Color.White);
                }

                if (help)
                {
                    spriteBatch.Draw(helpTexture, new Vector2(50, 50), Color.White);
                }

                spriteBatch.End();

                base.Draw(gameTime); 
            }
        }
    }
}
