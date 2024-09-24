namespace AliacSearchAlgo
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.programToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.resetToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.nodesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.saveNodesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.loadNodesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.searchToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.depthFirstSearchToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.stepToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.fullSearchToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.breadthFirstSearchToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.stepToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.fullSearchToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.hillClimbingToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.fullSearchToolStripMenuItem2 = new System.Windows.Forms.ToolStripMenuItem();
            this.resetSearchToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.normalToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.moveNodeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.removeNodeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.connectNodesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.setStartToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.setGoalNodeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.clearAllNodesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.aboutToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.label1 = new System.Windows.Forms.Label();
            this.saveFileDialog1 = new System.Windows.Forms.SaveFileDialog();
            this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
            this.menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.programToolStripMenuItem,
            this.nodesToolStripMenuItem,
            this.searchToolStripMenuItem,
            this.resetSearchToolStripMenuItem,
            this.aboutToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(685, 24);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // programToolStripMenuItem
            // 
            this.programToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.exitToolStripMenuItem,
            this.resetToolStripMenuItem});
            this.programToolStripMenuItem.Name = "programToolStripMenuItem";
            this.programToolStripMenuItem.Size = new System.Drawing.Size(62, 20);
            this.programToolStripMenuItem.Text = "Program ";
            // 
            // exitToolStripMenuItem
            // 
            this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            this.exitToolStripMenuItem.Size = new System.Drawing.Size(102, 22);
            this.exitToolStripMenuItem.Text = "Exit";
            this.exitToolStripMenuItem.Click += new System.EventHandler(this.exitToolStripMenuItem_Click);
            // 
            // resetToolStripMenuItem
            // 
            this.resetToolStripMenuItem.Name = "resetToolStripMenuItem";
            this.resetToolStripMenuItem.Size = new System.Drawing.Size(102, 22);
            this.resetToolStripMenuItem.Text = "Reset";
            this.resetToolStripMenuItem.Click += new System.EventHandler(this.resetToolStripMenuItem_Click);
            // 
            // nodesToolStripMenuItem
            // 
            this.nodesToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.saveNodesToolStripMenuItem,
            this.loadNodesToolStripMenuItem});
            this.nodesToolStripMenuItem.Name = "nodesToolStripMenuItem";
            this.nodesToolStripMenuItem.Size = new System.Drawing.Size(52, 20);
            this.nodesToolStripMenuItem.Text = "Nodes ";
            // 
            // saveNodesToolStripMenuItem
            // 
            this.saveNodesToolStripMenuItem.Name = "saveNodesToolStripMenuItem";
            this.saveNodesToolStripMenuItem.Size = new System.Drawing.Size(131, 22);
            this.saveNodesToolStripMenuItem.Text = "Save Nodes";
            this.saveNodesToolStripMenuItem.Click += new System.EventHandler(this.saveNodesToolStripMenuItem_Click);
            // 
            // loadNodesToolStripMenuItem
            // 
            this.loadNodesToolStripMenuItem.Name = "loadNodesToolStripMenuItem";
            this.loadNodesToolStripMenuItem.Size = new System.Drawing.Size(131, 22);
            this.loadNodesToolStripMenuItem.Text = "Load Nodes";
            this.loadNodesToolStripMenuItem.Click += new System.EventHandler(this.loadNodesToolStripMenuItem_Click);
            // 
            // searchToolStripMenuItem
            // 
            this.searchToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.depthFirstSearchToolStripMenuItem,
            this.breadthFirstSearchToolStripMenuItem,
            this.hillClimbingToolStripMenuItem});
            this.searchToolStripMenuItem.Name = "searchToolStripMenuItem";
            this.searchToolStripMenuItem.Size = new System.Drawing.Size(52, 20);
            this.searchToolStripMenuItem.Text = "Search";
            // 
            // depthFirstSearchToolStripMenuItem
            // 
            this.depthFirstSearchToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.stepToolStripMenuItem,
            this.fullSearchToolStripMenuItem});
            this.depthFirstSearchToolStripMenuItem.Name = "depthFirstSearchToolStripMenuItem";
            this.depthFirstSearchToolStripMenuItem.Size = new System.Drawing.Size(172, 22);
            this.depthFirstSearchToolStripMenuItem.Text = "Depth First Search";
            this.depthFirstSearchToolStripMenuItem.Click += new System.EventHandler(this.depthFirstSearchToolStripMenuItem_Click);
            // 
            // stepToolStripMenuItem
            // 
            this.stepToolStripMenuItem.Name = "stepToolStripMenuItem";
            this.stepToolStripMenuItem.Size = new System.Drawing.Size(126, 22);
            this.stepToolStripMenuItem.Text = "Step";
            this.stepToolStripMenuItem.Click += new System.EventHandler(this.stepToolStripMenuItem_Click);
            // 
            // fullSearchToolStripMenuItem
            // 
            this.fullSearchToolStripMenuItem.Name = "fullSearchToolStripMenuItem";
            this.fullSearchToolStripMenuItem.Size = new System.Drawing.Size(126, 22);
            this.fullSearchToolStripMenuItem.Text = "Full Search";
            this.fullSearchToolStripMenuItem.Click += new System.EventHandler(this.fullSearchToolStripMenuItem_Click);
            // 
            // breadthFirstSearchToolStripMenuItem
            // 
            this.breadthFirstSearchToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.stepToolStripMenuItem1,
            this.fullSearchToolStripMenuItem1});
            this.breadthFirstSearchToolStripMenuItem.Name = "breadthFirstSearchToolStripMenuItem";
            this.breadthFirstSearchToolStripMenuItem.Size = new System.Drawing.Size(172, 22);
            this.breadthFirstSearchToolStripMenuItem.Text = "Breadth First Search";
            // 
            // stepToolStripMenuItem1
            // 
            this.stepToolStripMenuItem1.Name = "stepToolStripMenuItem1";
            this.stepToolStripMenuItem1.Size = new System.Drawing.Size(126, 22);
            this.stepToolStripMenuItem1.Text = "Step";
            this.stepToolStripMenuItem1.Click += new System.EventHandler(this.stepToolStripMenuItem1_Click);
            // 
            // fullSearchToolStripMenuItem1
            // 
            this.fullSearchToolStripMenuItem1.Name = "fullSearchToolStripMenuItem1";
            this.fullSearchToolStripMenuItem1.Size = new System.Drawing.Size(126, 22);
            this.fullSearchToolStripMenuItem1.Text = "Full Search";
            this.fullSearchToolStripMenuItem1.Click += new System.EventHandler(this.fullSearchToolStripMenuItem1_Click);
            // 
            // hillClimbingToolStripMenuItem
            // 
            this.hillClimbingToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fullSearchToolStripMenuItem2});
            this.hillClimbingToolStripMenuItem.Name = "hillClimbingToolStripMenuItem";
            this.hillClimbingToolStripMenuItem.Size = new System.Drawing.Size(172, 22);
            this.hillClimbingToolStripMenuItem.Text = "Hill Climbing";
            this.hillClimbingToolStripMenuItem.Click += new System.EventHandler(this.hillClimbingToolStripMenuItem_Click);
            // 
            // fullSearchToolStripMenuItem2
            // 
            this.fullSearchToolStripMenuItem2.Name = "fullSearchToolStripMenuItem2";
            this.fullSearchToolStripMenuItem2.Size = new System.Drawing.Size(152, 22);
            this.fullSearchToolStripMenuItem2.Text = "Full Search";
            this.fullSearchToolStripMenuItem2.Click += new System.EventHandler(this.fullSearchToolStripMenuItem2_Click);
            // 
            // resetSearchToolStripMenuItem
            // 
            this.resetSearchToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.normalToolStripMenuItem,
            this.moveNodeToolStripMenuItem,
            this.removeNodeToolStripMenuItem,
            this.connectNodesToolStripMenuItem,
            this.setStartToolStripMenuItem,
            this.setGoalNodeToolStripMenuItem,
            this.clearAllNodesToolStripMenuItem});
            this.resetSearchToolStripMenuItem.Name = "resetSearchToolStripMenuItem";
            this.resetSearchToolStripMenuItem.Size = new System.Drawing.Size(86, 20);
            this.resetSearchToolStripMenuItem.Text = "Node Settings";
            // 
            // normalToolStripMenuItem
            // 
            this.normalToolStripMenuItem.Name = "normalToolStripMenuItem";
            this.normalToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.normalToolStripMenuItem.Text = "Create Nodes";
            this.normalToolStripMenuItem.Click += new System.EventHandler(this.normalToolStripMenuItem_Click);
            // 
            // moveNodeToolStripMenuItem
            // 
            this.moveNodeToolStripMenuItem.Name = "moveNodeToolStripMenuItem";
            this.moveNodeToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.moveNodeToolStripMenuItem.Text = "Move Node";
            this.moveNodeToolStripMenuItem.Click += new System.EventHandler(this.moveNodeToolStripMenuItem_Click);
            // 
            // removeNodeToolStripMenuItem
            // 
            this.removeNodeToolStripMenuItem.Name = "removeNodeToolStripMenuItem";
            this.removeNodeToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.removeNodeToolStripMenuItem.Text = "Remove Node";
            this.removeNodeToolStripMenuItem.Click += new System.EventHandler(this.removeNodeToolStripMenuItem_Click);
            // 
            // connectNodesToolStripMenuItem
            // 
            this.connectNodesToolStripMenuItem.Name = "connectNodesToolStripMenuItem";
            this.connectNodesToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.connectNodesToolStripMenuItem.Text = "Connect Nodes";
            this.connectNodesToolStripMenuItem.Click += new System.EventHandler(this.connectNodesToolStripMenuItem_Click);
            // 
            // setStartToolStripMenuItem
            // 
            this.setStartToolStripMenuItem.Name = "setStartToolStripMenuItem";
            this.setStartToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.setStartToolStripMenuItem.Text = "Set Start Node";
            this.setStartToolStripMenuItem.Click += new System.EventHandler(this.setStartToolStripMenuItem_Click);
            // 
            // setGoalNodeToolStripMenuItem
            // 
            this.setGoalNodeToolStripMenuItem.Name = "setGoalNodeToolStripMenuItem";
            this.setGoalNodeToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.setGoalNodeToolStripMenuItem.Text = "Set Goal Node";
            this.setGoalNodeToolStripMenuItem.Click += new System.EventHandler(this.setGoalNodeToolStripMenuItem_Click);
            // 
            // clearAllNodesToolStripMenuItem
            // 
            this.clearAllNodesToolStripMenuItem.Name = "clearAllNodesToolStripMenuItem";
            this.clearAllNodesToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.clearAllNodesToolStripMenuItem.Text = "Clear all nodes";
            this.clearAllNodesToolStripMenuItem.Click += new System.EventHandler(this.clearAllNodesToolStripMenuItem_Click);
            // 
            // aboutToolStripMenuItem
            // 
            this.aboutToolStripMenuItem.Name = "aboutToolStripMenuItem";
            this.aboutToolStripMenuItem.Size = new System.Drawing.Size(48, 20);
            this.aboutToolStripMenuItem.Text = "About";
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.LightBlue;
            this.pictureBox1.Location = new System.Drawing.Point(12, 27);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(663, 394);
            this.pictureBox1.TabIndex = 1;
            this.pictureBox1.TabStop = false;
            this.pictureBox1.MouseMove += new System.Windows.Forms.MouseEventHandler(this.pictureBox1_MouseMove);
            this.pictureBox1.MouseClick += new System.Windows.Forms.MouseEventHandler(this.pictureBox1_MouseClick);
            this.pictureBox1.Paint += new System.Windows.Forms.PaintEventHandler(this.pictureBox1_Paint);
            // 
            // label1
            // 
            this.label1.AutoEllipsis = true;
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(549, 424);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(0, 24);
            this.label1.TabIndex = 2;
            // 
            // saveFileDialog1
            // 
            this.saveFileDialog1.FileOk += new System.ComponentModel.CancelEventHandler(this.saveFileDialog1_FileOk);
            // 
            // openFileDialog1
            // 
            this.openFileDialog1.FileOk += new System.ComponentModel.CancelEventHandler(this.openFileDialog1_FileOk);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(685, 461);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Search Algorithm by Chris Jordan Aliac";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem programToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem nodesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem saveNodesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem loadNodesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem searchToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem depthFirstSearchToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem breadthFirstSearchToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem hillClimbingToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem resetSearchToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem aboutToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem moveNodeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem removeNodeToolStripMenuItem;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ToolStripMenuItem normalToolStripMenuItem;
        private System.Windows.Forms.SaveFileDialog saveFileDialog1;
        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.ToolStripMenuItem connectNodesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem setStartToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem setGoalNodeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem stepToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem fullSearchToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem resetToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem clearAllNodesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem stepToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem fullSearchToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem fullSearchToolStripMenuItem2;
    }
}

