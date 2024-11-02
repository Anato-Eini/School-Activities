using System;
using System.Collections;
using System.Text;
using System.Runtime.InteropServices;

namespace WebCamLib
{
    public class DeviceManager
    {
	//	[DllImport("inpout32.dll", EntryPoint="Out32")]
	//	public static extern void Output(int adress, int value);
		
	//	[DllImport("inpout32.dll", EntryPoint="Inp32")]
	//	public static extern int Input(int adress);
		
        [DllImport("avicap32.dll")]
        protected static extern bool capGetDriverDescriptionA(short wDriverIndex,
            [MarshalAs(UnmanagedType.VBByRefStr)]ref String lpszName,
           int cbName, [MarshalAs(UnmanagedType.VBByRefStr)] ref String lpszVer, int cbVer);

        static ArrayList devices = new ArrayList();

        public static Device[] GetAllDevices()
        {
            String dName = "".PadRight(100);
            String dVersion = "".PadRight(100);

            for (short i = 0; i < 10; i++)
            {
                if (capGetDriverDescriptionA(i, ref dName, 100, ref dVersion, 100))
                {
                    Device d = new Device(i);
                    d.Name = dName.Trim();
                    d.Version = dVersion.Trim();

                    devices.Add(d);                    
                }
            }

            return (Device[])devices.ToArray(typeof(Device));
        }

        public static Device GetDevice(int deviceIndex)
        {
            return (Device)devices[deviceIndex];
        }
    }
}
