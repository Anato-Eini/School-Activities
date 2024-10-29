using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Drawing.Drawing2D;
using System.Collections;
using System.Globalization;

namespace ACT4
{
    public partial class Form1 : Form
    {
    
        int side;
        int n = 6;
        SixState startState;
        SixState currentState;
        int moveCounter;

        int maxFrequency;
        int currentHeuristicValue;
        int counter;

        //bool stepMove = true;

        int[,] hTable;
        ArrayList bMoves;
        Object chosenMove;

        public Form1()
        {
            InitializeComponent();

            side = pictureBox1.Width / n;

            startState = randomSixState();
            currentState = new SixState(startState);

            maxFrequency = 30;
            currentHeuristicValue = n * n;

            updateUI();
            label1.Text = "Attacking pairs: " + getAttackingPairs(startState);
        }

        private void updateUI()
        {
            //pictureBox1.Refresh();
            pictureBox2.Refresh();

            //label1.Text = "Attacking pairs: " + getAttackingPairs(startState);
            label3.Text = "Attacking pairs: " + getAttackingPairs(currentState);
            label4.Text = "Moves: " + moveCounter + "\nIteration: " + counter;
            hTable = getHeuristicTableForPossibleMoves(currentState);
            bMoves = getBestMoves(hTable);

            listBox1.Items.Clear();
            foreach (Point move in bMoves)
            {
                listBox1.Items.Add(move);
            }

            if (bMoves.Count > 0)
                chosenMove = chooseMove(bMoves);
            label2.Text = "Chosen move: " + chosenMove;
        }

        private void pictureBox1_Paint(object sender, PaintEventArgs e)
        {
            // draw squares
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if ((i + j) % 2 == 0)
                    {
                        e.Graphics.FillRectangle(Brushes.Blue, i * side, j * side, side, side);
                    }
                    // draw queens
                    if (j == startState.Y[i])
                        e.Graphics.FillEllipse(Brushes.Fuchsia, i * side, j * side, side, side);
                }
            }
        }

        private void pictureBox2_Paint(object sender, PaintEventArgs e)
        {
            // draw squares
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if ((i + j) % 2 == 0)
                    {
                        e.Graphics.FillRectangle(Brushes.Black, i * side, j * side, side, side);
                    }
                    // draw queens
                    if (j == currentState.Y[i])
                        e.Graphics.FillEllipse(Brushes.Fuchsia, i * side, j * side, side, side);
                }
            }
        }

        private SixState randomSixState()
        {
            int[] array = new int[n];

            counter = 0;

            for(int i = 0; i < n; i++)
            {
                Point point = (Point)chooseMove(getBestMoves(getHeuristicTableForPossibleMoves(array, i + 1), array, i + 1));
                array[point.X] = point.Y;
            }

            return new SixState(array[0], array[1], array[2], array[3], array[4], array[5]);
        }

        private int getAttackingPairs(int[] array, int size)
        {
            int attackers = 0;

            for (int i = 0; i < size; i++)
            {
                for (int j = i + 1; j < size; j++)
                    if (array[i] == array[j])
                        attackers++;
                for (int j = i + 1; j < size; j++)
                    if (array[j] == array[i] + j - i)
                        attackers++;
                for (int j = i + 1; j < size; j++)
                    if (array[i] == array[j] + j - i)
                        attackers++;
            }

            return attackers;
        }

        private int getAttackingPairs(SixState f)
        {
            int attackers = 0;
            
            for (int rf = 0; rf < n; rf++)
            {
                for (int tar = rf+1; tar < n; tar++)
                {
                    // get horizontal attackers
                    if (f.Y[rf] == f.Y[tar])
                        attackers++;
                }
                for (int tar = rf+1; tar < n; tar++)
                {
                    // get diagonal down attackers
                    if (f.Y[tar] == f.Y[rf] + tar - rf)
                        attackers++;
                }
                for (int tar = rf+1; tar < n; tar++)
                {
                    // get diagonal up attackers
                    if (f.Y[rf] == f.Y[tar] + tar - rf)
                        attackers++;
                }
            }
            
            return attackers;
        }

        private int[] getHeuristicTableForPossibleMoves(int[] array, int column)
        {
            int[] hStates = new int[n];

            for(int i = 0; i < n; i++)
            {
                array[column - 1] = i;
                hStates[i] = getAttackingPairs(array, column);
            }

            return hStates;
                    
        }

        private int[,] getHeuristicTableForPossibleMoves(SixState thisState)
        {
            int[,] hStates = new int[n, n];

            for (int i = 0; i < n; i++) // go through the indices
            {
                for (int j = 0; j < n; j++) // replace them with a new value
                {
                    SixState possible = new SixState(thisState);
                    possible.Y[i] = j;
                    hStates[i, j] = getAttackingPairs(possible);
                }
            }

            return hStates;
        }

        private ArrayList getBestMoves(int[,] heuristicTable)
        {
            ArrayList bestMoves = new ArrayList();
            int bestHeuristicValue = heuristicTable[0, 0];

            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (bestHeuristicValue > heuristicTable[i, j])
                    {
                        bestHeuristicValue = heuristicTable[i, j];
                        bestMoves.Clear();
                        if (currentState.Y[i] != j)
                            bestMoves.Add(new Point(i, j));
                    }
                    else if (bestHeuristicValue == heuristicTable[i, j])
                    {
                        if (currentState.Y[i] != j)
                            bestMoves.Add(new Point(i, j));
                    }
                }
            }

            if(bestHeuristicValue < currentHeuristicValue)
            {

                currentHeuristicValue = bestHeuristicValue;
                counter = 0;
            }
            else
            {
                counter++;
            }


            return bestMoves;
        }

        private ArrayList getBestMoves(int[] heuristicTable, int[] array, int column)
        {
            ArrayList bestMoves = new ArrayList();
            int bestHeuristicValue = heuristicTable[0];

            for(int i = 0; i < n; i++)
            {
                if (bestHeuristicValue > heuristicTable[i])
                {
                    bestHeuristicValue = heuristicTable[i];
                    bestMoves.Clear();
                    if (array[column - 1] != i)
                        bestMoves.Add(new Point(column - 1, i));
                }
                else if (bestHeuristicValue == heuristicTable[i])
                    if (array[column - 1] != i)
                        bestMoves.Add(new Point(column - 1, i));
            }

            return bestMoves;
        }

        private Object chooseMove(ArrayList possibleMoves)
        {
            int arrayLength = possibleMoves.Count;
            Random r = new Random();
            int randomMove = r.Next(arrayLength);

            return possibleMoves[randomMove];
        }

        private void executeMove(Point move)
        {
            for (int i = 0; i < n; i++)
            {
                startState.Y[i] = currentState.Y[i];
            }
            currentState.Y[move.X] = move.Y;
            moveCounter++;

            chosenMove = null;
            updateUI();
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (getAttackingPairs(currentState) > 0)
               executeMove((Point)chosenMove);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            startState = randomSixState();
            currentState = new SixState(startState);

            moveCounter = 0;

            updateUI();
            pictureBox1.Refresh();
            label1.Text = "Attacking pairs: " + getAttackingPairs(startState);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            while (getAttackingPairs(currentState) > 0)
            {
                if(counter >= maxFrequency)
                {
                    startState = randomSixState();
                    currentState = new SixState(startState);

                    updateUI();
                    label1.Text = "Attacking pairs: " + getAttackingPairs(startState);

                }
                else
                {
                    executeMove((Point)chosenMove);
                }
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }        
    }
}
