using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.SQLite;
using System.Windows.Forms;
using System.Data;
using System.Drawing;
using System.Data.SqlClient;
namespace Remote_Computer
{
    class db
    {
        SQLiteConnection con1;
        SQLiteCommand s1 = new SQLiteCommand();
        public DataTable DataSource { get; set; }
        public String UnitName { get; set; }
        public String ParentId { get; set; }
        public String ID { get; set; }

        public db(string sBankname)
        {
            con1 = new SQLiteConnection(@"data source=" + Application.StartupPath + "\\" + sBankname + "\\" + "bank.db");
            con1.Open();

            s1.CommandText = @"CREATE TABLE tbl_information (sProjectID  TEXT,sURL  TEXT,sEmail  TEXT,sTitle  TEXT,sContent  TEXT,sSMS  TEXT,sAuthor TEXT,sVersion TEXT,sHelp TEXT,sRss TEXT,sProjectName TEXT,sID TEXT);";
            try
            {
                s1.ExecuteNonQuery();
            }
            catch (Exception)
            {

            }
        }

        public void updateIDProject(string id)
        {
            try
            {
                s1.CommandText = "UPDATE tbl_information SET sID = '" + id + "'";
                s1.Connection = con1;
                s1.ExecuteNonQuery();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
            }
        }

        public string getProjectID()
        {
            try
            {
                return QueryScaler("SELECT sID FROM tbl_information LIMIT 1").ToString();
            }
            catch (Exception) { return ""; }
        }

        public int getLastInsertID()
        {
            try
            {
                s1.CommandText = "SELECT last_insert_rowid()";
                s1.Connection = con1;
                object ret = s1.ExecuteScalar();
                return Convert.ToInt32(ret);

            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return 0;
            }
        }

        public int countRecord()
        {
            try
            {
                DataTable d1 = new DataTable();
                SQLiteDataAdapter sql1 = new SQLiteDataAdapter("SELECT sText FROM tbl_topic", con1);
                sql1.Fill(d1);
                return d1.Rows.Count;
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return 0;
            }
        }

        public void RunQuery(string strQuery)
        {
            try
            {
                s1.CommandText = strQuery;
                s1.Connection = con1;
                s1.ExecuteNonQuery();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
            }
        }

        public int countRecord(string sTitle)
        {
            try
            {
                DataTable d1 = new DataTable();
                SQLiteDataAdapter sql1 = new SQLiteDataAdapter("SELECT sText FROM tbl_app WHERE sTitle ='" + sTitle + "'", con1);
                sql1.Fill(d1);
                return d1.Rows.Count;
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return 0;
            }
        }

        public string[] statistic()
        {
            DataTable d1 = new DataTable();
            SQLiteDataAdapter sql1 = new SQLiteDataAdapter("SELECT sGroupName FROM tbl_app GROUP BY sGroupName", con1);
            sql1.Fill(d1);
            string rs = d1.Rows.Count.ToString();
            string rs1 = QueryScaler("SELECT COUNT(*) FROM tbl_app").ToString();
            string[] s = new string[2] { rs, rs1 };
            return s;
        }

        public void Update(string sTitle, string sGroupName)
        {
            try
            {
                s1.CommandText = "UPDATE tbl_app SET sGroupName = '" + sGroupName + "' WHERE sTitle ='" + sTitle + "'";
                s1.Connection = con1;
                s1.ExecuteNonQuery();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
            }
        }

        public void Update(string sField, string sText, string sID)
        {
            try
            {
                s1.CommandText = String.Format("UPDATE tbl_topic SET {0} = '{1}' WHERE sID ='{2}'", sField, sText.Replace("'", "-"), sID);
                s1.Connection = con1;
                s1.ExecuteNonQuery();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
            }
        }

        public string getBody(string sID)
        {
            try
            {
                DataTable d1 = new DataTable();
                SQLiteDataAdapter sql1 = new SQLiteDataAdapter("SELECT sText FROM tbl_topic WHERE sID = '" + sID + "'", con1);
                sql1.Fill(d1);
                return d1.Rows[0][0].ToString();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return "";
            }
        }

        public object QueryScaler(string sql)
        {
            try
            {
                s1.CommandText = sql;
                s1.Connection = con1;
                object ret = s1.ExecuteScalar();
                return ret;

            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return 0;
            }
        }

        public string getField(string sField, string sID)
        {
            try
            {
                DataTable d1 = new DataTable();
                SQLiteDataAdapter sql1 = new SQLiteDataAdapter("SELECT " + sField + " FROM tbl_topic WHERE sID = '" + sID + "'", con1);
                sql1.Fill(d1);
                return d1.Rows[0][0].ToString();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return "";
            }
        }

        public string getFieldInformation(string sField)
        {
            try
            {
                DataTable d1 = new DataTable();
                SQLiteDataAdapter sql1 = new SQLiteDataAdapter("SELECT " + sField + " FROM tbl_information", con1);
                sql1.Fill(d1);
                return d1.Rows[0][0].ToString();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return "";
            }
        }

        public string getPassword(string ch)
        {
            try
            {
                DataTable d1 = new DataTable();
                SQLiteDataAdapter sql1 = new SQLiteDataAdapter("SELECT sPassword1 FROM sPasswords WHERE sChapterTitle = '" + ch + "'", con1);
                sql1.Fill(d1);
                return d1.Rows[0][0].ToString();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return "";
            }
        }

        public int[] SelectItems(TreeView t1)
        {
            int i1 = 0;
            DataTable d1 = new DataTable();
            DataTable d2 = new DataTable();
            SQLiteDataAdapter sql1 = new SQLiteDataAdapter("SELECT * FROM tbl_category", con1);
            t1.Nodes.Clear();

            try
            {
                sql1.Fill(d1);
                DataSource = d1;

                ID = "sID";
                UnitName = "sTitle";
                ParentId = "sParent";

                foreach (DataRow row in DataSource.Rows)
                {
                    TreeNode treeRoot;
                    treeRoot = new TreeNode();
                    treeRoot.Text = row[UnitName].ToString();
                    treeRoot.Tag = row[ID].ToString();
                    treeRoot.ExpandAll();

                    if (row[ParentId].ToString() == "0")
                    {
                        t1.Nodes.Add(treeRoot);
                        GetTopics(t1, row[ID].ToString(), treeRoot);
                        CreateNodesOfParent(Convert.ToInt32(row[ID]), treeRoot, t1);
                    }
                }
            }

            catch (Exception e1)
            {

            }

            int[] int1 = new int[2];
            int1[0] = d1.Rows.Count;
            int1[1] = i1;
            return int1;

        }

        public void CreateNodesOfParent(int iParent, TreeNode pNode,TreeView t1)
        {

            DataView dvwData = new DataView(DataSource);
            dvwData.RowFilter = String.Format("{0}={1}", ParentId, iParent);

            foreach (DataRowView Row in dvwData)
            {
                TreeNode zNode = pNode.Nodes.Add(Row[UnitName].ToString());
                GetTopics(t1, Row[ID].ToString(),zNode);
                zNode.Tag = Row[ID].ToString();
                CreateNodesOfParent(Int32.Parse(Row[ID].ToString()), zNode,t1);
            }

        }

        private void GetTopics(TreeView t1,string ParentID,TreeNode tn2)
        {

            DataTable d2 = new DataTable();
            SQLiteDataAdapter sql1 = new SQLiteDataAdapter("SELECT * FROM tbl_topic WHERE sParent ='" + ParentID + "'", con1);
            sql1.Fill(d2);

            foreach (DataRow row in d2.Rows)
            {
                TreeNode tn1 = new TreeNode(row["sTitle"].ToString());
                tn2.Nodes.Add(tn1);
                tn1.Tag = new string[] {row["sID"].ToString()};
                tn1.ForeColor = Color.Green;
            }

        }

        public int AddTopic(string sTopic, string sText, string sParent, string sRank, string sType)
        {
            try
            {
                s1.CommandText = String.Format("INSERT INTO tbl_topic(sTitle,sText,sRate,sParent,sType) VALUES('{0}','{1}','{2}','{3}','{4}')", sTopic, sText.Replace("'", "-"), sRank, sParent, sType);
                s1.Connection = con1;
                s1.ExecuteNonQuery();
                return getLastInsertID();
            }
            catch (Exception e1)
            {
                return -1;
            }
        }

        public int AddCategory(string sTitle,string sParent)
        {
            try
            {
                s1.CommandText = String.Format("INSERT INTO tbl_category(sTitle,sParent) VALUES('{0}','{1}')", sTitle,sParent);
                s1.Connection = con1;
                s1.ExecuteNonQuery();
                return getLastInsertID();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return 0;
            }
        }

        public int AddFooter(string sTitle, string sBody,string TopicID)
        {
            try
            {
                s1.CommandText = String.Format("INSERT INTO tbl_footer(sFooterID,sTopicID,sText) VALUES('{0}','{1}','{2}')", sTitle, TopicID,sBody);
                s1.Connection = con1;
                s1.ExecuteNonQuery();
                return getLastInsertID();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return 0;
            }
        }

        public DataTable getRow(string query)
        {
            try
            {
                DataTable d1 = new DataTable();
                SQLiteDataAdapter sql1 = new SQLiteDataAdapter(query, con1);
                sql1.Fill(d1);
                return d1;
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
                return null;
            }
        }

        public void DeleteTopic(string sID)
        {
            try
            {
                s1.CommandText = "DELETE FROM tbl_topic WHERE sID = '" + sID + "'";
                s1.Connection = con1;
                s1.ExecuteNonQuery();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
            }
        }

        public void DeleteFooter(string sID, string TopicID)
        {
            try
            {
                s1.CommandText = "DELETE FROM tbl_footer WHERE sFooterID = '" + sID + "' AND sTopicID ='" + TopicID + "'";
                s1.Connection = con1;
                s1.ExecuteNonQuery();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
            }
        }

        public void DeleteCategory(string sID)
        {
            try
            {
                s1.CommandText = "DELETE FROM tbl_category WHERE sID = '" + sID + "' or sParent = '" + sID + "'";
                s1.Connection = con1;
                s1.ExecuteNonQuery();
                s1.CommandText = "DELETE FROM tbl_topic WHERE sParent = '" + sID + "'";
                s1.Connection = con1;
                s1.ExecuteNonQuery();
            }
            catch (Exception e1)
            {
                writeError(e1.Message, "275");
            }
        }

        public void stopConnect()
        {
            con1.Close();
            s1.Dispose();
            con1.Dispose();
        }

        public void writeError(string message, string line)
        {
            try
            {
                System.IO.File.AppendAllText(Application.StartupPath + "\\log.txt", "Error In line " + line + " with message: " + message + "\r\n");
            }
            catch (Exception)
            {

            }
        }
    }
}
