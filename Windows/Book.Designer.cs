namespace Remote_Computer
{
    partial class Book
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
            this.txtbookname = new System.Windows.Forms.TextBox();
            this.txtauthor = new System.Windows.Forms.TextBox();
            this.btndelete = new System.Windows.Forms.Button();
            this.piccover = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.piccover)).BeginInit();
            this.SuspendLayout();
            // 
            // txtbookname
            // 
            this.txtbookname.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.txtbookname.BackColor = System.Drawing.SystemColors.ControlLightLight;
            this.txtbookname.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtbookname.Font = new System.Drawing.Font("Tahoma", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.txtbookname.Location = new System.Drawing.Point(8, 23);
            this.txtbookname.Name = "txtbookname";
            this.txtbookname.ReadOnly = true;
            this.txtbookname.Size = new System.Drawing.Size(250, 17);
            this.txtbookname.TabIndex = 3;
            this.txtbookname.Tag = "s";
            // 
            // txtauthor
            // 
            this.txtauthor.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.txtauthor.BackColor = System.Drawing.SystemColors.ControlLightLight;
            this.txtauthor.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtauthor.Font = new System.Drawing.Font("Tahoma", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.txtauthor.Location = new System.Drawing.Point(8, 46);
            this.txtauthor.Name = "txtauthor";
            this.txtauthor.ReadOnly = true;
            this.txtauthor.Size = new System.Drawing.Size(250, 15);
            this.txtauthor.TabIndex = 4;
            this.txtauthor.Tag = "s";
            // 
            // btndelete
            // 
            this.btndelete.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btndelete.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btndelete.Location = new System.Drawing.Point(15, 87);
            this.btndelete.Name = "btndelete";
            this.btndelete.Size = new System.Drawing.Size(72, 23);
            this.btndelete.TabIndex = 52;
            this.btndelete.Text = "حذف";
            this.btndelete.UseVisualStyleBackColor = false;
            this.btndelete.Click += new System.EventHandler(this.btndelete_Click);
            // 
            // piccover
            // 
            this.piccover.Image = global::Remote_Computer.Properties.Resources.cover;
            this.piccover.Location = new System.Drawing.Point(274, 11);
            this.piccover.Name = "piccover";
            this.piccover.Size = new System.Drawing.Size(76, 99);
            this.piccover.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.piccover.TabIndex = 0;
            this.piccover.TabStop = false;
            // 
            // Book
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ControlLightLight;
            this.Controls.Add(this.btndelete);
            this.Controls.Add(this.txtauthor);
            this.Controls.Add(this.txtbookname);
            this.Controls.Add(this.piccover);
            this.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(178)));
            this.Name = "Book";
            this.Size = new System.Drawing.Size(359, 120);
            ((System.ComponentModel.ISupportInitialize)(this.piccover)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox piccover;
        private System.Windows.Forms.TextBox txtbookname;
        private System.Windows.Forms.TextBox txtauthor;
        private System.Windows.Forms.Button btndelete;
    }
}
