namespace Remote_Computer
{
    partial class Template
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
            this.groupBox5 = new System.Windows.Forms.GroupBox();
            this.btnpurchase = new System.Windows.Forms.Button();
            this.btnmore = new System.Windows.Forms.Button();
            this.lbltitle = new System.Windows.Forms.Label();
            this.lblprice = new System.Windows.Forms.Label();
            this.picture1 = new System.Windows.Forms.PictureBox();
            this.groupBox5.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picture1)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox5
            // 
            this.groupBox5.Controls.Add(this.btnpurchase);
            this.groupBox5.Controls.Add(this.btnmore);
            this.groupBox5.Controls.Add(this.lbltitle);
            this.groupBox5.Controls.Add(this.lblprice);
            this.groupBox5.Controls.Add(this.picture1);
            this.groupBox5.Location = new System.Drawing.Point(3, 3);
            this.groupBox5.Name = "groupBox5";
            this.groupBox5.Size = new System.Drawing.Size(146, 273);
            this.groupBox5.TabIndex = 1;
            this.groupBox5.TabStop = false;
            // 
            // btnpurchase
            // 
            this.btnpurchase.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btnpurchase.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnpurchase.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnpurchase.Location = new System.Drawing.Point(12, 235);
            this.btnpurchase.Name = "btnpurchase";
            this.btnpurchase.Size = new System.Drawing.Size(59, 28);
            this.btnpurchase.TabIndex = 15;
            this.btnpurchase.Text = "خرید";
            this.btnpurchase.UseVisualStyleBackColor = false;
            this.btnpurchase.Click += new System.EventHandler(this.btnpurchase_Click);
            // 
            // btnmore
            // 
            this.btnmore.BackColor = System.Drawing.Color.WhiteSmoke;
            this.btnmore.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnmore.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnmore.Location = new System.Drawing.Point(76, 235);
            this.btnmore.Name = "btnmore";
            this.btnmore.Size = new System.Drawing.Size(59, 28);
            this.btnmore.TabIndex = 1;
            this.btnmore.Text = "توضیحات";
            this.btnmore.UseVisualStyleBackColor = false;
            this.btnmore.Click += new System.EventHandler(this.btnmore_Click);
            // 
            // lbltitle
            // 
            this.lbltitle.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbltitle.Location = new System.Drawing.Point(6, 175);
            this.lbltitle.Name = "lbltitle";
            this.lbltitle.Size = new System.Drawing.Size(134, 27);
            this.lbltitle.TabIndex = 14;
            this.lbltitle.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblprice
            // 
            this.lblprice.Font = new System.Drawing.Font("Tahoma", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblprice.Location = new System.Drawing.Point(3, 207);
            this.lblprice.Name = "lblprice";
            this.lblprice.Size = new System.Drawing.Size(140, 13);
            this.lblprice.TabIndex = 13;
            this.lblprice.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // picture1
            // 
            this.picture1.Cursor = System.Windows.Forms.Cursors.Hand;
            this.picture1.Location = new System.Drawing.Point(16, 18);
            this.picture1.Name = "picture1";
            this.picture1.Size = new System.Drawing.Size(116, 155);
            this.picture1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.picture1.TabIndex = 0;
            this.picture1.TabStop = false;
            this.picture1.Click += new System.EventHandler(this.picture1_Click);
            // 
            // Template
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.groupBox5);
            this.Name = "Template";
            this.Size = new System.Drawing.Size(155, 281);
            this.groupBox5.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picture1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox5;
        private System.Windows.Forms.Button btnpurchase;
        private System.Windows.Forms.Button btnmore;
        private System.Windows.Forms.Label lbltitle;
        private System.Windows.Forms.Label lblprice;
        private System.Windows.Forms.PictureBox picture1;
    }
}
