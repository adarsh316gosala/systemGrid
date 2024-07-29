package SystemGrid.com.org;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.InetAddress;

@WebServlet("/StatusServlet")
public class StatusServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // HTML header
        out.println("<html><head><title>System Status</title>");
        out.println("<style>");
        out.println("table { width: 80%; border-collapse: collapse; margin: 20px auto; }");
        out.println("th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println(".online { color: green; font-weight: bold; }");
        out.println(".offline { color: red; font-weight: bold; }");
        out.println("</style>");
        out.println("</head><body>");

        // Table header
        out.println("<h2>System Status</h2>");
        out.println("<table>");
        out.println("<tr><th>System Name</th><th>IP Address</th><th>Status</th></tr>");

        try {
            // Loop through systems 1 to 10
            for (int i = 1; i <= 10; i++) {
                String systemName = "System" + i;
                String ipAddress = "192.168.10." + i;
                InetAddress inet = InetAddress.getByName(ipAddress);
                boolean reachable = inet.isReachable(5000);

                out.println("<tr>");
                out.println("<td>" + systemName + "</td>");
                out.println("<td>" + ipAddress + "</td>");

                if (reachable) {
                    out.println("<td class='online'>Online</td>");
                    System.out.println(systemName + " (" + ipAddress + ") is reachable.");
                } else {
                    out.println("<td class='offline'>Offline</td>");
                    System.out.println(systemName + " (" + ipAddress + ") is not reachable.");
                }

                out.println("</tr>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close table and body
        out.println("</table>");
        out.println("</body></html>");
    }
}
