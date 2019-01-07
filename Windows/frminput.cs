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
    public partial class frminput : Form
    {
        string sProject,sChapterNames,sFilename;
        public bool edit = false;

        public string sChapter
        {
            get{
                return txtchapter.Text;
            }
        }

        public bool isRoot
        {
            get
            {
                return ck1.Checked;
            }
        }

        public string sProjectName
        {
            set
            {
                sProject = value;
            }
        }

        public string SetChapterName
        {
            set
            {
                sChapterNames = value;
                txtchapter.Text = value;

            }
        }

        public frminput(bool Edit)
        {
            edit = Edit;
            InitializeComponent();
            if (edit == true)
                btnadd.Text = "اعمال ویرایش";
        }

        private void btnadd_Click(object sender, EventArgs e)
        {
            if (txtchapter.Text == "")
            {
                MessageBox.Show("لطفا نام سرفصل را وارد کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }

            Hide();

        }

        private void btncancel_Click(object sender, EventArgs e)
        {
            txtchapter.Text = "";
            Hide();
        }

        private void frminput_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
                btnadd_Click(null, null);
            if (e.KeyCode == Keys.Escape)
                btncancel_Click(null, null);
        }

        private void frminput_FormClosing(object sender, FormClosingEventArgs e)
        {
            e.Cancel = true;
        }

    }
}
