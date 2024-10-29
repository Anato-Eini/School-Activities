using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AliacSearchAlgo;

namespace AISearchSample
{
    class Fringe2:Fringes
{
        //Stack<Node> s;
        Queue<Node> s;
        public Fringe2() 
        {
            //s = new Stack<Node>();
            s = new Queue<Node>();
        }
        
        public void add(Node n,Node origin)
        {
            n.Origin = origin;
            //s.Push(n);
            s.Enqueue(n);
        }

        public Node remove()
        {
            if (s.Count != 0)
                return s.Dequeue();
                 //return s.Pop();
            return null;
        }
    }
}
