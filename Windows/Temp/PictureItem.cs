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
    public partial class PictureItem : UserControl
    {
        public PictureItem()
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

        private void btndelete_Click(object sender, EventArgs e)
        {
            Button b1 = sender as Button;
            string filename = b1.Tag.ToString() ;

            string ext = Path.GetExtension(filename);

            try
            {
                if (MessageBox.Show("آیا مایل به حذف فایل هستید؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    File.Delete(filename);

                    b1.Parent.Enabled = false;
                    
                    PictureBox pb = b1.Parent.Controls["gb1"].Controls["pbPicture"] as PictureBox;
                    pb.SizeMode = PictureBoxSizeMode.StretchImage;
                    pb.Image = Remote_Computer.Properties.Resources.No_picture_available;

                    frmToast t1 = new frmToast();
                    t1.setComment = "فایل مورد نظر حذف شد";
                    t1.Show();
                }
            }
            catch (Exception)
            {

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
                    File.Copy(fd1.FileName, Path.GetDirectoryName(filename) + "\\" + Path.GetFileName(filename), true);

                    using (FileStream m1 = new FileStream(filename,FileMode.Open, FileAccess.Read)) {
                        PictureBox pb = b1.Parent.Controls["gb1"].Controls["pbPicture"] as PictureBox;
                        pb.SizeMode = PictureBoxSizeMode.StretchImage;
                        pb.Image = Image.FromStream(m1);
                    }

                    frmToast t1 = new frmToast();
                    t1.setComment = "فایل مورد نظر تغییر کرد";
                    t1.Show();
                }
            }
        }
    }
}
