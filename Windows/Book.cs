using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Remote_Computer
{
    public partial class Book : UserControl
    {
        public Book()
        {
            InitializeComponent();
        }

        private void btndelete_Click(object sender, EventArgs e)
        {
            Button btn = sender as Button;
            if (MessageBox.Show("آیا مطین به حذف کتاب هستید؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {
                string id = btn.Tag.ToString();
                Enabled = false;
                string res = myClass.ExecuteMethod(myClass.URL + "index.php/server/remove_book/" + id + "/" + myClass.Username + "/" + myClass.Password,"");
                Enabled = true;
                if (res == "1")
                {
                    btndelete.Text = "حذف شد";
                    btndelete.Enabled = false;
                    MessageBox.Show("کتاب با موفقیت حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                else
                    MessageBox.Show("متاسفانه کتاب حذف نشد.دوباره تلاش کنید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }
    }
}
