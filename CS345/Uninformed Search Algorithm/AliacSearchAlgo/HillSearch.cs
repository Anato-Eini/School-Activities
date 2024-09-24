using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AISearchSample;
using System.Windows.Forms;
using System.Collections;

namespace AliacSearchAlgo
{

    class HillSearch
    {

        ArrayList n;
        bool start=false;

        public HillSearch(ArrayList nodes) 
        {
             n = nodes;
        }

        public void setStart(Node n)
        {
            n.Start = true;
        }

        public void setGoal(Node n) 
        {
            n.Goal = true;
        }

        public Node search() 
        {
            ArrayList temp = null ;
            double []heuristics;
            int s=0;
            // find start index
            if (!start)
            {
                for (int i = 0; i < n.Count; i++)
                {
                    if (((Node)n[i]).Start == true)
                    {
                        s = i;
                    }
                }
                start = true;
            }

            do
            {

                temp = ((Node)n[s]).getNeighbor();
                ((Node)n[s]).Expanded = true;
                heuristics = calculate(temp, ((Node)n[s]));
                double min = -1;
                int nodeloc = 0;
                for (int y = 0; y < temp.Count; y++)
                {
                    if (((Node)temp[y]).Expanded == false)
                    {
                        if (min == -1.0)
                            min = heuristics[y];
                        if (heuristics[y] <= min)
                        {
                            min = heuristics[y];
                            nodeloc = y;
                         }
                    }
                }
                for (int x = 0; x < n.Count; x++)
                {
                    if (((Node)n[x]).Name.Equals(((Node)temp[nodeloc]).Name))
                    {
                       ((Node)n[x]).Origin = ((Node)n[s]);
                        s = x;
                    }
                                
                }

            } while (((Node)n[s]).Goal != true);

            MessageBox.Show("found" + ((Node)n[s]).Name);
            ((Node)n[s]).Expanded = true;
            return ((Node)n[s]);
   }

        public double[] calculate(ArrayList nodes, Node start)
        {
            double[] heu = new double[nodes.Count];
            int x = 0;
            int y = 0;
            for (int g = 0; g < heu.Length; g++)
            {
                x = Math.Abs(start.X) - (((Node)nodes[g]).X);
                y = Math.Abs(start.Y) - (((Node)nodes[g]).Y);
                heu[g] = Math.Sqrt((x * x) + (y * y));
            }

            return heu;

        }


    }
}
