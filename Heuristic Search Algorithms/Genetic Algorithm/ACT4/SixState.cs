using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ACT4
{
    class SixState
    {
        public int[] Y = new int[6];

        public SixState() { }
        public SixState(int a)
        {
            Y[0] = Y[1] = Y[2] = Y[3] = Y[4] = Y[5] = a;
        }
        public SixState(int a, int b, int c, int d, int e, int f)
        {
            Y[0] = a;
            Y[1] = b;
            Y[2] = c;
            Y[3] = d;
            Y[4] = e;
            Y[5] = f;
        }
        public SixState(SixState f)
        {
            Y[0] = f.Y[0];
            Y[1] = f.Y[1];
            Y[2] = f.Y[2];
            Y[3] = f.Y[3];
            Y[4] = f.Y[4];
            Y[5] = f.Y[5];
        }
    }
}
