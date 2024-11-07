using ImageProcess2;
using System.Drawing.Imaging;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
using WebCamLib;  

namespace DIP_Activity
{
    public partial class Form1 : Form
    {
        Bitmap? loaded;
        Bitmap? processed;
        Bitmap? subtracted;

        System.Windows.Forms.Timer currentTimer;
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

        /// <summary>
        /// Fetch all connected devices
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Form1_Load(object sender, EventArgs e)
        {
            devices = DeviceManager.GetAllDevices();
        }

        /// <summary>
        /// Render video frame to pictureBox1
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void onToolStripMenuItem_Click(object sender, EventArgs e)
        {
            devices[0].ShowWindow(pictureBox1);
        }

        /// <summary>
        /// Stops video
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void offToolStripMenuItem_Click(object sender, EventArgs e)
        {
            devices[0].Stop();
        }

        /// <summary>
        /// Enabled timer for video subtract
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void subtractToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer1;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Fetch video frame
        /// </summary>
        /// <returns></returns>
        private Image getData()
        {
            IDataObject data;
            devices[0].Sendmessage();
            data = Clipboard.GetDataObject();
            return (Image)data.GetData("System.Drawing.Bitmap", true);
        }

        /// <summary>
        /// Applies subtraction method to video frame
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer1_Tick(object sender, EventArgs e)
        {
            if (processed == null)
                return;

            Image bmap = getData();

            if (bmap != null)
            {
                loaded = new Bitmap(bmap);
                subtracted = new Bitmap(loaded.Width, loaded.Height);

                BitmapData bmLoaded = loaded.LockBits(
                    new Rectangle(0, 0, loaded.Width, loaded.Height),
                    ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                    );

                BitmapData bmProcessed = processed.LockBits(
                    new Rectangle(0, 0, processed.Width, processed.Height),
                    ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                    );

                BitmapData bmSubtracted = subtracted.LockBits(
                    new Rectangle(0, 0, subtracted.Width, subtracted.Height),
                    ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                    );

                int limitAve = 255 / 3;
                int threshold = 5;

                unsafe
                {
                    int paddingLoaded = bmLoaded.Stride - loaded.Width * 3;
                    int paddingProcessed = bmProcessed.Stride - processed.Width * 3;
                    int paddingSubtracted = bmSubtracted.Stride - subtracted.Width * 3;

                    byte* pLoaded = (byte*)bmLoaded.Scan0;
                    byte* pProcessed = (byte*)bmProcessed.Scan0;
                    byte* pSubtracted = (byte*)bmSubtracted.Scan0;

                    byte* start_p_processed = (byte*)bmProcessed.Scan0;

                    for (int i = 0;
                        i < loaded.Height;
                        i++, pLoaded += paddingLoaded, pSubtracted += paddingSubtracted)
                    {
                        for (int j = 0;
                            j < loaded.Width;
                            j++, pLoaded += 3, pSubtracted += 3)
                        {
                            if (Math.Abs(pLoaded[0] + pLoaded[1] + pLoaded[2] - limitAve) < threshold)
                            {
                                pSubtracted[0] = pProcessed[0];
                                pSubtracted[1] = pProcessed[1];
                                pSubtracted[2] = pProcessed[2];
                            }
                            else
                            {
                                pSubtracted[0] = pLoaded[0];
                                pSubtracted[1] = pLoaded[1];
                                pSubtracted[2] = pLoaded[2];
                            }

                            if (j < processed.Width)
                                pProcessed += 3;
                        }

                        if (i < processed.Height)
                            pProcessed = start_p_processed + i * 3;
                    }
                }

                loaded.UnlockBits(bmLoaded);
                processed.UnlockBits(bmProcessed);
                subtracted.UnlockBits(bmSubtracted);

                pictureBox3.Image = subtracted;
            }
        }

        /// <summary>
        /// Enables timer for copy timer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void copyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer2;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies copy from video frame
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer2_Tick(object sender, EventArgs e)
        {
            Image bmap = getData();

            if (bmap != null)
                pictureBox2.Image = bmap;
        }

        /// <summary>
        /// Enables grayscale timer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void grayscaleToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer3;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies grayscale of video frame
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer3_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image != null)
            {
                processed = new Bitmap(image);
                BitmapFilter.GrayScale(processed);
                pictureBox2.Image = processed;
            }
        }

        /// <summary>
        /// Enables mirror horizontal timer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void mirrorHorizontalToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer4;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies mirror horizontal to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer4_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image != null)
            {
                processed = new Bitmap(image);

                BitmapFilter.Flip(processed, true, false);

                pictureBox2.Image = processed;
            }
        }

        /// <summary>
        /// Enables mirror vertical timer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void mirrorVerticalToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer5;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies mirror vertical to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer5_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image != null)
            {
                processed = new Bitmap(image);

                BitmapFilter.Flip(processed, false, true);

                pictureBox2.Image = processed;
            }
        }

        /// <summary>
        /// Enables histogram timer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void histogramToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer6;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Render histogram from video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer6_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image != null)
            {
                loaded = new Bitmap(image);
                processed = new Bitmap(256, 420);

                BitmapData bmLoaded = loaded.LockBits(
                    new Rectangle(0, 0, loaded.Width, loaded.Height),
                    ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

                BitmapData bmProcessed = processed.LockBits(
                    new Rectangle(0, 0, processed.Width, processed.Height),
                    ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

                unsafe
                {
                    int[] histData = new int[256];
                    int maxFreq = 420;
                    int offSet = bmLoaded.Stride - loaded.Width * 3; // Padding value

                    byte* p = (byte*)(void*)bmLoaded.Scan0;

                    for (int i = 0; i < loaded.Height; i++)
                    {
                        for (int j = 0; j < loaded.Width; j++)
                        {
                            int ave = (int)(.299 * p[2] + .587 * p[1] + .114 * p[0]);
                            p[0] = p[1] = p[2] = (byte)ave;
                            histData[ave]++;

                            if (histData[ave] > maxFreq)
                                maxFreq = histData[ave];

                            p += 3;
                        }

                        p += offSet;
                    }

                    int mFactor = maxFreq / 420;
                    int count;

                    int lastRow = (processed.Height - 1) * bmProcessed.Stride;
                    byte* pointerLast = (byte*)(void*)bmProcessed.Scan0 + lastRow;

                    for (int i = 0; i < 256; i++)
                    {
                        p = pointerLast + i * 3;
                        count = Math.Min(420, histData[i] / mFactor);

                        for (int j = 0; j < count; j++)
                        {
                            p[0] = p[1] = p[2] = 255;

                            p -= bmProcessed.Stride;
                        }
                    }
                }

                loaded.UnlockBits(bmLoaded);
                processed.UnlockBits(bmProcessed);

                pictureBox2.Image = processed;
            }
        }

        /// <summary>
        /// Enables sepia timer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void sepiaToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer7;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies sepia to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer7_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image != null)
            {
                processed = new Bitmap(image);

                BitmapData bmProcessed = processed.LockBits(
                    new Rectangle(0, 0, processed.Width, processed.Height),
                    ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

                int offSet = bmProcessed.Stride - processed.Width * 3;

                unsafe
                {
                    byte* p = (byte*)(void*)bmProcessed.Scan0;

                    for (int i = 0; i < processed.Height; i++)
                    {
                        for (int j = 0; j < processed.Width; j++)
                        {
                            p[0] = (byte)Math.Min(255, p[2] * 0.272 + p[1] * 0.534 + p[0] * 0.131);
                            p[1] = (byte)Math.Min(255, p[2] * 0.349 + p[1] * 0.686 + p[0] * 0.168);
                            p[2] = (byte)Math.Min(255, p[2] * 0.393 + p[1] * 0.769 + p[0] * 0.189);

                            p += 3;
                        }

                        p += offSet;
                    }
                }

                processed.UnlockBits(bmProcessed);

                pictureBox2.Image = processed;
            }
        }

        /// <summary>
        /// Applies smooth filter to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void smoothingToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.Smooth(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies gaussian blur to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void gaussianBlurToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.Smooth(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies mean removal to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void meanRemovalToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.MeanRemoval(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies emboss laplacian to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void embossLaplacianToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.EmbossLaplacian(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies sharpen to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void sharpenToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.Sharpen(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies emboss horizontal/vertical to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void horizontalVerticalToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.EmbossHorzVertical(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies emboss all directions to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void allDirectionsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.EmbossAllDirections(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies emboss lossy to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lossyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.EmbossLossy(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies emboss horizontal only to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void horizontalOnlyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.EmbossHorizontal(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Applies emboss vertical only to loaded image
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void verticalOnlyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;

            processed = new Bitmap(loaded);
            BitmapFilter.EmbossVertical(processed);
            pictureBox2.Image = processed;
        }

        /// <summary>
        /// Enables timer for smooth filter
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void smoothToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer8;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies smooth filter to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer8_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image == null)
                return;

            pictureBox1.Image = loaded = new Bitmap(image);
            BitmapFilter.Smooth(loaded);
            pictureBox2.Image = loaded;
        }

        /// <summary>
        /// Enables timer for gaussian blur filter to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void gaussianBlurToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer9;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies gaussian blur filter to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer9_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image == null)
                return;

            pictureBox1.Image = loaded = new Bitmap(image);
            BitmapFilter.GaussianBlur(loaded);
            pictureBox2.Image = loaded;
        }

        /// <summary>
        /// Enables timer for sharpen filter
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void sharpenToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer10;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies sharpen to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer10_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image == null)
                return;

            pictureBox1.Image = loaded = new Bitmap(image);
            BitmapFilter.Sharpen(loaded);
            pictureBox2.Image = loaded;
        }

        /// <summary>
        /// Enables timer for mean removal filter
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void meanRemovalToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer11;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies mean removal filter to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer11_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image == null)
                return;

            pictureBox1.Image = loaded = new Bitmap(image);
            BitmapFilter.MeanRemoval(loaded);
            pictureBox2.Image = loaded;
        }

        /// <summary>
        /// Enables timer for emboss laplacian
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void embossLaplacianToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer12;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies emboss laplacian to video filters
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer12_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image == null)
                return;

            pictureBox1.Image = loaded = new Bitmap(image);
            BitmapFilter.EmbossLaplacian(loaded);
            pictureBox2.Image = loaded;

        }

        /// <summary>
        /// Enables emboss (horizontal/vertical) filter timer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void horizontalVerticalToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer13;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies emboss horizontal/vertical filter to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer13_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image == null)
                return;

            pictureBox1.Image = loaded = new Bitmap(image);
            BitmapFilter.EmbossHorzVertical(loaded);
            pictureBox2.Image = loaded;

        }

        /// <summary>
        /// Enables emboss all directions timer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void allDirectionsToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer14;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies emboss all directions filter to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer14_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image == null)
                return;

            pictureBox1.Image = loaded = new Bitmap(image);
            BitmapFilter.EmbossAllDirections(loaded);
            pictureBox2.Image = loaded;

        }

        /// <summary>
        /// Enables emboss lossy timer
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void lossyToolStripMenuItem1_Click(object sender, EventArgs e)
        {

            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer15;
            currentTimer.Enabled = true;
        }

        /// <summary>
        /// Applies emboss lossy to video frames
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void timer15_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image == null)
                return;

            pictureBox1.Image = loaded = new Bitmap(image);
            BitmapFilter.EmbossLossy(loaded);
            pictureBox2.Image = loaded;

        }

        private void horizontalOnlyToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            if (currentTimer != null)
                currentTimer.Enabled = false;

            currentTimer = timer16;
            currentTimer.Enabled = true;
        }

        private void timer16_Tick(object sender, EventArgs e)
        {
            Image image = getData();

            if (image == null)
                return;

            pictureBox1.Image = loaded = new Bitmap(image);
            BitmapFilter.EmbossHorizontal(loaded);
            pictureBox2.Image = loaded;

        }
    }
}
