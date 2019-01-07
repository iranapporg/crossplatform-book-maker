namespace Remote_Computer
{
    partial class frmlanguage
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
            this.chkandroid = new System.Windows.Forms.RadioButton();
            this.btncancel = new System.Windows.Forms.Button();
            this.btncontinue = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.rbfa = new System.Windows.Forms.RadioButton();
            this.rben = new System.Windows.Forms.RadioButton();
            this.rbar = new System.Windows.Forms.RadioButton();
            this.pictureBox3 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox3)).BeginInit();
            this.SuspendLayout();
            // 
            // chkandroid
            // 
            this.chkandroid.AutoSize = true;
            this.chkandroid.Checked = true;
            this.chkandroid.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.chkandroid.Location = new System.Drawing.Point(-58, 329);
            this.chkandroid.Name = "chkandroid";
            this.chkandroid.Size = new System.Drawing.Size(13, 12);
            this.chkandroid.TabIndex = 37;
            this.chkandroid.TabStop = true;
            this.chkandroid.UseVisualStyleBackColor = true;
            // 
            // btncancel
            // 
            this.btncancel.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btncancel.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btncancel.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btncancel.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.btncancel.Location = new System.Drawing.Point(185, 190);
            this.btncancel.Name = "btncancel";
            this.btncancel.Size = new System.Drawing.Size(91, 34);
            this.btncancel.TabIndex = 30;
            this.btncancel.Text = "انصراف";
            this.btncancel.UseVisualStyleBackColor = false;
            this.btncancel.Click += new System.EventHandler(this.btncancel_Click);
            // 
            // btncontinue
            // 
            this.btncontinue.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btncontinue.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btncontinue.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btncontinue.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.btncontinue.Location = new System.Drawing.Point(13, 190);
            this.btncontinue.Name = "btncontinue";
            this.btncontinue.Size = new System.Drawing.Size(91, 34);
            this.btncontinue.TabIndex = 29;
            this.btncontinue.Text = "ادامه";
            this.btncontinue.UseVisualStyleBackColor = false;
            this.btncontinue.Click += new System.EventHandler(this.btncontinue_Click);
            // 
            // label2
            // 
            this.label2.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(12, 26);
            this.label2.Name = "label2";
            this.label2.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.label2.Size = new System.Drawing.Size(244, 13);
            this.label2.TabIndex = 44;
            this.label2.Text = "کتاب در کدام یک از زبان های زیر ارایه میشود؟";
            // 
            // rbfa
            // 
            this.rbfa.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.rbfa.AutoSize = true;
            this.rbfa.Location = new System.Drawing.Point(174, 61);
            this.rbfa.Name = "rbfa";
            this.rbfa.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.rbfa.Size = new System.Drawing.Size(79, 17);
            this.rbfa.TabIndex = 48;
            this.rbfa.TabStop = true;
            this.rbfa.Text = "زبان فارسی";
            this.rbfa.UseVisualStyleBackColor = true;
            this.rbfa.CheckedChanged += new System.EventHandler(this.rbfa_CheckedChanged);
            // 
            // rben
            // 
            this.rben.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.rben.AutoSize = true;
            this.rben.Location = new System.Drawing.Point(165, 98);
            this.rben.Name = "rben";
            this.rben.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.rben.Size = new System.Drawing.Size(88, 17);
            this.rben.TabIndex = 49;
            this.rben.TabStop = true;
            this.rben.Text = "زبان انگلیسی";
            this.rben.UseVisualStyleBackColor = true;
            this.rben.CheckedChanged += new System.EventHandler(this.rben_CheckedChanged);
            // 
            // rbar
            // 
            this.rbar.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.rbar.AutoSize = true;
            this.rbar.Location = new System.Drawing.Point(182, 131);
            this.rbar.Name = "rbar";
            this.rbar.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.rbar.Size = new System.Drawing.Size(71, 17);
            this.rbar.TabIndex = 50;
            this.rbar.TabStop = true;
            this.rbar.Text = "زبان عربی";
            this.rbar.UseVisualStyleBackColor = true;
            this.rbar.CheckedChanged += new System.EventHandler(this.rbar_CheckedChanged);
            // 
            // pictureBox3
            // 
            this.pictureBox3.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.pictureBox3.Image = global::Remote_Computer.Properties.Resources.Good21;
            this.pictureBox3.Location = new System.Drawing.Point(260, 25);
            this.pictureBox3.Name = "pictureBox3";
            this.pictureBox3.Size = new System.Drawing.Size(16, 16);
            this.pictureBox3.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox3.TabIndex = 43;
            this.pictureBox3.TabStop = false;
            // 
            // frmlanguage
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(289, 241);
            this.Controls.Add(this.rbar);
            this.Controls.Add(this.rben);
            this.Controls.Add(this.rbfa);
            this.Controls.Add(this.btncancel);
            this.Controls.Add(this.btncontinue);
            this.Controls.Add(this.chkandroid);
            this.Controls.Add(this.pictureBox3);
            this.Controls.Add(this.label2);
            this.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "frmlanguage";
            this.ShowIcon = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox3)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.RadioButton chkandroid;
        private System.Windows.Forms.PictureBox pictureBox3;
        private System.Windows.Forms.Button btncancel;
        private System.Windows.Forms.Button btncontinue;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.RadioButton rbfa;
        private System.Windows.Forms.RadioButton rben;
        private System.Windows.Forms.RadioButton rbar;
    }
}