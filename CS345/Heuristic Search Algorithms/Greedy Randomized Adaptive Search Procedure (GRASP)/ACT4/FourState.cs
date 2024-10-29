using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ACT4
{
    class FourState
    {
        public int[] Y = new int[4];

        public FourState() { }
        public FourState(int a)
        {
            Y[0] = Y[1] = Y[2] = Y[3] = a;
        }
        public FourState(int a, int b, int c, int d)
        {
            Y[0] = a;
            Y[1] = b;
            Y[2] = c;
            Y[3] = d;
        }
        public FourState(FourState f)
        {
            Y[0] = f.Y[0];
            Y[1] = f.Y[1];
            Y[2] = f.Y[2];
            Y[3] = f.Y[3];
        }
    }
}
