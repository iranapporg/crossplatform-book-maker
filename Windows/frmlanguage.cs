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
    public partial class frmlanguage : Form
    {
        string lang;

        public frmlanguage()
        {
            InitializeComponent();
        }

        public string Language
        {
            get
            {
                return lang;
            }
        }

        private void btncontinue_Click(object sender, EventArgs e)
        {
            Hide();
        }

        private void rbfa_CheckedChanged(object sender, EventArgs e)
        {
            lang = "فارسی";
        }

        private void rben_CheckedChanged(object sender, EventArgs e)
        {
            lang = "انگلیسی";
        }

        private void rbar_CheckedChanged(object sender, EventArgs e)
        {
            lang = "عربی";
        }

        private void btncancel_Click(object sender, EventArgs e)
        {
            lang = "";
            Hide();
        }

    }
}
