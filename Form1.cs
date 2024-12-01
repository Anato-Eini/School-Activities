using Backprop;
using System.CodeDom;

namespace Backpropagation_Neural_Networks
{
    public partial class Form1 : Form
    {
        NeuralNet? neuralNet;

        int currentEpoch;

        int[,] dataset;

        /// <summary>
        /// Initializes form
        /// </summary>
        public Form1()
        {
            InitializeComponent();
            dataset = new int[16, 5]
            {
                {0, 0, 0, 0, 0 },
                {0, 0, 0, 1, 0 },
                {0, 0, 1, 0, 0 },
                {0, 0, 1, 1, 0 },
                {0, 1, 0, 0, 0 },
                {0, 1, 1, 1, 0 },
                {0, 1, 1, 0, 0 },
                {0, 1, 0, 1, 0 },
                {1, 0, 1, 0, 0 },
                {1, 0, 1, 1, 0 },
                {1, 0, 0, 0, 0 },
                {1, 0, 1, 1, 0 },
                {1, 1, 1, 0, 0 },
                {1, 1, 0, 1, 0 },
                {1, 1, 1, 0, 0 },
                {1, 1, 1, 1, 1 }
            };
        }

        /// <summary>
        /// Initialize Neural Network
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button1_Click(object sender, EventArgs e)
        {
            label8.Text = "";
            neuralNet = new NeuralNet(4, 20, 1);
            textBox9.Text = 20.ToString();
        }

        /// <summary>
        /// The network is considered good if it passed all the tests
        /// </summary>
        /// <returns></returns>
        private bool fitTest()
        {
            if (neuralNet == null)
                return false;

            for (int i = 0; i < 16; i++)
            {
                neuralNet.setInputs(0, dataset[i, 0]);
                neuralNet.setInputs(1, dataset[i, 1]);
                neuralNet.setInputs(2, dataset[i, 2]);
                neuralNet.setInputs(3, dataset[i, 3]);
                neuralNet.run();

                if (Math.Round(neuralNet.getOuputData(0)) != dataset[i, 4])
                    return false;
            }

            return true;
        }

        /// <summary>
        /// Trains 1 epoch
        /// </summary>
        private void train()
        {
            if (neuralNet == null)
                return;

            for (int i = 0; i < 16; i++)
            {
                neuralNet.setInputs(0, dataset[i, 0]);
                neuralNet.setInputs(1, dataset[i, 1]);
                neuralNet.setInputs(2, dataset[i, 2]);
                neuralNet.setInputs(3, dataset[i, 3]);
                neuralNet.setDesiredOutput(0, dataset[i, 4]);
                neuralNet.learn();
            }
        }

        /// <summary>
        /// Trains the model until it correctly fits the desired outputs
        /// </summary>
        private void trainUntilFit()
        {
            textBox10.Text = 0.ToString();
            currentEpoch = 0;

            while (!fitTest())
            {
                train();

                textBox10.Text = (++currentEpoch).ToString();
            }
        }

        /// <summary>
        /// Button for model learning
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button2_Click(object sender, EventArgs e)
        {
            if (neuralNet == null)
            {
                label8.Text = "Neural Network not initialized!";
                return;
            }

            label8.Text = "";

            trainUntilFit();
        }

        /// <summary>
        /// Outputs a data from the model friven a set of inputs
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button3_Click(object sender, EventArgs e)
        {
            label8.Text = "";

            try
            {
                neuralNet.setInputs(0, Convert.ToDouble(textBox1.Text));
                neuralNet.setInputs(1, Convert.ToDouble(textBox2.Text));
                neuralNet.setInputs(2, Convert.ToDouble(textBox3.Text));
                neuralNet.setInputs(3, Convert.ToDouble(textBox4.Text));
                neuralNet.run();

                double output = neuralNet.getOuputData(0);
                textBox5.Text = output.ToString();
                textBox6.Text = Convert.ToInt32(Math.Round(output)).ToString();
            }
            catch (NullReferenceException ex)
            {
                label8.Text = "Model is not yet initialized!";
            }
            catch (Exception ex)
            {
                label8.Text = "Incorrect Inputs!";
            }
        }

        /// <summary>
        /// Finds minimum number of hidden neuron.
        /// Note that the maximum epoch is 5000
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void button4_Click(object sender, EventArgs e)
        {
            while (true)
            {
                for (int i = 1; i <= 100; i++)//neurons
                {
                    textBox9.Text = i.ToString();

                    neuralNet = new NeuralNet(4, i, 1);

                    trainUntilFit();
                }
            }
        }

        private void button5_Click(object sender, EventArgs e)
        {
            int minimumEpoch = int.MaxValue;
            int attempts = 0;

            for (int i = 1; i <= 10000; i++) //Neurons
            {
                textBox9.Text = i.ToString();

                neuralNet = new NeuralNet(4, i, 1);

                trainUntilFit();

                if (currentEpoch < minimumEpoch)
                {
                    minimumEpoch = currentEpoch;
                    attempts = 0;
                }
                else
                {
                    attempts++;

                    if (attempts == 100)
                        return;
                }
            }
        }
    }
}
