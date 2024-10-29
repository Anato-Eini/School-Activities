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
        int crossingPoint;
        int pExponent; // exponential probability where 0 <= probability <= 1

        double mutationRate;

        int[] hTable;
        ArrayList bMoves;
        object chosenMove;

        public Form1()
        {
            InitializeComponent();

            population = 10;
            crossingPoint = n / 2;
            mutationRate = 0.5;
            pExponent = 5;

            side = pictureBox1.Width / n;

            pStates = new SixState[population];
            pStates[0] = startState = randomSixState();
            for (int i = 1; i < population; i++)
                pStates[i] = randomSixState();

            label3.Text = "Attacking pairs: " + getAttackingPairs(startState);
            label4.Text = "Generations: " + moveCounter;
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

        private SixState[] generateChildren(SixState parent1, SixState parent2)
        {
            SixState[] child = new SixState[2];
            
            child[0] = new SixState(parent2);
            child[1] = new SixState(parent1);

            for(int i = 0; i < n / 2; i++)
            {
                child[0].Y[i] = parent2.Y[i];
                child[1].Y[i] = parent1.Y[i];
            }

            for(int i =  n / 2; i < n; i++)
            {
                child[0].Y[i] = parent1.Y[i];
                child[1].Y[i] = parent2.Y[i];
            }
            
            //Mutate
            Random rand = new Random();
            if(rand.NextDouble() <= mutationRate)
                child[0].Y[rand.Next(0, 6)] = rand.Next(0, 6);

            if (rand.NextDouble() <= mutationRate)
                child[1].Y[rand.Next(0, 6)] = rand.Next(0, 6);

            return child;
        }

        private void repopulate()
        {
            SixState[] newPopulation = new SixState[population];

            
            int[] parentIndices = getParentsIndex();
            SixState[] children = generateChildren(pStates[0], pStates[1]);
            newPopulation[0] = new SixState(children[0]);
            newPopulation[1] = new SixState(children[1]);

            for (int i = 3; i < population; i += 2)
            {
                parentIndices = getParentsIndex();
                children = generateChildren(pStates[parentIndices[0]], pStates[parentIndices[1]]);
                newPopulation[i] = new SixState(children[0]);
                newPopulation[i - 1] = new SixState(children[1]);
            }

            pStates = newPopulation;
        }

        private int[] getParentsIndex()
        {
            sort_population();

            int[] p = new int[2];

            Random ran = new Random();

            for(int i = 0; i < 2; i++)
            {
                p[i] = (int)(population * Math.Pow(ran.NextDouble(), pExponent));
            }

            return p;
        }

        private void updateUI()
        {

            hTable = getHeuristicTableForPossibleMoves(pStates);
            bMoves = getBestMoves(hTable);

            listBox1.Items.Clear();
            foreach (Point move in bMoves)
            {
                listBox1.Items.Add(move);
            }

            if (bMoves.Count > 0)
                chosenMove = chooseMove(bMoves);

            label2.Text = "Chosen parent index: " + 0;
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
                    if (j == startState.Y[i])
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
                hStates[i] = getAttackingPairs(pStates[i]);

            return hStates;
        }

        private ArrayList getBestMoves(int[] heuristicTable)
        {
            ArrayList bestMoves = new ArrayList();

            sort_population();

            int bestHeuristicValue = heuristicTable[0];

            for (int i = 1; i < population; i++)
                if (heuristicTable[0] == heuristicTable[i])
                    bestMoves.Add(new Point (i, 0));

            label5.Text = "Possible Moves (H="+bestHeuristicValue+")";
            return bestMoves;
        }

        private object chooseMove(ArrayList possibleMoves)
        {
            return possibleMoves[0];
        }

        private void executeMove()
        {
            repopulate();

            moveCounter++;

            updateUI();

            for (int i = 0; i < n; i++)
            {
                startState.Y[i] = pStates[0].Y[i];
            }

            pictureBox2.Refresh();
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            label3.Text = "Attacking pairs: " + getAttackingPairs(startState);
            label4.Text = "Generations: " + moveCounter;

            if (getAttackingPairs(startState) > 0)
               executeMove();
            
            label1.Text = "Attacking pairs: " + getAttackingPairs(startState);
            label4.Text = "Generations: " + moveCounter;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            startState = randomSixState();
            pStates[0] = new SixState(startState);
            for (int i = 1; i < population; i++)
                pStates[i] = new SixState(startState);

            moveCounter = 0;

            label3.Text = "Attacking pairs: " + getAttackingPairs(startState);
            label4.Text = "Generations: " + moveCounter;

            updateUI();

            pictureBox1.Refresh();
            label1.Text = "Attacking pairs: " + getAttackingPairs(startState);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            label3.Text = "Attacking pairs: " + getAttackingPairs(startState);
            label4.Text = "Generations: " + moveCounter;

            while (getAttackingPairs(startState) > 0)
                executeMove();

            label3.Text = "Attacking pairs: " + getAttackingPairs(startState);
            label4.Text = "Generations: " + moveCounter;

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }        
    }
}
