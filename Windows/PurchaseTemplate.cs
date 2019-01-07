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
    public partial class PurchaseTemplate : UserControl
    {
        public PurchaseTemplate()
        {
            InitializeComponent();
        }

        private void btnselect_Click(object sender, EventArgs e)
        {
            string filename = "", platform = "";
            Button b1 = sender as Button;

            try
            {
                string[] data = b1.Tag as string[];
                filename = data[0];
                platform = data[1];
            }
            catch (Exception) { }

            if (MessageBox.Show("آیا مطمئن هستید که این قالب را پیش فرض کتابساز قرار دهید؟","توجه",MessageBoxButtons.YesNo,MessageBoxIcon.Question) == DialogResult.Yes) {
                try
                {
                    if (platform == "android")
                    {
                        File.Copy(Application.StartupPath + "\\template\\android\\" + filename, Application.StartupPath + "\\tool\\book.apk", true);
                        MessageBox.Show("قالب شما با موفقیت به عنوان قالب پیش فرض انتخاب شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        File.WriteAllText(Application.StartupPath + "\\template\\android\\default.txt", filename);
                    }
                    else if (platform == "ios")
                    {
                        File.Copy(Application.StartupPath + "\\template\\ios\\" + filename, Application.StartupPath + "\\tool\\book.ipa", true);
                        MessageBox.Show("قالب شما با موفقیت به عنوان قالب پیش فرض انتخاب شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        File.WriteAllText(Application.StartupPath + "\\template\\ios\\default.txt", filename);
                    }
                    btnselect.BackColor = Color.FromArgb(81, 180, 102);
                }
                catch
                {
                }
            }
        }

        private void picture1_Click(object sender, EventArgs e)
        {
            PictureBox p1 = sender as PictureBox;

            frmpicture fr1 = new frmpicture();
            fr1.SetImage(p1.Image);
            fr1.ShowDialog();
        }

        private void btnedit_Click(object sender, EventArgs e)
        {
            Button b1 = sender as Button;
            string[] item = (string[])b1.Tag;

            frmEditApk frm1 = new frmEditApk();
            frm1.setInformation(item[5], item[0], item[1]);
            frm1.Show();
        }
    }
}
