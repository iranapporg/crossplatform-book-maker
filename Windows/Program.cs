using System;
using System .Collections .Generic;
using System .Linq;
using System .Windows .Forms;
using System.Security.Permissions;
namespace Remote_Computer
    {
     class Program
        {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
         [STAThread]
         [SecurityPermission(SecurityAction.Demand, Flags = SecurityPermissionFlag.ControlAppDomain)]
         static void Main()
         {
             AppDomain currentDomain = AppDomain.CurrentDomain;
             currentDomain.UnhandledException += new UnhandledExceptionEventHandler(MyHandler);
             Application.EnableVisualStyles();
             Application.SetCompatibleTextRenderingDefault(false);
             /*try
             {
                 Environment.SetEnvironmentVariable("ABMPath", Application.StartupPath, EnvironmentVariableTarget.Machine);
             }
             catch (Exception)
             {

             }*/
             string res, register;

             try
             {
                 res = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "Java", "false").ToString().ToLower();
             }
             catch (Exception)
             {
                 res = "false";
             }

             try
             {
                 register = Microsoft.Win32.Registry.GetValue("HKEY_CURRENT_USER\\Student", "register", "false").ToString().ToLower();
             }
             catch (Exception)
             {
                 register = "false";
             }

             if (res == "false")
             {
                 frmJava j1 = new frmJava();
                 j1.ShowDialog();
             }
    
              Application.Run(new frmmain());

         }

         static void MyHandler(object sender, UnhandledExceptionEventArgs args)
         {
             Exception e = (Exception)args.ExceptionObject;
             var st = new System.Diagnostics.StackTrace(e, true);
             // Get the top stack frame
             var frame = st.GetFrame(0);
             // Get the line number from the stack frame
             var line = frame.GetFileLineNumber();
             @System.IO.File.WriteAllText("c:\\log.txt", e.Message + " In line " + line.ToString());
         }

        private static bool IsUserAdministrator()
        {
            bool isAdmin;
            try
            {
                System.Security.Principal.WindowsIdentity user = System.Security.Principal.WindowsIdentity.GetCurrent();
                System.Security.Principal.WindowsPrincipal principal = new System.Security.Principal.WindowsPrincipal(user);
                isAdmin = principal.IsInRole(System.Security.Principal.WindowsBuiltInRole.Administrator);
            }
            catch (UnauthorizedAccessException)
            {
                isAdmin = false;
            }
            catch (Exception)
            {
                isAdmin = false;
            }
            return isAdmin;
        }
        }
    }
