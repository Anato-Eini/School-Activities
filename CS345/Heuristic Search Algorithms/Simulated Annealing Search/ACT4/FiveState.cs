using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ACT4
{
    class FiveState
    {
        public int[] Y = new int[5];

        public FiveState() { }
        public FiveState(int a)
        {
            Y[0] = Y[1] = Y[2] = Y[3] = Y[4] = a;
        }
        public FiveState(int a, int b, int c, int d, int e)
        {
            Y[0] = a;
            Y[1] = b;
            Y[2] = c;
            Y[3] = d;
            Y[4] = e;
        }
        public FiveState(FiveState f)
        {
            Y[0] = f.Y[0];
            Y[1] = f.Y[1];
            Y[2] = f.Y[2];
            Y[3] = f.Y[3];
            Y[4] = f.Y[4];
        }
    }
}
