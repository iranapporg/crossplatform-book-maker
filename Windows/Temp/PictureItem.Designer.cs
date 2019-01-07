namespace Remote_Computer
{
    partial class PictureItem
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
            this.pbPicture = new System.Windows.Forms.PictureBox();
            this.btnreplace = new System.Windows.Forms.Button();
            this.btndelete = new System.Windows.Forms.Button();
            this.fd1 = new System.Windows.Forms.OpenFileDialog();
            this.pbPictureW = new System.Windows.Forms.PictureBox();
            this.pbPictureH = new System.Windows.Forms.PictureBox();
            this.gb1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbPicture)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbPictureW)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbPictureH)).BeginInit();
            this.SuspendLayout();
            // 
            // gb1
            // 
            this.gb1.Controls.Add(this.pbPictureH);
            this.gb1.Controls.Add(this.pbPictureW);
            this.gb1.Controls.Add(this.pbPicture);
            this.gb1.Location = new System.Drawing.Point(9, 6);
            this.gb1.Name = "gb1";
            this.gb1.RightToLeft = System.Windows.Forms.RightToLeft.Yes;
            this.gb1.Size = new System.Drawing.Size(155, 155);
            this.gb1.TabIndex = 0;
            this.gb1.TabStop = false;
            // 
            // pbPicture
            // 
            this.pbPicture.Cursor = System.Windows.Forms.Cursors.Hand;
            this.pbPicture.Location = new System.Drawing.Point(33, 35);
            this.pbPicture.Name = "pbPicture";
            this.pbPicture.Size = new System.Drawing.Size(92, 92);
            this.pbPicture.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pbPicture.TabIndex = 0;
            this.pbPicture.TabStop = false;
            this.pbPicture.Visible = false;
            this.pbPicture.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pbPictureH_MouseDown);
            // 
            // btnreplace
            // 
            this.btnreplace.BackColor = System.Drawing.SystemColors.Control;
            this.btnreplace.BackgroundImage = global::Remote_Computer.Properties.Resources.replace;
            this.btnreplace.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.btnreplace.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnreplace.Font = new System.Drawing.Font("Tahoma", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(0)));
            this.btnreplace.Location = new System.Drawing.Point(4, 4);
            this.btnreplace.Name = "btnreplace";
            this.btnreplace.Size = new System.Drawing.Size(24, 22);
            this.btnreplace.TabIndex = 41;
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
            this.btndelete.Location = new System.Drawing.Point(4, 30);
            this.btndelete.Name = "btndelete";
            this.btndelete.Size = new System.Drawing.Size(24, 22);
            this.btndelete.TabIndex = 42;
            this.btndelete.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.btndelete.UseVisualStyleBackColor = false;
            this.btndelete.Click += new System.EventHandler(this.btndelete_Click);
            // 
            // fd1
            // 
            this.fd1.Title = "انتخاب فایل";
            // 
            // pbPictureW
            // 
            this.pbPictureW.Cursor = System.Windows.Forms.Cursors.Hand;
            this.pbPictureW.Location = new System.Drawing.Point(15, 45);
            this.pbPictureW.Name = "pbPictureW";
            this.pbPictureW.Size = new System.Drawing.Size(125, 74);
            this.pbPictureW.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pbPictureW.TabIndex = 1;
            this.pbPictureW.TabStop = false;
            this.pbPictureW.Visible = false;
            this.pbPictureW.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pbPictureH_MouseDown);
            // 
            // pbPictureH
            // 
            this.pbPictureH.Cursor = System.Windows.Forms.Cursors.Hand;
            this.pbPictureH.Location = new System.Drawing.Point(36, 27);
            this.pbPictureH.Name = "pbPictureH";
            this.pbPictureH.Size = new System.Drawing.Size(82, 105);
            this.pbPictureH.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pbPictureH.TabIndex = 2;
            this.pbPictureH.TabStop = false;
            this.pbPictureH.Visible = false;
            this.pbPictureH.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pbPictureH_MouseDown);
            // 
            // PictureItem
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.btndelete);
            this.Controls.Add(this.btnreplace);
            this.Controls.Add(this.gb1);
            this.Name = "PictureItem";
            this.Size = new System.Drawing.Size(174, 172);
            this.gb1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pbPicture)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbPictureW)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbPictureH)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox gb1;
        private System.Windows.Forms.PictureBox pbPicture;
        private System.Windows.Forms.Button btnreplace;
        private System.Windows.Forms.Button btndelete;
        private System.Windows.Forms.OpenFileDialog fd1;
        private System.Windows.Forms.PictureBox pbPictureH;
        private System.Windows.Forms.PictureBox pbPictureW;
    }
}
