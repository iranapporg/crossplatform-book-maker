using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Collections;

namespace Remote_Computer
{
    public partial class frmlist : Form
    {
        public frmlist()
        {
                InitializeComponent();
        }
        db db1;
        ArrayList al1 = new ArrayList();
        bool sCloseForm = false;

        public string sProjectName
        {
            get
            {
                try
                {
                    myClass.setValueRegistery("project", al1[treeView1.SelectedNode.Index].ToString());
                    return al1[treeView1.SelectedNode.Index].ToString();
                }
                catch(Exception)
                {
                    return "";
                }
            }
        }

        public bool FormClose
        {
            get
            {
                return sCloseForm;
            }
        }
        private void frmlist_Load(object sender, EventArgs e)
        {
            try
            {
                string[] d1;
                d1 = Directory.GetDirectories(Application.StartupPath);
                TreeNode tree1 = treeView1.Nodes[0];
                foreach (string s1 in d1)
                {
                    DirectoryInfo di1 = new DirectoryInfo(s1);
                    if (di1.Name == "demo" || di1.Name == "temp") continue;
                    if (File.Exists(s1 + "\\bank.db"))
                    {
                        db1 = new db(di1.Name);
                        string bookname = db1.getFieldInformation("sTitle");
                        al1.Add(di1.Name);

                        string prID = "";
                        string id = db1.getFieldInformation("sID");

                        if (id.Length > 0)
                            prID = " کد شابک " + id;

                        tree1.Nodes.Add(di1.Name, "پروژه " + di1.Name + " با عنوان " + bookname + prID);
                    }
                }
                treeView1.ExpandAll();
            }
            catch(Exception)
            {

            }
            string chkState = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER", "startup", "false").ToString();
            if (chkState.ToLower() == "true")
            {
                chk1.Checked = true;
            }
        }

        private void treeView1_AfterSelect(object sender, TreeViewEventArgs e)
        {
            TreeNode tn = treeView1.SelectedNode;
            if (tn.Name != "mainNode")
                Hide();
        }

        private void chk1_CheckedChanged(object sender, EventArgs e)
        {
            myClass.setValueRegistery("startup", chk1.Checked.ToString().ToLower());
        }

        private void btndelete_Click(object sender, EventArgs e)
        {
            System.Diagnostics.Process.Start(Application.StartupPath + "\\backup");
        }

        private void frmlist_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (treeView1.SelectedNode.Level == 0)
             sCloseForm = true;
        }
    }
}
