using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using AliacSearchAlgo;

namespace AISearchSample
{
    class Search
    {
        Fringes fringe;
        ArrayList n;
        bool start=false;

        public Search(ArrayList nodes,int type) 
        {
           if(type==1)//DFS 
            fringe = new Fringe();
           if(type==2)//BFS
            fringe = new Fringe2();
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
            Node explored=null;
            //pangita Start node
            for (int i = 0; i < n.Count; i++) 
            {
                if (((Node)n[i]).Start == true) 
                {
                    fringe.add(((Node)n[i]), null);
                }
            }

            Node explorer = null;
            ArrayList temp;
            Object []t;
            while ((explorer = fringe.remove()) != null)
            {
              //  MessageBox.Show(explorer.Name+"removed");
                if (explorer.Goal == true)
                {
                    explorer.Expanded = true;
                    MessageBox.Show("found " + explorer.Name);
                    explored = explorer;
                    break;
                }

                //find connections and push to fringe
                temp = explorer.getNeighbor();
                t = temp.ToArray();
                for (int i = 0; i < t.Length; i++)
                {
                    if (((Node)t[i]).Expanded != true)
                    {
                        //MessageBox.Show(((Node)t[i]).Name + "added");
                        fringe.add((Node)t[i],explorer);
                    }
                }
                explorer.Expanded = true;
                explored = explorer;
                

            }
            return explored;

        }


        public Node searchone()
        {
            Node explored=null;
            //pangita Start node
            if (!start)
            {
                for (int i = 0; i < n.Count; i++)
                {
                    if (((Node)n[i]).Start == true)
                    {
                        fringe.add(((Node)n[i]), null);
                    }
                }
                start = true;
            }

            Node explorer = null;
            ArrayList temp;
            Object[] t;

            if ((explorer = fringe.remove()) != null)
            {
                if (explorer.Goal == true)
                {
                    explorer.Expanded = true;
                    //    MessageBox.Show("found " + explorer.Name);
                    explored = explorer;

                }

                //find connections and push to fringe
                temp = explorer.getNeighbor();
                t = temp.ToArray();
                for (int i = 0; i < t.Length; i++)
                {
                    if (((Node)t[i]).Expanded != true)
                        fringe.add((Node)t[i], explorer);
                }
                explorer.Expanded = true;
                explored = explorer;
            }
                return explored;
                

            
        }


    }
}
