using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Net.NetworkInformation;

namespace Remote_Computer
{
    public partial class Template : UserControl
    {
        public Template()
        {
            InitializeComponent();
        }

        private void btnmore_Click(object sender, EventArgs e)
        {
            try
            {
                Button b1 = sender as Button;
                TemplateItem ti = b1.Tag as TemplateItem;
                TemplateInformation t1 = new TemplateInformation(ti.ID, ti.Comment, ti.Title, ti.Programmer, ti.PictureName1, ti.PictureName2, ti.PictureName3, ti.PictureName4);
                t1.ShowDialog();
            }
            catch (Exception) { }
        }

        private void btnpurchase_Click(object sender, EventArgs e)
        {
            Button b1 = sender as Button;
            TemplateItem ti = b1.Tag as TemplateItem;

            if (myClass.CheckForInternetConnection() == false)
            {
                MessageBox.Show("لطفا اینترنت را فعال کنید","توجه",MessageBoxButtons.OK,MessageBoxIcon.Information);
                return;
            }

            Application.DoEvents();
            myClass.wb1.Url = new Uri("http://www.iranapp.org/abm/purchase/" + ti.ID.ToString());
            myClass.CurrentTI = ti;
     
        }

        private void picture1_Click(object sender, EventArgs e)
        {
            PictureBox p1 = sender as PictureBox;

            frmpicture fr1 = new frmpicture();
            fr1.SetImage(p1.Image);
            fr1.ShowDialog();
        }
    }
}
