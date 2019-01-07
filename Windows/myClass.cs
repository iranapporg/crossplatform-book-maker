using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.IO;
using System.Net;
using System.Security.Cryptography;
using Aspose.Words;
using System.Reflection;
using System.Diagnostics;
using System.Runtime.Serialization.Formatters.Binary;
using System.Net.NetworkInformation;

namespace Remote_Computer
{
    static class myClass
    {
        //for edit apk
        public static string CurrentFilename;
        public static System.Windows.Forms.PictureBox CurrentPicturebox;

        public static System.Windows.Forms.WebBrowser wb1;

        public static string URL = "http://www.iranapp.org/bahonar/";
        public static string FTP = "ftp://iranapp.org/";
        public static string Domain = "iranapp.org";

        public static string Username, Password;

        public static TemplateItem CurrentTI;
        
        public static bool blnUpdate = false;

        public static void setValueRegistery(string key,string value)
        {
            Microsoft.Win32.RegistryKey exampleRegistryKey = Microsoft.Win32.Registry.CurrentUser.CreateSubKey("Student");
            exampleRegistryKey.SetValue(key, value);
            exampleRegistryKey.Close();
        }

        public static string CalculateMD5Hash(string input,bool onlyNumber)
        {
            // step 1, calculate MD5 hash from input
            string res = "";
            MD5 md5 = System.Security.Cryptography.MD5.Create("MD5");
            byte[] inputBytes = System.Text.Encoding.ASCII.GetBytes(changeLang(input));
            byte[] hash = md5.ComputeHash(inputBytes);

            // step 2, convert byte array to hex string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.Length; i++)
            {
                sb.Append(hash[i].ToString("X2"));
            }
            for (int j = 0; j < sb.Length; j++)
            {
                if (Char.IsNumber(sb[j]))
                    res += sb[j];
            }
            if (onlyNumber == true)
                return res.ToString();
            else
                return sb.ToString();
        }

        private static string changeLang(string s1)
        {
            string s2 = s1;
            s2 = s2.Replace("ض", "q");
            s2 = s2.Replace("ص", "w");
            s2 = s2.Replace("ث", "e");
            s2 = s2.Replace("ق", "r");
            s2 = s2.Replace("ف", "t");
            s2 = s2.Replace("غ", "y");
            s2 = s2.Replace("ع", "u");
            s2 = s2.Replace("ه", "i");
            s2 = s2.Replace("خ", "o");
            s2 = s2.Replace("ح", "p");
            s2 = s2.Replace("ج", "[");
            s2 = s2.Replace("چ", "]");
            s2 = s2.Replace("پ", "-");
            s2 = s2.Replace("ش", "a");
            s2 = s2.Replace("س", "s");
            s2 = s2.Replace("ي", "d");
            s2 = s2.Replace("ب", "f");
            s2 = s2.Replace("ل", "g");
            s2 = s2.Replace("ا", "h");
            s2 = s2.Replace("ت", "j");
            s2 = s2.Replace("ن", "k");
            s2 = s2.Replace("م", "l");
            s2 = s2.Replace("ک", ";");
            s2 = s2.Replace("گ", "'");
            s2 = s2.Replace("ظ", "z");
            s2 = s2.Replace("ط", "x");
            s2 = s2.Replace("ز", "c");
            s2 = s2.Replace("ر", "v");
            s2 = s2.Replace("ذ", "b");
            s2 = s2.Replace("د", "n");
            s2 = s2.Replace("ئ", "m");
            s2 = s2.Replace("و", ",");
            s2 = s2.Replace("ی", "d");
            s2 = s2.Replace("آ", "h");
            s2 = s2.Replace("ژ", "c");
            return s2;
        }

        public static void GetImagesInHTMLString(string htmlString,string path,bool isAndroid)
        {
            List<string> images = new List<string>();
            string pattern = @"src\s*=\s*(.+?)\""";
            string file = "";
            Regex rgx = new Regex(pattern, RegexOptions.IgnoreCase);
            MatchCollection matches = rgx.Matches(htmlString);

            for (int i = 0, l = matches.Count; i < l; i++)
            {
                file = @matches[i].Value.Replace(@"src=","").Replace("\""," ").Replace("%20"," ").Replace("file:///","");
                if (File.Exists(file) == true)
                    if (isAndroid == true)
                        File.Copy(file, path + "\\tool\\book\\assets\\" + new FileInfo(file).Name,true);
                    else
                        File.Copy(file, path + "\\tool\\cross\\book\\Files\\" + new FileInfo(file).Name, true);
            }
        }

        public static void GetImagesInHTMLString(string htmlString,string path)
        {
            List<string> images = new List<string>();
            string pattern = @"src\s*=\s*(.+?)\""";
            string file = "";
            Regex rgx = new Regex(pattern, RegexOptions.IgnoreCase);
            MatchCollection matches = rgx.Matches(htmlString);

            for (int i = 0, l = matches.Count; i < l; i++)
            {
                file = @matches[i].Value.Replace(@"src=", "").Replace("\"", " ").Replace("%20", " ").Replace("file:///", "");
                if (File.Exists(file) == true)
                    File.Copy(file, path + "\\" + Path.GetFileName(file), true);
            }
        }

        public static bool IsValidEmailAddress(string s)
        {
            var regex = new Regex(@"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
            return regex.IsMatch(s);
        }

        public static bool IsValidURLAddress(string s)
        {
            var regex = new Regex(@"^(ht|f)tp(s?)\:\/\/[0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*(:(0-9)*)*(\/?)([a-zA-Z0-9\-\.\?\,\'\/\\\+&amp;%\$#_]*)?$");
            return regex.IsMatch(s);
        }

        public static void clearFolder(string FolderName)
        {
            try
            {
                DirectoryInfo dir = new DirectoryInfo(FolderName);
                if (dir.Exists == true)
                {
                    foreach (FileInfo fi in dir.GetFiles())
                    {
                        if (fi.Name.ToLower().IndexOf("Thumbs.db") > -1) continue;
                        fi.Delete();
                    }

                    foreach (DirectoryInfo di in dir.GetDirectories())
                    {
                        clearFolder(di.FullName);
                        di.Delete();
                    }
                }
            }
            catch(Exception)
            {
          
            }
        }

        public static string sendPM(string sEmail,string sBody)
        {
            try
            {
                byte[] dataStream = Encoding.UTF8.GetBytes("email=" + sEmail + "&msg=" + sBody);
                string request = "http://www.iranapp.org/abm/message";
                WebRequest webRequest = WebRequest.Create(request);
                webRequest.Method = "post";
                webRequest.ContentType = "application/x-www-form-urlencoded";
                webRequest.ContentLength = dataStream.Length;
                Stream newStream = webRequest.GetRequestStream();
                newStream.Write(dataStream, 0, dataStream.Length);
                newStream.Close();
                WebResponse webResponse = webRequest.GetResponse();
                StreamReader n = new StreamReader(webResponse.GetResponseStream());
                string res = n.ReadToEnd();
                n.Close();
                return res;
            }
            catch
            {
                return "";
            }

        }

        public static void DirectoryCopy(string sourceDirName, string destDirName, bool copySubDirs)
        {
            DirectoryInfo dir = new DirectoryInfo(sourceDirName);
            DirectoryInfo[] dirs = dir.GetDirectories();

            // If the source directory does not exist, throw an exception.
            if (!dir.Exists)
            {
                throw new DirectoryNotFoundException(
                    "Source directory does not exist or could not be found: "
                    + sourceDirName);
            }

            // If the destination directory does not exist, create it.
            if (!Directory.Exists(destDirName))
            {
                Directory.CreateDirectory(destDirName);
            }


            // Get the file contents of the directory to copy.
            FileInfo[] files = dir.GetFiles();

            foreach (FileInfo file in files)
            {
                // Create the path to the new copy of the file.
                string temppath = Path.Combine(destDirName, file.Name);

                // Copy the file.
                if (File.Exists(temppath) == false)
                 file.CopyTo(temppath, false);
            }

            // If copySubDirs is true, copy the subdirectories.
            if (copySubDirs)
            {

                foreach (DirectoryInfo subdir in dirs)
                {
                    // Create the subdirectory.
                    string temppath = Path.Combine(destDirName, subdir.Name);

                    // Copy the subdirectories.
                    DirectoryCopy(subdir.FullName, temppath, copySubDirs);
                }
            }
        }

        public static void ReplaceInFile(string filePath, string searchText, string replaceText)
        {

            var content = string.Empty;
            using (StreamReader reader = new StreamReader(filePath))
            {
                content = reader.ReadToEnd();
                reader.Close();
            }

            content = Regex.Replace(content, searchText, replaceText);

            using (StreamWriter writer = new StreamWriter(filePath))
            {
                writer.Write(content);
                writer.Close();
            }
        }

        public static void SaveHtml(string path,string output)
        {
            Document d1 = new Document(path);
            d1.Save(output, SaveFormat.Html);
        }

        public static string ExecuteHttp()
        {
            try
            {
                string sParameter = "appCode=102&password=forcheckcommonfunctionstudentsite";
                byte[] dataStream = Encoding.UTF8.GetBytes(sParameter);
                string request = "http://www.iranapp.org/misc/getVersion";
                WebRequest webRequest = WebRequest.Create(request);
                webRequest.Method = "Post";
                webRequest.ContentType = "application/x-www-form-urlencoded";
                webRequest.ContentLength = dataStream.Length;
                Stream newStream = webRequest.GetRequestStream();
                newStream.Write(dataStream, 0, dataStream.Length);
                newStream.Close();
                WebResponse webResponse = webRequest.GetResponse();
                StreamReader n = new StreamReader(webResponse.GetResponseStream());
                string res = n.ReadToEnd();
                n.Close();
                return res;
            }
            catch
            {
                return "";
            }

        }

        public static int getAppVersion()
        {
            Assembly assembly = Assembly.GetExecutingAssembly();
            FileVersionInfo fvi = FileVersionInfo.GetVersionInfo(assembly.Location);
            string version = fvi.FileVersion;
            return Convert.ToInt16(version.Replace(".",""));
        }

        public static string getAppVersion2()
        {
            Assembly assembly = Assembly.GetExecutingAssembly();
            FileVersionInfo fvi = FileVersionInfo.GetVersionInfo(assembly.Location);
            string version = fvi.FileVersion;
            return version;
        }

        public static void Restart()
        {
            ProcessStartInfo proc = new ProcessStartInfo();
            proc.WindowStyle = ProcessWindowStyle.Hidden;
            proc.FileName = "cmd";
            proc.Arguments = "/C shutdown -f -r -t 5";
            Process.Start(proc);
        }

        public static string ExecuteMethod(string url,string field)
        {
            try
            {
                byte[] dataStream = Encoding.UTF8.GetBytes(field);
                string request = url;
                WebRequest webRequest = WebRequest.Create(request);
                webRequest.Method = "post";
                webRequest.ContentType = "application/x-www-form-urlencoded";
                webRequest.ContentLength = dataStream.Length;
                Stream newStream = webRequest.GetRequestStream();
                newStream.Write(dataStream, 0, dataStream.Length);
                newStream.Close();
                WebResponse webResponse = webRequest.GetResponse();
                StreamReader n = new StreamReader(webResponse.GetResponseStream());
                string res = n.ReadToEnd();
                n.Close();
                return res;
            }
            catch (WebException) {
                return "NoNet";
            }
            catch
            {
                return "";
            }
        }

        public static string Number2Curreny(string sNumber)
        {
            for (int i = sNumber.Length; i >= 0; i -= 3)
            {
                if (i == sNumber.Length)
                    continue;
                sNumber = sNumber.Insert(i, ",");
            }
            if (sNumber.StartsWith(","))
                return sNumber.Remove(0, 1);
            else
                return sNumber;
        }

        public static string getFilenameUrl(string url)
        {
            try
            {
                Uri uri = new Uri(url);
                return System.IO.Path.GetFileName(uri.LocalPath);
            }
            catch(Exception) {
                return url;
            }
            
        }

        public static void DownloadRemoteImageFile(string uri, string fileName)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(uri);
            HttpWebResponse response = (HttpWebResponse)request.GetResponse();

            // Check that the remote file was found. The ContentType
            // check is performed since a request for a non-existent
            // image file might be redirected to a 404-page, which would
            // yield the StatusCode "OK", even though the image was not
            // found.
            if ((response.StatusCode == HttpStatusCode.OK ||
                response.StatusCode == HttpStatusCode.Moved ||
                response.StatusCode == HttpStatusCode.Redirect) &&
                response.ContentType.StartsWith("image", StringComparison.OrdinalIgnoreCase))
            {

                // if the remote file was found, download oit
                using (Stream inputStream = response.GetResponseStream())
                using (Stream outputStream = File.OpenWrite(fileName))
                {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    do
                    {
                        bytesRead = inputStream.Read(buffer, 0, buffer.Length);
                        outputStream.Write(buffer, 0, bytesRead);
                    } while (bytesRead != 0);
                }
            }
        }

        public static bool CheckForInternetConnection()
        {
            return true;
            //try
            //{
            //    Ping myPing = new Ping();
            //    String host = "google.com";
            //    byte[] buffer = new byte[32];
            //    int timeout = 1000;
            //    PingOptions pingOptions = new PingOptions();
            //    PingReply reply = myPing.Send(host, timeout, buffer, pingOptions);
            //    return (reply.Status == IPStatus.Success);
            //}
            //catch (Exception)
            //{
            //    return false;
            //}
            try
            {
                using (var client = new WebClient())
                using (var stream = client.OpenRead("http://www.google.com"))
                {
                    return true;
                }
            }
            catch
            {
                return false;
            }
        }

        public static string getRemoteFileSize(string filename)
        {
            System.Net.WebRequest req = System.Net.HttpWebRequest.Create(filename);
            req.Method = "HEAD";
            using (System.Net.WebResponse resp = req.GetResponse())
            {
                int ContentLength;
                if (int.TryParse(resp.Headers.Get("Content-Length"), out ContentLength))
                {
                    return (ContentLength / 1024).ToString();
                }
            }
            return "";
        }

        public static void OpenFile(string filename)
        {
            Process photoViewer = new Process();
            photoViewer.StartInfo.FileName = filename;
            photoViewer.Start();
        }

        public static List<string> DirSearch(string sDir)
        {
            List<string> l1 = new List<string>();

            try
            {
                foreach (string d in Directory.GetDirectories(sDir))
                {
                    foreach (string f in Directory.GetFiles(d))
                    {
                        string ext = Path.GetExtension(f).ToLower();
                        l1.Add(f);
                    }
                    DirSearch(d);
                }
            }
            catch (System.Exception)
            {

            }
            return l1;
        }

        public static string FromHex(string hex)
        {
            hex = hex.Replace("-", "");
            byte[] raw = new byte[hex.Length / 2];
            for (int i = 0; i < raw.Length; i++)
            {
                raw[i] = Convert.ToByte(hex.Substring(i * 2, 2), 16);
            }
            return UnicodeEncoding.UTF8.GetString(raw);
        }

        public static string getServerUrl()
        {
            return FromHex("687474703a2f2f7777772e6972616e6170702e6f72672f61626d2f");
        }

        public static string registerUser(string name,string phone,string mail,bool state)
        {
            try
            {
                string version = getAppVersion().ToString();
                string format = String.Format("name={0}&phone={1}&mail={2}&version={3}&state={4}", name, phone, mail, version,(state == true ? "login" : "register"));
                string res = ExecuteMethod(FromHex("687474703a2f2f7777772e6972616e6170702e6f72672f61626d2f") + "register", format);
                return res;
            }
            catch (Exception) { return ""; }
        }

        public static string registerBook(string title, string parent,string author,string desc,string size,string lang,string cover,string username,string password,string id,string isEdit)
        {
            try
            {
                string format = String.Format("u={0}&p={1}&title={2}&size={3}&parent={4}&author={5}&desc={6}&lang={7}&cover={8}&id={9}&isedit={10}", username,password,title,size,parent,author,desc,lang,cover,id,isEdit);
                string res = ExecuteMethod(myClass.URL + "index.php/server/add_book", format);
                return res;
            }
            catch (Exception) { return ""; }
        }

        public static string getFreeID()
        {
            try
            {
                string res = ExecuteMethod(FromHex("687474703a2f2f7777772e6972616e6170702e6f72672f61626d2f") + "freeid", "");
                return res;
            }
            catch (Exception) { return ""; }
        }
    }
}
