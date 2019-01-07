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
    public partial class PDS : Form
    {
        public PDS()
        {
            InitializeComponent();
        }

        public void setComment(string cm)
        {
            txtcm.Text = cm;
        }

        public void setTitle(string title)
        {
            Text = title;
        }
    }
}
