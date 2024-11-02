using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;

namespace HNUDIP
{
    static class ImageProcess
    {

        public static void copyImage(ref Bitmap a, ref Bitmap b)
        {
            b = new Bitmap(a.Width, a.Height);
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    Color data = a.GetPixel(x, y);
                    b.SetPixel(x, y, data);
                 }
                
            }
        
        }

        public static void Fliphorizontal(ref Bitmap a, ref Bitmap b)
        {
            b = new Bitmap(a.Width, a.Height);
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    Color data = a.GetPixel(x, y);
                    b.SetPixel(x, (a.Height-1)-y, data);
                }

            }

        }

        public static void FlipVertical(ref Bitmap a, ref Bitmap b)
        {
            b = new Bitmap(a.Width, a.Height);
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    Color data = a.GetPixel(x, y);
                    b.SetPixel((a.Width-1)-x, y, data);
                }

            }

        }

        public static void Scale(ref Bitmap a, ref Bitmap b, int nwidth, int nheight)
        {
            int targetWidth = nwidth;
            int targetHeight = nheight;
            int xTarget, yTarget, xSource, ySource;
            int width = a.Width;
            int height = a.Height;
            b = new Bitmap(targetWidth, targetHeight);

            for (xTarget = 0; xTarget < targetWidth; xTarget++)
            {
                for (yTarget = 0; yTarget < targetHeight; yTarget++)
                {
                    xSource = xTarget * width / targetWidth;
                    ySource = yTarget * height / targetHeight;
                    b.SetPixel(xTarget, yTarget, a.GetPixel(xSource, ySource));
                }
            }
        }

        public static void Subtract(ref Bitmap a, ref Bitmap b,ref Bitmap result, int value)
        {
            result = new Bitmap(a.Width, a.Height);
            byte agraydata = 0;
            byte bgraydata = 0;
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    Color adata = a.GetPixel(x, y);
                    Color bdata = b.GetPixel(x, y);

                    agraydata = (byte)((adata.R + adata.G + adata.B) / 3);
                    bgraydata = (byte)((bdata.R + bdata.G + bdata.B) / 3);
                    if (Math.Abs(agraydata-bgraydata) > value)
                        result.SetPixel(x, y, Color.Red);
                    else
                        result.SetPixel(x, y,bdata);

                }

            }

        }


        public static void Threshold(ref Bitmap a, ref Bitmap b, int value)
        {
            b = new Bitmap(a.Width, a.Height);
            byte graydata = 0;
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    Color data = a.GetPixel(x, y);
                    graydata=(byte)((data.R+data.G+data.B)/3);
                    if(graydata>value)
                    b.SetPixel(x, y, Color.White);
                    else
                    b.SetPixel(x, y, Color.Black);
                    
                }

            }
        
        }

        public static void Rotate(ref Bitmap a, ref Bitmap b, int value)
        {
            float angleRadians = (float)value;
            int xCenter = (int)(a.Width / 2);
            int yCenter = (int)(a.Height / 2);
            int width, height, xs, ys, xp, yp, x0, y0;
            float cosA, sinA;
            cosA = (float)Math.Cos(angleRadians);
            sinA = (float)Math.Sin(angleRadians);
            width = a.Width;
            height = a.Height;
            b = new Bitmap(width, height);
            for (xp = 0; xp < width; xp++)
            {
                for (yp = 0; yp < height; yp++)
                {
                    x0 = xp - xCenter;     // translate to (0,0)
                    y0 = yp - yCenter;
                    xs = (int)(x0 * cosA + y0 * sinA);   // rotate around the origin
                    ys = (int)(-x0 * sinA + y0 * cosA);
                    xs = (int)(xs + xCenter);  // translate back to (xCenter,yCenter)
                    ys = (int)(ys + yCenter);
                    xs = Math.Max(0, Math.Min(width - 1, xs));  // force the source location to within image bounds
                    ys = Math.Max(0, Math.Min(height - 1, ys));
                    b.SetPixel(xp, yp, a.GetPixel(xs, ys));
                }
            }
        }
        
        public static void Brightness(ref Bitmap a, ref Bitmap b, int value)
        {
            b = new Bitmap(a.Width, a.Height);
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    Color temp = a.GetPixel(x, y);
                    Color changed;
                    if(value>0)
                    changed = Color.FromArgb(Math.Min(temp.R + value, 255), Math.Min(temp.G + value, 255), Math.Min(temp.B + value, 255));
                    else
                    changed = Color.FromArgb(Math.Max(temp.R + value, 0), Math.Max(temp.G + value, 0), Math.Max(temp.B + value, 0));
                    
                    b.SetPixel(x, y, changed);
                }
            }
        }
        public static void Equalisation(ref Bitmap a, ref Bitmap b, int degree)
        {
            int height = a.Height;
            int width = a.Width;
            int numSamples, histSum;
            int[] Ymap = new int[256];
            int[] hist = new int[256];
            int percent = degree;
            // compute the histogram from the sub-image
            Color nakuha;
            Color gray;
            Byte graydata;
            //compute greyscale
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    nakuha = a.GetPixel(x, y);
                    graydata = (byte)((nakuha.R + nakuha.G + nakuha.B) / 3);
                    gray = Color.FromArgb(graydata, graydata, graydata);
                    a.SetPixel(x, y, gray);
                }
            }
            //histogram 1d data;
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    nakuha = a.GetPixel(x, y);
                    hist[nakuha.B]++;

                }
            }
            // remap the Ys, use the maximum contrast (percent == 100) 
            // based on histogram equalization
            numSamples = (a.Width * a.Height);   // # of samples that contributed to the histogram
            histSum = 0;
            for (int h = 0; h < 256; h++)
            {
                histSum += hist[h];
                Ymap[h] = histSum * 255 / numSamples;
            }

            // if desired contrast is not maximum (percent < 100), then adjust the mapping
            if (percent < 100)
            {
                for (int h = 0; h < 256; h++)
                {
                    Ymap[h] = h + ((int)Ymap[h] - h) * percent / 100;
                }
            }

            b = new Bitmap(a.Width, a.Height);
            // enhance the region by remapping the intensities
            for (int y = 0; y < a.Height; y++)
            {
                for (int x = 0; x < a.Width; x++)
                {
                  // set the new value of the gray value
                    Color temp = Color.FromArgb(Ymap[a.GetPixel(x, y).R], Ymap[a.GetPixel(x, y).G], Ymap[a.GetPixel(x, y).B]);
                   b.SetPixel(x, y, temp);
                }

            }
          

        
        }

        

        public static void Histogram(ref Bitmap a, ref Bitmap b) 
        {
            Color sample;
            Color gray;
            Byte graydata;
            //Grayscale Convertion;
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    sample = a.GetPixel(x, y);
                    graydata = (byte)((sample.R + sample.G + sample.B) / 3);
                    gray = Color.FromArgb(graydata, graydata, graydata);
                    a.SetPixel(x, y, gray);
                }
            }
            
            //histogram 1d data;
            int[] histdata = new int[256]; // array from 0 to 255
            for (int x = 0; x < a.Width; x++)
            {
                for (int y = 0; y < a.Height; y++)
                {
                    sample = a.GetPixel(x, y);
                    histdata[sample.R]++; // can be any color property r,g or b
                }
            }

            // Bitmap Graph Generation
            // Setting empty Bitmap with background color
            b = new Bitmap(256, 800);
            for (int x = 0; x < 256; x++)
            {
                for (int y = 0; y < 800; y++)
                {
                    b.SetPixel(x, y, Color.White);
                }
            }
            // plotting points based from histdata
            for (int x = 0; x < 256; x++)
            {
                for (int y = 0; y < Math.Min(histdata[x] / 5, b.Height - 1); y++)
                {
                    b.SetPixel(x, (b.Height - 1) - y, Color.Black);
                }
            }
        }
        
    }
}
