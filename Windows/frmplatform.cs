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
    public partial class frmplatform : Form
    {
        string outputType;

        public frmplatform()
        {
            InitializeComponent();
            outputType = "android";
        }

        public string PlatformType
        {
            get
            {
                return outputType;
            }
        }

        private void chkandroid_CheckedChanged(object sender, EventArgs e)
        {
            outputType = "android";
        }

        private void btnget_Click(object sender, EventArgs e)
        {
            Hide();
        }

        private void btncancel_Click(object sender, EventArgs e)
        {
            outputType = "";
            Hide();
        }

        private void chkwindows_CheckedChanged(object sender, EventArgs e)
        {
            outputType = "win";
        }

        private void chklinux_CheckedChanged(object sender, EventArgs e)
        {
            outputType = "linux";
        }

        private void chkmac_CheckedChanged(object sender, EventArgs e)
        {
            outputType = "mac";
        }

        private void ckios_CheckedChanged(object sender, EventArgs e)
        {
            outputType = "ios";
         
            if (ckios.Checked == true)
                btnget.Text = "ادامه";
            else
                btnget.Text = "دریافت فایل";
        }
    }
}
