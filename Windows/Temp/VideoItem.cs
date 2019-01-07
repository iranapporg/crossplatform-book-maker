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
    public partial class VideoItem : UserControl
    {

        public VideoItem()
        {
            InitializeComponent();
        }

        private void btnplay_Click(object sender, EventArgs e)
        {
            Button b1 = sender as Button;
            myClass.OpenFile(b1.Tag.ToString());
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

        private void VideoItem_Load(object sender, EventArgs e)
        {

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
    }
}
