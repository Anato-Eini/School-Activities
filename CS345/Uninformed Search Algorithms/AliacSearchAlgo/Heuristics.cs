using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AISearchSample;
using System.Collections;

namespace AliacSearchAlgo
{
    public class Heuristics
    {
        public Heuristics() { }
        public void calculate(ArrayList nodes, int start)
        {
           double [] heu=new double[nodes.Count];
            int x = 0;
            int y = 0;
        for (int g = 0; g < heu.Length; g++)
        { 
            x=Math.Abs((((Node)nodes[start]).X)-(((Node)nodes[g]).X));
            y=Math.Abs((((Node)nodes[start]).Y)-(((Node)nodes[g]).Y));
            heu[g] = Math.Sqrt((x * x) + (y * y));
        }
        
           // return heu;

        }
    }
}
