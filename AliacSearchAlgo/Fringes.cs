using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using AISearchSample;

namespace AliacSearchAlgo
{
    interface Fringes
    {
         void add(Node n, Node origin);
         Node remove();
    }
}
