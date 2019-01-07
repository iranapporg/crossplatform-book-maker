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
    public partial class frmToast : Form
    {
        Timer t1 = new Timer();
        public frmToast()
        {
            InitializeComponent();
            CenterToScreen();
            Top = Top + 200;
        }
        
        public string setComment
        {
            set {
                lblcomment.Text = value;
            }
        }
        private void trm1_Tick(object sender, EventArgs e)
        {
            if (Opacity == 0.0)
            {
                trm1.Enabled = false;
                Hide();
            }
            else
                Opacity -= 0.1;
        }

        private void t2_Tick(object sender, EventArgs e)
        {
            trm1.Enabled = true;
            t2.Enabled = false;
        }

    }
}
