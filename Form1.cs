namespace DIP_Coins_Activity
{
    public partial class Form1 : Form
    {
        Bitmap? loaded;
        Bitmap? processed;

        public Form1()
        {
            InitializeComponent();
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog1.ShowDialog();
        }

        private void openFileDialog1_FileOk(object sender, System.ComponentModel.CancelEventArgs e)
        {
            pictureBox1.Image = loaded = new Bitmap(openFileDialog1.FileName);
        }

        private void countCoinsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (loaded == null)
                return;


            /// Use Edge Detection?

        }
    }
}
