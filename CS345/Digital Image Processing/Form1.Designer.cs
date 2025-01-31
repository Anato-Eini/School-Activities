﻿namespace DIP_Activity
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            components = new System.ComponentModel.Container();
            menuStrip1 = new MenuStrip();
            fileToolStripMenuItem = new ToolStripMenuItem();
            openToolStripMenuItem = new ToolStripMenuItem();
            saveToolStripMenuItem = new ToolStripMenuItem();
            dIPToolStripMenuItem = new ToolStripMenuItem();
            pixelCopyToolStripMenuItem = new ToolStripMenuItem();
            grayscalingToolStripMenuItem = new ToolStripMenuItem();
            inversionToolStripMenuItem = new ToolStripMenuItem();
            mirrorHorizontalToolStripMenuItem = new ToolStripMenuItem();
            mirrorVerticalToolStripMenuItem = new ToolStripMenuItem();
            histogramToolStripMenuItem = new ToolStripMenuItem();
            sepiaToolStripMenuItem = new ToolStripMenuItem();
            onToolStripMenuItem = new ToolStripMenuItem();
            offToolStripMenuItem = new ToolStripMenuItem();
            videoToolStripMenuItem = new ToolStripMenuItem();
            subtractToolStripMenuItem = new ToolStripMenuItem();
            copyToolStripMenuItem = new ToolStripMenuItem();
            grayscaleToolStripMenuItem = new ToolStripMenuItem();
            mirrorHorizontalToolStripMenuItem1 = new ToolStripMenuItem();
            mirrorVerticalToolStripMenuItem1 = new ToolStripMenuItem();
            histogramToolStripMenuItem1 = new ToolStripMenuItem();
            sepiaToolStripMenuItem1 = new ToolStripMenuItem();
            convolutionMatrixToolStripMenuItem = new ToolStripMenuItem();
            smoothToolStripMenuItem = new ToolStripMenuItem();
            gaussianBlurToolStripMenuItem1 = new ToolStripMenuItem();
            sharpenToolStripMenuItem1 = new ToolStripMenuItem();
            meanRemovalToolStripMenuItem1 = new ToolStripMenuItem();
            embossingToolStripMenuItem1 = new ToolStripMenuItem();
            embossLaplacianToolStripMenuItem1 = new ToolStripMenuItem();
            horizontalVerticalToolStripMenuItem1 = new ToolStripMenuItem();
            allDirectionsToolStripMenuItem1 = new ToolStripMenuItem();
            lossyToolStripMenuItem1 = new ToolStripMenuItem();
            horizontalOnlyToolStripMenuItem1 = new ToolStripMenuItem();
            verticalOnlyToolStripMenuItem1 = new ToolStripMenuItem();
            convolutionToolStripMenuItem = new ToolStripMenuItem();
            smoothingToolStripMenuItem = new ToolStripMenuItem();
            gaussianBlurToolStripMenuItem = new ToolStripMenuItem();
            sharpenToolStripMenuItem = new ToolStripMenuItem();
            meanRemovalToolStripMenuItem = new ToolStripMenuItem();
            embossingToolStripMenuItem = new ToolStripMenuItem();
            embossLaplacianToolStripMenuItem = new ToolStripMenuItem();
            horizontalVerticalToolStripMenuItem = new ToolStripMenuItem();
            allDirectionsToolStripMenuItem = new ToolStripMenuItem();
            lossyToolStripMenuItem = new ToolStripMenuItem();
            horizontalOnlyToolStripMenuItem = new ToolStripMenuItem();
            verticalOnlyToolStripMenuItem = new ToolStripMenuItem();
            saveFileDialog1 = new SaveFileDialog();
            openFileDialog1 = new OpenFileDialog();
            pictureBox1 = new PictureBox();
            pictureBox2 = new PictureBox();
            label1 = new Label();
            trackBar1 = new TrackBar();
            label3 = new Label();
            trackBar2 = new TrackBar();
            pictureBox3 = new PictureBox();
            button1 = new Button();
            button2 = new Button();
            button3 = new Button();
            openFileDialog2 = new OpenFileDialog();
            trackBar3 = new TrackBar();
            label2 = new Label();
            label4 = new Label();
            trackBar4 = new TrackBar();
            label5 = new Label();
            trackBar5 = new TrackBar();
            timer1 = new System.Windows.Forms.Timer(components);
            timer2 = new System.Windows.Forms.Timer(components);
            timer3 = new System.Windows.Forms.Timer(components);
            timer4 = new System.Windows.Forms.Timer(components);
            timer5 = new System.Windows.Forms.Timer(components);
            timer6 = new System.Windows.Forms.Timer(components);
            timer7 = new System.Windows.Forms.Timer(components);
            timer8 = new System.Windows.Forms.Timer(components);
            timer9 = new System.Windows.Forms.Timer(components);
            timer10 = new System.Windows.Forms.Timer(components);
            timer11 = new System.Windows.Forms.Timer(components);
            timer12 = new System.Windows.Forms.Timer(components);
            timer13 = new System.Windows.Forms.Timer(components);
            timer14 = new System.Windows.Forms.Timer(components);
            timer15 = new System.Windows.Forms.Timer(components);
            timer16 = new System.Windows.Forms.Timer(components);
            timer17 = new System.Windows.Forms.Timer(components);
            menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)pictureBox1).BeginInit();
            ((System.ComponentModel.ISupportInitialize)pictureBox2).BeginInit();
            ((System.ComponentModel.ISupportInitialize)trackBar1).BeginInit();
            ((System.ComponentModel.ISupportInitialize)trackBar2).BeginInit();
            ((System.ComponentModel.ISupportInitialize)pictureBox3).BeginInit();
            ((System.ComponentModel.ISupportInitialize)trackBar3).BeginInit();
            ((System.ComponentModel.ISupportInitialize)trackBar4).BeginInit();
            ((System.ComponentModel.ISupportInitialize)trackBar5).BeginInit();
            SuspendLayout();
            // 
            // menuStrip1
            // 
            menuStrip1.Items.AddRange(new ToolStripItem[] { fileToolStripMenuItem, dIPToolStripMenuItem, onToolStripMenuItem, offToolStripMenuItem, videoToolStripMenuItem, convolutionToolStripMenuItem });
            menuStrip1.Location = new Point(0, 0);
            menuStrip1.Name = "menuStrip1";
            menuStrip1.Size = new Size(1226, 24);
            menuStrip1.TabIndex = 1;
            menuStrip1.Text = "menuStrip1";
            // 
            // fileToolStripMenuItem
            // 
            fileToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { openToolStripMenuItem, saveToolStripMenuItem });
            fileToolStripMenuItem.Name = "fileToolStripMenuItem";
            fileToolStripMenuItem.Size = new Size(37, 20);
            fileToolStripMenuItem.Text = "File";
            // 
            // openToolStripMenuItem
            // 
            openToolStripMenuItem.Name = "openToolStripMenuItem";
            openToolStripMenuItem.Size = new Size(103, 22);
            openToolStripMenuItem.Text = "Open";
            openToolStripMenuItem.Click += openToolStripMenuItem_Click;
            // 
            // saveToolStripMenuItem
            // 
            saveToolStripMenuItem.Name = "saveToolStripMenuItem";
            saveToolStripMenuItem.Size = new Size(103, 22);
            saveToolStripMenuItem.Text = "Save";
            saveToolStripMenuItem.Click += saveToolStripMenuItem_Click;
            // 
            // dIPToolStripMenuItem
            // 
            dIPToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { pixelCopyToolStripMenuItem, grayscalingToolStripMenuItem, inversionToolStripMenuItem, mirrorHorizontalToolStripMenuItem, mirrorVerticalToolStripMenuItem, histogramToolStripMenuItem, sepiaToolStripMenuItem });
            dIPToolStripMenuItem.Name = "dIPToolStripMenuItem";
            dIPToolStripMenuItem.Size = new Size(37, 20);
            dIPToolStripMenuItem.Text = "DIP";
            // 
            // pixelCopyToolStripMenuItem
            // 
            pixelCopyToolStripMenuItem.Name = "pixelCopyToolStripMenuItem";
            pixelCopyToolStripMenuItem.Size = new Size(165, 22);
            pixelCopyToolStripMenuItem.Text = "Pixel Copy";
            pixelCopyToolStripMenuItem.Click += pixelCopyToolStripMenuItem_Click;
            // 
            // grayscalingToolStripMenuItem
            // 
            grayscalingToolStripMenuItem.Name = "grayscalingToolStripMenuItem";
            grayscalingToolStripMenuItem.Size = new Size(165, 22);
            grayscalingToolStripMenuItem.Text = "Grayscaling";
            grayscalingToolStripMenuItem.Click += grayscalingToolStripMenuItem_Click;
            // 
            // inversionToolStripMenuItem
            // 
            inversionToolStripMenuItem.Name = "inversionToolStripMenuItem";
            inversionToolStripMenuItem.Size = new Size(165, 22);
            inversionToolStripMenuItem.Text = "Inversion";
            inversionToolStripMenuItem.Click += inversionToolStripMenuItem_Click;
            // 
            // mirrorHorizontalToolStripMenuItem
            // 
            mirrorHorizontalToolStripMenuItem.Name = "mirrorHorizontalToolStripMenuItem";
            mirrorHorizontalToolStripMenuItem.Size = new Size(165, 22);
            mirrorHorizontalToolStripMenuItem.Text = "Mirror Horizontal";
            mirrorHorizontalToolStripMenuItem.Click += mirrorHorizontalToolStripMenuItem_Click;
            // 
            // mirrorVerticalToolStripMenuItem
            // 
            mirrorVerticalToolStripMenuItem.Name = "mirrorVerticalToolStripMenuItem";
            mirrorVerticalToolStripMenuItem.Size = new Size(165, 22);
            mirrorVerticalToolStripMenuItem.Text = "Mirror Vertical";
            mirrorVerticalToolStripMenuItem.Click += mirrorVerticalToolStripMenuItem_Click;
            // 
            // histogramToolStripMenuItem
            // 
            histogramToolStripMenuItem.Name = "histogramToolStripMenuItem";
            histogramToolStripMenuItem.Size = new Size(165, 22);
            histogramToolStripMenuItem.Text = "Histogram";
            histogramToolStripMenuItem.Click += histogramToolStripMenuItem_Click;
            // 
            // sepiaToolStripMenuItem
            // 
            sepiaToolStripMenuItem.Name = "sepiaToolStripMenuItem";
            sepiaToolStripMenuItem.Size = new Size(165, 22);
            sepiaToolStripMenuItem.Text = "Sepia";
            sepiaToolStripMenuItem.Click += sepiaToolStripMenuItem_Click;
            // 
            // onToolStripMenuItem
            // 
            onToolStripMenuItem.Name = "onToolStripMenuItem";
            onToolStripMenuItem.Size = new Size(33, 20);
            onToolStripMenuItem.Text = "on";
            onToolStripMenuItem.Click += onToolStripMenuItem_Click;
            // 
            // offToolStripMenuItem
            // 
            offToolStripMenuItem.Name = "offToolStripMenuItem";
            offToolStripMenuItem.Size = new Size(34, 20);
            offToolStripMenuItem.Text = "off";
            offToolStripMenuItem.Click += offToolStripMenuItem_Click;
            // 
            // videoToolStripMenuItem
            // 
            videoToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { subtractToolStripMenuItem, copyToolStripMenuItem, grayscaleToolStripMenuItem, mirrorHorizontalToolStripMenuItem1, mirrorVerticalToolStripMenuItem1, histogramToolStripMenuItem1, sepiaToolStripMenuItem1, convolutionMatrixToolStripMenuItem });
            videoToolStripMenuItem.Name = "videoToolStripMenuItem";
            videoToolStripMenuItem.Size = new Size(49, 20);
            videoToolStripMenuItem.Text = "Video";
            // 
            // subtractToolStripMenuItem
            // 
            subtractToolStripMenuItem.Name = "subtractToolStripMenuItem";
            subtractToolStripMenuItem.Size = new Size(180, 22);
            subtractToolStripMenuItem.Text = "Subtract";
            subtractToolStripMenuItem.Click += subtractToolStripMenuItem_Click;
            // 
            // copyToolStripMenuItem
            // 
            copyToolStripMenuItem.Name = "copyToolStripMenuItem";
            copyToolStripMenuItem.Size = new Size(180, 22);
            copyToolStripMenuItem.Text = "Copy";
            copyToolStripMenuItem.Click += copyToolStripMenuItem_Click;
            // 
            // grayscaleToolStripMenuItem
            // 
            grayscaleToolStripMenuItem.Name = "grayscaleToolStripMenuItem";
            grayscaleToolStripMenuItem.Size = new Size(180, 22);
            grayscaleToolStripMenuItem.Text = "Grayscale";
            grayscaleToolStripMenuItem.Click += grayscaleToolStripMenuItem_Click;
            // 
            // mirrorHorizontalToolStripMenuItem1
            // 
            mirrorHorizontalToolStripMenuItem1.Name = "mirrorHorizontalToolStripMenuItem1";
            mirrorHorizontalToolStripMenuItem1.Size = new Size(180, 22);
            mirrorHorizontalToolStripMenuItem1.Text = "Mirror Horizontal";
            mirrorHorizontalToolStripMenuItem1.Click += mirrorHorizontalToolStripMenuItem1_Click;
            // 
            // mirrorVerticalToolStripMenuItem1
            // 
            mirrorVerticalToolStripMenuItem1.Name = "mirrorVerticalToolStripMenuItem1";
            mirrorVerticalToolStripMenuItem1.Size = new Size(180, 22);
            mirrorVerticalToolStripMenuItem1.Text = "Mirror Vertical";
            mirrorVerticalToolStripMenuItem1.Click += mirrorVerticalToolStripMenuItem1_Click;
            // 
            // histogramToolStripMenuItem1
            // 
            histogramToolStripMenuItem1.Name = "histogramToolStripMenuItem1";
            histogramToolStripMenuItem1.Size = new Size(180, 22);
            histogramToolStripMenuItem1.Text = "Histogram";
            histogramToolStripMenuItem1.Click += histogramToolStripMenuItem1_Click;
            // 
            // sepiaToolStripMenuItem1
            // 
            sepiaToolStripMenuItem1.Name = "sepiaToolStripMenuItem1";
            sepiaToolStripMenuItem1.Size = new Size(180, 22);
            sepiaToolStripMenuItem1.Text = "Sepia";
            sepiaToolStripMenuItem1.Click += sepiaToolStripMenuItem1_Click;
            // 
            // convolutionMatrixToolStripMenuItem
            // 
            convolutionMatrixToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { smoothToolStripMenuItem, gaussianBlurToolStripMenuItem1, sharpenToolStripMenuItem1, meanRemovalToolStripMenuItem1, embossingToolStripMenuItem1 });
            convolutionMatrixToolStripMenuItem.Name = "convolutionMatrixToolStripMenuItem";
            convolutionMatrixToolStripMenuItem.Size = new Size(180, 22);
            convolutionMatrixToolStripMenuItem.Text = "Convolution Matrix";
            // 
            // smoothToolStripMenuItem
            // 
            smoothToolStripMenuItem.Name = "smoothToolStripMenuItem";
            smoothToolStripMenuItem.Size = new Size(180, 22);
            smoothToolStripMenuItem.Text = "Smooth";
            smoothToolStripMenuItem.Click += smoothToolStripMenuItem_Click;
            // 
            // gaussianBlurToolStripMenuItem1
            // 
            gaussianBlurToolStripMenuItem1.Name = "gaussianBlurToolStripMenuItem1";
            gaussianBlurToolStripMenuItem1.Size = new Size(180, 22);
            gaussianBlurToolStripMenuItem1.Text = "Gaussian Blur";
            gaussianBlurToolStripMenuItem1.Click += gaussianBlurToolStripMenuItem1_Click;
            // 
            // sharpenToolStripMenuItem1
            // 
            sharpenToolStripMenuItem1.Name = "sharpenToolStripMenuItem1";
            sharpenToolStripMenuItem1.Size = new Size(180, 22);
            sharpenToolStripMenuItem1.Text = "Sharpen";
            sharpenToolStripMenuItem1.Click += sharpenToolStripMenuItem1_Click;
            // 
            // meanRemovalToolStripMenuItem1
            // 
            meanRemovalToolStripMenuItem1.Name = "meanRemovalToolStripMenuItem1";
            meanRemovalToolStripMenuItem1.Size = new Size(180, 22);
            meanRemovalToolStripMenuItem1.Text = "Mean Removal";
            meanRemovalToolStripMenuItem1.Click += meanRemovalToolStripMenuItem1_Click;
            // 
            // embossingToolStripMenuItem1
            // 
            embossingToolStripMenuItem1.DropDownItems.AddRange(new ToolStripItem[] { embossLaplacianToolStripMenuItem1, horizontalVerticalToolStripMenuItem1, allDirectionsToolStripMenuItem1, lossyToolStripMenuItem1, horizontalOnlyToolStripMenuItem1, verticalOnlyToolStripMenuItem1 });
            embossingToolStripMenuItem1.Name = "embossingToolStripMenuItem1";
            embossingToolStripMenuItem1.Size = new Size(180, 22);
            embossingToolStripMenuItem1.Text = "Embossing";
            // 
            // embossLaplacianToolStripMenuItem1
            // 
            embossLaplacianToolStripMenuItem1.Name = "embossLaplacianToolStripMenuItem1";
            embossLaplacianToolStripMenuItem1.Size = new Size(180, 22);
            embossLaplacianToolStripMenuItem1.Text = "Emboss Laplacian";
            embossLaplacianToolStripMenuItem1.Click += embossLaplacianToolStripMenuItem1_Click;
            // 
            // horizontalVerticalToolStripMenuItem1
            // 
            horizontalVerticalToolStripMenuItem1.Name = "horizontalVerticalToolStripMenuItem1";
            horizontalVerticalToolStripMenuItem1.Size = new Size(180, 22);
            horizontalVerticalToolStripMenuItem1.Text = "Horizontal/Vertical";
            horizontalVerticalToolStripMenuItem1.Click += horizontalVerticalToolStripMenuItem1_Click;
            // 
            // allDirectionsToolStripMenuItem1
            // 
            allDirectionsToolStripMenuItem1.Name = "allDirectionsToolStripMenuItem1";
            allDirectionsToolStripMenuItem1.Size = new Size(180, 22);
            allDirectionsToolStripMenuItem1.Text = "All Directions";
            allDirectionsToolStripMenuItem1.Click += allDirectionsToolStripMenuItem1_Click;
            // 
            // lossyToolStripMenuItem1
            // 
            lossyToolStripMenuItem1.Name = "lossyToolStripMenuItem1";
            lossyToolStripMenuItem1.Size = new Size(180, 22);
            lossyToolStripMenuItem1.Text = "Lossy";
            lossyToolStripMenuItem1.Click += lossyToolStripMenuItem1_Click;
            // 
            // horizontalOnlyToolStripMenuItem1
            // 
            horizontalOnlyToolStripMenuItem1.Name = "horizontalOnlyToolStripMenuItem1";
            horizontalOnlyToolStripMenuItem1.Size = new Size(180, 22);
            horizontalOnlyToolStripMenuItem1.Text = "Horizontal Only";
            horizontalOnlyToolStripMenuItem1.Click += horizontalOnlyToolStripMenuItem1_Click;
            // 
            // verticalOnlyToolStripMenuItem1
            // 
            verticalOnlyToolStripMenuItem1.Name = "verticalOnlyToolStripMenuItem1";
            verticalOnlyToolStripMenuItem1.Size = new Size(180, 22);
            verticalOnlyToolStripMenuItem1.Text = "Vertical Only";
            verticalOnlyToolStripMenuItem1.Click += verticalOnlyToolStripMenuItem1_Click;
            // 
            // convolutionToolStripMenuItem
            // 
            convolutionToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { smoothingToolStripMenuItem, gaussianBlurToolStripMenuItem, sharpenToolStripMenuItem, meanRemovalToolStripMenuItem, embossingToolStripMenuItem });
            convolutionToolStripMenuItem.Name = "convolutionToolStripMenuItem";
            convolutionToolStripMenuItem.Size = new Size(122, 20);
            convolutionToolStripMenuItem.Text = "Convolution Matrix";
            // 
            // smoothingToolStripMenuItem
            // 
            smoothingToolStripMenuItem.Name = "smoothingToolStripMenuItem";
            smoothingToolStripMenuItem.Size = new Size(153, 22);
            smoothingToolStripMenuItem.Text = "Smoothing";
            smoothingToolStripMenuItem.Click += smoothingToolStripMenuItem_Click;
            // 
            // gaussianBlurToolStripMenuItem
            // 
            gaussianBlurToolStripMenuItem.Name = "gaussianBlurToolStripMenuItem";
            gaussianBlurToolStripMenuItem.Size = new Size(153, 22);
            gaussianBlurToolStripMenuItem.Text = "Gaussian Blur";
            gaussianBlurToolStripMenuItem.Click += gaussianBlurToolStripMenuItem_Click;
            // 
            // sharpenToolStripMenuItem
            // 
            sharpenToolStripMenuItem.Name = "sharpenToolStripMenuItem";
            sharpenToolStripMenuItem.Size = new Size(153, 22);
            sharpenToolStripMenuItem.Text = "Sharpen";
            sharpenToolStripMenuItem.Click += sharpenToolStripMenuItem_Click;
            // 
            // meanRemovalToolStripMenuItem
            // 
            meanRemovalToolStripMenuItem.Name = "meanRemovalToolStripMenuItem";
            meanRemovalToolStripMenuItem.Size = new Size(153, 22);
            meanRemovalToolStripMenuItem.Text = "Mean Removal";
            meanRemovalToolStripMenuItem.Click += meanRemovalToolStripMenuItem_Click;
            // 
            // embossingToolStripMenuItem
            // 
            embossingToolStripMenuItem.DropDownItems.AddRange(new ToolStripItem[] { embossLaplacianToolStripMenuItem, horizontalVerticalToolStripMenuItem, allDirectionsToolStripMenuItem, lossyToolStripMenuItem, horizontalOnlyToolStripMenuItem, verticalOnlyToolStripMenuItem });
            embossingToolStripMenuItem.Name = "embossingToolStripMenuItem";
            embossingToolStripMenuItem.Size = new Size(153, 22);
            embossingToolStripMenuItem.Text = "Embossing";
            // 
            // embossLaplacianToolStripMenuItem
            // 
            embossLaplacianToolStripMenuItem.Name = "embossLaplacianToolStripMenuItem";
            embossLaplacianToolStripMenuItem.Size = new Size(172, 22);
            embossLaplacianToolStripMenuItem.Text = "Emboss Laplacian";
            embossLaplacianToolStripMenuItem.Click += embossLaplacianToolStripMenuItem_Click;
            // 
            // horizontalVerticalToolStripMenuItem
            // 
            horizontalVerticalToolStripMenuItem.Name = "horizontalVerticalToolStripMenuItem";
            horizontalVerticalToolStripMenuItem.Size = new Size(172, 22);
            horizontalVerticalToolStripMenuItem.Text = "Horizontal/Vertical";
            horizontalVerticalToolStripMenuItem.Click += horizontalVerticalToolStripMenuItem_Click;
            // 
            // allDirectionsToolStripMenuItem
            // 
            allDirectionsToolStripMenuItem.Name = "allDirectionsToolStripMenuItem";
            allDirectionsToolStripMenuItem.Size = new Size(172, 22);
            allDirectionsToolStripMenuItem.Text = "All Directions";
            allDirectionsToolStripMenuItem.Click += allDirectionsToolStripMenuItem_Click;
            // 
            // lossyToolStripMenuItem
            // 
            lossyToolStripMenuItem.Name = "lossyToolStripMenuItem";
            lossyToolStripMenuItem.Size = new Size(172, 22);
            lossyToolStripMenuItem.Text = "Lossy";
            lossyToolStripMenuItem.Click += lossyToolStripMenuItem_Click;
            // 
            // horizontalOnlyToolStripMenuItem
            // 
            horizontalOnlyToolStripMenuItem.Name = "horizontalOnlyToolStripMenuItem";
            horizontalOnlyToolStripMenuItem.Size = new Size(172, 22);
            horizontalOnlyToolStripMenuItem.Text = "Horizontal Only";
            horizontalOnlyToolStripMenuItem.Click += horizontalOnlyToolStripMenuItem_Click;
            // 
            // verticalOnlyToolStripMenuItem
            // 
            verticalOnlyToolStripMenuItem.Name = "verticalOnlyToolStripMenuItem";
            verticalOnlyToolStripMenuItem.Size = new Size(172, 22);
            verticalOnlyToolStripMenuItem.Text = "Vertical Only";
            verticalOnlyToolStripMenuItem.Click += verticalOnlyToolStripMenuItem_Click;
            // 
            // saveFileDialog1
            // 
            saveFileDialog1.FileOk += saveFileDialog1_FileOk;
            // 
            // openFileDialog1
            // 
            openFileDialog1.FileName = "openFileDialog1";
            openFileDialog1.FileOk += openFileDialog1_FileOk;
            // 
            // pictureBox1
            // 
            pictureBox1.Location = new Point(0, 281);
            pictureBox1.Name = "pictureBox1";
            pictureBox1.Size = new Size(385, 420);
            pictureBox1.SizeMode = PictureBoxSizeMode.StretchImage;
            pictureBox1.TabIndex = 2;
            pictureBox1.TabStop = false;
            // 
            // pictureBox2
            // 
            pictureBox2.Location = new Point(391, 281);
            pictureBox2.Name = "pictureBox2";
            pictureBox2.Size = new Size(409, 420);
            pictureBox2.SizeMode = PictureBoxSizeMode.StretchImage;
            pictureBox2.TabIndex = 3;
            pictureBox2.TabStop = false;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(12, 24);
            label1.Name = "label1";
            label1.Size = new Size(62, 15);
            label1.TabIndex = 4;
            label1.Text = "Brightness";
            // 
            // trackBar1
            // 
            trackBar1.LargeChange = 10;
            trackBar1.Location = new Point(12, 42);
            trackBar1.Maximum = 255;
            trackBar1.Minimum = -255;
            trackBar1.Name = "trackBar1";
            trackBar1.Size = new Size(373, 45);
            trackBar1.TabIndex = 10;
            trackBar1.TickFrequency = 5;
            trackBar1.Scroll += trackBar1_Scroll;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(12, 81);
            label3.Name = "label3";
            label3.Size = new Size(52, 15);
            label3.TabIndex = 12;
            label3.Text = "Contrast";
            // 
            // trackBar2
            // 
            trackBar2.LargeChange = 10;
            trackBar2.Location = new Point(12, 99);
            trackBar2.Maximum = 100;
            trackBar2.Minimum = -100;
            trackBar2.Name = "trackBar2";
            trackBar2.Size = new Size(362, 45);
            trackBar2.TabIndex = 13;
            trackBar2.TickFrequency = 5;
            trackBar2.Scroll += trackBar2_Scroll;
            // 
            // pictureBox3
            // 
            pictureBox3.Location = new Point(806, 281);
            pictureBox3.Name = "pictureBox3";
            pictureBox3.Size = new Size(409, 420);
            pictureBox3.SizeMode = PictureBoxSizeMode.StretchImage;
            pictureBox3.TabIndex = 14;
            pictureBox3.TabStop = false;
            // 
            // button1
            // 
            button1.Location = new Point(104, 707);
            button1.Name = "button1";
            button1.Size = new Size(149, 23);
            button1.TabIndex = 15;
            button1.Text = "Load Image";
            button1.UseVisualStyleBackColor = true;
            button1.Click += button1_Click;
            // 
            // button2
            // 
            button2.Location = new Point(524, 707);
            button2.Name = "button2";
            button2.Size = new Size(149, 23);
            button2.TabIndex = 16;
            button2.Text = "Load Background";
            button2.UseVisualStyleBackColor = true;
            button2.Click += button2_Click;
            // 
            // button3
            // 
            button3.Location = new Point(944, 707);
            button3.Name = "button3";
            button3.Size = new Size(149, 23);
            button3.TabIndex = 17;
            button3.Text = "Subtract";
            button3.UseVisualStyleBackColor = true;
            button3.Click += button3_Click;
            // 
            // openFileDialog2
            // 
            openFileDialog2.FileName = "openFileDialog2";
            openFileDialog2.FileOk += openFileDialog2_FileOk;
            // 
            // trackBar3
            // 
            trackBar3.LargeChange = 10;
            trackBar3.Location = new Point(400, 42);
            trackBar3.Maximum = 180;
            trackBar3.Minimum = -180;
            trackBar3.Name = "trackBar3";
            trackBar3.Size = new Size(373, 45);
            trackBar3.TabIndex = 18;
            trackBar3.TickFrequency = 10;
            trackBar3.Scroll += trackBar3_Scroll;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(400, 24);
            label2.Name = "label2";
            label2.Size = new Size(41, 15);
            label2.TabIndex = 19;
            label2.Text = "Rotate";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(400, 81);
            label4.Name = "label4";
            label4.Size = new Size(34, 15);
            label4.TabIndex = 20;
            label4.Text = "Scale";
            // 
            // trackBar4
            // 
            trackBar4.LargeChange = 10;
            trackBar4.Location = new Point(400, 99);
            trackBar4.Maximum = 100;
            trackBar4.Minimum = 1;
            trackBar4.Name = "trackBar4";
            trackBar4.Size = new Size(373, 45);
            trackBar4.TabIndex = 21;
            trackBar4.TickFrequency = 5;
            trackBar4.Value = 50;
            trackBar4.Scroll += trackBar4_Scroll;
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new Point(783, 24);
            label5.Name = "label5";
            label5.Size = new Size(95, 15);
            label5.TabIndex = 22;
            label5.Text = "Binary Threshold";
            // 
            // trackBar5
            // 
            trackBar5.LargeChange = 10;
            trackBar5.Location = new Point(783, 42);
            trackBar5.Maximum = 255;
            trackBar5.Name = "trackBar5";
            trackBar5.Size = new Size(373, 45);
            trackBar5.TabIndex = 23;
            trackBar5.TickFrequency = 10;
            trackBar5.Scroll += trackBar5_Scroll;
            // 
            // timer1
            // 
            timer1.Interval = 60;
            timer1.Tick += timer1_Tick;
            // 
            // timer2
            // 
            timer2.Interval = 60;
            timer2.Tick += timer2_Tick;
            // 
            // timer3
            // 
            timer3.Interval = 60;
            timer3.Tick += timer3_Tick;
            // 
            // timer4
            // 
            timer4.Interval = 60;
            timer4.Tick += timer4_Tick;
            // 
            // timer5
            // 
            timer5.Interval = 60;
            timer5.Tick += timer5_Tick;
            // 
            // timer6
            // 
            timer6.Interval = 60;
            timer6.Tick += timer6_Tick;
            // 
            // timer7
            // 
            timer7.Interval = 60;
            timer7.Tick += timer7_Tick;
            // 
            // timer8
            // 
            timer8.Interval = 60;
            timer8.Tick += timer8_Tick;
            // 
            // timer9
            // 
            timer9.Interval = 60;
            timer9.Tick += timer9_Tick;
            // 
            // timer10
            // 
            timer10.Interval = 60;
            timer10.Tick += timer10_Tick;
            // 
            // timer11
            // 
            timer11.Interval = 60;
            timer11.Tick += timer11_Tick;
            // 
            // timer12
            // 
            timer12.Interval = 60;
            timer12.Tick += timer12_Tick;
            // 
            // timer13
            // 
            timer13.Interval = 60;
            timer13.Tick += timer13_Tick;
            // 
            // timer14
            // 
            timer14.Interval = 60;
            timer14.Tick += timer14_Tick;
            // 
            // timer15
            // 
            timer15.Interval = 60;
            timer15.Tick += timer15_Tick;
            // 
            // timer16
            // 
            timer16.Interval = 60;
            timer16.Tick += timer16_Tick;
            // 
            // timer17
            // 
            timer17.Interval = 60;
            timer17.Tick += timer17_Tick;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1226, 749);
            Controls.Add(trackBar5);
            Controls.Add(label5);
            Controls.Add(trackBar4);
            Controls.Add(label4);
            Controls.Add(label2);
            Controls.Add(trackBar3);
            Controls.Add(button3);
            Controls.Add(button2);
            Controls.Add(button1);
            Controls.Add(pictureBox3);
            Controls.Add(trackBar2);
            Controls.Add(label3);
            Controls.Add(trackBar1);
            Controls.Add(label1);
            Controls.Add(pictureBox2);
            Controls.Add(pictureBox1);
            Controls.Add(menuStrip1);
            MainMenuStrip = menuStrip1;
            Name = "Form1";
            Text = "Form1";
            Load += Form1_Load;
            menuStrip1.ResumeLayout(false);
            menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)pictureBox1).EndInit();
            ((System.ComponentModel.ISupportInitialize)pictureBox2).EndInit();
            ((System.ComponentModel.ISupportInitialize)trackBar1).EndInit();
            ((System.ComponentModel.ISupportInitialize)trackBar2).EndInit();
            ((System.ComponentModel.ISupportInitialize)pictureBox3).EndInit();
            ((System.ComponentModel.ISupportInitialize)trackBar3).EndInit();
            ((System.ComponentModel.ISupportInitialize)trackBar4).EndInit();
            ((System.ComponentModel.ISupportInitialize)trackBar5).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion
        private MenuStrip menuStrip1;
        private ToolStripMenuItem fileToolStripMenuItem;
        private ToolStripMenuItem openToolStripMenuItem;
        private ToolStripMenuItem saveToolStripMenuItem;
        private ToolStripMenuItem dIPToolStripMenuItem;
        private ToolStripMenuItem pixelCopyToolStripMenuItem;
        private ToolStripMenuItem grayscalingToolStripMenuItem;
        private ToolStripMenuItem inversionToolStripMenuItem;
        private ToolStripMenuItem mirrorHorizontalToolStripMenuItem;
        private ToolStripMenuItem mirrorVerticalToolStripMenuItem;
        private SaveFileDialog saveFileDialog1;
        private OpenFileDialog openFileDialog1;
        private PictureBox pictureBox1;
        private PictureBox pictureBox2;
        private ToolStripMenuItem histogramToolStripMenuItem;
        private Label label1;
        private TrackBar trackBar1;
        private Label label3;
        private TrackBar trackBar2;
        private ToolStripMenuItem sepiaToolStripMenuItem;
        private PictureBox pictureBox3;
        private Button button1;
        private Button button2;
        private Button button3;
        private OpenFileDialog openFileDialog2;
        private TrackBar trackBar3;
        private Label label2;
        private Label label4;
        private TrackBar trackBar4;
        private Label label5;
        private TrackBar trackBar5;
        private ToolStripMenuItem onToolStripMenuItem;
        private ToolStripMenuItem offToolStripMenuItem;
        private ToolStripMenuItem videoToolStripMenuItem;
        private ToolStripMenuItem subtractToolStripMenuItem;
        private System.Windows.Forms.Timer timer1;
        private ToolStripMenuItem copyToolStripMenuItem;
        private ToolStripMenuItem grayscaleToolStripMenuItem;
        private ToolStripMenuItem mirrorHorizontalToolStripMenuItem1;
        private ToolStripMenuItem mirrorVerticalToolStripMenuItem1;
        private ToolStripMenuItem histogramToolStripMenuItem1;
        private ToolStripMenuItem sepiaToolStripMenuItem1;
        private System.Windows.Forms.Timer timer2;
        private System.Windows.Forms.Timer timer3;
        private System.Windows.Forms.Timer timer4;
        private System.Windows.Forms.Timer timer5;
        private System.Windows.Forms.Timer timer6;
        private System.Windows.Forms.Timer timer7;
        private ToolStripMenuItem convolutionToolStripMenuItem;
        private ToolStripMenuItem smoothingToolStripMenuItem;
        private ToolStripMenuItem gaussianBlurToolStripMenuItem;
        private ToolStripMenuItem sharpenToolStripMenuItem;
        private ToolStripMenuItem meanRemovalToolStripMenuItem;
        private ToolStripMenuItem convolutionMatrixToolStripMenuItem;
        private ToolStripMenuItem embossingToolStripMenuItem;
        private ToolStripMenuItem embossLaplacianToolStripMenuItem;
        private ToolStripMenuItem horizontalVerticalToolStripMenuItem;
        private ToolStripMenuItem allDirectionsToolStripMenuItem;
        private ToolStripMenuItem lossyToolStripMenuItem;
        private ToolStripMenuItem horizontalOnlyToolStripMenuItem;
        private ToolStripMenuItem verticalOnlyToolStripMenuItem;
        private System.Windows.Forms.Timer timer8;
        private System.Windows.Forms.Timer timer9;
        private System.Windows.Forms.Timer timer10;
        private System.Windows.Forms.Timer timer11;
        private System.Windows.Forms.Timer timer12;
        private System.Windows.Forms.Timer timer13;
        private System.Windows.Forms.Timer timer14;
        private System.Windows.Forms.Timer timer15;
        private System.Windows.Forms.Timer timer16;
        private System.Windows.Forms.Timer timer17;
        private ToolStripMenuItem smoothToolStripMenuItem;
        private ToolStripMenuItem gaussianBlurToolStripMenuItem1;
        private ToolStripMenuItem sharpenToolStripMenuItem1;
        private ToolStripMenuItem meanRemovalToolStripMenuItem1;
        private ToolStripMenuItem embossingToolStripMenuItem1;
        private ToolStripMenuItem embossLaplacianToolStripMenuItem1;
        private ToolStripMenuItem horizontalVerticalToolStripMenuItem1;
        private ToolStripMenuItem allDirectionsToolStripMenuItem1;
        private ToolStripMenuItem lossyToolStripMenuItem1;
        private ToolStripMenuItem horizontalOnlyToolStripMenuItem1;
        private ToolStripMenuItem verticalOnlyToolStripMenuItem1;
    }
}
