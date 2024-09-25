using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;

namespace AISearchSample    
{
    [Serializable]
    class Node
    {
        string name;
        int value;
        bool moved;
        bool goal, expanded, start;
        ArrayList neighbors;
        private int x;
        private int y;
        private Node origin;
        public Node() {
            name = "";
            value = 0;
            origin = null;
            goal = expanded = start = false;
            neighbors = new ArrayList();
            x = 0;
            y = 0;
            moved = false;
        }

        public bool Moved {
            set { moved = value; }
            get { return moved; }
        }
        public Node Origin
        {
            set { origin = value; }
            get { return origin; }
        }
        public int X
        {
            set{x = value;}
            get{return x;}
        }
        public int Y
        {
            set{y = value;}
            get{return y;}
        }
        public string Name
        {
            get
            {
                return this.name;
            }
            set
            {
                this.name = value;
            }
        }
        public int Value
        {
            get
            {
                return this.value;
            }
            set
            {
                this.value = value;
            }
        }
        public bool Expanded
        {
            get
            {
                return this.expanded;
            }
            set
            {
                this.expanded = value;
            }
        }
        public bool Goal
        {
            get
            {
                return this.goal;
            }
            set
            {
                this.goal = value;
            }
        }

        public bool Start
        {
            get
            {
                return this.start;
            }
            set
            {
                this.start = value;
            }
        }


        public ArrayList getNeighbor()
        {
            //Object[]noodes=this.neighbors.ToArray();
            return neighbors;

        }


        public void addNeighbor(Node n)
        {
            this.neighbors.Add(n);
        }

        public void removeNeighbor(Node n)
        {
            this.neighbors.Remove(n);
        }

        public bool Equals(Node n)
        {
            bool result = false;
            if (this.name == n.Name)
                result= true;
            return result;
        }
    }
}
