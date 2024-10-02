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

namespace ACT4
{
    public partial class Form1 : Form
    {
        int side;
        int n = 6;
        SixState startState;
        SixState[] pStates; //Population States

        int moveCounter;

        int population; 
        int parents; //Number of parents
        int crossingPoint;
        int pExponent; // exponential probability where 0 <= i <= 1

        double mutationRate;

        int[] hTable;
        ArrayList[] bMoves;
        Object chosenMove;

        public Form1()
        {
            InitializeComponent();

            population = 100;
            parents = 2;
            crossingPoint = n / parents;
            mutationRate = 0.8;
            pExponent = 5;

            side = pictureBox1.Width / n;

            pStates = new SixState[population];
            pStates[0] = startState = randomSixState();
            for (int i = 1; i < population; i++)
                pStates[i] = randomSixState();

            updateUI();
            label1.Text = "Attacking pairs: " + getAttackingPairs(startState);
        }

        private void sort_population()
        {
            (SixState, int)[] stateWithHValue = new (SixState, int)[population];

            for (int i = 0; i < population; i++)
                stateWithHValue[i] = (pStates[i], hTable[i]);

            Array.Sort(stateWithHValue, (x, y) => x.Item2.CompareTo(y.Item2));

            for (int i = 0; i < population; i++)
            {
                pStates[i] = stateWithHValue[i].Item1;
                hTable[i] = stateWithHValue[i].Item2;
            }
        }
        private int[] getParentsIndex()
        {
            int[] p = new int[parents];

            sort_population();

            Random ran = new Random();

            for(int i = 0; i < parents; i++)
            {
                p[i] = (int)(population * Math.Pow(ran.NextDouble(), pExponent));
            }

            return p;
        }

        private void updateUI()
        {
            //pictureBox1.Refresh();
            pictureBox2.Refresh();

            label3.Text = "Attacking pairs: " + getAttackingPairs(startState);
            label4.Text = "Moves: " + moveCounter;
            hTable = getHeuristicTableForPossibleMoves(pStates);
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
            Random r = new Random();
            SixState random = new SixState(r.Next(n),
                                             r.Next(n),
                                             r.Next(n),
                                             r.Next(n),
                                             r.Next(n),
                                             r.Next(n));

            return random;
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

        private int[] getHeuristicTableForPossibleMoves(SixState[] thisState)
        {
            int[] hStates = new int[population];

            for (int i = 0; i < population; i++) // go through the indices
            {
                hStates[i] = getAttackingPairs(pStates[i]);
            }

            return hStates;
        }

        private ArrayList getBestMoves(int[] heuristicTable)
        {
            ArrayList bestMoves = new ArrayList();
            int bestHeuristicValue = heuristicTable[0];

            for (int i = 0; i < population; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (bestHeuristicValue > heuristicTable[i, j])
                    {
                        bestHeuristicValue = heuristicTable[i, j];
                        bestMoves.Clear();
                        if (currentState.Y[i] != j)
                            bestMoves.Add(new Point(i, j));
                    } else if (bestHeuristicValue == heuristicTable[i,j])
                    {
                        if (currentState.Y[i] != j)
                            bestMoves.Add(new Point(i, j));
                    }
                }
                
            }
            label5.Text = "Possible Moves (H="+bestHeuristicValue+")";
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
                executeMove((Point)chosenMove);
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }        
    }
}
