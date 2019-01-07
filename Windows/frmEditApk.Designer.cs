namespace Remote_Computer
{
    partial class frmEditApk
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(frmEditApk));
            this.btnadd = new System.Windows.Forms.Button();
            this.btncancel = new System.Windows.Forms.Button();
            this.label7 = new System.Windows.Forms.Label();
            this.pictureBox11 = new System.Windows.Forms.PictureBox();
            this.statusStrip1 = new System.Windows.Forms.StatusStrip();
            this.pgb1 = new System.Windows.Forms.ToolStripProgressBar();
            this.bwproccess = new System.ComponentModel.BackgroundWorker();
            this.tmrpb = new System.Windows.Forms.Timer(this.components);
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.tabmain = new DevExpress.XtraTab.XtraTabControl();
            this.tabpicture = new DevExpress.XtraTab.XtraTabPage();
            this.pnlparent = new System.Windows.Forms.Panel();
            this.pnlpicture = new System.Windows.Forms.Panel();
            this.sv1 = new System.Windows.Forms.VScrollBar();
            this.tabmusic = new DevExpress.XtraTab.XtraTabPage();
            this.panel1 = new System.Windows.Forms.Panel();
            this.pnlaudio = new System.Windows.Forms.Panel();
            this.sv2 = new System.Windows.Forms.VScrollBar();
            this.tabvideo = new DevExpress.XtraTab.XtraTabPage();
            this.panel2 = new System.Windows.Forms.Panel();
            this.pnlvideo = new System.Windows.Forms.Panel();
            this.sv3 = new System.Windows.Forms.VScrollBar();
            this.txtfont = new DevExpress.XtraTab.XtraTabPage();
            this.panel3 = new System.Windows.Forms.Panel();
            this.pnlttf = new System.Windows.Forms.Panel();
            this.sv4 = new System.Windows.Forms.VScrollBar();
            this.tabother = new DevExpress.XtraTab.XtraTabPage();
            this.panel4 = new System.Windows.Forms.Panel();
            this.pnlfile = new System.Windows.Forms.Panel();
            this.sv5 = new System.Windows.Forms.VScrollBar();
            this.bwcompile = new System.ComponentModel.BackgroundWorker();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.pictureBox3 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox11)).BeginInit();
            this.statusStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.tabmain)).BeginInit();
            this.tabmain.SuspendLayout();
            this.tabpicture.SuspendLayout();
            this.pnlparent.SuspendLayout();
            this.tabmusic.SuspendLayout();
            this.panel1.SuspendLayout();
            this.tabvideo.SuspendLayout();
            this.panel2.SuspendLayout();
            this.txtfont.SuspendLayout();
            this.panel3.SuspendLayout();
            this.tabother.SuspendLayout();
            this.panel4.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox3)).BeginInit();
            this.SuspendLayout();
            // 
            // btnadd
            // 
            this.btnadd.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btnadd.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnadd.Location = new System.Drawing.Point(625, 692);
            this.btnadd.Name = "btnadd";
            this.btnadd.Size = new System.Drawing.Size(114, 29);
            this.btnadd.TabIndex = 4;
            this.btnadd.Text = "ذخیره قالب";
            this.btnadd.UseVisualStyleBackColor = false;
            this.btnadd.Click += new System.EventHandler(this.btnadd_Click);
            // 
            // btncancel
            // 
            this.btncancel.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btncancel.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btncancel.Location = new System.Drawing.Point(505, 692);
            this.btncancel.Name = "btncancel";
            this.btncancel.Size = new System.Drawing.Size(114, 29);
            this.btncancel.TabIndex = 5;
            this.btncancel.Text = "انصراف";
            this.btncancel.UseVisualStyleBackColor = false;
            this.btncancel.Click += new System.EventHandler(this.btncancel_Click);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.Location = new System.Drawing.Point(26, 145);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(475, 13);
            this.label7.TabIndex = 17;
            this.label7.Text = "برای ویرایش یا جایگزین کردن فایل از آیکون         و برای حذف فایل مورد نظر از علا" +
                "مت         استفاده کنید.";
            // 
            // pictureBox11
            // 
            this.pictureBox11.Image = global::Remote_Computer.Properties.Resources.Good2;
            this.pictureBox11.Location = new System.Drawing.Point(7, 144);
            this.pictureBox11.Name = "pictureBox11";
            this.pictureBox11.Size = new System.Drawing.Size(16, 16);
            this.pictureBox11.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox11.TabIndex = 16;
            this.pictureBox11.TabStop = false;
            // 
            // statusStrip1
            // 
            this.statusStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.pgb1});
            this.statusStrip1.Location = new System.Drawing.Point(0, 726);
            this.statusStrip1.Name = "statusStrip1";
            this.statusStrip1.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.statusStrip1.Size = new System.Drawing.Size(743, 22);
            this.statusStrip1.TabIndex = 19;
            this.statusStrip1.Text = "statusStrip1";
            // 
            // pgb1
            // 
            this.pgb1.Name = "pgb1";
            this.pgb1.Size = new System.Drawing.Size(237, 16);
            this.pgb1.Value = 20;
            // 
            // bwproccess
            // 
            this.bwproccess.WorkerReportsProgress = true;
            this.bwproccess.WorkerSupportsCancellation = true;
            this.bwproccess.DoWork += new System.ComponentModel.DoWorkEventHandler(this.bwproccess_DoWork);
            this.bwproccess.RunWorkerCompleted += new System.ComponentModel.RunWorkerCompletedEventHandler(this.bwproccess_RunWorkerCompleted);
            // 
            // tmrpb
            // 
            this.tmrpb.Enabled = true;
            this.tmrpb.Interval = 1;
            this.tmrpb.Tick += new System.EventHandler(this.tmrpb_Tick);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::Remote_Computer.Properties.Resources.title2;
            this.pictureBox1.Location = new System.Drawing.Point(2, 2);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(667, 127);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.AutoSize;
            this.pictureBox1.TabIndex = 20;
            this.pictureBox1.TabStop = false;
            // 
            // tabmain
            // 
            this.tabmain.Location = new System.Drawing.Point(1, 173);
            this.tabmain.Name = "tabmain";
            this.tabmain.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.tabmain.SelectedTabPage = this.tabpicture;
            this.tabmain.Size = new System.Drawing.Size(739, 516);
            this.tabmain.TabIndex = 22;
            this.tabmain.TabPages.AddRange(new DevExpress.XtraTab.XtraTabPage[] {
            this.tabpicture,
            this.tabmusic,
            this.tabvideo,
            this.txtfont,
            this.tabother});
            // 
            // tabpicture
            // 
            this.tabpicture.Appearance.PageClient.BackColor = System.Drawing.Color.White;
            this.tabpicture.Appearance.PageClient.Options.UseBackColor = true;
            this.tabpicture.Controls.Add(this.pnlparent);
            this.tabpicture.Controls.Add(this.sv1);
            this.tabpicture.Name = "tabpicture";
            this.tabpicture.Size = new System.Drawing.Size(732, 487);
            this.tabpicture.Text = "فایل های تصویری";
            // 
            // pnlparent
            // 
            this.pnlparent.Controls.Add(this.pnlpicture);
            this.pnlparent.Location = new System.Drawing.Point(4, 12);
            this.pnlparent.Name = "pnlparent";
            this.pnlparent.Size = new System.Drawing.Size(707, 472);
            this.pnlparent.TabIndex = 1;
            // 
            // pnlpicture
            // 
            this.pnlpicture.Location = new System.Drawing.Point(3, 3);
            this.pnlpicture.Name = "pnlpicture";
            this.pnlpicture.Size = new System.Drawing.Size(701, 466);
            this.pnlpicture.TabIndex = 0;
            // 
            // sv1
            // 
            this.sv1.Location = new System.Drawing.Point(714, 1);
            this.sv1.Name = "sv1";
            this.sv1.Size = new System.Drawing.Size(17, 486);
            this.sv1.TabIndex = 0;
            this.sv1.Scroll += new System.Windows.Forms.ScrollEventHandler(this.sv1_Scroll);
            // 
            // tabmusic
            // 
            this.tabmusic.Controls.Add(this.panel1);
            this.tabmusic.Controls.Add(this.sv2);
            this.tabmusic.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.tabmusic.Name = "tabmusic";
            this.tabmusic.Size = new System.Drawing.Size(732, 487);
            this.tabmusic.Text = "فایل های صوتی";
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.pnlaudio);
            this.panel1.Location = new System.Drawing.Point(3, 11);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(707, 472);
            this.panel1.TabIndex = 3;
            // 
            // pnlaudio
            // 
            this.pnlaudio.Location = new System.Drawing.Point(3, 3);
            this.pnlaudio.Name = "pnlaudio";
            this.pnlaudio.Size = new System.Drawing.Size(701, 466);
            this.pnlaudio.TabIndex = 0;
            // 
            // sv2
            // 
            this.sv2.Location = new System.Drawing.Point(714, 0);
            this.sv2.Name = "sv2";
            this.sv2.Size = new System.Drawing.Size(17, 486);
            this.sv2.TabIndex = 2;
            this.sv2.Scroll += new System.Windows.Forms.ScrollEventHandler(this.sv2_Scroll);
            // 
            // tabvideo
            // 
            this.tabvideo.Controls.Add(this.panel2);
            this.tabvideo.Controls.Add(this.sv3);
            this.tabvideo.Name = "tabvideo";
            this.tabvideo.Size = new System.Drawing.Size(732, 487);
            this.tabvideo.Text = "فایل های ویدئویی";
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.pnlvideo);
            this.panel2.Location = new System.Drawing.Point(2, 11);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(707, 472);
            this.panel2.TabIndex = 5;
            // 
            // pnlvideo
            // 
            this.pnlvideo.Location = new System.Drawing.Point(3, 3);
            this.pnlvideo.Name = "pnlvideo";
            this.pnlvideo.Size = new System.Drawing.Size(701, 466);
            this.pnlvideo.TabIndex = 0;
            // 
            // sv3
            // 
            this.sv3.Location = new System.Drawing.Point(713, 0);
            this.sv3.Name = "sv3";
            this.sv3.Size = new System.Drawing.Size(17, 486);
            this.sv3.TabIndex = 4;
            this.sv3.Scroll += new System.Windows.Forms.ScrollEventHandler(this.sv3_Scroll);
            // 
            // txtfont
            // 
            this.txtfont.Controls.Add(this.panel3);
            this.txtfont.Controls.Add(this.sv4);
            this.txtfont.Name = "txtfont";
            this.txtfont.Size = new System.Drawing.Size(732, 487);
            this.txtfont.Text = "فونت ها";
            // 
            // panel3
            // 
            this.panel3.Controls.Add(this.pnlttf);
            this.panel3.Location = new System.Drawing.Point(2, 11);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(707, 472);
            this.panel3.TabIndex = 7;
            // 
            // pnlttf
            // 
            this.pnlttf.Location = new System.Drawing.Point(3, 3);
            this.pnlttf.Name = "pnlttf";
            this.pnlttf.Size = new System.Drawing.Size(701, 466);
            this.pnlttf.TabIndex = 0;
            // 
            // sv4
            // 
            this.sv4.Location = new System.Drawing.Point(713, 0);
            this.sv4.Name = "sv4";
            this.sv4.Size = new System.Drawing.Size(17, 486);
            this.sv4.TabIndex = 6;
            this.sv4.Scroll += new System.Windows.Forms.ScrollEventHandler(this.sv4_Scroll);
            // 
            // tabother
            // 
            this.tabother.Controls.Add(this.panel4);
            this.tabother.Controls.Add(this.sv5);
            this.tabother.Name = "tabother";
            this.tabother.Size = new System.Drawing.Size(732, 487);
            this.tabother.Text = "دیگر فایل ها";
            // 
            // panel4
            // 
            this.panel4.Controls.Add(this.pnlfile);
            this.panel4.Location = new System.Drawing.Point(3, 11);
            this.panel4.Name = "panel4";
            this.panel4.Size = new System.Drawing.Size(707, 472);
            this.panel4.TabIndex = 3;
            // 
            // pnlfile
            // 
            this.pnlfile.Location = new System.Drawing.Point(3, 3);
            this.pnlfile.Name = "pnlfile";
            this.pnlfile.Size = new System.Drawing.Size(701, 466);
            this.pnlfile.TabIndex = 0;
            // 
            // sv5
            // 
            this.sv5.Location = new System.Drawing.Point(713, 0);
            this.sv5.Name = "sv5";
            this.sv5.Size = new System.Drawing.Size(17, 486);
            this.sv5.TabIndex = 2;
            this.sv5.Scroll += new System.Windows.Forms.ScrollEventHandler(this.sv5_Scroll);
            // 
            // bwcompile
            // 
            this.bwcompile.WorkerReportsProgress = true;
            this.bwcompile.WorkerSupportsCancellation = true;
            this.bwcompile.DoWork += new System.ComponentModel.DoWorkEventHandler(this.bwcompile_DoWork);
            this.bwcompile.RunWorkerCompleted += new System.ComponentModel.RunWorkerCompletedEventHandler(this.bwcompile_RunWorkerCompleted);
            // 
            // pictureBox2
            // 
            this.pictureBox2.Image = global::Remote_Computer.Properties.Resources.replace;
            this.pictureBox2.Location = new System.Drawing.Point(219, 140);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(24, 24);
            this.pictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox2.TabIndex = 23;
            this.pictureBox2.TabStop = false;
            // 
            // pictureBox3
            // 
            this.pictureBox3.Image = global::Remote_Computer.Properties.Resources.remove;
            this.pictureBox3.Location = new System.Drawing.Point(410, 140);
            this.pictureBox3.Name = "pictureBox3";
            this.pictureBox3.Size = new System.Drawing.Size(24, 24);
            this.pictureBox3.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox3.TabIndex = 24;
            this.pictureBox3.TabStop = false;
            // 
            // frmEditApk
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(743, 748);
            this.ControlBox = false;
            this.Controls.Add(this.pictureBox3);
            this.Controls.Add(this.pictureBox2);
            this.Controls.Add(this.tabmain);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.statusStrip1);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.pictureBox11);
            this.Controls.Add(this.btncancel);
            this.Controls.Add(this.btnadd);
            this.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximumSize = new System.Drawing.Size(749, 776);
            this.MinimumSize = new System.Drawing.Size(749, 776);
            this.Name = "frmEditApk";
            this.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.RightToLeftLayout = true;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "ویرایش قالب فلت 2";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox11)).EndInit();
            this.statusStrip1.ResumeLayout(false);
            this.statusStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.tabmain)).EndInit();
            this.tabmain.ResumeLayout(false);
            this.tabpicture.ResumeLayout(false);
            this.pnlparent.ResumeLayout(false);
            this.tabmusic.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            this.tabvideo.ResumeLayout(false);
            this.panel2.ResumeLayout(false);
            this.txtfont.ResumeLayout(false);
            this.panel3.ResumeLayout(false);
            this.tabother.ResumeLayout(false);
            this.panel4.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox3)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnadd;
        private System.Windows.Forms.Button btncancel;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.PictureBox pictureBox11;
        private System.Windows.Forms.StatusStrip statusStrip1;
        private System.Windows.Forms.ToolStripProgressBar pgb1;
        private System.ComponentModel.BackgroundWorker bwproccess;
        private System.Windows.Forms.Timer tmrpb;
        private System.Windows.Forms.PictureBox pictureBox1;
        private DevExpress.XtraTab.XtraTabControl tabmain;
        private DevExpress.XtraTab.XtraTabPage tabpicture;
        private DevExpress.XtraTab.XtraTabPage tabmusic;
        private DevExpress.XtraTab.XtraTabPage tabvideo;
        private DevExpress.XtraTab.XtraTabPage txtfont;
        private DevExpress.XtraTab.XtraTabPage tabother;
        private System.Windows.Forms.VScrollBar sv1;
        private System.Windows.Forms.Panel pnlparent;
        private System.Windows.Forms.Panel pnlpicture;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Panel pnlaudio;
        private System.Windows.Forms.VScrollBar sv2;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.Panel pnlvideo;
        private System.Windows.Forms.VScrollBar sv3;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.Panel pnlttf;
        private System.Windows.Forms.VScrollBar sv4;
        private System.ComponentModel.BackgroundWorker bwcompile;
        private System.Windows.Forms.Panel panel4;
        private System.Windows.Forms.Panel pnlfile;
        private System.Windows.Forms.VScrollBar sv5;
        private System.Windows.Forms.PictureBox pictureBox2;
        private System.Windows.Forms.PictureBox pictureBox3;
    }
}