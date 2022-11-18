package Java_Classes;
public class Constants {
	public static String PRE_CONTENT_TEMPLATE = "<!DOCTYPE html>\n"
		+ "<html>\n"
		+ "	<head>\n"
		+ "		<meta charset=\"ISO-8859-1\">\n"
		+ "	"
		+ "	</head>\n"
		+ "	\n"
		+ "	<body>\n"
		+ "		<!-- The big table for the overall site layout -->\n"
		+ "		<table style = \"width:100%; height:100%; position:absolute; left:0; right:0; top:0; bottom:0\">\n"
		+ "		\n"
		+ "			<!-- The header -->\n"
		+ "			<tr>\n"
		+ "				<td colspan = \"2\" style = \"background-color:red\">\n"
		+ "					<h1 style=\"text-align:center\">WCOO's Cat Food Emporium</h1>\n"
		+ "					<h3 style=\"text-align:center\">Quality cat food for great cats!</h3>\n"
		+ "				</td>\n"
		+ "			</tr>\n"
		+ "			\n"
		+ "			<tr style = \"height:100%\">\n"
		+ "				<!-- The product preview table -->\n"
		+ "				<td style = \"width:80%; height:100%; background-color:blue\">\n"
		+ "				\n";

	public static String POST_CONTENT_TEMPLATE = ""
			+ "				</td>\n"
			+ "				\n"
			+ "				<!-- The navigational sidebar -->\n"
			+ "				<td style = \"width:20%; height:100%; vertical-align:top; background-color:green\">\n"
			+ "					<a href = \"Login.jsp\">Login</a><br>\n"
			+ "					<a href = \"ListProducts\">Products List</a><br>\n"
			+ "					<a href = \"Cart\">Cart</a>\n"
			+ "				</td>\n"
			+ "			</tr>\n"
			+ "		</table>\n"
			+ "	</body>\n"
			+ "</html>";
}
