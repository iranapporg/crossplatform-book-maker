using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Remote_Computer
{
    public partial class frmvideo : Form
    {
        public int position = 0;

        public frmvideo()
        {
            InitializeComponent();
        }

        public void PlayVideo(string url)
        {
            try
            {
                axWindowsMediaPlayer1.URL = "http://www.iranapp.org/asset/abm_shop/help/" + url + ".wmv";
                axWindowsMediaPlayer1.Ctlcontrols.currentPosition = position;
            }
            catch (Exception) { }
        }
    }
}
