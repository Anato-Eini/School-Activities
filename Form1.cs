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
    }
}
