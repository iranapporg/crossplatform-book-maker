using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net.NetworkInformation;
using System.Net;
using System.IO;
using System.Drawing.Imaging;

namespace Remote_Computer
{
    public partial class TemplateInformation : Form
    {
        string[] links = new string[4];
        byte[][] b1 = new byte[4][];
        PictureBox[] pic = new PictureBox[4];
        string ID;

        public TemplateInformation(string id,string comment, string title, string programmer, string px1, string px2, string px3, string px4)
        {
            InitializeComponent();
            Text = title;
            lblcomment.Text = comment.Replace(@"</br>",Environment.NewLine);
            lblprogrammer.Text = programmer;

            ID = id;

            if (File.Exists(Application.StartupPath + "\\cache\\Image\\" + id + "\\" + myClass.getFilenameUrl(px1)))
            {
                p1.Image = Image.FromFile(Application.StartupPath + "\\cache\\Image\\" + id + "\\" + myClass.getFilenameUrl(px1));
                p1.SizeMode = PictureBoxSizeMode.StretchImage;
            }
            else
                links[0] = px1;

            if (File.Exists(Application.StartupPath + "\\cache\\Image\\" + id + "\\" + myClass.getFilenameUrl(px2)))
            {
                p2.Image = Image.FromFile(Application.StartupPath + "\\cache\\Image\\" + id + "\\" + myClass.getFilenameUrl(px2));
                p2.SizeMode = PictureBoxSizeMode.StretchImage;
            }
            else
                links[1] = px2;

            if (File.Exists(Application.StartupPath + "\\cache\\Image\\" + id + "\\" + myClass.getFilenameUrl(px3)))
            {
                p3.Image = Image.FromFile(Application.StartupPath + "\\cache\\Image\\" + id + "\\" + myClass.getFilenameUrl(px3));
                p3.SizeMode = PictureBoxSizeMode.StretchImage;
            }
            else
                links[2] = px3;

            if (File.Exists(Application.StartupPath + "\\cache\\Image\\" + id + "\\" + myClass.getFilenameUrl(px4)))
            {
                p4.Image = Image.FromFile(Application.StartupPath + "\\cache\\Image\\" + id + "\\" + myClass.getFilenameUrl(px4));
                p4.SizeMode = PictureBoxSizeMode.StretchImage;
            }
            else
                links[3] = px4;

            p1.Tag = px1;
            pic[0] = p1;

            p2.Tag = px2;
            pic[1] = p2;

            p3.Tag = px3;
            pic[2] = p3;

            p4.Tag = px4;
            pic[3] = p4;

            backgroundWorker1.RunWorkerAsync();

        }

        private void btnexit_Click(object sender, EventArgs e)
        {
            Hide();
        }

        private void p1_Click(object sender, EventArgs e)
        {

        }

        private void backgroundWorker1_DoWork(object sender, DoWorkEventArgs e)
        {
            WebClient wc = new WebClient();

            for (int i = 0; i < links.Length; i++)
            {
                if (links[i] != "" && links[i] != null)
                    try
                    {
                        b1[i] = wc.DownloadData(links[i]);
                    }
                    catch (Exception) { }
            }

        }

        private void backgroundWorker1_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            for (int i = 0; i < b1.Length; i++)
            {
                try
                {
                    if (b1[i] == null) continue;

                    if (b1[i].Length > 0)
                    {
                        MemoryStream m1 = new MemoryStream(b1[i]);
                        pic[i].SizeMode = PictureBoxSizeMode.StretchImage;
                        pic[i].Image = Image.FromStream(m1);

                        string filename = Application.StartupPath + "\\cache\\Image\\" + ID + "\\" + myClass.getFilenameUrl(pic[i].Tag.ToString());

                        Bitmap bb1 = new Bitmap(pic[i].Image);
                        bb1.Save(filename);
                    }
                    else
                    {
                        pic[i].SizeMode = PictureBoxSizeMode.StretchImage;
                        pic[i].Image = Remote_Computer.Properties.Resources.No_picture_available;
                    }
                }
                catch (Exception) {
                    pic[i].SizeMode = PictureBoxSizeMode.StretchImage;
                    pic[i].Image = Remote_Computer.Properties.Resources.No_picture_available;
                }
            }
        }

        private void p4_Click(object sender, EventArgs e)
        {
            PictureBox p1 = sender as PictureBox;
            frmpicture fr = new frmpicture();
            fr.SetImage(p1.Image);
            fr.ShowDialog();
        }
    }
}
