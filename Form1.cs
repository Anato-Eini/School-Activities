using System.Windows.Markup;

namespace DIP_Activity
{
    public partial class Form1 : Form
    {
        Bitmap? loaded;
        Bitmap? processed;

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
                            ));
                    }
                    else
                    {
                        processed.SetPixel(i, j, Color.FromArgb(
                            Math.Max(pixel.R + value, 0),
                            Math.Max(pixel.G + value, 0),
                            Math.Max(pixel.B + value, 0)
                            ));
                    }
                }

            pictureBox2 .Image = processed;
        }
    }
}
