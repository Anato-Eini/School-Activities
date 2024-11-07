/* CITU DIP METHODS REPOSITORY
COPYRIGHT INTELLIGENT SYSTEMS LAB.
CEBU INSTITUTE OF TECHNOLOGY

THIS SOFTWARE IS FREE AND SUBJECT TO THE GNU LICENSE AGREEMENT
WWW.GETGNU.ORG

CHRIS JORDAN ALIAC
CITBADROBOT@GMAIL.COM
*/

using System.Drawing.Imaging;

namespace ImageProcess2
{
	public class ConvMatrix
	{
		public int TopLeft = 0, TopMid = 0, TopRight = 0;
		public int MidLeft = 0, Pixel = 1, MidRight = 0;
		public int BottomLeft = 0, BottomMid = 0, BottomRight = 0;
		public int Factor = 1;
		public int Offset = 0;
		public void SetAll(int nVal)
		{
			TopLeft = TopMid = TopRight = MidLeft = Pixel = MidRight = BottomLeft = BottomMid = BottomRight = nVal;
		}
	}

	public struct FloatPoint
	{
		public double X;
		public double Y;
	}

	public class BitmapFilter
	{
		public const short EDGE_DETECT_KIRSH		= 1;
		public const short EDGE_DETECT_PREWITT		= 2;
		public const short EDGE_DETECT_SOBEL		= 3;

		public static bool Invert(Bitmap b)
		{
			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;

				int nOffset = stride - b.Width*3;
				int nWidth = b.Width * 3;
				int height = b.Height;
	
				for(int y = 0;y < height; ++y)
				{
					for(int x=0; x < nWidth; ++x )
					{
						p[0] = (byte)(255-p[0]);
						++p;
					}
					p += nOffset;
				}
			}

			b.UnlockBits(bmData);
			return true;
		}

		public static bool Binary(Bitmap src, Bitmap dst, int threshold)
		{

			if (threshold < 0 || threshold > 255)
				return false;

			int dstHeight = dst.Height;
			int dstWidth = dst.Width;
			int srcHeight = src.Height;
			int srcWidth = src.Width;

            BitmapData bmLoaded = src.LockBits(
                new Rectangle(0, 0, srcWidth, srcHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                );

            BitmapData bmProcessed = dst.LockBits(
                new Rectangle(0, 0, dstWidth, dstHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                );

            unsafe
            {
                int paddingProcessed = bmProcessed.Stride - dstWidth * 3;
                int paddingLoaded = bmLoaded.Stride - srcWidth * 3;

                byte* pProcessed = (byte*)bmProcessed.Scan0;
                byte* pLoaded = (byte*)bmLoaded.Scan0;

                for (int i = 0;
                    i < srcHeight;
                    i++, pProcessed += paddingProcessed, pLoaded += paddingLoaded)

                    for (int j = 0;
                        j < srcWidth;
                        j++, pProcessed += 3, pLoaded += 3)
                        pProcessed[0] = pProcessed[1] = pProcessed[2] = (byte)(
                            (pLoaded[0] + pLoaded[1] + pLoaded[2]) / 3 < threshold ? 0 : 255);
            }

            src.UnlockBits(bmLoaded);
            dst.UnlockBits(bmProcessed);

			return true;
		}

		public static void Copy(Bitmap src, Bitmap dst)
		{
            int srcHeight = src.Height;
            int srcWidth = src.Width;

            BitmapData bmLoaded = src.LockBits(
                new Rectangle(0, 0, srcWidth, srcHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

            BitmapData bmProcessed = dst.LockBits(
                new Rectangle(0, 0, dst.Width, dst.Height),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

            unsafe
            {
                int offSet = bmLoaded.Stride - srcWidth * 3; // Padding value

                byte* pLoaded = (byte*)bmLoaded.Scan0;
                byte* pProcessed = (byte*)bmProcessed.Scan0;

                for (int i = 0; i < srcHeight; i++)
                {
                    for (int j = 0; j < srcWidth; j++)
                    {
                        pProcessed[0] = pLoaded[0];
                        pProcessed[1] = pLoaded[1];
                        pProcessed[2] = pLoaded[2];

                        pLoaded += 3;
                        pProcessed += 3;
                    }

                    pLoaded += offSet;
                    pProcessed += offSet;

                }
            }

            src.UnlockBits(bmLoaded);
            dst.UnlockBits(bmProcessed);
		}


		public static void Scale(Bitmap src, Bitmap dst)
		{
            int dstHeight = dst.Height;
            int dstWidth = dst.Width;
            int srcHeight = src.Height;
            int srcWidth = src.Width;

            BitmapData bmLoaded = src.LockBits(
                new Rectangle(0, 0, srcWidth, srcHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                );

            BitmapData bmProcessed = dst.LockBits(
                new Rectangle(0, 0, dstWidth, dstHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                );

            unsafe
            {
                int paddingProcessed = bmProcessed.Stride - dstWidth * 3;
				int loadedStride = bmLoaded.Stride;

                byte* pProcessed = (byte*)bmProcessed.Scan0;
                byte* startLoaded = (byte*)bmLoaded.Scan0;

                for (int i = 0;
                    i < dstHeight;
                    i++, pProcessed += paddingProcessed)
                {
                    for (int j = 0;
                        j < dstWidth;
                        j++, pProcessed += 3)
                    {
                        byte* pTarget = (startLoaded + i * srcHeight / dstHeight * loadedStride)
                            + j * srcWidth / dstWidth * 3;

                        pProcessed[0] = pTarget[0];
                        pProcessed[1] = pTarget[1];
                        pProcessed[2] = pTarget[2];
                    }
                }
            }

            src.UnlockBits(bmLoaded);
            dst.UnlockBits(bmProcessed);
		}

		public static void Rotate(Bitmap src, Bitmap dst, int degree)
		{
			int srcHeight = src.Height;
			int srcWidth = src.Width;
			int dstHeight = dst.Height;
			int dstWidth = dst.Width;

            BitmapData bmLoaded = src.LockBits(
                new Rectangle(0, 0, srcWidth, srcHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                );

            BitmapData bmProcessed = dst.LockBits(
                new Rectangle(0, 0, dstWidth, dstHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                );

            float radians = degree * (float)Math.PI / 180f;
            int centerX = srcWidth / 2;
            int centerY = srcHeight / 2;
            float cosA = (float)Math.Cos(radians);
            float sinA = (float)Math.Sin(radians);

            unsafe
            {
                int paddingProcessed = bmProcessed.Stride - dstWidth * 3;
				int srcStride = bmLoaded.Stride;

                byte* pProcessed = (byte*)bmProcessed.Scan0;
                byte* startLoaded = (byte*)bmLoaded.Scan0;

                for (int i = 0;
                    i < srcHeight;
                    i++, pProcessed += paddingProcessed)
                {
                    for (int j = 0;
                        j < srcWidth;
                        j++, pProcessed += 3)
                    {
                        int translatedX = j - centerX;
                        int translatedY = i - centerY;

                        int newX = (int)(translatedX * cosA - translatedY * sinA) + centerX;
                        int newY = (int)(translatedX * sinA + translatedY * cosA) + centerY;

                        if ( newX >= 0 && newX < srcWidth && newY >= 0 && newY < srcHeight )
                        {
                            byte* pTarget = (startLoaded + newY * srcStride) + newX * 3;
                            pProcessed[0] = pTarget[0];
                            pProcessed[1] = pTarget[1];
                            pProcessed[2] = pTarget[2];
                        }                    
                    }
                }
            }

            src.UnlockBits(bmLoaded);
            dst.UnlockBits(bmProcessed);
		}

		public static void Subtract(Bitmap foreground, Bitmap background, Bitmap result)
		{
			int foregroundHeight = foreground.Height;
			int foregroundWidth = foreground.Width;
			int backgroundHeight = background.Height;
			int backgroundWidth = background.Width;
			int resultHeight = result.Height;
			int resultWidth = result.Width;

            BitmapData bmLoaded = foreground.LockBits(
                new Rectangle(0, 0, foregroundWidth, foregroundHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                );

            BitmapData bmProcessed = background.LockBits(
                new Rectangle(0, 0, backgroundWidth, backgroundHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                );

            BitmapData bmSubtracted = result.LockBits(
                new Rectangle(0, 0, resultWidth, resultHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb
                );

            int limitAve = 255 / 3;
            int threshold = 5;

            unsafe
            {
                int paddingLoaded = bmLoaded.Stride - foregroundWidth * 3;
                int paddingProcessed = bmProcessed.Stride - backgroundWidth * 3;
                int paddingSubtracted = bmSubtracted.Stride - resultWidth * 3;

				int processedStride = bmProcessed.Stride;

                byte* pLoaded = (byte*)bmLoaded.Scan0;
                byte* pProcessed = (byte*)bmProcessed.Scan0;
                byte* pSubtracted = (byte*)bmSubtracted.Scan0;

                byte* start_p_processed = (byte*)bmProcessed.Scan0;

				int backgroundLimitHeight = background.Height - 1;
				int backgroundLimitWidth = background.Width - 1;

                for (int i = 0;
                    i < foregroundHeight;
                    i++, pLoaded += paddingLoaded, pSubtracted += paddingSubtracted)
                {
                    for (int j = 0;
                        j < foregroundWidth;
                        j++, pLoaded += 3, pSubtracted += 3)
                    {
                        if (Math.Abs(((pLoaded[0] + pLoaded[1] + pLoaded[2]) / 3) - limitAve) < threshold)
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

                        if (j < backgroundLimitWidth)
                            pProcessed += 3;
                    }

                    if (i < backgroundLimitHeight)
                        pProcessed = start_p_processed + i * processedStride;
                }
            }

            foreground.UnlockBits(bmLoaded);
            background.UnlockBits(bmProcessed);
            result.UnlockBits(bmSubtracted);
		}

		public static void Sepia(Bitmap dst)
		{

			int dstHeight = dst.Height;
			int dstWidth = dst.Width;

            BitmapData bmProcessed = dst.LockBits(
                new Rectangle(0, 0, dstWidth, dstHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

            int offSet = bmProcessed.Stride - dstWidth * 3;

            unsafe
            {
                byte* p = (byte*)(void*)bmProcessed.Scan0;

                for (int i = 0; i < dstHeight; i++)
                {
                    for (int j = 0; j < dstWidth; j++)
                    {
                        p[0] = (byte)Math.Min(255, p[2] * 0.272 + p[1] * 0.534 + p[0] * 0.131);
                        p[1] = (byte)Math.Min(255, p[2] * 0.349 + p[1] * 0.686 + p[0] * 0.168);
                        p[2] = (byte)Math.Min(255, p[2] * 0.393 + p[1] * 0.769 + p[0] * 0.189);

                        p += 3;
                    }

                    p += offSet;
                }
            }

            dst.UnlockBits(bmProcessed);
		}

		public static void Histogram(Bitmap src, Bitmap dst)
		{
			int srcHeight = src.Height;
			int srcWidth = src.Width;
			int dstHeight = dst.Height;
			int dstWidth = dst.Width;

            BitmapData bmLoaded = src.LockBits(
                new Rectangle(0, 0, srcWidth, srcHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

            BitmapData bmProcessed = dst.LockBits(
                new Rectangle(0, 0, dstWidth, dstHeight),
                ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

            unsafe
            {
                int[] histData = new int[256];
                int maxFreq = 420;
                int offSet = bmLoaded.Stride - srcWidth * 3; // Padding value

                byte* p = (byte*)(void*)bmLoaded.Scan0;

                for (int i = 0; i < srcHeight; i++)
                {
                    for (int j = 0; j < srcWidth; j++)
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

                int lastRow = (dst.Height - 1) * bmProcessed.Stride;
                byte* pointerLast = (byte*)(void*)bmProcessed.Scan0 + lastRow;

				int processedStride = bmProcessed.Stride;

                for (int i = 0; i < 256; i++)
                {
                    p = pointerLast + i * 3;
                    count = Math.Min(420, histData[i] / mFactor);

                    for (int j = 0; j < count; j++)
                    {
                        p[0] = p[1] = p[2] = 255;

						p -= processedStride;
                    }
                }
            }

            src.UnlockBits(bmLoaded);
            dst.UnlockBits(bmProcessed);
		}

		public static bool GrayScale(Bitmap b)
		{
			int bmHeight = b.Height;
			int bmWidth = b.Width;

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, bmWidth, bmHeight), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;

				int nOffset = stride - b.Width*3;

				for(int y = 0; y < bmHeight; ++y)
				{
					for(int x = 0; x < bmWidth; ++x )
					{
						p[0] = p[1] = p[2] = (byte)(.299 * p[2] + .587 * p[1] + .114 * p[0]);

						p += 3;
					}

					p += nOffset;
				}
			}

			b.UnlockBits(bmData);

			return true;
		}

		public static bool Brightness(Bitmap b, int nBrightness)
		{
			if (nBrightness < -255 || nBrightness > 255)
				return false;

			int bmHeight = b.Height;
			int bmWidth = b.Width;

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, bmWidth, bmHeight), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;

			int nVal = 0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;

				int nOffset = stride - bmWidth*3;
				int nWidth = bmWidth * 3;

				for(int y = 0; y < bmHeight; ++y)
				{
					for(int x = 0; x < nWidth; ++x )
					{
						nVal = p[0] + nBrightness;
		
						if (nVal < 0) nVal = 0;
						if (nVal > 255) nVal = 255;

						p[0] = (byte)nVal;

						++p;
					}
					p += nOffset;
				}
			}

			b.UnlockBits(bmData);

			return true;
		}

		public static bool Contrast(Bitmap b, sbyte nContrast)
		{
			if (nContrast < -100 || nContrast > 100) return false;

			int bmHeight = b.Height;
			int bmWidth = b.Width;

			double pixel = 0, contrast = (100.0+nContrast)/100.0;

			contrast *= contrast;

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, bmWidth, bmHeight), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;

				int nOffset = stride - bmWidth *3;

				for(int y = 0 ; y < bmHeight; ++y)
				{
					for(int x = 0; x < bmWidth; ++x)
					{
						pixel = p[2] /255.0;
						pixel -= 0.5;
						pixel *= contrast;
						pixel += 0.5;
						pixel *= 255;
						if (pixel < 0) pixel = 0;
						if (pixel > 255) pixel = 255;
						p[2] = (byte) pixel;

						pixel = p[1] /255.0;
						pixel -= 0.5;
						pixel *= contrast;
						pixel += 0.5;
						pixel *= 255;
						if (pixel < 0) pixel = 0;
						if (pixel > 255) pixel = 255;
						p[1] = (byte) pixel;

						pixel = p[0] /255.0;
						pixel -= 0.5;
						pixel *= contrast;
						pixel += 0.5;
						pixel *= 255;
						if (pixel < 0) pixel = 0;
						if (pixel > 255) pixel = 255;
						p[0] = (byte) pixel;					

						p += 3;
					}
					p += nOffset;
				}
			}

			b.UnlockBits(bmData);

			return true;
		}
	
		public static bool Gamma(Bitmap b, double red, double green, double blue)
		{
			if (red < .2 || red > 5) return false;
			if (green < .2 || green > 5) return false;
			if (blue < .2 || blue > 5) return false;

			byte [] redGamma = new byte [256];
			byte [] greenGamma = new byte [256];
			byte [] blueGamma = new byte [256];

			for (int i = 0; i< 256; ++i)
			{
				redGamma[i] = (byte)Math.Min(255, (int)(( 255.0 * Math.Pow(i/255.0, 1.0/red)) + 0.5));
				greenGamma[i] = (byte)Math.Min(255, (int)(( 255.0 * Math.Pow(i/255.0, 1.0/green)) + 0.5));
				blueGamma[i] = (byte)Math.Min(255, (int)(( 255.0 * Math.Pow(i/255.0, 1.0/blue)) + 0.5));
			}

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;

				int nOffset = stride - b.Width*3;

				for(int y=0;y<b.Height;++y)
				{
					for(int x=0; x < b.Width; ++x )
					{
						p[2] = redGamma[ p[2] ];
						p[1] = greenGamma[ p[1] ];
						p[0] = blueGamma[ p[0] ];

						p += 3;
					}
					p += nOffset;
				}
			}

			b.UnlockBits(bmData);

			return true;
		}

		public static bool Color(Bitmap b, int red, int green, int blue)
		{
			if (red < -255 || red > 255) return false;
			if (green < -255 || green > 255) return false;
			if (blue < -255 || blue > 255) return false;

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;

				int nOffset = stride - b.Width*3;
				int nPixel;

				for(int y=0;y<b.Height;++y)
				{
					for(int x=0; x < b.Width; ++x )
					{
						nPixel = p[2] + red;
						nPixel = Math.Max(nPixel, 0);
						p[2] = (byte)Math.Min(255, nPixel);

						nPixel = p[1] + green;
						nPixel = Math.Max(nPixel, 0);
						p[1] = (byte)Math.Min(255, nPixel);

						nPixel = p[0] + blue;
						nPixel = Math.Max(nPixel, 0);
						p[0] = (byte)Math.Min(255, nPixel);

						p += 3;
					}
					p += nOffset;
				}
			}

			b.UnlockBits(bmData);

			return true;
		}

		public static bool Conv3x3(Bitmap b, ConvMatrix m)
		{
			// Avoid divide by zero errors
			if (0 == m.Factor) return false;

			Bitmap bSrc = (Bitmap)b.Clone(); 

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmSrc = bSrc.LockBits(new Rectangle(0, 0, bSrc.Width, bSrc.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			int stride2 = stride * 2;
			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr SrcScan0 = bmSrc.Scan0;

			int topLeft = m.TopLeft;
			int topMid = m.TopMid;
			int topRight = m.TopRight;
			int midLeft = m.MidLeft;
			int midRight = m.MidRight;
			int pixel = m.Pixel;
			int bottomLeft = m.BottomLeft;
			int bottomMid = m.BottomMid;
			int bottomRight = m.BottomRight;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * pSrc = (byte *)(void *)SrcScan0;

				int nOffset = stride - b.Width*3;
				int nWidth = b.Width - 2;
				int nHeight = b.Height - 2;

				int nPixel;

				for(int y=0;y < nHeight;++y)
				{
					for(int x=0; x < nWidth; ++x )
					{
						nPixel = ( ( ( (pSrc[2] * topLeft) + (pSrc[5] * topMid) + (pSrc[8] * topRight) +
							(pSrc[2 + stride] * midLeft) + (pSrc[5 + stride] * pixel) + (pSrc[8 + stride] * midRight) +
							(pSrc[2 + stride2] * bottomLeft) + (pSrc[5 + stride2] * bottomMid) + (pSrc[8 + stride2] * bottomRight)) / m.Factor) + m.Offset); 

						if (nPixel < 0) nPixel = 0;
						if (nPixel > 255) nPixel = 255;

						p[5 + stride]= (byte)nPixel;

						nPixel = ( ( ( (pSrc[1] * topLeft) + (pSrc[4] * topMid) + (pSrc[7] * topRight) +
							(pSrc[1 + stride] * midLeft) + (pSrc[4 + stride] * pixel) + (pSrc[7 + stride] * midRight) +
							(pSrc[1 + stride2] * bottomLeft) + (pSrc[4 + stride2] * bottomMid) + (pSrc[7 + stride2] * bottomRight)) / m.Factor) + m.Offset); 

						if (nPixel < 0) nPixel = 0;
						if (nPixel > 255) nPixel = 255;
							
						p[4 + stride] = (byte)nPixel;

						nPixel = ( ( ( (pSrc[0] * topLeft) + (pSrc[3] * topMid) + (pSrc[6] * topRight) +
							(pSrc[0 + stride] * midLeft) + (pSrc[3 + stride] * pixel) + (pSrc[6 + stride] * midRight) +
							(pSrc[0 + stride2] * bottomLeft) + (pSrc[3 + stride2] * bottomMid) + (pSrc[6 + stride2] * bottomRight)) / m.Factor) + m.Offset); 

						if (nPixel < 0) nPixel = 0;
						if (nPixel > 255) nPixel = 255;

						p[3 + stride] = (byte)nPixel;

						p += 3;
						pSrc += 3;
					}
					p += nOffset;
					pSrc += nOffset;
				}
			}

			b.UnlockBits(bmData);
			bSrc.UnlockBits(bmSrc);

			return true;
		}

		public static bool Smooth(Bitmap b, int nWeight = 1)
		{
			ConvMatrix m = new ConvMatrix();
			m.SetAll(1);
			m.Pixel = nWeight;
			m.Factor = nWeight + 8;

			return  BitmapFilter.Conv3x3(b, m);
		}

		public static bool GaussianBlur(Bitmap b, int nWeight = 4)
		{
			ConvMatrix m = new ConvMatrix();
			m.SetAll(1);
			m.Pixel = nWeight;
			m.TopMid = m.MidLeft = m.MidRight = m.BottomMid = 2;
			m.Factor = nWeight + 12;

			return  BitmapFilter.Conv3x3(b, m);
		}
		public static bool? MeanRemoval(Bitmap b, int nWeight = 9)
		{
			ConvMatrix m = new ConvMatrix();
			m.SetAll(-1);
			m.Pixel = nWeight;
			m.Factor = nWeight - 8;

			return BitmapFilter.Conv3x3(b, m);
		}
		public static bool Sharpen(Bitmap b, int nWeight = 11)
		{
			ConvMatrix m = new ConvMatrix();
			m.SetAll(0);
			m.Pixel = nWeight;
			m.TopMid = m.MidLeft = m.MidRight = m.BottomMid = -2;
			m.Factor = nWeight - 8;

			return  BitmapFilter.Conv3x3(b, m);
		}
		public static bool EmbossLaplacian(Bitmap b)
		{
			ConvMatrix m = new ConvMatrix();
			m.SetAll(-1);
			m.TopMid = m.MidLeft = m.MidRight = m.BottomMid = 0;
			m.Pixel = 4;
			m.Offset = 127;

			return  BitmapFilter.Conv3x3(b, m);
		}	

		public static bool EmbossHorzVertical(Bitmap b)
		{
			ConvMatrix m = new ConvMatrix();
			m.TopLeft = m.TopRight = m.BottomLeft = m.BottomRight = 0;
			m.TopMid = m.MidLeft = m.MidRight = -1;
			m.Pixel = 4;
			m.Offset = 127;

			return BitmapFilter.Conv3x3(b, m);
		}

		public static bool EmbossAllDirections(Bitmap b)
		{
			ConvMatrix m = new();
			m.SetAll(-1);
			m.Pixel = 8;
			m.Offset = 127;

			return BitmapFilter.Conv3x3(b, m);
		}

		public static bool EmbossLossy(Bitmap b)
		{
			ConvMatrix m = new();
			m.TopLeft = m.TopRight = m.BottomMid = 1;
			m.TopMid = m.MidLeft = m.MidRight = m.BottomLeft = m.BottomRight = -2;
			m.Pixel = 4;
			m.Offset = 127;

			return BitmapFilter.Conv3x3(b, m);
		}

		public static bool EmbossHorizontal(Bitmap b) 
		{ 
			ConvMatrix m = new();
			m.SetAll(0);
			m.MidLeft = m.MidRight = -1;
			m.Pixel = 2;
			m.Offset = 127;

			return BitmapFilter.Conv3x3(b, m);
		}

		public static bool EmbossVertical(Bitmap b)
		{
			ConvMatrix m = new();
			m.SetAll(0);
			m.TopMid = -1;
			m.BottomMid = 1;
			m.Offset = 127;

			return BitmapFilter.Conv3x3(b, m);
		}

		public static bool EdgeDetectQuick(Bitmap b)
		{
			ConvMatrix m = new ConvMatrix();
			m.TopLeft = m.TopMid = m.TopRight = -1;
			m.MidLeft = m.Pixel = m.MidRight = 0;
			m.BottomLeft = m.BottomMid = m.BottomRight = 1;
		
			m.Offset = 127;

			return  BitmapFilter.Conv3x3(b, m);
		}

		public static bool EdgeDetectConvolution(Bitmap b, short nType, byte nThreshold)
		{
			ConvMatrix m = new ConvMatrix();

			// I need to make a copy of this bitmap BEFORE I alter it 80)
			Bitmap bTemp = (Bitmap)b.Clone();

			switch (nType)
			{
				case EDGE_DETECT_SOBEL:
					m.SetAll(0);
					m.TopLeft = m.BottomLeft = 1;
					m.TopRight = m.BottomRight = -1;
					m.MidLeft = 2;
					m.MidRight = -2;
					m.Offset = 0;
					break;
				case EDGE_DETECT_PREWITT:
					m.SetAll(0);
					m.TopLeft = m.MidLeft = m.BottomLeft = -1;
					m.TopRight = m.MidRight = m.BottomRight = 1;
					m.Offset = 0;
					break;
				case EDGE_DETECT_KIRSH:
					m.SetAll(-3);
					m.Pixel = 0;
					m.TopLeft = m.MidLeft = m.BottomLeft = 5;
					m.Offset = 0;
					break;
			}

			BitmapFilter.Conv3x3(b, m);

			switch (nType)
			{
				case EDGE_DETECT_SOBEL:
					m.SetAll(0);
					m.TopLeft = m.TopRight = 1;
					m.BottomLeft = m.BottomRight = -1;
					m.TopMid = 2;
					m.BottomMid = -2;
					m.Offset = 0;
					break;
				case EDGE_DETECT_PREWITT:
					m.SetAll(0);
					m.BottomLeft = m.BottomMid = m.BottomRight = -1;
					m.TopLeft = m.TopMid = m.TopRight = 1;
					m.Offset = 0;
					break;
				case EDGE_DETECT_KIRSH:
					m.SetAll(-3);
					m.Pixel = 0;
					m.BottomLeft = m.BottomMid = m.BottomRight = 5;
					m.Offset = 0;
					break;
			}

			BitmapFilter.Conv3x3(bTemp, m);

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmData2 = bTemp.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr Scan02 = bmData2.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * p2 = (byte *)(void *)Scan02;

				int nOffset = stride - b.Width*3;
				int nWidth = b.Width * 3;

				int nPixel = 0;
	
				for(int y=0;y<b.Height;++y)
				{
					for(int x=0; x < nWidth; ++x )
					{
						nPixel = (int) Math.Sqrt((p[0]*p[0]) + (p2[0] * p2[0]));
						if (nPixel<nThreshold)nPixel = nThreshold;
						if (nPixel>255) nPixel = 255;
						p[0] = (byte) nPixel;
						++p;
						++p2;
					}
					p += nOffset;
					p2 += nOffset;
				}
			}

			b.UnlockBits(bmData);
			bTemp.UnlockBits(bmData2);

			return true;
		}
	
		public static bool EdgeDetectHorizontal(Bitmap b)
		{
			Bitmap bmTemp = (Bitmap)b.Clone();

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmData2 = bmTemp.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr Scan02 = bmData2.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * p2 = (byte *)(void *)Scan02;

				int nOffset = stride - b.Width*3;
				int nWidth = b.Width * 3;

				int nPixel = 0;
	
				p += stride;
				p2 += stride;

				for(int y=1;y<b.Height-1;++y)
				{
					p += 9;
					p2 += 9;

					for(int x=9; x < nWidth-9; ++x )
					{
						nPixel = ((p2 + stride - 9)[0] +
							(p2 + stride - 6)[0] +
							(p2 + stride - 3)[0] +
							(p2 + stride)[0] +
							(p2 + stride + 3)[0] +
							(p2 + stride + 6)[0] +
							(p2 + stride + 9)[0] -
							(p2 - stride - 9)[0] -
							(p2 - stride - 6)[0] -
							(p2 - stride - 3)[0] -
							(p2 - stride)[0] -
							(p2 - stride + 3)[0] -
							(p2 - stride + 6)[0] -
							(p2 - stride + 9)[0]);

						if (nPixel < 0) nPixel = 0;
						if (nPixel > 255) nPixel = 255;

						(p+stride)[0] = (byte) nPixel;
					
						++ p;
						++ p2;
					}

					p += 9 + nOffset;
					p2 += 9 + nOffset;
				}
			}

			b.UnlockBits(bmData);
			bmTemp.UnlockBits(bmData2);

			return true;
		}

		public static bool EdgeDetectVertical(Bitmap b)
		{
			Bitmap bmTemp = (Bitmap)b.Clone();

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmData2 = bmTemp.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr Scan02 = bmData2.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * p2 = (byte *)(void *)Scan02;

				int nOffset = stride - b.Width*3;
				int nWidth = b.Width * 3;

				int nPixel = 0;

				int nStride2 = stride *2;
				int nStride3 = stride * 3;
	
				p += nStride3;
				p2 += nStride3;

				for(int y=3;y<b.Height-3;++y)
				{
					p += 3;
					p2 += 3;

					for(int x=3; x < nWidth-3; ++x )
					{
						nPixel = ((p2 + nStride3 + 3)[0] +
							(p2 + nStride2 + 3)[0] +
							(p2 + stride + 3)[0] +
							(p2 + 3)[0] +
							(p2 - stride + 3)[0] +
							(p2 - nStride2 + 3)[0] +
							(p2 - nStride3 + 3)[0] -
							(p2 + nStride3 - 3)[0] -
							(p2 + nStride2 - 3)[0] -
							(p2 + stride - 3)[0] -
							(p2 - 3)[0] -
							(p2 - stride - 3)[0] -
							(p2 - nStride2 - 3)[0] -
							(p2 - nStride3 - 3)[0]);

						if (nPixel < 0) nPixel = 0;
						if (nPixel > 255) nPixel = 255;

						p[0] = (byte) nPixel;
					
						++ p;
						++ p2;
					}

					p += 3 + nOffset;
					p2 += 3 + nOffset;
				}
			}

			b.UnlockBits(bmData);
			bmTemp.UnlockBits(bmData2);

			return true;
		}

		public static bool EdgeDetectHomogenity(Bitmap b, byte nThreshold)
		{
			// This one works by working out the greatest difference between a pixel and it's eight neighbours.
			// The threshold allows softer edges to be forced down to black, use 0 to negate it's effect.
			Bitmap b2 = (Bitmap) b.Clone();

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmData2 = b2.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr Scan02 = bmData2.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * p2 = (byte *)(void *)Scan02;

				int nOffset = stride - b.Width*3;
				int nWidth = b.Width * 3;

				int nPixel = 0, nPixelMax = 0;

				p += stride;
				p2 += stride;

				for(int y=1;y<b.Height-1;++y)
				{
					p += 3;
					p2 += 3;

					for(int x=3; x < nWidth-3; ++x )
					{
						nPixelMax = Math.Abs(p2[0] - (p2+stride-3)[0]);
						nPixel = Math.Abs(p2[0] - (p2 + stride)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs(p2[0] - (p2 + stride + 3)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs(p2[0] - (p2 - stride)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs(p2[0] - (p2 + stride)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs(p2[0] - (p2 - stride - 3)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs(p2[0] - (p2 - stride)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs(p2[0] - (p2 - stride + 3)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						if (nPixelMax < nThreshold) nPixelMax = 0;

						p[0] = (byte) nPixelMax;

						++ p;
						++ p2;
					}

					p += 3 + nOffset;
					p2 += 3 + nOffset;
				}
			}

			b.UnlockBits(bmData);
			b2.UnlockBits(bmData2);

			return true;
            
		}
		public static bool EdgeDetectDifference(Bitmap b, byte nThreshold)
		{
			// This one works by working out the greatest difference between a pixel and it's eight neighbours.
			// The threshold allows softer edges to be forced down to black, use 0 to negate it's effect.
			Bitmap b2 = (Bitmap) b.Clone();

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmData2 = b2.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr Scan02 = bmData2.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * p2 = (byte *)(void *)Scan02;

				int nOffset = stride - b.Width*3;
				int nWidth = b.Width * 3;

				int nPixel = 0, nPixelMax = 0;

				p += stride;
				p2 += stride;

				for(int y=1;y<b.Height-1;++y)
				{
					p += 3;
					p2 += 3;

					for(int x=3; x < nWidth-3; ++x )
					{
						nPixelMax = Math.Abs((p2 - stride + 3)[0] - (p2+stride-3)[0]);
						nPixel = Math.Abs((p2 + stride + 3)[0] - (p2 - stride - 3)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs((p2 - stride)[0] - (p2 + stride)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs((p2+3)[0] - (p2 - 3)[0]);
						if (nPixel>nPixelMax) nPixelMax = nPixel;

						if (nPixelMax < nThreshold) nPixelMax = 0;

						p[0] = (byte) nPixelMax;

						++ p;
						++ p2;
					}

					p += 3 + nOffset;
					p2 += 3 + nOffset;
				}
			}

			b.UnlockBits(bmData);
			b2.UnlockBits(bmData2);

			return true;
            
		}

		public static bool EdgeEnhance(Bitmap b, byte nThreshold)
		{
			// This one works by working out the greatest difference between a nPixel and it's eight neighbours.
			// The threshold allows softer edges to be forced down to black, use 0 to negate it's effect.
			Bitmap b2 = (Bitmap) b.Clone();

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmData2 = b2.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int stride = bmData.Stride;
			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr Scan02 = bmData2.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * p2 = (byte *)(void *)Scan02;

				int nOffset = stride - b.Width*3;
				int nWidth = b.Width * 3;

				int nPixel = 0, nPixelMax = 0;

				p += stride;
				p2 += stride;

				for (int y = 1; y < b.Height-1; ++y)
				{
					p += 3;
					p2 += 3;

					for (int x = 3; x < nWidth-3; ++x)
					{
						nPixelMax = Math.Abs((p2 - stride + 3)[0] - (p2 + stride - 3)[0]);

						nPixel = Math.Abs((p2 + stride + 3)[0] - (p2 - stride - 3)[0]);

						if (nPixel > nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs((p2 - stride)[0] - (p2 + stride)[0]);

						if (nPixel > nPixelMax) nPixelMax = nPixel;

						nPixel = Math.Abs((p2 + 3)[0] - (p2 - 3)[0]);

						if (nPixel > nPixelMax) nPixelMax = nPixel;

						if (nPixelMax > nThreshold && nPixelMax > p[0])
							p[0] = (byte) Math.Max(p[0], nPixelMax);

						++ p;
						++ p2;			
					}

					p += nOffset + 3;
					p2 += nOffset + 3;
				}
			}	

			b.UnlockBits(bmData);
			b2.UnlockBits(bmData2);

			return true;
		}
		public static Bitmap Resize(Bitmap b, int nWidth, int nHeight, bool bBilinear)
		{
			Bitmap bTemp = (Bitmap)b.Clone();
			b = new Bitmap(nWidth, nHeight, bTemp.PixelFormat);

			double nXFactor = (double)bTemp.Width/(double)nWidth;
			double nYFactor = (double)bTemp.Height/(double)nHeight;

			if (bBilinear)
			{
				double fraction_x, fraction_y, one_minus_x, one_minus_y;
				int ceil_x, ceil_y, floor_x, floor_y;
				Color c1 = new Color();
				Color c2 = new Color();
				Color c3 = new Color();
				Color c4 = new Color();
				byte red, green, blue;

				byte b1, b2;

				for (int x = 0; x < b.Width; ++x)
					for (int y = 0; y < b.Height; ++y)
					{
						// Setup

						floor_x = (int)Math.Floor(x * nXFactor);
						floor_y = (int)Math.Floor(y * nYFactor);
						ceil_x = floor_x + 1;
						if (ceil_x >= bTemp.Width) ceil_x = floor_x;
						ceil_y = floor_y + 1;
						if (ceil_y >= bTemp.Height) ceil_y = floor_y;
						fraction_x = x * nXFactor - floor_x;
						fraction_y = y * nYFactor - floor_y;
						one_minus_x = 1.0 - fraction_x;
						one_minus_y = 1.0 - fraction_y;

						c1 = bTemp.GetPixel(floor_x, floor_y);
						c2 = bTemp.GetPixel(ceil_x, floor_y);
						c3 = bTemp.GetPixel(floor_x, ceil_y);
						c4 = bTemp.GetPixel(ceil_x, ceil_y);

						// Blue
						b1 = (byte)(one_minus_x * c1.B + fraction_x * c2.B);

						b2 = (byte)(one_minus_x * c3.B + fraction_x * c4.B);
						
						blue = (byte)(one_minus_y * (double)(b1) + fraction_y * (double)(b2));

						// Green
						b1 = (byte)(one_minus_x * c1.G + fraction_x * c2.G);

						b2 = (byte)(one_minus_x * c3.G + fraction_x * c4.G);
						
						green = (byte)(one_minus_y * (double)(b1) + fraction_y * (double)(b2));

						// Red
						b1 = (byte)(one_minus_x * c1.R + fraction_x * c2.R);

						b2 = (byte)(one_minus_x * c3.R + fraction_x * c4.R);
						
						red = (byte)(one_minus_y * (double)(b1) + fraction_y * (double)(b2));

						b.SetPixel(x,y, System.Drawing.Color.FromArgb(255, red, green, blue));
					}
			}
			else
			{
				for (int x = 0; x < b.Width; ++x)
					for (int y = 0; y < b.Height; ++y)
						b.SetPixel(x, y, bTemp.GetPixel((int)(Math.Floor(x * nXFactor)),(int)(Math.Floor(y * nYFactor))));
			}

			return b;
		}

		public static bool OffsetFilterAbs(Bitmap b, Point[,] offset )
		{
			Bitmap bSrc = (Bitmap)b.Clone(); 

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmSrc = bSrc.LockBits(new Rectangle(0, 0, bSrc.Width, bSrc.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int scanline = bmData.Stride;

			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr SrcScan0 = bmSrc.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * pSrc = (byte *)(void *)SrcScan0;

				int nOffset = bmData.Stride - b.Width*3;
				int nWidth = b.Width;
				int nHeight = b.Height;

				int xOffset, yOffset;

				for(int y=0;y < nHeight;++y)
				{
					for(int x=0; x < nWidth; ++x )
					{	
						xOffset = offset[x,y].X;
						yOffset = offset[x,y].Y;
				
						if (yOffset >= 0 && yOffset < nHeight && xOffset >= 0 && xOffset < nWidth)
						{
							p[0] = pSrc[(yOffset * scanline) + (xOffset * 3)];
							p[1] = pSrc[(yOffset * scanline) + (xOffset * 3) + 1];
							p[2] = pSrc[(yOffset * scanline) + (xOffset * 3) + 2];
						}

						p += 3;
					}
					p += nOffset;
				}
			}

			b.UnlockBits(bmData);
			bSrc.UnlockBits(bmSrc);

			return true;
		}

		public static bool OffsetFilter(Bitmap b, Point[,] offset )
		{
			Bitmap bSrc = (Bitmap)b.Clone(); 

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmSrc = bSrc.LockBits(new Rectangle(0, 0, bSrc.Width, bSrc.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int scanline = bmData.Stride;

			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr SrcScan0 = bmSrc.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * pSrc = (byte *)(void *)SrcScan0;

				int nOffset = bmData.Stride - b.Width*3;
				int nWidth = b.Width;
				int nHeight = b.Height;

				int xOffset, yOffset;

				for(int y=0;y < nHeight;++y)
				{
					for(int x=0; x < nWidth; ++x )
					{	
						xOffset = offset[x,y].X;
						yOffset = offset[x,y].Y;

						if (y+yOffset >= 0 && y+yOffset < nHeight && x+xOffset >= 0 && x+xOffset < nWidth)
						{
							p[0] = pSrc[((y+yOffset) * scanline) + ((x+xOffset) * 3)];
							p[1] = pSrc[((y+yOffset) * scanline) + ((x+xOffset) * 3) + 1];
							p[2] = pSrc[((y+yOffset) * scanline) + ((x+xOffset) * 3) + 2];
						}
						p += 3;
					}
					p += nOffset;
				}
			}

			b.UnlockBits(bmData);
			bSrc.UnlockBits(bmSrc);

			return true;
		}

		public static bool OffsetFilterAntiAlias(Bitmap b, FloatPoint[,] fp)
		{
			Bitmap bSrc = (Bitmap)b.Clone(); 

			// GDI+ still lies to us - the return format is BGR, NOT RGB.
			BitmapData bmData = b.LockBits(new Rectangle(0, 0, b.Width, b.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);
			BitmapData bmSrc = bSrc.LockBits(new Rectangle(0, 0, bSrc.Width, bSrc.Height), ImageLockMode.ReadWrite, PixelFormat.Format24bppRgb);

			int scanline = bmData.Stride;

			System.IntPtr Scan0 = bmData.Scan0;
			System.IntPtr SrcScan0 = bmSrc.Scan0;

			unsafe
			{
				byte * p = (byte *)(void *)Scan0;
				byte * pSrc = (byte *)(void *)SrcScan0;

				int nOffset = bmData.Stride - b.Width*3;
				int nWidth = b.Width;
				int nHeight = b.Height;

				double xOffset, yOffset;

				double fraction_x, fraction_y, one_minus_x, one_minus_y;
				int ceil_x, ceil_y, floor_x, floor_y;
				Byte p1, p2;

				for(int y=0;y < nHeight;++y)
				{
					for(int x=0; x < nWidth; ++x )
					{	
						xOffset = fp[x,y].X;
						yOffset = fp[x,y].Y;
				
						// Setup

						floor_x = (int)Math.Floor(xOffset);
						floor_y = (int)Math.Floor(yOffset);
						ceil_x = floor_x + 1;
						ceil_y = floor_y + 1;
						fraction_x = xOffset - floor_x;
						fraction_y = yOffset - floor_y;
						one_minus_x = 1.0 - fraction_x;
						one_minus_y = 1.0 - fraction_y;

						if (floor_y >= 0 && ceil_y < nHeight && floor_x >= 0 && ceil_x < nWidth)
						{
							// Blue

							p1 = (Byte)(one_minus_x * (double)(pSrc[floor_y * scanline + floor_x * 3]) +
								fraction_x * (double)(pSrc[floor_y * scanline + ceil_x * 3]));

							p2 = (Byte)(one_minus_x * (double)(pSrc[ceil_y * scanline + floor_x * 3]) +
								fraction_x * (double)(pSrc[ceil_y * scanline + 3 * ceil_x]));

							p[x * 3 + y*scanline] = (Byte)(one_minus_y * (double)(p1) + fraction_y * (double)(p2));
							
							// Green

							p1 = (Byte)(one_minus_x * (double)(pSrc[floor_y * scanline + floor_x * 3 + 1]) +
								fraction_x * (double)(pSrc[floor_y * scanline + ceil_x * 3 + 1]));

							p2 = (Byte)(one_minus_x * (double)(pSrc[ceil_y * scanline + floor_x * 3 + 1]) +
								fraction_x * (double)(pSrc[ceil_y * scanline + 3 * ceil_x + 1]));
			
							p[x * 3 + y*scanline + 1] = (Byte)(one_minus_y * (double)(p1) + fraction_y * (double)(p2));

							// Red

							p1 = (Byte)(one_minus_x * (double)(pSrc[floor_y * scanline + floor_x * 3 + 2]) +
								fraction_x * (double)(pSrc[floor_y * scanline + ceil_x * 3 + 2]));

							p2 = (Byte)(one_minus_x * (double)(pSrc[ceil_y * scanline + floor_x * 3 + 2]) +
								fraction_x * (double)(pSrc[ceil_y * scanline + 3 * ceil_x + 2]));
			
							p[x * 3 + y*scanline + 2] = (Byte)(one_minus_y * (double)(p1) + fraction_y * (double)(p2));
						}
					}
				}
			}

			b.UnlockBits(bmData);
			bSrc.UnlockBits(bmSrc);

			return true;
		}

		public static bool Flip(Bitmap b, bool bHorz, bool bVert)
		{
			Point [,] ptFlip = new Point[b.Width,b.Height]; 

			int nWidth = b.Width;
			int nHeight = b.Height;

			for (int x = 0; x < nWidth; ++x)
				for (int y = 0; y < nHeight; ++y)
				{
					ptFlip[x, y].X = (bHorz) ? nWidth - (x+1) : x;
					ptFlip[x,y].Y = (bVert) ? nHeight - (y + 1) : y;
				}
				
			OffsetFilterAbs(b, ptFlip);		

			return true;
		}

		public static bool RandomJitter(Bitmap b, short nDegree)
		{
			Point [,] ptRandJitter = new Point[b.Width,b.Height]; 

			int nWidth = b.Width;
			int nHeight = b.Height;

			int newX, newY;

			short nHalf = (short)Math.Floor((double)nDegree/2);
			Random rnd = new Random();

			for (int x = 0; x < nWidth; ++x)
				for (int y = 0; y < nHeight; ++y)
				{
					newX = rnd.Next(nDegree) - nHalf;

					if (x + newX > 0 && x + newX < nWidth)
						ptRandJitter[x, y].X = newX;
					else
						ptRandJitter[x, y].X = 0;

					newY = rnd.Next(nDegree) - nHalf;

					if (y + newY > 0 && y + newY < nWidth)
						ptRandJitter[x, y].Y = newY;
					else
						ptRandJitter[x, y].Y = 0;
				}
				
			OffsetFilter(b, ptRandJitter);		

			return true;
		}
		public static bool Swirl(Bitmap b, double fDegree, bool bSmoothing /* default fDegree to .05 */)
		{
			int nWidth = b.Width;
			int nHeight = b.Height;
			FloatPoint [,] fp = new FloatPoint[nWidth, nHeight];
			Point [,] pt = new Point[nWidth, nHeight];
			Point mid = new Point();
			mid.X = nWidth/2;
			mid.Y = nHeight/2;
			double theta, radius;
			double newX, newY;
			for (int x = 0; x < nWidth; ++x)
				for (int y = 0; y < nHeight; ++y){
					int trueX = x - mid.X;
					int trueY = y - mid.Y;
					theta = Math.Atan2((trueY),(trueX));
					radius = Math.Sqrt(trueX*trueX + trueY*trueY);
					newX = mid.X + (radius * Math.Cos(theta + fDegree * radius));
					if (newX > 0 && newX < nWidth){
						fp[x, y].X = newX;
						pt[x, y].X = (int)newX;
					}
					else
						fp[x, y].X = pt[x, y].X = x;
					newY = mid.Y + (radius * Math.Sin(theta + fDegree * radius));
					if (newY > 0 && newY < nHeight){
						fp[x, y].Y = newY;
						pt[x, y].Y = (int)newY;
					}
					else
						fp[x, y].Y = pt[x, y].Y = y;
				}
			if(bSmoothing)
				OffsetFilterAntiAlias(b, fp);
			else
				OffsetFilterAbs(b, pt);		
			return true;
		}
		public static bool Sphere(Bitmap b, bool bSmoothing){
			int nWidth = b.Width;
			int nHeight = b.Height;
			FloatPoint [,] fp = new FloatPoint[nWidth, nHeight];
			Point [,] pt = new Point[nWidth, nHeight];
			Point mid = new Point();
			mid.X = nWidth/2;
			mid.Y = nHeight/2;
			double theta, radius;
			double newX, newY;
			for (int x = 0; x < nWidth; ++x)
				for (int y = 0; y < nHeight; ++y){
					int trueX = x - mid.X;
					int trueY = y - mid.Y;
					theta = Math.Atan2((trueY),(trueX));
					radius = Math.Sqrt(trueX*trueX + trueY*trueY);
					double newRadius = radius * radius/(Math.Max(mid.X, mid.Y));
					newX = mid.X + (newRadius * Math.Cos(theta));
					if (newX > 0 && newX < nWidth){
						fp[x, y].X = newX;
						pt[x, y].X = (int) newX;
					}
					else
					{
						fp[x, y].X = fp[x,y].Y = 0.0;
						pt[x, y].X = pt[x,y].Y = 0;
					}
					newY = mid.Y + (newRadius * Math.Sin(theta));
					if (newY > 0 && newY < nHeight && newX > 0 && newX < nWidth){
						fp[x, y].Y = newY;
						pt[x, y].Y = (int) newY;
					}
					else{
						fp[x, y].X = fp[x,y].Y = 0.0;
						pt[x, y].X = pt[x,y].Y = 0;
					}
				}
			if(bSmoothing)
				OffsetFilterAbs(b, pt);
			else
				OffsetFilterAntiAlias(b, fp);
			return true;
		}

		public static bool TimeWarp(Bitmap b, Byte factor, bool bSmoothing){
			int nWidth = b.Width;
			int nHeight = b.Height;
			FloatPoint [,] fp = new FloatPoint[nWidth, nHeight];
			Point [,] pt = new Point[nWidth, nHeight];
			Point mid = new Point();
			mid.X = nWidth/2;
			mid.Y = nHeight/2;
			double theta, radius;
			double newX, newY;
			for (int x = 0; x < nWidth; ++x)
				for (int y = 0; y < nHeight; ++y){
					int trueX = x - mid.X;
					int trueY = y - mid.Y;
					theta = Math.Atan2((trueY),(trueX));
					radius = Math.Sqrt(trueX*trueX + trueY*trueY);
					double newRadius = Math.Sqrt(radius) * factor;
					newX = mid.X + (newRadius * Math.Cos(theta));
					if (newX > 0 && newX < nWidth){
						fp[x, y].X = newX;
						pt[x, y].X = (int) newX;
					}
					else
					{
						fp[x, y].X = 0.0;
						pt[x, y].X = 0;
					}
					newY = mid.Y + (newRadius * Math.Sin(theta));
					if (newY > 0 && newY < nHeight){
						fp[x, y].Y = newY;
						pt[x, y].Y = (int) newY;
					}
					else{
						fp[x, y].Y = 0.0;
						pt[x, y].Y = 0;
					}
				}

			if(bSmoothing)
				OffsetFilterAbs(b, pt);
			else
				OffsetFilterAntiAlias(b, fp);
	
			return true;
		}

		public static bool Moire(Bitmap b, double fDegree)
		{
			int nWidth = b.Width;
			int nHeight = b.Height;

			Point [,] pt = new Point[nWidth, nHeight];

			Point mid = new Point();
			mid.X = nWidth/2;
			mid.Y = nHeight/2;
			double theta, radius;
			int newX, newY;
			for (int x = 0; x < nWidth; ++x)
				for (int y = 0; y < nHeight; ++y)
				{
					int trueX = x - mid.X;
					int trueY = y - mid.Y;
					theta = Math.Atan2((trueX),(trueY));
					radius = Math.Sqrt(trueX*trueX + trueY*trueY);
					newX = (int)(radius * Math.Sin(theta + fDegree * radius));
					if (newX > 0 && newX < nWidth)
					{
						pt[x, y].X = (int) newX;
					}
					else
					{
						pt[x, y].X = 0;
					}

					newY = (int)(radius * Math.Sin(theta + fDegree * radius));
					if (newY > 0 && newY < nHeight)
					{
						pt[x, y].Y = (int) newY;
					}
					else
					{
						pt[x, y].Y = 0;
					}
				}

			OffsetFilterAbs(b, pt);

			return true;
		}

		public static bool Water(Bitmap b, short nWave, bool bSmoothing)
		{
			int nWidth = b.Width;
			int nHeight = b.Height;

			FloatPoint [,] fp = new FloatPoint[nWidth, nHeight];
			Point [,] pt = new Point[nWidth, nHeight];

			Point mid = new Point();
			mid.X = nWidth/2;
			mid.Y = nHeight/2;

			double newX, newY;
			double xo, yo;

			for (int x = 0; x < nWidth; ++x)
				for (int y = 0; y < nHeight; ++y)
				{
					xo = ((double)nWave * Math.Sin(2.0 * 3.1415 * (float)y / 128.0));
					yo = ((double)nWave * Math.Cos(2.0 * 3.1415 * (float)x / 128.0));

					newX = (x + xo);
					newY = (y + yo);

					if (newX > 0 && newX < nWidth)
					{
						fp[x, y].X = newX;
						pt[x, y].X = (int) newX;
					}
					else
					{
						fp[x, y].X = 0.0;
						pt[x, y].X = 0;
					}


					if (newY > 0 && newY < nHeight)
					{
						fp[x, y].Y = newY;
						pt[x, y].Y = (int) newY;
					}
					else
					{
						fp[x, y].Y = 0.0;
						pt[x, y].Y = 0;
					}
				}

			if(bSmoothing)
				OffsetFilterAbs(b, pt);
			else
				OffsetFilterAntiAlias(b, fp);
	
			return true;
		}

		public static bool Pixelate(Bitmap b, short pixel, bool bGrid)
		{
			int nWidth = b.Width;
			int nHeight = b.Height;

			Point [,] pt = new Point[nWidth, nHeight];

			int newX, newY;

			for (int x = 0; x < nWidth; ++x)
				for (int y = 0; y < nHeight; ++y)
				{
					newX = pixel - x%pixel;

					if (bGrid && newX == pixel)
						pt[x, y].X = -x;
					else if (x + newX > 0 && x +newX < nWidth)
						pt[x, y].X = newX;
					else
						pt[x, y].X = 0;

					newY = pixel - y%pixel;

					if (bGrid && newY == pixel)
						pt[x, y].Y = -y;
					else if (y + newY > 0 && y + newY < nHeight)
						pt[x, y].Y = newY;
					else
						pt[x, y].Y = 0;
				}

			OffsetFilter(b, pt);

			return true;
		}
	}
}
