import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Put the assignment Header here
 */
public class InterpretParse
{
    public static void main(String args[])
    {
        String sWhatever = "";
        Scanner scanIn = new Scanner(System.in);
        InputStream inputstream = new ByteArrayInputStream(sWhatever.getBytes());
        Parser myParser = new Parser(inputstream);
        do
        {
            System.out.print("> ");
            sWhatever = scanIn.nextLine();
            InputStream stream = new ByteArrayInputStream(sWhatever.getBytes());
            myParser.ReInit(stream);
            if (!sWhatever.equals("quit"))
            {
                //System.out.print(myParser.expr());
                String firstParseRes = null;
                try
                {
                    firstParseRes = myParser.lambdaExpr();
                    System.out.println(firstParseRes);
                } catch (Exception e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                InputStream parseStream = new ByteArrayInputStream(firstParseRes.getBytes());
                myParser.ReInit(parseStream);
                try
                {
                    System.out.print(myParser.expr());
                } catch (Exception e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        } while (!sWhatever.equals("quit"));
        scanIn.close();

    }
}
