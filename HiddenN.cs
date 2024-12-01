using System;

namespace Backprop
{
	public class HNeuron
	{
		private double []weights;//there will be 10 for the input neurons
		private int idno;
		private int wsize;
		private double hactivation;
		private double bias;
		private double error;
		static System.Random rand;
		public HNeuron()
		{
			idno=0;
			hactivation=0.0;
			bias=this.randomweight();
			error=0.0;
			wsize=10;
			weights=new double[10];
			this.setRandomWeights(10);
		}
		public HNeuron(int idnodata,int size)
		{
			idno=idnodata;
			hactivation=0.0;
			bias=0.01;//this.randomweight();
			wsize=size;
			error=0.0;
			weights=new double[wsize];
			this.setRandomWeights(wsize);
		}
		public void calculateErr(double [] der)
		{
			// calculate the error on the input layer
			double result=0.0;
			for(int x=0;x<der.Length;x++)
			{
				result+=(der[x]*weights[x]);
			}
			//result*=hactivation;
			//result*=(1-hactivation);
			error=(result*hactivation*(1-hactivation));
		}
		public void setWeight(double lrpout,double [] der)
		{
			// change the weights of connected between input and 2 layer
			double temp=0.0;
			temp=hactivation*lrpout;
			for(int x=0;x<der.Length;x++)
			{
				weights[x]+=(temp*der[x]);
			}
		}
		public void setWeight(int pos,double dat)
		{
			weights[pos]=dat;
		}
		public double getWeight(int outid)
		{
			return weights[outid];
		}
		public void setErr(double x)
		{
			error=x;
		}
		public double getErr()
		{
			return error;
		}
		public void setHactivation(double data)
		{
			this.hactivation=data;
		}
		public double getHactivation()
		{
			return hactivation;
		}
		public void setIdno(int x)
		{
			idno=x;
		}
		public int getIdno()
		{
			return idno;
		}
		public void setBias(double b)
		{
			bias=b;
		}
		public double getBias()
		{
			return bias;
		}
		public void changeBias(double lrpin)
		{
			//change the bias of this neuron
			bias+=(error*lrpin);
		}
		public void setRandomWeights(int size)
		{
			for(int x=0;x<size;x++)
			{
				weights[x]=this.randomweight();
			}
		}
		private double randomweight()
		{
			/*int num;
			DateTime x=DateTime.Now;
			Random rnd=new Random((int)x.Millisecond);
			num=(int)(rnd.Next()%100.00);
			return 2*((float)(num/100.00));
			
			Random y=new Random();
			double x=(double)y.Next(-10,10);
			Console.WriteLine("at hidden {0} = {1}", this.idno,x);
			return x;*/
			if(rand == null)
			{
				rand = new System.Random();
			}
                
			int MaxLimit = + 1000; 
                
			int MinLimit = - 1000; 

			double number = (double) (rand.Next(MinLimit,MaxLimit))/2000;
                
			return number; 
		}
	}//end of class HNeuron




}
