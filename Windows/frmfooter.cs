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
    public partial class frmfooter : Form
    {

        string ID;
        db d1;

        public string TopicId
        {
            set
            {
                ID = value;
            }
        }

        public frmfooter(string sProjectID)
        {
            InitializeComponent();
            d1 = new db(sProjectID);
        }

        public void LoadFooters(DataTable dt1)
        {
            foreach (DataRow dr1 in dt1.Rows)
            {
                dg1.Rows.Add(dr1["sFooterID"].ToString(), dr1["sText"].ToString());
            }
        }

        private void btnadd_Click(object sender, EventArgs e)
        {

            if (txtbody.Text.Length < 3 || txttitle.Text.Length < 3)
            {
                MessageBox.Show("لطفا اطلاعات را وارد کنید","خطا",MessageBoxButtons.OK,MessageBoxIcon.Exclamation);
                return;
            }

            d1.AddFooter(txttitle.Text, txtbody.Text, ID);
            dg1.Rows.Add(txttitle.Text, txtbody.Text);
            txtbody.Text = txttitle.Text = "";
            MessageBox.Show("پاورقی با موفقیت ایجاد شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);

        }

        private void dg1_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Delete)
            {
                if (MessageBox.Show("آیا مایل به حذف این گزینه هستید؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    string id = dg1.Rows[dg1.CurrentCell.RowIndex].Cells[0].Value.ToString();
                    d1.DeleteFooter(id,ID);
                    dg1.Rows.RemoveAt(dg1.CurrentCell.RowIndex);
                    MessageBox.Show("گزینه مورد نظر با موفقیت حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }
        }

        private void dg1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
 
    }
}
