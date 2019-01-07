using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Remote_Computer
{
    public partial class UserControl1 : UserControl
    {
        public UserControl1()
        {
            InitializeComponent();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            if (progressBar1.Value == 100)
                progressBar1.Value = 0;
            progressBar1.Value = progressBar1.Value + 2;
        }
        public void goProgess(bool Active)
        {
            timer1.Enabled = Active;
        }
    }
}
