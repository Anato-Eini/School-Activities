using System.Windows.Markup;

namespace DIP_Activity
{
    public partial class Form1 : Form
    {
        Bitmap? loaded;
        Bitmap? processed;
        Bitmap? subtracted;

        public Form1()
        {
            InitializeComponent();
        }

        private void openFileDialog1_FileOk(object sender, System.ComponentModel.CancelEventArgs e)
        {
            pictureBox1.Image = loaded = new Bitmap(openFileDialog1.FileName);
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog1.ShowDialog();
        }

        private void pixelCopyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded.Width, loaded.Height);

            for (int i = 0; i < loaded.Width; i++)
                for (int j = 0; j < loaded.Height; j++)
                    processed.SetPixel(i, j, loaded.GetPixel(i, j));

            pictureBox2.Image = processed;
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveFileDialog1.ShowDialog();
        }

        private void saveFileDialog1_FileOk(object sender, System.ComponentModel.CancelEventArgs e)
        {
            if (processed == null)
                return;

            processed.Save(saveFileDialog1.FileName);
        }

        private void grayscalingToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded.Width, loaded.Height);
            Color pixel;
            int average;
            for (int i = 0; i < loaded.Width; i++)
                for (int j = 0; j < loaded.Height; j++)
                {
                    pixel = loaded.GetPixel(i, j);
                    average = (pixel.R + pixel.G + pixel.B) / 3;
                    processed.SetPixel(i, j, Color.FromArgb(average, average, average));
                }

            pictureBox2.Image = processed;
        }

        private void inversionToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded.Width, loaded.Height);
            Color pixel;
            for (int i = 0; i < loaded.Width; i++)
                for (int j = 0; j < loaded.Height; j++)
                {
                    pixel = loaded.GetPixel(i, j);
                    processed.SetPixel(i, j, Color.FromArgb(255 - pixel.R, 255 - pixel.G, 255 - pixel.B));
                }

            pictureBox2.Image = processed;
        }

        private void mirrorHorizontalToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded.Width, loaded.Height);

            int width = loaded.Width;
            int height = loaded.Height;

            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++)
                    processed.SetPixel(width - i - 1, j, loaded.GetPixel(i, j));

            pictureBox2.Image = processed;
        }

        private void mirrorVerticalToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded.Width, loaded.Height);

            int width = loaded.Width;
            int height = loaded.Height;

            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++)
                    processed.SetPixel(i, height - j - 1, loaded.GetPixel(i, j));

            pictureBox2.Image = processed;
        }

        private void histogramToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            int[] histData = new int[256];
            Color pixel;
            int maxFreq = 420;

            for (int i = 0; i < loaded.Width; i++)
                for (int j = 0; j < loaded.Height; j++)
                {
                    pixel = loaded.GetPixel(i, j);
                    int ave = (pixel.R + pixel.G + pixel.B) / 3;
                    histData[ave]++;

                    if (histData[ave] > maxFreq)
                        maxFreq = histData[ave];
                }

            processed = new Bitmap(256, 420);
            int mFactor = maxFreq / 420;
            int count;

            for (int i = 0; i < 256; i++)
            {
                count = Math.Min(420, histData[i] / mFactor);

                for (int j = 0; j < count; j++)
                    processed.SetPixel(i, 419 - j, Color.Black);
            }

            pictureBox2.Image = processed;
        }

        private void trackBar1_Scroll(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded.Width, loaded.Height);

            int value = trackBar1.Value;

            for (int i = 0; i < loaded.Width; i++)
                for (int j = 0; j < loaded.Height; j++)
                {
                    Color pixel = loaded.GetPixel(i, j);
                    if (value > 0)
                    {
                        processed.SetPixel(i, j, Color.FromArgb(
                            Math.Min(pixel.R + value, 255),
                            Math.Min(pixel.G + value, 255),
                            Math.Min(pixel.B + value, 255)
                            )
                        );
                    }
                    else
                    {
                        processed.SetPixel(i, j, Color.FromArgb(
                            Math.Max(pixel.R + value, 0),
                            Math.Max(pixel.G + value, 0),
                            Math.Max(pixel.B + value, 0)
                            )
                         );
                    }
                }

            pictureBox2.Image = processed;
        }

        private void trackBar2_Scroll(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            float contrastFactor = trackBar2.Value / 100.0f + 1;

            processed = new Bitmap(loaded.Width, loaded.Height);

            int[,] tableRed = new int[loaded.Width, loaded.Height];
            int[,] tableGreen = new int[loaded.Width, loaded.Height];
            int[,] tableBlue = new int[loaded.Width, loaded.Height];

            Thread red = new(new ThreadStart(() =>
            {
                Bitmap preLoad;
                lock (loaded)
                    preLoad = (Bitmap)loaded.Clone();

                for (int i = 0; i < preLoad.Width; i++)
                    for (int j = 0; j < preLoad.Height; j++)
                        tableRed[i, j] = Algorithm.ApplyContrast(preLoad.GetPixel(i, j).R, contrastFactor);
            }));

            Thread green = new(new ThreadStart(() =>
            {
                Bitmap preLoad;
                lock (loaded)
                    preLoad = (Bitmap)loaded.Clone();

                for (int i = 0; i < preLoad.Width; i++)
                    for (int j = 0; j < preLoad.Height; j++)
                        tableGreen[i, j] = Algorithm.ApplyContrast(preLoad.GetPixel(i, j).G, contrastFactor);
            }));

            Thread blue = new(new ThreadStart(() =>
            {
                Bitmap preLoad;
                lock (loaded)
                    preLoad = (Bitmap)loaded.Clone();

                for (int i = 0; i < preLoad.Width; i++)
                    for (int j = 0; j < preLoad.Height; j++)
                        tableBlue[i, j] = Algorithm.ApplyContrast(preLoad.GetPixel(i, j).B, contrastFactor);
            }));

            red.Start();
            blue.Start();
            green.Start();

            red.Join();
            blue.Join();
            green.Join();

            for (int i = 0; i < loaded.Width; i++)
                for (int j = 0; j < loaded.Height; j++)
                {
                    processed.SetPixel(i, j, Color.FromArgb(
                        tableRed[i, j],
                        tableGreen[i, j],
                        tableBlue[i, j]
                        ));
                }

            pictureBox2.Image = processed;

        }

        private void sepiaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded.Width, loaded.Height);

            Color pixel;
            for (int i = 0; i < loaded.Width; i++)
                for (int j = 0; j < loaded.Height; j++)
                {
                    pixel = loaded.GetPixel(i, j);
                    processed.SetPixel(i, j,
                        Color.FromArgb(
                            (int)Math.Min(pixel.R * 0.393 + pixel.G * 0.769 + pixel.B * 0.189, 255),
                            (int)Math.Min(pixel.R * 0.349 + pixel.G * 0.686 + pixel.B * 0.168, 255),
                            (int)Math.Min(pixel.R * 0.272 + pixel.G * 0.534 + pixel.B * 0.131, 255)
                            )
                        );
                }

            pictureBox2.Image = processed;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            openFileDialog2.ShowDialog();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            openFileDialog1.ShowDialog();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            if (loaded == null || processed == null)
                return;

            Color myGreen = Color.Green;
            int greyGreen = 255 / 3;
            int threshold = 5;

            Color pixel;
            subtracted = new Bitmap(loaded.Width, loaded.Height);

            for (int i = 0; i < loaded.Width; i++)
            {
                if (i >= processed.Width)
                    break;

                for (int j = 0; j < loaded.Height; j++)
                {
                    if (j >= processed.Height)
                        break;

                    pixel = loaded.GetPixel(i, j);

                    subtracted.SetPixel(i, j,
                        Math.Abs((pixel.R + pixel.G + pixel.B) / 3 - greyGreen) < threshold ?
                        processed.GetPixel(i, j) : pixel
                        );
                }
            }
            pictureBox3.Image = subtracted;
        }

        private void openFileDialog2_FileOk(object sender, System.ComponentModel.CancelEventArgs e)
        {
            pictureBox2.Image = processed = new Bitmap(openFileDialog2.FileName);
        }

        private void trackBar3_Scroll(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded.Width, loaded.Height);

            float radians = trackBar3.Value * (float)Math.PI / 180f;
            int centerX = loaded.Width / 2;
            int centerY = loaded.Height / 2;
            float cosA = (float)Math.Cos(radians);
            float sinA = (float)Math.Sin(radians);

            for (int i = 0; i < loaded.Width; ++i)
                for (int j = 0; j < loaded.Height; ++j)
                {
                    int translatedX = i - centerX;
                    int translatedY = j - centerY;

                    int newX = (int)(translatedX * cosA - translatedY * sinA) + centerX;
                    int newY = (int)(translatedX * sinA + translatedY * cosA) + centerY;

                    processed.SetPixel(i, j,
                            newX >= 0 && newX < loaded.Width && newY >= 0 && newY < loaded.Height ?
                            loaded.GetPixel(newX, newY) : Color.Transparent
                        );
                }

            pictureBox2.Image = processed;
        }
    }
}
