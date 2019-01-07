namespace Remote_Computer
{
    partial class AudioItem
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.gb1 = new System.Windows.Forms.GroupBox();
            this.btnreplace = new System.Windows.Forms.Button();
            this.btndelete = new System.Windows.Forms.Button();
            this.txtsize = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.txtname = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.btnplay = new System.Windows.Forms.Button();
            this.fd1 = new System.Windows.Forms.OpenFileDialog();
            this.gb1.SuspendLayout();
            this.SuspendLayout();
            // 
            // gb1
            // 
            this.gb1.Controls.Add(this.btnreplace);
            this.gb1.Controls.Add(this.btndelete);
            this.gb1.Controls.Add(this.txtsize);
            this.gb1.Controls.Add(this.label2);
            this.gb1.Controls.Add(this.txtname);
            this.gb1.Controls.Add(this.label1);
            this.gb1.Controls.Add(this.btnplay);
            this.gb1.Location = new System.Drawing.Point(6, 6);
            this.gb1.Name = "gb1";
            this.gb1.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.gb1.Size = new System.Drawing.Size(685, 84);
            this.gb1.TabIndex = 0;
            this.gb1.TabStop = false;
            // 
            // btnreplace
            // 
            this.btnreplace.BackColor = System.Drawing.SystemColors.Control;
            this.btnreplace.BackgroundImage = global::Remote_Computer.Properties.Resources.replace;
            this.btnreplace.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnreplace.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnreplace.Font = new System.Drawing.Font("Tahoma", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(0)));
            this.btnreplace.Location = new System.Drawing.Point(53, 31);
            this.btnreplace.Name = "btnreplace";
            this.btnreplace.Size = new System.Drawing.Size(34, 34);
            this.btnreplace.TabIndex = 38;
            this.btnreplace.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.btnreplace.UseVisualStyleBackColor = false;
            this.btnreplace.Click += new System.EventHandler(this.btnreplace_Click);
            // 
            // btndelete
            // 
            this.btndelete.BackColor = System.Drawing.SystemColors.Control;
            this.btndelete.BackgroundImage = global::Remote_Computer.Properties.Resources.remove;
            this.btndelete.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.btndelete.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btndelete.Font = new System.Drawing.Font("Tahoma", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(0)));
            this.btndelete.Location = new System.Drawing.Point(13, 31);
            this.btndelete.Name = "btndelete";
            this.btndelete.Size = new System.Drawing.Size(34, 34);
            this.btndelete.TabIndex = 37;
            this.btndelete.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.btndelete.UseVisualStyleBackColor = false;
            this.btndelete.Click += new System.EventHandler(this.btndelete_Click);
            // 
            // txtsize
            // 
            this.txtsize.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(247)))), ((int)(((byte)(245)))), ((int)(((byte)(241)))));
            this.txtsize.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtsize.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.txtsize.Location = new System.Drawing.Point(146, 38);
            this.txtsize.Name = "txtsize";
            this.txtsize.ReadOnly = true;
            this.txtsize.Size = new System.Drawing.Size(159, 14);
            this.txtsize.TabIndex = 36;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(309, 36);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(36, 13);
            this.label2.TabIndex = 35;
            this.label2.Text = "حجم :";
            // 
            // txtname
            // 
            this.txtname.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(247)))), ((int)(((byte)(245)))), ((int)(((byte)(241)))));
            this.txtname.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtname.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.txtname.Location = new System.Drawing.Point(353, 39);
            this.txtname.Name = "txtname";
            this.txtname.ReadOnly = true;
            this.txtname.Size = new System.Drawing.Size(219, 14);
            this.txtname.TabIndex = 34;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(576, 39);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(49, 13);
            this.label1.TabIndex = 33;
            this.label1.Text = "نام فایل :";
            // 
            // btnplay
            // 
            this.btnplay.BackColor = System.Drawing.SystemColors.Control;
            this.btnplay.BackgroundImage = global::Remote_Computer.Properties.Resources.play;
            this.btnplay.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.btnplay.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnplay.Font = new System.Drawing.Font("Tahoma", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(0)));
            this.btnplay.Location = new System.Drawing.Point(631, 27);
            this.btnplay.Name = "btnplay";
            this.btnplay.Size = new System.Drawing.Size(41, 38);
            this.btnplay.TabIndex = 32;
            this.btnplay.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.btnplay.UseVisualStyleBackColor = false;
            this.btnplay.Click += new System.EventHandler(this.btnplay_Click);
            // 
            // fd1
            // 
            this.fd1.AddExtension = false;
            this.fd1.Title = "انتخاب فایل";
            this.fd1.FileOk += new System.ComponentModel.CancelEventHandler(this.fd1_FileOk);
            // 
            // AudioItem
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.gb1);
            this.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.Name = "AudioItem";
            this.Size = new System.Drawing.Size(697, 99);
            this.gb1.ResumeLayout(false);
            this.gb1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox gb1;
        private System.Windows.Forms.TextBox txtsize;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtname;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btnplay;
        private System.Windows.Forms.Button btndelete;
        private System.Windows.Forms.Button btnreplace;
        private System.Windows.Forms.OpenFileDialog fd1;
    }
}
