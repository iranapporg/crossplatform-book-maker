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
    public partial class frmios : Form
    {

        public frmios()
        {
            InitializeComponent();
        }

        public string ShabakCode
        {
            set
            {
               txtcode.Text = value;
            }
        }

        private void btnget_Click(object sender, EventArgs e)
        {
            SaveFileDialog fd1 = new SaveFileDialog();
            fd1.Filter = "*.ipa|*.ipa";
            fd1.Title = "خروجي کتاب";
            fd1.ShowDialog();

            if (fd1.FileName != "")
            {
                File.Copy(Application.StartupPath + "\\tool\\book.ipa", fd1.FileName, true);
                MessageBox.Show("کتاب با موفقيت ايجاد شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                System.Diagnostics.Process.Start("explorer.exe", Path.GetDirectoryName(fd1.FileName));
            }
            Hide();
        }
    }
}
