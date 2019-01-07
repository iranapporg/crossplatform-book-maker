using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using CompressionSample;
using System.Drawing.Text;

namespace Remote_Computer
{
    public partial class frmEditApk : Form
    {
        int i = 1;
        string Filename;

        public frmEditApk()
        {
            InitializeComponent();
        }

        public void setInformation(string filename,string id,string title)
        {
            Text = "ویرایش قالب " + title;
            Filename = filename;

            try
            {
                myClass.clearFolder(Application.StartupPath + "\\tool\\book");
                Directory.Delete(Application.StartupPath + "\\tool\\book");
            }
            catch (Exception) { }

            try
            {
                File.Copy(Application.StartupPath + "\\template\\" + filename, Application.StartupPath + "\\tool\\book.apk", true);
            }
            catch (Exception)
            {
            }

            Enabled = false;
            bwproccess.RunWorkerAsync();
            
        }


        private void bwproccess_DoWork(object sender, DoWorkEventArgs e)
        {
            try
            {
                myClass.clearFolder(Application.StartupPath + "\\tool\\book");
                Directory.Delete(Application.StartupPath + "\\tool\\book");
            }
            catch (Exception)
            {

            }
            ZipUtil.ExportAPK("d");
            
        }

        private void bwproccess_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            Enabled = true;
            pgb1.Visible = false;
            tmrpb.Enabled = false;
            LoadFile();
        }

        private void tmrpb_Tick(object sender, EventArgs e)
        {
            if (i > 100) i = 1;
            pgb1.Value = i++;
        }

        private void btncancel_Click(object sender, EventArgs e)
        {
            try
            {
                try
                {
                    myClass.clearFolder(Application.StartupPath + "\\tool\\book");
                    Directory.Delete(Application.StartupPath + "\\tool\\book");
                }
                catch (Exception) { }

                if (File.Exists((Application.StartupPath + "\\template\\default.txt")))
                {
                    string res = File.ReadAllText(Application.StartupPath + "\\template\\default.txt");
                    if (File.Exists(Application.StartupPath + "\\template\\" + res))
                        File.Copy(Application.StartupPath + "\\template\\" + res, Application.StartupPath + "\\tool\\book.apk", true);
                }
                Dispose();
            }
            catch (Exception) { }
        }


        private void LoadFile()
        {
            string[] files;
            try
            {
                 files = Directory.GetFiles(Application.StartupPath + "\\tool\\book\\assets");
            }
            catch (Exception)
            {
                MessageBox.Show("متاسفانه در ویرایشگر کتاب مشکل به وجود آمده است\nدوباره تلاش کنید","خطا",MessageBoxButtons.OK,MessageBoxIcon.Exclamation);
                Dispose();
                return;
            }

            int resCount = 0,pictureCount=0,audioCount = 0,videoCount=0,ttfCount = 0,fileCount = 0;
            int top,top1,left,top2,top3,top4;

            top = top2  = top1 = top3 = top4 = 10;
            left = 3;

            foreach (string file in files)
            {

                string ext = Path.GetExtension(file).Replace(".","");

                switch (ext)
                {
                    case "png":
                    case "gif":
                    case "jpg":
                    case "bmp":
                    case "jpeg":

                        ++pictureCount;

                        try
                        {
                            PictureItem pi = new PictureItem();

                            pi.Controls["btnreplace"].Tag = file;
                            pi.Controls["btndelete"].Tag = file;

                            using (Bitmap b1 = new Bitmap(file))
                            {
                                GroupBox gb = pi.Controls["gb1"] as GroupBox;

                                gb.Text = b1.Width + " * " + b1.Height + " Type : " + Path.GetExtension(file).Replace(".", "");

                                if (b1.Width > b1.Height)
                                {
                                    PictureBox p1 = gb.Controls["pbPictureW"] as PictureBox;
                                    gb.Controls["pbPictureH"].Visible = false;
                                    gb.Controls["pbPicture"].Visible = false;
                                    gb.Controls["pbPictureW"].Visible = true;
                                    gb.Controls["pbPictureW"].Tag = file;

                                    using (FileStream m1 = new FileStream(file, FileMode.Open, FileAccess.Read))
                                    {
                                        p1.Image = Image.FromStream(m1);
                                    }


                                    if (b1.Width < 99 && b1.Height < 99)
                                        p1.SizeMode = PictureBoxSizeMode.CenterImage;


                                }
                                else if (b1.Width < b1.Height)
                                {
                                    PictureBox p1 = gb.Controls["pbPictureH"] as PictureBox;
                                    gb.Controls["pbPictureW"].Visible = false;
                                    gb.Controls["pbPicture"].Visible = false;
                                    gb.Controls["pbPictureH"].Visible = true;
                                    gb.Controls["pbPictureH"].Tag = file;

                                    using (FileStream m1 = new FileStream(file, FileMode.Open, FileAccess.Read))
                                    {
                                        p1.Image = Image.FromStream(m1);
                                    }

                                    if (b1.Width < 99 && b1.Height < 99)
                                        p1.SizeMode = PictureBoxSizeMode.CenterImage;
                                }
                                else if (b1.Width == b1.Height)
                                {
                                    PictureBox p1 = gb.Controls["pbPicture"] as PictureBox;

                                    gb.Controls["pbPictureW"].Visible = false;
                                    gb.Controls["pbPictureH"].Visible = false;
                                    gb.Controls["pbPicture"].Visible = true;
                                    gb.Controls["pbPicture"].Tag = file;

                                    using (FileStream m1 = new FileStream(file, FileMode.Open, FileAccess.Read))
                                    {
                                        p1.Image = Image.FromStream(m1);
                                    }

                                    if (b1.Width < 99 && b1.Height < 99)
                                        p1.SizeMode = PictureBoxSizeMode.CenterImage;
                                }

                            }


                            pnlpicture.Controls.Add(pi);
                            pi.Location = new Point(left, top);

                            left += pi.Width + 2;

                            if (pictureCount % 4 == 0)
                            {
                                left = 5;
                                top += pi.Height;
                                pnlpicture.Height += pi.Height;
                            }
                        }
                        catch (Exception)
                        {

                        }

                        break;

                    case "mp3":
                    case "wav":
                    case "mid":
                    case "amr":
                    case "ogg":
                    case "wma":
                    case "m4a":
                        ++audioCount;

                        try
                        {
                            AudioItem pi1 = new AudioItem();

                            GroupBox gb1 = pi1.Controls[0] as GroupBox;
                            gb1.Controls["btnreplace"].Tag = file;
                            gb1.Controls["btndelete"].Tag = file;

                            gb1.Text = "Type : " + Path.GetExtension(file).Replace(".", "");
                            gb1.Controls["btnplay"].Tag = file;
                            gb1.Controls["txtname"].Text = Path.GetFileName(file);
                            gb1.Controls["txtsize"].Text = (new FileInfo(file).Length * 1024).ToString() + " کیلوبایت";
                            gb1.Controls["btndelete"].Tag = file;

                            pnlaudio.Controls.Add(pi1);
                            pi1.Location = new Point(3, top1);

                            top1 += pi1.Height;
                            pnlaudio.Height += pi1.Height;
                        }
                        catch (Exception)
                        {

                        }

                        break;


                    case "mp4":
                    case "3gp":
                    case "wmv":
                    case "mkv":
                    case "avi":
                    case "mpg":
                    case "mpeg":
                        ++videoCount;

                        try
                        {
                            VideoItem pi2 = new VideoItem();

                            GroupBox gb2 = pi2.Controls[0] as GroupBox;
                            gb2.Controls["btnreplace"].Tag = file;
                            gb2.Controls["btndelete"].Tag = file;

                            gb2.Text = "Type : " + Path.GetExtension(file).Replace(".", "");
                            gb2.Controls["btnplay"].Tag = file;
                            gb2.Controls["txtname"].Text = Path.GetFileName(file);
                            gb2.Controls["txtsize"].Text = (new FileInfo(file).Length * 1024).ToString() + " کیلوبایت";
                            gb2.Controls["btndelete"].Tag = file;

                            pnlvideo.Controls.Add(pi2);
                            pi2.Location = new Point(3, top2);

                            top2 += pi2.Height;
                            pnlvideo.Height += pi2.Height;
                        }
                        catch (Exception)
                        {

                        }

                        break;

                    case "ttf":
                        ++ttfCount;

                        try
                        {
                            FontItem pi3 = new FontItem();

                            GroupBox gb3 = pi3.Controls[0] as GroupBox;

                            gb3.Controls["btnreplace"].Tag = file;
                            gb3.Controls["btndelete"].Tag = file;

                            gb3.Text = "Type : " + Path.GetExtension(file).Replace(".", "");
                            gb3.Controls["txtname"].Text = Path.GetFileName(file);
                            gb3.Controls["txtsize"].Text = (new FileInfo(file).Length * 1024).ToString() + " کیلوبایت";
                            gb3.Controls["btndelete"].Tag = file;

                            pnlttf.Controls.Add(pi3);
                            pi3.Location = new Point(3, top3);

                            top3 += pi3.Height;
                            pnlttf.Height += pi3.Height;
                        }
                        catch (Exception) {

                        }

                        break;

                    default:
                        ++fileCount;

                        try
                        {
                            FileItem pi4 = new FileItem();

                            GroupBox gb4 = pi4.Controls[0] as GroupBox;
                            gb4.Controls["btnreplace"].Tag = file;
                            gb4.Controls["btndelete"].Tag = file;
                            gb4.Controls["btnopen"].Tag = file;

                            gb4.Text = "Type : " + Path.GetExtension(file).Replace(".", "");
                            gb4.Controls["txtname"].Text = Path.GetFileName(file);
                            gb4.Controls["txtsize"].Text = (new FileInfo(file).Length * 1024).ToString() + " کیلوبایت";
                            gb4.Controls["btndelete"].Tag = file;

                            pnlfile.Controls.Add(pi4);
                            pi4.Location = new Point(3, top4);

                            top4 += pi4.Height;
                            pnlfile.Height += pi4.Height;
                        }
                        catch (Exception)
                        {

                        }

                        break;

                }
            }

            try
            {
                List<string> l1 = myClass.DirSearch(Application.StartupPath + "\\tool\\book\\res");
                for (int i = 0; i < l1.Count; i++)
                {
                    ++resCount;
                    string file = l1[i];

                    FileItem pi4 = new FileItem();

                    GroupBox gb4 = pi4.Controls[0] as GroupBox;
                    gb4.Controls["btnreplace"].Tag = file;
                    gb4.Controls["btndelete"].Tag = file;
                    gb4.Controls["btnopen"].Tag = file;

                    gb4.Text = "Type : " + Path.GetExtension(file).Replace(".", "");
                    gb4.Controls["txtname"].Text = Path.GetFileName(file);
                    gb4.Controls["txtsize"].Text = (new FileInfo(file).Length * 1024).ToString() + " کیلوبایت";
                    gb4.Controls["btndelete"].Tag = file;

                    pnlfile.Controls.Add(pi4);
                    pi4.Location = new Point(3, top4);

                    top4 += pi4.Height;
                    pnlfile.Height += pi4.Height;

                }
                sv5.Maximum = fileCount * 10;
            }
            catch (Exception) { return; }

            left = 3;
            top = 10;



            pnlpicture.Refresh();
            pnlaudio.Refresh();
            pnlvideo.Refresh();
            sv1.Maximum = (pictureCount * 4);
            sv2.Maximum = audioCount * 8;
            sv3.Maximum = videoCount * 8;
            sv4.Maximum = ttfCount * 8;
        }

        private void sv1_Scroll(object sender, ScrollEventArgs e)
        {
            pnlpicture.Top = -(e.NewValue * 10);
            pnlpicture.Refresh();
        }

        private void sv2_Scroll(object sender, ScrollEventArgs e)
        {
            pnlaudio.Top = -(e.NewValue * 10);
            pnlaudio.Refresh();
        }

        private void sv3_Scroll(object sender, ScrollEventArgs e)
        {
            pnlvideo.Top = -(e.NewValue * 10);
            pnlvideo.Refresh();
        }

        private void sv4_Scroll(object sender, ScrollEventArgs e)
        {
            pnlttf.Top = -(e.NewValue * 10);
            pnlttf.Refresh();
        }

        private void sv5_Scroll(object sender, ScrollEventArgs e)
        {
            pnlfile.Top = -(e.NewValue * 10);
            pnlfile.Refresh();
        }

        private void bwcompile_DoWork(object sender, DoWorkEventArgs e)
        {
            ZipUtil.ExportAPK("b");
        }

        private void bwcompile_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            try
            {
                File.Copy(Application.StartupPath + "\\tool\\book\\dist\\book.apk", Application.StartupPath + "\\template\\" + Filename, true);

                try
                {
                    myClass.clearFolder(Application.StartupPath + "\\tool\\book");
                    Directory.Delete(Application.StartupPath + "\\tool\\book");
                }
                catch (Exception) { }

                frmToast t1 = new frmToast();
                t1.setComment = "تغیرات قالب با موفقیت ذخیره شد";
                t1.Show();
                btncancel_Click(null, null);
            }
            catch (Exception)
            {
                frmToast t1 = new frmToast();
                t1.setComment = "عملیات با خطا مواجه شد";
                t1.Show();
                Dispose();
            }
        }

        private void btnadd_Click(object sender, EventArgs e)
        {
            Enabled = false;
            pgb1.Visible = true;
            tmrpb.Enabled = true;
            File.Delete(Application.StartupPath + "\\tool\\output\\bookN_out.apk");
            bwcompile.RunWorkerAsync();
        }

    }
}
