using ImageProcess2;
using WebCamLib;  

namespace DIP_Activity
{
    public partial class Form1 : Form
    {
        Bitmap? loaded;
        Bitmap? processed;
        Bitmap? subtracted;

        Device[] devices;

        public Form1()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Load the image from system to application
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void openFileDialog1_FileOk(object sender, System.ComponentModel.CancelEventArgs e)
        {
            pictureBox1.Image = loaded = new Bitmap(openFileDialog1.FileName);
        }

        /// <summary>
        /// Opens a dialogue for choosing image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog1.ShowDialog();
        }

        /// <summary>
        /// Copy operation of loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
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

        /// <summary>
        /// Opens a dialogue for saving an image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveFileDialog1.ShowDialog();
        }

        /// <summary>
        /// Save the image after confirming the dialogue
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void saveFileDialog1_FileOk(object sender, System.ComponentModel.CancelEventArgs e)
        {
            if (processed == null)
                return;

            processed.Save(saveFileDialog1.FileName);
        }

        /// <summary>
        /// Applies grayscale of loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void grayscalingToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            Bitmap copy = (Bitmap)loaded.Clone();
            BitmapFilter.GrayScale(copy);
            pictureBox2.Image = copy;


        }

        /// <summary>
        /// Inverts the colors of loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void inversionToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            Bitmap copy = (Bitmap)loaded.Clone();
            BitmapFilter.Invert(copy);
            pictureBox2.Image = copy;
        }

        /// <summary>
        /// Flip the loaded image horizontally
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
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

        /// <summary>
        /// Flip the loaded image vertically
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
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

        /// <summary>
        /// Shows a histogram(greyscaled) of the loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
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

        /// <summary>
        /// Adjusts brightness of the loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void trackBar1_Scroll(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            Bitmap copy = (Bitmap)loaded.Clone();

            BitmapFilter.Brightness(copy, trackBar1.Value);

            pictureBox2.Image = copy;

        }

        /// <summary>
        /// Adjusts contrasts of the loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void trackBar2_Scroll(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            Bitmap copy = (Bitmap)loaded.Clone();
            BitmapFilter.Contrast(copy, (SByte)trackBar2.Value);

            pictureBox2.Image = copy;

        }

        /// <summary>
        /// Applies sepia to the loaded imagej
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
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

        /// <summary>
        /// Opens dialogue for "background image" button
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button2_Click(object sender, EventArgs e)
        {
            openFileDialog2.ShowDialog();
        }

        /// <summary>
        /// Opens dialogue for "Load Image" button
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button1_Click(object sender, EventArgs e)
        {
            openFileDialog1.ShowDialog();
        }

        /// <summary>
        /// Do a subtraction operation of background and loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
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

        /// <summary>
        /// Load the image as background
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void openFileDialog2_FileOk(object sender, System.ComponentModel.CancelEventArgs e)
        {
            pictureBox2.Image = processed = new Bitmap(openFileDialog2.FileName);
        }

        /// <summary>
        /// Rotate the loaded image based on degrees value
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
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

        /// <summary>
        /// Scale the loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void trackBar4_Scroll(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            int newWidth = (int)(trackBar4.Value / 50f * loaded.Width);
            int newHeight = (int)(trackBar4.Value / 50f * loaded.Height);

            processed = new Bitmap(newWidth, newHeight);

            for (int i = 0; i < newWidth; ++i)
                for (int j = 0; j < newHeight; ++j)
                    processed.SetPixel(i, j, loaded.GetPixel(
                        i * loaded.Width / newWidth,
                        j * loaded.Height / newHeight
                        )
                     );

            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies a binary operation with thresholding to the loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void trackBar5_Scroll(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded.Width, loaded.Height);
            int threshold = trackBar5.Value;

            for (int i = 0; i < loaded.Width; ++i)
                for (int j = 0; j < loaded.Height; ++j)
                {
                    Color pixel = loaded.GetPixel(i, j);
                    processed.SetPixel(i, j,
                        (pixel.R + pixel.G + pixel.B) / 3 < threshold ?
                        Color.Black : Color.White
                        );
                }

            pictureBox2.Image = processed;

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            devices = DeviceManager.GetAllDevices();
        }

        private void onToolStripMenuItem_Click(object sender, EventArgs e)
        {
            devices[0].ShowWindow(pictureBox1);
        }

        private void offToolStripMenuItem_Click(object sender, EventArgs e)
        {
            devices[0].Stop();
        }

        private void subtractToolStripMenuItem_Click(object sender, EventArgs e)
        {
            timer1.Enabled = true;
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            if (processed == null)
                return;

            IDataObject data;
            Image bmap;
            devices[0].Sendmessage();
            data = Clipboard.GetDataObject();
            bmap = (Image)data.GetData("System.Drawing.Bitmap", true);
            if (bmap != null)
            {
                loaded = new Bitmap(bmap);

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
        }
    }
}
