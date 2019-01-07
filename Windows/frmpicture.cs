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
    public partial class frmpicture : Form
    {
        public frmpicture()
        {
            InitializeComponent();
        }

        public void SetImage(Image i1)
        {
            p1.Image = i1;
        }
    }
}
