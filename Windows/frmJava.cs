using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace Remote_Computer
{
    public partial class frmJava : Form
    {
        public frmJava()
        {
            InitializeComponent();
        }

        private void frmJava_Load(object sender, EventArgs e)
        {

        }

        private void btnready_Click(object sender, EventArgs e)
        {
                OpenFileDialog fd = new OpenFileDialog(); ;
                fd.CheckFileExists = true;
                fd.CheckPathExists = true;
                fd.Filter = "Java file|java.exe";
                fd.Title = "انتخاب فایل جاوا";

                if (fd.ShowDialog() == DialogResult.OK)
                {
                    FileInfo f1 = new FileInfo(fd.FileName);
                    if (f1.Name == "java.exe")
                    {
                        myClass.setValueRegistery("Java", fd.FileName);
                        MessageBox.Show("مسیر جاوا به درستی انتخاب و تنظیم شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        Application.Restart();
                    }
                    else
                        MessageBox.Show("لطفا فایل را انتخاب کنید تا وارد کتابساز شوید","خطا",MessageBoxButtons.OK,MessageBoxIcon.Warning);
                }
                else
                    MessageBox.Show("لطفا فایل را انتخاب کنید تا وارد کتابساز شوید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Warning);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Dispose();
            Application.ExitThread();
        }

        private void lbldownload_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            System.Diagnostics.Process.Start("http://p30download.com/fa/entry/698/");
        }

    }
}
