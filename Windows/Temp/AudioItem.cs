using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace Remote_Computer
{
    public partial class AudioItem : UserControl
    {
        WMPLib.WindowsMediaPlayer w1 = new WMPLib.WindowsMediaPlayer();
        bool state;

        public AudioItem()
        {
            InitializeComponent();
        }

        private void pbPictureH_MouseDown(object sender, MouseEventArgs e)
        {
            PictureBox p1 = sender as PictureBox;
            myClass.CurrentFilename = p1.Tag.ToString();
            myClass.CurrentPicturebox = p1;

            if (e.Button == MouseButtons.Left)
            {
                myClass.OpenFile(p1.Tag.ToString());
            }
            
        }

        private void btnplay_Click(object sender, EventArgs e)
        {
            Button b1 = sender as Button;
            w1.URL = b1.Tag.ToString();
            if (state == false)
            {
                w1.controls.play();
                b1.BackgroundImage = Remote_Computer.Properties.Resources.stop;
                b1.BackgroundImageLayout = ImageLayout.Stretch;
                state = true;
            }
            else
            {
                w1.controls.stop();
                state = false;
                b1.BackgroundImage = Remote_Computer.Properties.Resources.play;
                b1.BackgroundImageLayout = ImageLayout.Stretch;
            }
        }

        private void btndelete_Click(object sender, EventArgs e)
        {
            Button b1 = sender as Button;

            if (MessageBox.Show("آیا مایل به حذف فایل هستید؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {
                try
                {
                    File.Delete(b1.Tag.ToString());
                    frmToast t1 = new frmToast();
                    t1.setComment = "فایل مورد نظر حذف شد";
                    t1.Show();
                    b1.Parent.Enabled = false;
                }
                catch (Exception) { }
            }
        }

        private void btnreplace_Click(object sender, EventArgs e)
        {
            Button b1 = sender as Button;
            string filename = b1.Tag.ToString();
            string ext = Path.GetExtension(filename);

            fd1.Filter = ext + "|*" + ext;

            if (fd1.ShowDialog() == DialogResult.OK)
            {
                if (File.Exists(fd1.FileName))
                {
                    File.Copy(fd1.FileName, Application.StartupPath + "\\tool\\book\\assets\\" + Path.GetFileName(filename), true);

                    frmToast t1 = new frmToast();
                    t1.setComment = "فایل مورد نظر تغییر کرد";
                    t1.Show();
                }
            }
        }

        private void fd1_FileOk(object sender, CancelEventArgs e)
        {

        }
    }
}
