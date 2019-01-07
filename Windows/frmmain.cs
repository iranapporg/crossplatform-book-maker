using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using DevExpress.DXCore.Controls.XtraEditors.Filtering;
using CompressionSample;
using System.Threading;
using System.Xml;
using System.Media;
using System.Text.RegularExpressions;
using System.Runtime.InteropServices;
using System.Net.NetworkInformation;
using System.Diagnostics;
using System.Net;
using System.Security.AccessControl;
using Ionic.Zip;
using System.Drawing.Text;

namespace Remote_Computer
{

    public partial class frmmain : Form
    {
        string currentZipName,freeID;
        TreeNode currentNode,CurrentCategory;
        PDS pg = new PDS();
        string currentLanguage,currentCategoryBook;

        DataTable dtOnlineCategory = new DataTable();

        List<Newtonsoft.Json.Linq.JObject> links = new List<Newtonsoft.Json.Linq.JObject>();
        Dictionary<string,string> ListBook = new Dictionary<string,string>();
 
        Thread t1;
        public static bool completeCompile = false;
        public static string currentCopyFile;
        string currentFilename;

        bool maximize = false;
        string sIconPath, sProjectName, sRank;
        clsProject c1;
        db d1;
        TreeNode selectNode;
        [DllImport("kernel32.dll", SetLastError = true, CharSet = CharSet.Auto)]
        static extern uint GetWindowsDirectory(StringBuilder lpBuffer, uint uSize);

        public frmmain()
        {
            InitializeComponent();
        }

        private void iControl_Enter(object sender, EventArgs e)
        {
            TextBox t1 = sender as TextBox;
            if (t1.BorderStyle != BorderStyle.None)
                t1.BackColor = Color.Beige;
            else
            {
                t1.BorderStyle = BorderStyle.Fixed3D;
                t1.BackColor = Color.White;
                t1.Tag = "edit";
            }
        }

        private void iControl_Leave(object sender, EventArgs e)
        {
            TextBox t1 = sender as TextBox;

            if (t1.Tag != "edit")
                t1.BackColor = Color.White;
            else
            {
                t1.BorderStyle = BorderStyle.None;
                t1.BackColor = Color.FromArgb(247, 245, 241);
                t1.Tag = "";
            }
        }

        private void btnready_Click(object sender, EventArgs e)
        {

            #region  Validate Input
            if (txtprojectname.Text == "" || txtprojectname.Text.Length < 4)
            {
                txtprojectname.BackColor = Color.Red;
                return;
            }
            else
                txtprojectname.BackColor = Color.White;
            //----------------------------------------
            if (txtversion.Text == "" || txtversion.Text.Trim().Length != 5)
            {
                txtversion.BackColor = Color.Red;
                return;
            }
            else
                txtversion.BackColor = Color.White;
            //----------------------------------------
            if (txtbookname.Text == "" || txtbookname.Text.Length < 4)
            {
                txtbookname.BackColor = Color.Red;
                return;
            }
            else
                txtbookname.BackColor = Color.White;
            //----------------------------------------
            if (txtauthorname.Text == "" || txtauthorname.Text.Length < 4)
            {
                txtauthorname.BackColor = Color.Red;
                return;
            }
            else
                txtauthorname.BackColor = Color.White;
            //----------------------------------------
            if (txtemail.Text == "")
            {
                txtemail.BackColor = Color.Red;
                return;
            }

            //----------------------------------------
            if (txtemail.Text != "" && myClass.IsValidEmailAddress(txtemail.Text) == false)
            {
                txtemail.BackColor = Color.Red;
                return;
            }
            else
                txtemail.BackColor = Color.White;

            //----------------------------------------
            if (File.Exists(Application.StartupPath + "\\" + txtprojectname.Text + ".ini"))
            {
                MessageBox.Show("نام پروژه انتخاب شده قبلا ثبت شده است", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtprojectname.Text = "prj";
                return;
            }
            //////////////////////////////////////////////////////////////////////////
            #endregion

            if (Directory.Exists(Application.StartupPath + "\\" + txtprojectname.Text) == true)
            {
                MessageBox.Show("پروژه ای با این نام از قبل ایجاد شده است\nلطفا نام دیگری انتخاب کنید","خطا",MessageBoxButtons.OK,MessageBoxIcon.Warning);
                return;
            }

            Directory.CreateDirectory(Application.StartupPath + "\\" + txtprojectname.Text);
            File.Copy(Application.StartupPath + "\\data", Application.StartupPath + "\\" + txtprojectname.Text + "\\bank.db");

            sProjectName = txtprojectname.Text;

            c1 = new clsProject(txtprojectname.Text, true);
            c1.sAuthor = txtauthorname.Text;
            c1.sBookName = txtbookname.Text;
            c1.sEmail = txtemail.Text;
            c1.sContent = txtcontent.Text;
            c1.sIcon = sIconPath;
            c1.sPhone = txtphone.Text;
            c1.sWebsite = txtwebsite.Text;
            c1.saveProject();
            startProject();
            this.Text = "کتابساز - " + txtprojectname.Text;
            tabContent.PageEnabled = true;
            tabLibrary.PageEnabled = true;
            tabProduct.PageEnabled = true;
            tabmain.SelectedTabPageIndex = 1;
            myClass.setValueRegistery("Email", txtemail.Text);
            myClass.setValueRegistery("Url", txtwebsite.Text);
            myClass.setValueRegistery("Phone", txtphone.Text);
            myClass.setValueRegistery("Author", txtauthorname.Text);
            try
            {
                File.Copy(Application.StartupPath + "\\b.mp3", Path.Combine(Application.StartupPath + "\\" + sProjectName, "\\b.mp3"), true);
            }
            catch (Exception)
            {

            }
            btnbrowse.Enabled = true;

            MessageBox.Show("پروژه ي جديد ايجاد شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
            d1 = new db(txtprojectname.Text);
            btnchoosepdf.Enabled = true;
        }

        void startProject()
        {
            txtprojectname.BorderStyle = BorderStyle.None;
            txtprojectname.Enabled = false;
            txtprojectname.BackColor = Color.FromArgb(247, 245, 241);
            txtprojectname.Font = new Font(txtprojectname.Font, FontStyle.Bold);
            //////////////////////////////////////////////////////////////////////////
            txtversion.BorderStyle = BorderStyle.None;
            txtversion.BackColor = Color.FromArgb(247, 245, 241);
            txtversion.Font = new Font(txtversion.Font, FontStyle.Bold);
            //////////////////////////////////////////////////////////////////////////
            txtbookname.BorderStyle = BorderStyle.None;
            txtbookname.BackColor = Color.FromArgb(247, 245, 241);
            txtbookname.Font = new Font(txtbookname.Font, FontStyle.Bold);
            //////////////////////////////////////////////////////////////////////////
            txtauthorname.BorderStyle = BorderStyle.None;
            txtauthorname.BackColor = Color.FromArgb(247, 245, 241);
            txtauthorname.Font = new Font(txtauthorname.Font, FontStyle.Bold);
            //////////////////////////////////////////////////////////////////////////
            txtwebsite.BorderStyle = BorderStyle.None;
            txtwebsite.BackColor = Color.FromArgb(247, 245, 241);
            txtwebsite.Font = new Font(txtwebsite.Font, FontStyle.Bold);
            //////////////////////////////////////////////////////////////////////////
            txtemail.BorderStyle = BorderStyle.None;
            txtemail.BackColor = Color.FromArgb(247, 245, 241);
            txtemail.Font = new Font(txtemail.Font, FontStyle.Bold);
            //////////////////////////////////////////////////////////////////////////
            txtphone.BorderStyle = BorderStyle.None;
            txtphone.BackColor = Color.FromArgb(247, 245, 241);
            txtphone.Font = new Font(txtphone.Font, FontStyle.Bold);
            //////////////////////////////////////////////////////////////////////////
            txtcontent.BorderStyle = BorderStyle.None;
            txtcontent.BackColor = Color.FromArgb(247, 245, 241);
            txtcontent.Font = new Font(txtcontent.Font, FontStyle.Bold);
            //////////////////////////////////////////////////////////////////////////
            lblalert.Visible = false;
            lblalertcomment.Visible = false;
        }

        private void txtprojectname_TextChanged(object sender, EventArgs e)
        {
            if (txtprojectname.Text != "")
                if (!(System.Text.RegularExpressions.Regex.IsMatch(txtprojectname.Text, "^[a-zA-Z]+$")))
                {
                    txtprojectname.Text = txtprojectname.Text.Remove(txtprojectname.Text.Length - 1);
                }
        }

        private void btnbrowse_Click(object sender, EventArgs e)
        {
            if (sProjectName == "" || sProjectName == null)
            {
                MessageBox.Show("پروژه اي براي تغييرات وجود ندارد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            OpenFileDialog of1 = new OpenFileDialog();
            of1.CheckFileExists = true;
            of1.Filter = "*.png|*.png";
            of1.Title = "انتخاب آيکون";
            of1.ShowDialog();
            if (of1.FileName != "")
            {
                Bitmap b1 = new Bitmap(of1.FileName);
                if (b1.Width < 64 || b1.Height < 64 || b1.Width > 512 || b1.Height > 512)
                {
                    MessageBox.Show("اندازه تصوير انتخاب شده کوچکتر از 64 پيکسل است", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return;
                }
                else
                {
                    imgicon.Image = Image.FromFile(of1.FileName);
                    sIconPath = of1.FileName;
                    btnremove.Visible = true;
                    if (txtprojectname.Enabled == false)
                        @File.Copy(sIconPath, Application.StartupPath + "\\" + sProjectName + "\\icon.png", true);
                    MessageBox.Show("آيکون کتاب تعيين شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }
        }

        private void btnremove_Click(object sender, EventArgs e)
        {
            sIconPath = "";
            btnremove.Visible = false;
            imgicon.Image = Remote_Computer.Properties.Resources.icon;
            try
            {
                @File.Delete(Application.StartupPath + "\\" + sProjectName + "\\icon.png");
            }
            catch (Exception)
            {

            }
        }

        private void txtbookname_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter && txtprojectname.Enabled == false)
            {
                d1.RunQuery("update tbl_information set sTitle='" + txtbookname.Text + "' where sProjectID='" + txtprojectname.Text + "'");
                MessageBox.Show("گزينه مورد نظر ويرايش شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtbookname.BorderStyle = BorderStyle.None;
                txtbookname.BackColor = Color.FromArgb(247, 245, 241);
            }
        }

        private void txtauthorname_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter && txtprojectname.Enabled == false)
            {
                d1.RunQuery("update tbl_information set sAuthor='" + txtauthorname.Text + "' where sProjectID='" + txtprojectname.Text + "'");
                MessageBox.Show("گزينه مورد نظر ويرايش شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtauthorname.BorderStyle = BorderStyle.None;
                txtauthorname.BackColor = Color.FromArgb(247, 245, 241);
            }
        }

        private void txtcontent_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter && txtprojectname.Enabled == false)
            {
                d1.RunQuery("update tbl_information set sContent='" + txtcontent.Text + "' where sProjectID='" + txtprojectname.Text + "'");
                MessageBox.Show("گزينه مورد نظر ويرايش شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtauthorname.BorderStyle = BorderStyle.None;
                txtauthorname.BackColor = Color.FromArgb(247, 245, 241);
            }
        }

        private void txtwebsite_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter && txtprojectname.Enabled == false)
            {
                d1.RunQuery("update tbl_information set sURL='" + txtwebsite.Text + "' where sProjectID='" + txtprojectname.Text + "'");
                MessageBox.Show("گزينه مورد نظر ويرايش شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtwebsite.BorderStyle = BorderStyle.None;
                txtwebsite.BackColor = Color.FromArgb(247, 245, 241);
            }
        }

        private void txtemail_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter && txtprojectname.Enabled == false)
            {
                if (myClass.IsValidEmailAddress(txtemail.Text) == false)
                {
                    MessageBox.Show("آدرس الکترونيکي اشتباه است", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    txtemail.BackColor = Color.Red;
                    return;
                }
                d1.RunQuery("update tbl_information set sEmail='" + txtemail.Text + "' where sProjectID='" + txtprojectname.Text + "'");
                MessageBox.Show("گزينه مورد نظر ويرايش شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtemail.BorderStyle = BorderStyle.None;
                txtemail.BackColor = Color.FromArgb(247, 245, 241);
            }
        }

        private void txtphone_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter && txtprojectname.Enabled == false)
            {
                d1.RunQuery("update tbl_information set sSMS='" + txtphone.Text + "' where sProjectID='" + txtprojectname.Text + "'");
                MessageBox.Show("گزينه مورد نظر ويرايش شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtphone.BorderStyle = BorderStyle.None;
                txtphone.BackColor = Color.FromArgb(247, 245, 241);
            }
        }

        private void txtphone_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        private void newProject()
        {
            txtprojectname.Text = txtpdf.Text = txtversion.Text = txtwebsite.Text = txtphone.Text = txtemail.Text = txtbookname.Text = txtauthorname.Text = txtcontent.Text = "";
            txtversion.Text = "1.0.0";
            txtwebsite.Text = "http://www.";
            txtversion.BorderStyle = txtprojectname.BorderStyle = txtcontent.BorderStyle = txtbookname.BorderStyle = txtauthorname.BorderStyle = txtwebsite.BorderStyle = txtphone.BorderStyle = txtemail.BorderStyle = txtauthorname.BorderStyle = BorderStyle.FixedSingle;
            txtversion.BackColor = txtbookname.BackColor = txtcontent.BackColor = txtprojectname.BackColor = txtauthorname.BackColor = txtwebsite.BackColor = txtphone.BackColor = txtemail.BackColor = txtauthorname.BackColor = Color.White;
            txtprojectname.Enabled = true;
            btnchoosepdf.Enabled = true;
            imgicon.Image = Remote_Computer.Properties.Resources.icon;
            txttopic.Text = "";
           // txtbody.Text = "متن خود را اینجا بنویسید";
            treeView1.Nodes.Clear();
            img1_Click(null, null);
            btnready.Visible = true;
        }

        private void mnuurl_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            newProject();
        }

        private void btnaddgroup_Click(object sender, EventArgs e)
        {
            int p;

            if (treeView1.SelectedNode != null)
            {
                if (int.TryParse(treeView1.SelectedNode.Tag.ToString(), out p) == false)
                {
                    MessageBox.Show("شما نمیتوانید در خود موضوع دسته بندی ایجاد کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }
            }

            frminput f1 = new frminput(false);
            f1.sProjectName = Application.StartupPath + "\\" + sProjectName;
            f1.ShowDialog();

            try
            {
                if (f1.sChapter != "")
                {

                    int res = 0;

                    if (treeView1.Nodes.Count > 0)
                    {
                        if (treeView1.SelectedNode == null || f1.isRoot == true)
                        {
                            res = d1.AddCategory(f1.sChapter, "0");
                            treeView1.Nodes.Add(f1.sChapter).Tag = res;
                        }
                        else
                        {
                            res = d1.AddCategory(f1.sChapter, treeView1.SelectedNode.Tag.ToString());
                            TreeNode tn = treeView1.SelectedNode;
                            tn.Nodes.Add(f1.sChapter).Tag = res;
                        }
                    }
                    else
                    {
                        res = d1.AddCategory(f1.sChapter, "0");
                        treeView1.Nodes.Add(f1.sChapter).Tag = res;
                    }

                  
                    //treeView1.ExpandAll();

                }
            }

            catch (Exception)
            {
                MessageBox.Show("عملیات با خطا مواجه شد.دوباره تلاش کنید","خطا",MessageBoxButtons.OK,MessageBoxIcon.Exclamation);
            }

        }

        private void btnadd_Click(object sender, EventArgs e)
        {
            if (txttopic.Text == "" || txtbody.Text == "")
            {
                MessageBox.Show("لطفا گزينه ها را بدرستي وارد کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            TreeNode n1 = treeView1.SelectedNode;
            if (n1 == null)
            {
                MessageBox.Show("لطفا سرفصل را براي ثبت مطلب انتخاب کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            int p;
            if (int.TryParse(treeView1.SelectedNode.Tag.ToString(), out p) == false)
            {
                MessageBox.Show("لطفا دسته بندی را جهت ثبت موضوع انتخاب کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            d1 = new db(sProjectName);
            TreeNode topic = n1.Nodes.Add(txttopic.Text);
            topic.ForeColor = Color.Green;

            int res;
            string sType = "text";

            if (chksummary.Checked == true) sType = "summary";

            res = d1.AddTopic(txttopic.Text, txtbody.Text, treeView1.SelectedNode.Tag.ToString(), sRank, sType);
            topic.Tag = new string[] {res.ToString()};

            txttopic.Text = "";
           // txtbody.Text = "متن خود را اینجا بنویسید";

            img1_Click(null, null);
            sRank = "1";

            chksummary.Checked = false;
            MessageBox.Show("گزينه مورد نظر ثبت شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);

        }

        private void treeView1_KeyDown(object sender, KeyEventArgs e)
        {
            try
            {
                if (e.KeyCode == Keys.Delete)
                    if (treeView1.SelectedNode.Tag is string[])
                    {
                        if (MessageBox.Show("آيا مايل به حذف اين عنوان هستيد؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                        {
                            string[] id = (string[])treeView1.SelectedNode.Tag;
                            d1.DeleteTopic(id[0]);
                            treeView1.Nodes.Remove(treeView1.SelectedNode);
                            MessageBox.Show("گزينه مورد نظر حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                    }
                    else
                    {
                        if (MessageBox.Show("شما سرفصل را انتخاب کرده ايد\r\nاگر سرفصل را حذف کنيد همه مطالب حذف ميشوند\r\nآيا حذف ميکنيد؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                        {
                            string id = treeView1.SelectedNode.Tag.ToString();
                            d1.DeleteCategory(id);
                            treeView1.Nodes.Remove(treeView1.SelectedNode);
                            MessageBox.Show("گزينه مورد نظر حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                    }
            }
            catch (Exception)
            {
                MessageBox.Show("عملیات با خطا مواجه شد.دوباره تلاش کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }

        private void btndelete_Click(object sender, EventArgs e)
        {
            SendKeys.Send("delete");
            treeView1_KeyDown(null, null);
        }

        private void btnopenfile_Click(object sender, EventArgs e)
        {
            OpenFileDialog of1 = new OpenFileDialog();
            of1.CheckFileExists = true;
            of1.Filter = "*.txt|*.txt";
            of1.Title = "انتخاب فايل متني";
            of1.ShowDialog();
            if (of1.FileName != "")
                txtbody.Text = File.ReadAllText(of1.FileName);
        }

        private void mnuopen_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            frmlist l1 = new frmlist();
            l1.ShowDialog(this);
            if (l1.FormClose == false)
                if (l1.sProjectName != "" && l1.sProjectName != "لیست پروژه ها")
                    openProject(l1.sProjectName);
        }

        private void fillProductList()
        {
            //string titleProduct;
            //DataTable dtproduct = new DataTable();
            //dtproduct = d1.getRow("SELECT sTitle,sUrl FROM tbl_product");
            //tv1.Nodes.Clear();
            //for (int k = 0; k <= dtproduct.Rows.Count - 1; k++)
            //{
            //    titleProduct = dtproduct.Rows[k][0].ToString();
            //    tv1.Nodes.Add(titleProduct);
            //    TreeNode tn1 = tv1.SelectedNode = tv1.Nodes[k];
            //    tn1.Nodes.Add("آدرس دانلود :" + dtproduct.Rows[k][1].ToString());
            //}
            //tv1.ExpandAll();
        }

        private void openProject(string sprojectName)
        {

            int[] int1 = new int[2];

            newProject();
            sProjectName = sprojectName;
            DataTable dt1 = new DataTable();
            d1 = new db(sProjectName);
            //load product list
            fillProductList();
            //////////////////////////////////////////////////////////////////////////
            dt1 = d1.getRow("select * from tbl_information where sProjectID='" + sProjectName + "'");
            txtprojectname.Text = sProjectName.ToLower();
            try
            {
                txtbookname.Text = dt1.Rows[0]["sTitle"].ToString();
            }
            catch (Exception)
            {
                txtbookname.Text = "";
            }
 
    
            try
            {
                txtauthorname.Text = dt1.Rows[0]["sAuthor"].ToString();
            }
            catch (Exception)
            {
                txtauthorname.Text = "";
            }
            try
            {
                txtcontent.Text = dt1.Rows[0]["sContent"].ToString();
            }
            catch (Exception)
            {
                txtcontent.Text = "";
            }
            try
            {
                txtwebsite.Text = dt1.Rows[0]["sURL"].ToString();
            }
            catch (Exception)
            {
                txtwebsite.Text = "";
            }
            try
            {
                txtemail.Text = dt1.Rows[0]["sEmail"].ToString();
            }
            catch (Exception)
            {
                txtemail.Text = "";
            }
            try
            {
                txtphone.Text = dt1.Rows[0]["sSMS"].ToString();
            }
            catch (Exception)
            {
                txtphone.Text = "";
            }
            try
            {
                txtversion.Text = dt1.Rows[0]["sVersion"].ToString();
            }
            catch (Exception)
            {
                txtversion.Text = "1.0.0";
            }

            img1_Click(null, null);

            getLibFiles(sProjectName);

            if (File.Exists(Application.StartupPath + "\\" + sProjectName + "\\icon.png"))
            {
                loadImage(imgicon, "icon.png");
                sIconPath = Application.StartupPath + "\\" + sProjectName + "\\icon.png";
            }

            if (File.Exists(Application.StartupPath + "\\" + sProjectName + "\\cover.jpg"))
            {
                loadImage(picover, "cover.jpg");
            }

            if (File.Exists(Application.StartupPath + "\\" + sProjectName + "\\default.ttf"))
            {
               
            }

            if (File.Exists(Application.StartupPath + "\\" + sProjectName + "\\book.pdf"))
            {
                txtpdf.Text = Application.StartupPath + "\\" + sProjectName + "\\book.pdf";
            }

            startProject();
            btnready.Visible = false;
            tabContent.PageEnabled = true;
            tabLibrary.PageEnabled = true;
            tabProduct.PageEnabled = true;
            txtprojectname.Enabled = false;
            btnbrowse.Enabled = true;
            btncover.Enabled = true;
            tabmain.SelectedTabPage = tabSystem;
            this.Text = "کتابساز - " + sProjectName;
            d1 = new db(sProjectName);
            int1 = d1.SelectItems(treeView1);
            //lblcountchapter.Text = int1[0].ToString();
            //lblcounttopic.Text = int1[1].ToString();
            Show();
            //////////////////////////////////////////////////////////////////////////

            fillPrioratylist();
        }

        private void fillPrioratylist()
        {
            //try
            //{
            //    txtprioraty.Items.Clear();
            //    DataTable datatable1 = d1.getRow("SELECT sPriority FROM tbl_app GROUP BY sPriority ORDER BY sPriority");
            //    for (int i = 0; i <= datatable1.Rows.Count - 1; i++)
            //    {
            //        txtprioraty.Items.Add(datatable1.Rows[i][0].ToString());
            //    }
            //}
            //catch (Exception)
            //{

            //}
        }

        private void loadImage(PictureBox p1, string imgName)
        {
            if (File.Exists(Application.StartupPath + "\\" + sProjectName + "\\" + imgName))
            {
                FileStream stream = new FileStream(Application.StartupPath + "\\" + sProjectName + "\\" + imgName, FileMode.Open, FileAccess.Read);
                p1.Image = Image.FromStream(stream);
                stream.Close();
            }
        }

        private void mnunew_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            newProject();
            btnready.Visible = true;
            sRank = "1";
            img1_Click(null, null);
            this.Text = "کتابساز";
            tabContent.PageEnabled = false;
            tabLibrary.PageEnabled = false;
            tabProduct.PageEnabled = false;
            btnbrowse.Enabled = false;
            btncover.Enabled = true;

            txtprojectname.Enabled = true;

            try
            {
                txtemail.Text = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "Email", "").ToString();
            }
            catch (Exception)
            {

            } try
            {
                txtphone.Text = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "Phone", "").ToString();
            }
            catch (Exception)
            {

            } try
            {
                txtwebsite.Text = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "Url", "").ToString();
            }
            catch (Exception)
            {

            } try
            {
                txtauthorname.Text = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "Author", "").ToString();
            }
            catch (Exception)
            {

            }
            sProjectName = "";

        }

        private void img2_Click(object sender, EventArgs e)
        {
            img1.Image = Remote_Computer.Properties.Resources.favoris;
            img2.Image = Remote_Computer.Properties.Resources.favoris;
            img3.Image = Remote_Computer.Properties.Resources.favoris1;
            img4.Image = Remote_Computer.Properties.Resources.favoris1;
            img5.Image = Remote_Computer.Properties.Resources.favoris1;
            sRank = "2";
           
            try
            {
                string[] id = (string[])currentNode.Tag;
                d1.Update("sRate", sRank, id[0]);
            }
            catch (Exception)
            {

            }
        }

        private void img1_Click(object sender, EventArgs e)
        {
            img1.Image = Remote_Computer.Properties.Resources.favoris;
            img2.Image = Remote_Computer.Properties.Resources.favoris1;
            img3.Image = Remote_Computer.Properties.Resources.favoris1;
            img4.Image = Remote_Computer.Properties.Resources.favoris1;
            img5.Image = Remote_Computer.Properties.Resources.favoris1;
            sRank = "1";

            try
            {
                string[] id = (string[])currentNode.Tag;
                d1.Update("sRate", sRank, id[0]);
            }
            catch (Exception)
            {

            }
        }

        private void img3_Click(object sender, EventArgs e)
        {
            img1.Image = Remote_Computer.Properties.Resources.favoris;
            img2.Image = Remote_Computer.Properties.Resources.favoris;
            img3.Image = Remote_Computer.Properties.Resources.favoris;
            img4.Image = Remote_Computer.Properties.Resources.favoris1;
            img5.Image = Remote_Computer.Properties.Resources.favoris1;
            sRank = "3";

            try
            {
                string[] id = (string[])currentNode.Tag;
                d1.Update("sRate", sRank,id[0]);
            }
            catch (Exception)
            {

            }
        }

        private void img4_Click(object sender, EventArgs e)
        {
            img1.Image = Remote_Computer.Properties.Resources.favoris;
            img2.Image = Remote_Computer.Properties.Resources.favoris;
            img3.Image = Remote_Computer.Properties.Resources.favoris;
            img4.Image = Remote_Computer.Properties.Resources.favoris;
            img5.Image = Remote_Computer.Properties.Resources.favoris1;
            sRank = "4";
            
            try
            {
                string[] id = (string[])currentNode.Tag;
                d1.Update("sRate", sRank, id[0]);
            }
            catch (Exception)
            {

            }
        }

        private void img5_Click(object sender, EventArgs e)
        {
            img1.Image = Remote_Computer.Properties.Resources.favoris;
            img2.Image = Remote_Computer.Properties.Resources.favoris;
            img3.Image = Remote_Computer.Properties.Resources.favoris;
            img4.Image = Remote_Computer.Properties.Resources.favoris;
            img5.Image = Remote_Computer.Properties.Resources.favoris;
            sRank = "5";
            
            try
            {
                string[] id = (string[])currentNode.Tag;
                d1.Update("sRate", sRank, id[0]);
            }
            catch (Exception)
            {

            }
        }

        private void mnudelete_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (sProjectName == "" || sProjectName == null)
            {
                MessageBox.Show("پروژه اي براي حذف وجود ندارد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
            try
            {
                if (MessageBox.Show("آيا مطمئن به حذف اين پروژه هستيد؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    string project;
                    d1.stopConnect();
                    if (Directory.Exists(Application.StartupPath + "\\backup") == false)
                        Directory.CreateDirectory(Application.StartupPath + "\\backup");
                    d1.stopConnect();
                    project = sProjectName;
                    mnunew_LinkClicked(null, null);
                    myClass.DirectoryCopy(Application.StartupPath + "\\" + project, Application.StartupPath + "\\backup\\" + project, true);
                    myClass.clearFolder(Application.StartupPath + "\\" + project);
                    Directory.Delete(Application.StartupPath + "\\" + project);
                }
                else
                    return;
            }
            catch (Exception)
            {

            }
            MessageBox.Show("پروژه انتخاب شده حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        private void imgexit_MouseHover(object sender, EventArgs e)
        {
            try
            {
                PictureBox p1 = sender as PictureBox;
                p1.BorderStyle = BorderStyle.Fixed3D;
            }
            catch (Exception)
            {

            }
        }

        private void imgexit_MouseLeave(object sender, EventArgs e)
        {
            try
            {
                PictureBox p1 = sender as PictureBox;
                p1.BorderStyle = BorderStyle.None;
            }
            catch (Exception)
            {

            }
        }

        private void imgsearch_Click(object sender, EventArgs e)
        {
            PictureBox p1 = sender as PictureBox;
            of1.FileName = "";
            of1.Filter = "*.png|*.png";
            of1.ShowDialog();
            if (of1.FileName != "")
            {
                File.Copy(of1.FileName, Application.StartupPath + "\\" + sProjectName + "\\" + p1.Tag, true);
                p1.Image = Image.FromFile(of1.FileName);
            }
        }

        private void mnuquit_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (MessageBox.Show("آيا مايل به خروج هستيد؟", "خروج", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {
                Application.ExitThread();
                Application.ExitThread();
            }
        }

        private void mnusetting_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            tabmain.SelectedTabPageIndex = 3;
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            //System.Diagnostics.Process.Start("iexplore.exe", "http://www.iranapp.org");
        }

        private void mnucompile_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {

            DirectoryInfo di1 = new DirectoryInfo(Application.StartupPath + "\\tool\\output");

            if (d1 == null)
            {
                MessageBox.Show("پروژه اي براي تهيه خروجي وجود ندارد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }

            if (d1.countRecord() < 1)
            {
                MessageBox.Show("در حال حاظر مطلبي در کتاب وجود ندارد\r\nلطفا ابتدا مطالب را ايجاد سپس خروجي تهيه کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            try
            {
                if (di1.Exists == false)
                    Directory.CreateDirectory(Application.StartupPath + "\\tool\\output");
            }
            catch (Exception)
            {

            }
            ZipUtil.GrantAccess(Application.StartupPath);
            goCompile(Application.StartupPath + "\\" + sProjectName,"android");

        }

        private void backgroundWorker1_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            lblfile.Visible = false;
            lbl1.Visible = false;
            progressBar2.Visible = false;
            progressBar2.Value = 1;
            Enabled = true;
        }

        private void backgroundWorker1_DoWork(object sender, System.ComponentModel.DoWorkEventArgs e)
        {
            for (int i = 1; i <= 100; i++)
            {
                backgroundWorker1.ReportProgress(i);
                Thread.Sleep(10);
                if (i == 100 && completeCompile != true)
                {
                    i = 1;
                }
                if (completeCompile == true)
                {
                    output(currentFilename);
                    completeCompile = false;
                    break;
                }
            }
        }

        private void backgroundWorker1_ProgressChanged(object sender, System.ComponentModel.ProgressChangedEventArgs e)
        {
            progressBar2.Value = e.ProgressPercentage;
            lblfile.Text = currentCopyFile;
        }

        private string SelectPlatform()
        {
            frmplatform frmP = new frmplatform();
            frmP.ShowDialog();
            return frmP.PlatformType;
        }

        private void goCompile(string projectPath,string platform)
        {
            //string platform = SelectPlatform();

            if (platform == "")
                return;

            else if (platform == "online")
            {
                prepareFiles();
            }

            #region windows output
            else if (platform == "other")
            {
                compileBook();
                SaveFileDialog fd1 = new SaveFileDialog();
                fd1.Filter = "*.jar|*.jar";
                fd1.Title = "خروجي کتاب";
                fd1.ShowDialog();
                if (fd1.FileName != "")
                {
                    Enabled = false;
                    ZipUtil z1 = new ZipUtil();
                    z1.sProjectName = sProjectName;
                    File.Delete(fd1.FileName);
                    z1.sAuthors = txtauthorname.Text;
                    z1.sBookNames = txtbookname.Text;
                    z1.sContents = txtcontent.Text;
                    z1.sEmails = txtemail.Text;
                    z1.sWebsites = txtwebsite.Text;
                    z1.sPhones = txtphone.Text;
                    z1.sVersions = txtversion.Text;
                    z1.platformType = platform;

                    backgroundWorker1.RunWorkerAsync();
                    currentFilename = fd1.FileName;

                    string[] str_files = Directory.GetFiles(projectPath);
                    string[] files = new string[str_files.Length+2];

                    files[0] = Application.StartupPath + "\\" + sProjectName + "\\default.txt";
                    files[1] = Application.StartupPath + "\\" + sProjectName + "\\book_1.zip";
                    
                    for (int io = 0;io < str_files.Length ; io++)
                    {
                        files[io + 2] = str_files[io];
                    }

                    z1.files = files;

                    z1.sourceFile = fd1.FileName;
                    lblprogress.Visible = true;
                    progressbar1.Visible = true;
                    lblfile.Visible = true;
                    lbl1.Visible = true;
                    progressBar2.Visible = true;
                    progressBar2.Value = 1;

                    @File.Delete(Application.StartupPath + "\\" + sProjectName + "\\book_1.zip");

                    try
                    {
                        using (ZipFile zip = new ZipFile())
                        {
                            zip.AlternateEncoding = Encoding.UTF8;
                            zip.AddDirectory(Application.StartupPath + "\\" + sProjectName);
                            zip.Comment = "This zip was created at " + System.DateTime.Now.ToString("G");
                            zip.Save(Application.StartupPath + "\\" + sProjectName + "\\book_1.zip");
                        }
                    }
                    catch (Exception)
                    {
                        MessageBox.Show("متاسفانه کتابساز قادر به ادامه عملیات نیست\nبا مدیریت ارتباط برقرار کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        return;
                    }

                    //z1.files = Directory.GetFiles(projectPath);

                    string details = String.Format("{0}<>{1}<>{2}<>{3}<>{4}<>{5}<>{6}<>{7}<>{8}<>{9}<>{10}<>{11}", "1", "0", txtbookname.Text, "cover.jpg", txtauthorname.Text, "-", txtcontent.Text, "-", currentLanguage, "-", "pdf", DateTime.Today.ToString());
                    File.WriteAllText(Application.StartupPath + "\\" + sProjectName + "\\default.txt", details);

                    t1 = new Thread(new ThreadStart(z1.CrossPlatform));
                    t1.Start();
                }
            }
            #endregion

            else if (platform == "android")
            {
                if (File.Exists(Application.StartupPath + "\\tool\\book.apk") == false)
                {
                    MessageBox.Show("قالبی برای ساخت کتاب وجود ندارد\nلطفا قالبی را انتخاب و سپس کتاب تولید کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return;
                }
                try
                {
                    myClass.clearFolder(Application.StartupPath + "\\tool\\book");
                    Directory.Delete(Application.StartupPath + "\\tool\\book");
                }
                catch (Exception) { }

                compileBook();
                SaveFileDialog fd1 = new SaveFileDialog();
                fd1.Filter = "*.apk|*.apk";
                fd1.Title = "خروجي کتاب";
                fd1.ShowDialog();

                if (fd1.FileName != "")
                {
                    Enabled = false;
                    ZipUtil z1 = new ZipUtil();
                    z1.sProjectName = sProjectName;
                    File.Delete(fd1.FileName);
                    z1.sAuthors = txtauthorname.Text;
                    z1.sBookNames = txtbookname.Text;
                    z1.AppID = txtappid.Text;
                    z1.ClientID = txtclientid.Text;
                    z1.sContents = txtcontent.Text;
                    z1.sEmails = txtemail.Text;
                    z1.sWebsites = txtwebsite.Text;
                    z1.sPhones = txtphone.Text;
                    z1.sVersions = txtversion.Text;

                    backgroundWorker1.RunWorkerAsync();
                    currentFilename = fd1.FileName;

                    @File.Delete(Application.StartupPath + "\\" + sProjectName + "\\book_1.zip");

                    try
                    {
                        using (ZipFile zip = new ZipFile())
                        {
                            zip.AlternateEncoding = Encoding.UTF8;
                            zip.AddDirectory(Application.StartupPath + "\\" + sProjectName);
                            zip.Comment = "This zip was created at " + System.DateTime.Now.ToString("G");
                            zip.Save(Application.StartupPath + "\\" + sProjectName + "\\book_1.zip");
                        }
                    }
                    catch (Exception)
                    {
                        MessageBox.Show("متاسفانه کتابساز قادر به ادامه عملیات نیست\nبا مدیریت ارتباط برقرار کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        return;
                    }

                    //z1.files = Directory.GetFiles(projectPath);

                    string details = String.Format("{0}<>{1}<>{2}<>{3}<>{4}<>{5}<>{6}<>{7}<>{8}<>{9}<>{10}<>{11}", "1", "0", txtbookname.Text, "cover.jpg", txtauthorname.Text, "-", txtcontent.Text,"-",currentLanguage,"-","pdf",DateTime.Today.ToString());
                    File.WriteAllText(Application.StartupPath + "\\" + sProjectName + "\\default.txt", details);

                    z1.files = new string[] { Application.StartupPath + "\\" + sProjectName + "\\book_1.zip", Application.StartupPath + "\\" + sProjectName + "\\default.txt" };

                    z1.sourceFile = fd1.FileName;
                    lblprogress.Visible = true;
                    progressbar1.Visible = true;
                    lblfile.Visible = true;
                    lbl1.Visible = true;
                    progressBar2.Visible = true;
                    progressBar2.Value = 1;
                    t1 = new Thread(new ThreadStart(z1.CompressFile));
                    t1.Start();
                }
            }
        }

        private void prepareFiles()
        {
            if (Directory.Exists(Application.StartupPath + "\\temp") == false)
                try
                {
                    Directory.CreateDirectory(Application.StartupPath + "\\temp");
                }
                catch (Exception)
                {
                }
            
            try
            {
                myClass.clearFolder(Application.StartupPath + "\\temp");
            }
            catch (Exception) { }

            string temp = Application.StartupPath + "\\temp";
            string[] libfiles;
            string[] files = Directory.GetFiles(Application.StartupPath + "\\" + sProjectName);

            if (Directory.Exists(Application.StartupPath + "\\" + sProjectName + "\\lib"))
            {
                libfiles = Directory.GetFiles(Application.StartupPath + "\\" + sProjectName + "\\lib");

                foreach (string file1 in libfiles)
                {
                    string filename = Path.GetFileName(file1);
                    try
                    {
                        if (filename.ToLower().IndexOf("thumbs.db") > -1 || filename.ToLower().IndexOf("ds_store") > -1) continue;
                        File.Copy(file1, temp + "\\" + filename, true);
                    }
                    catch (Exception) { }
                }
            }

            freeID = d1.getProjectID();
            currentZipName = "temp.zip";

            try
            {
                using (ZipFile zip = new ZipFile())
                {
                    zip.AlternateEncoding = Encoding.UTF8;
                    zip.AddDirectory(Application.StartupPath + "\\" + sProjectName);
                    zip.Comment = "This zip was created at " + System.DateTime.Now.ToString("G");
                    zip.Save(Application.StartupPath + "\\temp.zip");
                }
            }
            catch (Exception)
            {
                MessageBox.Show("متاسفانه کتابساز قادر به ادامه عملیات نیست\nبا مدیریت ارتباط برقرار کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            Enabled = false;
            pg.setComment("در حال آپلود فایل...");
            pg.setTitle("حجم : " + (new FileInfo(Application.StartupPath + "\\" + currentZipName).Length / 1024).ToString() + " کیلوبایت");
            pg.Show(this);
            Application.DoEvents();
            bgupload.RunWorkerAsync();

        }

        private void bgupload_DoWork(object sender, DoWorkEventArgs e)
        {

            string id;

            if (freeID.Length == 0)
            {
                id = myClass.registerBook(txtbookname.Text, currentCategoryBook, txtauthorname.Text, txtcontent.Text, (new FileInfo(Application.StartupPath + "\\temp.zip").Length / 1024).ToString(), currentLanguage, "cover", myClass.Username, myClass.Password, "", "0");
                freeID = id;

            }
            else
            {
                id = myClass.registerBook(txtbookname.Text, currentCategoryBook, txtauthorname.Text, txtcontent.Text, (new FileInfo(Application.StartupPath + "\\temp.zip").Length / 1024).ToString(), currentLanguage, "cover", myClass.Username, myClass.Password, freeID, "1");
                id = freeID;
            }

            FTP ftpClient = new FTP(myClass.FTP, myClass.Username + "@" + myClass.Domain, myClass.Password);
            ftpClient.upload("book_" + id + ".zip", Application.StartupPath + "\\temp.zip");

            if (File.Exists(Application.StartupPath + "\\" + sProjectName + "\\cover.jpg"))
            {
                ftpClient.upload("cover/cover_" + id + ".jpg", Application.StartupPath + "\\" + sProjectName + "\\cover.jpg");
            }

        }

        private void bgupload_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            Enabled = true;
            pg.Hide();

            d1.updateIDProject(freeID);

            try { File.Delete(Application.StartupPath + "\\" + currentZipName); }
            catch (Exception) { }

            MessageBox.Show("کتاب با موفقیت در سرور قرار گرفت", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);

        }

        private void output(string sFilename)
        {
            try
            {
                File.Copy(Application.StartupPath + "\\tool\\output\\book.apk", sFilename);
                File.Delete(Application.StartupPath + "\\tool\\output\\book.apk");
            }
            catch (Exception)
            {

            }

            lblprogress.Visible = false;
            progressbar1.Visible = false;
            t1.Abort();

            MessageBox.Show("کتاب با موفقيت ايجاد شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
            System.Diagnostics.Process.Start("explorer.exe", Path.GetDirectoryName(sFilename));
        }

        private void ni1_MouseDown(object sender, MouseEventArgs e)
        {
            Show();
            Activate();
        }

        private void button9_Click(object sender, EventArgs e)
        {
            TreeNode parent1;
            TreeNode t1;
            if (treeView1.SelectedNode.Level == 0)
            {
                MessageBox.Show("قابليت جابجايي سرفصل ها وجود ندارد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
            try
            {
                t1 = treeView1.SelectedNode;
                parent1 = treeView1.SelectedNode.Parent;
                if (treeView1.Nodes[parent1.Index + 1] != null)
                {
                    treeView1.SelectedNode.Remove();
                    treeView1.Nodes[parent1.Index + 1].Nodes.Add(t1);
                    d1.Update(t1.Text, treeView1.Nodes[parent1.Index + 1].Text);
                }
            }
            catch (Exception)
            {
                MessageBox.Show("سرفصلي براي جابجايي اين گزينه وجود ندارد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

        }

        private void button10_Click(object sender, EventArgs e)
        {
            TreeNode t1;
            TreeNode parent1;
            if (treeView1.SelectedNode.Level == 0)
            {
                MessageBox.Show("قابليت جابجايي سرفصل ها وجود ندارد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
            try
            {
                t1 = treeView1.SelectedNode;
                parent1 = treeView1.SelectedNode.Parent;
                if (treeView1.Nodes[parent1.Index - 1] != null)
                {
                    treeView1.SelectedNode.Remove();
                    treeView1.Nodes[parent1.Index - 1].Nodes.Add(t1);
                    d1.Update(t1.Text, treeView1.Nodes[parent1.Index - 1].Text);
                }
            }
            catch (Exception)
            {
                MessageBox.Show("سرفصلي براي جابجايي اين گزينه وجود ندارد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
        }

        private void treeView1_AfterSelect(object sender, TreeViewEventArgs e)
        {
            currentNode = e.Node;

            try
            {
                if (e.Node.Level != 0)
                {
                    txttopic.Text = e.Node.Text;
                    string[] id = (string[]) e.Node.Tag;
                    txtbody.Text = d1.getBody(id[0]).Replace("-", "'");
                    switch (d1.getField("sRate", id[0]))
                    {
                        case "1":
                            img1_Click(null, null);
                            break;
                        case "2":
                            img2_Click(null, null);
                            break;
                        case "3":
                            img3_Click(null, null);
                            break;
                        case "4":
                            img4_Click(null, null);
                            break;
                        case "5":
                            img5_Click(null, null);
                            break;
                    }
                }
                else
                {

                }
                selectNode = treeView1.SelectedNode;
                lblnode.Text = selectNode.Text;
            }
            catch (Exception)
            {

            }
        }

        private void button11_Click(object sender, EventArgs e)
        {
            if (treeView1.SelectedNode == null)
            {
                MessageBox.Show("لطفا موضوع را انتخاب سپس ذخيره کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            if (treeView1.SelectedNode.Tag is string[])
            {
            }
            else
            {
                MessageBox.Show("لطفا فقط موضوع را انتخاب و ویرایش کنید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            string[] id = (string[])treeView1.SelectedNode.Tag;

            if (txttopic.Text.Length == 0)
            {
                MessageBox.Show("موضوعی برای ذخیره تغیرات انتخاب نشده است", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
            try
            {
                d1.Update("sText", txtbody.Text.Replace("'", "-"),id[0]);
                MessageBox.Show("تغييرات ذخيره شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            catch (Exception)
            {
                MessageBox.Show("موضوعی برای ذخیره تغیرات انتخاب نشده است", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
        }

        private void button12_Click(object sender, EventArgs e)
        {
            KeyEventArgs k1 = new KeyEventArgs(Keys.Delete);
            treeView1_KeyDown(null, k1);
        }

        private void mnuchapter_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            btnaddgroup_Click(null, null);
        }

        private void compileBook()
        {
            ZipUtil z1 = new ZipUtil();
        }

        private void frmmain_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (myClass.blnUpdate == true)
            {
                Application.ExitThread();
                Application.ExitThread();
                return;
            }
            e.Cancel = true;
            mnuquit_LinkClicked(null, null);
        }

        private void button7_Click(object sender, EventArgs e)
        {
            txttopic.Text = "";
            txtbody.Text = "متن خود را اینجا بنویسید";
        }

        private void frmmain_Load(object sender, EventArgs e)
        {
            string TypeBook,first,chkState;

            try
            {
                TypeBook = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "typebook", "0").ToString();
            }
            catch (Exception)
            {
                TypeBook = "0";
            }

            try
            {
                txtappid.Text = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "appid", "").ToString();
            }
            catch (Exception)
            {
                txtappid.Text = "";
            }

            try
            {
                txtclientid.Text = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "clientid", "").ToString();
            }
            catch (Exception)
            {
                txtclientid.Text = "";
            }

            try
            {
                chkcache.Checked = Convert.ToBoolean(Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "cache", "false").ToString());
            }
            catch (Exception)
            {
                chkcache.Checked = false;
            }

            try
            {
                first = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "firsttime", "0").ToString();
            }
            catch (Exception)
            {
                first = "0";
            }

            if (first == "0")
            {
                MessageBox.Show("کاربر گرامی وارد فروشگاه قالب شده و قالب های مورد نظر را دریافت کنید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                Microsoft.Win32.Registry.SetValue("HKEY_CURRENT_USER\\Student", "firsttime", "1");
            }

            if (TypeBook == "0")
                 cmstate.SelectedIndex = 0;
            else
                 cmstate.SelectedIndex = 1;

            try
            {
                chkState = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "startup", "false").ToString();
            }
            catch (Exception)
            {
                chkState = "false";
            }
            

            if (chkState.ToLower() == "true")
            {
                string project = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "project", "").ToString();
                
                if (project != "" && new DirectoryInfo(Application.StartupPath + "\\" + project).Exists == true)
                    openProject(project);
            }
           // txtbody.Text = "متن مطلب را اين قسمت وارد کنيد";
            bwcheckinternet.RunWorkerAsync();
        }

        void ChecknewVersion()
        {
            //try
            //{
            //    if (canUpdate == false)
            //    {
            //        string sres = myClass.ExecuteHttp();
            //        string[] data = sres.Split(new string[] { "<!>" }, StringSplitOptions.None);
            //        if (Convert.ToInt16(data[0].Replace(".", "")) > myClass.getAppVersion())
            //        {
            //            alarm a1 = new alarm();
            //            a1.Changelog = data[2];
            //            a1.Version = data[0];
            //            a1.FileUrl = data[1];
            //            a1.ShowDialog();
            //        }
            //    }
            //}
            //catch (Exception)
            //{
            //}
        }

        void NetworkChange_NetworkAvailabilityChanged(object sender, NetworkAvailabilityEventArgs e)
        {
            //try
            //{
            //    if (e.IsAvailable == true)
            //    {
            //        picmahak.Visible = false;
            //        AD.Visible = true;
            //        try
            //        {
            //            AD.Load("http://www.iranapp.org/asset/images/AD.gif");
            //        }
            //        catch (Exception)
            //        {

            //        }
            //        ChecknewVersion();
            //    }
            //    else
            //    {
            //        picmahak.Visible = true;
            //        AD.Visible = false;
            //    }
            //}
            //catch (Exception)
            //{
            //    //piclogo.Visible = true;
            //    //picmahak.Visible = true;
            //}
        }

        private string GetJavaInstallationPath()
        {
            string home;
            try
            {
                string environmentPath = Environment.GetEnvironmentVariable("JAVA_HOME");
                string javaKey = "SOFTWARE\\JavaSoft\\Java Development Kit\\";

                if (!string.IsNullOrEmpty(environmentPath))
                {
                    return environmentPath;
                }

                using (Microsoft.Win32.RegistryKey rk = Microsoft.Win32.Registry.LocalMachine.OpenSubKey(javaKey))
                {
                    string currentVersion = rk.GetValue("CurrentVersion").ToString();
                    using (Microsoft.Win32.RegistryKey key = rk.OpenSubKey(currentVersion))
                    {
                        home = key.GetValue("JavaHome").ToString();
                    }
                }

                if (home == null)
                {
                    using (Microsoft.Win32.RegistryKey rk = Microsoft.Win32.Registry.CurrentUser.OpenSubKey(javaKey))
                    {
                        string currentVersion = rk.GetValue("CurrentVersion").ToString();
                        using (Microsoft.Win32.RegistryKey key = rk.OpenSubKey(currentVersion))
                        {
                            home = key.GetValue("JavaHome").ToString();
                        }
                    }
                }
                return home;
            }
            catch (Exception)
            {
                return "";
            }
        }

        private void button13_Click(object sender, EventArgs e)
        {
            try
            {
                if (treeView1.SelectedNode.Level == 0)
                {
                    OpenFileDialog of1 = new OpenFileDialog();
                    of1.CheckFileExists = true;
                    of1.Filter = "Standard Format png|*.png";
                    of1.Title = "تصوير سرفصل";
                    of1.FileName = "";
                    of1.ShowDialog();
                    if (of1.FileName != "")
                    {
                        try
                        {
                            Bitmap b1 = new Bitmap(of1.FileName);
                        }
                        catch (Exception)
                        {
                            MessageBox.Show("اندازه تصوير انتخاب شده درست نيست", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                            return;
                        }
                        pictureBox4.Image = Image.FromFile(of1.FileName);
                        System.IO.File.Copy(of1.FileName, Application.StartupPath + "\\" + sProjectName + "\\chapter_" + myClass.CalculateMD5Hash(treeView1.SelectedNode.Text, true) + ".png", true);
                        MessageBox.Show("تصوير جديد براي سرفصل تعين شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
            }
            catch (Exception)
            {
                MessageBox.Show("لطفا عنوان سرفصل را براي تغيير تصوير انتخاب کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void button8_Click(object sender, EventArgs e)
        {
            try
            {
                if (treeView1.SelectedNode.Level == 0)
                {
                    File.Delete(Application.StartupPath + "\\" + sProjectName + "\\chapter_" + myClass.CalculateMD5Hash(treeView1.SelectedNode.Text, true) + ".png");
                    MessageBox.Show("تصوير سرفصل مورد نظر حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    pictureBox4.Image = null;
                }
            }
            catch (Exception)
            {

            }
        }

        private void txtversion_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter && txtprojectname.Enabled == false)
            {
                d1.RunQuery("update tbl_information set sVersion='" + txtversion.Text + "' where sProjectID='" + txtprojectname.Text + "'");
                MessageBox.Show("گزينه مورد نظر ويرايش شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtauthorname.BorderStyle = BorderStyle.None;
                txtauthorname.BackColor = Color.FromArgb(247, 245, 241);
            }
        }

        private void btnaudio_Click(object sender, EventArgs e)
        {
            /*if (btnaudio.Enabled == false) return;

            if (sProjectName == "" || sProjectName == null)
            {
                MessageBox.Show("پروژه اي براي تغييرات وجود ندارد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }

            OpenFileDialog of1 = new OpenFileDialog();
            of1.CheckFileExists = true;
            of1.Filter = "*.mp3|*.mp3";
            of1.Title = "انتخاب آهنگ زمينه";
            of1.ShowDialog();
            if (of1.FileName != "")
            {
                double size = new FileInfo(of1.FileName).Length / 1024;
                if (size > 700)
                {
                    MessageBox.Show("حجم آهنگ زمينه کتاب نبايد بيشتر از 700 کيلوبايت باشد\r\nاين امر باعث کندي در عملکرد کتاب ميشود", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }
                sAudioPath = of1.FileName;
                btnplay.Enabled = true;
                if (txtprojectname.Enabled == false)
                {
                    wplayer.close();
                    File.Delete(Application.StartupPath + "\\" + sProjectName + "\\bg.mp3");
                    @File.Copy(of1.FileName, Application.StartupPath + "\\" + sProjectName + "\\bg.mp3", true);
                    btnremoveaudio.Visible = true;
                    MessageBox.Show("اهنگ پس زمينه اعمال شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }*/
        }

        private void btnplay_Click(object sender, EventArgs e)
        {
            /*
            if (sAudioPath != "")
                if (sPlayAudio == false)
                {
                    wplayer.URL = Application.StartupPath + "\\" + sProjectName + "\\bg.mp3";
                    btnplay.BackgroundImage = Remote_Computer.Properties.Resources.stop;
                    wplayer.controls.play();
                    sPlayAudio = true;
                }
                else
                {
                    wplayer.controls.stop();
                    wplayer.controls.pause();
                    sPlayAudio = false;
                    btnplay.BackgroundImage = Remote_Computer.Properties.Resources.play;
                }
             * */
        }

        private void button8_Click_2(object sender, EventArgs e)
        {
            /*
            sAudioPath = "";
            btnplay.Enabled = false;
            btnremoveaudio.Visible = false;
            try
            {
                File.Delete(Application.StartupPath + "\\" + sProjectName + "\\bg.mp3");
            }
            catch (Exception)
            {

            }
            btnplay.Image = Remote_Computer.Properties.Resources.play;
            sPlayAudio = false;*/
        }

        private void navBarItem13_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {

        }

        private void tabmain_Click(object sender, EventArgs e)
        {

        }

        private void mnudemo_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (d1 == null)
            {
                MessageBox.Show("پروژه اي براي تهيه خروجي وجود ندارد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            if (d1.countRecord() < 1)
            {
                MessageBox.Show("در حال حاظر مطلبي در کتاب وجود ندارد\r\nلطفا ابتدا مطالب را ايجاد سپس خروجي تهيه کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            try
            {
                if (Directory.Exists(Application.StartupPath + "\\demo") == false)
                    Directory.CreateDirectory(Application.StartupPath + "\\demo");
            }
            catch (Exception)
            {

            }
            myClass.clearFolder(Application.StartupPath + "\\demo");
            myClass.DirectoryCopy(Application.StartupPath + "\\" + sProjectName, Application.StartupPath + "\\demo", true);
            changeDB2Demo(Application.StartupPath + "\\demo");
            goCompile(Application.StartupPath + "\\demo","android");
            Enabled = true;
        }

        private void changeDB2Demo(string path)
        {
            db db1 = new db("demo");
            string limit = "";
            DataTable dataTable1;
            int countGroup;
            dataTable1 = db1.getRow("select sGroupName,COUNT(*) as sGroupCount from tbl_app GROUP BY sGroupName");
            for (int i = 0; i <= dataTable1.Rows.Count - 1; i++)
            {
                countGroup = Convert.ToInt32(dataTable1.Rows[0][1]) / 3;
                if (countGroup > 0)
                    limit = " limit " + countGroup;
                db1.RunQuery("delete from tbl_app where sID in(select sID from tbl_app where sGroupName ='" + dataTable1.Rows[0][0].ToString() + "' " + limit + ")");
            }
            string title = db1.getFieldInformation("sTitle") + " - آزمايشي";
            db1.RunQuery("update tbl_information set sTitle = '" + title + "'");
        }

        private void btnrename_Click(object sender, EventArgs e)
        {
            mnuchangename_Click(null, null);
        }

        private void btnlocation_Click(object sender, EventArgs e)
        {
            System.Diagnostics.Process.Start("explorer.exe", Application.StartupPath);
        }

        private void btnmax_Click(object sender, EventArgs e)
        {
            if (maximize == false)
            {
                txtbody.Location = new Point(3, 15);
                txtbody.Size = new Size(663, 474);
                btnmax.Location = new Point(10, 19);
                btnmax.Size = new Size(24, 24);
                maximize = true;
                btnmax.BackgroundImage = Remote_Computer.Properties.Resources.min;
                treeView1.Visible = false;
            }
            else
            {
                txtbody.Location = new Point(203, 142);
                txtbody.Size = new Size(455, 307);
                btnmax.Size = new Size(29, 29);
                btnmax.Location = new Point(265, 81);
                maximize = false;
                btnmax.BackgroundImage = Remote_Computer.Properties.Resources.max;
                treeView1.Visible = true;
            }
        }

        private void btnaddproduct_Click(object sender, EventArgs e)
        {
            if (txtproductitle.Text.Length < 2 || txtproducturl.Text.Length < 8 || txtpicture.Text == "")
            {
                MessageBox.Show("لطفا فيلد هاي لازم را پر کنيد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }
            Regex re = new Regex(@"^http://www\..+\.\w{2,6}$", RegexOptions.IgnoreCase | RegexOptions.Multiline);
            Match m = re.Match(txtproducturl.Text);
            if (m.Groups.Count == 0)
            {
                MessageBox.Show("فرمت آدرس وارد شده معتبر نيست", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }
            d1.RunQuery("INSERT INTO tbl_product(sTitle,sUrl) VALUES('" + txtproductitle.Text + "','" + txtproducturl.Text + "')");
            int retID = d1.getLastInsertID();
            File.Copy(txtpicture.Text, Application.StartupPath + "\\" + sProjectName + "\\product_" + retID + Path.GetExtension(txtpicture.Text), true);

            //ready for new item
            tv1.ExpandAll();
            txtproducturl.Text = "http://www.";
            picproduct.Image = Remote_Computer.Properties.Resources.product;
            txtproductitle.Text = "";
            txtpicture.Text = "";
            fillProductList();
            picproduct.Image = Remote_Computer.Properties.Resources.product;
            MessageBox.Show("محصول جديد در کتاب ثبت شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        private void tv1_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Delete)
            {
                if (tv1.SelectedNode.Level == 1)
                {
                    MessageBox.Show("براي حذف گزينه مورد نظر،عنوان را انتخاب سپس حذف کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }
                if (MessageBox.Show("آيا مايل به حذف اين عنوان هستيد؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    string[] str1 = new string[2];
                    string[] str2 = new string[1];
                    str1 = tv1.SelectedNode.Text.Split(new string[] { "vs" }, StringSplitOptions.None);
                    object id = d1.QueryScaler("SELECT sID FROM tbl_product WHERE sTitle ='" + str1[0].Trim() + "'");
                    d1.RunQuery("DELETE FROM tbl_product WHERE sTitle ='" + str1[0].Trim() + "'");
                    try
                    {
                        File.Delete(Application.StartupPath + "\\" + sProjectName + "\\product_" + id.ToString() + ".png");
                    }
                    catch
                    {

                    }
                    tv1.Nodes.Remove(tv1.SelectedNode);
                    MessageBox.Show("گزينه مورد نظر حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }
        }

        private void txtversion_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsDigit(e.KeyChar) && e.KeyChar != '' && e.KeyChar != '.') e.Handled = true;
        }

        private void btnreport_Click(object sender, EventArgs e)
        {
            string[] rs = d1.statistic();
            MessageBox.Show(String.Format("تعداد سرفصل هاي ثبت شده : {0}\n" + "تعداد تاپيک هاي ثبت شده : {1}", rs[0], rs[1]), "آمار", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            FontDialog fontdialog1 = new FontDialog();
            fontdialog1.ShowDialog();
        }

        private void btnproductpic_Click(object sender, EventArgs e)
        {

        }

        private void btnchangepicture_Click(object sender, EventArgs e)
        {
            of1.FileName = "";
            of1.Filter = "Starndard Format png|*.png";
            of1.ShowDialog();
            if (of1.FileName != "")
            {
                try
                {
                    Bitmap b1 = new Bitmap(of1.FileName);
                    if (b1.Width != b1.Height)
                    {
                        MessageBox.Show("طول تصویر باید با عرض تصویر یسکان باشد", "اخطار", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        return;
                    }
                    if (b1.Width > 128)
                    {
                        MessageBox.Show("اندازه تصویر نباید بیشتر از 128 پیکسل باشد", "اخطار", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        return;
                    }
                    picproduct.Image = Image.FromFile(of1.FileName);
                    txtpicture.Text = of1.FileName;
                }
                catch (Exception)
                {
                    txtpicture.Text = "";
                }
            }
        }

        private void tv1_AfterSelect(object sender, TreeViewEventArgs e)
        {
            if (tv1.SelectedNode.Level == 0)
            {
                object id = d1.QueryScaler("SELECT sID FROM tbl_product WHERE sTitle ='" + e.Node.Text.Trim() + "'");
                try
                {
                    picproduct.Image = Image.FromFile(Application.StartupPath + "\\" + sProjectName + "\\product_" + id.ToString() + ".png");
                }
                catch
                {

                }

            }
        }

        //private void btnsavehelp_Click(object sender, EventArgs e)
        //{
        //    if (txthelp.Text.Length < 50)
        //    {
        //        MessageBox.Show("لطفا فيلد هاي لازم را پر کنيد\nحداقل بايد 50 کاراکتر باشد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
        //        return;
        //    }
        //    d1.RunQuery("update tbl_information set sHelp='" + txthelp.Text + "'");
        //    MessageBox.Show("راهنماي کتابچه ذخيره شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
        //}

        private void mnuchangename_Click(object sender, EventArgs e)
        {
            try
            {
                if (treeView1.SelectedNode.Level == 0)
                {
                    if (MessageBox.Show("آيا مايل به تغيير مشخصات سرفصل هستيد؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                    {
                        string chapter = treeView1.SelectedNode.Text;
                        frminput f1 = new frminput(true);
                        f1.sProjectName = sProjectName;
                        f1.SetChapterName = treeView1.SelectedNode.Text;
                        f1.ShowDialog();

                        if (f1.sChapter != "")
                        {
                            treeView1.SelectedNode.Text = f1.sChapter;
                            d1.RunQuery("update tbl_app set sGroupName ='" + f1.sChapter + "' where sGroupName ='" + chapter + "'");
                            MessageBox.Show("سرفصل انتخاب شده تغيير نام داده شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                    }
                }
            }
            catch (Exception)
            {
                MessageBox.Show("لطف سرفصل مورد نظر را براي تغيير نام انتخاب کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }

        private void btnrename_MouseDown(object sender, MouseEventArgs e)
        {
            //mnuchapters.Show(btnrename, e.X, e.Y);
        }

        private void button14_Click(object sender, EventArgs e)
        {
            of1.Filter = "APK|*.apk";
            of1.Title = "انتخاب کتاب";
            if (of1.ShowDialog() == DialogResult.OK && of1.FileName != "")
            {
                try
                {
                    if (File.Exists(Application.StartupPath + "\\tool\\bak_book.apk") == false)
                        File.Copy(Application.StartupPath + "\\tool\\book.apk", Application.StartupPath + "\\tool\\bak_book.apk", true);
                    File.Delete(Application.StartupPath + "\\tool\\book.apk");
                }
                catch
                {

                }
                File.Copy(of1.FileName, Application.StartupPath + "\\tool\\book.apk");
                MessageBox.Show("کتاب ويرايش شده جايگزين کتاب پيش فرض شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void button15_Click(object sender, EventArgs e)
        {
            try
            {
                if (File.Exists(Application.StartupPath + "\\tool\\bak_book.apk"))
                {
                    File.Delete(Application.StartupPath + "\\tool\\book.apk");
                    File.Copy(Application.StartupPath + "\\tool\\bak_book.apk", Application.StartupPath + "\\tool\\book.apk");
                    MessageBox.Show("کتاب پيش فرض بازيابي شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }
            catch (Exception)
            {

            }
        }

        private void btnfont_Click(object sender, EventArgs e)
        {
            of1.FileName = "";
            of1.Filter = "*.ttf|*.ttf";
            of1.ShowDialog();
            if (of1.FileName != "")
            {
                try
                {
                    File.Copy(of1.FileName, Application.StartupPath + "\\" + sProjectName + "\\BNazanin.ttf", true);
                    MessageBox.Show("فونت جديد بروز شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                catch (Exception)
                {

                }
            }
        }

        private string WindowsDirectory()
        {
            uint size = 0;
            size = GetWindowsDirectory(null, size);

            StringBuilder sb = new StringBuilder((int)size);
            GetWindowsDirectory(sb, size);

            return sb.ToString();
        }

        private void pictureBox69_Click(object sender, EventArgs e)
        {
            System.Diagnostics.Process.Start("http://www.mahak-charity.org/main/fa");
        }

        private void pictureBox10_Click(object sender, EventArgs e)
        {
            System.Diagnostics.Process.Start("http://www.iranapp.org");
        }

        private void mnuduplicate_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (sProjectName == "" || sProjectName == null)
            {
                MessageBox.Show("پروژه اي در حال حاضر فعال نيست", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            SaveFileDialog backup = new SaveFileDialog();
            backup.Filter = "Zip File |*.zip";
            backup.CheckPathExists = true;
            backup.FileName = sProjectName + ".zip";

            if (backup.ShowDialog() == DialogResult.OK && backup.FileName.Length > 0)
            {
                try
                {
                    using (Ionic.Zip.ZipFile zip = new Ionic.Zip.ZipFile())
                    {
                        zip.AlternateEncoding = Encoding.UTF8;
                        zip.AddDirectory(Application.StartupPath + "\\" + sProjectName);
                        zip.Comment = "This zip was created at " + System.DateTime.Now.ToString("G");
                        zip.Save(backup.FileName);
                    }
                    MessageBox.Show("پشتيباني با موفقيت انجام شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                catch (Exception)
                {
                    MessageBox.Show("متاسفانه کتابساز با خطا مواجه شد.دوباره تلاش کنید","خطا",MessageBoxButtons.OK,MessageBoxIcon.Warning);
                }
            }
        }

        private void btnimport_Click(object sender, EventArgs e)
        {
            of1.Filter = "*.doc*.docx|*.doc;*.docx";
            of1.FileName = "";
            of1.Title = "فايل ورد را انتخاب کنيد";
            if (of1.ShowDialog() == DialogResult.OK && of1.FileName != "")
            {
                try
                {
                    if (new DirectoryInfo(Application.StartupPath + "\\temp").Exists == false)
                        Directory.CreateDirectory(Application.StartupPath + "\\temp");
                    Cursor = Cursors.WaitCursor;
                    myClass.SaveHtml(of1.FileName, Application.StartupPath + "\\temp\\temp.html");
                    txtbody.Text = File.ReadAllText(Application.StartupPath + "\\temp\\temp.html");
                    Cursor = Cursors.Arrow;
                }
                catch (IOException)
                {
                    MessageBox.Show("فايل انتخاب شده در حال حاضر باز ميباشد\nلطفا فايل را بسته سپس انتخاب کنيد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                catch (Exception)
                {
                    MessageBox.Show("فايل شما معتبر نيست", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }

        private void cmtime_SelectedIndexChanged(object sender, EventArgs e)
        {
            //myClass.setValueRegistery("Time", cmtime.Text);
        }

        private void btnrtl_Click(object sender, EventArgs e)
        {
            txtbody.Text = "<DIV dir=rtl>" + txtbody.Text + "</DIV>";
        }

        private void btnupdate_Click(object sender, EventArgs e)
        {
            //canUpdate = true;
            ChecknewVersion();
        }

        private void lblaudio_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            if (sProjectName == "" || sProjectName == null)
            {
                MessageBox.Show("پروژه اي براي تغييرات وجود ندارد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            OpenFileDialog of1 = new OpenFileDialog();
            of1.CheckFileExists = true;
            of1.Filter = "*.mp3|*.mp3";
            of1.Title = "انتخاب صداي کليک";
            of1.ShowDialog();
            if (of1.FileName != "")
            {
                double size = new FileInfo(of1.FileName).Length / 1024;
                if (size > 700)
                {
                    MessageBox.Show("حجم فايل جهت صداي کليک ها بايد کمتر از 700 کيلوبايت باشد\r\nاينکار جهت افزايش سرعت کتابچه پيشنهاد ميشود", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }
                if (txtprojectname.Enabled == false)
                {
                    @File.Copy(of1.FileName, Application.StartupPath + "\\" + sProjectName + "\\b.mp3", true);
                    MessageBox.Show("صداي مورد نظر شما جايگزين صداي پيش فرض کتابچه شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }
        }

        private void txtprioraty_SelectedIndexChanged(object sender, EventArgs e)
        {
            //if (treeView1.SelectedNode.Level != 0)
            //{
            //    MessageBox.Show("جهت اوليت بندي،سرفصل مورد نظر را بايد انتخاب کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            //    return;
            //}
            //else
            //{
            //    if (currentNodePrioraty == "")
            //    {
            //        MessageBox.Show("سرفصلي جهت تغيير اولويت انتخاب نشده است", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            //        return;
            //    }
            //    else
            //    {
            //        d1.RunQuery("UPDATE tbl_app SET sPriority = '" + txtprioraty.Text + "' WHERE sGroupName ='" + currentNodePrioraty + "'");
            //        currentNodePrioraty = "";
            //    }
            //}
        }

        private void btnlibrary_Click(object sender, EventArgs e)
        {
            if (sProjectName == "" || sProjectName == null)
            {
                MessageBox.Show("پروژه اي براي تغييرات وجود ندارد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            of1.Title = "اضافه کردن فايل به کتابخانه کتاب";
            of1.Multiselect = true;
            of1.Filter = "All Files |*.*|Video File | *.avi;*.3gp;*.mkv;*.avi;*.mp4;*.tts |Audio File |*.mp3;*.wav;*.mid;*.ogg|Image File | *.jpg;*.png;*.gif |Ebook |*.pdf";
            
            if (of1.ShowDialog() == DialogResult.OK && of1.FileNames.Length > 0)
            {

                #region Create Library Folder
                if (Directory.Exists(Application.StartupPath + "\\" + sProjectName + "\\lib") == false)
                    try
                    {
                        Directory.CreateDirectory(Application.StartupPath + "\\" + sProjectName + "\\lib");
                    }
                    catch (Exception)
                    {

                    }
                #endregion

                foreach (string i in of1.FileNames)
                {
                    if (File.Exists(i) == true)
                        try
                        {
                            File.Copy(i, Path.Combine(Application.StartupPath + "\\" + sProjectName + "\\lib", new FileInfo(i).Name.ToLower().Replace(" ", "_")), true);
                            ls1.Items.Add(new FileInfo(i).Name.ToLower().Replace(" ", "_"));
                            
                        }
                        catch (Exception)
                        {

                        }
                }
                txtfilecount.Text = ls1.Items.Count.ToString();
                string[] inf = getFolderSize();
                txtsize.Text = inf[0];
                MessageBox.Show("فايل هاي انتخاب شده با موفقيت به کتابخانه اضافه شدند", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void ls1_KeyDown(object sender, KeyEventArgs e)
        {
            if (ls1.SelectedIndex > -1)
            {
                if (e.KeyCode == Keys.Delete)
                {
                    try
                    {
                        if (MessageBox.Show("آيا مايل به حذف فايل از کتابخانه هستيد؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                        {
                            File.Delete(Path.Combine(Application.StartupPath + "\\" + sProjectName + "\\lib", ls1.SelectedItem.ToString()));
                            ls1.Items.RemoveAt(ls1.SelectedIndex);
                            txtfilecount.Text = ls1.Items.Count.ToString();
                            string[] inf = getFolderSize();
                            txtsize.Text = inf[0];
                            MessageBox.Show("فايل مورد نظر از کتابخانه حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                    }
                    catch
                    {

                    }
           
                }
            }
        }

        private void ls1_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                txtfilename.Text = ls1.SelectedItem.ToString();
                System.Windows.Forms.Clipboard.SetText(ls1.SelectedItem.ToString());
                frmToast toast = new frmToast();
                toast.setComment = "نام فایل در حافظه کلیپ بورد قرار گرفت";
                toast.Show();
            }
            catch (Exception)
            {

            }
        }

        private string[] getFolderSize()
        {
            double b = 0;
            string[] inf = new string[2];
            try
            {
                string[] a = Directory.GetFiles(Application.StartupPath + "\\" + sProjectName + "\\lib", "*.*");

                // 2.
                // Calculate total bytes of all files in a loop.
                foreach (string name in a)
                {
                    // 3.
                    // Use FileInfo to get length of each file.
                    FileInfo info = new FileInfo(name);
                    b += info.Length;
                }
                // 4.
                // Return total size
                inf[0] = Math.Round(b / 1024 / 1024, 2).ToString() + " مگابايت";
                inf[1] = (a.Length - 1).ToString();
                return inf;
            }
            catch
            {
                inf[0] = (b).ToString() + " بايت";
                inf[1] = "0";
                return inf;
            }
        }

        private void getLibFiles(string projectName)
        {
            ls1.Items.Clear();

            try
            {
                string[] a = Directory.GetFiles(Application.StartupPath + "\\" + projectName + "\\lib", "*.*");
                foreach (string name in a)
                {
                    ls1.Items.Add(new FileInfo(name).Name);
                }
                txtfilecount.Text = ls1.Items.Count.ToString();
                string[] inf = getFolderSize();
                txtsize.Text = inf[0];
            }
            catch (Exception)
            {

            }
        }

        private void ls1_DoubleClick(object sender, EventArgs e)
        {
            //string filename = ls1.SelectedItem.ToString();
            //string ext = new FileInfo(Path.Combine(Application.StartupPath + "\\" + sProjectName + "\\lib", filename)).Extension;
            //switch (ext)
            //{
            //    case ".mp3":
            //    case ".wav":
            //    case ".mid":
            //    case ".ogg":
            //    case ".avi":
            //    case ".mp4":
            //    case ".mkv":
            //    case ".3gp":
            //    case ".tts":
            //        if (playAudio == false)
            //        {
            //            try
            //            {
            //                wplayer.URL = Path.Combine(Application.StartupPath + "\\" + sProjectName + "\\lib", filename);
            //                wplayer.controls.play();
            //                playAudio = true;
            //            }
            //            catch (Exception)
            //            {

            //            }
            //        }
            //        else
            //        {
            //            try
            //            {
            //                wplayer.controls.stop();
            //            }
            //            catch (Exception)
            //            {

            //            }
            //            playAudio = false;
            //        }
            //        return;
            //    case ".jpg":
            //    case ".png":
            //    case ".gif":
            //        try
            //        {
            //            Process.Start(Path.Combine(Application.StartupPath + "\\" + sProjectName + "\\lib", filename));
            //        }
            //        catch
            //        {

            //        }
            //        return;
            //}
        }

        private void tabmain_SelectedPageChanged(object sender, DevExpress.XtraTab.TabPageChangedEventArgs e)
        {

        }

        private void frmmain_Activated(object sender, EventArgs e)
        {
            try
            {
                bwcheckinternet.RunWorkerAsync();
            }
            catch (Exception) { }
        }

        private void cmstate_SelectedIndexChanged(object sender, EventArgs e)
        {
            myClass.setValueRegistery("TypeBook", cmstate.SelectedIndex.ToString());
        }

        private void btnupdate_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (myClass.CheckForInternetConnection() == false)
            {
                MessageBox.Show("لطفا اینترنت را فعال کنید و سپس بروز رسانی را انجام دهید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            try
            {
                myClass.clearFolder(Application.StartupPath + "\\cache");

                try
                {
                    if (Directory.Exists(Application.StartupPath + "\\cache") == true)
                        Directory.Delete(Application.StartupPath + "\\cache");
                }
                catch (Exception)
                {
                }

                tabmain.SelectedTabPage = tabShop;
            }
            catch (Exception)
            {

            }
        }

        private void btnrestore_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            OpenFileDialog opf = new OpenFileDialog();
            opf.Filter = "Zip File|*.zip";
            opf.Title = "انتخاب فایل پشتیبان";
            if (opf.ShowDialog() == DialogResult.OK && opf.FileName.Length > 0)
            {
                if (File.Exists(opf.FileName))
                {
                    string file = Path.GetFileName(opf.FileName).ToLower().Replace(".zip", "");
                    if (Directory.Exists(Application.StartupPath + "\\" + file))
                    {
                        MessageBox.Show("پروژه ای با این نام وجود دارد.لطفا نام فایل را تغیر دهید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        return;
                    }
                    try
                    {
                        newProject();
                        d1.stopConnect();
                        myClass.clearFolder(Application.StartupPath + "\\" + file);
                        Directory.Delete(Application.StartupPath + "\\" + file);
                    }
                    catch (Exception) { }
                }
                try
                {
                    Ionic.Zip.ZipFile z1 = new Ionic.Zip.ZipFile(opf.FileName);
                    string filename = Path.GetFileName(opf.FileName).Replace(".zip", "");

                    if (Directory.Exists(Application.StartupPath + "\\" + filename) == false)
                        Directory.CreateDirectory(Application.StartupPath + "\\" + filename);

                    z1.ExtractAll(Application.StartupPath + "\\" + filename, Ionic.Zip.ExtractExistingFileAction.DoNotOverwrite);
                    MessageBox.Show("فایل پشتیبان با موفقیت بازیابی شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    openProject(filename);
                }
                catch (Exception)
                {
                    MessageBox.Show("متاسفانه کتابساز با خطا مواجه شد.دوباره تلاش کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                }
            }
        }

        private void lnklib_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {

        }

        private void lnlhelplibrary_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            if (myClass.CheckForInternetConnection() == false)
            {
                MessageBox.Show("لطفا اینترنت را فعال کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            frmvideo frmvideo = new frmvideo();
            frmvideo.position = 0;
            frmvideo.PlayVideo("1");
            frmvideo.Show();
        }

        private void chkcache_CheckedChanged(object sender, EventArgs e)
        {
            try
            {
                myClass.setValueRegistery("cache", chkcache.Checked.ToString());
            }
            catch (Exception)
            {
                myClass.setValueRegistery("cache","false");
            }
        }

        private void lbljava_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            frmJava java = new frmJava();
            java.ShowDialog();
        }

        private void txtappid_Leave(object sender, EventArgs e)
        {

        }

        private void btnsavepush_Click(object sender, EventArgs e)
        {
            myClass.setValueRegistery("appid", txtappid.Text);
            myClass.setValueRegistery("clientid", txtclientid.Text);
            MessageBox.Show("تنظیمات خبرنامه ذخیره شد","توجه",MessageBoxButtons.OK,MessageBoxIcon.Information);
        }

        private void btnpushhelp_Click(object sender, EventArgs e)
        {
            if (myClass.CheckForInternetConnection() == false)
            {
                MessageBox.Show("لطفا اینترنت را فعال کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Enabled = true;
                return;
            }
            frmvideo frmvideo = new frmvideo();
            frmvideo.position = 0;
            frmvideo.PlayVideo("3");
            frmvideo.Show();
        }

        private void picads_Click(object sender, EventArgs e)
        {
           
        }

        private void btnchoosepdf_Click(object sender, EventArgs e)
        {
            if (sProjectName == "" || sProjectName == null)
            {
                MessageBox.Show("پروژه اي براي تغييرات وجود ندارد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            OpenFileDialog of1 = new OpenFileDialog();
            of1.CheckFileExists = true;
            of1.Filter = "*.pdf|*.pdf";
            of1.Title = "انتخاب نسخه pdf کتاب";
            of1.ShowDialog();

            if (of1.FileName != "")
            {
                txtpdf.Text = of1.FileName;
                btnremovecover.Visible = true;

                if (txtprojectname.Enabled == false)
                    @File.Copy(of1.FileName, Application.StartupPath + "\\" + sProjectName + "\\book.pdf", true);

                MessageBox.Show("کتاب الکترونیکی کتاب مورد نظر انتخاب شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);

            }
            else
                txtpdf.Text = "";

        }

        private void btncover_Click(object sender, EventArgs e)
        {
            if (sProjectName == "" || sProjectName == null)
            {
                MessageBox.Show("پروژه اي براي تغييرات وجود ندارد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
            OpenFileDialog of1 = new OpenFileDialog();
            of1.CheckFileExists = true;
            of1.Filter = "*.jpg|*.jpg";
            of1.Title = "انتخاب کاور کتاب";
            of1.ShowDialog();

            if (of1.FileName != "")
            {
                Bitmap b1 = new Bitmap(of1.FileName);
                if (b1.Width > b1.Height)
                {
                    MessageBox.Show("اندازه تصویر باید در قالب کتاب باشد.طول تصویر از عرض بیشتر شود", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return;
                }
                else
                {
                    picover.Image = Image.FromFile(of1.FileName);
                    btnremovecover.Visible = true;

                    if (txtprojectname.Enabled == false)
                        @File.Copy(of1.FileName, Application.StartupPath + "\\" + sProjectName + "\\cover.jpg", true);
                    MessageBox.Show("کاور کتاب با موفقیت انتخاب شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }
        }

        private void btnremovecover_Click(object sender, EventArgs e)
        {
            btnremovecover.Visible = false;
            picover.Image = Remote_Computer.Properties.Resources.cover;
            try
            {
                @File.Delete(Application.StartupPath + "\\" + sProjectName + "\\cover.jpg");
            }
            catch (Exception)
            {

            }
        }

        private void treeView1_Click(object sender, EventArgs e)
        {
         
        }

        private void btnfont_Click_1(object sender, EventArgs e)
        {
            if (sProjectName == "" || sProjectName == null)
            {
                MessageBox.Show("پروژه اي براي تغييرات وجود ندارد", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }

            OpenFileDialog of1 = new OpenFileDialog();
            of1.CheckFileExists = true;
            of1.Filter = "*.tff|*.ttf";
            of1.Title = "فونت پیش فرض کتاب";
            of1.ShowDialog();

            if (of1.FileName != "")
            {

                if (txtprojectname.Enabled == false)
                    @File.Copy(of1.FileName, Application.StartupPath + "\\" + sProjectName + "\\default.ttf", true);

                MessageBox.Show("فونت کتاب انتخاب شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);

                PrivateFontCollection pfcoll = new PrivateFontCollection();
                pfcoll.AddFontFile(of1.FileName);
                FontFamily ff = pfcoll.Families[0];
                Font ft = new Font(ff, 40);
                lblfont.Font = ft;

            }
        }

        private void mnuaddfooter_Click(object sender, EventArgs e)
        {

            if (currentNode == null)
            {
                MessageBox.Show("لطفا موضوع را انتخاب و سپس کلیک راست و مدیریت پاورقی را انتخاب کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            if (currentNode.Tag is string[])
            {
                string[] id = (string[])currentNode.Tag;
                frmfooter fr = new frmfooter(sProjectName);
                fr.TopicId = id[0];
                fr.LoadFooters(d1.getRow("SELECT * FROM tbl_footer WHERE sTopicID ='" + id[0] + "'"));
                fr.ShowDialog();
            }
            else
                MessageBox.Show("فقط موضوع میتواند شامل پاورقی باشد","خطا",MessageBoxButtons.OK,MessageBoxIcon.Exclamation);

        }

        private void btnlogin_Click(object sender, EventArgs e)
        {
            
            if (txtu.Text.Length < 4 || txtp.Text.Length < 4)
            {
                MessageBox.Show("لطفا نام کاربری و رمز عبور را حداقل 5 کاراکتر وارد کنید","توجه",MessageBoxButtons.OK,MessageBoxIcon.Exclamation);
                return;
            }

            Enabled = false;
            string res = myClass.ExecuteMethod(myClass.URL + "index.php/server/login", "u=" + txtu.Text + "&p=" + txtp.Text);
            Enabled = true;

            try
            {
                Newtonsoft.Json.Linq.JObject result = (Newtonsoft.Json.Linq.JObject)Newtonsoft.Json.JsonConvert.DeserializeObject(res);
                Newtonsoft.Json.Linq.JArray data = (Newtonsoft.Json.Linq.JArray)result["result"];
                Newtonsoft.Json.Linq.JArray messages = (Newtonsoft.Json.Linq.JArray)result["message"];
                Newtonsoft.Json.Linq.JArray category = (Newtonsoft.Json.Linq.JArray)result["cat"];


                dtOnlineCategory.Columns.Add("sID");
                dtOnlineCategory.Columns.Add("sTitle");
                dtOnlineCategory.Columns.Add("sParent");

                for (int k = 0; k < category.Count; k++)
                {
                    Newtonsoft.Json.Linq.JObject cats = (Newtonsoft.Json.Linq.JObject)category[k];
                    DataRow dr;
                    dr = dtOnlineCategory.NewRow();
                    dr["sID"] = cats["sID"].ToString();
                    dr["sTitle"] = cats["sTitle"].ToString();
                    dr["sParent"] = cats["sParent"].ToString();
                    dtOnlineCategory.Rows.Add(dr);
                }

                foreach (DataRow row in dtOnlineCategory.Rows)
                {
                    TreeNode treeRoot;
                    treeRoot = new TreeNode();
                    treeRoot.Text = row["sTitle"].ToString();
                    treeRoot.Tag = row["sID"].ToString();
                    treeRoot.ExpandAll();

                    if (row["sParent"].ToString() == "0")
                    {
                        tvcat.Nodes.Add(treeRoot);
                        CreateNodesOfParent(Convert.ToInt32(row["sID"]), treeRoot, tvcat);
                    }
                }

                for (int i = 0; i < data.Count; i++)
                {
                    Newtonsoft.Json.Linq.JObject cat = (Newtonsoft.Json.Linq.JObject)data[i];

                    links.Add(cat);

                    if (cat["sType"].ToString() == "s")
                    {
                        dgsocial.Rows.Add(cat["sTitle"].ToString(), cat["sLink1"].ToString());
                    }
                    else
                    {
                        dglink.Rows.Add(cat["sTitle"].ToString(), cat["sLink1"].ToString(), cat["sLink2"].ToString());
                    }
                }

                int top = 0;
                svmessage.Maximum = messages.Count * 25;
                pnlmessages.Height = 0;

                for (int j = 0; j < messages.Count; j++)
                {
                    Newtonsoft.Json.Linq.JObject message = (Newtonsoft.Json.Linq.JObject)messages[j];
                    UserMessages um = new UserMessages();
                    pnlmessages.Controls.Add(um);
                    um.Controls["txtusername"].Text = message["sName"].ToString();
                    um.Controls["txtusermessage"].Text = message["sComment"].ToString();
                    um.Controls["txtuseremail"].Text = message["sEmail"].ToString();
                    um.Controls["lbltime"].Text = message["sDateTime"].ToString();
                    um.Top = top;
                    um.Left = 0;
                    top += um.Height + 10;
                }

                pnlmessages.Height = top;

                gblogin.Visible = false;
                myClass.Username = txtu.Text;
                myClass.Password = txtp.Text;
                tabcp1.Visible = true;

            }

            catch (Exception)
            {
                if (res == "NoNet")
                {
                    MessageBox.Show("متاسفانه برنامه قادر به برقراری ارتباط نیست.لطفا اینترنت را بررسی کنید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }
                else
                {
                    MessageBox.Show("خطا : نام یا رمز کاربری ممکن است اشتباه باشد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }
            }  
        }

        public void CreateNodesOfParent(int iParent, TreeNode pNode, TreeView t1)
        {

            DataView dvwData = new DataView(dtOnlineCategory);
            dvwData.RowFilter = String.Format("{0}={1}", "sParent", iParent);

            foreach (DataRowView Row in dvwData)
            {
                TreeNode zNode = pNode.Nodes.Add(Row["sTitle"].ToString());
                zNode.Tag = Row["sID"].ToString();
                CreateNodesOfParent(Int32.Parse(Row["sID"].ToString()), zNode, t1);
            }

        }

        private void mnuexits_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (MessageBox.Show("آيا مايل به خروج هستيد؟", "خروج", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {
                Application.ExitThread();
                Application.ExitThread();
            }
        }

        private void btnnews_Click(object sender, EventArgs e)
        {
            if (txtstitle.Text.Length < 4 || txtslink.Text.Length < 4)
            {
                MessageBox.Show("لطفا عنوان و آدرس شبکه را حداقل 5 کاراکتر وارد کنید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            Enabled = false;
            string res = myClass.ExecuteMethod(myClass.URL + "index.php/server/addlink", String.Format("u={0}&p={1}&title={2}&link1={3}&link2={4}&type={5}",myClass.Username,myClass.Password,txtstitle.Text,txtslink.Text,"-","s"));
            Enabled = true;

            if (res == "NoNet")
            {
                MessageBox.Show("متاسفانه برنامه قادر به برقراری ارتباط نیست.لطفا اینترنت را بررسی کنید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            if (res == "1")
            {
                dgsocial.Rows.Add(txtstitle.Text, txtslink.Text);
                MessageBox.Show("گزینه مورد نظر با موفقیت ایجاد شد","توجه",MessageBoxButtons.OK,MessageBoxIcon.Information);
                txtslink.Text = txtstitle.Text = "";
            }
            else
            {
                MessageBox.Show("خطا : نام یا رمز کاربری ممکن است اشتباه باشد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }
        }

        private void btnnewlink_Click(object sender, EventArgs e)
        {
            if (txtltitle.Text.Length < 4 || txtllink.Text.Length < 4)
            {
                MessageBox.Show("لطفا عنوان و آدرس شبکه را حداقل 5 کاراکتر وارد کنید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            Enabled = false;
            string res = myClass.ExecuteMethod(myClass.URL + "index.php/server/addlink", String.Format("u={0}&p={1}&title={2}&link1={3}&link2={4}&type={5}", myClass.Username, myClass.Password, txtltitle.Text, txtllink.Text, txtlfile.Text, "l"));
            Enabled = true;

            if (res == "NoNet")
            {
                MessageBox.Show("متاسفانه برنامه قادر به برقراری ارتباط نیست.لطفا اینترنت را بررسی کنید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            if (res == "1")
            {
                dglink.Rows.Add(txtltitle.Text, txtllink.Text,txtlfile.Text);
                MessageBox.Show("گزینه مورد نظر با موفقیت ایجاد شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtllink.Text = txtltitle.Text = txtlfile.Text = "";
            }
            else
            {
                MessageBox.Show("خطا : نام یا رمز کاربری ممکن است اشتباه باشد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }
        }

        private void dgsocial_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Delete)
            {
                if (MessageBox.Show("آیا مایل به حذف این گزینه هستید؟", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    string title = dgsocial.Rows[dgsocial.CurrentCell.RowIndex].Cells[0].Value.ToString();
                    string link = dgsocial.Rows[dgsocial.CurrentCell.RowIndex].Cells[1].Value.ToString();
                    string id = GetIDLink(title, link);
                    Enabled = false;
                    string res = myClass.ExecuteMethod(myClass.URL + "index.php/server/deletelink", String.Format("u={0}&p={1}&id={2}", myClass.Username, myClass.Password,id));
                    Enabled = true;
                    if (res == "1")
                    {
                        dgsocial.Rows.RemoveAt(dgsocial.CurrentCell.RowIndex);
                        MessageBox.Show("گزینه مورد نظر با موفقیت حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
            }
          
        }

        private string GetIDLink(string title,string link)
        {
            for (int i = 0; i <= links.Count - 1; i++)
            {
                Newtonsoft.Json.Linq.JObject cat = (Newtonsoft.Json.Linq.JObject)links[i];
                string title1, link1;
                title1 = cat["sTitle"].ToString();
                link1 = cat["sLink1"].ToString();
                if (title1 == title && link1 == link)
                    return cat["sID"].ToString();
            }
            return "";
        }

        private void svmessage_Scroll(object sender, ScrollEventArgs e)
        {
            pnlmessages.Top = -(e.NewValue * 10);
        }

        private void tvcat_AfterSelect(object sender, TreeViewEventArgs e)
        {
            CurrentCategory = tvcat.SelectedNode;

            if (tvcat.SelectedNode != null)
            {
                string id = tvcat.SelectedNode.Tag.ToString();
                Enabled = false;

                string res;

                if (!ListBook.ContainsKey(id))
                {
                    res = myClass.ExecuteMethod(myClass.URL + "index.php/server/get_books", "id=" + id);
                    ListBook.Add(id,res);
                }
                else
                    res = ListBook[id];

                Enabled = true;

                try
                {
                    Newtonsoft.Json.Linq.JObject result = (Newtonsoft.Json.Linq.JObject)Newtonsoft.Json.JsonConvert.DeserializeObject(res);
                    Newtonsoft.Json.Linq.JArray data = (Newtonsoft.Json.Linq.JArray)result["result"];

                    pnlbooks.Controls.Clear();
                    vsbooks.Maximum = data.Count * 25;
                    int top = 0;

                    for (int k = 0; k < data.Count; k++)
                    {
                        Newtonsoft.Json.Linq.JObject cats = (Newtonsoft.Json.Linq.JObject)data[k];

                        Book b1 = new Book();
                        pnlbooks.Controls.Add(b1);
                        b1.Top = top;
                        b1.Left = 0;
                        b1.Controls["txtbookname"].Text = cats["sTitle"].ToString() + " ( " + cats["sDownload"].ToString() + " )";
                        b1.Controls["txtauthor"].Text = cats["sAuthor"].ToString();
                        b1.Controls["btndelete"].Tag = cats["sID"].ToString();
                        top += b1.Height + 10;

                        PictureBox pb1 = b1.Controls["piccover"] as PictureBox;
                        string urlpic = cats["sCover"].ToString();
                        pnlbooks.Height = top;
                        pb1.Load(myClass.URL + urlpic);
                    }
                }
                catch (Exception)
                {
                }
            }
        }

        private void vsbooks_Scroll(object sender, ScrollEventArgs e)
        {
            pnlbooks.Top = -(e.NewValue * 10);
        }

        private void btndeletecat_Click(object sender, EventArgs e)
        {
            if (CurrentCategory == null)
            {
                MessageBox.Show("لطفا دسته بندی را انتخاب کنید و سپس اقدام کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            if (MessageBox.Show("آیا مطمین به حذف دسته بندی هستید؟\nبا حذف دسته بندی،کتاب های زیر مجموعه نیز حذف خواهد شد", "توجه", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.No) return;
            string id = CurrentCategory.Tag.ToString();

            Enabled = false;
            string result = myClass.ExecuteMethod(myClass.URL + "index.php/server/remove_category", "u=" + myClass.Username + "&p=" + myClass.Password + "&id=" + id);
            Enabled = true;

            if (result == "1")
            {
                pnlbooks.Controls.Clear();
                CurrentCategory.Remove();
                MessageBox.Show("دسته بندی با کل کتاب های زر مجموعه حذف شد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else
            {
                MessageBox.Show("عملیات با خطا مواجه شد.دوباره تلاش کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }

        }

        private void btnnewcat_Click(object sender, EventArgs e)
        {
            frminput f1 = new frminput(false);
            f1.sProjectName = Application.StartupPath + "\\" + sProjectName;
            f1.ShowDialog();

            try
            {
                if (f1.sChapter != "")
                {
                    int res = 0;

                    if (f1.isRoot == true)
                    {
                        res = 0;
                    }
                    else
                    {
                        res = Convert.ToInt16(CurrentCategory.Tag.ToString());
                    }

                    Enabled = false;
                    string result = myClass.ExecuteMethod(myClass.URL + "index.php/server/add_category", "u=" + myClass.Username + "&p=" + myClass.Password + "&parent=" + res + "&title=" + f1.sChapter);
                    Enabled = true;

                    int outNum;
                    bool isNumber = int.TryParse(result, out outNum);

                    if (isNumber == true)
                        if (outNum > 0)
                        {
                            if (res == 0)
                                tvcat.Nodes.Add(f1.sChapter).Tag = result;
                            else
                            {
                                TreeNode tn = tvcat.SelectedNode;
                                tn.Nodes.Add(f1.sChapter).Tag = result;
                            }
                        }
                }
            }

            catch (Exception)
            {
                MessageBox.Show("عملیات با خطا مواجه شد.دوباره تلاش کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }

        private void mnusendserver_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {

            if (myClass.CheckForInternetConnection() == false)
            {
                MessageBox.Show("لطفا اینترنت را فعال کنید", "خطا", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                return;
            }

            if (myClass.Username == null || myClass.Password == null)
            {
                MessageBox.Show("لطفا وارد کنترل پنل مدیریت شده  و سپس این عملیات را انجام دهید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                tabmain.SelectedTabPage = tabcp;
                return;
            }

            if (myClass.Username.Length < 4 || myClass.Password.Length < 4)
            {
                MessageBox.Show("لطفا وارد کنترل پنل مدیریت شده  و سپس این عملیات را انجام دهید","توجه",MessageBoxButtons.OK,MessageBoxIcon.Exclamation);
                tabmain.SelectedTabPage = tabcp;
                return;
            }

            if (CurrentCategory == null)
            {
                MessageBox.Show("لطفا وارد کنترل پنل مدیریت شده  و سپس دسته بندی را انتخاب کنید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                tabmain.SelectedTabPage = tabcp;
                tabcp1.SelectedTabPage = tabcat;
                return;
            }

            currentCategoryBook = CurrentCategory.Tag.ToString();

            frmlanguage fr1 = new frmlanguage();
            fr1.ShowDialog();
            
            if (fr1.Language == "") return;

            currentLanguage = fr1.Language;

            goCompile(Application.StartupPath + "\\" + sProjectName,"online");
            
        }

        private void mnuusermessage_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (IsLogin() == false)
            {
                MessageBox.Show("لطفا وارد کنترل پنل مدیریت شده  و سپس این عملیات را انجام دهید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                tabmain.SelectedTabPage = tabcp;
                return;
            }

            tabmain.SelectedTabPage = tabcp;
            tabcp1.SelectedTabPage = tabmessages;
        }

        private bool IsLogin()
        {
            {
                if (myClass.Username == null || myClass.Password == null)
                {
                    return false;
                }

                if (myClass.Username.Length < 4 || myClass.Password.Length < 4)
                {
                    return false;
                }
                return true;
            }
        }

        private void mnusites_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (IsLogin() == false)
            {
                MessageBox.Show("لطفا وارد کنترل پنل مدیریت شده  و سپس این عملیات را انجام دهید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                tabmain.SelectedTabPage = tabcp;
                return;
            }

            tabmain.SelectedTabPage = tabcp;
            tabcp1.SelectedTabPage = tabsite;
        }

        private void mnusocials_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (IsLogin() == false)
            {
                MessageBox.Show("لطفا وارد کنترل پنل مدیریت شده  و سپس این عملیات را انجام دهید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                tabmain.SelectedTabPage = tabcp;
                return;
            }

            tabmain.SelectedTabPage = tabcp;
            tabcp1.SelectedTabPage = tabsocials;
        }

        private void mnucat_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {
            if (IsLogin() == false)
            {
                MessageBox.Show("لطفا وارد کنترل پنل مدیریت شده  و سپس این عملیات را انجام دهید", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                tabmain.SelectedTabPage = tabcp;
                return;
            }

            tabmain.SelectedTabPage = tabcp;
            tabcp1.SelectedTabPage = tabcat;
        }

        private void mnuosoffline_LinkClicked(object sender, DevExpress.XtraNavBar.NavBarLinkEventArgs e)
        {

            if (d1 == null)
            {
                MessageBox.Show("پروژه اي براي تهيه خروجي وجود ندارد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }

            if (d1.countRecord() < 1)
            {
                MessageBox.Show("در حال حاظر مطلبي در کتاب وجود ندارد\r\nلطفا ابتدا مطالب را ايجاد سپس خروجي تهيه کنيد", "توجه", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }

            ZipUtil.GrantAccess(Application.StartupPath);
            goCompile(Application.StartupPath + "\\" + sProjectName, "other");
        
        }

    }
}